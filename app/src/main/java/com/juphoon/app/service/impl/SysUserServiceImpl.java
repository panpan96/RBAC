package com.juphoon.app.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.juphoon.app.common.Md5Encrypt;
import com.juphoon.app.entity.SysUser;
import com.juphoon.app.exception.BusinessException;
import com.juphoon.app.mapper.SysUserMapper;
import com.juphoon.app.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.util.List;

@Service
public class SysUserServiceImpl implements SysUserService{

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    @Transactional
    public void addUser(String operator,String ip,SysUser sysUser) {
        if (checkExist(sysUser.getUsername())) {
            throw new BusinessException("用户名称已经存在");
        }
        sysUser.setStatus(1);
        sysUser.setCreateTime(new Date());
        sysUser.setPassword(Md5Encrypt.string2MD5(sysUser.getPassword()));
        sysUser.setOperator(operator);
        sysUser.setOperateIp(ip);
        sysUserMapper.insertSelective(sysUser);
    }

    @Override
    public void updateUser(SysUser sysUser) {
        sysUserMapper.updateByPrimaryKeySelective(sysUser);
    }

    @Override
    public SysUser getUserById(Integer id) {
        return sysUserMapper.selectByPrimaryKey(id);
    }


    @Override
    public List<SysUser> getAll() {
        return sysUserMapper.getAll();
    }

    @Override
    public void changeUserStatus(Integer id, Integer status, String operator, String ip) {
        sysUserMapper.changeUserStatus(id,status,operator,ip);
    }


    @Override
    public SysUser validate(String username,String intPassword) {
        SysUser u = sysUserMapper.findByName(username);
        if(u == null){
            throw new BusinessException("用户名不存在");
        }
        String password = u.getPassword();
        if(Md5Encrypt.string2MD5(intPassword).equals(password)){
            return u;
        }
        throw new BusinessException("密码错误");
    }

    private boolean checkExist(String name) {
        return sysUserMapper.countByName(name) > 0;
    }

    @Override
    public PageInfo<SysUser> getSysUserList(Integer page, Integer size) {
        PageHelper.startPage(page, size);
        List<SysUser> sysUserList = sysUserMapper.getSysUserList();
        return new PageInfo<>(sysUserList);
    }


}
