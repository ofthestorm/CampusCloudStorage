package com.campusCloudStorage.dao;

import com.campusCloudStorage.entity.Dir;

import java.util.List;

public interface DirDao {
    int deleteByPrimaryKey(Integer dId);

    int insert(Dir record);

    int insertSelective(Dir record);

    Dir selectByPrimaryKey(Integer dId);

    int updateByPrimaryKeySelective(Dir record);

    int updateByPrimaryKey(Dir record);

    List<Dir> selectByParentId(Integer parentId);
}