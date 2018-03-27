package com.juphoon.app.entity;

import java.io.Serializable;
import java.util.Date;

public class App implements Serializable {
    private Integer id;

    private String appName;

    private Integer appType;

    private String appId;

    private String downloadPageUrl;

    private Date createTime;

    private Integer appEnablePassword;

    private String appPassword;

    private Integer userId;

    private String downloadPageUrlSuffix;

    private static final long serialVersionUID = 1L;

    public App(Integer id, String appName, Integer appType, String appId, String downloadPageUrl, Date createTime, Integer appEnablePassword, String appPassword, Integer userId, String downloadPageUrlSuffix) {
        this.id = id;
        this.appName = appName;
        this.appType = appType;
        this.appId = appId;
        this.downloadPageUrl = downloadPageUrl;
        this.createTime = createTime;
        this.appEnablePassword = appEnablePassword;
        this.appPassword = appPassword;
        this.userId = userId;
        this.downloadPageUrlSuffix = downloadPageUrlSuffix;
    }

    public App() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName == null ? null : appName.trim();
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
        this.appId = appId == null ? null : appId.trim();
    }

    public String getDownloadPageUrl() {
        return downloadPageUrl;
    }

    public void setDownloadPageUrl(String downloadPageUrl) {
        this.downloadPageUrl = downloadPageUrl == null ? null : downloadPageUrl.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getAppEnablePassword() {
        return appEnablePassword;
    }

    public void setAppEnablePassword(Integer appEnablePassword) {
        this.appEnablePassword = appEnablePassword;
    }

    public String getAppPassword() {
        return appPassword;
    }

    public void setAppPassword(String appPassword) {
        this.appPassword = appPassword == null ? null : appPassword.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getDownloadPageUrlSuffix() {
        return downloadPageUrlSuffix;
    }

    public void setDownloadPageUrlSuffix(String downloadPageUrlSuffix) {
        this.downloadPageUrlSuffix = downloadPageUrlSuffix == null ? null : downloadPageUrlSuffix.trim();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        App other = (App) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getAppName() == null ? other.getAppName() == null : this.getAppName().equals(other.getAppName()))
                && (this.getAppType() == null ? other.getAppType() == null : this.getAppType().equals(other.getAppType()))
                && (this.getAppId() == null ? other.getAppId() == null : this.getAppId().equals(other.getAppId()))
                && (this.getDownloadPageUrl() == null ? other.getDownloadPageUrl() == null : this.getDownloadPageUrl().equals(other.getDownloadPageUrl()))
                && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
                && (this.getAppEnablePassword() == null ? other.getAppEnablePassword() == null : this.getAppEnablePassword().equals(other.getAppEnablePassword()))
                && (this.getAppPassword() == null ? other.getAppPassword() == null : this.getAppPassword().equals(other.getAppPassword()))
                && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
                && (this.getDownloadPageUrlSuffix() == null ? other.getDownloadPageUrlSuffix() == null : this.getDownloadPageUrlSuffix().equals(other.getDownloadPageUrlSuffix()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getAppName() == null) ? 0 : getAppName().hashCode());
        result = prime * result + ((getAppType() == null) ? 0 : getAppType().hashCode());
        result = prime * result + ((getAppId() == null) ? 0 : getAppId().hashCode());
        result = prime * result + ((getDownloadPageUrl() == null) ? 0 : getDownloadPageUrl().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getAppEnablePassword() == null) ? 0 : getAppEnablePassword().hashCode());
        result = prime * result + ((getAppPassword() == null) ? 0 : getAppPassword().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getDownloadPageUrlSuffix() == null) ? 0 : getDownloadPageUrlSuffix().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", appName=").append(appName);
        sb.append(", appType=").append(appType);
        sb.append(", appId=").append(appId);
        sb.append(", downloadPageUrl=").append(downloadPageUrl);
        sb.append(", createTime=").append(createTime);
        sb.append(", appEnablePassword=").append(appEnablePassword);
        sb.append(", appPassword=").append(appPassword);
        sb.append(", userId=").append(userId);
        sb.append(", downloadPageUrlSuffix=").append(downloadPageUrlSuffix);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}