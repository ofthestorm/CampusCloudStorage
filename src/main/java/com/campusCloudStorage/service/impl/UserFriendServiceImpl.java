package com.campusCloudStorage.service.impl;

import com.campusCloudStorage.dao.UserDao;
import com.campusCloudStorage.entity.User;
import com.campusCloudStorage.enums.DeleteStateEnum;
import com.campusCloudStorage.service.UserFriendService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("userFriendService")
public class UserFriendServiceImpl implements UserFriendService{
    @Resource
    private UserFriendDao userFriendDao;

    @Resource
    private UserDao userDao;

    @Override
    public List<User> selectPermittedFriendsByUId(int uId) {

        return null;
    }

    @Override
    public List<UserFriend> selectFriendRequestByUId(int uId) {
        return null;
    }

    @Override
    public DeleteStateEnum deleteFriendsByPrimaryKey(int from, int to) {
        return null;
    }

    @Override
    public boolean insertFriendsByPrimaryKey(int from, int to) {
        return false;
    }
}
