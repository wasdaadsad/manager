package com.vivo.uhost.dal.entity.process;

import com.vivo.uhost.dal.entity.process.PushTask;

import java.util.Date;
import java.util.Map;

/**
 * @author litingfa  2018/3/30 21:06
 * @version 1.0
 * @description
 */
public class TestProcess {
    private Long id;

    private String processName;

    private String productName;

    /**
     * queryString:<br/>
     *	taskTypes=11&taskTypes=20&taskTypes=14&taskTypes=15<br/>
     *  result:<br/>
     *  11,20,14,15
     */
    private String taskTypes;

    private Integer modelId;

    private int totalNum;

    private int successNum;

    private Date createTime;

    private Date startTime;

    private Date updateTime;

    private Integer state;  //10 11 12 1

    private String modelVersion;

    /**
     * PushType --> PushTask
     */
    private Map<Long, PushTask> pushTasks;  //Long为了jsp页面处理

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    public String getTaskTypes() {
        return taskTypes;
    }

    public void setTaskTypes(String taskTypes) {
        this.taskTypes = taskTypes;
    }

    public Integer getModelId() {
        return modelId;
    }

    public void setModelId(Integer modelId) {
        this.modelId = modelId;
    }

    public int getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }

    public int getSuccessNum() {
        return successNum;
    }

    public void setSuccessNum(int successNum) {
        this.successNum = successNum;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getModelVersion() {
        return modelVersion;
    }

    public void setModelVersion(String modelVersion) {
        this.modelVersion = modelVersion;
    }

    public Map<Long, PushTask> getPushTasks() {
        return pushTasks;
    }

    public void setPushTasks(Map<Long, PushTask> pushTasks) {
        this.pushTasks = pushTasks;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
