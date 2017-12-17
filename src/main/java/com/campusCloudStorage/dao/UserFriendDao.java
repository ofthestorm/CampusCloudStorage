package com.campusCloudStorage.dao;

import com.campusCloudStorage.entity.UserFriend;

public interface UserFriendDao {
    int deleteByPrimaryKey(Integer from);

    int insert(UserFriend record);

    int insertSelective(UserFriend record);

    UserFriend selectByPrimaryKey(Integer from);

    int updateByPrimaryKeySelective(UserFriend record);

    int updateByPrimaryKey(UserFriend record);
}