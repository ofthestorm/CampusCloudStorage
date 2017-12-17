package com.campusCloudStorage.dao;

import com.campusCloudStorage.entity.GroupMemberKey;

public interface GroupMemberDao {
    int deleteByPrimaryKey(GroupMemberKey key);

    int insert(GroupMemberKey record);

    int insertSelective(GroupMemberKey record);
}