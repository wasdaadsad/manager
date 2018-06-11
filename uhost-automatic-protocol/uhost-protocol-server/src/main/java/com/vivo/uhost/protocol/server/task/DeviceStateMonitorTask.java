package com.vivo.uhost.protocol.server.task;

import com.vivo.internet.vivocfg.client.VivoConfigManager;
import com.vivo.uhost.comm.util.DateUtils;
import com.vivo.uhost.comm.util.StringUtil;
import com.vivo.uhost.protocol.core.model.ChannelInfo;
import com.vivo.uhost.protocol.redis.RedisDao;
import com.vivo.uhost.protocol.server.service.IUhostStateChangeService;
import com.vivo.uhost.protocol.server.utils.RedisKeyUtils;
import com.vivo.uhost.protocol.server.utils.ServerConstants;
import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Title:
 * @Description:
 * @Author:chengen
 * @Since:15-7-8
 * @Modified By:
 * @Modified Date:
 * @Why &amp; What is modified:
 * @Version:1.0
 */
@Service("deviceStateMonitorTask")
public class DeviceStateMonitorTask {

    private static final Logger logger = LoggerFactory.getLogger(DeviceStateMonitorTask.class);

    private static final Long TIME_OUT = VivoConfigManager.getLong("ping.time.out.offline");

    @Autowired
    private RedisDao redisDao;

    @Autowired
    private IUhostStateChangeService uhostStateChangeService;


    /**
     * Monitor the device state.
     * If channel is off or timeout, inform the backend management systems via redis list.
     */
    public void monitor() {
        try {
            logger.info("==========device monitor start==========");
            if ((ServerConstants.MAP_TOKEN_CHANNEL == null || ServerConstants.MAP_TOKEN_CHANNEL.isEmpty())
                    && (ServerConstants.MAP_CHANNEL_CHANNELINFO == null || ServerConstants.MAP_CHANNEL_CHANNELINFO.isEmpty())) {
                return;
            }
            int channelSize = ServerConstants.MAP_CHANNEL_CHANNELINFO.size();
            int tokenSize = ServerConstants.MAP_TOKEN_CHANNEL.size();
            Map<Channel, ChannelInfo> tempChannelMap = new HashMap<Channel, ChannelInfo>();
            tempChannelMap.putAll(ServerConstants.MAP_CHANNEL_CHANNELINFO);
            Map<String, Channel> tempTokenMap = new HashMap<String, Channel>();
            tempTokenMap.putAll(ServerConstants.MAP_TOKEN_CHANNEL);
            for (Channel channel : tempChannelMap.keySet()) {
                ChannelInfo channelInfo = tempChannelMap.get(channel);
                Long pingTime = channelInfo.getPingTime();
                String token = channelInfo.getToken();
                if (!channel.isActive() || (System.currentTimeMillis() - pingTime) > TIME_OUT) {
                    // 超时
                    if (StringUtil.isNotBlank(token) && tempTokenMap.containsKey(token) && channel.equals(tempTokenMap.get(token))) {
                        ServerConstants.MAP_TOKEN_CHANNEL.remove(token);  //remove timeout or inactive channel
                        String tokenKey = RedisKeyUtils.getUhostKey(token);
                        Map<String, String> map = redisDao.entries(tokenKey);
                        if (map == null || map.isEmpty()) {
                            return;
                        }
                        redisDao.hashAdd(tokenKey, "lastLogout", DateUtils.format(new Date(), DateUtils.FORMAT_YMDHMS));
                        uhostStateChangeService.uhostStateChange(token, map.get("uidentify"), 0);
                    }
                    channel.close();
                    ServerConstants.MAP_CHANNEL_CHANNELINFO.remove(channel);
                }
            }
            tempChannelMap.clear();
            tempTokenMap.clear();
            logger.info("==========device monitor end==========channel: {}=>{}, token: {}=>{}"
                    , channelSize, ServerConstants.MAP_CHANNEL_CHANNELINFO.size(), tokenSize, ServerConstants.MAP_TOKEN_CHANNEL.size());
        } catch (Exception e) {
            logger.error("device monitor error!", e);
        }
    }
}
