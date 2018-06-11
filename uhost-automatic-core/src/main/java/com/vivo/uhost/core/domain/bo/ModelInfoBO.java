package com.vivo.uhost.core.domain.bo;

import com.vivo.uhost.dal.entity.ModelInfo;

/**
 * @author LuZhiWei
 * @version 1.0
 * @describe
 * @date 2018/1/3
 */
public class ModelInfoBO {
    private Integer id;

    private String modelVersion;

    private String modelName;

    private String createTime;

    private Integer state;

    public Integer getId() {
        return id;
    }

    public ModelInfoBO() {
    }

    public ModelInfoBO(ModelInfo modelInfo) {
        this.id = modelInfo.getId();
        this.modelVersion = modelInfo.getModelVersion();
        this.modelName = modelInfo.getModelName();
        this.createTime = modelInfo.getCreateTime();
        this.state = modelInfo.getState();
    }

    @Override
    public String toString() {
        return "ModelInfoBO{" +
                "id=" + id +
                ", modelVersion='" + modelVersion + '\'' +
                ", modelName='" + modelName + '\'' +
                ", createTime=" + createTime +
                ", state=" + state +
                '}';
    }

    public ModelInfo toModelInfo() {
        ModelInfo modelInfo = new ModelInfo();
        modelInfo.setId(this.id);
        modelInfo.setModelName(this.modelName);
        modelInfo.setModelVersion(this.modelVersion);
        modelInfo.setCreateTime(this.createTime);
        modelInfo.setState(this.state);
        return modelInfo;
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
