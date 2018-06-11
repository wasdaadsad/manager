package com.vivo.uhost.message.model;

import org.apache.velocity.runtime.directive.MacroParseException;

import java.util.Map;

/**
 * @author LuZhiWei
 * @version 1.0
 * @describe
 * @date 2018/4/9
 */
public class DevicePortNameChangeReq {
    private String token;
    private Map<String,String> map;

    public DevicePortNameChangeReq() {
    }

    public DevicePortNameChangeReq(String token, Map<String, String> map) {
        this.token = token;
        this.map = map;
    }

    @Override
    public String toString() {
        return "DevicePortNameChangeReq{" +
                "token='" + token + '\'' +
                ", map=" + map +
                '}';
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }
}
