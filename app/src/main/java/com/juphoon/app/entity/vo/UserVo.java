package com.juphoon.app.entity.vo;

import com.juphoon.app.entity.User;

public class UserVo extends User {
    private String uuid;

    public UserVo(String uuid) {
        this.uuid = uuid;
    }

    public UserVo() {
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUuid() {
        return this.uuid;
    }

}
