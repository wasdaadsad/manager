package com.vivo.uhost.dal.entity;

import java.util.Date;

/**
 * Created by Administrator on 2018/1/3.
 */
public class DeviceInfo {
    private Long id;//唯一id
    private String factory;//厂区
    private String workShop;//车间
    private String agingRoom;//老化房
    private String agingRack;//老化架
    private String token;
    private String uhost;
    private Long uhostId;//uhostId;
    private String model;//机型
    private String pcb;//pcb号
    private String emmcId;//emmcId
    private String systemVersion;//系统版本
    private Integer state;//在线状态
    private String imei;
    private String portName;
    private String serial;
    private int offset;
    private int pageSize;

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getPortName() {
        return portName;
    }

    public void setPortName(String portName) {
        this.portName = portName;
    }

    public Long getUhostId() {
        return uhostId;
    }

    public void setUhostId(Long uhostId) {
        this.uhostId = uhostId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    private String ip;

    public Integer getNodeId() {
        return nodeId;
    }

    public void setNodeId(Integer nodeId) {
        this.nodeId = nodeId;
    }

    private Integer nodeId;




    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    private Date lastLoginTime;//最近一次上线时间

    public Date getRegTime() {
        return regTime;
    }

    public void setRegTime(Date regTime) {
        this.regTime = regTime;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    @Override
    public String toString() {
        return "Device{" +
                "id=" + id +
                ", factory='" + factory + '\'' +
                ", workShop='" + workShop + '\'' +
                ", agingRoom='" + agingRoom + '\'' +
                ", agingRack='" + agingRack + '\'' +
                ", token='" + token + '\'' +
                ", uhost='" + uhost + '\'' +
                ", model='" + model + '\'' +
                ", pcb='" + pcb + '\'' +
                ", emmcId='" + emmcId + '\'' +
                ", serial='" + serial + '\'' +
                ", systemVersion='" + systemVersion + '\'' +
                ", state=" + state +
                ", ip='" + ip + '\'' +
                ", nodeId=" + nodeId +
                ", offset=" + offset +
                ", pageSize=" + pageSize +
                ", lastLoginTime=" + lastLoginTime +
                ", regTime=" + regTime +
                '}';
    }

    private Date regTime;//注册时间

    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory;
    }

    public String getWorkShop() {
        return workShop;
    }

    public void setWorkShop(String workShop) {
        this.workShop = workShop;
    }

    public String getAgingRoom() {
        return agingRoom;
    }

    public void setAgingRoom(String agingRoom) {
        this.agingRoom = agingRoom;
    }

    public String getAgingRack() {
        return agingRack;
    }

    public void setAgingRack(String agingRack) {
        this.agingRack = agingRack;
    }

    public String getUhost() {
        return uhost;
    }

    public void setUhost(String uhost) {
        this.uhost = uhost;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getPcb() {
        return pcb;
    }

    public void setPcb(String pcb) {
        this.pcb = pcb;
    }

    public String getEmmcId() {
        return emmcId;
    }

    public void setEmmcId(String emmcId) {
        this.emmcId = emmcId;
    }

    public String getSystemVersion() {
        return systemVersion;
    }

    public void setSystemVersion(String systemVersion) {
        this.systemVersion = systemVersion;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }


}
