package com.vivo.uhost.protocol.client;

import com.vivo.uhost.protocol.client.service.LoginAckService;
import com.vivo.uhost.protocol.client.service.PingAckService;
import com.vivo.uhost.protocol.client.service.RegisterAckService;
import com.vivo.uhost.comm.util.Constants;
import com.vivo.uhost.protocol.core.service.IFactory;
import com.vivo.uhost.protocol.core.service.impl.ServiceFactory;
import io.netty.channel.Channel;

/**
 * @Title:
 * @Description:
 * @Author:chengen
 * @Since:15-6-25
 * @Modified By:
 * @Modified Date:
 * @Why &amp; What is modified:
 * @Version:1.0
 */
public class VPushClient extends AbstractClient {
    private static IFactory serviceFactory;

    static {
        serviceFactory = new ServiceFactory();
        serviceFactory.addService(Constants.CMD_TYPE_REGISTER_ACK, new RegisterAckService());
        serviceFactory.addService(Constants.CMD_TYPE_LOGIN_ACK, new LoginAckService());
        serviceFactory.addService(Constants.CMD_TYPE_PING_ACK, new PingAckService());
    }

    public VPushClient(){
        super.setServiceFactory(serviceFactory);
    }

    public VPushClient(String host, int port){
        super(host, port, serviceFactory);
    }

    public Channel connect(){
        try {
            return super.connect();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
