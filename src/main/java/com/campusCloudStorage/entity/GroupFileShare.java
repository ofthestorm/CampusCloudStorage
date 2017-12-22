package com.campusCloudStorage.entity;

public class GroupFileShare extends GroupFileShareKey {
    private String remark;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}