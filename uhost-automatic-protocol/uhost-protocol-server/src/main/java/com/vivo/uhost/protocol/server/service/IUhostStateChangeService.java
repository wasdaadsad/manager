/**
 * @Copyright:Copyright © VIVO Communication Technology Co., Ltd. All rights reserved.
 * @Company:http://www.vivo.com.cn/
 */
package com.vivo.uhost.protocol.server.service;

/**
 * @author huangxiaoqun  2018/3/26 21:03
 * @version 1.0
 * @description
 */
public interface IUhostStateChangeService {


    /**
     * uhost状态变更
     *
     * @param token     token
     * @param uidentify 身份id
     * @param state     状态 （0:下线，1:登录中，2:在线）
     */
    void uhostStateChange(String token, String uidentify, Integer state);

}
