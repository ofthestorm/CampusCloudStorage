package com.campusCloudStorage.dao;

import com.campusCloudStorage.entity.UserGroup;

import java.util.List;

public interface UserGroupDao {
    int deleteByPrimaryKey(Integer gId);

    int insert(UserGroup record);

    int insertSelective(UserGroup record);

    UserGroup selectByPrimaryKey(Integer gId);

    List<UserGroup> selectByBuilderId(Integer builderId);

    int updateByPrimaryKeySelective(UserGroup record);

    int updateByPrimaryKey(UserGroup record);
}