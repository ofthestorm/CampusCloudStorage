package com.campusCloudStorage.dao;

import com.campusCloudStorage.entity.UserFriend;
import com.campusCloudStorage.entity.UserFriendKey;

public interface UserFriendMapper {
    int deleteByPrimaryKey(UserFriendKey key);

    int insert(UserFriend record);

    int insertSelective(UserFriend record);

    UserFriend selectByPrimaryKey(UserFriendKey key);

    int updateByPrimaryKeySelective(UserFriend record);

    int updateByPrimaryKey(UserFriend record);
}