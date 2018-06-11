/**
 * @Copyright:Copyright © VIVO Communication Technology Co., Ltd. All rights reserved.
 * @Company:http://www.vivo.com.cn/
 */
package com.vivo.uhost.protocol.server.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.bbkmobile.iqoo.common.util.JsonUtil;
import com.bbkmobile.iqoo.common.util.SpringBeanUtil;
import com.vivo.uhost.comm.util.Constants;
import com.vivo.uhost.dal.entity.ServerNode;
import com.vivo.uhost.dal.entity.UhostInfo;
import com.vivo.uhost.dal.redis.IRedisService;
import com.vivo.uhost.comm.websocket.WebSocketServer;
import com.vivo.uhost.protocol.redis.mq.topic.RedisQProducer;
import com.vivo.uhost.protocol.server.service.IUhostStateChangeService;
import com.vivo.uhost.protocol.server.utils.HttpUtils;
import com.vivo.uhost.protocol.server.utils.ServerConstants;
import com.vivo.uhost.service.IUhostInfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;

/**
 * @author huangxiaoqun  2018/3/26 21:03
 * @version 1.0
 * @description
 */
@Service("uhostStateChangeService")
public class UhostStateChangeServiceImpl implements IUhostStateChangeService {

    private static final Object lock = new Object();


    @Autowired
    private IRedisService redisService;

    @Autowired
    private IUhostInfoService uhostInfoService;

    /**
     * uhost状态变更
     *
     * @param token
     * @param uidentify
     * @param state
     */
    public void uhostStateChange(String token, String uidentify, Integer state) {

        synchronized (lock) {
            String hashValue = redisService.hget(Constants.PUSH_SERVER, ServerConstants.SERVER_HASH);
            if (StringUtils.isNotBlank(hashValue)) {
                Integer connectCount = null;
                JSONObject jsonObject = JSONObject.parseObject(hashValue);
                String serverAddress = jsonObject.getString("serverAddress");
                Integer port = jsonObject.getInteger("tcpPort");
                if (state == 2) {
                    connectCount = jsonObject.getInteger("connectCount") + 1;
                } else {
                    connectCount = jsonObject.getInteger("connectCount") - 1;
                }
                if (connectCount < 0) {
                    connectCount = 0;
                }
                ServerNode serverNode = new ServerNode(serverAddress, port, connectCount);
                redisService.hashSet(Constants.PUSH_SERVER, ServerConstants.SERVER_HASH, JsonUtil.bean2Json(serverNode));
            }
        }

        //更新本地数据库
        UhostInfo uhostInfo = uhostInfoService.findByIdentify(uidentify);
        uhostInfo.setToken(token);
        uhostInfo.setStatus(state);
        uhostInfo.setUpdateTime(new Date());
        uhostInfoService.editUhost(uhostInfo);
        //通知浏览器刷新uhost列表
        RedisQProducer redisQProducer = SpringBeanUtil.getBean("redisQProducer", RedisQProducer.class);
        redisQProducer.sendPageRefresh(Constants.REFRESH_UHOST);
        //调用API，更新远程主库
        HttpUtils.reportApiServer(ServerConstants.API_UHOST_STATE, JsonUtil.bean2Json(uhostInfo));
    }

}
