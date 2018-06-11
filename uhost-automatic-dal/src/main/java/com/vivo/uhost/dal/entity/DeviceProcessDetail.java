package com.vivo.uhost.dal.entity;

import java.util.Date;

/**
 * @author litingfa  2018/3/21 15:22
 * @version 1.0
 * @description
 */
public class DeviceProcessDetail {
    private Long id;//id
    private Long deviceId;//设备id
    private Long processId;//流程id
    private Long detailId;//流程明细
    private int status;//状态
    private Date createTime;
    private Date endTime;


    public Long getProcessId() {
        return processId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public void setProcessId(Long processId) {
        this.processId = processId;
    }

    public Long getDetailId() {
        return detailId;
    }

    public void setDetailId(Long detailId) {
        this.detailId = detailId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
