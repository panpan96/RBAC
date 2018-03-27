package com.juphoon.app.service;

import com.juphoon.app.entity.SysRole;
import com.juphoon.app.entity.SysUser;

import java.util.List;

public interface SysRoleService {

    void addSysRole(SysRole sysRole);

    SysRole getSysRoleById(Integer id);

    void updateSysRole(SysRole sysRole);

    List<SysRole> getSysRoleList();

    List<SysRole> getRoleListByUserId(int userId);

    List<SysRole> getRoleListByAclId(int aclId);

    List<SysUser> getUserListByRoleList(List<SysRole> roleList);
}
