package com.my.service;

import com.my.pojo.view.Chat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author w
 */

public interface RecordService {
    void addAllRecord(Chat chat);

    List<Chat> getRecords();

    void addSiChat(Chat chat);

    List<Chat> getSiChats(String userName ,String toUserName);
}
