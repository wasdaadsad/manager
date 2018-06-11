package com.vivo.uhost.dal.entity;

/**
 * @author LuZhiWei
 * @version 1.0
 * @describe
 * @date 2018/1/3
 */
public class ModelInfo {
    private Integer id;

    private String modelVersion;

    private String modelName;

    private String createTime;

    private Integer state;

    @Override
    public String toString() {
        return "ModelInfo{" +
                "id=" + id +
                ", modelVersion='" + modelVersion + '\'' +
                ", modelName='" + modelName + '\'' +
                ", createTime=" + createTime +
                ", state=" + state +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getModelVersion() {
        return modelVersion;
    }

    public void setModelVersion(String modelVersion) {
        this.modelVersion = modelVersion;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
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
