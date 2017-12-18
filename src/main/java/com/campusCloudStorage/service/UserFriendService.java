package com.campusCloudStorage.service;

import com.campusCloudStorage.entity.User;
import com.campusCloudStorage.enums.DeleteStateEnum;

import java.util.List;

public interface UserFriendService {
    public List<User> selectPermittedFriendsByUId(int uId);

    public List<UserFriend> selectFriendRequestByUId(int uId);

    public DeleteStateEnum deleteFriendsByPrimaryKey(int from,int to);

    public boolean insertFriendsByPrimaryKey(int from,int to);
}
