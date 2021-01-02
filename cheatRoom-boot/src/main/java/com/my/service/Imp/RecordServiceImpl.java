package com.my.service.Imp;

import com.my.pojo.base.User;
import com.my.pojo.view.Chat;
import com.my.service.RecordService;
import com.my.service.UserService;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class RecordServiceImpl implements RecordService {
    @Autowired
    RedisTemplate<String,Object> redisTemplate;
    @Autowired
    UserService userService;

    @Override
    public void addAllRecord(Chat chat){

        ListOperations<String, Object> ops = redisTemplate.opsForList();

        if (ops.size("qun") >= 50){
            Object qun = ops.leftPop("qun");
            System.out.println(qun+" be pop");
        }
        ops.rightPush("qun",chat);
    }

    @Override
    public List<Chat> getRecords(){
        ListOperations<String, Object> ops = redisTemplate.opsForList();
        List<Object> qun = ops.range("qun", 0, -1);
        List<Chat> chats = new ArrayList<>();
        for (Object o :qun
        ) {
            Chat i = (Chat) o;

            String userName = i.getUserName();
            User userByName = userService.getUserByName(userName);

            i.setUserIsVip(userByName.getUserIsVip());
            i.setUserLevel(userService.getLevel(userByName.getUserId())/100);

            chats.add(i);
        }
        return chats;
    }

    @Override
    public void addSiChat(Chat chat) {
        HashOperations<String, Object, Object> opsForHash = redisTemplate.opsForHash();
        Set<String> set = new HashSet<>();
        set.add(chat.getUserName());
        set.add(chat.getToUserName());
        if (!opsForHash.hasKey("si",set.toString())) {
            List<Chat> chats = new ArrayList<>();
            chats.add(chat);
            opsForHash.put("si",set.toString(),chats);
        }
        else {
            List<Chat> chats = (List<Chat>) opsForHash.get("si", set.toString());
            if (chats.size()==50){
                chats.remove(0);
            }
            chats.add(chat);
            opsForHash.put("si",set.toString(),chats);
        }
    }

    @Override
    public List<Chat> getSiChats(String userName ,String toUserName) {
        HashOperations<String, Object, Object> opsForHash = redisTemplate.opsForHash();
        Set<String> set = new HashSet<>();
        set.add(userName);
        set.add(toUserName);
        if (!opsForHash.hasKey("si",set.toString())) {
            return null;
        }
        else {
            return  (List<Chat>) opsForHash.get("si", set.toString());
        }
    }


}
