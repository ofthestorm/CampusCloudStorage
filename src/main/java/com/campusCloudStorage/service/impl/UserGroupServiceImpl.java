package com.campusCloudStorage.service.impl;

import com.campusCloudStorage.dao.GroupFileShareDao;
import com.campusCloudStorage.dao.GroupMemberDao;
import com.campusCloudStorage.dao.UserDao;
import com.campusCloudStorage.dao.UserGroupDao;
import com.campusCloudStorage.entity.GroupMember;
import com.campusCloudStorage.entity.User;
import com.campusCloudStorage.entity.UserGroup;
import com.campusCloudStorage.enums.CreateStateEnum;
import com.campusCloudStorage.enums.DeleteStateEnum;
import com.campusCloudStorage.service.UserGroupService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service("userGroupService")
public class UserGroupServiceImpl implements UserGroupService{
    @Resource
    private UserDao userDao;

    @Resource
    private UserGroupDao userGroupDao;

    @Resource
    private GroupMemberDao groupMemberDao;

    @Resource
    private GroupFileShareDao groupFileShareDao;

    @Override
    public UserGroup selectByPrimaryKey(int gId) {
        return userGroupDao.selectByPrimaryKey(gId);
    }

    @Override
    public List<UserGroup> selectOwnedGroups(int builderId) {
        return userGroupDao.selectByBuilderId(builderId);
    }

    @Override
    public List<UserGroup> selectJoinedGroups(int uId) {
        List<GroupMember>groupMemberList=groupMemberDao.selectPermittedByUId(uId);
        List<UserGroup> detailGroupList=new ArrayList<UserGroup>();
        for (GroupMember groupMember:groupMemberList){
            UserGroup userGroup = userGroupDao.selectByPrimaryKey(groupMember.getgId());
            if (userGroup!=null){
                detailGroupList.add(userGroup);
            }
        }
        return detailGroupList;
    }

    @Override
    public List<UserGroup> selectOwnedAndJoinedGroups(int uId) {
        List<UserGroup>groupList=selectOwnedGroups(uId);
        groupList.addAll(selectJoinedGroups(uId));
        return groupList;
    }

    @Override
    public List<User> selectMembers(int gId) {
        List<GroupMember> groupMemberList=groupMemberDao.selectMembersByGId(gId);
        List<User> detailMemberList=new ArrayList<User>();
        for (GroupMember groupMember:groupMemberList){
            User member=userDao.selectByPrimaryKey(groupMember.getuId());
            if(member!=null){
                detailMemberList.add(member);
            }
        }
        return detailMemberList;
    }

    @Override
    public User selectOwner(int gId) {
        UserGroup userGroup=userGroupDao.selectByPrimaryKey(gId);
        if(userGroup!=null){
            return userDao.selectByPrimaryKey(userGroup.getBuilderId());
        }
        return null;
    }

    @Override
    public DeleteStateEnum deleteUserGroup(int uId, int gId) {
        UserGroup userGroup=userGroupDao.selectByPrimaryKey(gId);
        if(userGroup==null||userGroup.getBuilderId()!=uId){
            return DeleteStateEnum.FAILED;
        }
        int deleteCount=userGroupDao.deleteByPrimaryKey(gId);
        if(deleteCount==1){
            groupMemberDao.deleteByGId(gId);
            groupFileShareDao.deleteByGId(gId);
            return DeleteStateEnum.SUCCESS;
        }
        return DeleteStateEnum.FAILED;
    }

    @Override
    public CreateStateEnum createUserGroup(UserGroup userGroup) {
        if(userGroup==null || userGroup.getName()==null || userGroup.getName().trim().equals("")){
            return CreateStateEnum.IMCOMPLETE;
        }

        int insertCount=userGroupDao.insert(userGroup);
        if(insertCount==1){
            return CreateStateEnum.SUCCESS;
        }
        return CreateStateEnum.FAILED;
    }
}
