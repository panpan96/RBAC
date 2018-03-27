package com.juphoon.app.service;

import com.github.pagehelper.PageInfo;
import com.juphoon.app.entity.SysAcl;


public interface SysAclService {
    void addSysAcl(SysAcl sysAcl);

    SysAcl getSysAclById(Integer id);

    void updateSysAcl(SysAcl sysAcl);

    void delSysAclById(Integer id);

    PageInfo<SysAcl> getSysAclList(Integer page, Integer size, Integer aclModuleId);
}
