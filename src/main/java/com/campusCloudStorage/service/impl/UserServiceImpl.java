package com.campusCloudStorage.service.impl;

import com.campusCloudStorage.dao.DirDao;
import com.campusCloudStorage.dao.UserDao;
import com.campusCloudStorage.entity.Dir;
import com.campusCloudStorage.entity.User;
import com.campusCloudStorage.enums.LoginStateEnum;
import com.campusCloudStorage.enums.RegisterStateEnum;
import com.campusCloudStorage.enums.UpdateStateEnum;
import com.campusCloudStorage.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service("userService")
public class UserServiceImpl implements UserService{

    @Resource
    private UserDao userDao;

    @Resource
    private DirDao dirDao;

    @Override
    public User selectByPrimaryKey(int uId) {
        User user=userDao.selectByPrimaryKey(uId);
        return user;
    }

    @Override
    public RegisterStateEnum register(User user) {
        if(user==null || user.getName()==null || "".equals(user.getName().trim())
                ||user.getPassword()==null || "".equals(user.getPassword().trim())
                ||user.getuId()==null || user.getuId()<=0){
            return RegisterStateEnum.FAILED;
        }

        User userFromDB=userDao.selectByPrimaryKey(user.getuId());
        if(userFromDB!=null){
            return RegisterStateEnum.ID_REPEAT;
        }

        Dir rootDir=new Dir();
        rootDir.setCreateTime(new Date());
        rootDir.setName("root");
        dirDao.insert(rootDir);
        user.setRootDir(rootDir.getdId());

        Dir recyclebin=new Dir();
        recyclebin.setCreateTime(new Date());
        recyclebin.setName("recyclebin");
        dirDao.insert(recyclebin);
        user.setRecyclebin(recyclebin.getdId());

        int insertCount = userDao.insertSelective(user);
        if(insertCount==1){

            return RegisterStateEnum.SUCCESS;
        }
        return RegisterStateEnum.FAILED;
    }

    @Override
    public LoginStateEnum validate(User user) {
        if(user==null || user.getuId()==null || user.getPassword()==null){
            return LoginStateEnum.IMCOMPLETE;
        }
        User userFromDB = userDao.selectByPrimaryKey(user.getuId());
        if(userFromDB==null || !userFromDB.getPassword().equals(user.getPassword())){
            return LoginStateEnum.INFO_ERROR;
        }
        return LoginStateEnum.SUCCESS;
    }

    @Override
    public UpdateStateEnum update(User user) {
        if(user==null || user.getuId()==null
                || user.getName()==null || "".equals(user.getName().trim())
                ||user.getPassword()==null || "".equals(user.getPassword().trim())){
            return UpdateStateEnum.FAILED;
        }
        int updateCount=userDao.updateByPrimaryKeySelective(user);
        if(updateCount==1){
            return UpdateStateEnum.SUCCESS;
        }
        return UpdateStateEnum.FAILED;
    }
}
