package com.vivo.uhost.comm.util;


import java.util.HashMap;
import java.util.Map;

public class Constants {

    public static final String DEFAULT_ENCODING = "UTF-8";
    public static final String STRING_HTTP = "http://";
    public static final String CONTENT_TYPE_JSON = "application/json";


    public static String UHOST_UPDATE_PACKAGE_PATH = "/home/htnice/updatePackage/";                           //uhost保存升级包的路径（注意linux的权限问题）
    public static final String PHONE_UPDATE_PACKAGE_PATH = "/storage/sdcard0/";                               //手机上T卡升级包的放置路径
    public static final String PHONE_APK_PACKAGE_PATH = "/data/app";                                          //手机上APK的放置路径
    public static final String UHOST_UPDATE_PACKAGE_PATH_WIN = "F:\\uhost\\updatePackage\\";                  //windowns电脑上升级包存放路径
    public static String UHOST_TEST_CASE_PATH = "/home/htnice/testCase/";                                     //uhost(linux)上自动化点检测试用例存放位置
    public static final String UHOST_TEST_CASE_PATH_WIN = "F:\\uhost\\testCase\\";                            //windowns电脑上自动化点检测试用例存放路径

    // cmd type, protocol method
    public static final Integer CMD_TYPE_VALID = 0; // 保留字，数据有效

    public static final Integer CMD_TYPE_INVALID = 1; // 保留字，数据无效

    //uhost注册
    public static final Integer CMD_TYPE_REGISTER = 2; // 注册

    //uhost注册回复
    public static final Integer CMD_TYPE_REGISTER_ACK = 20; // 注册回执

    public static final String PUSH_SERVER = "pushServer";

    //uhost login
    public static final Integer CMD_TYPE_LOGIN = 3; // 登录
    public static final Integer CMD_TYPE_LOGIN_ACK = 30; // 登录回执

    //心跳
    public static final Integer CMD_TYPE_PING = 4; // 心跳
    public static final Integer CMD_TYPE_PING_ACK = 40; // 心跳回执

    //任务消息
    public static final Integer CMD_TYPE_SEND = 5; // 发消息
    public static final Integer CMD_TYPE_SEND_ACK = 50; // 消息回执

    //device登录或者登出
    public static final Integer CMD_DEVICE_LOGIN = 6; // 登录
    public static final Integer CMD_DEVICE_LOGIN_ACK = 60; // 登录回执

    //device对应PC端口登录
    public static final Integer CMD_DEVICE_PORT_LOGIN = 7; //端口登录
    public static final Integer CMD_DEVICE_PORT_LOGIN_ACK = 70; // 端口登录回执

    public static final Integer CMD_TYPE_ERROR = 101; // 服务器异常
    public static final Integer CMD_TYPE_INVALID_TOKEN = 102; // 无效token

    // push type
    /**
     * 系统升级
     */
    public static final Integer PUSH_TYPE_UPDATE = 11;

    /**
     * 下载并安装APK
     */
    public static final Integer PUSH_TYPE_INSTALL = 12;
    /**
     * 启动测试
     */
    public static final Integer PUSH_TYPE_RUN_TEST = 13;
    /**
     * 出厂系统更新
     */
    public static final Integer PUSH_TYPE_FACTORY_UPDATE = 14;
    /**
     * 恢复出厂设置
     */
    public static final Integer PUSH_TYPE_FACTORY_RESET = 15;
    /**
     * 自动化点检
     */
    public static final Integer PUSH_TYPE_AUTOMAT_TEST = 16;
    /**
     * 测试组（测试任务的父级）
     */
    public static final Integer PUSH_TYPE_TEST_GROUP = 20;

    public static final String COMMENT_PKG_NAME = "pkgName";
    public static final String COMMENT_PKG_CONFIG = "pkgConfig";

    public static final String SIGN_COMMA = ",";
    public static final String SIGN_BLANK = " ";
    public static final String SIGN_SEMICOLON = ";";
    public static final String SIGN_UNDERLINE = "_";
    public static final String SIGN_MINUS = "-";
    public static final String SIGN_PERCENT = "%";
    public static final String SIGN_OBLIQUELINE = "/";
    public static final String SIGN_COLON = ":";
    public static final String SIGN_POINT = ".";
    public static final String SIGN_QUERY = "?";
    public static final String SIGN_AT = "@";
    public static final String SIGN_EQUALS = "=";
    public static final String SIGN_LT = "<";
    public static final String SIGN_GT = ">";
    public static final String SIGN_OR = "|";
    public static final String SIGN_BRACKET_L = "[";
    public static final String SIGN_BRACKET_R = "]";

    public static final String TEMP_SUFFIX = "_tmp";

    public static final Integer STATE_OFFLINE = 0;  //状态-离线
    public static final Integer STATE_ONLINE = 1;  //状态-在线

    public static final Integer LEVEL_ONE = 1;  //级别-1级
    public static final Integer LEVEL_TWO = 2;  //级别-2级

    /**
     * 状态-任务已推送
     */
    public static final Integer STATE_TASK_PUSHED = 0;

    /**
     * 状态-处理成功/全部成功
     */
    public static final Integer STATE_TASK_SUCCESS = 1;

    /**
     * 状态-处理失败
     */
    public static final Integer STATE_TASK_FAILED = 2;

    /**
     * 状态-已就绪（升级包下载成功或测试启动成功）
     */
    public static final Integer STATE_TASK_READY = 3;

    /**
     * 状态-任务推送失败
     */
    public static final Integer STATE_TASK_PUSH_FAILED = 4;

    /**
     * 状态-未开始
     */
    public static final Integer STATE_TASK_NOT_RUN = 10;

    /**
     * 状态-运行中
     */
    public static final Integer STATE_TASK_RUNNING = 11;

    /**
     * 状态-已关闭
     */
    public static final Integer STATE_TASK_CLOSED = 12;

    /**
     * 状态-等待中
     */
    public static final Integer STATE_TASK_WAITING = 13;

    /**
     * 状态-测试开始
     */
    public static final Integer STATE_TEST_START = 0;

    /**
     * 状态-测试成功
     */
    public static final Integer STATE_TEST_SUCCESS = 1;

    /**
     * 状态-测试失败
     */
    public static final Integer STATE_TEST_FAILED = 2;

    /**
     * 状态-测试完成
     */
    public static final Integer STATE_TEST_FINISHED = 3;

    /**
     * 状态-测试超时
     */
    public static final Integer STATE_TEST_TIMEOUT = 4;  //

    private static final Map<Integer, String> STATE_TEST_MAP = new HashMap<Integer, String>();

    public static final String getTestState(Integer testState) {
        return STATE_TEST_MAP.get(testState);
    }

    static {
        STATE_TEST_MAP.put(STATE_TEST_SUCCESS, "运行中");
        STATE_TEST_MAP.put(STATE_TEST_FAILED, "失败");
        STATE_TEST_MAP.put(STATE_TEST_FINISHED, "已完成");
        STATE_TEST_MAP.put(STATE_TEST_TIMEOUT, "超时");
    }

    public static final Integer STATE_DELETE = -1;  //状态删除
    public static final Integer STATE_INVALID = 0;  //状态无效
    public static final Integer STATE_VALID = 1;  //状态有效


    public static final String REFRESH_DEVICE = "device";                   //刷新device列表
    public static final String REFRESH_UHOST = "uhost";                     //刷新uhost列表
    public static final String REFRESH_DEVICE_UHOST = "device_uhost";       //刷新device和uhsot列表
    public static final String REFRESH_TASK_STATE = "taskState";            //刷新任务状态列表和流程状态列表

}