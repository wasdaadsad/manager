/**
 * @Copyright:Copyright © VIVO Communication Technology Co., Ltd. All rights reserved.
 * @Company:http://www.vivo.com.cn/
 */
package com.vivo.uhost.message.model;

import java.util.List;

/**
 * @author huangxiaoqun  2018/3/20 15:47
 * @version 1.0
 * @description uhost上报手机连接状态变更
 */
public class DeviceStateChangeReq {

    //uhost身份id
    private String token;

    //连接的设备
    private List<DeviceModel> connected;

    //没有连接的设备
    private List<DeviceModel> disConnected;


    public DeviceStateChangeReq() {
    }

    public DeviceStateChangeReq(String token, List<DeviceModel> connected, List<DeviceModel> disConnected) {
        this.token = token;
        this.connected = connected;
        this.disConnected = disConnected;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<DeviceModel> getConnected() {
        return connected;
    }

    public void setConnected(List<DeviceModel> connected) {
        this.connected = connected;
    }

    public List<DeviceModel> getDisConnected() {
        return disConnected;
    }

    public void setDisConnected(List<DeviceModel> disConnected) {
        this.disConnected = disConnected;
    }
}
