package com.campusCloudStorage.service;

import com.campusCloudStorage.entity.GroupMember;
import com.campusCloudStorage.entity.User;
import com.campusCloudStorage.enums.GroupRequestStateEnum;

import java.util.List;

public interface GroupMemberService {
    boolean permitMemberRequest(int gId, int memberId);

    boolean deleteMember(int gId, int memberId);

    boolean refuseGroupRequest(int gId, int uId);

    GroupMember selectByPrimaryKey(int gId, int uId);

    List<User> selectUnpermittedMembers(int gId);

    GroupRequestStateEnum insertByPrimaryKey(int gId, int uId);
}
