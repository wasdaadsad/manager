/**
 * @Copyright:Copyright © VIVO Communication Technology Co., Ltd. All rights reserved.
 * @Company:http://www.vivo.com.cn/
 */
package com.vivo.uhost.message.model;

import java.util.List;

/**
 * @author huangxiaoqun  2018/3/20 19:17
 * @version 1.0
 * @description Uhost上报手机测试执行状态
 */
public class DeviceRunningReportReq {

    private String token;

    private List<PushTaskReportModel> pushTaskReportModels;

    private String ipAdress;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<PushTaskReportModel> getPushTaskReportModels() {
        return pushTaskReportModels;
    }

    public void setPushTaskReportModels(List<PushTaskReportModel> pushTaskReportModels) {
        this.pushTaskReportModels = pushTaskReportModels;
    }

    public String getIpAdress() {
        return ipAdress;
    }

    public void setIpAdress(String ipAdress) {
        this.ipAdress = ipAdress;
    }
}
