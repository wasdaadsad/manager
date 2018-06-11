package com.vivo.uhost.protocol.core.service.impl;

import com.vivo.uhost.message.Message;
import com.vivo.uhost.protocol.core.service.IFactory;
import com.vivo.uhost.protocol.core.service.IService;
import io.netty.channel.ChannelHandlerContext;

import java.util.HashMap;
import java.util.Map;

public class ServiceFactory implements IFactory {

	private final Map<Integer, IService> SERVICE_POOL = new HashMap<Integer, IService>();

    @Override
	public ServiceFactory addService(Integer cmdType, IService service){
		SERVICE_POOL.put(cmdType, service);
		return this;
	}

	@Override
	public IService getService(ChannelHandlerContext ctx, Message receiveMsg) {
        IService service = null;
		if (receiveMsg != null && receiveMsg.getCmdType() != null) {
			service = SERVICE_POOL.get(receiveMsg.getCmdType());
		}

        if(service == null){
            service = IService.DEFAULT_SERVICE;
        }

		return service;
	}

}
