package com.juphoon.app.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.stuxuhai.jpinyin.PinyinException;
import com.github.stuxuhai.jpinyin.PinyinFormat;
import com.github.stuxuhai.jpinyin.PinyinHelper;
import com.juphoon.app.entity.App;
import com.juphoon.app.entity.AppVersion;
import com.juphoon.app.entity.vo.LatestVersionVo;
import com.juphoon.app.exception.BusinessException;
import com.juphoon.app.mapper.AppMapper;
import com.juphoon.app.mapper.AppVersionMapper;
import com.juphoon.app.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.util.List;

@Service
public class AppServiceImpl implements AppService {

    @Autowired
    private AppMapper appMapper;

    @Autowired
    private AppVersionMapper appVersionMapper;


    @Value("${DownloadPageUrl}")
    private String DownloadPageUrl;

    @Override
    public PageInfo<App> getAppList(Integer page, Integer size, String appName) {
        PageHelper.startPage(page, size);
        List<App> appList = appMapper.getAppList(null, "%" + appName + "%");
        return new PageInfo<>(appList);
    }

    @Override
    public PageInfo<App> getAppListByUserId(Integer page, Integer size, Integer userId, String appName) {

        PageHelper.startPage(page, size);
        List<App> appList = appMapper.getAppList(userId, appName);
        return new PageInfo<>(appList);
    }

    @Override
    public PageInfo<LatestVersionVo> getVersionList(Integer page, Integer size, Integer appId) {
        PageHelper.startPage(page, size);
        List<LatestVersionVo> appList = appVersionMapper.getVersionList(appId);
        return new PageInfo<>(appList);
    }

    @Override
    public AppVersion getVersionDetail(Integer id) {
        return appVersionMapper.selectByPrimaryKey(id);
    }

    //更新版本信息
    @Override
    @Transactional
    public void updateAppVersion(AppVersion appVersion) {

        appVersionMapper.updateByPrimaryKeySelective(appVersion);
    }

    //获取versionCode为第一位的版本信息列表
    @Override
    public List<LatestVersionVo> getAppVersions(String suffix, String versionCode) {

        List<LatestVersionVo> versionVoList = appVersionMapper.getAppVersions(suffix);
        for (int i = 0; i < versionVoList.size(); i++) {
            LatestVersionVo version = versionVoList.get(i);
            if (version.getVersionCode() == versionCode || versionCode.equals(version.getVersionCode())) {
                versionVoList.remove(version);
                versionVoList.add(0, version);
                break;
            }
        }
        return versionVoList;
    }

    @Override
    @Transactional
    public void delVersion(Integer id) {
        appVersionMapper.delVersion(id);
    }

    @Override
    @Transactional
    public void updateReportUrl(Integer id, String reportUrl) {
        appVersionMapper.updateReportUrl(id,reportUrl);
    }


    //根据后缀获取最新的版本信息
    @Override
    public LatestVersionVo getIOSAPP(String downloadPageUrlSuffix) {

        return appVersionMapper.getIOSApp(downloadPageUrlSuffix);
    }


    @Override
    @Transactional
    public void addApp(App app) throws PinyinException {

        if (appMapper.selectByAppName(app.getAppName()) != null) {
            throw new BusinessException("应用名已存在");
        }

        if (app.getAppPassword() == null || "".equals(app.getAppPassword())) {
            app.setAppEnablePassword(0);
        } else {
            app.setAppEnablePassword(1);
        }
        String tranAppName = PinyinHelper.convertToPinyinString(app.getAppName(), "", PinyinFormat.WITHOUT_TONE);
        String downloadPageUrlSuffix;
        if (app.getAppType() == 0) {
            downloadPageUrlSuffix = tranAppName + "-android";
        } else if (app.getAppType() == 1) {
            downloadPageUrlSuffix = tranAppName + "-ios";
        } else {
            downloadPageUrlSuffix = tranAppName + "-window";
        }
        app.setDownloadPageUrlSuffix(downloadPageUrlSuffix);
        app.setDownloadPageUrl(DownloadPageUrl + downloadPageUrlSuffix);
        app.setCreateTime(new Date());
        appMapper.insertSelective(app);
    }

    @Override
    @Transactional
    public void addVersion(AppVersion appVersion) {

        App app = appMapper.selectByPrimaryKey(appVersion.getAppId());
        if (app.getAppType() == 1) {
            String downloadFileUrl = appVersion.getDownloadFileUrl();
            appVersion.setDownloadFileUrl(downloadFileUrl.replace("http://rcs.wo.cn:8083", "https://rcs.wo.cn"));
            appVersion.setLogUrl(appVersion.getLogUrl().replace("http://rcs.wo.cn:8083", "https://rcs.wo.cn"));
        }
        appVersion.setFileSize(convertFileSize(Long.parseLong(appVersion.getFileSize())));
        appVersion.setCreateTime(new Date());
        appVersion.setChangeTime(new Date());
        appVersion.setStatus(1);
        appVersionMapper.insertSelective(appVersion);

    }

    //删除应用及版本信息
    @Override
    @Transactional
    public void delApp(Integer id) {
        appMapper.deleteByPrimaryKey(id);
        appVersionMapper.deleteByAppId(id);
    }

    public static String convertFileSize(long size) {
        long kb = 1024;
        long mb = kb * 1024;
        long gb = mb * 1024;
        //%.2f 即是保留两位小数的浮点数，后面跟上对应单位就可以了
        if (size >= gb) {
            return String.format("%.2f GB", (float) size / gb);
        } else if (size >= mb) {
            float f = (float) size / mb;
            //如果大于100MB就不用保留小数位啦
            return String.format(f > 100 ? "%.0f MB" : "%.2f MB", f);
        } else if (size >= kb) {
            float f = (float) size / kb;
            //如果大于100kB就不用保留小数位了
            return String.format(f > 100 ? "%.0f KB" : "%.2f KB", f);
        } else
            return String.format("%d B", size);
    }
}

