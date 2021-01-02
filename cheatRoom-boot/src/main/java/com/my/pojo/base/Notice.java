package com.my.pojo.base;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.util.Date;

/**
 * @author w
 */
public class Notice {
    @TableId(type = IdType.AUTO)
    private int noticeId;
    private Date noticeTime;
    private String noticeContext;
    private String noticeTitle;
    @TableField(exist = false)
    private int noticeReadCount;

    @Override
    public String toString() {
        return "Notice{" +
                "noticeId=" + noticeId +
                ", noticeTime=" + noticeTime +
                ", noticeContext='" + noticeContext + '\'' +
                ", noticeTitle='" + noticeTitle + '\'' +
                ", noticeReadCount=" + noticeReadCount +
                '}';
    }

    public int getNoticeReadCount() {
        return noticeReadCount;
    }

    public void setNoticeReadCount(int noticeReadCount) {
        this.noticeReadCount = noticeReadCount;
    }

    public Notice(String noticeContext, String noticeTitle) {
        this.noticeContext = noticeContext;
        this.noticeTitle = noticeTitle;
        this.noticeTime = new Date();
    }

    public String getNoticeTitle() {
        return noticeTitle;

    }

    public Notice(int noticeId, Date noticeTime, String noticeContext) {
        this.noticeId = noticeId;
        this.noticeTime = noticeTime;
        this.noticeContext = noticeContext;
    }

    public Notice(String noticeContext) {
        this.noticeContext = noticeContext;

    }

    public Notice() {
    }

    public int getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(int noticeId) {
        this.noticeId = noticeId;
    }

    public Date getNoticeTime() {
        return noticeTime;
    }

    public void setNoticeTime(Date noticeTime) {
        this.noticeTime = noticeTime;
    }

    public String getNoticeContext() {
        return noticeContext;
    }

    public void setNoticeContext(String noticeContext) {
        this.noticeContext = noticeContext;
    }
}
