package com.juphoon.app.controller;


import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.juphoon.app.common.JedisUtils;
import com.juphoon.app.common.JsonUtils;
import com.juphoon.app.common.ServerResponse;
import com.juphoon.app.entity.SysUser;
import com.juphoon.app.entity.User;
import com.juphoon.app.entity.vo.SysUserVo;
import com.juphoon.app.entity.vo.UserVo;
import com.juphoon.app.service.SysTreeService;
import com.juphoon.app.service.SysUserService;
import com.juphoon.app.service.impl.LoginServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.UUID;

/**
 * Created by ztf on 2018/2/3
 */
@RestController
public class UserController extends BaseController {

    @Autowired
    private SysUserService sysUserService;;

    @Autowired
    private JedisUtils jedisUtils;

    @Autowired
    private SysTreeService sysTreeService;
//登陆验证码认证
//    @Autowired
//    private DefaultKaptcha defaultKaptcha;

    //
//    @RequestMapping("/defaultKaptcha")
//    public void defaultKaptcha(HttpServletResponse httpServletResponse,String unique) throws Exception{
//        byte[] captchaChallengeAsJpeg = null;
//        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
//        try {
//            //生产验证码字符串并保存到session中
//            String createText = defaultKaptcha.createText();
//            System.out.println("createText"+createText);
//            jedisUtils.setToken(unique,createText);
//
//
//            //使用生产的验证码字符串返回一个BufferedImage对象并转为byte写入到byte数组中
//            BufferedImage challenge = defaultKaptcha.createImage(createText);
//            ImageIO.write(challenge, "jpg", jpegOutputStream);
//        } catch (IllegalArgumentException e) {
//            httpServletResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
//            return;
//        }
//        //定义response输出类型为image/jpeg类型，使用response输出流输出图片的byte数组
//        captchaChallengeAsJpeg = jpegOutputStream.toByteArray();
//        httpServletResponse.setHeader("Cache-Control", "no-store");
//        httpServletResponse.setHeader("Pragma", "no-cache");
//        httpServletResponse.setDateHeader("Expires", 0);
//        httpServletResponse.setContentType("image/jpeg");
//        ServletOutputStream responseOutputStream =
//                httpServletResponse.getOutputStream();
//        responseOutputStream.write(captchaChallengeAsJpeg);
//        responseOutputStream.flush();
//        responseOutputStream.close();
//    }
//
//

   //用户登录
//    @RequestMapping(value = "/login", method = RequestMethod.POST)
//    public ServerResponse login(String username, String password,String code,String unique) throws Exception {
//
//        if (username == null || "".equals(username)) {
//            return ServerResponse.error("用户名不能为空");
//        }
//        if (password == null || "".equals(password)) {
//            return ServerResponse.error("密码不能为空");
//        }
//        System.out.println(jedisUtils.getJedis(unique));
//        if((code.equals(jedisUtils.getJedis(unique)))==false) {
//            return ServerResponse.error("验证码不正确");
//        }
//        SysUser loginUser = sysUserService.validate(username, password);//登录成功过后得到用户信息
//        if(loginUser.getStatus() == 0) {
//            return ServerResponse.error("账号已删除");
//        }
//        else if(loginUser.getStatus()==2) {
//            return ServerResponse.error("账号已冻结");
//        }
//        //接口返回用户信息和uuid
//        String uuid = UUID.randomUUID().toString();    //获取UUID并转化为String对象
//        uuid = uuid.replace("-", "");
//        SysUserVo userResponse = new SysUserVo();
//        BeanUtils.copyProperties(loginUser, userResponse);
//        userResponse.setPassword(null);
//        userResponse.setUuid(uuid);
//        jedisUtils.setToken(uuid, JsonUtils.objectToJson(userResponse));//保存用户信息到Redis
//        userResponse.setAclModuleLevelDtoList(sysTreeService.userAclTree(loginUser.getId()));
//        return ServerResponse.success(userResponse);
//    }


    //用户登录
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ServerResponse login(String username, String password) throws Exception {

        if (username == null || "".equals(username)) {
            return ServerResponse.error("用户名不能为空");
        }
        if (password == null || "".equals(password)) {
            return ServerResponse.error("密码不能为空");
        }

        SysUser loginUser = sysUserService.validate(username, password);//登录成功过后得到用户信息
        if(loginUser.getStatus() == 0) {
            return ServerResponse.error("账号已删除");
        }
        else if(loginUser.getStatus()==2) {
            return ServerResponse.error("账号已冻结");
        }
        //接口返回用户信息和uuid
        String uuid = UUID.randomUUID().toString();    //获取UUID并转化为String对象
        uuid = uuid.replace("-", "");
        SysUserVo userResponse = new SysUserVo();
        BeanUtils.copyProperties(loginUser, userResponse);
        userResponse.setPassword(null);
        userResponse.setUuid(uuid);
        jedisUtils.setToken(uuid, JsonUtils.objectToJson(userResponse));//保存用户信息到Redis
        userResponse.setAclModuleLevelDtoList(sysTreeService.userAclTree(loginUser.getId()));
        return ServerResponse.success(userResponse);
    }

    //用户退出
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    @ResponseBody
    public Object loginout(HttpServletRequest request) {
        String uuid = request.getHeader("token");
        if (uuid == null) {
            return ServerResponse.error("uuid不能为空");
        }
        //清空key为uuid的缓存
        jedisUtils.delJedis(uuid);
        return ServerResponse.success();
    }
}
