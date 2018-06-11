package com.vivo.uhost.dal.entity;

/**
 * @author LuZhiWei
 * @version 1.0
 * @describe
 * @date 2018/2/8
 */
public class OsVersion {
    private Integer id;

    private Integer osType;

    private Integer modelId;

    private String versionCode;

    private String packageUrl;

    private String createTime;

    private Integer state;

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

    @Override
    public String toString() {
        return "OsVersion{" +
                "id=" + id +
                ", osType=" + osType +
                ", modelId='" + modelId + '\'' +
                ", versionCode='" + versionCode + '\'' +
                ", packageUrl='" + packageUrl + '\'' +
                ", createTime='" + createTime + '\'' +
                ", state=" + state +
                '}';
    }
}

