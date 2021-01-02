package com.my.service.Imp;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.my.dao.KeyMapper;
import com.my.dao.NoticeMapper;
import com.my.dao.UserMapper;
import com.my.pojo.base.Key;
import com.my.pojo.base.Notice;
import com.my.pojo.base.User;
import com.my.pojo.view.KeyShow;
import com.my.service.AdminService;
import com.my.util.RandomUtil;
import com.thoughtworks.qdox.model.expression.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author w
 */
@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    NoticeMapper noticeMapper;
    @Autowired
    KeyMapper keyMapper;
    @Autowired
    UserMapper userMapper;

    @Override
    public boolean addAdminNotice(Notice notice) {
        int insert = noticeMapper.insert(notice);
        return insert==1;
    }

    @Override
    public boolean deleteNotice(int noticeId) {
        QueryWrapper<Notice> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("notice_id",noticeId);
        int delete = noticeMapper.delete(queryWrapper);
        return delete==1;
    }

    @Override
    public String getVipKey() {
        String s = IdUtil.randomUUID();
        keyMapper.insertKey(new Key(s));
        return s;
    }

    @Override
    public List<KeyShow> getAllKeys() {
        List<Key> keys = keyMapper.getAllKeys();
        List<KeyShow> keyShows = new ArrayList<>();

        for (Key k: keys
             ) {
            if (k.getUserId()!=0){
                keyShows.add(new KeyShow(k,userMapper.selectById(k.getUserId())));
            }
            else {
                keyShows.add(new KeyShow(k,new User(k.getUserId())));
            }
        }
        return keyShows;
    }
}
