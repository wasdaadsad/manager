/*
*
* Created by IntelliJ IDEA.
  User: dongjiajin
  Date: 2018/1/9
  Time: 16:15
  To change this template use File | Settings | File Templates.
  */
package com.vivo.uhost.dal.entity;

import java.util.Date;

public class FlowList {
    private long id;
    private String flowName;//流程名
    private String model ;//机型
    private String upgrade;//升级
    private String test;//测试
    private String factoryUpgrade ;//出厂升级
    private String factoryReset;//恢复出厂设置
    private Date startTime;//开始时间
    private Date updateTime;//完成时间
    private String sums;//总数
    private String succeed;//成功数
    private String status;//状态
    private String remark;//描述
    private String processType;
    private Integer taskId;//流程明细ID
    private int offset;
    private int pageSize;

    public FlowList() {
    }

    @Override
    public String toString() {
        return "FlowList{" +
                "id=" + id +
                ", flowName='" + flowName + '\'' +
                ", model='" + model + '\'' +
                ", upgrade='" + upgrade + '\'' +
                ", test='" + test + '\'' +
                ", factoryUpgrade='" + factoryUpgrade + '\'' +
                ", factoryReset='" + factoryReset + '\'' +
                ", startTime=" + startTime +
                ", updateTime=" + updateTime +
                ", sums='" + sums + '\'' +
                ", succeed='" + succeed + '\'' +
                ", status='" + status + '\'' +
                ", offset=" + offset +
                ", pageSize=" + pageSize +
                '}';
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public void setProcessType(String processType) {
        this.processType = processType;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getProcessType() {
        return processType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFlowName() {
        return flowName;
    }

    public void setFlowName(String flowName) {
        this.flowName = flowName;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getUpgrade() {
        return upgrade;
    }

    public void setUpgrade(String upgrade) {
        this.upgrade = upgrade;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    public String getFactoryUpgrade() {
        return factoryUpgrade;
    }

    public void setFactoryUpgrade(String factoryUpgrade) {
        this.factoryUpgrade = factoryUpgrade;
    }

    public String getFactoryReset() {
        return factoryReset;
    }

    public void setFactoryReset(String factoryReset) {
        this.factoryReset = factoryReset;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public String getSums() {
        return sums;
    }

    public void setSums(String sums) {
        this.sums = sums;
    }

    public String getSucceed() {
        return succeed;
    }

    public void setSucceed(String succeed) {
        this.succeed = succeed;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
