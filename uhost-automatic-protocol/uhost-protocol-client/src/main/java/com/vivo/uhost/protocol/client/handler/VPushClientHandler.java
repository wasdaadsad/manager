package com.vivo.uhost.protocol.client.handler;

import com.bbkmobile.iqoo.common.util.SpringBeanUtil;
import com.vivo.uhost.message.Message;
import com.vivo.uhost.message.codec.PackageDecoder;
import com.vivo.uhost.message.codec.PackageEncoder;
import com.vivo.uhost.message.model.UhostModel;
import com.vivo.uhost.protocol.client.MessageSender;
import com.vivo.uhost.protocol.core.service.IFactory;
import com.vivo.uhost.protocol.core.service.IService;
import com.vivo.uhost.protocol.redis.mq.topic.RedisQProducer;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;

@Sharable
public class VPushClientHandler extends ChannelInboundHandlerAdapter {

	private static Logger logger = LoggerFactory.getLogger(VPushClientHandler.class);

	private IFactory serviceFactory;

	public VPushClientHandler(IFactory serviceFactory){
		this.serviceFactory = serviceFactory;
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		logger.info("connect. channel.hashCode: {}", ctx.channel().hashCode());
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) {
		try {
			byte[] b = (byte[]) msg;
			Message receiveMsg = PackageDecoder.decode(b);
			IService service = serviceFactory.getService(ctx, receiveMsg);
			Message message = service.processMsg(ctx, receiveMsg);
			if(message != null){
				ack(ctx, message);
			}
			ReferenceCountUtil.release(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void ack(ChannelHandlerContext ctx, Message message) {
		try {
			byte[] bytes = PackageEncoder.encode(message);
			ByteBuf data = ctx.alloc().buffer(bytes.length);
			data.writeBytes(bytes);
			ChannelFuture cf = ctx.channel().writeAndFlush(data);
			if (cf.isDone() && cf.cause() != null) {
				cf.cause().printStackTrace();
				ctx.close();
			}
		} catch (Exception e) {
			logger.error("ack failed!", e);
		}
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		super.channelReadComplete(ctx);
		ctx.flush();
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		MessageSender.setIsOnline(false);
		InetSocketAddress insocket = (InetSocketAddress) ctx.channel().remoteAddress();
		String serverIp = insocket.getAddress().getHostAddress();//获取远程server的ip地址
		logger.info("所连接server异常断开，地址为：" + serverIp + "  请从数据库更新Uhost和手机状态，稍后尝试重连...");
		RedisQProducer redisQProducer = SpringBeanUtil.getBean("redisQProducer", RedisQProducer.class);
		Map<String, Object> params = new HashMap<>();
		params.put("serverIp", serverIp);
		params.put("token", MessageSender.getToken());
		redisQProducer.sendServerBreakDown(params);
		cause.printStackTrace();
		ctx.close();
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		logger.info("server当前不活跃");
		ctx.fireChannelInactive();
	}

	public IFactory getServiceFactory() {
		return serviceFactory;
	}

	public void setServiceFactory(IFactory serviceFactory) {
		this.serviceFactory = serviceFactory;
	}
}
