package com.my.controller;

import cn.hutool.core.img.ImgUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.my.constant.SystemConstant;
import com.my.dao.KeyMapper;
import com.my.dao.UserMapper;
import com.my.pojo.base.Notice;
import com.my.pojo.view.Chat;
import com.my.pojo.base.User;
import com.my.pojo.view.FriendShow;
import com.my.service.FriendSerivce;
import com.my.service.RecordService;
import com.my.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.UUID;

/**
 * @author w
 */
@CrossOrigin
@Controller
@RequestMapping("/user")
public class UserController{
    @Autowired
    UserMapper userMapper;
    @Autowired
    RecordService recordService;
    @Autowired
    KeyMapper keyMapper;
    @Autowired
    UserService userService;
    @Autowired
    FriendSerivce friendSerivce;

    @ResponseBody
    @RequestMapping(value = "/getMessage", method = RequestMethod.GET)
    public User getMessage(Principal principal,String userId) {
        String name = principal.getName();
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if ("null".equals(userId)||userId==null|| "0".equals(userId)|| "".equals(userId)){
            queryWrapper.eq("user_name",name);
            return userMapper.selectOne(queryWrapper);
        }
        else {
            queryWrapper.eq("user_id",Integer.valueOf(userId));
            User user = userMapper.selectOne(queryWrapper);
            user.setGoodCount(userService.getGoodCount(user.getUserId()));
            user.setUserExperience(userService.getLevel(user.getUserId()));
            return user;
        }
    }

    @ResponseBody
    @RequestMapping(value = "/fileUpload",method = RequestMethod.POST)
    public String fileUpload(@RequestParam(value = "file") MultipartFile file, Principal principal,HttpServletRequest request) {
        if (file.isEmpty()) {
            return "0";
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

    @ResponseBody
    @RequestMapping(value = "/getOnlineList",method = RequestMethod.GET)
    public String[] getOnlineList(){
        String[] strings1 = new String[0];
        return SystemConstant.loginSet.toArray(strings1);
    }

    @ResponseBody
    @RequestMapping(value = "/getRecords",method = RequestMethod.GET)
    public List<Chat> getRecords(HttpServletRequest request){
        try{
            return recordService.getRecords();
        }catch (Exception e){
            return null;
        }
    }

    @RequestMapping(value = "/getAllInfo")
    public String getAllInfo(Principal principal,HttpServletRequest request,String userName){
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
                user.setHeadName("http://localhost:8081/static/"+user.getHeadName()+"?"+UUID.randomUUID());
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

    @ResponseBody
    @RequestMapping(value = "/changeInfo")
    public String changeInfo(Principal principal,User user){
        String name = principal.getName();
        User userByName = userService.getUserByName(name);
        boolean b = userService.updateUser(userByName,user);
        if (!userByName.getUserName().equals(user.getUserName())){
            return b?"1":"0";
        }
        else {
            return b?"2":"0";
        }
    }

    @ResponseBody
    @PostMapping(value = "/goodUser")
    public String goodUser(Principal principal,String userId){
        String name = principal.getName();
        User userByName = userService.getUserByName(name);
        boolean b = userService.goodUser(Integer.parseInt(userId), userByName.getUserId());
        boolean b1 = userService.addLevel(userByName, 10);
        boolean b2 = userService.addLevel(userService.getUserById(Integer.parseInt(userId)), 20);
        return b&&b1&&b2?"1":"0";
    }

    @RequestMapping(value = "/getUserFriends")
    public String getUserFriends(Principal principal,HttpServletRequest request){
        String name = principal.getName();
        User userByName = userService.getUserByName(name);
        List<FriendShow> userFriends = friendSerivce.getUserFriends(userByName.getUserId());
        request.setAttribute("friendList",userFriends);
        request.setAttribute("myInfo",userByName);
        return "friendList";
    }

    @ResponseBody
    @PostMapping(value = "/addFriend")
    public String addFriend(Principal principal,String userId){
        String name = principal.getName();
        User userByName = userService.getUserByName(name);
        int userId1;
        try{
            userId1 = Integer.parseInt(userId);
        }catch (Exception e){
            return "0";
        }
        boolean b = friendSerivce.addFriend(userByName.getUserId(), userId1);
        return b?"1":"0";
    }

    @RequestMapping(value = "/toFriendChat")
    public String toFriendChat(String userId,HttpServletRequest request){
        User userById = userService.getUserById(Integer.parseInt(userId));
        request.setAttribute("toUserName",userById.getUserName());
        return "friendChat";
    }

    @RequestMapping(value = "/toFriendChat/{userName}")
    public String toFriendChat2(@PathVariable("userName") String userName,HttpServletRequest request){
        User userById = userService.getUserByName(userName);
        request.setAttribute("toUserName",userById.getUserName());
        return "friendChat";
    }

    @RequestMapping(value = "/getNotice/{noticeId}")
    public String getNotice(@PathVariable("noticeId") String noticeId,HttpServletRequest request,Principal principal){
        String name = principal.getName();
        User userByName = userService.getUserByName(name);
        Notice noticeById = userService.getNoticeById(Integer.parseInt(noticeId));
        boolean b = userService.readNotice(noticeById.getNoticeId(), userByName.getUserId());
        if (b){
            noticeById.setNoticeReadCount(userService.getNoticeReadCount(noticeById.getNoticeId()));
            request.setAttribute("notice",noticeById);
        }
        else {
            request.setAttribute("msg","通知获取失败！");
        }
        return "notice";
    }

    @RequestMapping(value = "/getAllNotices")
    public String getAllNotices(Principal principal,HttpServletRequest request){
        String name = principal.getName();
        User userByName = userService.getUserByName(name);
        List<Notice> allNotice = userService.getAllNotice();
        request.setAttribute("noticeList",allNotice);
        request.setAttribute("myInfo",userByName);
        return "noticeList";
    }

    @RequestMapping(value = "/toNotice")
    public String toNotice(String noticeId,HttpServletRequest request,Principal principal){
        String name = principal.getName();
        User userByName = userService.getUserByName(name);
        Notice noticeById = userService.getNoticeById(Integer.parseInt(noticeId));
        boolean b = userService.readNotice(noticeById.getNoticeId(), userByName.getUserId());
        boolean b1 = userService.addLevel(userByName, 5);
        if (b&&b1){
            noticeById.setNoticeReadCount(userService.getNoticeReadCount(noticeById.getNoticeId()));
            request.setAttribute("notice",noticeById);
        }
        else {
            request.setAttribute("msg","通知获取失败！");
        }
        return "notice";
    }

    @ResponseBody
    @RequestMapping(value = "/useKey")
    public String useKey(Principal principal,String key){
        String name = principal.getName();
        User userByName = userService.getUserByName(name);
        boolean b = userService.useKey(key, userByName.getUserId());
        boolean b1 = userService.addLevel(userByName, 100);
        return b&&b1?"1":"0";
    }

    @ResponseBody
    @RequestMapping(value = "/getSiChats")
    public List<Chat> getSiChats(Principal principal,String toUserName){
        return recordService.getSiChats(principal.getName(),toUserName);
    }
}