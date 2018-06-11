package com.vivo.uhost.message.model;


import java.util.List;

/**
 * @Title:
 * @Description:
 * @Author:chengen
 * @Since:15-6-25
 * @Modified By:
 * @Modified Date:
 * @Why &amp; What is modified:
 * @Version:1.0
 */
public class PushTaskResp {

    private Integer msgId;  //消息id

    private String token;

    private List<DeviceStateInfo> result;  //消息处理状态  STATE_TASK_SUCCESS：成功， STATE_TASK_FAILED：失败

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

    public List<DeviceStateInfo> getResult() {
        return result;
    }

    public void setResult(List<DeviceStateInfo> result) {
        this.result = result;
    }


    @Override
    public String toString() {
        return "PushTaskResp{" +
                "msgId=" + msgId +
                ", token='" + token + '\'' +
                ", result=" + result +
                '}';
    }
}
