package com.vivo.uhost.protocol.redis;

/**
 * @Author: DongJiaJin
 * @Description:redis 推送给pushServer的管道信息配置，这里需要和server的vivo.properties对应
 * @Date: Created in 18:12 2018/4/17
 * @Modified By:
 */
public class RedisConstans {

    //设备状态topic  uhost -> pushSerevr
    public static final String REDIS_MQ_DEVICE_STATE = "mq:device:state";

    //端口号
    public static final String REDIS_MQ_UHOST_PORTNAME_STATE = "mq:uhost:portname:state";

    //任务进度topic  uhost -> pushSerevr
    public static final String REDIS_MQ_TASK_STATE = "mq:task:state";

    //console端测试任务topic  console --> pushServer
    public static final String REDIS_CONSOLE_TASK_CMD = "console:task:cmd";

    //测试任务topic  pushSerevr -> uhost
    public static final String REDIS_TOPIC_PUSH = "topic:push:task";

    //server崩溃后通知web修改状态   Uhost -> web
    public static final String REDIS_TOPIC_SERVER_BREAKDOWN = "topic:server:breakdown";

    //server修改数据库后要通知浏览器刷新页面   server -> web
    public static final String REDIS_TOPIC_SERVER_REFRESH = "topic:server:refresh";

}
