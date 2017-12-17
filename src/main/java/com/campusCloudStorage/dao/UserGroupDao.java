package com.campusCloudStorage.dao;

import com.campusCloudStorage.entity.UserGroup;

public interface UserGroupDao {
    int deleteByPrimaryKey(Integer gId);

    int insert(UserGroup record);

    int insertSelective(UserGroup record);

    UserGroup selectByPrimaryKey(Integer gId);

    int updateByPrimaryKeySelective(UserGroup record);

    int updateByPrimaryKey(UserGroup record);
}