package com.my;

import com.my.dao.KeyMapper;
import com.my.dao.UserMapper;
import com.my.pojo.base.Key;
import com.my.pojo.view.Chat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;

@SpringBootTest
class HelloWordTests {
    @Autowired
    KeyMapper keyMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    RedisTemplate<String,Object> redisTemplate;

    @Test
    void t1(){
        Set<String> strings = new HashSet<>();
        strings.add("1");
        strings.add("2");
        System.out.println(strings.toString());

        Set<String> strings2 = new HashSet<>();
        strings2.add("2");
        strings2.add("1");
        System.out.println(strings2.toString());

        System.out.println(strings.toString().equals(strings2.toString()));
    }
}