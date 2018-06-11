package com.vivo.uhost.protocol.client.tools;

/**
 * 串口通信指令类型封装
 *
 * @author LuZhiWei
 * @version 1.0
 * @describe
 * @date 2018/3/30
 */
public class SerialCmdEntity {
    private String cmd;
    //轮询200，测试500以上
    private long sendWaitTime;
    //respOutTime>sendWaitTime,包含发送等待
    private long respOutTime;

    public SerialCmdEntity(String cmd, long sendWaitTime, long respOutTime) {
        this.cmd = cmd;
        this.sendWaitTime = sendWaitTime;
        this.respOutTime = respOutTime;
    }

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    public long getSendWaitTime() {
        return sendWaitTime;
    }

    public void setSendWaitTime(long sendWaitTime) {
        this.sendWaitTime = sendWaitTime;
    }

    public long getRespOutTime() {
        return respOutTime;
    }

    public void setRespOutTime(long respOutTime) {
        this.respOutTime = respOutTime;
    }

    @Override
    public String toString() {
        return "SerialCmdEntity{" +
                "cmd='" + cmd + '\'' +
                ", sendWaitTime=" + sendWaitTime +
                ", respOutTime=" + respOutTime +
                '}';
    }
}
