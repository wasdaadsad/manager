package com.vivo.uhost.web.controller;

import com.bbkmobile.iqoo.auth.util.SecurityUtils;
import com.bbkmobile.iqoo.common.util.DateUtil;
import com.vivo.console.data.domain.SimplePage;
import com.vivo.uhost.comm.web.BaseJsonResponse;
import com.vivo.uhost.core.domain.bo.*;
import com.vivo.uhost.core.util.CfgContants;
import com.vivo.uhost.dal.entity.*;
import com.vivo.uhost.service.*;
import com.vivo.uhost.service.controller.BaseController;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gaojun on 2018/1/3.
 */
@Controller
public class UhostController extends BaseController {

    private static Log log = LogFactory.getLog(UhostController.class);

    @Autowired
    private IUhostInfoService uhostInfoService;

    @RequestMapping("/uhostmanage/uhostMange/home")
    public String loginHome(ModelMap modelMap) {

        addUhostModeMap(modelMap);
        return "/uhost/home";
    }

    @RequestMapping("/uhostmanage/uhostMange/list")
    @ResponseBody
    public BaseJsonResponse list(UhostInfoBO uhostInfoBO, Pageable pageable) {
        List<UhostInfo> uhostInfos = uhostInfoService.findList(uhostInfoBO.toEntity(), pageable);
        long count = uhostInfoService.count(uhostInfoBO.toEntity());
        List<UhostInfoBO> boList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(uhostInfos)) {
            for (UhostInfo uhostInfo : uhostInfos) {
                UhostInfoBO siteBo = new UhostInfoBO(uhostInfo);
                boList.add(siteBo);
            }
        }
        SimplePage<UhostInfoBO> simplePage = new SimplePage<>(boList, pageable, count);
        return new BaseJsonResponse(BaseJsonResponse.OK, simplePage);
    }


}