package com.juphoon.app.service.impl;

import com.google.common.collect.Lists;
import com.juphoon.app.entity.SysAcl;
import com.juphoon.app.entity.SysRoleUser;
import com.juphoon.app.entity.SysUser;
import com.juphoon.app.mapper.SysAclMapper;
import com.juphoon.app.mapper.SysRoleAclMapper;
import com.juphoon.app.mapper.SysRoleUserMapper;
import com.juphoon.app.mapper.SysUserMapper;
import com.juphoon.app.service.SysCoreService;
import org.apache.commons.collections.CollectionUtils;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysCoreServiceImpl implements SysCoreService{

    @Autowired
    private SysRoleAclMapper sysRoleAclMapper;
    @Autowired
    private SysAclMapper sysAclMapper;
    @Autowired
    private SysRoleUserMapper sysRoleUserMapper;
    @Autowired
    private SysUserMapper sysUserMapper;


    public List<SysAcl> getUserAclList(int userId) {

        //是否是超级管理员
        if (isSuperAdmin(userId)) {
            return sysAclMapper.getAll();
        }
        List<Integer> userRoleIdList = sysRoleUserMapper.getRoleIdListByUserId(userId);
        if (CollectionUtils.isEmpty(userRoleIdList)) {
            return Lists.newArrayList();
        }
        List<Integer> userAclIdList = sysRoleAclMapper.getAclIdListByRoleIdList(userRoleIdList);
        if (CollectionUtils.isEmpty(userAclIdList)) {
            return Lists.newArrayList();
        }
        return sysAclMapper.getByIdList(userAclIdList);
    }

    public boolean isSuperAdmin(Integer userId) {
        // 这里是我自己定义了一个超级管理员规则(指定Admin这个用户为超级管理员)，
        // 可以指定某个用户，也可以指定某个角色
        SysUser sysUser = sysUserMapper.selectByPrimaryKey(userId);
        if (sysUser.getUsername().equals("Admin")) {
            return true;
        }
        return false;
    }
    @Override
    public List<SysAcl> getCurrentUserAclList(int userId) {

        List<Integer> userRoleIdList = sysRoleUserMapper.getRoleIdListByUserId(userId);
        if (CollectionUtils.isEmpty(userRoleIdList)) {
            return Lists.newArrayList();
        }
        List<Integer> userAclIdList = sysRoleAclMapper.getAclIdListByRoleIdList(userRoleIdList);
        if (CollectionUtils.isEmpty(userAclIdList)) {
            return Lists.newArrayList();
        }
        return sysAclMapper.getByIdList(userAclIdList);
    }

    @Override
    public List<SysAcl> getRoleAclList(int roleId) {

        List<Integer> aclIdList = sysRoleAclMapper.getAclIdListByRoleIdList(Lists.<Integer>newArrayList(roleId));
        if (CollectionUtils.isEmpty(aclIdList)) {
            return Lists.newArrayList();
        }
        return sysAclMapper.getByIdList(aclIdList);
    }

}
