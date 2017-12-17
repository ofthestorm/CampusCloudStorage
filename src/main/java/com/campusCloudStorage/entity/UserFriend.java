package com.campusCloudStorage.entity;

public class UserFriend {
    private Integer from;

    private Integer to;

    private Byte permitted;

    public Integer getFrom() {
        return from;
    }

    public void setFrom(Integer from) {
        this.from = from;
    }

    public Integer getTo() {
        return to;
    }

    public void setTo(Integer to) {
        this.to = to;
    }

    public Byte getPermitted() {
        return permitted;
    }

    public void setPermitted(Byte permitted) {
        this.permitted = permitted;
    }
}