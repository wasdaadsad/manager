package com.vivo.uhost.protocol.core.model.mq;

import com.vivo.uhost.message.model.DeviceInfo;

import java.io.Serializable;
import java.util.List;

/**
 * @Title:
 * @Description:
 * @Author:chengen
 * @Since:15-7-3
 * @Modified By:
 * @Modified Date:
 * @Why &amp; What is modified:
 * @Version:1.0
 */
public class PushNotice implements Serializable {

    private Integer msgId;  //推送任务id

    private Integer pushType;  //推送类型

    private String content;  //推送内容

    private String productName;  //推送机型

    private String comment;  //推送备注

    //执行测试任务的emmcid 设置指定编号的手机进行测试 如果为空则表示全部的手机
    private List<String> emmcidList;

    private List<DeviceInfo> deviceInfoList;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getMsgId() {
        return msgId;
    }

    public void setMsgId(Integer msgId) {
        this.msgId = msgId;
    }

    public Integer getPushType() {
        return pushType;
    }

    public void setPushType(Integer pushType) {
        this.pushType = pushType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getEmmcidList() {
        return emmcidList;
    }

    public void setEmmcidList(List<String> emmcidList) {
        this.emmcidList = emmcidList;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public List<DeviceInfo> getDeviceInfoList() {
        return deviceInfoList;
    }

    public void setDeviceInfoList(List<DeviceInfo> deviceInfoList) {
        this.deviceInfoList = deviceInfoList;
    }
}
