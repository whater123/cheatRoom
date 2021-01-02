package com.my.pojo.base;

import com.baomidou.mybatisplus.annotation.TableField;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @author w
 */
public class Friend {
    @TableField("user_id1")
    private int userId1;
    @TableField("user_id2")
    private int userId2;
    @TableField("friend_time")
    private Date friendTime;

    public Date getFriendTime() {
        return friendTime;
    }

    public Friend(int userId1, int userId2, Timestamp friendTime) {
        this.userId1 = userId1;
        this.userId2 = userId2;
        this.friendTime = friendTime;
    }

    public void setFriendTime(Timestamp friendTime) {
        this.friendTime = friendTime;
    }

    public int getUserId1() {
        return userId1;
    }

    public void setUserId1(int userId1) {
        this.userId1 = userId1;
    }

    public int getUserId2() {
        return userId2;
    }

    public void setUserId2(int userId2) {
        this.userId2 = userId2;
    }

    public Friend(int userId1, int userId2) {
        this.userId1 = userId1;
        this.userId2 = userId2;
        this.friendTime = new Date();
    }
}
