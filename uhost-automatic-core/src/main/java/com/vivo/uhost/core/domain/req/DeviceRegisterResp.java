package com.vivo.uhost.core.domain.req;


import com.vivo.uhost.comm.util.JsonUtils;
import com.vivo.uhost.comm.web.WebResult;

import java.util.Date;

/**
 * @Title:
 * @Description:
 * @Author:chengen
 * @Since:15-7-2
 * @Modified By:
 * @Modified Date:
 * @Why &amp; What is modified:
 * @Version:1.0
 */
public class DeviceRegisterResp extends WebResult<DeviceRegisterResp.Data> {

    public DeviceRegisterResp() {
    }

    public DeviceRegisterResp(Data data) {
        super(CODE_SUCCESS, data, MSG_SUCCESS, SDF.format(new Date()));
    }

    public static String STATUS_OK(Data response){
        return JsonUtils.toJson(new DeviceRegisterResp(response));
    }



    public static class Data{
        private String token;  //新token
        private String oldToken;  //老token

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getOldToken() {
            return oldToken;
        }

        public void setOldToken(String oldToken) {
            this.oldToken = oldToken;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "token='" + token + '\'' +
                    ", oldToken='" + oldToken + '\'' +
                    '}';
        }
    }
}
