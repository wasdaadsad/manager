/**
 * @Copyright:Copyright © VIVO Communication Technology Co., Ltd. All rights reserved.
 * @Company:http://www.vivo.com.cn/
 */
package com.vivo.uhost.message.model;

/**
 * @author huangxiaoqun  2018/3/21 19:52
 * @version 1.0
 * @description uhost模型
 */
public class UhostModel {

    private String uidentify;

    private String factory;

    private String workShorp;

    private String agingRoom;

    private String agingRack;

    private String ipAdress;

    public UhostModel() {
    }

    public UhostModel(String uidentify, String factory, String workShorp, String agingRoom, String agingRack, String ipAdress) {
        this.uidentify = uidentify;
        this.factory = factory;
        this.workShorp = workShorp;
        this.agingRoom = agingRoom;
        this.agingRack = agingRack;
        this.ipAdress = ipAdress;
    }

    public String getUidentify() {
        return uidentify;
    }

    public void setUidentify(String uidentify) {
        this.uidentify = uidentify;
    }

    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory;
    }

    public String getWorkShorp() {
        return workShorp;
    }

    public void setWorkShorp(String workShorp) {
        this.workShorp = workShorp;
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

    public String getIpAdress() {
        return ipAdress;
    }

    public void setIpAdress(String ipAdress) {
        this.ipAdress = ipAdress;
    }
}
