package com.vivo.uhost.protocol.core.model.mq;

import com.vivo.uhost.message.model.DeviceStateInfo;

import java.util.List;

/**
 * @Title:
 * @Description:
 * @Author:chengen
 * @Since:15-7-2
 * @Modified By:
 * @Modified Date:
 * @Why &amp; What is modified:
 * @Version:1.0
 */
public class TaskStateNotice {

    private Integer msgId;  //消息id

    private String token;

    //各个节点的结果
    private List<DeviceStateInfo> result;

    public TaskStateNotice() {
    }


    @Override
    public String toString() {
        return "TaskStateNotice{" +
                "msgId=" + msgId +
                ", token='" + token + '\'' +
                ", result=" + result +
                '}';
    }

    public TaskStateNotice(Integer msgId, String token, List<DeviceStateInfo> result) {
        this.msgId = msgId;
        this.token = token;
        this.result = result;
    }

    public List<DeviceStateInfo> getResult() {
        return result;
    }

    public void setResult(List<DeviceStateInfo> result) {
        this.result = result;
    }

    public Integer getMsgId() {
        return msgId;
    }

    public void setMsgId(Integer msgId) {
        this.msgId = msgId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
