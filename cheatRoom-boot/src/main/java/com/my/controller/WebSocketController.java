package com.my.controller;


import com.my.constant.SystemConstant;
import com.my.dao.UserMapper;
import com.my.pojo.base.Notice;
import com.my.pojo.base.User;
import com.my.pojo.view.LoginShow;
import com.my.pojo.view.Chat;
import com.my.service.AdminService;
import com.my.service.RecordService;
import com.my.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Date;

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
    @Autowired
    RecordService recordService;
    @Autowired
    UserService userService;
    @Autowired
    AdminService adminService;

    @MessageMapping("/all") // @MessageMapping 和 @RequestMapping 功能类似，浏览器向服务器发起消息，映射到该地址。
    @SendTo("/nasus/getResponse") // 如果服务器接受到了消息，就会对订阅了 @SendTo 括号中的地址的浏览器发送消息。
    public Chat say(Principal principal, Chat chat) throws Exception {
        if ("".equals(chat.getResponseMessage())){
            return null;
        }
        chat.setUserName(principal.getName());
        User userByName = userService.getUserByName(principal.getName());
        userService.addLevel(userByName,1);
        try{
            if (SystemConstant.openRedis){
                recordService.addAllRecord(chat);
            }
        }catch (Exception e){
            return chat;
        }
        chat.setUserLevel(userService.getLevel(userByName.getUserId())/100);
        chat.setUserIsVip(userByName.getUserIsVip());
        return chat;
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
    public Notice adminControl(Notice notice){
        System.out.println(notice);
        notice.setNoticeTime(new Date());
        boolean b = adminService.addAdminNotice(notice);
        return b?notice:null;
    }

    @MessageMapping("/send")
    public void send(Principal principal,Chat chat){
        chat.setUserName(principal.getName());
        User userByName = userService.getUserByName(principal.getName());

        userService.addLevel(userByName,1);

        chat.setUserIsVip(userByName.getUserIsVip());
        chat.setUserLevel(userService.getLevel(userByName.getUserId())/100);

        recordService.addSiChat(chat);
        simpMessagingTemplate.convertAndSendToUser(chat.getToUserName(),"/siChat/si",chat);
        simpMessagingTemplate.convertAndSendToUser(chat.getToUserName(),"/siChat/send",chat);
        simpMessagingTemplate.convertAndSendToUser(chat.getUserName(),"/siChat/send",chat);
    }
}
