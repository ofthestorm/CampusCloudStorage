package com.campusCloudStorage.entity;

public class GroupMember extends GroupMemberKey {
    private Byte permitted;

    public Byte getPermitted() {
        return permitted;
    }

    public void setPermitted(Byte permitted) {
        this.permitted = permitted;
    }
}