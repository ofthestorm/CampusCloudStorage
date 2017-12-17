package com.campusCloudStorage.service;


import com.campusCloudStorage.entity.FileHeader;
import com.campusCloudStorage.enums.CreateStateEnum;
import com.campusCloudStorage.enums.DeleteStateEnum;

public interface FileHeaderService {
    CreateStateEnum createFileHeader(FileHeader fileHeader);

    DeleteStateEnum deleteByPrimaryKey(int fId);

    FileHeader selectByPrimaryKey(int fId);
}
