package com.campusCloudStorage.service;

import com.campusCloudStorage.entity.User;
import com.campusCloudStorage.entity.UserFriend;
import com.campusCloudStorage.enums.DeleteStateEnum;
import com.campusCloudStorage.enums.FriendRequestStateEnum;

import java.util.List;

public interface UserFriendService {
    public List<User> selectPermittedFriendsByUId(int uId);

    public List<User> selectFriendRequestByUId(int uId);

    public FriendRequestStateEnum insertFriendsByPrimaryKey(int from, int to);

    public int agreeFriendRequest(int uId, int friendId);

    public int deleteFriend(int uId, int friendId);
}
