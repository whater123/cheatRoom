package com.my.pojo.view;

import com.my.pojo.base.Friend;
import com.my.pojo.base.User;

import java.util.Date;

public class FriendShow {
    private User user;
    private Date friendTime;

    public FriendShow(User user, Date friendTime) {
        this.user = user;
        this.friendTime = friendTime;
    }

    public FriendShow() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getFriendTime() {
        return friendTime;
    }

    public void setFriendTime(Date friendTime) {
        this.friendTime = friendTime;
    }
}
