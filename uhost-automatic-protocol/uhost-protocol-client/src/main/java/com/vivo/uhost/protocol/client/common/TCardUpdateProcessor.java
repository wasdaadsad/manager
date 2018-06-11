package com.vivo.uhost.protocol.client.common;

import com.vivo.uhost.comm.util.Constants;
import com.vivo.uhost.protocol.client.tools.DeviceCommProp;
import com.vivo.uhost.protocol.client.tools.UhostProcessor;

import java.io.File;

/*
 *  T卡升级处理器
 *  @serial： 手机的序列号
 *  @url：要下载的包的url路径（即topic的content字段）
 *  @author：dongjiajin
 * */
public class TCardUpdateProcessor {
    public static Integer process(String serial, String url) {

        return UhostProcessor.downloadFileAndPushToPhone(serial, url, Constants.UHOST_UPDATE_PACKAGE_PATH, Constants.PHONE_UPDATE_PACKAGE_PATH);
    }
}
