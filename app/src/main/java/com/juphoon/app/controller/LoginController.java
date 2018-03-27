package com.juphoon.app.controller;


import com.juphoon.app.common.JedisUtils;
import com.juphoon.app.common.JsonUtils;
import com.juphoon.app.common.ServerResponse;
import com.juphoon.app.entity.User;
import com.juphoon.app.entity.vo.UserVo;
import com.juphoon.app.service.impl.LoginServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * Created by ztf on 2018/2/1
 */
@RestController
public class LoginController extends BaseController {
    @Autowired
    private LoginServiceImpl loginService;
    @Autowired
    private JedisUtils jedisUtils;

//
//    @RequestMapping(value = "/login", method = RequestMethod.POST)
//    public ServerResponse login(String username, String password) throws Exception {
//
//        if (username == null || "".equals(username)) {
//            return ServerResponse.error("用户名不能为空");
//        }
//        if (password == null || "".equals(password)) {
//            return ServerResponse.error("密码不能为空");
//        }
//
//        User loginUser = loginService.validate(username, password);//登录成功过后得到用户信息
//        if(loginUser.getUserStatus() == 0) {
//            return ServerResponse.error("账号被禁用");
//        }
//
//        //接口返回用户信息和uuid
//        String uuid = UUID.randomUUID().toString();    //获取UUID并转化为String对象
//        uuid = uuid.replace("-", "");
//
//        UserVo userResponse = new UserVo();
//        BeanUtils.copyProperties(loginUser, userResponse);
//        userResponse.setPassword(null);
//        userResponse.setUuid(uuid);
//
//        jedisUtils.setToken(uuid, JsonUtils.objectToJson(userResponse));//保存用户信息到Redis
//        return ServerResponse.success(userResponse);
//    }
//
//    @RequestMapping(value = "/logout", method = RequestMethod.POST)
//    @ResponseBody
//    public Object loginout(HttpServletRequest request) {
//        String uuid = request.getHeader("token");
//        if (uuid == null) {
//            return ServerResponse.error("uuid不能为空");
//        }
//        //清空key为uuid的缓存
//        jedisUtils.delJedis(uuid);
//        return ServerResponse.success();
//    }
}
