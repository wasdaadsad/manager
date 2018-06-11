package com.vivo.uhost.dal.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

public class ServerNode {

    private Integer id;

    //ip地址
    private String serverAddress;

    //tcp服务端口
    private Integer tcpPort;

    //创建时间
    @JsonIgnore
    private Date createTime;

    //更新时间
    @JsonIgnore
    private Date updateTime;

    //状态
    private Integer state = 1;

    //当前uhost在线连接量
    private Integer connectCount = 0;

    public ServerNode() {
    }

    public ServerNode(String serverAddress, Integer tcpPort, Integer connectCount) {
        this.serverAddress = serverAddress;
        this.tcpPort = tcpPort;
        this.connectCount = connectCount;
    }

    public ServerNode(Integer id, String serverAddress, Integer tcpPort, Date createTime, Date updateTime, Integer state, Integer connectCount) {
        this.id = id;
        this.serverAddress = serverAddress;
        this.tcpPort = tcpPort;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.state = state;
        this.connectCount = connectCount;
    }

    public Integer getTcpPort() {
        return tcpPort;
    }

    public void setTcpPort(Integer tcpPort) {
        this.tcpPort = tcpPort;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getServerAddress() {
        return serverAddress;
    }

    public void setServerAddress(String serverAddress) {
        this.serverAddress = serverAddress;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }


    public int getConnectCount() {
        return connectCount;
    }

    public void setConnectCount(int connectCount) {
        this.connectCount = connectCount;
    }


    @Override
    public String toString() {
        return "ServerNode{" +
                "id=" + id +
                ", serverAddress='" + serverAddress + '\'' +
                ", tcpPort=" + tcpPort +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", state=" + state +
                ", connectCount=" + connectCount +
                '}';
    }
}