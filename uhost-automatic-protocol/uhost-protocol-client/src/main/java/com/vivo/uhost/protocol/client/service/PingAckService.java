package com.vivo.uhost.protocol.client.service;

import com.vivo.uhost.message.Message;
import com.vivo.uhost.protocol.core.service.IService;
import io.netty.channel.ChannelHandlerContext;

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
public class PingAckService implements IService {
    @Override
    public Message processMsg(ChannelHandlerContext ctx, Message receiveMsg) {
        return null;
    }
}
