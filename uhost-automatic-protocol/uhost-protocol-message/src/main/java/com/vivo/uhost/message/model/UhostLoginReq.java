/**
 * @Copyright:Copyright © VIVO Communication Technology Co., Ltd. All rights reserved.
 * @Company:http://www.vivo.com.cn/
 */
package com.vivo.uhost.message.model;

/**
 * @author huangxiaoqun  2018/3/21 11:16
 * @version 1.0
 * @description
 */
public class UhostLoginReq {

    //token
    private String token;

    //身份标识
    private String uidentify;

    //ip地址
    private String ipAdress;


    public UhostLoginReq() {
    }

    public UhostLoginReq(String token, String uidentify, String ipAdress) {
        this.token = token;
        this.uidentify = uidentify;
        this.ipAdress = ipAdress;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUidentify() {
        return uidentify;
    }

    public void setUidentify(String uidentify) {
        this.uidentify = uidentify;
    }

    public String getIpAdress() {
        return ipAdress;
    }

    public void setIpAdress(String ipAdress) {
        this.ipAdress = ipAdress;
    }
}
