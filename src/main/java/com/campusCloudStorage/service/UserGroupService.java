package com.campusCloudStorage.service;

import com.campusCloudStorage.entity.User;
import com.campusCloudStorage.entity.UserGroup;
import com.campusCloudStorage.enums.CreateStateEnum;
import com.campusCloudStorage.enums.DeleteStateEnum;

import java.util.List;

public interface UserGroupService {
    UserGroup selectByPrimaryKey(int gId);

    List<UserGroup> selectOwnedGroups(int builderId);

    List<UserGroup> selectJoinedGroups(int uId);

    List<UserGroup> selectOwnedAndJoinedGroups(int uId);

    List<User> selectMembers(int gId);

    User selectOwner(int gId);

    DeleteStateEnum deleteUserGroup(int uid, int gId);

    CreateStateEnum createUserGroup(UserGroup userGroup);
}
