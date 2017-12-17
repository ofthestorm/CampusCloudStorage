package com.campusCloudStorage.service.impl;

import com.campusCloudStorage.entity.User;
import com.campusCloudStorage.enums.LoginStateEnum;
import com.campusCloudStorage.enums.RegisterStateEnum;
import com.campusCloudStorage.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "classpath:spring/spring-dao.xml",
        "classpath:spring/spring-service.xml"})
public class UserServiceImplTest {
    @Resource
    private UserService userService;

    @Test
    public void register() throws Exception {
        User user=new User();
        user.setuId(2);
        user.setPassword("2");
        user.setType("PRIVATE");
        user.setName("马兰花");
        RegisterStateEnum registerStateEnum=userService.register(user);
        System.out.println(registerStateEnum.getStateInfo());
    }

    @Test
    public void validate() throws Exception{
        User user=new User();
        user.setuId(2);
        user.setPassword("2");
        LoginStateEnum loginStateEnum=userService.validate(user);
        System.out.println(loginStateEnum.getStateInfo());
    }

}