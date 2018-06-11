package com.vivo.uhost.protocol.server.utils;

import com.bbkmobile.iqoo.common.util.JsonUtil;
import com.vivo.uhost.comm.http.CloseableHttpClientUtils;
import com.vivo.uhost.comm.util.StringUtil;
import com.vivo.uhost.comm.web.BaseJsonResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * http工具类
 */

public class HttpUtils {

    private static final Logger logger = LoggerFactory.getLogger(HttpUtils.class);

    public static boolean reportApiServer(String requestUrl, String json) {
        String url = ServerConstants.API_HOST + requestUrl;
        try {
            String respData = CloseableHttpClientUtils.post(url, json);
            if (StringUtil.isNotBlank(respData)) {
                BaseJsonResponse response = JsonUtil.json2Bean(respData, BaseJsonResponse.class);
                if (response != null && BaseJsonResponse.OK == response.getStat()) {
                    return true;
                } else {
                    logger.warn("api server start failed! req: {}, resp: {}", json, respData);
                }
            }
        } catch (Exception e) {
            logger.error("start push server {} failed!", json, e);
        }
        return false;
    }

}
