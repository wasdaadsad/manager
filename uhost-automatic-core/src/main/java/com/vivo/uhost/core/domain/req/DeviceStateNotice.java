package com.vivo.uhost.core.domain.req;

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

    private String token;

    private Integer state;

    private String address;

    public DeviceStateNotice() {
    }

    public DeviceStateNotice(String token, Integer state, String address) {
        this.token = token;
        this.state = state;
        this.address = address;
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
