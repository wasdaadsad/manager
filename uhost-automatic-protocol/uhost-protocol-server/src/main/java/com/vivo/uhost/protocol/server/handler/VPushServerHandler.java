package com.vivo.uhost.protocol.server.handler;

import com.bbkmobile.iqoo.common.util.SpringBeanUtil;
import com.vivo.uhost.comm.util.Constants;
import com.vivo.uhost.comm.util.StringUtil;
import com.vivo.uhost.comm.websocket.WebSocketServer;
import com.vivo.uhost.core.util.CfgContants;
import com.vivo.uhost.dal.entity.DeviceInfo;
import com.vivo.uhost.message.Message;
import com.vivo.uhost.message.codec.PackageDecoder;
import com.vivo.uhost.message.codec.PackageEncoder;
import com.vivo.uhost.protocol.core.model.ChannelInfo;
import com.vivo.uhost.protocol.core.service.IFactory;
import com.vivo.uhost.protocol.core.service.IService;
import com.vivo.uhost.protocol.redis.RedisDao;
import com.vivo.uhost.protocol.redis.mq.topic.RedisQProducer;
import com.vivo.uhost.protocol.server.service.IUhostStateChangeService;
import com.vivo.uhost.protocol.server.utils.ChannelUtils;
import com.vivo.uhost.protocol.server.utils.RedisKeyUtils;
import com.vivo.uhost.protocol.server.utils.ServerConstants;
import com.vivo.uhost.service.IDeviceService;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Sharable
public class VPushServerHandler extends ChannelInboundHandlerAdapter {

    private static Logger logger = LoggerFactory.getLogger(VPushServerHandler.class);

    private IFactory serviceFactory;

    public VPushServerHandler(IFactory serviceFactory) {
        this.serviceFactory = serviceFactory;
    }

    @Override
    public void channelActive(final ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        ServerConstants.MAP_CHANNEL_CHANNELINFO.put(channel, new ChannelInfo(System.currentTimeMillis()));
        logger.info("A client is connected. channel.hashCode: {}", channel.hashCode());
    }

    /**
     * 读取新消息
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        try {
            byte[] bytes = (byte[]) msg;
            Message receiveMsg = PackageDecoder.decode(bytes);
            logger.info("receive msg: {}", receiveMsg);

            IService service = serviceFactory.getService(ctx, receiveMsg);
            Message message = service.processMsg(ctx, receiveMsg);
            if (message != null) {
                ack(ctx, message);
            }
        } catch (Exception e) {
            logger.error("channelRead error. ", e);
            Message message = new Message(Constants.CMD_TYPE_ERROR);
            ack(ctx, message);
        } finally {
            ctx.fireChannelRead(msg);
        }
    }

    private void ack(ChannelHandlerContext ctx, Message message) {
        try {
            byte[] bytes = PackageEncoder.encode(message);
            ByteBuf data = ctx.alloc().buffer(bytes.length);
            data.writeBytes(bytes);
            ChannelFuture cf = ctx.channel().writeAndFlush(data);
            if (cf.isDone() && null != cf.cause()) {
                cf.cause().printStackTrace();
                ctx.close();
            }
        } catch (Exception e) {
            logger.error("ack failed!", e);
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
//		logger.info("channelReadComplete: " + ctx.channel().hashCode());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        logger.error("channelException", cause);
        ctx.close();
    }

    /**
     * 会话不活跃了 需要更新uhost的在线状态
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        ChannelInfo channelInfo = ServerConstants.MAP_CHANNEL_CHANNELINFO.get(channel);
        String token = null;
        Long pingTime = null;
        if (channelInfo != null) {
            token = channelInfo.getToken();
            pingTime = channelInfo.getPingTime();
        }
        logger.info("channelInactive: |remoteIp:{}|token:{}|pingTime:{}", ChannelUtils.getRemoteHostAddress(channel), token, pingTime);
        if (StringUtil.isNotBlank(token) && ServerConstants.MAP_TOKEN_CHANNEL.containsKey(token)) {
            Channel tokenChannel = ServerConstants.MAP_TOKEN_CHANNEL.get(token);
            if (tokenChannel != null && tokenChannel.equals(channel)) {
                ServerConstants.MAP_TOKEN_CHANNEL.remove(token);
                String tokneKey = RedisKeyUtils.getUhostKey(token);
                RedisDao redisDao = SpringBeanUtil.getBean("redisDao", RedisDao.class);
                Map<String, String> map = redisDao.entries(tokneKey);
                if (null == map || map.isEmpty()) {
                    return;
                }
                IUhostStateChangeService uhostStateChangeService = SpringBeanUtil.getBean("uhostStateChangeService", IUhostStateChangeService.class);
                uhostStateChangeService.uhostStateChange(token, map.get("uidentify"), 0);
                //Uhsot下线时，需要操作DB将设备状态改为离线
                IDeviceService deviceService = SpringBeanUtil.getBean("deviceService", IDeviceService.class);
                List<DeviceInfo> deviceInfoList = deviceService.selectListByToken(token);
                for (DeviceInfo deviceInfo  : deviceInfoList){
                    deviceService.setDeviceState(deviceInfo, CfgContants.DEVICE_NOT_ONLINE, token);
                }
                //通知浏览器刷新设备列表和uhsot列表哦
                RedisQProducer redisQProducer = SpringBeanUtil.getBean("redisQProducer", RedisQProducer.class);
                redisQProducer.sendPageRefresh(Constants.REFRESH_DEVICE_UHOST);
            }
            logger.info("close channel. MAP_TOKENS size: {}", ServerConstants.MAP_TOKEN_CHANNEL.size());
        }
        ServerConstants.MAP_CHANNEL_CHANNELINFO.remove(channel);
    }

    public IFactory getServiceFactory() {
        return serviceFactory;
    }

    public void setServiceFactory(IFactory serviceFactory) {
        this.serviceFactory = serviceFactory;
    }
}
