package com.vivo.uhost.protocol.core.model.mq;

/**
 * @Title:
 * @Description:
 * @Author:chengen
 * @Since:15-7-2
 * @Modified By:
 * @Modified Date:
 * @Why &amp; What is modified:
 * @Version:1.0
 */
public class DeviceStateNotice {

    //token
    private String token;

    //Uhost身份标识
    private String uidentify;

    //Uhost在线状态
    private Integer state;

    //push server 地址
    private String address;

    public DeviceStateNotice() {
    }

    public DeviceStateNotice(String token, String uidentify, Integer state, String address) {
        this.token = token;
        this.uidentify = uidentify;
        this.state = state;
        this.address = address;
    }

    public String getUidentify() {
        return uidentify;
    }

    public void setUidentify(String uidentify) {
        this.uidentify = uidentify;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
