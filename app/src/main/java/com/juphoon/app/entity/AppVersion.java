package com.juphoon.app.entity;

import java.io.Serializable;
import java.util.Date;

public class AppVersion implements Serializable {
    private Integer id;

    private String versionName;

    private String versionCode;

    private String updateInstructions;

    private String fileSize;

    private String logUrl;

    private String downloadFileUrl;

    private Integer appId;

    private Date createTime;

    private Date changeTime;

    private Integer status;

    private Integer mandatoryUpdate;

    private String reportUrl;

    private static final long serialVersionUID = 1L;

    public AppVersion(Integer id, String versionName, String versionCode, String updateInstructions, String fileSize, String logUrl, String downloadFileUrl, Integer appId, Date createTime, Date changeTime, Integer status, Integer mandatoryUpdate, String reportUrl) {
        this.id = id;
        this.versionName = versionName;
        this.versionCode = versionCode;
        this.updateInstructions = updateInstructions;
        this.fileSize = fileSize;
        this.logUrl = logUrl;
        this.downloadFileUrl = downloadFileUrl;
        this.appId = appId;
        this.createTime = createTime;
        this.changeTime = changeTime;
        this.status = status;
        this.mandatoryUpdate = mandatoryUpdate;
        this.reportUrl = reportUrl;
    }

    public AppVersion() {
        super();
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
        this.versionName = versionName == null ? null : versionName.trim();
    }

    public String getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode == null ? null : versionCode.trim();
    }

    public String getUpdateInstructions() {
        return updateInstructions;
    }

    public void setUpdateInstructions(String updateInstructions) {
        this.updateInstructions = updateInstructions == null ? null : updateInstructions.trim();
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize == null ? null : fileSize.trim();
    }

    public String getLogUrl() {
        return logUrl;
    }

    public void setLogUrl(String logUrl) {
        this.logUrl = logUrl == null ? null : logUrl.trim();
    }

    public String getDownloadFileUrl() {
        return downloadFileUrl;
    }

    public void setDownloadFileUrl(String downloadFileUrl) {
        this.downloadFileUrl = downloadFileUrl == null ? null : downloadFileUrl.trim();
    }

    public Integer getAppId() {
        return appId;
    }

    public void setAppId(Integer appId) {
        this.appId = appId;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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
        this.reportUrl = reportUrl == null ? null : reportUrl.trim();
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
        AppVersion other = (AppVersion) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getVersionName() == null ? other.getVersionName() == null : this.getVersionName().equals(other.getVersionName()))
                && (this.getVersionCode() == null ? other.getVersionCode() == null : this.getVersionCode().equals(other.getVersionCode()))
                && (this.getUpdateInstructions() == null ? other.getUpdateInstructions() == null : this.getUpdateInstructions().equals(other.getUpdateInstructions()))
                && (this.getFileSize() == null ? other.getFileSize() == null : this.getFileSize().equals(other.getFileSize()))
                && (this.getLogUrl() == null ? other.getLogUrl() == null : this.getLogUrl().equals(other.getLogUrl()))
                && (this.getDownloadFileUrl() == null ? other.getDownloadFileUrl() == null : this.getDownloadFileUrl().equals(other.getDownloadFileUrl()))
                && (this.getAppId() == null ? other.getAppId() == null : this.getAppId().equals(other.getAppId()))
                && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
                && (this.getChangeTime() == null ? other.getChangeTime() == null : this.getChangeTime().equals(other.getChangeTime()))
                && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
                && (this.getMandatoryUpdate() == null ? other.getMandatoryUpdate() == null : this.getMandatoryUpdate().equals(other.getMandatoryUpdate()))
                && (this.getReportUrl() == null ? other.getReportUrl() == null : this.getReportUrl().equals(other.getReportUrl()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getVersionName() == null) ? 0 : getVersionName().hashCode());
        result = prime * result + ((getVersionCode() == null) ? 0 : getVersionCode().hashCode());
        result = prime * result + ((getUpdateInstructions() == null) ? 0 : getUpdateInstructions().hashCode());
        result = prime * result + ((getFileSize() == null) ? 0 : getFileSize().hashCode());
        result = prime * result + ((getLogUrl() == null) ? 0 : getLogUrl().hashCode());
        result = prime * result + ((getDownloadFileUrl() == null) ? 0 : getDownloadFileUrl().hashCode());
        result = prime * result + ((getAppId() == null) ? 0 : getAppId().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getChangeTime() == null) ? 0 : getChangeTime().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getMandatoryUpdate() == null) ? 0 : getMandatoryUpdate().hashCode());
        result = prime * result + ((getReportUrl() == null) ? 0 : getReportUrl().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", versionName=").append(versionName);
        sb.append(", versionCode=").append(versionCode);
        sb.append(", updateInstructions=").append(updateInstructions);
        sb.append(", fileSize=").append(fileSize);
        sb.append(", logUrl=").append(logUrl);
        sb.append(", downloadFileUrl=").append(downloadFileUrl);
        sb.append(", appId=").append(appId);
        sb.append(", createTime=").append(createTime);
        sb.append(", changeTime=").append(changeTime);
        sb.append(", status=").append(status);
        sb.append(", mandatoryUpdate=").append(mandatoryUpdate);
        sb.append(", reportUrl=").append(reportUrl);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}