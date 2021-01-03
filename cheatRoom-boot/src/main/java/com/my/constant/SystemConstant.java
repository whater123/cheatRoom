package com.my.constant;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import java.util.HashSet;
import java.util.Set;

/**
 * @author w
 */
public class SystemConstant {
    //头像路径
    public static String picturePath = "E:\\headPicture\\";
//    public static String picturePath = "/root/headPicture/";
    //登录用户名
    public static Set<String> loginSet = new HashSet<>();
    //是否开启群聊消息存储
    public static boolean openRedis = false;
}
