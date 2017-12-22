package com.campusCloudStorage.dto;

import com.campusCloudStorage.entity.FileHeader;
import com.campusCloudStorage.entity.GroupFileShare;
import com.campusCloudStorage.entity.User;
import com.campusCloudStorage.entity.UserFileShare;

public class GroupFileShareItem {
    private GroupFileShare groupFileShare;
    private FileHeader fileHeader;
    private User provider;

    public GroupFileShare getGroupFileShare() {
        return groupFileShare;
    }

    public void setGroupFileShare(GroupFileShare groupFileShare) {
        this.groupFileShare = groupFileShare;
    }

    public FileHeader getFileHeader() {
        return fileHeader;
    }

    public void setFileHeader(FileHeader fileHeader) {
        this.fileHeader = fileHeader;
    }

    public User getProvider() {
        return provider;
    }

    public void setProvider(User provider) {
        this.provider = provider;
    }
}
