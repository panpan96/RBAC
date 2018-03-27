package com.juphoon.app.service;

import com.juphoon.app.dto.AclModuleLevelDto;

import java.util.List;

public interface SysTreeService {

    List<AclModuleLevelDto> aclModuleTree();

    List<AclModuleLevelDto> roleTree(int roleId,Integer userId);


    List<AclModuleLevelDto> userAclTree(int userId);
}
