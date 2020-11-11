package com.my.pojo;

/**
 * @author w
 */
public class LoginShow {
    private String userName;
    private int number;
    private String isLogin;

    public String getIsLogin() {
        return isLogin;
    }

    public LoginShow(String isLogin) {
        this.isLogin = isLogin;
        this.userName = "";
        this.number = 0;
    }

    public LoginShow() {
    }

    public void setIsLogin(String isLogin) {
        this.isLogin = isLogin;
    }

    public LoginShow(String userName, int number, String isLogin) {
        this.userName = userName;
        this.number = number;
        this.isLogin = isLogin;
    }

    public LoginShow(String userName, int number) {
        this.userName = userName;
        this.number = number;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}