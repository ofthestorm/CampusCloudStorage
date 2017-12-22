package com.campusCloudStorage.dao;

import com.campusCloudStorage.entity.UserFriend;
import com.campusCloudStorage.entity.UserFriendKey;

import java.util.List;

public interface UserFriendDao {
    int deleteByPrimaryKey(UserFriendKey key);

    int insert(UserFriend record);

    int insertSelective(UserFriend record);

    UserFriend selectByPrimaryKey(UserFriendKey key);

    int updateByPrimaryKeySelective(UserFriend record);

    int updateByPrimaryKey(UserFriend record);

    List<UserFriend> selectUnpermittedFriends(int uId);

    List<UserFriend> selectFriendRequests(int uId);

    List<UserFriend> selectFriends(int uId);
}