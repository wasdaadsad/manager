/**
 * @Copyright:Copyright © VIVO Communication Technology Co., Ltd. All rights reserved.
 * @Company:http://www.vivo.com.cn/
 */
package com.vivo.uhost.api.controller;

import com.vivo.uhost.comm.util.StringUtil;
import com.vivo.uhost.comm.web.BaseJsonResponse;
import com.vivo.uhost.dal.entity.UhostInfo;
import com.vivo.uhost.service.IUhostInfoService;
import com.vivo.uhost.service.controller.BaseController;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author huangxiaoqun  2018/3/23 18:41
 * @version 1.0
 * @description
 */
@Controller
@RequestMapping("/uhost/v1")
public class UhostController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(ServerController.class);

    @Autowired
    private IUhostInfoService uhostInfoService;

    @RequestMapping("/login")
    @ResponseBody
    public BaseJsonResponse startServer(@RequestBody UhostInfo uhostInfo) {
        if (uhostInfo == null
                || StringUtil.isBlank(uhostInfo.getIpAdress())
                || StringUtils.isEmpty(uhostInfo.getUidentify())) {
            return BaseJsonResponse.invalidParam("param is error!");
        }
        try {
            UhostInfo uhost = uhostInfoService.findByIdentify(uhostInfo.getUidentify());
            if (uhost == null) {
                uhostInfoService.addUhost(uhostInfo);
            } else {
                uhostInfoService.editUhost(uhostInfo);
            }
            return BaseJsonResponse.ok();
        } catch (Exception e) {
            logger.error("server start error!", e);
            return BaseJsonResponse.serverError(e.getMessage());
        }
    }

}
