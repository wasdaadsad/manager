package com.vivo.uhost.core.domain.bo;

import com.vivo.uhost.dal.entity.UhostInfo;
import org.springframework.format.datetime.DateFormatter;

import java.util.Date;
import java.util.Locale;

/**
 * @author litingfa  2018/3/19 17:42
 * @version 1.0
 * @description
 */
public class UhostInfoBO {
    protected static String PATTEN = "yyyy-MM-dd HH:mm:ss";

    private Long id;//uhost id

    private int serverId;//服务器id

    private String factory;//厂区

    private String workShop;//车间

    private String agingRoom;//老化房

    private String agingRack;//老化架

    private String uidentify;

    private String token;

    private String ipAdress;//uhost的Ip地址

    private Date createTime;

    private Date updateTime;

    private String createTimeVP;

    private String updateTimeVP;

    private String status;

    private String remark;

    public UhostInfoBO(UhostInfo res) {
        this.id = res.getId();
        this.serverId = res.getServerId();
        this.factory = res.getFactory();
        this.workShop = res.getWorkShop();
        this.agingRoom = res.getAgingRoom();
        this.agingRack = res.getAgingRack();
        this.uidentify = res.getUidentify();
        this.token = res.getToken();
        this.ipAdress = res.getIpAdress();
        this.createTime = res.getCreateTime();
        this.updateTime = res.getUpdateTime();
        if (res.getStatus() == 0){
            this.status = "离线";
        }else if(res.getStatus() == 2){
            this.status = "在线";
        }

        this.remark = res.getRemark();
        if(res.getCreateTime() != null){
            this.createTimeVP = new DateFormatter(PATTEN).print(res.getCreateTime(), Locale.CHINA);
        }
        if(res.getUpdateTime() != null){
            this.updateTimeVP = new DateFormatter(PATTEN).print(res.getUpdateTime(), Locale.CHINA);
        }
    }

    public UhostInfo toEntity() {
        UhostInfo uhost = new UhostInfo();
        uhost.setId(this.id);
        uhost.setServerId(this.serverId);
        uhost.setFactory(this.factory);
        uhost.setWorkShop(this.workShop);
        uhost.setAgingRoom(this.agingRoom);
        uhost.setAgingRack(this.agingRack);
        uhost.setToken(this.token);
        uhost.setUidentify(this.uidentify);
        uhost.setIpAdress(this.ipAdress);
        if (this.status != null){
            if (this.status.equals("在线")){
                uhost.setStatus(2);
            }else if(this.status.equals("离线")){
                uhost.setStatus(0);
            }
        }
        uhost.setCreateTime(this.createTime);
        uhost.setRemark(this.remark);
        uhost.setUpdateTime(this.updateTime);
        return uhost;
    }

    public UhostInfoBO() {
    }

    public String getCreateTimeVP() {
        return createTimeVP;
    }

    public void setCreateTimeVP(String createTimeVP) {
        this.createTimeVP = createTimeVP;
    }

    public String getUpdateTimeVP() {
        return updateTimeVP;
    }

    public void setUpdateTimeVP(String updateTimeVP) {
        this.updateTimeVP = updateTimeVP;
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

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


    @Override
    public String toString() {
        return "UhostInfoBO{" +
                "id=" + id +
                ", serverId=" + serverId +
                ", factory='" + factory + '\'' +
                ", workShop='" + workShop + '\'' +
                ", agingRoom='" + agingRoom + '\'' +
                ", agingRack='" + agingRack + '\'' +
                ", uidentify='" + uidentify + '\'' +
                ", token='" + token + '\'' +
                ", ipAdress='" + ipAdress + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", createTimeVP='" + createTimeVP + '\'' +
                ", updateTimeVP='" + updateTimeVP + '\'' +
                ", status='" + status + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
