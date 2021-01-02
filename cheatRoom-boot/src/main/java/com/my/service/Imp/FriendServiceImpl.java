package com.my.service.Imp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.my.dao.FriendMapper;
import com.my.pojo.base.Friend;
import com.my.pojo.base.User;
import com.my.pojo.view.FriendShow;
import com.my.service.FriendSerivce;
import com.my.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author w
 */
@Service
public class FriendServiceImpl implements FriendSerivce {
    @Autowired
    FriendMapper friendMapper;
    @Autowired
    UserService userService;

    @Override
    public boolean addFriend(int userId1, int userId2) {
        if (userId1==userId2){
            return false;
        }
        QueryWrapper<Friend> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id1",userId1).eq("user_id2",userId2);
        Friend friend = friendMapper.selectOne(queryWrapper);

        QueryWrapper<Friend> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.eq("user_id1",userId1).eq("user_id2",userId2);
        Friend friend2 = friendMapper.selectOne(queryWrapper);
        if (friend!=null&&friend2!=null){
            return false;
        }
        else {
            int insert = friendMapper.insert(new Friend(userId1, userId2));
            return insert==1;
        }
    }

    @Override
    public List<FriendShow> getUserFriends(int userId) {
        List<FriendShow> list = new ArrayList<>();

        QueryWrapper<Friend> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id1",userId).or().eq("user_id2",userId);
        List<Friend> friends = friendMapper.selectList(queryWrapper);

        for (Friend f:friends
             ) {
            if (f.getUserId1()==userId){
                User userById = userService.getUserById(f.getUserId2());
                list.add(new FriendShow(userById,f.getFriendTime()));
            }
            else {
                User userById = userService.getUserById(f.getUserId1());
                list.add(new FriendShow(userById,f.getFriendTime()));
            }
        }
        return list;
    }

    @Override
    public boolean deleteFriend(int userId1, int userId2) {
        QueryWrapper<Friend> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id1",userId1).eq("user_id2",userId2);
        int delete = friendMapper.delete(queryWrapper);
        if (delete!=1){
            QueryWrapper<Friend> queryWrapper2 = new QueryWrapper<>();
            queryWrapper2.eq("user_id1",userId2).eq("user_id2",userId1);
            int delete2 = friendMapper.delete(queryWrapper);
            return delete2==1;
        }
        else {
            return true;
        }
    }
}
