package com.juphoon.app.controller;

import com.alibaba.fastjson.JSONObject;
import com.juphoon.app.common.IpUtil;
import com.juphoon.app.common.JedisUtils;
import com.juphoon.app.common.ServerResponse;
import com.juphoon.app.entity.SysAclModule;
import com.juphoon.app.entity.SysUser;
import com.juphoon.app.service.SysAclModuleService;
import com.juphoon.app.service.SysTreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
public class SysAclModuleController extends BaseController {

    @Autowired
    private SysAclModuleService sysAclModuleService;

    @Autowired
    private SysTreeService sysTreeService;

    @Autowired
    private JedisUtils jedisUtils;

    //创建权限模块
    @RequestMapping(value = "/addSysAclModule", method = RequestMethod.POST)
    public ServerResponse addSysAclModule(@Valid SysAclModule sysAclModule, BindingResult result,HttpServletRequest request) {
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
        sysAclModule.setOperator(operator);
        sysAclModule.setOperateIp(ip);
        sysAclModuleService.addSysAclModule(sysAclModule);
        return ServerResponse.success();
    }


    //根据id 查找权限模块
    @RequestMapping(value = "/getSysAclModuleById", method = RequestMethod.GET)
    public ServerResponse getSysAclModuleById(Integer id) {

        SysAclModule sysAclModule=sysAclModuleService.getSysAclModuleById(id);
        return ServerResponse.success(sysAclModule);
    }

    //更新权限模块
    @RequestMapping(value = "/updateSysAclModule", method = RequestMethod.POST)
    public ServerResponse updateSysAclModule(@Valid SysAclModule sysAclModule, BindingResult result,HttpServletRequest request) {
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
        sysAclModule.setOperator(operator);
        sysAclModule.setOperateIp(ip);
        sysAclModuleService.updateSysAclModule(sysAclModule);
        return ServerResponse.success();
    }

    //删除权限模块

    @RequestMapping(value = "/delSysAclModule", method = RequestMethod.POST)
    public ServerResponse delSysAclModule(Integer id) {

        sysAclModuleService.delSysAclModule(id);
        return ServerResponse.success();
    }

    //获取权限模块树
    @GetMapping("/getSysAclModuleList")
    public ServerResponse getSysAclModuleList() {
        return ServerResponse.success(sysTreeService.aclModuleTree());
    }

}
