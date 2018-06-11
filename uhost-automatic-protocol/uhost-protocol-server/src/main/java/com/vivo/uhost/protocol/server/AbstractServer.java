package com.vivo.uhost.protocol.server;


import com.vivo.internet.vivocfg.client.VivoConfigManager;
import com.vivo.uhost.protocol.core.service.IFactory;
import com.vivo.uhost.protocol.server.handler.VPushServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.bytes.ByteArrayDecoder;
import io.netty.handler.codec.bytes.ByteArrayEncoder;


public class AbstractServer {

	private static final int actSize = VivoConfigManager.getInteger("server.actors", Runtime.getRuntime().availableProcessors());
	private static final int workerSize = VivoConfigManager.getInteger("server.workers", Runtime.getRuntime().availableProcessors() * 2);

	private IFactory serviceFactory;

	public AbstractServer() {
	}

	public AbstractServer(IFactory serviceFactory){
		this.serviceFactory = serviceFactory;
	}
	
	protected ChannelHandler getChannelHandler(){
		if(serviceFactory == null){
			serviceFactory = IFactory.DEFAULT_FACTORY;
		}

		return new ChannelInitializer<SocketChannel>() {
			@Override
			public void initChannel(SocketChannel ch) throws Exception {

				ChannelPipeline pipeline = ch.pipeline();
				pipeline.addLast("frameDecoder", new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));
				pipeline.addLast("bytesDecoder", new ByteArrayDecoder());
				pipeline.addLast("frameEncoder", new LengthFieldPrepender(4, false));
				pipeline.addLast("bytesEncoder", new ByteArrayEncoder());
				pipeline.addLast("handler", new VPushServerHandler(serviceFactory));
			}
		};
	}
	
	protected void bind(int port){
		EventLoopGroup parentGroup = new NioEventLoopGroup(actSize); // 用于接收发来的连接请求
		EventLoopGroup childGroup = new NioEventLoopGroup(workerSize); // 用于处理parentGroup接收并注册给child的连接中的信息
		try {
			ServerBootstrap serverBootstrap = new ServerBootstrap();
			serverBootstrap.group(parentGroup, childGroup).channel(NioServerSocketChannel.class)
					.childHandler(getChannelHandler());

			serverBootstrap.option(ChannelOption.SO_KEEPALIVE, true);
			serverBootstrap.option(ChannelOption.TCP_NODELAY, true);
			serverBootstrap.option(ChannelOption.SO_REUSEADDR, true);

			ChannelFuture f = serverBootstrap.bind(port).sync();

			f.channel().closeFuture().sync();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			childGroup.shutdownGracefully();
			parentGroup.shutdownGracefully();
		}
	}

	public IFactory getServiceFactory() {
		return serviceFactory;
	}

	public void setServiceFactory(IFactory serviceFactory) {
		this.serviceFactory = serviceFactory;
	}
}
