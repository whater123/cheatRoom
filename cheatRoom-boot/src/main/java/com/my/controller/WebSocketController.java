package com.my.controller;


import com.my.constant.SystemConstant;
import com.my.dao.UserMapper;
import com.my.pojo.Client2ServerMessage;
import com.my.pojo.LoginShow;
import com.my.pojo.Server2ClientMessage;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @author w
 */
@CrossOrigin
@RestController
public class WebSocketController {
    @Autowired
    UserMapper userMapper;

    @MessageMapping("/all") // @MessageMapping 和 @RequestMapping 功能类似，浏览器向服务器发起消息，映射到该地址。
    @SendTo("/nasus/getResponse") // 如果服务器接受到了消息，就会对订阅了 @SendTo 括号中的地址的浏览器发送消息。
    public Server2ClientMessage say(Principal principal,Client2ServerMessage context) throws Exception {
        String role = userMapper.getRole(principal.getName());
        if ("admin".equals(role)){
            return new Server2ClientMessage("<span style=\"color: #ff0c0c;\">" +"站长sama</span>" ,context.getContext());
        }
        return new Server2ClientMessage(principal.getName(),context.getContext());
    }

    @MessageMapping("/loginShow")
    @SendTo("/nasus/getLoginShow")
    public LoginShow userLoginShow(Principal principal, LoginShow isLogin){
        if ("true".equals(isLogin.getIsLogin())){
            SystemConstant.LOGIN_NUMBER++;
            return new LoginShow(principal.getName(), SystemConstant.LOGIN_NUMBER,"true");
        }
        else {
            SystemConstant.LOGIN_NUMBER--;
            return new LoginShow(principal.getName(), SystemConstant.LOGIN_NUMBER,"false");
        }
    }



}
