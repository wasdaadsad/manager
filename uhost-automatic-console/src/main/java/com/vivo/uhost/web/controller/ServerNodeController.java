package com.vivo.uhost.web.controller;

import com.bbkmobile.iqoo.auth.util.SecurityUtils;
import com.bbkmobile.iqoo.common.util.DateUtil;
import com.vivo.console.data.domain.SimplePage;
import com.vivo.uhost.comm.web.BaseJsonResponse;
import com.vivo.uhost.core.domain.bo.ServerNodeBo;
import com.vivo.uhost.dal.entity.ServerNode;
import com.vivo.uhost.service.IServerNodeService;
import com.vivo.uhost.service.controller.BaseController;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gaojun on 2018/1/3.
 */
@Controller
public class ServerNodeController extends BaseController {
    private static Log log = LogFactory.getLog(ServerNodeController.class);
    @Autowired
    private IServerNodeService serverNodeService;

    @RequestMapping("/uhostmanage/servermanage/home")
    public String loginHome() {
        return "/servermanager/home";
    }

    @RequestMapping("/uhostmanage/servermanage/servernodelist")
    @ResponseBody
    public BaseJsonResponse list(ServerNode serverNode, Pageable pageable) {
        List<ServerNode> serverNodes = serverNodeService.findList(serverNode, pageable);
        long count = serverNodeService.count(serverNode);
        List<ServerNodeBo> boList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(serverNodes)) {
            for (ServerNode serverNode1 : serverNodes) {
                ServerNodeBo serverNodeBo = new ServerNodeBo();
                serverNodeBo.setId(serverNode1.getId());
                serverNodeBo.setCreateTime(DateUtil.formatDate(serverNode1.getCreateTime()));
                serverNodeBo.setServerAddress(serverNode1.getServerAddress());
                if (serverNode1.getUpdateTime() != null) {
                    serverNodeBo.setUpdateTime(DateUtil.formatDate(serverNode1.getUpdateTime()));
                }
                if (serverNode1.getState() == 0) {
                    serverNodeBo.setState("离线");
                } else if(serverNode1.getState() == 1){
                    serverNodeBo.setState("在线");
                }

                boList.add(serverNodeBo);
            }
        }
        SimplePage<ServerNodeBo> simplePage = new SimplePage<>(boList, pageable, count);
        return new BaseJsonResponse(BaseJsonResponse.OK, simplePage);
    }


    /*编辑*/

    @RequestMapping("/uhostmanage/servermanage/edit")
    @ResponseBody
    public BaseJsonResponse editUser(ServerNodeBo serverNodeBo) {
        System.out.println("edit访问了" + serverNodeBo);
        ServerNode serverNode = new ServerNode();
        try {
            serverNode.setId(serverNodeBo.getId());
            serverNode.setServerAddress(serverNodeBo.getServerAddress());
            serverNode.setCreateTime(DateUtil.parseDate(serverNodeBo.getCreateTime()));
            serverNode.setUpdateTime(DateUtil.parseDate(serverNodeBo.getUpdateTime()));
            if (serverNodeBo.getState().equals("在线")) {
                serverNode.setState(1);
            } else {
                serverNode.setState(0);
            }
            serverNodeService.edit(serverNode);
        } catch (Exception ex) {
            log.error("edit user failed", ex);
            return new BaseJsonResponse(BaseJsonResponse.INVALID_PARAM, new ArrayList<>());
        }
        return new BaseJsonResponse(BaseJsonResponse.OK, new ArrayList<>());
    }

    /*删除*/

    @RequestMapping("/uhostmanage/servermanage/delete")
    @ResponseBody
    public BaseJsonResponse delete(@RequestParam("id") Integer id) {
        try {
            String loginUser = SecurityUtils.getSubject().getPrincipal().toString();
            System.out.println("登录的用户是:" + loginUser);
            if (loginUser.equals("gaojun")) {
                serverNodeService.delete(id);
            } else {
                return new BaseJsonResponse(BaseJsonResponse.INVALID_PARAM, new ArrayList<>());
            }
        } catch (Exception ex) {
            log.error("delete user failed", ex);
            return new BaseJsonResponse(BaseJsonResponse.INVALID_PARAM, new ArrayList<>());
        }
        return new BaseJsonResponse(BaseJsonResponse.OK, new ArrayList<>());
    }
}