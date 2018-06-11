/**
 * @Copyright:Copyright © VIVO Communication Technology Co., Ltd. All rights reserved.
 * @Company:http://www.vivo.com.cn/
 */
package com.vivo.uhost.message.model;

/**
 * @author huangxiaoqun  2018/3/26 22:13
 * @version 1.0
 * @description
 */
public class DeviceStateInfo {

    private String emecid;  //emecid

    private Integer state;  //消息处理状态  1：成功， 2：失败

    public String getEmecid() {
        return emecid;
    }

    public void setEmecid(String emecid) {
        this.emecid = emecid;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "DeviceStateInfo{" +
                "emecid='" + emecid + '\'' +
                ", state=" + state +
                '}';
    }
}
