package com.vivo.uhost.message.model;

import java.util.List;

/**
 * @Title:
 * @Description:
 * @Author:chengen
 * @Since:15-6-25
 * @Modified By:
 * @Modified Date:
 * @Why &amp; What is modified:
 * @Version:1.0 serevr执行任务
 */
public class PushTaskReq {

    //推送任务id
    private Integer msgId;

    //推送类型
    private Integer pushType;

    //执行任务的机型
    private String productName;

    //执行测试任务的emmcid 设置指定编号的手机进行测试
    private List<String> emmcidList;

    private List<DeviceInfo> deviceInfoList;

    //推送内容
    private String content;

    //推送备注
    private String comment;


    public PushTaskReq() {
    }

    public PushTaskReq(Integer msgId, Integer pushType, String productName, List<String> emmcidList, List<DeviceInfo> deviceInfoList, String content, String comment) {
        this.msgId = msgId;
        this.pushType = pushType;
        this.productName = productName;
        this.emmcidList = emmcidList;
        this.deviceInfoList = deviceInfoList;
        this.content = content;
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "PushTaskReq{" +
                "msgId=" + msgId +
                ", pushType=" + pushType +
                ", productName='" + productName + '\'' +
                ", emmcidList=" + emmcidList +
                ", content='" + content + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<String> getEmmcidList() {
        return emmcidList;
    }

    public void setEmmcidList(List<String> emmcidList) {
        this.emmcidList = emmcidList;
    }

    public void setPushType(Integer pushType) {
        this.pushType = pushType;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setMsgId(Integer msgId) {
        this.msgId = msgId;
    }

    public Integer getMsgId() {
        return msgId;
    }

    public void setMsgId(int msgId) {
        this.msgId = msgId;
    }

    public Integer getPushType() {
        return pushType;
    }

    public void setPushType(int pushType) {
        this.pushType = pushType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<DeviceInfo> getDeviceInfoList() {
        return deviceInfoList;
    }

    public void setDeviceInfoList(List<DeviceInfo> deviceInfoList) {
        this.deviceInfoList = deviceInfoList;
    }
}
