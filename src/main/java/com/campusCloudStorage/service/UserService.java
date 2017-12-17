package com.campusCloudStorage.service;

import com.campusCloudStorage.entity.User;
import com.campusCloudStorage.enums.LoginStateEnum;
import com.campusCloudStorage.enums.RegisterStateEnum;
import com.campusCloudStorage.enums.UpdateStateEnum;

public interface UserService {
    User selectByPrimaryKey(int uId);

    RegisterStateEnum register(User user);

    LoginStateEnum validate(User user);

    UpdateStateEnum update(User user);

}
