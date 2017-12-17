package com.campusCloudStorage.service;

import com.campusCloudStorage.entity.Dir;
import com.campusCloudStorage.entity.FileHeader;
import com.campusCloudStorage.enums.DeleteStateEnum;
import com.campusCloudStorage.enums.CreateStateEnum;
import com.campusCloudStorage.enums.UpdateStateEnum;

import java.util.List;


public interface DirService {
    CreateStateEnum createDir(Dir dir);

    UpdateStateEnum update(Dir dir);

    DeleteStateEnum deleteByPrimaryKey(int dId);

    Dir selectByPrimaryKey(int dId);

    Dir selectWithFirstChildrenById(int dId);

    List<Dir> getFirstChildrenDirList(int dId);

    List<FileHeader> getFirstChildrenFileHeaderList(int dId);

    Dir selectWithAllChildrenById(int dId);

    List<Dir> getSiblingsDirList(Dir dir);

    List<Dir> getPathList(Dir dir);

}
