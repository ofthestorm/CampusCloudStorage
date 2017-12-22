package com.campusCloudStorage.service;

import com.campusCloudStorage.dto.GroupFileShareItem;
import com.campusCloudStorage.enums.DeleteStateEnum;
import com.campusCloudStorage.enums.ShareStateEnum;

import java.util.List;

public interface GroupFileShareService {
    List<GroupFileShareItem> selectSharedFilesByGId(int gId);

    ShareStateEnum insert(int uId,int gId,int fId,String remark);

    DeleteStateEnum deleteByPrimaryKey(int uId, int gId, int fId);

    DeleteStateEnum deleteByGIdAndFId(int gId, int fId);
}
