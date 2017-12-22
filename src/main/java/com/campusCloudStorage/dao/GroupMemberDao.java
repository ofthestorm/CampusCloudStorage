package com.campusCloudStorage.dao;

import com.campusCloudStorage.entity.GroupMember;
import com.campusCloudStorage.entity.GroupMemberKey;


import java.util.List;

public interface GroupMemberDao {
    int deleteByPrimaryKey(GroupMemberKey key);

    int deleteByGId(Integer gId);

    int insert(GroupMember record);

    int insertSelective(GroupMember record);

    GroupMember selectByPrimaryKey(GroupMemberKey key);

    List<GroupMember> selectMembersByGId(Integer gId);

    List<GroupMember> selectPermittedByUId(Integer uId);

    List<GroupMember> selectUnpermittedMembers(Integer gId);

    int updateByPrimaryKeySelective(GroupMember record);

    int updateByPrimaryKey(GroupMember record);
}