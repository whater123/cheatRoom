package com.my.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.my.constant.SystemConstant;
import com.my.dao.UserMapper;
import com.my.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.xml.soap.SAAJResult;
import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * @author w
 */
@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController{
    @Autowired
    UserMapper userMapper;



    @RequestMapping(value = "/getMessage", method = RequestMethod.GET)
    public User getMessage(Principal principal) {
        String name = principal.getName();
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name",name);
        return userMapper.selectOne(queryWrapper);
    }

    @RequestMapping(value = "/fileUpload",method = RequestMethod.POST)
    public String fileUpload(@RequestParam(value = "file") MultipartFile file, Principal principal,HttpServletRequest request) {
        if (file.isEmpty()) {
            System.out.println("文件为空");
        }
        // 上传后的路径
        String filePath = SystemConstant.picturePath;
        // 新文件名
        String fileName = principal.getName() + ".jpg";

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name",principal.getName());
        User user = userMapper.selectOne(queryWrapper);
        user.setHeadName(fileName);
        userMapper.updateById(user);

        File dest = new File(filePath + fileName);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileName;
    }

    @RequestMapping(value = "/getOnlineList",method = RequestMethod.GET)
    public String[] getOnlineList(){
        String[] strings1 = new String[0];
        return SystemConstant.loginSet.toArray(strings1);
    }
}