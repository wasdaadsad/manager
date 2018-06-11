/*
*
* Created by IntelliJ IDEA.
  User: dongjiajin
  Date: 2018/1/10
  Time: 16:15
  To change this template use File | Settings | File Templates.
*
* */

package com.vivo.uhost.core.domain.bo;


import java.util.Date;

public class CheckDeviceBO {
    private long id;//id
    private String ip;//ip地址
    private String status;//状态
    private String model;//机型
    private String software;//软件版本
    private Date lastTime;//最后上线时间
    private String token;//唯一标示管道
    private int offset;
    private int pageSize;

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

    @Override
    public String toString() {
        return "CheckDeviceBO{" +
                "id=" + id +
                ", ip='" + ip + '\'' +
                ", status='" + status + '\'' +
                ", model='" + model + '\'' +
                ", software='" + software + '\'' +
                ", lastTime=" + lastTime +
                ", token='" + token + '\'' +
                ", offset=" + offset +
                ", pageSize=" + pageSize +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSoftware() {
        return software;
    }

    public void setSoftware(String software) {
        this.software = software;
    }

    public Date getLastTime() {
        return lastTime;
    }

    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
