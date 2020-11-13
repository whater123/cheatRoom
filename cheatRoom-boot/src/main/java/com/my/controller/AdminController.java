package com.my.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.my.constant.SystemConstant;
import com.my.dao.UserMapper;
import com.my.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.util.Set;

@CrossOrigin
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    UserMapper userMapper;

    @RequestMapping(value = "/getOnline", method = RequestMethod.GET)
    public Set<String> getMessage() {
        return SystemConstant.loginSet;
    }

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
}