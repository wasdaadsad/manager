/**
 * @Copyright:Copyright © VIVO Communication Technology Co., Ltd. All rights reserved.
 * @Company:http://www.vivo.com.cn/
 */
package com.vivo.uhost.protocol.client.task;

import com.bbkmobile.iqoo.common.util.SpringBeanUtil;
import com.vivo.uhost.message.model.DeviceModel;
import com.vivo.uhost.message.model.DevicePortNameChangeReq;
import com.vivo.uhost.message.model.DeviceStateChangeReq;
import com.vivo.uhost.protocol.client.CfgUtils;
import com.vivo.uhost.protocol.client.ClientConstants;
import com.vivo.uhost.protocol.client.MessageSender;
import com.vivo.uhost.protocol.client.tools.DeviceCommProp;
import com.vivo.uhost.protocol.client.tools.SerialUtils;
import com.vivo.uhost.protocol.redis.mq.topic.RedisQProducer;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.ListUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author huangxiaoqun  2018/3/26 17:47
 * @version 1.0
 * @description
 */
public class DeviceStateTask implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(DeviceStateTask.class);

    private volatile List<String> oldEmmcs = new ArrayList<>();

    private volatile Map<String, DeviceModel> deviceModelMap = new ConcurrentHashMap<>();

    private DeviceStateChangeReq getDeviceStateChangeReq() {
        DeviceStateChangeReq deviceStateChangeReq = new DeviceStateChangeReq();
        ArrayList<String> serials = DeviceCommProp.adbDevices();
        if (!MessageSender.isOnline()) {
            oldEmmcs.clear();//确保server崩溃后，可以重新开始对比，否则设备状态不会变为在线
            return deviceStateChangeReq;
        }
        //获取上次的数据，进行比较

        List<String> emmcs = new ArrayList<>();
        for (String serial : serials) {
            DeviceModel deviceModel = new DeviceModel();
            String emmcId = DeviceCommProp.getEmmcId(serial);
            String modelVer = DeviceCommProp.getModelVersion(serial);
            String oSVersion = DeviceCommProp.getOsVersion(serial);
            String imei = DeviceCommProp.adbGetImeiNum(serial);
            deviceModel.setEmmcId(emmcId);
            deviceModel.setModelVer(modelVer);
            deviceModel.setOsVer(oSVersion);
            deviceModel.setPcbCode(serial);
            deviceModel.setImei(imei);
            deviceModel.setSerial(serial);
            emmcs.add(emmcId);
            deviceModelMap.put(emmcId, deviceModel);
        }

        if (ListUtils.isEqualList(emmcs, oldEmmcs)) {
            return deviceStateChangeReq;
        }

        List<DeviceModel> connected = new ArrayList<>();
        List<DeviceModel> unConnected = new ArrayList<>();

        for (String emmid : oldEmmcs) {
            if (!emmcs.contains(emmid)) {
                unConnected.add(deviceModelMap.get(emmid));
            }
        }

        for (String emmid : emmcs) {
            if (!oldEmmcs.contains(emmid)) {
                connected.add(deviceModelMap.get(emmid));
            }
        }

        deviceStateChangeReq.setConnected(connected);
        deviceStateChangeReq.setDisConnected(unConnected);
        deviceStateChangeReq.setToken(MessageSender.getToken());
        //怎么检测处理单台设备的登入登出;
        oldEmmcs = emmcs;
        return deviceStateChangeReq;
    }


    @Override
    public void run() {
        DeviceStateChangeReq deviceStateChangeReq = getDeviceStateChangeReq();
        if (CollectionUtils.isEmpty(deviceStateChangeReq.getConnected())
                && CollectionUtils.isEmpty(deviceStateChangeReq.getDisConnected())) {
            return;
        }
        try {
            //状态通过redis 发布订阅来完成上报
            String sendMethod = CfgUtils.getString(ClientConstants.DEVICE_STATE_SEND, "channel");
            if (sendMethod.equals("topic")) {
                //使用redis的发布订阅模式来实现上报设备在线状态
                RedisQProducer redisQProducer = SpringBeanUtil.getBean("redisQProducer", RedisQProducer.class);
                redisQProducer.sendDeviceState(deviceStateChangeReq);
            } else {
                //通过netty Channel来上报在线状态
                MessageSender.reportDeviceStateChange(deviceStateChangeReq);
            }
        } catch (Exception ex) {
            logger.error("send device state change failed!", ex);
        } finally {
            if(System.getProperty ("os.name").contains("Windows")){
                ArrayList<String> serials = DeviceCommProp.adbDevices();
                List<String> emmcs = new ArrayList<>();
                for (String serial : serials) {
                    emmcs.add(DeviceCommProp.getEmmcId(serial));
                }
                Map<String, String> map = SerialUtils.getEmmcAndSerialPortNameMap(emmcs);
                String token = deviceStateChangeReq.getToken();
                DevicePortNameChangeReq devicePortNameChangeReq = new DevicePortNameChangeReq(token,map);
                if (map!=null && map.size()>0){
                    //状态通过redis 发布订阅来完成上报
                    String sendMethod = CfgUtils.getString(ClientConstants.DEVICE_STATE_SEND, "channel");
                    if (sendMethod.equals("topic")) {
                        //使用redis的发布订阅模式来实现上报设备在线状态
                        RedisQProducer redisQProducer = SpringBeanUtil.getBean("redisQProducer", RedisQProducer.class);
                        redisQProducer.sendDeviceStateToPortName(devicePortNameChangeReq);
                    }else {
                        MessageSender.reportDevicePortNameStateChange(devicePortNameChangeReq);
                    }
                }
            }
        }
    }
}
