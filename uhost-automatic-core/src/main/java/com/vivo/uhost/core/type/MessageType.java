/**
 * @Copyright:Copyright © VIVO Communication Technology Co., Ltd. All rights reserved.
 * @Company:http://www.vivo.com.cn/
 */
package com.vivo.uhost.core.type;

import java.util.ArrayList;
import java.util.List;

/**
 * @author huangxiaoqun  2017/11/23 14:38
 * @version 1.0
 * @description
 */
public enum MessageType {

    CheckReport("upload-report-msg", "检测报告"),

    UserLog("upload-userlog-msg", "用户日志"),

    CheckValid("report-check-result", "上报检测状态");

    private String channel;

    private String remark;

    MessageType(String channel, String remark) {
        this.channel = channel;
        this.remark = remark;
    }

    /**
     * 根据Channel获取消息类型
     *
     * @param channel Channel
     * @return 消息类型
     */
    public static MessageType messageType(String channel) {
        for (MessageType messageType : MessageType.values()) {
            if (messageType.getChannel().equals(channel)) {
                return messageType;
            }
        }
        return null;
    }


    public static String[] getChannels() {
        List<String> channels = new ArrayList<String>();
        for (MessageType messageType : MessageType.values()) {
            channels.add(messageType.getChannel());
        }
        return channels.toArray(new String[channels.size()]);
    }


    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
