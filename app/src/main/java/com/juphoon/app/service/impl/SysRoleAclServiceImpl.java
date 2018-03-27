package com.juphoon.app.service.impl;

import com.google.common.collect.Lists;
import com.juphoon.app.entity.SysRoleAcl;
import com.juphoon.app.mapper.SysRoleAclMapper;
import com.juphoon.app.service.SysRoleAclService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.util.List;


@Service
public class SysRoleAclServiceImpl implements SysRoleAclService {

    @Autowired
    private SysRoleAclMapper sysRoleAclMapper;

    @Override
    public void changeRoleAcls(int roleId, List<Integer> aclIdList,String operator,String ip) {

//        List<Integer> originAclIdList = sysRoleAclMapper.getAclIdListByRoleIdList(Lists.newArrayList(roleId));
//        if (originAclIdList.size() == aclIdList.size()) {
//            Set<Integer> originAclIdSet = Sets.newHashSet(originAclIdList);
//            Set<Integer> aclIdSet = Sets.newHashSet(aclIdList);
//            originAclIdSet.removeAll(aclIdSet);
//            if (CollectionUtils.isEmpty(originAclIdSet)) {
//                return;
//            }
//        }
        updateRoleAcls(roleId, aclIdList,operator,ip);
       // saveRoleAclLog(roleId, originAclIdList, aclIdList);
    }

    @Transactional
    public void updateRoleAcls(int roleId, List<Integer> aclIdList,String operator,String ip) {
        sysRoleAclMapper.deleteByRoleId(roleId);

        if (CollectionUtils.isEmpty(aclIdList)) {
            return;
        }
        List<SysRoleAcl> roleAclList = Lists.newArrayList();
        for(Integer aclId : aclIdList) {
            SysRoleAcl roleAcl = new SysRoleAcl(roleId,aclId,operator,new Date(),ip);
//            roleAcl.setOperator(operator);
//            roleAcl.setOperateIp(ip);
//                    SysRoleAcl.builder().roleId(roleId).aclId(aclId).operator(RequestHolder.getCurrentUser().getUsername())
//                    .operateIp(IpUtil.getRemoteIp(RequestHolder.getCurrentRequest())).operateTime(new Date()).build();
            roleAclList.add(roleAcl);
        }
        sysRoleAclMapper.batchInsert(roleAclList);
    }
}
