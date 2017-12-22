package com.campusCloudStorage.service.impl;

import com.campusCloudStorage.dao.UserDao;
import com.campusCloudStorage.dao.UserFriendDao;
import com.campusCloudStorage.entity.User;
import com.campusCloudStorage.entity.UserFriend;
import com.campusCloudStorage.entity.UserFriendKey;
import com.campusCloudStorage.enums.DeleteStateEnum;
import com.campusCloudStorage.enums.FriendRequestStateEnum;
import com.campusCloudStorage.service.UserFriendService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service("userFriendService")
public class UserFriendServiceImpl implements UserFriendService{
    @Resource
    private UserFriendDao userFriendDao;

    @Resource
    private UserDao userDao;

    @Override
    public List<User> selectPermittedFriendsByUId(int uId) {
        List<UserFriend> permittedFriendsList=userFriendDao.selectFriends(uId);
        List<User> permittedFriendsDetailList=new ArrayList<User>();
        for (UserFriend userFriend: permittedFriendsList){
            int friendId;
            if(userFriend.getToId()!=uId){
                friendId=userFriend.getToId();
            }else {
                friendId=userFriend.getFromId();
            }
            User friendDetail = userDao.selectByPrimaryKey(friendId);
            permittedFriendsDetailList.add(friendDetail);
        }
        return permittedFriendsDetailList;
    }

    @Override
    public List<User> selectFriendRequestByUId(int uId) {
        List<UserFriend> unpermittedFriendsList=userFriendDao.selectFriendRequests(uId);
        List<User> unpermittedFriendsDetailList=new ArrayList<User>();
        for (UserFriend userFriend: unpermittedFriendsList){
            int friendId;
            if(userFriend.getFromId()!=uId){
                friendId=userFriend.getFromId();
            }else {
                friendId=userFriend.getToId();
            }
            User friendDetail = userDao.selectByPrimaryKey(friendId);
            unpermittedFriendsDetailList.add(friendDetail);
        }
        return unpermittedFriendsDetailList;
    }

    @Override
    public int agreeFriendRequest(int uId, int friendId) {
        UserFriend userFriend=new UserFriend(friendId,uId,new Byte("1"));
        return userFriendDao.updateByPrimaryKey(userFriend);
    }

    @Override
    public int deleteFriend(int uId, int friendId) {
        UserFriendKey userFriendKey=new UserFriendKey(friendId,uId);
        return userFriendDao.deleteByPrimaryKey(userFriendKey);
    }

    @Override
    public FriendRequestStateEnum insertFriendsByPrimaryKey(int from, int to) {
        UserFriend userFriend = new UserFriend();
        userFriend.setFromId(from);
        userFriend.setToId(to);

        User friend=userDao.selectByPrimaryKey(to);
        if(friend.getType().equals("PRIVATE")){
            userFriend.setPermitted(new Byte("0"));
        }else {
            userFriend.setPermitted(new Byte("1"));
        }

        int insertCount=userFriendDao.insert(userFriend);
        if(insertCount==0){
            return FriendRequestStateEnum.REPEAT;
        }
        return FriendRequestStateEnum.SUCCESS;
    }
}
