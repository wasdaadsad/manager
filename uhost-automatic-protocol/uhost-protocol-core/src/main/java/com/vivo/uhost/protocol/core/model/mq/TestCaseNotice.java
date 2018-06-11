package com.vivo.uhost.protocol.core.model.mq;

import com.vivo.uhost.message.model.TestCaseInfo;
import java.util.List;

/**
 * @Author: DongJiaJin
 * @Description:上报给server的自动化点检的测试结果的消息体
 * @Date: Created in 14:52 2018/5/21
 * @Modified By:
 */
public class TestCaseNotice {

    private Integer msgId;                  //消息id
    private String token;                   //携带token指明哪一个客户端
    private List<TestCaseInfo> result;      //各个子用例的测试结果

    public TestCaseNotice() {
    }

    public TestCaseNotice(Integer msgId, String token, List<TestCaseInfo> result) {
        this.msgId = msgId;
        this.token = token;
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

    public List<TestCaseInfo> getResult() {
        return result;
    }

    public void setResult(List<TestCaseInfo> result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "TestCaseNotice{" +
                "msgId=" + msgId +
                ", token='" + token + '\'' +
                ", result=" + result +
                '}';
    }
}