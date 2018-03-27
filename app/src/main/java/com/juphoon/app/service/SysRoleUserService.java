package com.juphoon.app.service;

import com.juphoon.app.entity.SysUser;

import java.util.List;

public interface SysRoleUserService {
    List<SysUser> getListByRoleId(int roleId);

    void changeRoleUsers(int roleId, List<Integer> userIdList,String operator,String ip);
}
