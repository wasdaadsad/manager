package com.vivo.uhost.message.model;

/**
 * @Title:
 * @Description:
 * @Author:chengen
 * @Since:15-6-25
 * @Modified By:
 * @Modified Date:
 * @Why &amp; What is modified:
 * @Version:1.0
 */
public class LoginResp {

    private String host;

    private Integer port;

    public LoginResp() {
    }

    public LoginResp(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    @Override
    public String toString() {
        return "LoginResp{" +
                "host='" + host + '\'' +
                ", port=" + port +
                '}';
    }
}
