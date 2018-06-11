package com.vivo.uhost.web.mq;

import com.bbkmobile.iqoo.common.util.SpringBeanUtil;
import com.vivo.uhost.comm.util.Constants;
import com.vivo.uhost.comm.util.JsonUtils;
import com.vivo.uhost.comm.websocket.WebSocketServer;
import com.vivo.uhost.dal.entity.DeviceInfo;
import com.vivo.uhost.dal.entity.UhostInfo;
import com.vivo.uhost.service.IDeviceService;
import com.vivo.uhost.service.IUhostInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @Author: DongJiaJin
 * @Description:server崩溃或强制退出后的web消费者
 * @Date: Created in 19:37 2018/5/3
 * @Modified By:
 */
public class ServerBreakDownConsumer implements MessageListener {
    private static Logger logger = LoggerFactory.getLogger(ServerBreakDownConsumer.class);

    @Override
    public void onMessage(Message message, byte[] bytes) {
        if(message == null){
            return;
        }
        try {
            String msg = message.toString();
            logger.info("received mesage from Topic-[uhost]: {}", msg);
            Map map = JsonUtils.toObject(msg, Map.class);
            String serverIp = (String) map.get("serverIp");
            String token = (String) map.get("token");
            IUhostInfoService uhostInfoService = SpringBeanUtil.getBean("uhostInfoService", IUhostInfoService.class);
            IDeviceService deviceService = SpringBeanUtil.getBean("deviceService", IDeviceService.class);
            UhostInfo uhostInfo = new UhostInfo();
            uhostInfo.setToken(token);
            UhostInfo uhostInfoItem = uhostInfoService.selectByToken(uhostInfo);
            if (uhostInfoItem != null && uhostInfoItem.getIpAdress().equals(serverIp)){//进一步确保修改的Uhost的ip为崩溃的server的ip
                uhostInfoItem.setStatus(0);
                uhostInfoService.editUhost(uhostInfoItem);//更新Uhost的状态为离线
                DeviceInfo deviceInfo = new DeviceInfo();
                deviceInfo.setUhostId(uhostInfoItem.getId());
                List<DeviceInfo> deviceInfoList =  deviceService.selectListByUhostId(deviceInfo);
                for (DeviceInfo deviceInfoItem  : deviceInfoList){
                    deviceInfoItem.setState(0);
                    deviceService.edit(deviceInfoItem);//更新设备的状态为离线
                }
            }
            try {
                new WebSocketServer().sendToAllMessage(Constants.REFRESH_DEVICE_UHOST);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            logger.error("handle push message error!", e);
        }
    }
}
