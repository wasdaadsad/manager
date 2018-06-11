package com.vivo.uhost.core.domain.bo;

import com.vivo.uhost.dal.entity.DeviceInfo;
import org.apache.commons.lang3.StringUtils;

/**
 * @author WangWenqian
 * @version 1.3.0
 * @describe
 * @date 2016/12/5
 */
public class DeviceBo {

    protected static String PATTEN = "yyyy-MM-dd HH:mm:ss";
    @Override
    public String toString() {
        return "DeviceBo{" +
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
                ", nodeId=" + nodeId +
                ", ip='" + ip + '\'' +
                ", state='" + state + '\'' +
                ", systemVersion='" + systemVersion + '\'' +
                ", lastLoginTime='" + lastLoginTime + '\'' +
                '}';
    }

    private Long id;//唯一id
    private String factory;//厂区
    private String workShop;//车间
    private String agingRoom;//老化房
    private String agingRack;//老化架
    private String token;
    private String uhost;//uhostId;
    private Long uhostId;//uhostId;
    private String model;//机型
    private String pcb;//pcb号
    private String emmcId;//emmcId
    private Integer nodeId;
    private String systemVersion;//系统版本
    private String ip;
    private Integer state;
    private String lastLoginTime;//最近一次上线时间
    private String stateVp;//状态描述
    private String imei;
    private String portName;
    private String serial;

    public DeviceBo() {
    }

    public DeviceInfo toEntity() {
        DeviceInfo device = new DeviceInfo();
        device.setId(this.id);
        if(StringUtils.isNotBlank(factory) && factory.equals("all")){
            device.setFactory(null);
        }else{
            device.setFactory(this.factory);
        }
        if(StringUtils.isNotBlank(workShop) && workShop.equals("all")){
            device.setWorkShop(null);
        }else{
            device.setWorkShop(this.workShop);
        }if(StringUtils.isNotBlank(agingRack) && agingRack.equals("all")){
            device.setAgingRack(null);
        }else{
            device.setAgingRack(this.agingRack);
        }if(StringUtils.isNotBlank(agingRoom) && agingRoom.equals("all")){
            device.setAgingRoom(null);
        }else{
            device.setAgingRoom(this.agingRoom);
        }if(StringUtils.isNotBlank(uhost) && uhost.equals("all")){
            device.setUhost(null);
        }else{
            device.setUhost(this.uhost);
        }if(StringUtils.isNotBlank(model) && model.equals("all")){
            device.setModel(null);
        }else{
            device.setModel(this.model);
        }
        device.setState(this.state);
        device.setUhostId(this.uhostId);
        device.setPcb(this.pcb);
        device.setEmmcId(this.emmcId);
        device.setSystemVersion(this.systemVersion);
        device.setToken(this.token);
        device.setImei(this.imei);
        device.setPortName(this.portName);
        device.setSerial(this.serial);
        return device;
    }

    public DeviceBo(DeviceInfo cr) {
        this.model = cr.getModel();
        this.id = cr.getId();
        this.factory = cr.getFactory();
        this.workShop = cr.getWorkShop();
        this.agingRoom = cr.getAgingRoom();
        this.agingRack = cr.getAgingRack();
        this.state = cr.getState();
        switch (state) {
                case 1:
                    stateVp = "在线";
                    break;
                case 0:
                    stateVp = "离线";
                    break;
                default:
                    stateVp = "未知";
        }
        this.uhost = cr.getUhost();
        this.pcb = cr.getPcb();
        this.emmcId = cr.getEmmcId();
        this.systemVersion = cr.getSystemVersion();
        this.token = cr.getToken();
        this.uhostId = cr.getUhostId();
        this.imei = cr.getImei();
        this.portName = cr.getPortName();
        this.serial = cr.getSerial();
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

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

    public void setUhostId(Long  uhostId) {
        this.uhostId = uhostId;
    }

    public Integer getNodeId() {
        return nodeId;
    }

    public void setNodeId(Integer nodeId) {
        this.nodeId = nodeId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getStateVp() {
        return stateVp;
    }

    public void setStateVp(String stateVp) {
        this.stateVp = stateVp;
    }

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

    public String getLastLoginTime() {
        return lastLoginTime;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setLastLoginTime(String lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }
}
