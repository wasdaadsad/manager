package com.vivo.uhost.web.controller;

import com.bbkmobile.iqoo.auth.util.SecurityUtils;
import com.bbkmobile.iqoo.common.util.DateUtil;
import com.vivo.console.data.domain.SimplePage;
import com.vivo.uhost.comm.web.BaseJsonResponse;
import com.vivo.uhost.core.domain.bo.*;
import com.vivo.uhost.core.util.CfgContants;
import com.vivo.uhost.dal.entity.DeviceInfo;
import com.vivo.uhost.dal.entity.DeviceProcessDetail;
import com.vivo.uhost.dal.entity.FlowList;
import com.vivo.uhost.dal.entity.TestResult;
import com.vivo.uhost.service.IDeviceService;
import com.vivo.uhost.service.IFlowListService;
import com.vivo.uhost.service.IModelInfoService;
import com.vivo.uhost.service.ITestResultService;
import com.vivo.uhost.service.IUhostInfoService;
import com.vivo.uhost.dal.entity.*;
import com.vivo.uhost.service.*;
import com.vivo.uhost.service.controller.BaseController;
import com.vivo.uhost.service.impl.DeviceServiceImpl;
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
public class DeviceController extends BaseController {

    private static Log log = LogFactory.getLog(DeviceController.class);

    @Autowired
    private IDeviceService deviceService;

    @Autowired
    private IModelInfoService modelInfoService;

    @Autowired
    private IUhostInfoService uhostService;

    @Autowired
    private IProcessDetailService processDetailService;

    @Autowired
    private IProcessStepItemService processStepItemService;

    @Autowired
    private IFlowListService flowListService;

    @RequestMapping("/uhostmanage/machineMange/home")
    public String loginHome(ModelMap modelMap) {

        addModeMap(modelMap);
        return "/device/home";
    }

    @RequestMapping("/admin/device/devicelist")
    @ResponseBody
    public BaseJsonResponse list(DeviceBo device, Pageable pageable) {
        List<DeviceInfo> deviceList = deviceService.findList(device.toEntity(), pageable);
        long count = deviceService.count(device.toEntity());
        List<DeviceBo> boList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(deviceList)) {
            for (DeviceInfo device1 : deviceList) {
                DeviceBo siteBo = new DeviceBo(device1);
                if (device1.getLastLoginTime() != null) {
                    siteBo.setLastLoginTime(DateUtil.formatTime(device1.getLastLoginTime()));
                }
                boList.add(siteBo);
            }
        }
        SimplePage<DeviceBo> simplePage = new SimplePage<>(boList, pageable, count);
        return new BaseJsonResponse(BaseJsonResponse.OK, simplePage);
    }

    /**
     * 已选设备的查看
     *
     * @param processId
     * @param pageable
     * @return
     */
    @RequestMapping("/admin/device/selectlist")
    @ResponseBody
    public BaseJsonResponse selsctlist(@RequestParam(value = "processId") String processId,
                                       @RequestParam("taskId") String taskId,
                                       Pageable pageable) {
        FlowList flowList = flowListService.findById(processId);
        flowList.setTaskId(Integer.valueOf(taskId));
            List<DeviceProcessDetail> details = deviceService.findDetailByPid(flowList, pageable);
        long count = deviceService.countDetail(flowList);
        List<DeviceProcessDetailBO> boList = new ArrayList<>();
        for (DeviceProcessDetail detail : details) {
            DeviceProcessDetailBO detailBO = new DeviceProcessDetailBO(detail);
            DeviceInfo deviceInfo = deviceService.getById(detail.getDeviceId());
            detailBO.setPcb(deviceInfo.getPcb());
            detailBO.setProcessState(Integer.parseInt(flowList.getStatus()));
            setDeviceInfo(detailBO);
            boList.add(detailBO);
        }
        SimplePage<DeviceProcessDetailBO> simplePage = new SimplePage<>(boList, pageable, count);
        return new BaseJsonResponse(BaseJsonResponse.OK, simplePage);
    }

    @RequestMapping("/admin/device/faillist")
    @ResponseBody
    public BaseJsonResponse faillist(@RequestParam(value = "processId") String processId, Pageable pageable) {
        FlowList flowList = flowListService.findById(processId);
        DeviceProcessDetail deviceProcessDetail = new DeviceProcessDetail();
        deviceProcessDetail.setProcessId(Long.valueOf(processId));
        deviceProcessDetail.setStatus(CfgContants.DEVICE_STATUS_FAIL);
        List<DeviceProcessDetail> details = deviceService.findFailList(deviceProcessDetail, pageable);
        long count = deviceService.countDetail(flowList);
        List<DeviceProcessDetailBO> boList = new ArrayList<>();
        for (DeviceProcessDetail detail : details) {
            DeviceProcessDetailBO detailBO = new DeviceProcessDetailBO(detail);
            DeviceInfo deviceInfo = deviceService.getById(detail.getDeviceId());
            detailBO.setPcb(deviceInfo.getPcb());
            detailBO.setProcessState(Integer.parseInt(flowList.getStatus()));
            boList.add(detailBO);
        }
        SimplePage<DeviceProcessDetailBO> simplePage = new SimplePage<>(boList, pageable, count);
        return new BaseJsonResponse(BaseJsonResponse.OK, simplePage);
    }

    /**
     * 任务进度的数据查看
     *
     * @param processId
     * @param pageable
     * @return
     */
    @RequestMapping("/admin/device/scheduleList")
    @ResponseBody
    public BaseJsonResponse scheduleList(@RequestParam(value = "processId") String processId, Pageable pageable) {
        FlowList flowList = flowListService.findById(processId);
        if (flowList == null) {
            return new BaseJsonResponse(BaseJsonResponse.INVALID_PARAM, "流程数据错误");
        }
        try {
            List<ProcessStepItemBO> boList = new ArrayList<>();
            List<ProcessStepItem> processStepItems = processStepItemService.findByPid(processId);
            for (ProcessStepItem processStepItem : processStepItems) {
                ProcessStepItemBO processStepItemBO = new ProcessStepItemBO();
                setProcessDetailBO(processStepItemBO, processStepItem, flowList);
                boList.add(processStepItemBO);
            }
            return new BaseJsonResponse(BaseJsonResponse.OK, boList);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("query scheduleList failed", e);
            return new BaseJsonResponse(BaseJsonResponse.INVALID_PARAM, new ArrayList<>());
        }
    }

    /**
     * 配置页面展示任务进度的数据
     *
     * @param processStepItemBO
     * @param processStepItem
     * @param flowList
     */
    private void setProcessDetailBO(ProcessStepItemBO processStepItemBO, ProcessStepItem processStepItem, FlowList flowList) {
        FlowListBO flowListBO = new FlowListBO(flowList);
        processStepItemBO.setByEntity(processStepItem);
        processStepItemBO.setStartTime(flowListBO.getStartTime());
        processStepItemBO.setUpdateTime(flowListBO.getUpdateTime());
        processStepItemBO.setStatus(Integer.parseInt(flowListBO.getStatus()));
    }


    @RequestMapping("/admin/device/choiceDevice")
    @ResponseBody
    public BaseJsonResponse choiceDevice(@RequestParam(value = "processId") String processId,
                                         @RequestBody List<String> deviceIds) {
        if (!(deviceIds.size() > 0)) {
            return new BaseJsonResponse(BaseJsonResponse.INVALID_PARAM, "没有选择设备");
        }
        try {
            List<String> ids = new ArrayList<>();
            ids.add(processId);
            List<FlowList> byIds = flowListService.findByIds(ids);
            FlowList flowList = byIds.get(0);
            int count = 0;
            List<DeviceProcessDetail> details = setDeviceProcessDetail(deviceIds, flowList);
            for (DeviceProcessDetail detail : details) {
                List<DeviceProcessDetail> res = deviceService.findByDetail(detail);
                if (res.size() > 0) {
                    continue;
                }
                //保存流程统选设备 流程测试总数增加
                deviceService.addProcessDetail(detail);
                count++;
            }
            flowListService.setCount(count, processId);
            return new BaseJsonResponse(BaseJsonResponse.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseJsonResponse(BaseJsonResponse.INVALID_PARAM, e.getMessage());
        }
    }

    /**
     * 构建设备测试明细数据
     *
     * @param deviceIds
     * @param flowList
     */
    private List<DeviceProcessDetail> setDeviceProcessDetail(List<String> deviceIds, FlowList flowList) {
        List<DeviceProcessDetail> details = new ArrayList<>();
        for (String deviceId : deviceIds) {
            DeviceProcessDetail detail = new DeviceProcessDetail();
            detail.setDeviceId(Long.valueOf(deviceId));
            detail.setProcessId(flowList.getId());
            detail.setDetailId(Long.valueOf(0));
            details.add(detail);
        }
        return details;
    }


    /*编辑*/

    @RequestMapping("/uhostmanage/machineMange/edit")
    @ResponseBody
    public BaseJsonResponse editUser(DeviceBo deviceBo) {
        System.out.println("edit访问了" + deviceBo);
        DeviceInfo device = new DeviceInfo();
        try {
            device.setId(deviceBo.getId());
            device.setFactory(deviceBo.getFactory());
            device.setWorkShop(deviceBo.getWorkShop());
            device.setAgingRoom(deviceBo.getAgingRoom());
            device.setAgingRack(deviceBo.getAgingRack());
            device.setUhost(deviceBo.getUhost());
            device.setModel(deviceBo.getModel());
            device.setPcb(deviceBo.getPcb());
            device.setEmmcId(deviceBo.getEmmcId());
            device.setSystemVersion(deviceBo.getSystemVersion());
            device.setState(deviceBo.getState());
            device.setLastLoginTime(DateUtil.parseTime(deviceBo.getLastLoginTime()));
            deviceService.edit(device);
        } catch (Exception ex) {
            log.error("edit user failed", ex);
            return new BaseJsonResponse(BaseJsonResponse.INVALID_PARAM, new ArrayList<>());
        }
        return new BaseJsonResponse(BaseJsonResponse.OK, new ArrayList<>());
    }

    /*删除*/

    @RequestMapping("/uhostmanage/machineMange/delete")
    @ResponseBody
    public BaseJsonResponse delete(@RequestParam("id") Long id) {
        try {
            String loginUser = SecurityUtils.getSubject().getPrincipal().toString();
            if (loginUser.equals("gaojun")) {
                deviceService.delete(id);
            } else {
                return new BaseJsonResponse(BaseJsonResponse.INVALID_PARAM, new ArrayList<>());
            }
        } catch (Exception ex) {
            log.error("delete user failed", ex);
            return new BaseJsonResponse(BaseJsonResponse.INVALID_PARAM, new ArrayList<>());
        }
        return new BaseJsonResponse(BaseJsonResponse.OK, new ArrayList<>());
    }

    public void setDeviceInfo(DeviceProcessDetailBO deviceInfo) {
        if (deviceInfo.getDetailId() != null) {
            ProcessStepItem byId = processStepItemService.getById(String.valueOf(deviceInfo.getDetailId()));
            if (byId != null) {
                deviceInfo.setDetailVP(byId.getTestName());
            }
        }
    }
}