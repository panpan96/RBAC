package com.juphoon.app.service.impl;


import com.google.common.collect.Lists;
import com.juphoon.app.entity.SysRoleUser;
import com.juphoon.app.entity.SysUser;
import com.juphoon.app.mapper.SysRoleUserMapper;
import com.juphoon.app.mapper.SysUserMapper;
import com.juphoon.app.service.SysRoleUserService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class SysRoleUserServiceImpl implements SysRoleUserService {

    @Autowired
    private SysRoleUserMapper sysRoleUserMapper;

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public List<SysUser> getListByRoleId(int roleId) {

        List<Integer> userIdList = sysRoleUserMapper.getUserIdListByRoleId(roleId);
        if (CollectionUtils.isEmpty(userIdList)) {
            return Lists.newArrayList();
        }
        return sysUserMapper.getByIdList(userIdList);
    }

    @Override
    @Transactional
    public void changeRoleUsers(int roleId, List<Integer> userIdList,String operator,String ip) {

        sysRoleUserMapper.deleteByRoleId(roleId);
        if (CollectionUtils.isEmpty(userIdList)) {
            return;
        }
        List<SysRoleUser> roleUserList = Lists.newArrayList();
        for (Integer userId : userIdList) {
            SysRoleUser roleUser = new SysRoleUser(roleId,userId,operator,new Date(),ip);
            roleUserList.add(roleUser);
        }
        sysRoleUserMapper.batchInsert(roleUserList);
    }

}
