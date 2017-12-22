package com.campusCloudStorage.service;

import com.campusCloudStorage.dto.FriendFileShareItem;
import com.campusCloudStorage.enums.ShareStateEnum;


import java.util.List;

public interface UserFileShareService {
    List<FriendFileShareItem> selectSharedFilesByUId(int fromId, int toId);

    ShareStateEnum insertByPrimaryKey(int fromId, int toId, int fId, String remark);

    boolean deleteByPrimaryKey(int fromId, int toId, int fId);
}
