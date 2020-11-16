package com.my.controller;


import com.my.constant.SystemConstant;
import com.my.dao.UserMapper;
import com.my.pojo.Client2ServerMessage;
import com.my.pojo.LoginShow;
import com.my.pojo.Chat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
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
    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/all") // @MessageMapping 和 @RequestMapping 功能类似，浏览器向服务器发起消息，映射到该地址。
    @SendTo("/nasus/getResponse") // 如果服务器接受到了消息，就会对订阅了 @SendTo 括号中的地址的浏览器发送消息。
    public Chat say(Principal principal, Client2ServerMessage context) throws Exception {
        String role = userMapper.getRole(principal.getName());
        if ("admin".equals(role)){
            return new Chat("<span style=\"color: #ff0c0c;\">" +"站长sama</span>" ,context.getContext());
        }
        if ("".equals(context.getContext())){
            return null;
        }
        return new Chat(principal.getName(),context.getContext());
    }

    @MessageMapping("/loginShow")
    @SendTo("/nasus/getLoginShow")
    public LoginShow userLoginShow(Principal principal, LoginShow isLogin){
        if ("true".equals(isLogin.getIsLogin())){
            if (SystemConstant.loginSet.contains(principal.getName())){
                return new LoginShow(SystemConstant.loginSet.size(),"error");
            }
            else {
                SystemConstant.loginSet.add(principal.getName());
                return new LoginShow(principal.getName(), SystemConstant.loginSet.size(),"true");
            }
        }
        else {
            if (!SystemConstant.loginSet.contains(principal.getName())){
                return new LoginShow(SystemConstant.loginSet.size(),"error");
            }else {
                SystemConstant.loginSet.remove(principal.getName());
                return new LoginShow(principal.getName(), SystemConstant.loginSet.size(),"false");
            }
        }
    }

    @MessageMapping("/admin")
    @SendTo("/nasus/adminControl")
    public int adminControl(){
        return 1;
    }

    @MessageMapping("/send")
    public void send(Principal principal,Chat chat){
        chat.setUserName(principal.getName());
        simpMessagingTemplate.convertAndSendToUser(chat.getToUserName(),"/siChat/send",chat);
        simpMessagingTemplate.convertAndSendToUser(chat.getUserName(),"/siChat/send",chat);
    }

}
