package com.juphoon.app.service;

import com.github.pagehelper.PageInfo;
import com.github.stuxuhai.jpinyin.PinyinException;
import com.juphoon.app.entity.App;
import com.juphoon.app.entity.AppVersion;
import com.juphoon.app.entity.vo.LatestVersionVo;

import java.util.List;

public interface AppService {


    PageInfo<App> getAppList(Integer page, Integer size,String appName);

    void addApp(App app) throws PinyinException;

    void delApp(Integer id);

    void addVersion(AppVersion appVersion);

    LatestVersionVo getIOSAPP(String downloadPageUrlSuffix);

    PageInfo<App> getAppListByUserId(Integer page, Integer size, Integer userId,String appName);

    PageInfo<LatestVersionVo> getVersionList(Integer page, Integer size, Integer appId);

    AppVersion getVersionDetail(Integer id);

    void updateAppVersion(AppVersion appVersion);

   // LatestVersionVo getLatestAppVersion(Integer appId, Integer appType);

    List<LatestVersionVo> getAppVersions(String suffix, String versionCode);

    void delVersion(Integer id);

    void updateReportUrl(Integer id, String reportUrl);
}
