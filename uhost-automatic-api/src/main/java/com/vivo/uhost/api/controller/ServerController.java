package com.vivo.uhost.api.controller;

import com.vivo.uhost.comm.util.StringUtil;
import com.vivo.uhost.comm.web.BaseJsonResponse;
import com.vivo.uhost.core.domain.req.ServerStartReq;
import com.vivo.uhost.dal.entity.ServerNode;
import com.vivo.uhost.service.IServerNodeService;
import com.vivo.uhost.service.controller.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * @Title:
 * @Description:
 * @Author:chengen
 * @Since:15-7-3
 * @Modified By:
 * @Modified Date:
 * @Why &amp; What is modified:
 * @Version:1.0
 */
@Controller
@RequestMapping("/server/v1")
public class ServerController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(ServerController.class);

    @Autowired
    private IServerNodeService serverNodeService;

    @RequestMapping("/start")
    @ResponseBody
    public BaseJsonResponse startServer(@RequestBody ServerStartReq req) {
        if (req == null
                || StringUtil.isBlank(req.getCurrentAddress())
                || req.getTcpPort() == null) {
            return BaseJsonResponse.invalidParam("param is error!");
        }
        try {
            ServerNode serverNode = new ServerNode();
            serverNode.setTcpPort(req.getTcpPort());
            serverNode.setServerAddress(req.getCurrentAddress());
            ServerNode existedNode = serverNodeService.getNodeByAddress(serverNode);
            if (existedNode == null) {
                serverNode.setState(1);
                serverNode.setConnectCount(0);
                serverNode.setUpdateTime(new Date());
                serverNode.setCreateTime(new Date());
                serverNodeService.add(serverNode);
            } else {
                existedNode.setConnectCount(0);
                existedNode.setState(1);
                existedNode.setUpdateTime(new Date());
                serverNodeService.edit(existedNode);
            }
            return BaseJsonResponse.ok();
        } catch (Exception e) {
            logger.error("server start error!", e);
            return BaseJsonResponse.serverError(e.getMessage());
        }
    }

}
