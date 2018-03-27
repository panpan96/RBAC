package com.juphoon.app.service.impl;

import com.juphoon.app.common.LevelUtil;
import com.juphoon.app.entity.SysAclModule;
import com.juphoon.app.exception.BusinessException;
import com.juphoon.app.mapper.SysAclMapper;
import com.juphoon.app.mapper.SysAclModuleMapper;
import com.juphoon.app.service.SysAclModuleService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class SysAclModuleServiceImpl implements SysAclModuleService{

    @Autowired
    private SysAclModuleMapper sysAclModuleMapper;


    @Autowired
    private SysAclMapper sysAclMapper;
    @Override
    public void addSysAclModule(SysAclModule sysAclModule) {
        if(checkExist(sysAclModule.getParentId(), sysAclModule.getName(), sysAclModule.getId())) {
            throw new BusinessException("同一层级下存在相同名称的权限模块");
        }
        sysAclModule.setLevel(LevelUtil.calculateLevel(getLevel(sysAclModule.getParentId()), sysAclModule.getParentId()));
        sysAclModule.setCreateTime(new Date());
        sysAclModuleMapper.insertSelective(sysAclModule);
    }


    //删除角色模块
    @Override
    public void delSysAclModule(Integer id) {

        SysAclModule aclModule = sysAclModuleMapper.selectByPrimaryKey(id);
        if(aclModule==null){
            throw new BusinessException("待删除的权限模块不存在，无法删除");
        }
        if(sysAclModuleMapper.countByParentId(aclModule.getId()) > 0) {
            throw new BusinessException("当前模块下面有子模块，无法删除");
        }
        if (sysAclMapper.countByAclModuleId(aclModule.getId()) > 0) {
            throw new BusinessException("当前模块下面有模块点，无法删除");
        }
        sysAclModuleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public SysAclModule getSysAclModuleById(Integer id) {

        return sysAclModuleMapper.selectByPrimaryKey(id);
    }

    @Override
    public void updateSysAclModule(SysAclModule sysAclModule) {
        if(checkExist(sysAclModule.getParentId(), sysAclModule.getName(), sysAclModule.getId())) {
            throw new BusinessException("同一层级下存在相同名称的权限模块");
        }
        SysAclModule before = sysAclModuleMapper.selectByPrimaryKey(sysAclModule.getId());
        if(before==null){
            throw new BusinessException("待更新的权限模块不存在");
        }
        sysAclModule.setLevel(LevelUtil.calculateLevel(getLevel(sysAclModule.getParentId()), sysAclModule.getParentId()));
        updateWithChild(before, sysAclModule);
    }


    @Transactional
    public void updateWithChild(SysAclModule before, SysAclModule after) {
        String newLevelPrefix = after.getLevel();  //0.3
        String oldLevelPrefix = before.getLevel();  //0.6
        if (!after.getLevel().equals(before.getLevel())) {
            List<SysAclModule> aclModuleList = sysAclModuleMapper.getChildAclModuleListByLevel(before.getLevel()+"."+before.getId());
            if (CollectionUtils.isNotEmpty(aclModuleList)) {
                for (SysAclModule aclModule : aclModuleList) {
                    String level = aclModule.getLevel();
                    if (level.indexOf(oldLevelPrefix) == 0) {
                        level = newLevelPrefix + level.substring(oldLevelPrefix.length());
                        aclModule.setLevel(level);
                    }
                }
                for (SysAclModule sysAclModule:aclModuleList
                     ) {
                    sysAclModuleMapper.UpdateLevel(sysAclModule.getLevel(),sysAclModule.getId());
                }
                System.out.println("aclModuleList"+aclModuleList);
            //   sysAclModuleMapper.batchUpdateLevel(aclModuleList);
            }
        }
        sysAclModuleMapper.updateByPrimaryKeySelective(after);
    }


    private boolean checkExist(Integer parentId, String aclModuleName, Integer deptId) {
        return sysAclModuleMapper.countByNameAndParentId(parentId, aclModuleName, deptId) > 0;
    }

    private String getLevel(Integer aclModuleId) {
        SysAclModule aclModule = sysAclModuleMapper.selectByPrimaryKey(aclModuleId);
        if (aclModule == null) {
            return null;
        }
        return aclModule.getLevel();
    }
}
