/**
 * @Copyright:Copyright © VIVO Communication Technology Co., Ltd. All rights reserved.
 * @Company:http://www.vivo.com.cn/
 */
package com.vivo.uhost.protocol.server.service.impl;

import com.bbkmobile.iqoo.common.util.JsonUtil;
import com.bbkmobile.iqoo.common.util.SpringBeanUtil;
import com.vivo.uhost.comm.http.CloseableHttpClientUtils;
import com.vivo.uhost.comm.util.Constants;
import com.vivo.uhost.comm.util.DateUtils;
import com.vivo.uhost.comm.util.StringUtil;
import com.vivo.uhost.core.util.CfgContants;
import com.vivo.uhost.dal.entity.DeviceInfo;
import com.vivo.uhost.message.model.*;
import com.vivo.uhost.comm.websocket.WebSocketServer;
import com.vivo.uhost.protocol.redis.RedisDao;
import com.vivo.uhost.protocol.redis.mq.topic.RedisQProducer;
import com.vivo.uhost.protocol.server.service.IDeviceChangeService;
import com.vivo.uhost.protocol.server.utils.RedisKeyUtils;
import com.vivo.uhost.protocol.server.utils.ServerConstants;
import com.vivo.uhost.service.IDeviceService;
import com.vivo.uhost.service.IDeviceTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author huangxiaoqun  2018/3/20 16:55
 * @version 1.0
 * @description
 */
@Service("genericService")
public class DeviceChangeServiceImpl implements IDeviceChangeService {


    @Autowired
    private RedisDao redisDao;

    @Autowired
    private IDeviceService deviceService;

    @Autowired
    private IDeviceTaskService deviceTaskService;

    private String tokenKey = null;

    /**
     * 设备状态变更本地登记
     *
     * @param deviceStateChangeReq 设备参数
     */
    public void deviceChanged(DeviceStateChangeReq deviceStateChangeReq) {
        tokenKey = RedisKeyUtils.getUhostKey(deviceStateChangeReq.getToken());
        Map<String, String> mapEntriy = redisDao.entries(tokenKey);
        if (mapEntriy == null || mapEntriy.isEmpty()) {
            return;
        }
        for (DeviceModel deviceModel : deviceStateChangeReq.getConnected()) {

            if (StringUtil.isBlank(deviceModel.getEmmcId())
                    || StringUtil.isBlank(deviceModel.getModelVer())
                    || StringUtil.isBlank(deviceModel.getOsVer())) {
                continue;
            }
            String hashKey = RedisKeyUtils.getDeviceKey(deviceModel.getEmmcId());
            redisDao.hashAdd(hashKey, "createdTime", DateUtils.format(new Date(), DateUtils.FORMAT_YMDHMS));
            redisDao.hashAdd(hashKey, "emmcId", deviceModel.getEmmcId().trim());
            redisDao.hashAdd(hashKey, "pcbCode", deviceModel.getPcbCode().trim());
            redisDao.hashAdd(hashKey, "modelVer", deviceModel.getModelVer().trim());
            redisDao.hashAdd(hashKey, "osVer", deviceModel.getOsVer().trim());
            redisDao.hashAdd(hashKey,"imei",deviceModel.getImei().trim());
            redisDao.hashAdd(hashKey,"serial",deviceModel.getSerial().trim());

            //db操作设备为在线状态
            DeviceInfo deviceInfo = deviceModelToEntity(deviceModel);
            deviceService.setDeviceState(deviceInfo, CfgContants.DEVICE_ONLINE, deviceStateChangeReq.getToken());

        }
        for (DeviceModel deviceModel : deviceStateChangeReq.getDisConnected()) {
            if (StringUtil.isBlank(deviceModel.getEmmcId())
                    || StringUtil.isBlank(deviceModel.getModelVer())
                    || StringUtil.isBlank(deviceModel.getOsVer())
                    || StringUtil.isBlank(deviceModel.getImei())
                    || StringUtil.isBlank(deviceModel.getSerial())) {
                continue;
            }
            String hashKey = RedisKeyUtils.getDeviceKey(deviceModel.getEmmcId());
            redisDao.del(hashKey);
            //db操作设备为离线状态
            DeviceInfo deviceInfo = deviceModelToEntity(deviceModel);
            deviceService.setDeviceState(deviceInfo, CfgContants.DEVICE_NOT_ONLINE, deviceStateChangeReq.getToken());
        }
        RedisQProducer redisQProducer = SpringBeanUtil.getBean("redisQProducer", RedisQProducer.class);
        //通知浏览器刷新设备列表
        redisQProducer.sendPageRefresh(Constants.REFRESH_DEVICE);//这里和前端对应，消息体中包含device

        String url = ServerConstants.API_HOST + "/device/state";
        CloseableHttpClientUtils.post(url, JsonUtil.bean2Json(deviceStateChangeReq));
    }

    private DeviceInfo deviceModelToEntity(DeviceModel deviceModel) {
        DeviceInfo deviceInfo = new DeviceInfo();
        deviceInfo.setEmmcId(deviceModel.getEmmcId());
        deviceInfo.setModel(deviceModel.getModelVer());
        deviceInfo.setPcb(deviceModel.getPcbCode());
        deviceInfo.setSystemVersion(deviceModel.getOsVer());
        deviceInfo.setImei(deviceModel.getImei());
        deviceInfo.setSerial(deviceModel.getSerial());
        return deviceInfo;
    }

    @Override
    public void processTaskResult(PushTaskResp pushTaskResp) {
        Map<String, Integer> resultMap = new HashMap<>();
        for (DeviceStateInfo deviceStateInfo : pushTaskResp.getResult()) {
            resultMap.put(deviceStateInfo.getEmecid(), deviceStateInfo.getState());
        }
        //更改设备测试状态
        deviceTaskService.changeTaskState(pushTaskResp.getMsgId(), pushTaskResp.getToken(), resultMap);
    }

    /**
     * 设备状态变更的同时，端口号也随之变更
     * 获取的都是在线的emmc进行操作
     *
     * @param changeReq
     */
    @Override
    public void uhostDeviceChanged(DevicePortNameChangeReq changeReq) {
        Map<String, String> map = changeReq.getMap();
        Iterator iter = map.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            String emmcId = entry.getKey().toString();
            String portName = entry.getValue().toString();
            String key = RedisKeyUtils.getDeviceKey(emmcId);
            if (redisDao.hasKey(key)) {
                String oldPortNum = redisDao.getHashValue(key, "portName");
                if (!portName.equals(oldPortNum)) {
                    redisDao.hashAdd(key, "portName", portName.trim());
                }
                DeviceInfo deviceInfo = new DeviceInfo();
                deviceInfo.setEmmcId(emmcId);
                DeviceInfo device = deviceService.selectListByEntity(deviceInfo);
                if (device.getState() == 1) {
                    device.setPortName(portName);
                    deviceService.edit(device);
                }
            }
        }
    }
}