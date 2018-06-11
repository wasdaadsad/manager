package com.vivo.uhost.protocol.core.model.http;

import com.vivo.internet.vivocfg.client.VivoConfigManager;
import com.vivo.uhost.comm.util.JsonUtils;
import com.vivo.uhost.comm.util.StringUtil;
import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Title:
 * @Description:
 * @Author:chengen
 * @Since:15-7-2
 * @Modified By:
 * @Modified Date:
 * @Why &amp; What is modified:
 * @Version:1.0
 */
public class WebResult<T> {

    protected static final SimpleDateFormat SDF = new SimpleDateFormat("yyyyMMddHHmmss");

    public static final Integer CODE_SUCCESS = 0; // 成功
    public static final Integer CODE_EXCEPTION = -1; // 程序异常
    public static final Integer CODE_FAILED = 11; // 失败
    public static final Integer CODE_PARAM_INVALID = 12; // 参数无效
    public static final Integer CODE_INVALID = 13; // 请求无效
    public static final Integer CODE_NOT_FOND = 14; // 没有数据
    public static final Integer CODE_NOT_LOGIN = 15; // 没有数据

    public static final String CODE_PREFIX = "CODE_";

    public static final String MSG_SUCCESS = VivoConfigManager.getString(CODE_PREFIX + CODE_SUCCESS, StringUtil.EMPTY);
    public static final String MSG_EXCEPTION = VivoConfigManager.getString(CODE_PREFIX + CODE_EXCEPTION, StringUtil.EMPTY);
    public static final String MSG_FAILED = VivoConfigManager.getString(CODE_PREFIX + CODE_FAILED, StringUtil.EMPTY);
    public static final String MSG_PARAM_INVALID = VivoConfigManager.getString(CODE_PREFIX + CODE_PARAM_INVALID, StringUtil.EMPTY);
    public static final String MSG_INVALID = VivoConfigManager.getString(CODE_PREFIX + CODE_INVALID, StringUtil.EMPTY);
    public static final String MSG_NOT_FOND = VivoConfigManager.getString(CODE_PREFIX + CODE_NOT_FOND, StringUtil.EMPTY);

    private Integer code;
    private T data;
    private String msg;
    private String timestamp;

    public WebResult() {
    }

    public WebResult(Integer code, String msg, String timestamp) {
        this.code = code;
        this.msg = msg;
        this.timestamp = timestamp;
    }

    public WebResult(Integer code, T data, String msg, String timestamp) {
        this.code = code;
        this.data = data;
        this.msg = msg;
        this.timestamp = timestamp;
    }

    public static String statusOk() {
        return JsonUtils.toJson(new WebResult(CODE_SUCCESS, MSG_SUCCESS, SDF.format(new Date())));
    }

    public static String statusOk(Object data) {
        return JsonUtils.toJson(new WebResult<Object>(CODE_SUCCESS, data, MSG_SUCCESS, SDF.format(new Date())));
    }

    public static String statusFailed() {
        return JsonUtils.toJson(new WebResult(CODE_FAILED, MSG_FAILED, SDF.format(new Date())));
    }

    public static String statusFailed(Integer code) {
        return JsonUtils.toJson(new WebResult(code, VivoConfigManager.getString(CODE_PREFIX + code, StringUtil.EMPTY)
                , SDF.format(new Date())));
    }

    public static String statusFailed(Integer code, String msg) {
        return JsonUtils.toJson(new WebResult(code, StringUtils.isBlank(msg)
                ? VivoConfigManager.getString(CODE_PREFIX + code, StringUtil.EMPTY) : msg
                , SDF.format(new Date())));
    }

    public static String statusInvalid() {
        return JsonUtils.toJson(new WebResult(CODE_INVALID, MSG_INVALID, SDF.format(new Date())));
    }

    public static String dataNotFound() {
        return JsonUtils.toJson(new WebResult(CODE_NOT_FOND, MSG_NOT_FOND, SDF.format(new Date())));
    }

    public static String parameterError() {
        return JsonUtils.toJson(new WebResult(CODE_PARAM_INVALID, MSG_PARAM_INVALID, SDF.format(new Date())));
    }

    public static String exceptionError() {
        return JsonUtils.toJson(new WebResult(CODE_EXCEPTION, MSG_EXCEPTION, SDF.format(new Date())));
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "WebResult{" +
                "code=" + code +
                ", data=" + data +
                ", msg='" + msg + '\'' +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }
}
