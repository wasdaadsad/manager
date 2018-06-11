package com.vivo.uhost.protocol.server.utils;

import com.vivo.internet.vivocfg.client.VivoConfigManager;
import com.vivo.uhost.comm.util.NoLockAllConcurrentHashMap;
import com.vivo.uhost.protocol.core.model.ChannelInfo;
import io.netty.channel.Channel;

import java.util.Map;

/**
 * @Title:
 * @Description:
 * @Author:chengen
 * @Since:15-7-2
 * @Modified By:
 * @Modified Date:
 * @Why &amp; What is modified:
 * @Version:1.0
 */
public class ServerConstants {

    public static final Map<String, Channel> MAP_TOKEN_CHANNEL = new NoLockAllConcurrentHashMap<String, Channel>();

    public static final Map<Channel, ChannelInfo> MAP_CHANNEL_CHANNELINFO = new NoLockAllConcurrentHashMap<Channel, ChannelInfo>();

    public static final Integer ROLE_AUTH = 1;  //鉴权

    public static final Integer ROLE_SERVER = 2;  //服务

    public static final Integer ROLE_ALL = 3;  //鉴权&服务

    public volatile static String SERVER_HASH = "";

    public static final Integer SERVER_ROLE = VivoConfigManager.getInteger("server.role");

    public static final String SERVER_ADDRESS = VivoConfigManager.getString("server.address");

    public static final String SERVER_ALL_ADDRESS = VivoConfigManager.getString("server.all.address");

    public static final String API_HOST = VivoConfigManager.getString("api.host");

    public static final String API_DEVICE_REGISTER = VivoConfigManager.getString("api.device.register");

    public static final String API_SERVER_START = VivoConfigManager.getString("api.server.start");

    //uhost状态上报接口
    public static final String API_UHOST_STATE = VivoConfigManager.getString("api.uhost.state");

    public static final String UHOST_REDIS_TOKEN = "uhosts:{0}:{1}"; // tokens:{hashValue}:{token}

    public static final String DEVICE_REDIS_TOKEN = "devices:{0}:{1}"; // tokens:{hashValue}:{token}

    public static final String KEY_REDIS_MSG = "consume:msg:{0}"; // 消息内容：consume:msg:{appId}

    public static final String KEY_REDIS_SENDING_HASH = "consume:sending:hash"; // 正在发送的token：consume:sending:hash

    public static final String KEY_REDIS_SENT_SET = "consume:sent:{0}"; // 发送成功的token：consume:sent:{appId}
}
