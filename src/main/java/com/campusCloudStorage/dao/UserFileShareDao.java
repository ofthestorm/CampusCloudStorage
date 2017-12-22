package com.campusCloudStorage.dao;

import com.campusCloudStorage.entity.UserFileShare;
import com.campusCloudStorage.entity.UserFileShareKey;

import java.util.List;

public interface UserFileShareDao {
    int deleteByPrimaryKey(UserFileShareKey key);

    int deleteByFId(int fId);

    int insert(UserFileShare record);

    int insertSelective(UserFileShare record);

    UserFileShare selectByPrimaryKey(UserFileShareKey key);

    int updateByPrimaryKeySelective(UserFileShare record);

    int updateByPrimaryKey(UserFileShare record);

    List<UserFileShare> selectByUId(int fromId, int toId);
}