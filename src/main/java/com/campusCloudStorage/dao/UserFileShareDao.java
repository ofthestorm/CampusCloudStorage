package com.campusCloudStorage.dao;

import com.campusCloudStorage.entity.UserFileShare;
import com.campusCloudStorage.entity.UserFileShareKey;

public interface UserFileShareDao {
    int deleteByPrimaryKey(UserFileShareKey key);

    int deleteByFId(int fId);

    int insert(UserFileShare record);

    int insertSelective(UserFileShare record);

    UserFileShare selectByPrimaryKey(UserFileShareKey key);

    int updateByPrimaryKeySelective(UserFileShare record);

    int updateByPrimaryKey(UserFileShare record);
}