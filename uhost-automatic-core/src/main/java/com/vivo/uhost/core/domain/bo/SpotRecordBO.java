package com.vivo.uhost.core.domain.bo;

import com.vivo.uhost.dal.entity.SpotRecord;

/**
 * @author LuZhiWei
 * @version 1.0
 * @describe
 * @date 2018/1/11
 */
public class SpotRecordBO {
    private Integer id;

    private String testNum;

    private String recordIp;

    private String modelVersion;

    private String softVersion;

    private String updateTime;

    private String recordUrl;

    private String recordComment;

    public SpotRecordBO() {
    }

    public SpotRecordBO(SpotRecord spotRecord) {
        this.id = spotRecord.getId();
        this.testNum = spotRecord.getTestNum();
        this.recordIp = spotRecord.getRecordIp();
        this.modelVersion = spotRecord.getModelVersion();
        this.softVersion = spotRecord.getSoftVersion();
        this.updateTime = spotRecord.getUpdateTime();
        this.recordUrl = spotRecord.getRecordUrl();
        this.recordComment = spotRecord.getRecordComment();
    }

    public SpotRecord toSpotRecord() {
        SpotRecord spotRecord = new SpotRecord();
        spotRecord.setId(this.id);
        spotRecord.setTestNum(this.testNum);
        spotRecord.setRecordIp(this.recordIp);
        spotRecord.setModelVersion(this.modelVersion);
        spotRecord.setSoftVersion(this.softVersion);
        spotRecord.setUpdateTime(this.updateTime);
        spotRecord.setRecordUrl(this.recordUrl);
        spotRecord.setRecordComment(this.recordComment);
        return spotRecord;
    }

    @Override
    public String toString() {
        return "SpotRecordBO{" +
                "id=" + id +
                ", testNum='" + testNum + '\'' +
                ", recordIp='" + recordIp + '\'' +
                ", modelVersion='" + modelVersion + '\'' +
                ", softVersion='" + softVersion + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", recordUrl='" + recordUrl + '\'' +
                ", recordComment='" + recordComment + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTestNum() {
        return testNum;
    }

    public void setTestNum(String testNum) {
        this.testNum = testNum;
    }

    public String getRecordIp() {
        return recordIp;
    }

    public void setRecordIp(String recordIp) {
        this.recordIp = recordIp;
    }

    public String getModelVersion() {
        return modelVersion;
    }

    public void setModelVersion(String modelVersion) {
        this.modelVersion = modelVersion;
    }

    public String getSoftVersion() {
        return softVersion;
    }

    public void setSoftVersion(String softVersion) {
        this.softVersion = softVersion;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getRecordUrl() {
        return recordUrl;
    }

    public void setRecordUrl(String recordUrl) {
        this.recordUrl = recordUrl;
    }

    public String getRecordComment() {
        return recordComment;
    }

    public void setRecordComment(String recordComment) {
        this.recordComment = recordComment;
    }
}
