package com.juphoon.app.entity.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class LatestVersionVo {

    private Integer id;

    private String versionName;

    private String versionCode;

    private String updateInstructions;

    private String logUrl;

    private String downloadFileUrl;

    private Date createTime;

    private Date changeTime;

    private String appName;

    private Integer appType;

    private String appId;

    private String downloadPageUrl;

    private String downloadPageUrlSuffix;

    private Integer mandatoryUpdate;

    private String reportUrl;

    public LatestVersionVo() {
    }

    public LatestVersionVo(Integer id, String versionName, String versionCode, String updateInstructions, String logUrl, String downloadFileUrl, Date createTime, Date changeTime, String appName, Integer appType, String appId, String downloadPageUrl, String downloadPageUrlSuffix, Integer mandatoryUpdate, String reportUrl) {
        this.id = id;
        this.versionName = versionName;
        this.versionCode = versionCode;
        this.updateInstructions = updateInstructions;
        this.logUrl = logUrl;
        this.downloadFileUrl = downloadFileUrl;
        this.createTime = createTime;
        this.changeTime = changeTime;
        this.appName = appName;
        this.appType = appType;
        this.appId = appId;
        this.downloadPageUrl = downloadPageUrl;
        this.downloadPageUrlSuffix = downloadPageUrlSuffix;
        this.mandatoryUpdate = mandatoryUpdate;
        this.reportUrl = reportUrl;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode;
    }

    public String getUpdateInstructions() {
        return updateInstructions;
    }

    public void setUpdateInstructions(String updateInstructions) {
        this.updateInstructions = updateInstructions;
    }

    public String getLogUrl() {
        return logUrl;
    }

    public void setLogUrl(String logUrl) {
        this.logUrl = logUrl;
    }

    public String getDownloadFileUrl() {
        return downloadFileUrl;
    }

    public void setDownloadFileUrl(String downloadFileUrl) {
        this.downloadFileUrl = downloadFileUrl;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getChangeTime() {
        return changeTime;
    }

    public void setChangeTime(Date changeTime) {
        this.changeTime = changeTime;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public Integer getAppType() {
        return appType;
    }

    public void setAppType(Integer appType) {
        this.appType = appType;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getDownloadPageUrl() {
        return downloadPageUrl;
    }

    public void setDownloadPageUrl(String downloadPageUrl) {
        this.downloadPageUrl = downloadPageUrl;
    }

    public String getDownloadPageUrlSuffix() {
        return downloadPageUrlSuffix;
    }

    public void setDownloadPageUrlSuffix(String downloadPageUrlSuffix) {
        this.downloadPageUrlSuffix = downloadPageUrlSuffix;
    }

    public Integer getMandatoryUpdate() {
        return mandatoryUpdate;
    }

    public void setMandatoryUpdate(Integer mandatoryUpdate) {
        this.mandatoryUpdate = mandatoryUpdate;
    }

    public String getReportUrl() {
        return reportUrl;
    }

    public void setReportUrl(String reportUrl) {
        this.reportUrl = reportUrl;
    }

    @Override
    public String toString() {
        return "LatestVersionVo{" +
                "id=" + id +
                ", versionName='" + versionName + '\'' +
                ", versionCode='" + versionCode + '\'' +
                ", updateInstructions='" + updateInstructions + '\'' +
                ", logUrl='" + logUrl + '\'' +
                ", downloadFileUrl='" + downloadFileUrl + '\'' +
                ", createTime=" + createTime +
                ", changeTime=" + changeTime +
                ", appName='" + appName + '\'' +
                ", appType=" + appType +
                ", appId='" + appId + '\'' +
                ", downloadPageUrl='" + downloadPageUrl + '\'' +
                ", downloadPageUrlSuffix='" + downloadPageUrlSuffix + '\'' +
                ", mandatoryUpdate=" + mandatoryUpdate +
                ", reportUrl='" + reportUrl + '\'' +
                '}';
    }
}
