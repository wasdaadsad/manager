package com.vivo.uhost; /**
 * @Copyright:Copyright © VIVO Communication Technology Co., Ltd. All rights reserved.
 * @Company:http://www.vivo.com.cn/
 */


import com.vivo.uhost.core.util.CfgContants;
import com.vivo.uhost.message.model.DeviceStateInfo;
import com.vivo.uhost.message.model.PushTaskResp;
import com.vivo.uhost.protocol.server.service.IDeviceChangeService;
import com.vivo.uhost.protocol.server.service.impl.DeviceChangeServiceImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * @author huangxiaoqun  2017/9/1 9:26
 * @version 1.0
 * @description
 */
public class BaseTest {


    public static void main(String[] args) throws Exception {

        /*PushTaskResp pushTaskResp = new PushTaskResp();
        pushTaskResp.setMsgId(61);
        pushTaskResp.setToken("c6240e478d6542d2befbd854bde30749");
        List<DeviceStateInfo> list = new ArrayList();
        DeviceStateInfo deviceStateInfo = new DeviceStateInfo();
        deviceStateInfo.setEmecid("150100475836424d420353e2e4648400");
        deviceStateInfo.setState(CfgContants.DEVICE_STATUS_OK);
        list.add(deviceStateInfo);
        DeviceStateInfo deviceStateInfo1 = new DeviceStateInfo();
        deviceStateInfo1.setEmecid("150100475836424d420353e2e4fd8400");
        deviceStateInfo1.setState(CfgContants.DEVICE_STATUS_OK);
        list.add(deviceStateInfo1);
        pushTaskResp.setResult(list);
        IDeviceChangeService deviceChangeService = new DeviceChangeServiceImpl();
        deviceChangeService.processTaskResult(pushTaskResp);*/
    }

}
