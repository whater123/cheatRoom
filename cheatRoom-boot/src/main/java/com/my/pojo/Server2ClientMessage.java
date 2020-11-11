package com.my.pojo;

public class Server2ClientMessage {

    private String userName;
    private String responseMessage;

    public String getUserName() {
        return userName;
    }

    public Server2ClientMessage(String userName, String responseMessage) {
        this.userName = userName;
        this.responseMessage = responseMessage;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Server2ClientMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }
}
