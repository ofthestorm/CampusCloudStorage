package com.campusCloudStorage.dto;

import com.campusCloudStorage.entity.FileHeader;
import com.campusCloudStorage.entity.UserFileShare;

public class FriendFileShareItem {
    private UserFileShare userFileShare;
    private FileHeader fileHeader;

    public UserFileShare getUserFileShare() {
        return userFileShare;
    }

    public void setUserFileShare(UserFileShare userFileShare) {
        this.userFileShare = userFileShare;
    }

    public FileHeader getFileHeader() {
        return fileHeader;
    }

    public void setFileHeader(FileHeader fileHeader) {
        this.fileHeader = fileHeader;
    }
}
