package com.my.service;

import com.my.pojo.base.Friend;
import com.my.pojo.base.User;
import com.my.pojo.view.FriendShow;

import java.util.List;

public interface FriendSerivce {

    boolean addFriend(int userId1,int userId2);

    List<FriendShow> getUserFriends(int userId);

    boolean deleteFriend(int userId1,int userId2);
}
