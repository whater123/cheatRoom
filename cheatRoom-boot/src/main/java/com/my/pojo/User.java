package com.my.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

/**
 * @author w
 */
public class User {
    @TableId(type = IdType.AUTO)
    private int id;
    @TableField(value = "user_name")
    private String userName;
    private String password;
    private String role;
    private String headName;

    public String getHeadName() {
        return headName;
    }

    public void setHeadName(String headName) {
        this.headName = headName;
    }

    public User(int id, String userName, String password, String role, String headName) {
        this.id = id;
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

    public User(int id, String userName, String password, String role) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
