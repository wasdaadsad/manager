/*
*
* Created by IntelliJ IDEA.
  User: dongjiajin
  Date: 2018/1/10
  Time: 16:15
  To change this template use File | Settings | File Templates.
*
* */

package com.vivo.uhost.core.domain.bo;


import com.vivo.uhost.dal.entity.DeviceProcessDetail;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.format.datetime.DateFormatter;

import java.util.Date;
import java.util.Locale;

public class DeviceProcessDetailBO {

    protected static String PATTEN = "yyyy-MM-dd HH:mm:ss";

    private static Log log = LogFactory.getLog(DeviceProcessDetailBO.class);

    private Long id;//id
    private Long deviceId;//设备id
    private Long processId;//流程id
    private Long detailId;//流程明细
    private String detailVP;//流程名称
    private int status;//状态
    private String statusVP;//设备状态描述
    private Date createTime;
    private Date endTime;
    private String startTime;
    private String endTimeVP;
    private int processState;//流程状态
    private String processStateVP;//流程状态描述
    private String pcb;


    public DeviceProcessDetailBO() {
    }

    public DeviceProcessDetailBO(DeviceProcessDetail detail) {
        this.id = detail.getId();
        this.deviceId = detail.getDeviceId();
        this.processId = detail.getProcessId();
        this.detailId = detail.getDetailId();
        this.status = detail.getStatus();
        this.createTime = detail.getCreateTime();
        this.endTime = detail.getEndTime();
        if (detail.getCreateTime() != null) {
            this.startTime = new DateFormatter(PATTEN).print(detail.getCreateTime(), Locale.CHINA);
        }
        if (detail.getEndTime() != null) {
            this.endTimeVP = new DateFormatter(PATTEN).print(detail.getEndTime(),Locale.CHINA);
        }
        switch (detail.getStatus()) {
            case 0://-1
                statusVP = "未开始";
                break;
            case 1://1
                statusVP = "测试ok";
                break;
            case 2://0
                statusVP = "测试完成";
                break;
            case 3://2
                statusVP = "运行中";
                break;
            default:
                break;
        }

    }

    public String getProcessStateVP() {
        return processStateVP;
    }

    public void setProcessStateVP(String processStateVP) {
        this.processStateVP = processStateVP;
    }

    public String getDetailVP() {
        return detailVP;
    }

    public void setDetailVP(String detailVP) {
        this.detailVP = detailVP;
    }

    public String getStatusVP() {
        return statusVP;
    }

    public void setStatusVP(String statusVP) {
        this.statusVP = statusVP;
    }

    public String getPcb() {
        return pcb;
    }

    public void setPcb(String pcb) {
        this.pcb = pcb;
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

    public Long getProcessId() {
        return processId;
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

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTimeVP() {
        return endTimeVP;
    }

    public void setEndTimeVP(String endTimeVP) {
        this.endTimeVP = endTimeVP;
    }

    public int getProcessState() {
        return processState;
    }

    public void setProcessState(int processState) {
        this.processState = processState;
        switch (processState) {
            case 10://-1
                processStateVP = "未开始";
                break;
            case 11://1
                processStateVP = "运行中";
                break;
            case 13://0
                processStateVP = "已完成";
                break;
            case 12://2
                processStateVP = "已关闭";
                break;
            default:
                break;
        }
    }
}
