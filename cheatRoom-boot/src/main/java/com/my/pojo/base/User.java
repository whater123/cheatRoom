package com.my.pojo.base;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.security.SecureRandom;

/**
 * @author w
 */
public class User {
    @TableId(type = IdType.AUTO)
    @TableField(value = "user_id")
    private int userId;
    @TableField(value = "user_name")
    private String userName;
    @TableField(value = "user_password")
    private String password;
    @TableField(value = "user_role")
    private String role;
    @TableField(value = "user_head_name")
    private String headName;
    @TableField(value = "user_introduce")
    private String userIntroduce;
    @TableField(value = "user_is_vip")
    private int userIsVip;
    @TableField(exist = false)
    private int userExperience;
    @TableField(exist = false)
    private int goodCount;

    public User() {
    }

    public int getUserLevel() {
        return userExperience/100;
    }
    public String getUserIntr2(){
        if (userIntroduce==null){
            return "该用户很懒，啥也没写~";
        }else {
            return userIntroduce;
        }
    }
    public String getUserIsVip2(){
        if (userIsVip==0){
            return "否";
        }
        else {
            return "是";
        }
    }
    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", headName='" + headName + '\'' +
                ", userIntroduce='" + userIntroduce + '\'' +
                ", userIsVip=" + userIsVip +
                ", userExperience=" + userExperience +
                ", goodCount=" + goodCount +
                '}';
    }

    public int getGoodCount() {
        return goodCount;
    }

    public void setGoodCount(int goodCount) {
        this.goodCount = goodCount;
    }

    public User(int userId) {
        this.userId = userId;
    }

    public User(int userId, int userIsVip) {
        this.userId = userId;
        this.userIsVip = userIsVip;
    }

    public User(int userId, String userName, String password, String headName, String role, String userIntroduce, Boolean userIsVip) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.headName = headName;
        this.role = role;
        this.userIntroduce = userIntroduce;
        this.userIsVip = userIsVip?1:0;
    }

    public User(String userName, String userIntroduce) {
        this.userName = userName;
        this.userIntroduce = userIntroduce;
    }

    public User(int userId, String userName, String password, String role, String headName, String userIntroduce, int userIsVip, int userExperience) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.role = role;
        this.headName = headName;
        this.userIntroduce = userIntroduce;
        this.userIsVip = userIsVip;
        this.userExperience = userExperience;
    }

    public String getUserIntroduce() {
        return userIntroduce;
    }

    public void setUserIntroduce(String userIntroduce) {
        this.userIntroduce = userIntroduce;
    }

    public int getUserIsVip() {
        return userIsVip;
    }

    public void setUserIsVip(int userIsVip) {
        this.userIsVip = userIsVip;
    }

    public int getUserExperience() {
        return userExperience;
    }

    public void setUserExperience(int userExperience) {
        this.userExperience = userExperience;
    }

    public String getHeadName() {
        return headName;
    }

    public void setHeadName(String headName) {
        this.headName = headName;
    }

    public User(int userId, String userName, String password, String role, String headName) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.role = role;
        this.headName = headName;
    }

    public User(String userName, String password, String role, String headName) {
        this.userName = userName;
        this.password = password;
        this.role = role;
        this.headName = headName;
    }

    public User(String userName, String password, String role) {
        this.userName = userName;
        this.password = password;
        this.role = role;
    }

    public User(int userId, String userName, String password, String role) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.role = role;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


}
