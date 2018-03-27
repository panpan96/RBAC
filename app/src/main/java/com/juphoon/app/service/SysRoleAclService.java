package com.juphoon.app.service;

import java.util.List;

public interface SysRoleAclService {
    void changeRoleAcls(int roleId, List<Integer> aclIdList,String operator,String ip);
}
