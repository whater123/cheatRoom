package com.my.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.my.constant.SystemConstant;
import com.my.dao.UserMapper;
import com.my.pojo.base.User;
import com.my.pojo.view.KeyShow;
import com.my.service.AdminService;
import com.my.service.UserService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.security.Principal;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@CrossOrigin
@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    UserMapper userMapper;
    @Autowired
    AdminService adminService;
    @Autowired
    UserService userService;

    @ResponseBody
    @RequestMapping(value = "/getOnline", method = RequestMethod.GET)
    public Set<String> getMessage() {
        return SystemConstant.loginSet;
    }

    @ResponseBody
    @RequestMapping(value = "/setDefault", method = RequestMethod.POST)
    public String setDefault(String userName) {
        // 上传后的路径
        String filePath = SystemConstant.picturePath;
        // 新文件名
        String fileName = userName + ".jpg";
        File dest = new File(filePath + fileName);
        if (dest.getParentFile().exists()) {
            dest.delete();

            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("user_name",userName);
            User user = userMapper.selectOne(queryWrapper);
            user.setHeadName("default.jpg");
            userMapper.updateById(user);
            return "设置成功";
        }
        else {
            return "用户名不存在";
        }
    }

    @ResponseBody
    @RequestMapping(value = "/changeRedis", method = RequestMethod.POST)
    public String changeRedis() {
        SystemConstant.openRedis = !SystemConstant.openRedis;
        if (SystemConstant.openRedis){
            return "保留历史消息已开启";
        }
        else {
            return "保留历史消息已关闭";
        }
    }

    //生成会员秘钥
    @ResponseBody
    @RequestMapping(value = "/getVipKey")
    public String getVipKey(){
        return adminService.getVipKey();
    }

    //获取会员秘钥
    @RequestMapping(value = "/getAllKeys")
    public String getAllKeys(HttpServletRequest request){
        List<KeyShow> allKeys = adminService.getAllKeys();
        request.setAttribute("keyList",allKeys);
        return "keyList";
    }

    @RequestMapping(value = "/getAllInfo")
    public String getAllInfo(Principal principal, HttpServletRequest request, String userName){
        String name = principal.getName();
        User user;
        if ("".equals(userName)||userName==null||"null".equals(userName)){
            user = userService.getUserByName(name);
        }
        else {
            user = userService.getUserByName(userName);
        }
        if (user!=null){
            if ("E:\\headPicture\\".equals(SystemConstant.picturePath)){
                user.setHeadName("http://localhost:8081/static/"+user.getHeadName()+"?"+ UUID.randomUUID());
            }
            else {
                user.setHeadName("http://106.54.174.38:8081/static/"+user.getHeadName()+"?"+UUID.randomUUID());
            }
            user.setGoodCount(userService.getGoodCount(user.getUserId()));
            user.setUserExperience(userService.getLevel(user.getUserId()));
            request.setAttribute("allInfo",user);
            request.setAttribute("myInfo",name);
            return "information";
        }
        else {
            request.setAttribute("msg","未找到目标用户！");
            return "myError";
        }
    }
}