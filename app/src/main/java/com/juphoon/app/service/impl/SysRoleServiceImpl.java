package com.juphoon.app.service.impl;

import com.google.common.collect.Lists;
import com.juphoon.app.entity.SysRole;
import com.juphoon.app.entity.SysUser;
import com.juphoon.app.exception.BusinessException;
import com.juphoon.app.mapper.SysRoleAclMapper;
import com.juphoon.app.mapper.SysRoleMapper;
import com.juphoon.app.mapper.SysRoleUserMapper;
import com.juphoon.app.mapper.SysUserMapper;
import com.juphoon.app.service.SysRoleService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private SysRoleUserMapper sysRoleUserMapper;

    @Autowired
    private SysRoleAclMapper sysRoleAclMapper;

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public void addSysRole(SysRole sysRole) {
        if (checkExist(sysRole.getName(), sysRole.getId())) {
            throw new BusinessException("角色名称已经存在");
        }
        sysRoleMapper.insertSelective(sysRole);

    }

    @Override
    public SysRole getSysRoleById(Integer id) {
        return sysRoleMapper.selectByPrimaryKey(id);
    }

    @Override
    public void updateSysRole(SysRole sysRole) {

        if (checkExist(sysRole.getName(), sysRole.getId())) {
            throw new BusinessException("角色名称已经存在");
        }
       sysRoleMapper.updateByPrimaryKeySelective(sysRole);
    }

    @Override
    public List<SysRole> getSysRoleList() {
        return sysRoleMapper.getAll();
    }


    @Override
    public List<SysRole> getRoleListByUserId(int userId) {
        List<Integer> roleIdList = sysRoleUserMapper.getRoleIdListByUserId(userId);
        if (CollectionUtils.isEmpty(roleIdList)) {
            return Lists.newArrayList();
        }
        return sysRoleMapper.getByIdList(roleIdList);
    }

    @Override
    public List<SysRole> getRoleListByAclId(int aclId) {
        List<Integer> roleIdList = sysRoleAclMapper.getRoleIdListByAclId(aclId);
        if (CollectionUtils.isEmpty(roleIdList)) {
            return Lists.newArrayList();
        }
        return sysRoleMapper.getByIdList(roleIdList);
    }

    @Override
    public List<SysUser> getUserListByRoleList(List<SysRole> roleList) {

        if (CollectionUtils.isEmpty(roleList)) {
            return Lists.newArrayList();
        }
        List<Integer> roleIdList = roleList.stream().map(role -> role.getId()).collect(Collectors.toList());
        List<Integer> userIdList = sysRoleUserMapper.getUserIdListByRoleIdList(roleIdList);
        if (CollectionUtils.isEmpty(userIdList)) {
            return Lists.newArrayList();
        }
        return sysUserMapper.getByIdList(userIdList);
    }


    private boolean checkExist(String name, Integer id) {
        return sysRoleMapper.countByName(name, id) > 0;
    }
}
