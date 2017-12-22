package com.campusCloudStorage.dao;

import com.campusCloudStorage.entity.UserFriend;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-dao.xml")
public class UserFriendDaoTest {
    @Resource
    private UserFriendDao userFriendDao;

    @Test
    public void selectFriends() throws Exception {
        List<UserFriend> friends= userFriendDao.selectFriends(1);
        for (UserFriend userFriend:friends){
            System.out.println(userFriend.getToId()+"  "+userFriend.getFromId());
        }
    }

    @Test
    public void selectFriendRequests()throws Exception{
        List<UserFriend> friendRequests=userFriendDao.selectFriendRequests(1);
        for(UserFriend friend:friendRequests){
            System.out.println(friend.getFromId()+" "+friend.getToId());
        }
    }

}