package com.my.service;

import com.my.pojo.base.Notice;
import com.my.pojo.base.User;

import java.util.List;

/**
 * @author w
 */
public interface UserService {
    User getUserByName(String name);

    User getUserById(int userId);

    boolean updateUser(User oldUser,User newUser);

    boolean goodUser(int beUserId,int doUserId);

    int getGoodCount(int userId);

    boolean addLevel(User user,int addNumber);

    int getLevel(int userId);

    List<Notice> getAllNotice();

    Notice getNoticeById(int noticeId);

    boolean readNotice(int noticeId,int userId);

    int getNoticeReadCount(int noticeId);

    boolean useKey(String key,int userId);
}
