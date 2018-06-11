package com.vivo.uhost.api.controller;

import com.vivo.uhost.comm.util.JsonUtils;
import com.vivo.uhost.comm.util.StringUtil;
import com.vivo.uhost.comm.util.UUIDUtils;
import com.vivo.uhost.comm.web.BaseJsonResponse;
import com.vivo.uhost.comm.web.WebResult;
import com.vivo.uhost.core.domain.req.DeviceRegisterReq;
import com.vivo.uhost.core.domain.req.DeviceRegisterResp;
import com.vivo.uhost.core.domain.req.DeviceStateNotice;
import com.vivo.uhost.dal.entity.DeviceInfo;
import com.vivo.uhost.service.IServerNodeService;
import com.vivo.uhost.service.controller.BaseController;
import com.vivo.uhost.service.impl.DeviceServiceImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by gaojun on 2018/1/3.
 */
@Controller
@RequestMapping("/device")
public class DeviceController extends BaseController {
    private static Log logger = LogFactory.getLog(DeviceController.class);
    @Autowired
    private DeviceServiceImpl deviceServiceImpl;
    //获得设备节点的service
    @Autowired
    private IServerNodeService serverNodeService;

    @RequestMapping("/uhostmanage/machineMange/home")
    public String loginHome() {
        return "/device/home";
    }

    @RequestMapping("/register")
    public void register(@RequestBody String data, HttpServletRequest request, HttpServletResponse response) {
        String result = WebResult.statusFailed();
        try {
            Map<String, Object> paramMap = getParamMap(request);
            DeviceRegisterReq regReq = JsonUtils.toObject(data, DeviceRegisterReq.class);
            logger.info("我被访问了" + regReq);
            if (regReq != null && StringUtil.isNotBlank(regReq.getEmmcId())
                    && StringUtil.isNotBlank(regReq.getOsVer()) && StringUtil.isNotBlank(regReq.getModelVer())) {
                String emmcId = regReq.getEmmcId();
                String pcbCode = regReq.getPcbCode();
                String osVer = regReq.getOsVer();
                String modelVer = regReq.getModelVer();
                logger.info("传过来的参数为:" + regReq);
                /*ModelInfo modelInfo = modelInfoService.getModelByVersion(modelVer);
                if(modelInfo == null){
                    result = WebResult.statusFailed();
                    logger.info("device register failed, model is not exist! emmcId: {}, model: {}", emmcId, modelVer);
                    responseJson(request, response, result);
                    return;
                }*/
                DeviceInfo device = new DeviceInfo();
                device.setEmmcId(emmcId);
                List<DeviceInfo> devices = deviceServiceImpl.selectListByUhostId(device);
                DeviceRegisterResp.Data resultData = new DeviceRegisterResp.Data();
                if (devices.size() > 0) {
                    logger.info("找到了一个设备" + devices.get(0));
                    devices.get(0).setRegTime(new Date());
                    resultData.setOldToken(devices.get(0).getToken());
                    buildRegisterDevice(devices.get(0), osVer, pcbCode);
                    deviceServiceImpl.edit(devices.get(0));
                } else {
                    logger.info("没有找到设备");
                    device = new DeviceInfo();
                    device.setEmmcId(emmcId);
                    buildRegisterDevice(device, osVer, pcbCode);
                    deviceServiceImpl.add(device);
                    resultData.setToken(device.getToken());
                    result = DeviceRegisterResp.STATUS_OK(resultData);
                }
                /*Device device = deviceService.getDeviceByEmmcId(emmcId);
                OsVersion osVersion = osVersionService.getByCode(osVer);
                DeviceRegisterResp.Data resultData = new DeviceRegisterResp.Data();
                if(device != null){
                    if(osVer.equals(device.getVersionCode())){
                        device.setRegTime(new Date());
                        if(osVersion != null && !osVersion.getId().equals(device.getVersionId())){
                            device.setVersionId(osVersion.getId());
                        }
                    }else {
                        resultData.setOldToken(device.getToken());
                        buildRegisterDevice(device, osVersion, osVer, modelInfo.getId(), pcbCode);
                    }
                    deviceService.updateDevice(device);
                    if(osVersion != null){
                        //已存在设备注册需要检查是否完成升级任务
                        pushTaskService.handleTask4Register(device.getId(), osVersion);
                    }
                }else {
                    device = new Device();
                    device.setEmmcId(emmcId);

                    buildRegisterDevice(device, osVersion, osVer, modelInfo.getId(), pcbCode);
                    deviceService.insertDevice(device);
                }*/
                if (devices.size() > 0) {
                    resultData.setToken(devices.get(0).getToken());
                } else {
                    resultData.setToken(device.getToken());
                }
                result = DeviceRegisterResp.STATUS_OK(resultData);
            } else {
                result = WebResult.parameterError();
            }
        } catch (Exception e) {
            logger.error("device register error!", e);
            result = WebResult.exceptionError();
        }
        responseJson(request, response, result);
    }

    private void buildRegisterDevice(DeviceInfo device, String osVer, String pcbCode) {
        device.setToken(UUIDUtils.getUUID());
        device.setRegTime(new Date());
    }

    @RequestMapping("/state")
    @ResponseBody
    public BaseJsonResponse setDeviceState(DeviceStateNotice deviceState) {

        return null;
    }
}