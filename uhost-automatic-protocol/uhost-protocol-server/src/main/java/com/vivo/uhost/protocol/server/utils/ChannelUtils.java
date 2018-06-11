package com.vivo.uhost.protocol.server.utils;

import io.netty.channel.Channel;

import java.net.InetAddress;
import java.net.InetSocketAddress;

public class ChannelUtils {
	
	/**
	 * the raw IP address in a string format.
	 * @param channel
	 * @return
	 */
    public static String getRemoteHostAddress(Channel channel){
        InetSocketAddress address = (InetSocketAddress) channel.remoteAddress();
        InetAddress ip = address.getAddress();
        return ip.getHostAddress();
    }
}
