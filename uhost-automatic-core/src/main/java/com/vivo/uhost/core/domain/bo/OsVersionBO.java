package com.vivo.uhost.core.domain.bo;

import com.vivo.uhost.dal.entity.OsVersion;

/**
 * @author LuZhiWei
 * @version 1.0
 * @describe
 * @date 2018/2/8
 */
public class OsVersionBO {
    private Integer id;

    private Integer osType;

    private Integer modelId;

    private String modelName;

    private String versionCode;

    private String packageUrl;

    private String createTime;

    private Integer state;

    public OsVersionBO() {
    }

    public OsVersionBO(OsVersion osVersion) {
        this.id = osVersion.getId();
        this.osType = osVersion.getOsType();
        this.modelId = osVersion.getModelId();
        this.versionCode = osVersion.getVersionCode();
        this.packageUrl = osVersion.getPackageUrl();
        this.createTime = osVersion.getCreateTime();
        this.state = osVersion.getState();
    }

    public OsVersion toOsVersion(){
        OsVersion osVersion = new OsVersion();
        osVersion.setId(this.id);
        osVersion.setOsType(this.osType);
        osVersion.setModelId(this.modelId);
        osVersion.setVersionCode(this.versionCode);
        osVersion.setPackageUrl(this.packageUrl);
        osVersion.setCreateTime(this.createTime);
        osVersion.setState(this.state);
        return osVersion;
    }

    @Override
    public String toString() {
        return "OsVersionBO{" +
                "id=" + id +
                ", osType=" + osType +
                ", modelId=" + modelId +
                ", versionCode='" + versionCode + '\'' +
                ", packageUrl='" + packageUrl + '\'' +
                ", createTime='" + createTime + '\'' +
                ", state=" + state +
                '}';
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOsType() {
        return osType;
    }

    public void setOsType(Integer osType) {
        this.osType = osType;
    }

    public Integer getModelId() {
        return modelId;
    }

    public void setModelId(Integer modelId) {
        this.modelId = modelId;
    }

    public String getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode;
    }

    public String getPackageUrl() {
        return packageUrl;
    }

    public void setPackageUrl(String packageUrl) {
        this.packageUrl = packageUrl;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
