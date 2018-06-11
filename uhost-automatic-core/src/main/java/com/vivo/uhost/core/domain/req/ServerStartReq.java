package com.vivo.uhost.core.domain.req;

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
public class ServerStartReq {

    private String currentAddress;  // 服务节点的当前地址

    private Integer tcpPort;        // 端口号

    public ServerStartReq() {
    }

    public ServerStartReq(String currentAddress, Integer tcpPort) {
        this.currentAddress = currentAddress;
        this.tcpPort = tcpPort;
    }

    public String getCurrentAddress() {
        return currentAddress;
    }

    public void setCurrentAddress(String currentAddress) {
        this.currentAddress = currentAddress;
    }

    public Integer getTcpPort() {
        return tcpPort;
    }

    public void setTcpPort(Integer tcpPort) {
        this.tcpPort = tcpPort;
    }

    @Override
    public String toString() {
        return "ServerStartReq{" +
                "currentAddress='" + currentAddress + '\'' +
                ", tcpPort=" + tcpPort +
                '}';
    }
}
