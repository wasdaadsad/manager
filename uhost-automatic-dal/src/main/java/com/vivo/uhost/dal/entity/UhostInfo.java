package com.vivo.uhost.dal.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.vivo.uhost.dal.json.CustomJsonDateDeserializer;

import java.util.Date;

/**
 * @author litingfa  2018/3/19 17:54
 * @version 1.0
 * @description
 */
public class UhostInfo {

    private Long id;//uhost id

    private int serverId;//服务器id

    private String factory;//厂区

    private String workShop;//车间

    private String agingRoom;//老化房

    private String agingRack;//老化架

    private String token;

    //uhost唯一标示
    private String uidentify;

    private String ipAdress;//uhost的Ip地址

    private Date createTime;

    private Date updateTime;

    private Integer status;

    private String remark;

    public UhostInfo() {
    }


    public UhostInfo(String factory, String workShop, String agingRoom, String agingRack, String uidentify, String ipAdress) {
        this.factory = factory;
        this.workShop = workShop;
        this.agingRoom = agingRoom;
        this.agingRack = agingRack;
        this.uidentify = uidentify;
        this.ipAdress = ipAdress;
        this.createTime = new Date();
        this.updateTime = new Date();
        this.status = 1;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getServerId() {
        return serverId;
    }

    public void setServerId(int serverId) {
        this.serverId = serverId;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


    public String getUidentify() {
        return uidentify;
    }

    public void setUidentify(String uidentify) {
        this.uidentify = uidentify;
    }

    public String getIpAdress() {
        return ipAdress;
    }

    public void setIpAdress(String ipAdress) {
        this.ipAdress = ipAdress;
    }

    public Date getCreateTime() {
        return createTime;
    }

    @JsonDeserialize(using = CustomJsonDateDeserializer.class)
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    @JsonDeserialize(using = CustomJsonDateDeserializer.class)
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
