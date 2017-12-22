package com.campusCloudStorage.dao;

import com.campusCloudStorage.entity.GroupFileShare;
import com.campusCloudStorage.entity.GroupFileShareKey;

import java.util.List;

public interface GroupFileShareDao {
    int deleteByPrimaryKey(GroupFileShareKey key);

    int deleteByGIdAndProviderId(Integer gId, Integer providerId);

    int deleteByGIdAndFId(Integer gId, Integer fId);

    int deleteByGId(Integer gId);

    int insert(GroupFileShare record);

    int insertSelective(GroupFileShare record);

    GroupFileShare selectByPrimaryKey(GroupFileShareKey key);

    List<GroupFileShare> selectByGId(Integer gId);

    int updateByPrimaryKeySelective(GroupFileShare record);

    int updateByPrimaryKey(GroupFileShare record);
}