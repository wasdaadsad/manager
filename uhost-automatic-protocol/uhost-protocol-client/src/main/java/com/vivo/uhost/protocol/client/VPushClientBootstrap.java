package com.vivo.uhost.protocol.client;

import com.alibaba.fastjson.JSONObject;
import com.bbkmobile.iqoo.common.util.JsonUtil;
import com.bbkmobile.iqoo.common.util.SpringBeanUtil;
import com.vivo.uhost.comm.util.Constants;
import com.vivo.uhost.comm.util.JsonUtils;
import com.vivo.uhost.message.codec.PackageEncoder;
import com.vivo.uhost.message.model.UhostModel;
import com.vivo.uhost.protocol.client.common.CommUtils;
import com.vivo.uhost.protocol.client.task.DeviceStateTask;
import com.vivo.uhost.protocol.redis.RedisDao;
import io.netty.channel.Channel;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static com.vivo.uhost.comm.util.FileUtils.createDirectory;

/**
 * clinet 启动类
 */
public class VPushClientBootstrap {

    private static final Logger logger = LoggerFactory.getLogger(VPushClientBootstrap.class);

    private static ScheduledExecutorService pingService = null;

    private static volatile boolean isLogin = false;

    private static ScheduledExecutorService stateChanedService = null;

    public static void main(String[] args) {
        try {
            if(System.getProperty ("os.name").contains("Windows")){
                Constants.UHOST_UPDATE_PACKAGE_PATH = Constants.UHOST_UPDATE_PACKAGE_PATH_WIN;
                Constants.UHOST_TEST_CASE_PATH = Constants.UHOST_TEST_CASE_PATH_WIN;
            }
            createDirectory(Constants.UHOST_UPDATE_PACKAGE_PATH, null);
            createDirectory(Constants.UHOST_TEST_CASE_PATH, null);

            //启动Spring容器
            final FileSystemXmlApplicationContext applicationContext = new FileSystemXmlApplicationContext("classpath:spring/spring-core.xml");
            applicationContext.start();

            RedisDao redisDao = SpringBeanUtil.getBean("redisDao", RedisDao.class);


            Map<String, String> serverInfo = redisDao.entries(Constants.PUSH_SERVER);
            String serverAddress = null;
            Integer port = null;
            if (null == serverInfo || serverInfo.isEmpty()) {
                logger.error("no push server is existed!");
                applicationContext.stop();
                return;
            }

            final List<JSONObject> jsonObjectList = new ArrayList<>();
            for (Map.Entry<String, String> entry : serverInfo.entrySet()) {
                JSONObject jsonObject = JSONObject.parseObject(entry.getValue());
                jsonObjectList.add(jsonObject);
            }

            Collections.sort(jsonObjectList, new Comparator<JSONObject>() {
                @Override
                public int compare(JSONObject o1, JSONObject o2) {
                    Integer count1 = (Integer) o1.get("connectCount");
                    Integer count2 = (Integer) o2.get("connectCount");
                    return count1 - count2;
                }
            });

            VPushClient vPushClient = null;
            Channel channel = null;
            //如果启动了多个push Server，则从连接数最少的开始进行连接
            for (JSONObject jsonObject : jsonObjectList) {
                serverAddress = jsonObject.getString("serverAddress");
                port = jsonObject.getInteger("tcpPort");
                vPushClient = new VPushClient(serverAddress, port);
                channel = vPushClient.connect();
                if (channel != null && channel.isActive()) {
                    break;
                }
            }
            if (channel == null || !channel.isActive()) {
                applicationContext.close();
                throw new RuntimeException("connect push server failed!");
            }
            MessageSender.setChannel(channel);
            String factory = CfgUtils.getConfig(ClientConstants.FACTORY_KEY);
            String workShop = CfgUtils.getConfig(ClientConstants.WORK_SHOP_KEY);
            String agingRack = CfgUtils.getConfig(ClientConstants.AGING_RACK_KEY);
            String agingRoom = CfgUtils.getConfig(ClientConstants.AGING_ROOM_KEY);
            String uIdentify = CfgUtils.getConfig(ClientConstants.U_IDENTIFY_KEY);
            final UhostModel uhostModel = new UhostModel(uIdentify, factory, workShop, agingRoom, agingRack, CommUtils.getLocalIP());
            registAndLogin(uhostModel,vPushClient);
            //延迟初始化
            pingService = Executors.newSingleThreadScheduledExecutor();
            //每10秒钟发送一次心跳
            final VPushClient finalVPushClient = vPushClient;
            pingService.scheduleWithFixedDelay(new Runnable() {
                @Override
                public void run() {
                    MessageSender.ping();
                    if (!MessageSender.isOnline()) {
                        if (!isLogin) {
                            //如果启动了多个push Server，则从连接数最少的开始进行重连,如果都没有连上，则关闭spring容器，抛出异常
                            Channel reconnectChannel = null;
                            for (JSONObject jsonObject : jsonObjectList) {
                                String serverAddress = jsonObject.getString("serverAddress");
                                Integer port = jsonObject.getInteger("tcpPort");
                                finalVPushClient.setHost(serverAddress);
                                finalVPushClient.setPort(port);
                                reconnectChannel = finalVPushClient.connect();
                                if (reconnectChannel != null && reconnectChannel.isActive()) {
                                    break;
                                }
                            }
                            if (reconnectChannel == null || !reconnectChannel.isActive()) {
                                applicationContext.close();
                                throw new RuntimeException("connect push server failed!");
                            }
                            MessageSender.setChannel(reconnectChannel);
                            registAndLogin(uhostModel, finalVPushClient);
                        }
                    }
                }
            }, 1000, 15000, TimeUnit.MILLISECONDS);

            //延迟初始化
            stateChanedService = Executors.newSingleThreadScheduledExecutor();
            //设备状态检测定时任务
            stateChanedService.scheduleWithFixedDelay(new DeviceStateTask(), 5000, 3000, TimeUnit.MILLISECONDS);

            Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
                @Override
                public void run() {
                    pingService.shutdownNow();
                    stateChanedService.shutdownNow();
                }
            }));
        } catch (Exception ex) {
            logger.error("start client failed !", ex);
        }
    }

    private static void registAndLogin(UhostModel uhostModel,VPushClient vPushClient) {
        //状态初始化
        isLogin = true;
        MessageSender.setToken(null);
        MessageSender.setIsOnline(false);
        long current = System.currentTimeMillis();
        try {
            while (true) {
                if (StringUtils.isEmpty(MessageSender.getToken())) {
                    /*
                    * 重连获取新的channel管道，并确认channel为活跃状态
                    * client每3秒发一次重连请求
                    * */
                    Channel channel = null;
                    try {
                        channel = vPushClient.connect();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (channel != null){
                        logger.info("start connect remoteAddress:" + channel.remoteAddress() + "\n");
                        MessageSender.setChannel(channel);
                        MessageSender.register(uhostModel);
                        MessageSender.login();
                    }else{
                        logger.error("token is " + MessageSender.getToken());
                        logger.error("connect push server failed!，try to reconnect after 3 seconds...");
                    }
                    TimeUnit.MILLISECONDS.sleep(3000);
                } else {
                    logger.info("connect push server succeed! token is [" + MessageSender.getToken() + "]");
                    break;
                }
            }


            if (StringUtils.isBlank(MessageSender.getToken())) {
                throw new RuntimeException("register uhost " + JsonUtil.bean2Json(uhostModel) + " failed ! ");
            }


            current = System.currentTimeMillis();
            MessageSender.login();
            while (System.currentTimeMillis() - current <= 30 * 1000) {
                if (StringUtils.isEmpty(MessageSender.getToken())) {
                    TimeUnit.MILLISECONDS.sleep(3000);
                } else {
                    break;
                }
            }

            if (!MessageSender.isOnline()) {
                throw new RuntimeException("login uhost " + JsonUtil.bean2Json(uhostModel) + " failed ! ");
            }
            isLogin = false;

        } catch (InterruptedException ex) {
            isLogin = false;
            logger.error("register or login interrupted !!!", ex);
            throw new RuntimeException(ex);
        }
    }
}