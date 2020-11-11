package com.my.constant;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import java.util.HashSet;
import java.util.Set;

/**
 * @author w
 */
public class SystemConstant {
    public static String picturePath = "E:\\headPicture\\";
//    public static String picturePath = "/root/headPicture/";
    public static Set<String> loginSet = new HashSet<>();
}
