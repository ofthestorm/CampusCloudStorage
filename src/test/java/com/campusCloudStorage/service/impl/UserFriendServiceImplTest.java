package com.campusCloudStorage.service.impl;

import com.campusCloudStorage.entity.User;
import com.campusCloudStorage.service.UserFriendService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "classpath:spring/spring-dao.xml",
        "classpath:spring/spring-service.xml"})
public class UserFriendServiceImplTest {
    @Resource
    public UserFriendService userFriendService;

    @Test
    public void selectPermittedFriendsByUId() throws Exception {
    }

    @Test
    public void selectFriendRequestByUId() throws Exception {
        List<User>friendRequests=userFriendService.selectFriendRequestByUId(1);
        for(User friend:friendRequests){
            System.out.println(friend.getName());
        }
    }

    @Test
    public void agreeFriendRequest() throws Exception {
    }

    @Test
    public void deleteFriendRequest() throws Exception {
    }

    @Test
    public void insertFriendsByPrimaryKey() throws Exception {
    }

}