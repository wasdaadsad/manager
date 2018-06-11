package com.vivo.uhost.protocol.client;

import com.vivo.uhost.comm.util.Constants;
import com.vivo.uhost.comm.util.JsonUtils;
import com.vivo.uhost.comm.util.StringUtil;
import com.vivo.uhost.message.codec.PackageEncoder;
import com.vivo.uhost.message.model.*;
import io.netty.channel.Channel;

/**
 * @Title:
 * @Description:
 * @Author:chengen
 * @Since:15-6-25
 * @Modified By:
 * @Modified Date:
 * @Why &amp; What is modified:
 * @Version:1.0
 */
public class MessageSender {

    private static Channel channel;

    private volatile static String token;

    private volatile static boolean isOnline = false;

    /**
     * uhost注册到
     */
    public static void register(UhostModel uhostModel) {
        isOnline = false;
        token = null;
        if (channel.isActive()) {
            byte[] b = PackageEncoder.encode(Constants.CMD_TYPE_REGISTER, JsonUtils.toJson(uhostModel).getBytes());
            channel.writeAndFlush(b);
        }
    }

    public static void login() {
        if (channel.isActive() && StringUtil.isNotBlank(token)) {
            byte[] b = PackageEncoder.encode(Constants.CMD_TYPE_LOGIN, JsonUtils.toJson(new TokenMsg(token)).getBytes());
            channel.writeAndFlush(b);
        }
    }

    /**
     * uhost与server的心跳
     */
    public static void ping() {
        if (channel.isActive() && isOnline && StringUtil.isNotBlank(token)) {
            byte[] b = PackageEncoder.encode(Constants.CMD_TYPE_PING, JsonUtils.toJson(new TokenMsg(token)).getBytes());
            channel.writeAndFlush(b);
        }
    }


    /**
     * 上报任务完成状态
     *
     * @param pushTaskResp 任务执行结果
     */
    public static void report(PushTaskResp pushTaskResp) {
        if (channel.isActive() && isOnline && StringUtil.isNotBlank(token)) {
            byte[] b = PackageEncoder.encode(Constants.CMD_TYPE_SEND_ACK, JsonUtils.toJson(pushTaskResp).getBytes());
            channel.writeAndFlush(b);
        }
    }

    /**
     * Author:dongjiajin
     * 状态通过netty channel的事件监听来完成上报
     */
    public static void reportDeviceStateChange(DeviceStateChangeReq deviceStateChangeReq) {
        if (channel.isActive() && StringUtil.isNotBlank(token)) {
            byte[] b = PackageEncoder.encode(Constants.CMD_DEVICE_LOGIN, JsonUtils.toJson(deviceStateChangeReq).getBytes());
            channel.writeAndFlush(b);
        }
    }

    /**
     * Author:luzhiwei
     * 状态通过netty channel的事件监听来完成上报
     */
    public static void reportDevicePortNameStateChange(DevicePortNameChangeReq devicePortNameChangeReq) {
        if (channel.isActive() && StringUtil.isNotBlank(token)) {
            byte[] b = PackageEncoder.encode(Constants.CMD_DEVICE_PORT_LOGIN, JsonUtils.toJson(devicePortNameChangeReq).getBytes());
            channel.writeAndFlush(b);
        }
    }


    public static Channel getChannel() {
        return channel;
    }

    public static void setChannel(Channel channel) {
        MessageSender.channel = channel;
    }

    public static String getToken() {
        return token;
    }

    public static void setToken(String token) {
        MessageSender.token = token;
    }

    public static boolean isOnline() {
        return isOnline && (channel != null && channel.isActive() && channel.isWritable());
    }

    public static void setIsOnline(boolean isOnline) {
        MessageSender.isOnline = isOnline;
    }
}
