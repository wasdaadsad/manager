/**
 * @Copyright:Copyright © VIVO Communication Technology Co., Ltd. All rights reserved.
 * @Company:http://www.vivo.com.cn/
 */
package com.vivo.uhost.protocol.server.service;

import com.vivo.uhost.message.model.DevicePortNameChangeReq;
import com.vivo.uhost.message.model.DeviceStateChangeReq;
import com.vivo.uhost.message.model.PushTaskResp;

import java.util.Map;

/**
 * @author huangxiaoqun  2018/3/20 16:55
 * @version 1.0
 * @description
 */
public interface IDeviceChangeService {

    /**
     * 消息变更
     *
     * @param deviceStateChangeReq 设备状态变更
     */
    void deviceChanged(DeviceStateChangeReq deviceStateChangeReq);


    /**
     * 处理设备任务进度
     *
     * @param pushTaskResp
     */
    void processTaskResult(PushTaskResp pushTaskResp);

    /**
     * 设备变更后，uhostPortName 的变更
     *
     * @param changeReq
     */
    void uhostDeviceChanged(DevicePortNameChangeReq changeReq);
}