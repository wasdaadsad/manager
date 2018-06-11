package com.vivo.uhost.message;


import com.vivo.uhost.comm.util.Constants;
import com.vivo.uhost.comm.util.StringUtil;

import java.io.Serializable;

public class Message implements Serializable{
	
	private Integer cmdType;
	private String msg = StringUtil.EMPTY;

	public Message() {
	}

	public Message(Integer cmdType){
		this.cmdType = cmdType;
	}

	public Message(Integer cmdType, String msg){
		this.cmdType = cmdType;
		this.msg = StringUtil.trim(msg);
	}

	public static final Message INVALID_MESSAGE = new Message(Constants.CMD_TYPE_INVALID);
	public static final Message INVALID_TOKEN_MESSAGE = new Message(Constants.CMD_TYPE_INVALID_TOKEN);
	public static final Message PING_ACK_MESSAGE = new Message(Constants.CMD_TYPE_PING_ACK);

	public Integer getCmdType() {
		return cmdType;
	}

	public Message setCmdType(Integer cmdType) {
		this.cmdType = cmdType;
		return this;
	}

	public String getMsg() {
		return msg;
	}

	public Message setMsg(String msg) {
		this.msg = msg;
		return this;
	}

	@Override
	public String toString() {
		return "Message{" +
				"cmdType=" + cmdType +
				", msg='" + msg + '\'' +
				'}';
	}
}
