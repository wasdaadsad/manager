package com.vivo.uhost.web.controller;

import com.alibaba.druid.support.logging.Log;
import com.alibaba.druid.support.logging.LogFactory;
import com.bbkmobile.iqoo.common.util.DateUtil;
import com.vivo.console.data.domain.SimplePage;
import com.vivo.uhost.comm.util.Constants;
import com.vivo.uhost.comm.web.BaseJsonResponse;
import com.vivo.uhost.core.domain.bo.DeviceBo;
import com.vivo.uhost.core.mq.PushNotice;
import com.vivo.uhost.dal.entity.DeviceInfo;
import com.vivo.uhost.dal.entity.TestItem;
import com.vivo.uhost.service.IDeviceService;
import com.vivo.uhost.service.ITestItemService;
import com.vivo.uhost.service.controller.BaseController;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CheckDeviceController extends BaseController {
    private static Log log = LogFactory.getLog(CheckDeviceController.class);

    @Autowired
    private IDeviceService deviceService;

    @Autowired
    private ITestItemService testItemService;

    /*home.jsp*/
    @RequestMapping("/uhostmanage/checkManage/home")
    public String home() {
        return "checkmanage/home";
    }


    /*流程列表*/

    @RequestMapping("/uhostmanage/checkManage/devicelist")
    @ResponseBody
    public BaseJsonResponse list(DeviceInfo device, Pageable pageable) {
        List<DeviceInfo> deviceList = deviceService.findList(device, pageable);
        long count = deviceService.count(device);
        List<DeviceBo> boList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(deviceList)) {
            for (DeviceInfo device1 : deviceList) {
                DeviceBo siteBo = new DeviceBo();
                siteBo.setId(device1.getId());
                siteBo.setIp(device1.getIp());
                siteBo.setToken(device1.getToken());
                siteBo.setEmmcId(device1.getEmmcId());
                siteBo.setSystemVersion(device1.getSystemVersion());
                siteBo.setModel(device1.getModel());
                if (device1.getState() == 0) {
                    siteBo.setStateVp("离线");
                } else {
                    siteBo.setStateVp("在线");
                }
                siteBo.setLastLoginTime(DateUtil.formatTime(device1.getLastLoginTime()));
                boList.add(siteBo);
            }
        }
        SimplePage<DeviceBo> simplePage = new SimplePage<>(boList, pageable, count);
        return new BaseJsonResponse(BaseJsonResponse.OK, simplePage);
    }

    /*选择配置项*/
    @RequestMapping("/uhostmanage/checkManage/selectItem")
    public ModelAndView selectItem(@RequestParam String cellValue, String ip, String token, String model, String emmcId, String state, String systemVersion, String lastLoginTime) {
        ModelAndView modelAndView = new ModelAndView("checkmanage/selectItem");
        modelAndView.addObject("devicesID", cellValue);
        modelAndView.addObject("ip", ip);
        modelAndView.addObject("token", token);
        modelAndView.addObject("model", model);
        modelAndView.addObject("emmcId", emmcId);
        try {
            String param = new String(state.getBytes("ISO8859-1"), "UTF-8");
            modelAndView.addObject("state", param);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        modelAndView.addObject("systemVersion", systemVersion);
        modelAndView.addObject("lastLoginTime", lastLoginTime);
        return modelAndView;
    }


    @RequestMapping("/uhostmanage/checkManage/testItem")
    @ResponseBody
    public List<TestItem> testItems(@RequestParam String testItem, Pageable pageable) {
        TestItem testItem1 = new TestItem();
        testItem1.setChildModel(testItem);
        return testItemService.findList(testItem1, pageable);
    }


    @RequestMapping(value = "/uhostmanage/checkManage/dataString", method = {RequestMethod.POST})
    @ResponseBody
    public String requestBodyBind(@RequestBody String dataString) {
        DeviceInfo device = new DeviceInfo();
        long id = 111;
        device.setId(id);
        List<DeviceInfo> devices = deviceService.selectListByUhostId(device);
        String token = devices.get(0).getToken();
        PushNotice notice = new PushNotice();
        notice.setPushType(Constants.PUSH_TYPE_RUN_TEST);
        notice.setContent(dataString);
        notice.setMsgId(2);
        notice.setToken(token);
        return dataString;
    }


}
