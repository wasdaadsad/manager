package com.vivo.uhost.dal.entity;

/**
 * @author LuZhiWei
 * @version 1.0
 * @describe
 * @date 2018/1/11
 */
public class SpotRecord {
    private Integer id;

    private String testNum;

    private String recordIp;

    private String modelVersion;

    private String softVersion;

    private String updateTime;

    private String recordUrl;

    private String recordComment;

    @Override
    public String toString() {
        return "SpotRecord{" +
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
