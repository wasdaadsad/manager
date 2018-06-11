package com.vivo.uhost.protocol.client;


import com.vivo.uhost.comm.util.StringUtil;
import com.vivo.uhost.protocol.client.handler.VPushClientHandler;
import com.vivo.uhost.protocol.core.service.IFactory;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.bytes.ByteArrayDecoder;
import io.netty.handler.codec.bytes.ByteArrayEncoder;

public class AbstractClient {

	private String host;
	private int port;
	private IFactory serviceFactory;

	public AbstractClient() {
	}

	public AbstractClient(String host, int port) {
		this.host = host;
		this.port = port;
	}

	public AbstractClient(String host, int port, IFactory serviceFactory) {
		this.host = host;
		this.port = port;
		this.serviceFactory = serviceFactory;
	}

	private Bootstrap getBootstrap() {
		Bootstrap b = new Bootstrap();
		try {
			if(serviceFactory == null){
				serviceFactory = IFactory.DEFAULT_FACTORY;
			}

			EventLoopGroup group = new NioEventLoopGroup();
			b.option(ChannelOption.SO_KEEPALIVE, true);
			b.option(ChannelOption.TCP_NODELAY, true);
			b.group(group).channel(NioSocketChannel.class);
			b.handler(new ChannelInitializer<Channel>() {
				@Override
				protected void initChannel(Channel ch) throws Exception {
					ChannelPipeline pipeline = ch.pipeline();
					pipeline.addLast("frameDecoder", new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));
					pipeline.addLast("bytesDecoder", new ByteArrayDecoder());
					pipeline.addLast("frameEncoder", new LengthFieldPrepender(4, false));
					pipeline.addLast("bytesEncoder", new ByteArrayEncoder());
					pipeline.addLast("handler", new VPushClientHandler(serviceFactory));
				}
			});
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return b;
	}

	public Channel connect() throws Exception {
		if(StringUtil.isBlank(host) || port == 0){
			throw new IllegalArgumentException("host or port is null!");
		}

		return getBootstrap().connect(host, port).sync().channel();
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public IFactory getServiceFactory() {
		return serviceFactory;
	}

	public void setServiceFactory(IFactory serviceFactory) {
		this.serviceFactory = serviceFactory;
	}
}
