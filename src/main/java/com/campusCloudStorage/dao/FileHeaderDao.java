package com.campusCloudStorage.dao;

import com.campusCloudStorage.entity.FileHeader;

import java.util.List;

public interface FileHeaderDao {
    int deleteByPrimaryKey(Integer fId);

    int insert(FileHeader record);

    int insertSelective(FileHeader record);

    FileHeader selectByPrimaryKey(Integer fId);

    List<FileHeader> selectByParentId(Integer parentId);

    int updateByPrimaryKeySelective(FileHeader record);

    int updateByPrimaryKey(FileHeader record);
}