package com.juphoon.app.controller;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.juphoon.app.common.IpUtil;
import com.juphoon.app.common.JedisUtils;
import com.juphoon.app.common.ServerResponse;
import com.juphoon.app.common.StringUtil;
import com.juphoon.app.entity.SysRole;
import com.juphoon.app.entity.SysUser;
import com.juphoon.app.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class SysRoleController extends BaseController {

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysTreeService sysTreeService;

    @Autowired
    private JedisUtils jedisUtils;

    @Autowired
    private SysRoleAclService sysRoleAclService;

    @Autowired
    private SysRoleUserService sysRoleUserService;

    @Autowired
    private SysUserService sysUserService;

    //创建角色
    @RequestMapping(value = "/addSysRole", method = RequestMethod.POST)
    public ServerResponse addSysRole(@Valid SysRole sysRole, BindingResult result,HttpServletRequest request) {
        if (result.hasErrors()) {
            return ServerResponse.error(vailDataErrorMessage(result));
        }
        String uuid = request.getHeader("token");
        if (uuid == null) {
            return ServerResponse.error("uuid不能为空");
        }
        JSONObject jsonObject = JSONObject.parseObject(jedisUtils.getJedis(uuid));
        String operator=jsonObject.getString("username");
        String  ip= IpUtil.getRemoteIp(request);
        sysRole.setOperator(operator);
        sysRole.setOperateIp(ip);
        sysRoleService.addSysRole(sysRole);
        return ServerResponse.success();
    }

    //获取某一角色
    @RequestMapping(value = "/getSysRoleById", method = RequestMethod.GET)
    public ServerResponse getSysRoleById(Integer id) {

        SysRole sysRole = sysRoleService.getSysRoleById(id);
        return ServerResponse.success(sysRole);
    }

    //更新角色
    @RequestMapping(value = "/updateSysRole", method = RequestMethod.POST)
    public ServerResponse updateSysRole(@Valid SysRole sysRole, BindingResult result,HttpServletRequest request) {
        if (result.hasErrors()) {
            return ServerResponse.error(vailDataErrorMessage(result));
        }
        String uuid = request.getHeader("token");
        if (uuid == null) {
            return ServerResponse.error("uuid不能为空");
        }
        JSONObject jsonObject = JSONObject.parseObject(jedisUtils.getJedis(uuid));
        String operator=jsonObject.getString("username");
        String  ip= IpUtil.getRemoteIp(request);
        sysRole.setOperator(operator);
        sysRole.setOperateIp(ip);
        sysRoleService.updateSysRole(sysRole);
        return ServerResponse.success();
    }


    //角色列表
    @RequestMapping(value = "/getSysRoleList", method = RequestMethod.GET)
    public ServerResponse getSysRoleList() {

        List<SysRole> sysRoleList = sysRoleService.getSysRoleList();

        return ServerResponse.success(sysRoleList);
    }

    //根据角色id返回角色权限树
    @RequestMapping(value = "/getRoleTree", method = RequestMethod.GET)
    public ServerResponse roleTree(HttpServletRequest request, int roleId) {

        String uuid = request.getHeader("token");
        if (uuid == null) {
            return ServerResponse.error("uuid不能为空");
        }
        JSONObject jsonObject = JSONObject.parseObject(jedisUtils.getJedis(uuid));
        Integer userId = jsonObject.getInteger("id");
        return ServerResponse.success(sysTreeService.roleTree(roleId, userId));
    }


    //根据角色id,保存修改后角色权限树POST，aclIds:为 1，2，34格式字符串
    @RequestMapping(value = "/saveRoleTree", method = RequestMethod.POST)
    public ServerResponse changeAcls(int roleId, String aclIds,HttpServletRequest request) {
        List<Integer> aclIdList = StringUtil.splitToListInt(aclIds);
        String uuid = request.getHeader("token");
        if (uuid == null) {
            return ServerResponse.error("uuid不能为空");
        }
        JSONObject jsonObject = JSONObject.parseObject(jedisUtils.getJedis(uuid));
        String operator=jsonObject.getString("username");
        String  ip= IpUtil.getRemoteIp(request);
        sysRoleAclService.changeRoleAcls(roleId, aclIdList,operator,ip);
        return ServerResponse.success();
    }

    //根据角色Id，查询此角色的用户列表
    @RequestMapping(value = "/getUsersByRoleId", method = RequestMethod.GET)
    public ServerResponse getUsersByRoleId(int roleId) {

        List<SysUser> selectedUserList = sysRoleUserService.getListByRoleId(roleId);
        List<SysUser> allUserList = sysUserService.getAll();
        List<SysUser> unselectedUserList = Lists.newArrayList();
        Set<Integer> selectedUserIdSet = selectedUserList.stream().map(sysUser -> sysUser.getId()).collect(Collectors.toSet());
        for (SysUser sysUser : allUserList) {
            if (sysUser.getStatus() == 1 && !selectedUserIdSet.contains(sysUser.getId())) {
                unselectedUserList.add(sysUser);
            }
        }
        // selectedUserList = selectedUserList.stream().filter(sysUser -> sysUser.getStatus() != 1).collect(Collectors.toList());
        Map<String, List<SysUser>> map = Maps.newHashMap();
        map.put("selected", selectedUserList);
        map.put("unselected", unselectedUserList);
        return ServerResponse.success(map);
    }

    //保存此角色id下修改的用户，userIds:为 1，2，34格式字符串
    @RequestMapping(value = "/saveRoleUsers", method = RequestMethod.POST)
    public ServerResponse saveRoleUsers(int roleId, String userIds,HttpServletRequest request) {
        List<Integer> userIdList = StringUtil.splitToListInt(userIds);
        String uuid = request.getHeader("token");
        if (uuid == null) {
            return ServerResponse.error("uuid不能为空");
        }
        JSONObject jsonObject = JSONObject.parseObject(jedisUtils.getJedis(uuid));
        String operator=jsonObject.getString("username");
        String  ip= IpUtil.getRemoteIp(request);
        sysRoleUserService.changeRoleUsers(roleId, userIdList,operator,ip);
        return ServerResponse.success();
    }


}