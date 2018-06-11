package com.vivo.uhost.protocol.server.bootstrap;

import com.bbkmobile.iqoo.common.util.JsonUtil;
import com.bbkmobile.iqoo.common.util.SpringBeanUtil;
import com.vivo.uhost.comm.util.Constants;
import com.vivo.uhost.comm.util.StringUtil;
import com.vivo.uhost.comm.util.UUIDUtils;
import com.vivo.uhost.dal.entity.ServerNode;
import com.vivo.uhost.dal.redis.IRedisService;
import com.vivo.uhost.protocol.core.model.http.ServerStartReq;
import com.vivo.uhost.protocol.core.service.IFactory;
import com.vivo.uhost.protocol.core.service.IService;
import com.vivo.uhost.protocol.core.service.impl.ServiceFactory;
import com.vivo.uhost.protocol.server.AbstractServer;
import com.vivo.uhost.protocol.server.utils.HttpUtils;
import com.vivo.uhost.protocol.server.utils.ServerConstants;
import com.vivo.uhost.service.IServerNodeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.Map;

public class VPushServerBootstrap extends AbstractServer {

    private static final Logger logger = LoggerFactory.getLogger(VPushServerBootstrap.class);

    private static final int DEFAULT_PORT = 8888;

    public VPushServerBootstrap(IFactory serviceFactory) {
        super(serviceFactory);
    }

    public static void main(String[] args) {
        int port = DEFAULT_PORT;
        if (args.length > 0) {
            port = Integer.parseInt(args[0], DEFAULT_PORT);
        }

        doPrepareStart(port);

        final IFactory serviceFactory = new ServiceFactory();

        //uhost注册处理器
        serviceFactory.addService(Constants.CMD_TYPE_REGISTER, SpringBeanUtil.getBean("uhostRegisterService", IService.class));

        //uhost登录处理器
        serviceFactory.addService(Constants.CMD_TYPE_LOGIN, SpringBeanUtil.getBean("uhostLoginService", IService.class));

        //uhost心跳处理器
        serviceFactory.addService(Constants.CMD_TYPE_PING, SpringBeanUtil.getBean("uhostPingService", IService.class));

        //设备状态变更处理器
        serviceFactory.addService(Constants.CMD_DEVICE_LOGIN, SpringBeanUtil.getBean("deviceLoginStateService", IService.class));

        //端口状态变更处理器
        serviceFactory.addService(Constants.CMD_DEVICE_PORT_LOGIN, SpringBeanUtil.getBean("devicePortNameService", IService.class));

        //处理任务执行结果
        serviceFactory.addService(Constants.CMD_TYPE_SEND_ACK, SpringBeanUtil.getBean("uhostPushAckService", IService.class));

        final int tcpPort = port;

        new Thread(new Runnable() {
            @Override
            public void run() {
                VPushServerBootstrap serverBootstrap = new VPushServerBootstrap(serviceFactory);
                logger.info("start bind port in {}...", tcpPort);
                serverBootstrap.bind(tcpPort);
            }
        }).start();
    }


    //校验状态
    private static void doPrepareStart(final int port) {
        if (StringUtil.isBlank(ServerConstants.SERVER_ADDRESS)) {
            throw new RuntimeException("server adress can not be null !");
        }
        final IRedisService redisService = SpringBeanUtil.getBean("redisService", IRedisService.class);
        //返回哈希表key中所有域和值
        Map<String, String> serverMap = redisService.entries(Constants.PUSH_SERVER);
        String hashKey = null;
        ServerNode serverNode = null;
        if (null != serverMap && !serverMap.isEmpty()) {
            for (Map.Entry<String, String> entry : serverMap.entrySet()) {
                ServerNode node = JsonUtil.json2Bean(entry.getValue(), ServerNode.class);
                if (ServerConstants.SERVER_ADDRESS.equals(node.getServerAddress())) {
                    hashKey = entry.getKey();
                    ServerConstants.SERVER_HASH = entry.getKey();
                    serverNode = node;
                }
            }
        }

        //更新内存中的Server Hash
        if (StringUtil.isBlank(hashKey)) {
            hashKey = UUIDUtils.getUUID();
            ServerConstants.SERVER_HASH = hashKey;
        }

        //新增Server
        if (null == serverNode) {
            serverNode = new ServerNode();
            serverNode.setServerAddress(ServerConstants.SERVER_ADDRESS);
            serverNode.setTcpPort(port);
            serverNode.setState(1);
            serverNode.setConnectCount(0);
        }

        redisService.hashSet(Constants.PUSH_SERVER, hashKey, JsonUtil.bean2Json(serverNode));
        IServerNodeService serverNodeService = SpringBeanUtil.getBean("serverNodeService", IServerNodeService.class);
        //检验本地Server是否已经存在
        long serverCount = serverNodeService.count(serverNode);
        if (serverCount == 0) {
            serverNode.setCreateTime(new Date());
            serverNode.setUpdateTime(new Date());
            serverNodeService.add(serverNode);
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                ServerStartReq req = new ServerStartReq(ServerConstants.SERVER_ADDRESS, port);
                HttpUtils.reportApiServer(ServerConstants.API_SERVER_START, JsonUtil.bean2Json(req));
            }
        }).start();
    }

}
