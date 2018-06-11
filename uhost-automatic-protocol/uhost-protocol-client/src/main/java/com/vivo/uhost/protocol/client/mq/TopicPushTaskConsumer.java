package com.vivo.uhost.protocol.client.mq;

import com.vivo.uhost.comm.thread.ThreadDistribution;
import com.vivo.uhost.comm.util.Constants;
import com.vivo.uhost.comm.util.JsonUtils;
import com.vivo.uhost.message.model.DeviceInfo;
import com.vivo.uhost.message.model.PushTaskReq;
import com.vivo.uhost.protocol.client.MessageSender;
import com.vivo.uhost.protocol.client.task.AutomatCheckProcessor;
import com.vivo.uhost.protocol.client.task.PushTaskProcessor;
import com.vivo.uhost.protocol.core.model.mq.PushNotice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

import java.util.ArrayList;
import java.util.List;

/**
 * uhost消费push任务topic
 */
public class TopicPushTaskConsumer implements MessageListener {

    private static Logger logger = LoggerFactory.getLogger(TopicPushTaskConsumer.class);

    @Override
    public void onMessage(Message message, byte[] pattern) {
        if (message == null) {
            return;
        }
        try {
            String msg = message.toString();
            PushNotice notice = JsonUtils.toObject(msg, PushNotice.class);
            Integer mId = notice.getMsgId();
            Integer pushType = notice.getPushType();
            String content = notice.getContent();
            String productName = notice.getProductName();
            String comment = notice.getComment();
            List<String> emmcidList = notice.getEmmcidList();
            List<DeviceInfo> deviceInfos = notice.getDeviceInfoList();

            String token = MessageSender.getToken();
            List<String> localEmmcidList = new ArrayList<>();
            List<DeviceInfo> localDeviceInfos = new ArrayList<>();
            for (DeviceInfo deviceInfo:deviceInfos){
                if (deviceInfo.getUhost().equals(token)){
                    localEmmcidList.add(deviceInfo.getEmmcId());
                    localDeviceInfos.add(deviceInfo);
                }
            }
            if (mId == null || pushType == null) {
                return;
            }
            if (localDeviceInfos.size()==0){
                return;
            }
            //16 -> BSP点检   为了统一，依然使用PushTaskReq作为请求体，但是回执体完全不同，无法统一
            if (pushType.equals(Constants.PUSH_TYPE_AUTOMAT_TEST)){
                ThreadDistribution.getInstance().submit(new AutomatCheckProcessor(
                        new PushTaskReq(mId, pushType, productName, localEmmcidList,localDeviceInfos,content,comment)));
            }else{
                ThreadDistribution.getInstance().submit(new PushTaskProcessor(
                        new PushTaskReq(mId, pushType, productName, localEmmcidList,localDeviceInfos,content,comment)));
            }
        } catch (Exception e) {
            logger.error("handle push message error!", e);
        }
    }
}