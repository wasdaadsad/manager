package com.vivo.uhost.web.controller;

import com.vivo.console.data.domain.SimplePage;
import com.vivo.uhost.comm.web.BaseJsonResponse;
import com.vivo.uhost.core.domain.bo.SpotRecordBO;
import com.vivo.uhost.service.controller.BaseController;
import com.vivo.uhost.service.impl.SpotRecordServiceImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author LuZhiWei
 * @version 1.0
 * @describe
 * @date 2018/1/11
 */
@Controller
public class SpotRecordController extends BaseController {
    private static Log log = LogFactory.getLog(SpotRecordController.class);

    @Autowired
    private SpotRecordServiceImpl spotRecordServiceImpl;

    @RequestMapping("uhostmanage/recordManage/home")
    public String home() {
        return "bspreport/home";
    }

    @RequestMapping("uhostmanage/recordManage/add")
    public void addSpotRecord(SpotRecordBO spotRecordBO) {
        spotRecordServiceImpl.insertSpotRecord(spotRecordBO);
    }

    @RequestMapping("uhostmanage/recordManage/update")
    public void updateSpotRecord(SpotRecordBO spotRecordBO) {
        spotRecordServiceImpl.updateSpotRecord(spotRecordBO);
    }

    @RequestMapping("uhostmanage/recordManage/delete")
    public void deleteSpotRecord(@Param("id") Integer id) {
        spotRecordServiceImpl.deleteSpotRecord(id);
    }

    @RequestMapping("uhostmanage/recordManage/list")
    @ResponseBody
    public BaseJsonResponse getSpotRecordList(SpotRecordBO spotRecordBO, Pageable pageable) {
        SimplePage<SpotRecordBO> simplePage = spotRecordServiceImpl.getSpotRecordList(spotRecordBO, pageable);
        return new BaseJsonResponse(BaseJsonResponse.OK, simplePage);
    }


    /*查看详情*/
    @RequestMapping("/uhostmanage/recordManage/lookDetails")
    public ModelAndView selectItem(@RequestParam String testCode) {
        ModelAndView modelAndView = new ModelAndView("bspreport/lookDetails");
        modelAndView.addObject("testCode", testCode);
        return modelAndView;
    }


    /*查看测试信息*/
    @RequestMapping("/uhostmanage/recordManage/lookTestDetails")
    public ModelAndView selectItem2(@RequestParam String comment) {
        ModelAndView modelAndView = new ModelAndView("bspreport/lookTestDetails");
        modelAndView.addObject("comment", comment);
        return modelAndView;
    }


}
