package com.vivo.uhost.protocol.server.utils;


import com.vivo.uhost.comm.util.StringUtil;

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
public class RedisKeyUtils {

    /**
     * 获取设备key
     *
     * @param token
     * @return
     */
    public static String getUhostKey(String token) {
        int flag = Integer.parseInt(token.substring(0, 1), 16) % 16;
        return StringUtil.buildString(ServerConstants.UHOST_REDIS_TOKEN, flag, token);
    }

    /**
     * 获取设备key
     *
     * @param emmcid 设备对象
     * @return 设备key
     */
    public static String getDeviceKey(String emmcid) {
        int flag = Integer.parseInt(emmcid.substring(0, 1), 16) % 16;
        return StringUtil.buildString(ServerConstants.DEVICE_REDIS_TOKEN, flag, emmcid);
    }

}
