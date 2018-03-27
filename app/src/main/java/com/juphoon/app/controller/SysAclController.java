package com.juphoon.app.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.juphoon.app.common.IpUtil;
import com.juphoon.app.common.JedisUtils;
import com.juphoon.app.common.ServerResponse;
import com.juphoon.app.entity.SysAcl;
import com.juphoon.app.entity.SysRole;
import com.juphoon.app.service.SysAclService;
import com.juphoon.app.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class SysAclController extends BaseController {

    @Autowired
    private SysAclService sysAclService;

    @Autowired
    private JedisUtils jedisUtils;

    @Autowired
    private SysRoleService sysRoleService;

    //创建权限点
    @RequestMapping(value = "/addSysAcl", method = RequestMethod.POST)
    public ServerResponse addSysAcl(@Valid SysAcl sysAcl, BindingResult result,HttpServletRequest request) {
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
        sysAcl.setOperator(operator);
        sysAcl.setOperateIp(ip);
        sysAclService.addSysAcl(sysAcl);
        return ServerResponse.success();
    }

    //获取某一权限点
    @RequestMapping(value = "/getSysAclById", method = RequestMethod.GET)
    public ServerResponse getSysAclById(Integer id) {
        //System.out.println("id"+id);
        SysAcl sysAcl=sysAclService.getSysAclById(id);
        return ServerResponse.success(sysAcl);
    }

  //更新权限点
    @RequestMapping(value = "/updateSysAcl", method = RequestMethod.POST)
    public ServerResponse updateSysAclModule(@Valid SysAcl sysAcl, BindingResult result) {

        if (result.hasErrors()) {
            return ServerResponse.error(vailDataErrorMessage(result));
        }
            sysAclService.updateSysAcl(sysAcl);
        return ServerResponse.success();
    }
    //根据权限模块id,查看权限点列表
    @RequestMapping(value = "/getSysAclList", method = RequestMethod.GET)
    public ServerResponse getSysAclList(Integer page, Integer size, Integer aclModuleId) {

        PageInfo<SysAcl> sysAclPageInfo = sysAclService.getSysAclList(page, size, aclModuleId);
        Map<String, Object> map = new HashMap();
        map.put("total", sysAclPageInfo.getTotal());
        map.put("list", sysAclPageInfo.getList());
        return ServerResponse.success(map);
    }

    //根据权限模块id,查询分配次权限点的角色与用户list
    @RequestMapping(value = "/getRolesAndUsersByAclId", method = RequestMethod.GET)
    public ServerResponse acls(int aclId) {
        Map<String, Object> map = Maps.newHashMap();
        List<SysRole> roleList = sysRoleService.getRoleListByAclId(aclId);
        map.put("roles", roleList);
        map.put("users", sysRoleService.getUserListByRoleList(roleList));
        return ServerResponse.success(map);
    }

}
