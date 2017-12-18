package com.campusCloudStorage.entity;

public class UserFriend extends UserFriendKey {
    private Byte permitted;

    public Byte getPermitted() {
        return permitted;
    }

    public void setPermitted(Byte permitted) {
        this.permitted = permitted;
    }
}