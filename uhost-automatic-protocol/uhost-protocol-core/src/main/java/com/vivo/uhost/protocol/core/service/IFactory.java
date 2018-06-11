package com.vivo.uhost.protocol.core.service;

import com.vivo.uhost.message.Message;
import com.vivo.uhost.protocol.core.service.impl.ServiceFactory;
import io.netty.channel.ChannelHandlerContext;

public interface IFactory {

	ServiceFactory addService(Integer cmdType, IService service);

	IService getService(ChannelHandlerContext ctx, Message receiveMsg);

	IFactory DEFAULT_FACTORY = new IFactory() {
		@Override
		public ServiceFactory addService(Integer cmdType, IService service) {
			return null;
		}

		@Override
		public IService getService(ChannelHandlerContext ctx, Message receiveMsg) {
			return IService.DEFAULT_SERVICE;
		}
	};
}
