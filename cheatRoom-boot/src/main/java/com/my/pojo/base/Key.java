package com.my.pojo.base;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.sql.Timestamp;
import java.util.Date;

public class Key {
    @TableId(type = IdType.AUTO)
    @TableField(value = "key_id")
    private int keyId;
    @TableField(value = "key_create_time")
    private Date keyCreateTime;
    @TableField(value = "key_use_time")
    private Date keyUseTime;
    @TableField(value = "key_context")
    private String keyContext;
    @TableField(value = "user_id")
    private int userId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Key(int keyId, Timestamp keyCreateTime, Date keyUseTime, String keyContext, int userId) {
        this.keyId = keyId;
        this.keyCreateTime = keyCreateTime;
        this.keyUseTime = keyUseTime;
        this.keyContext = keyContext;
        this.userId = userId;
    }

    public Key(int keyId, int userId) {
        this.keyId = keyId;
        this.userId = userId;
        this.keyUseTime = new Date();
    }

    public int getKeyId() {
        return keyId;
    }

    public void setKeyId(int keyId) {
        this.keyId = keyId;
    }

    public Key(String keyContext) {
        this.keyCreateTime = new Date();
        this.keyContext = keyContext;
    }

    public Key() {
    }

    public Date getKeyCreateTime() {
        return keyCreateTime;
    }

    public void setKeyCreateTime(Date keyCreateTime) {
        this.keyCreateTime = keyCreateTime;
    }

    public Date getKeyUseTime() {
        return keyUseTime;
    }

    public void setKeyUseTime(Date keyUseTime) {
        this.keyUseTime = keyUseTime;
    }

    public String getKeyContext() {
        return keyContext;
    }

    public void setKeyContext(String keyContext) {
        this.keyContext = keyContext;
    }
}
