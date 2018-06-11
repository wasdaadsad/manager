package com.vivo.uhost.api.task;

import com.vivo.uhost.dal.entity.DeviceInfo;
import com.vivo.uhost.dal.entity.ServerNode;
import com.vivo.uhost.comm.util.Constants;
import com.vivo.uhost.service.IServerNodeService;
import com.vivo.uhost.service.impl.DeviceServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;

/**
 * @Title:
 * @Description:
 * @Author:chengen
 * @Since:15-7-3
 * @Modified By:
 * @Modified Date:
 * @Why &amp; What is modified:
 * @Version:1.0
 */
public class DeviceStateTask implements Runnable {
    //log工厂获得对应的log实例
    private static final Logger logger = LoggerFactory.getLogger(DeviceStateTask.class);
    //获得操作设备的service
    //token
    private String token;
    //地址
    private String address;
    //状态
    private Integer state;

    private IServerNodeService iServerNodeService;

    private DeviceServiceImpl deviceServiceImpl;

    //构造方法
    public DeviceStateTask(IServerNodeService serverNodeService, DeviceServiceImpl deviceServiceImpl, String token, String address, Integer state) {
        this.token = token;
        this.address = address;
        this.state = state;
        this.iServerNodeService = serverNodeService;
        this.deviceServiceImpl = deviceServiceImpl;
        //初始化环境
        /*initEnv();*/
    }

    /*private void initEnv(){
        deviceService = SpringUtils.getBean("deviceService", DeviceService.class);
        serverNodeService = SpringUtils.getBean("serverNodeService", ServerNodeService.class);
    }*/

    @Override
    public void run() {
        try {
            ServerNode serverNodeE = new ServerNode();
            serverNodeE.setServerAddress(address);
            ServerNode serverNode = iServerNodeService.getNodeByAddress(serverNodeE);
            if (serverNode == null) {
                return;
            }
            DeviceInfo deviceE = new DeviceInfo();
            deviceE.setToken(token);
            List<DeviceInfo> devices = deviceServiceImpl.selectListByUhostId(deviceE);
            if (devices.size() < 0) {
                return;
            }

            long nodeId = serverNode.getId();
            //与当前所属节点不一致的下线消息忽略
            if (Constants.STATE_OFFLINE.equals(state) && devices.get(0).getNodeId() != null && nodeId != (devices.get(0).getNodeId())) {
                return;
            }
            if (Constants.STATE_ONLINE.equals(state)) {
                devices.get(0).setNodeId((int)nodeId);
                devices.get(0).setLastLoginTime(new Date());
            }
            devices.get(0).setState(state);
            deviceServiceImpl.edit(devices.get(0));
            logger.info("device state update! deviceId: {}, nodeId: {}, state: {}", devices.get(0).getId(), nodeId, state);
        } catch (Exception e) {
            logger.error("execute device state task error!", e);
        }
    }
}
