package com.vivo.uhost.comm.web;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class BaseJsonResponse {
	public static final int OK = 200;
	
	public static final int SERVER_ERROR = 500;
	
	public static final int INVALID_PARAM = 400;
	
	private Integer stat;
	
	private String msg;
	
	@JsonInclude(Include.NON_NULL)
	private Object data;

	public static BaseJsonResponse generateResult(int state){
		return new BaseJsonResponse(state);
	}
	
	public static BaseJsonResponse ok(){
		return new BaseJsonResponse(OK);
	}
	
	/**
	 * 
	 * @param msg
	 * @return
	 */
	public static BaseJsonResponse ok(String msg){
		return new BaseJsonResponse(OK,null,msg);
	}
	
	/**
	 * 返回成功标识
	 * @param data 数据字段
	 * @param msg 附件信息字段
	 * @return
	 */
	public static BaseJsonResponse ok(Object data,String msg){
		return new BaseJsonResponse(OK,data,msg);
	}
	
	public static BaseJsonResponse serverError(){
		return new BaseJsonResponse(SERVER_ERROR);
	}
	
	public static BaseJsonResponse serverError(String msg){
		return new BaseJsonResponse(SERVER_ERROR,null,msg);
	}
	
	/**
	 * 返回参数错误标识
	 * @return
	 */
	public static BaseJsonResponse invalidParam(){
		return new BaseJsonResponse(INVALID_PARAM);
	}
	
	/**
	 * 
	 * @param msg 附加的信息
	 * @return
	 */
	public static BaseJsonResponse invalidParam(String msg){
		return new BaseJsonResponse(INVALID_PARAM,null,msg);
	}
	public BaseJsonResponse(){
		
	}
	
	public BaseJsonResponse(Integer stat,Object data, String msg) {
		this.stat = stat;
		this.msg = msg;
		this.data = data;
	}
	
	public BaseJsonResponse(int stat, Object data) {
		this(stat);
		this.data = data;
	}

	public BaseJsonResponse(int state){
		this.stat = state;
	}
	public Integer getStat() {
		return stat;
	}

	public void setStat(Integer stat) {
		this.stat = stat;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
	
}
