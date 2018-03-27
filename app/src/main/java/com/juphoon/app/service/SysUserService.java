package com.juphoon.app.service;

import com.github.pagehelper.PageInfo;
import com.juphoon.app.entity.SysUser;

import java.util.List;


public interface SysUserService {

    void addUser(String operator,String ip,SysUser sysUser);

    void updateUser(SysUser sysUser);

    SysUser getUserById(Integer id);

    SysUser validate(String username, String intPassword);

    PageInfo<SysUser> getSysUserList(Integer page, Integer size);

    List<SysUser> getAll();

    void changeUserStatus(Integer id, Integer status, String operator, String ip);
}
