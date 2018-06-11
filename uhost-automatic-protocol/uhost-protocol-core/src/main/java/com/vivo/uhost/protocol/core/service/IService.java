package com.vivo.uhost.protocol.core.service;


import com.vivo.uhost.message.Message;
import io.netty.channel.ChannelHandlerContext;

public interface IService {

	Message processMsg(ChannelHandlerContext ctx, Message receiveMsg);

	IService DEFAULT_SERVICE = new IService() {
		@Override
		public Message processMsg(ChannelHandlerContext ctx, Message receiveMsg) {
			return null;
		}
	};
}