package com.campusCloudStorage.service.impl;

import com.campusCloudStorage.entity.UserGroup;
import com.campusCloudStorage.service.UserGroupService;
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
public class UserGroupServiceImplTest {
    @Resource
    private UserGroupService userGroupService;

    @Test
    public void selectUserGroupByBuilderId() throws Exception {
        List<UserGroup>groups=userGroupService.selectOwnedGroups(1);
        System.out.println(groups.size());
    }

    @Test
    public void deleteUserGroupByGId() throws Exception {
    }

    @Test
    public void createUserGroup() throws Exception {
    }

}