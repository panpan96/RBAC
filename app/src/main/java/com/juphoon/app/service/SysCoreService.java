package com.juphoon.app.service;

import com.juphoon.app.entity.SysAcl;

import java.util.List;

public interface SysCoreService {

    List<SysAcl> getCurrentUserAclList(int userId);

    List<SysAcl> getRoleAclList(int roleId);

    List<SysAcl> getUserAclList(int userId);
}
