package com.campusCloudStorage.enums;

public enum GroupRequestStateEnum {
    SUCCESS(0,"加群请求发送成功！"),
    REPEAT(1,"请勿重复发送加群请求！"),
    SELF_REQUEST(2,"您申请的是自己建立的群组！"),
    NO_GROUP(3,"无此群组！");


    private int state;

    private String stateInfo;

    GroupRequestStateEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public static GroupRequestStateEnum stateOf(int index){
        for(GroupRequestStateEnum stateEnum:values()){
            if(stateEnum.getState()==index){
                return stateEnum;
            }
        }
        return null;
    }
}
