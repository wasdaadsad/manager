/**
 * @Copyright:Copyright © VIVO Communication Technology Co., Ltd. All rights reserved.
 * @Company:http://www.vivo.com.cn/
 */
package com.vivo.uhost.core.util;

import com.vivo.internet.vivocfg.client.VivoConfigManager;
import org.omg.PortableInterceptor.INACTIVE;

/**
 * @author huangxiaoqun  2017/11/15 20:42
 * @version 1.0
 * @description
 */
public class CfgContants {


    //印度域名
    public static final String india_domain_url = VivoConfigManager.get("india_domain_url", "http://as.in.vivo.xyz:8088/");

    //外销域名
    public static final String foreign_domain_url = VivoConfigManager.get("foreign_domain_url", "http://as.ft.vivo.xyz:8088/");

    //检测报告的路径
    public static final String CHECK_REPORT_PATH = VivoConfigManager.get("uhost.checkreport.uploadpath", "/cdn/sda/");

    //用户日志报告
    public static final String USER_LOG_PATH = VivoConfigManager.get("uhost.userlog.uploadpath", "/cdn/sda/userlog/");

    //缓存超时时间
    public static final Long CACHE_EXPIRE_COMMON = VivoConfigManager.getLong("CACHE_EXPIRE_VENDOR", 24);

    //区域信息缓存时间
    public static final Long CSS_POINTS_EXPIRE_VENDOR = VivoConfigManager.getLong("CSS_POINTS_EXPIRE_VENDOR", 1);

    //网点信息缓存时间
    public static final Long POINTS_CHECK_EXPIRE_VENDOR = VivoConfigManager.getLong("POINTS_CHECK_EXPIRE_VENDOR", 60);

    public static final String SDA_FILE_KEY = VivoConfigManager.get("SDA_FILE_KEY", "sda_aes_1125");

    public static final String endpoint = VivoConfigManager.getString("","http://vivofs-api-inner.vivoglobal.com");

    public static final String appKey = VivoConfigManager.get("appKey","k1dYLLLR1jViTZjg");

    public static final String appSecret = VivoConfigManager.get("appSecret","092a1b82650432985c0c7da3c8f13aa9");

    public static final String request_username = VivoConfigManager.get("request_username", "vivo");

    public static final String request_password = VivoConfigManager.get("request_password", "vivo_20150504");

    //默认的缓存天数
    public static final Integer REPORT_CHECK_CACHE = VivoConfigManager.getInteger("REPORT_CHECK_CACHE", 3);

    public static final String VCRM_REQUEST_URL = VivoConfigManager.get("VCRM_REQUEST_URL", "http://kf.vivo.xyz:8055/");

    //设备测试明细状态
    //状态:未开始
    public static final Integer DEVICE_PROCESS_DETAIL_NOSTART = 0;
    //状态:运行中
    public static final Integer DEVICE_PROCESS_DETAIL_RUNNING = 1;

    //工具状态
    public  static final Integer TOOL_STATE_VALID = 1;//有效

    public  static final Integer TOOL_STATE_INVALID = 0;//无效

    //工具版本状态
    public static final Integer TOOL_VERSION_VALID = 1;//有效

    public static final Integer TOOL_VERSION_INVALID = 0;//无效

    //设备在线状态
    public static final Integer DEVICE_ONLINE = 1;//在线

    public static final Integer DEVICE_NOT_ONLINE = 0;//离线

    //设备测试状态
    public static final Integer DEVICE_STATUS_RUN = 3;//运行

    public static final Integer DEVICE_STATUS_OK = 1;//测试ok

    public static final Integer DEVICE_STATUS_FAIL = 2;//测试失败

    public static final Integer DEVICE_STATUS_NO = 0;//为开始

    // push type
    /** 系统更新 */
    public static final Integer PUSH_TYPE_UPDATE = 11;

    /**下载并安装APK*/
    public static final Integer PUSH_TYPE_INSTALL = 12;

    /** 启动测试 */
    public static final Integer PUSH_TYPE_RUN_TEST = 13;

    /** 出厂系统更新 */
    public static final Integer PUSH_TYPE_FACTORY_UPDATE = 14;

    /** 恢复出厂设置*/
    public static final Integer PUSH_TYPE_FACTORY_RESET = 15;

    /** 自动化点检*/
    public static final Integer PUSH_TYPE_AUTOMATION = 16;

    /** 测试*/
    public static final Integer PUSH_TYPE_TEST = 20;

    //状态
    public static final Integer STATE_INVALID = 0;  //状态无效

    public static final Integer STATE_VALID = 1;  //状态有效

    /** 状态-未开始  */
    public static final Integer STATE_TASK_NOT_RUN = 10;
    /** 状态-运行中 */
    public static final Integer STATE_TASK_RUNNING = 11;
    /** 状态-已关闭 */
    public static final Integer STATE_TASK_CLOSED = 12;
    /** 状态-等待中 */
    public static final Integer STATE_TASK_END = 13;


    public static final String  CONSOLE_REDIS_TOPIC = "console:task:cmd";//reids 通信端口

    public static final String  TOPIC_PUSH_TASK = "topic:push:task";//发布消息给topic:push:task频道，给Uhsot消费


}
