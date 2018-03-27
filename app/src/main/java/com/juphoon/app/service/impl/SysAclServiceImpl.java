package com.juphoon.app.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.juphoon.app.entity.SysAcl;
import com.juphoon.app.exception.BusinessException;
import com.juphoon.app.mapper.SysAclMapper;
import com.juphoon.app.service.SysAclService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class SysAclServiceImpl implements SysAclService {

    @Autowired
    private SysAclMapper sysAclMapper;

    @Override
    public void addSysAcl(SysAcl sysAcl) {
      if(checkExist(sysAcl.getAclModuleId(),sysAcl.getName(),sysAcl.getId())){
          throw new BusinessException("当前权限模块下面存在相同名称的权限点");
      }
        sysAcl.setCode(generateCode());
        sysAcl.setCreateTime(new Date());
        sysAclMapper.insertSelective(sysAcl);
    }




    public boolean checkExist(int aclModuleId, String name, Integer id) {
        return sysAclMapper.countByNameAndAclModuleId(aclModuleId, name, id) > 0;
    }

    public String generateCode() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        return dateFormat.format(new Date()) + "_" + (int)(Math.random() * 100);
    }
//    private boolean checkExist(Integer parentId, String aclModuleName, Integer deptId) {
//        return sysAclModuleMapper.countByNameAndParentId(parentId, aclModuleName, deptId) > 0;
//    }

    @Override
    public SysAcl getSysAclById(Integer id) {

        return sysAclMapper.selectByPrimaryKey(id);
    }

    @Override
    public void updateSysAcl(SysAcl sysAcl) {

        if(checkExist(sysAcl.getAclModuleId(),sysAcl.getName(),sysAcl.getId())){
            throw new BusinessException("当前权限模块下面存在相同名称的权限点");
        }
//        SysAcl before = sysAclMapper.selectByPrimaryKey(sysAcl.getId());
//        if(before==null) {
//            throw new BusinessException("待更新的权限点不存在");
//        }
        sysAclMapper.updateByPrimaryKeySelective(sysAcl);
    }

    @Override
    public void delSysAclById(Integer id) {

    }

    @Override
    public PageInfo<SysAcl> getSysAclList(Integer page, Integer size, Integer aclModuleId) {
        PageHelper.startPage(page, size);
        List<SysAcl> sysAclList = sysAclMapper.getSysAclList(aclModuleId);
        return new PageInfo<>(sysAclList);
    }
}
