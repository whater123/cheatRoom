package com.my.pojo.view;

import com.my.constant.SystemConstant;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author w
 */
public class Chat implements Serializable {

    private String userName;
    private String responseMessage;
    private String toUserName;
    private String userHead;
    private int userLevel;
    private int userIsVip;

    public int getUserIsVip() {
        return userIsVip;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Chat chat = (Chat) o;
        return userLevel == chat.userLevel &&
                userIsVip == chat.userIsVip &&
                Objects.equals(userName, chat.userName) &&
                Objects.equals(responseMessage, chat.responseMessage) &&
                Objects.equals(toUserName, chat.toUserName) &&
                Objects.equals(userHead, chat.userHead);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, responseMessage, toUserName, userHead, userLevel, userIsVip);
    }

    public void setUserIsVip(int userIsVip) {
        this.userIsVip = userIsVip;
    }

    public int getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(int userLevel) {
        this.userLevel = userLevel;
    }

    @Override
    public String toString() {
        return "Chat{" +
                "userName='" + userName + '\'' +
                ", responseMessage='" + responseMessage + '\'' +
                ", toUserName='" + toUserName + '\'' +
                '}';
    }

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

    public String getUserHead() {
        return userHead;
    }

    public void setUserHead(String userHead) {
        this.userHead = userHead;
    }
}
