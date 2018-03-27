package com.juphoon.app.controller;


import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.github.stuxuhai.jpinyin.PinyinException;
import com.juphoon.app.common.JedisUtils;
import com.juphoon.app.common.ServerResponse;
import com.juphoon.app.entity.App;
import com.juphoon.app.entity.AppVersion;
import com.juphoon.app.entity.vo.LatestVersionVo;
import com.juphoon.app.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ztf on 2018/2/5
 */
@RestController
@RequestMapping
public class AppController {

    @Autowired
    private AppService appService;

    @Autowired
    private JedisUtils jedisUtils;

    //创建应用
    @RequestMapping(value = "/addApp", method = RequestMethod.POST)
    public ServerResponse addApp(HttpServletRequest request, App app) throws PinyinException {
        String uuid = request.getHeader("token");
        if (uuid == null) {
            return ServerResponse.error("uuid不能为空");
        }
        JSONObject jsonObject = JSONObject.parseObject(jedisUtils.getJedis(uuid));
        app.setUserId(jsonObject.getInteger("id"));
        appService.addApp(app);

        return ServerResponse.success();
    }

    //添加应用版本
    @RequestMapping(value = "/addVersion", method = RequestMethod.POST)
    public ServerResponse addVersion(AppVersion appVersion) {

        appService.addVersion(appVersion);

        return ServerResponse.success();
    }

    //删除应用版本

    @RequestMapping(value = "/delVersion", method = RequestMethod.POST)
    public ServerResponse delVersion(Integer id) {

        appService.delVersion(id);

        return ServerResponse.success();
    }


    //删除应用
    @RequestMapping(value = "/delApp", method = RequestMethod.POST)
    public ServerResponse delApp(Integer id) {

        appService.delApp(id);
        return ServerResponse.success();
    }


    //获取应用列表
    @RequestMapping(value = "/getAppList", method = RequestMethod.GET)
    public ServerResponse getAppList(HttpServletRequest request, Integer page, Integer size, String appName) {

        PageInfo<App> appPageInfo = null;
        String uuid = request.getHeader("token");
        if (uuid == null) {
            return ServerResponse.error("uuid不能为空");
        }
        JSONObject jsonObject = JSONObject.parseObject(jedisUtils.getJedis(uuid));
        if (jsonObject.getInteger("roleId") == 0) {
            appPageInfo = appService.getAppList(page, size, appName);
        } else {
            Integer userId = jsonObject.getInteger("id");
            appPageInfo = appService.getAppListByUserId(page, size, userId, appName);
        }
        Map<String, Object> map = new HashMap();
        map.put("total", appPageInfo.getTotal());
        map.put("list", appPageInfo.getList());
        return ServerResponse.success(map);
    }


    //获取应用版本列表
    @RequestMapping(value = "/getVersionList", method = RequestMethod.GET)
    public ServerResponse getVersionList(Integer page, Integer size, Integer appId) {

        PageInfo<LatestVersionVo> appPageInfo = appService.getVersionList(page, size, appId);
        Map<String, Object> map = new HashMap();
        map.put("total", appPageInfo.getTotal());
        map.put("list", appPageInfo.getList());
        return ServerResponse.success(map);
    }

    //获取版本详情
    @RequestMapping(value = "/getVersionDetail", method = RequestMethod.GET)
    public ServerResponse getVersionDetail(Integer id) {

        AppVersion appVersion = appService.getVersionDetail(id);

        return ServerResponse.success(appVersion);
    }

    //保存修改的版本详情
    @RequestMapping(value = "/updateAppVersion", method = RequestMethod.POST)
    public ServerResponse updateAppVersion(AppVersion appVersion) {

        appService.updateAppVersion(appVersion);

        return ServerResponse.success();
    }

    //更新版本报告url
    @RequestMapping(value = "/updateReportUrl", method = RequestMethod.POST)
    public ServerResponse updateReportUrl(Integer id, String reportUrl) {

        appService.updateReportUrl(id, reportUrl);

        return ServerResponse.success();
    }

    //获取应用列表
    @RequestMapping(value = "/getApps", method = RequestMethod.GET)
    public ServerResponse getAppList(String suffix, String versionCode) {

        List<LatestVersionVo> appList = appService.getAppVersions(suffix, versionCode);
        return ServerResponse.success(appList);
    }


    @RequestMapping("/{suffix}/mainflist")
    public void mainflist(HttpServletResponse response, @PathVariable String suffix) throws IOException {
        response.setCharacterEncoding("UTF-8");
        LatestVersionVo installPackage = appService.getIOSAPP(suffix);
        if (installPackage != null) {
            response.getWriter().write(getPlist(installPackage));
        }
    }

    private String getPlist(LatestVersionVo installPackage) {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<!DOCTYPE plist PUBLIC \"-//Apple//DTD PLIST 1.0//EN\" \"http://www.apple.com/DTDs/PropertyList-1.0.dtd\">\n" +
                "<plist version=\"1.0\">\n" +
                "<dict>\n" +
                "        <key>items</key>\n" +
                "        <array>\n" +
                "                <dict>\n" +
                "                        <key>assets</key>\n" +
                "                        <array>\n" +
                "                                <dict>\n" +
                "                                        <key>kind</key>\n" +
                "                                        <string>software-package</string>\n" +
                "                                        <key>url</key>\n" +
                "                                        <string>" + installPackage.getDownloadFileUrl() + "</string>\n" +
                "                                </dict>\n" +
                "                                <dict>\n" +
                "                                        <key>kind</key>\n" +
                "                                        <string>display-image</string>\n" +
                "                                        <key>url</key>\n" +
                "                                        <string>" + installPackage.getLogUrl() + "</string>\n" +
                "                                </dict>\n" +
                "                                <dict>\n" +
                "                                        <key>kind</key>\n" +
                "                                        <string>full-size-image</string>\n" +
                "                                        <key>url</key>\n" +
                "                                        <string>" + installPackage.getLogUrl() + "</string>\n" + "</dict>\n" +
                "                        </array>\n" +
                "                        <key>metadata</key>\n" +
                "                        <dict>\n" +
                "                                <key>bundle-identifier</key>\n" +
                "                                <string>" + installPackage.getAppId() + "</string>\n" +
                "                                <key>bundle-version</key>\n" +
                "                                <string>" + installPackage.getVersionName() + "</string>\n" +
                "                                <key>kind</key>\n" +
                "                                <string>software</string>\n" +
                "                                <key>title</key>\n" +
                "                                <string>" + installPackage.getAppName() + "</string>\n" +
                "                        </dict>\n" +
                "                </dict>\n" +
                "        </array>\n" +
                "</dict>\n" +
                "</plist>";
    }

}
