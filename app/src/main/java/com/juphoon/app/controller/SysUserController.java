package com.juphoon.app.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.juphoon.app.common.IpUtil;
import com.juphoon.app.common.JedisUtils;
import com.juphoon.app.common.ServerResponse;
import com.juphoon.app.entity.SysUser;
import com.juphoon.app.service.SysRoleService;
import com.juphoon.app.service.SysTreeService;
import com.juphoon.app.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Map;

/**
 * Created by ztf on 22/26
 */
@RestController
public class SysUserController extends BaseController  {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private JedisUtils jedisUtils;

    @Autowired
    private SysTreeService sysTreeService;

     @Autowired
     private SysRoleService sysRoleService;

    //创建用户
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public ServerResponse addApp(@Valid SysUser sysUser, BindingResult result,HttpServletRequest request) {
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
         sysUserService.addUser(operator,ip,sysUser);
        return ServerResponse.success();
    }

    //根据id 查找用户
    @RequestMapping(value = "/getUserById", method = RequestMethod.GET)
    public ServerResponse getUserById(Integer id) {

        return ServerResponse.success(sysUserService.getUserById(id));
    }

    //更新用户
    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    public ServerResponse updateUser(@Valid SysUser sysUser, BindingResult result,HttpServletRequest request) {
        if (result.hasErrors()) {
            return ServerResponse.error(vailDataErrorMessage(result));
        }
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
        sysUser.setOperator(operator);
        sysUser.setOperateIp(ip);
        sysUserService.updateUser(sysUser);
        return ServerResponse.success();
    }

    //删除用户，status设为0,冻结用户 status设为2,解冻账户，status设为1
    @RequestMapping(value = "/changeUserStatus", method = RequestMethod.POST)
    public ServerResponse changeUserStatus(Integer id,Integer status,HttpServletRequest request) {

        String uuid = request.getHeader("token");
        if (uuid == null) {
            return ServerResponse.error("uuid不能为空");
        }
        JSONObject jsonObject = JSONObject.parseObject(jedisUtils.getJedis(uuid));
        String operator=jsonObject.getString("username");
        String  ip= IpUtil.getRemoteIp(request);
        sysUserService.changeUserStatus(id,status,operator,ip);
        return ServerResponse.success();
    }

    //获取用户列表,status不为0的账户
    @GetMapping("/getSysUserList")
    public ServerResponse getSysUserList(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "20") Integer size) {
        PageInfo<SysUser> sysUserPageInfo = sysUserService.getSysUserList(page, size);
        return ServerResponse.success(getTables(sysUserPageInfo));
    }


   //获取当前用户已分配的权限
   @GetMapping("/getAclsList")
   public ServerResponse getAclsList(Integer userId) {

       Map<String, Object> map = Maps.newHashMap();
       map.put("acls", sysTreeService.userAclTree(userId));
       map.put("roles", sysRoleService.getRoleListByUserId(userId));
       return ServerResponse.success(map);
   }

}
