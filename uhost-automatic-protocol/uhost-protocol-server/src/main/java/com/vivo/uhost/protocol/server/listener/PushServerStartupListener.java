/**
 * @Copyright:Copyright © VIVO Communication Technology Co., Ltd. All rights reserved.
 * @Company:http://www.vivo.com.cn/
 */
package com.vivo.uhost.protocol.server.listener;

import com.vivo.uhost.protocol.server.bootstrap.VPushServerBootstrap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author huangxiaoqun  2017/10/30 20:22
 * @version 1.0
 * @description
 */
public class PushServerStartupListener implements ServletContextListener {

    private static Logger LOGGER = LoggerFactory.getLogger(PushServerStartupListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        LOGGER.info("loading spring context...");
        VPushServerBootstrap.main(new String[0]);
    }


    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
