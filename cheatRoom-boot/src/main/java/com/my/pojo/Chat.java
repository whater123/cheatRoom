package com.my.pojo;

import java.io.Serializable;

/**
 * @author w
 */
public class Chat implements Serializable {

    private String userName;
    private String responseMessage;
    private String toUserName;

    public Chat(String userName, String responseMessage, String toUserName) {
        this.userName = userName;
        this.responseMessage = responseMessage;
        this.toUserName = toUserName;
    }

    public Chat() {
    }

    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    public String getUserName() {
        return userName;
    }

    public Chat(String userName, String responseMessage) {
        this.userName = userName;
        this.responseMessage = responseMessage;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Chat(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }
}
