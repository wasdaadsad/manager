package com.vivo.uhost.core.domain.bo;

public class ServerNodeBo {

    private Integer id;

    private String serverAddress;

    private String createTime;

    private String updateTime;

    private String state;
    private int offset;

    public int getOffset() {
        return offset;
    }

    public int getPageSize() {
        return pageSize;
    }

    private int pageSize;

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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "ServerNode{" +
                "id=" + id +
                ", serverAddress='" + serverAddress + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", state=" + state +
                ", offset=" + offset +
                ", pageSize=" + pageSize +
                '}';
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}