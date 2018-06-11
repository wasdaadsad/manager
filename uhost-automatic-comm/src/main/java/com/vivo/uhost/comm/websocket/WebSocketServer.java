package com.vivo.uhost.comm.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @Author: DongJiaJin
 * @Description:
 * 	websocket服务器，用于与浏览器通信，@ServerEndpoint 注解是一个类层次的注解，
 * 	它的功能主要是将目前的类定义成一个websocket服务器端,
 * 	注解的值将被用于监听用户连接的终端访问URL地址,客户端可以通过这个URL来连接到WebSocket服务器端。
 * @Date: Created in 19:37 2018/5/5
 * @Modified By:
 */
@ServerEndpoint("/refresh")
public class WebSocketServer {

	private static Logger logger = LoggerFactory.getLogger(WebSocketServer.class);
	private static int onlineCount = 0;
	private static CopyOnWriteArraySet<WebSocketServer> webSocketSet = new CopyOnWriteArraySet<WebSocketServer>();
	private Session session;

	/**
	 * 连接建立成功调用的方法
	 * @param session  可选的参数。session为与某个客户端的连接会话，需要通过它来给客户端发送数据
	 */
	@OnOpen
	public void onOpen(Session session){
		this.session = session;
		webSocketSet.add(this);
		addOnlineCount();
		logger.info("有新连接加入！当前在线客户端数量为" + getOnlineCount());
	}

	/**
	 * 连接关闭调用的方法
	 */
	@OnClose
	public void onClose(){
		webSocketSet.remove(this);
		subOnlineCount();
		logger.info("有一连接关闭！当前在线人数为" + getOnlineCount());
	}

	/**
	 * 收到客户端消息后调用的方法
	 * @param message 客户端发送过来的消息
	 * @param session 可选的参数
	 */
	@OnMessage
	public void onMessage(String message, Session session) {
		logger.info("来自客户端的消息:" + message);
		//群发消息
		for(WebSocketServer item: webSocketSet){
			try {
				item.sendMessage(message);
			} catch (IOException e) {
				e.printStackTrace();
				continue;
			}
		}
	}

	/**
	 * 发生错误时调用
	 * @param session
	 * @param error
	 */
	@OnError
	public void onError(Session session, Throwable error){
		logger.info("发生错误");
		error.printStackTrace();
	}

	/**
	 * 这个方法与上面几个方法不一样。没有用注解，是根据自己需要添加的方法。
	 * @param message
	 * @throws IOException
	 */
	private void sendMessage(String message) throws IOException{
		this.session.getBasicRemote().sendText(message);
		//this.session.getAsyncRemote().sendText(message);
	}


	public void sendToAllMessage(String message) throws IOException{
		for(WebSocketServer item: webSocketSet){//循环发消息给所有的浏览器
			try {
				item.sendMessage(message);
			} catch (IOException e) {
				e.printStackTrace();
				continue;
			}
		}
	}


	private static synchronized int getOnlineCount() {
		return onlineCount;
	}

	private static synchronized void addOnlineCount() {
		WebSocketServer.onlineCount++;
	}

	private static synchronized void subOnlineCount() {
		WebSocketServer.onlineCount--;
	}
}
