package com.juphoon.app.dto;

import com.google.common.collect.Lists;
import com.juphoon.app.entity.SysAclModule;
import org.springframework.beans.BeanUtils;
import java.util.List;


public class AclModuleLevelDto extends SysAclModule {

    private List<AclModuleLevelDto> aclModuleList = Lists.newArrayList();

    private List<AclDto> aclList = Lists.newArrayList();

    public List<AclModuleLevelDto> getAclModuleList() {
        return aclModuleList;
    }

    public void setAclModuleList(List<AclModuleLevelDto> aclModuleList) {
        this.aclModuleList = aclModuleList;
    }

    public List<AclDto> getAclList() {
        return aclList;
    }

    public void setAclList(List<AclDto> aclList) {
        this.aclList = aclList;
    }

    public static AclModuleLevelDto adapt(SysAclModule aclModule) {
        AclModuleLevelDto dto = new AclModuleLevelDto();
        BeanUtils.copyProperties(aclModule, dto);
        return dto;
    }
}
