package com.vivo.uhost.core.mq;

import com.vivo.uhost.dal.entity.DeviceInfo;

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
    private String token;//推送的token 就是管道的key
    /** pushTask's Id*/
    private Integer msgId;  //推送任务id
    private Integer pushType;  //推送类型
    private String content;  //推送内容
    private String comment;  //推送备注
    private String productName;//机型
    //执行测试任务的emmcid 设置指定编号的手机进行测试 如果为空则表示全部的手机
    private List<String> emmcidList;
    private List<DeviceInfo> deviceInfoList;
    /**
     * Json格式
     */

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public List<String> getEmmcidList() {
        return emmcidList;
    }

    public void setEmmcidList(List<String> emmcidList) {
        this.emmcidList = emmcidList;
    }

    public List<DeviceInfo> getDeviceInfoList() {
        return deviceInfoList;
    }

    public void setDeviceInfoList(List<DeviceInfo> deviceInfoList) {
        this.deviceInfoList = deviceInfoList;
    }

    @Override
    public String toString() {
        return "PushNotice{" +
                "token='" + token + '\'' +
                ", msgId=" + msgId +
                ", pushType=" + pushType +
                ", content='" + content + '\'' +
                ", comment='" + comment + '\'' +
                ", productName='" + productName + '\'' +
                ", emmcidList=" + emmcidList +
                '}';
    }
}
