package com.vivo.uhost.protocol.core.model;

public class ChannelInfo {
	
	private Long pingTime;
	
	private String token;

	public ChannelInfo() {
	}

	public ChannelInfo(Long pingTime) {
		this.pingTime = pingTime;
	}

	public Long getPingTime() {
		return pingTime;
	}

	public void setPingTime(Long pingTime) {
		this.pingTime = pingTime;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
