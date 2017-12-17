package com.campusCloudStorage.entity;

public class User {
    private Integer uId;

    private String password;

    private String name;

    private Integer rootDir;

    private String type;

    public Integer getuId() {
        return uId;
    }

    public void setuId(Integer uId) {
        this.uId = uId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getRootDir() {
        return rootDir;
    }

    public void setRootDir(Integer rootDir) {
        this.rootDir = rootDir;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }
}