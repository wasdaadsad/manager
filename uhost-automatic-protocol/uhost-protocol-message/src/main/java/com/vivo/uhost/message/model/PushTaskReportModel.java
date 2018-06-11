package com.vivo.uhost.message.model;

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
public class PushTaskReportModel {


    private Integer msgId;

    private String token;

    //测试项  0-CPUTest	 1-RAMTest	2-MMCTest  3-SleepTest  4-RebootTest
    private Integer testType;

    //运行状态 0-启动测试 1-成功  2-失败 3-测试结束
    private Integer runState;

    //备注
    private String comment;

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

    public Integer getTestType() {
        return testType;
    }

    public void setTestType(Integer testType) {
        this.testType = testType;
    }

    public Integer getRunState() {
        return runState;
    }

    public void setRunState(Integer runState) {
        this.runState = runState;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "PushTaskReportModel{" +
                "msgId=" + msgId +
                ", token='" + token + '\'' +
                ", testType=" + testType +
                ", runState=" + runState +
                ", comment='" + comment + '\'' +
                '}';
    }
}
