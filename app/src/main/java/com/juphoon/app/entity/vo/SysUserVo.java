package com.juphoon.app.entity.vo;

import com.juphoon.app.dto.AclModuleLevelDto;
import com.juphoon.app.entity.SysUser;

import java.util.List;

public class SysUserVo extends SysUser {
    private String uuid;

    private List<AclModuleLevelDto> aclModuleLevelDtoList;

    public SysUserVo(String uuid, List<AclModuleLevelDto> aclModuleLevelDtoList) {
        this.uuid = uuid;
        this.aclModuleLevelDtoList = aclModuleLevelDtoList;
    }

    public SysUserVo() {
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUuid() {
        return this.uuid;
    }

    public List<AclModuleLevelDto> getAclModuleLevelDtoList() {
        return aclModuleLevelDtoList;
    }

    public void setAclModuleLevelDtoList(List<AclModuleLevelDto> aclModuleLevelDtoList) {
        this.aclModuleLevelDtoList = aclModuleLevelDtoList;
    }
}
