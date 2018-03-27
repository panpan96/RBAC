package com.juphoon.app.service;

import com.juphoon.app.entity.SysAclModule;

public interface SysAclModuleService {
    void addSysAclModule(SysAclModule sysAclModule);

    SysAclModule getSysAclModuleById(Integer id);

    void updateSysAclModule(SysAclModule sysAclModule);

    void delSysAclModule(Integer id);
}
