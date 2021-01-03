package com.my.service.Imp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.my.dao.KeyMapper;
import com.my.dao.NoticeMapper;
import com.my.dao.UserMapper;
import com.my.pojo.base.Key;
import com.my.pojo.base.Notice;
import com.my.pojo.base.User;
import com.my.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author w
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    RedisTemplate<String,Object> redisTemplate;
    @Autowired
    NoticeMapper noticeMapper;
    @Autowired
    KeyMapper keyMapper;

    @Override
    public User getUserByName(String name) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name",name);
        return userMapper.selectOne(queryWrapper);
    }

    @Override
    public User getUserById(int userId) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        return userMapper.selectOne(queryWrapper);
    }

    @Override
    public boolean updateUser(User oldUser,User newUser) {
        newUser.setUserIsVip(oldUser.getUserIsVip());

        if (!oldUser.getUserName().equals(newUser.getUserName())){
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("user_name",newUser.getUserName());
            User user1 = userMapper.selectOne(queryWrapper);
            if (user1!=null){
                return false;
            }
        }

        QueryWrapper<User> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.eq("user_name",oldUser.getUserName());
        int update = userMapper.update(newUser, queryWrapper2);
        return update==1;
    }

    //点赞用户
    @Override
    public boolean goodUser(int beUserId, int doUserId) {
        try {
            HashOperations<String, Object, Object> opsForHash = redisTemplate.opsForHash();
            if (!opsForHash.hasKey("good",String.valueOf(beUserId))){
                Set<Integer> set = new TreeSet<>();
                set.add(doUserId);
                opsForHash.put("good",String.valueOf(beUserId),set);
            }
            else {
                Set<Integer> set = (Set<Integer>) opsForHash.get("good", String.valueOf(beUserId));
                if (set.contains(doUserId)){
                    return false;
                }
                set.add(doUserId);
                opsForHash.put("good",String.valueOf(beUserId),set);
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
    //获取点赞个数
    @Override
    public int getGoodCount(int userId) {
        HashOperations<String, Object, Object> opsForHash = redisTemplate.opsForHash();
        if (!opsForHash.hasKey("good",String.valueOf(userId))) {
            return 0;
        }
        else {
            Set<Integer> set = (Set<Integer>) opsForHash.get("good", String.valueOf(userId));
            return set.size();
        }
    }

    @Override
    public boolean addLevel(User user, int addNumber) {
        try{
            if (user.getUserIsVip()==1){
                addNumber*=2;
            }
            HashOperations<String, Object, Object> opsForHash = redisTemplate.opsForHash();
            if (!opsForHash.hasKey("level",String.valueOf(user.getUserId()))) {
                opsForHash.put("level",String.valueOf(user.getUserId()),addNumber);
            }
            else {
                opsForHash.increment("level",String.valueOf(user.getUserId()),addNumber);
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public int getLevel(int userId) {
        HashOperations<String, Object, Object> opsForHash = redisTemplate.opsForHash();
        if ( !opsForHash.hasKey("level", String.valueOf(userId))){
            return 0;
        }
        return (int) opsForHash.get("level", String.valueOf(userId));
    }
    //获取全部通知
    @Override
    public List<Notice> getAllNotice() {
        List<Notice> notices = noticeMapper.selectList(null);
        for (Notice n : notices
                ) {
            n.setNoticeReadCount(getNoticeReadCount(n.getNoticeId()));
        }
        return notices;
    }
    //获取某个通知
    @Override
    public Notice getNoticeById(int noticeId) {
        return noticeMapper.selectById(noticeId);
    }
    //用户阅读通知
    @Override
    public boolean readNotice(int noticeId, int userId) {
        try{
            HashOperations<String, Object, Object> opsForHash = redisTemplate.opsForHash();
            if (!opsForHash.hasKey("notice",String.valueOf(noticeId))) {
                Set<Integer> set = new TreeSet<>();
                set.add(userId);
                opsForHash.put("notice",String.valueOf(noticeId),set);
            }
            else {
                Set<Integer> set = (Set<Integer>) opsForHash.get("notice", String.valueOf(noticeId));
                set.add(userId);
                opsForHash.put("notice",String.valueOf(noticeId),set);
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
    //获取通知的阅读量
    @Override
    public int getNoticeReadCount(int noticeId) {
        HashOperations<String, Object, Object> opsForHash = redisTemplate.opsForHash();
        if (!opsForHash.hasKey("notice",String.valueOf(noticeId))) {
            return 0;
        }
        else {
            Set<Integer> set = (Set<Integer>) opsForHash.get("notice", String.valueOf(noticeId));
            return set.size();
        }
    }

    @Override
    public boolean useKey(String key, int userId) {
        int i = userMapper.updateById(new User(userId, 1));
        if (i!=1){
            return false;
        }
        Key keyBtName = keyMapper.getKeyBtName(key.trim());
        if (keyBtName==null||keyBtName.getUserId()!=0){
            return false;
        }
        int b = keyMapper.useKey(new Key(keyBtName.getKeyId(), userId));
        return b==1;
    }
}