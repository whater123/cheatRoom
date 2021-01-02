package com.my.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.my.dao.FriendMapper;
import com.my.dao.UserMapper;
import com.my.pojo.base.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author w
 */
@CrossOrigin
@RestController
public class LoginController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private FriendMapper friendMapper;

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "成功注销！";
    }

    /**
     * 登陆
     *
     * @param userName 用户名
     * @param password 密码
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(String userName, String password, String rememberMe) {
        boolean rememberMe2 = "true".equals(rememberMe);

        // 从SecurityUtils里边创建一个 subject
        Subject subject = SecurityUtils.getSubject();
        // 在认证提交前准备 token（令牌）
        UsernamePasswordToken token = new UsernamePasswordToken(userName, password,rememberMe2);
        // 执行认证登陆，直接跳到认证方法内，没有异常才执行下面的程序，有异常直接被全局捕捉然后返回json信息了
        subject.login(token);
        //根据权限，指定返回数据
        String role = userMapper.getRole(userName);
        if ("user".equals(role)) {
            return "欢迎登陆";
        }
        if ("admin".equals(role)) {
            return "欢迎来到管理员页面";
        }
        return "权限错误！";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(String userName, String password){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name",userName);
        User user = userMapper.selectOne(queryWrapper);
        if (user == null){
            userMapper.insert(new User(userName,password,"user","default.jpg"));
            return "注册成功";
        }
        else {
            return "该用户名已被注册";
        }
    }
}
