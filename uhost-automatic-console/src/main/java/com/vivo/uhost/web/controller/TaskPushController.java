package com.vivo.uhost.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.vivo.internet.vivocfg.client.VivoConfigManager;
import com.vivo.uhost.comm.thread.ThreadDistribution;
import com.vivo.uhost.comm.util.Constants;
import com.vivo.uhost.comm.util.JsonUtils;
import com.vivo.uhost.comm.web.BaseJsonResponse;
import com.vivo.uhost.comm.websocket.WebSocketServer;
import com.vivo.uhost.core.domain.bo.OsVersionBO;
import com.vivo.uhost.core.domain.bo.ToolInfoBO;
import com.vivo.uhost.core.mq.PushNotice;
import com.vivo.uhost.core.type.ResetTestWayEunmType;
import com.vivo.uhost.core.type.TestWayEunmType;
import com.vivo.uhost.core.util.CfgContants;
import com.vivo.uhost.dal.entity.*;
import com.vivo.uhost.dal.entity.process.TestItem;
import com.vivo.uhost.dal.redis.IRedisService;
import com.vivo.uhost.service.*;
import com.vivo.uhost.service.controller.BaseController;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;


/**
 * Created by gaojun on 2018/1/3.
 */
@Controller
public class TaskPushController extends BaseController {

    private static final String REDIS_CONSOLE_TASK_CMD = VivoConfigManager.getString("redis.console.task.cmd");

    private static Log logger = LogFactory.getLog(TaskPushController.class);

    @Autowired
    private IRedisService redisService;

    @Autowired
    private IDeviceService deviceService;

    @Autowired
    private IDeviceTaskService deviceTaskService;

    @Autowired
    private IOsVersionService osVersionService;

    @Autowired
    private IDeviceProcessDetailService deviceProcessDetailService;

    @Autowired
    private IProcessStepItemService processStepItemService;

    @Autowired
    private IProcessDetailService processDetailService;

    @Autowired
    private IFlowListService flowListService;

    @Autowired
    private IToolVersionService toolVersionService;

    @Autowired
    private IToolInfoService toolInfoService;

    @Autowired
    private IToolItemService toolItemService;

    /**
     * 發送流程消息
     *
     * @param taskId
     */
    @RequestMapping("/uhostmanage/taskPush/start")
    @ResponseBody
    public BaseJsonResponse startPushTask(Integer taskId) {
        final ProcessStepItem processStepItem = processStepItemService.getById(String.valueOf(taskId));
        //判断该任务ide状态是否开始
        int status = processStepItem.getStatus();
        if (status != CfgContants.STATE_TASK_NOT_RUN) {
            return new BaseJsonResponse(BaseJsonResponse.INVALID_PARAM);
        }
        final Integer type = processStepItem.getType();
        //获取该流程的设备信息
        final List<DeviceProcessDetail> pid = deviceProcessDetailService.findByPid(processStepItem.getProcessId().toString());
        if (CollectionUtils.isEmpty(pid)) {
            return new BaseJsonResponse(BaseJsonResponse.INVALID_PARAM);
        }
        ThreadDistribution.getInstance().submit(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                setPushNotice(type, processStepItem, pid);
                return null;
            }
        });
        return new BaseJsonResponse(BaseJsonResponse.OK);
    }

    /**
     * 根据type 构造发送的消息
     *
     * @param type
     * @return
     */
    private PushNotice setPushNotice(Integer type, ProcessStepItem processStepItem, List<DeviceProcessDetail> details) {
        PushNotice pushNotice = new PushNotice();
        Integer id = Integer.valueOf(processStepItem.getId().toString());
        FlowList flowList = flowListService.findById(processStepItem.getProcessId().toString());
        //for (DeviceProcessDetail detail : details) {
        //获取设备信息从而获取token
        //DeviceInfo deviceInfo = deviceService.getById(detail.getDeviceId());
        //升级测试
        if (type.equals(CfgContants.PUSH_TYPE_UPDATE)) {
            TestWayEunmType testWayEunmType = TestWayEunmType.getByVal(processStepItem.getTestName());
            OsVersionBO byVersionCode = osVersionService.getByVersionCode(processStepItem.getTestVersion());
            pushNotice.setMsgId(id);
            pushNotice.setComment(testWayEunmType.getIndex().toString());
            pushNotice.setPushType(type);
            pushNotice.setContent(byVersionCode.getPackageUrl());
        }
        //出厂升级测试
        if (type.equals(CfgContants.PUSH_TYPE_FACTORY_UPDATE)) {
            OsVersionBO byVersionCode = osVersionService.getByVersionCode(processStepItem.getTestVersion());
            TestWayEunmType testWayEunmType = TestWayEunmType.getByVal(processStepItem.getTestName());
            pushNotice.setMsgId(id);
            pushNotice.setComment(testWayEunmType.getIndex().toString());
            pushNotice.setPushType(type);
            pushNotice.setContent(byVersionCode.getPackageUrl());
        }
        //恢复出厂设置
        if (type.equals(CfgContants.PUSH_TYPE_FACTORY_RESET)) {
            pushNotice.setMsgId(id);
            pushNotice.setPushType(type);
            ResetTestWayEunmType testWayEunmType = ResetTestWayEunmType.getByVal(processStepItem.getTestName());
            pushNotice.setComment(testWayEunmType.getIndex().toString());
        }
        //安装APK
        if (type.equals(CfgContants.PUSH_TYPE_INSTALL)) {
            pushNotice.setMsgId(id);
            pushNotice.setPushType(type);
            //工具的URL
            ToolVersion toolVersion = toolVersionService.findVersion(processStepItem.getToolId(), processStepItem.getTestVersion());
            pushNotice.setContent(toolVersion.getToolUrl());
        }
        if (type.equals(CfgContants.PUSH_TYPE_RUN_TEST)) {
            //工具的cmd
            ToolInfo toolInfo = new ToolInfo();
            toolInfo.setId(processStepItem.getToolId());
            ToolInfoBO res = toolInfoService.findById(toolInfo);
            //生成comment
            List<TestItem> list = toolItemService.getListByTaskId(Integer.valueOf(processStepItem.getId().toString()));
            String comment = buildRunComment(res.getPackageName(), list);
            pushNotice.setContent(res.getStartCmd());
            pushNotice.setMsgId(id);
            pushNotice.setPushType(type);
            pushNotice.setComment(comment);
        }
        try {
            //pushNotice.setToken(deviceInfo.getUhost());
            pushNotice.setProductName(flowList.getModel());
            //获取到用户选择的设备的emmcList
            List<String> emmcList = new ArrayList<>();
            //获取设备信息列表
            List<DeviceInfo> deviceInfoList = new ArrayList<>();
            //跟新流程测试状态
            flowListService.updateState(processStepItem.getProcessId().toString(), CfgContants.STATE_TASK_RUNNING);
            //跟新流程step_item的状态
            processStepItemService.updateState(processStepItem, CfgContants.STATE_TASK_RUNNING);
            try {
                new WebSocketServer().sendToAllMessage(Constants.REFRESH_TASK_STATE);//告诉浏览器状态由“未开始”变为“运行中”，要刷新页面
            } catch (IOException e) {
                e.printStackTrace();
            }
            //设置设备测试信息
            for (DeviceProcessDetail detail : details) {
                emmcList.add(deviceService.getById(detail.getDeviceId()).getEmmcId());
                deviceInfoList.add(deviceService.getById(detail.getDeviceId()));
                detail.setDetailId(Long.valueOf(id));
                detail.setStatus(CfgContants.DEVICE_STATUS_RUN);
                deviceService.addProcessDetail(detail);
            }
            pushNotice.setEmmcidList(emmcList);
            pushNotice.setDeviceInfoList(deviceInfoList);
            String message = JsonUtils.toJson(pushNotice);
            redisService.publishMsg(CfgContants.TOPIC_PUSH_TASK, message);

            logger.info("send message to push success! message: {}");
        } catch (Exception e) {
            logger.error("send message to push error!", e);
        }
        //}
        return pushNotice;
    }

    private String buildRunComment(String pkgName, List<TestItem> testItems) {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode parentNode = objectMapper.createObjectNode();
        parentNode.put("packageName", pkgName);
        ArrayNode testItemstNode = objectMapper.createArrayNode();
        for (TestItem item : testItems) {
            ObjectNode node = objectMapper.createObjectNode();
            node.put("testType", item.getTestType());
            node.put("duration", item.getTestDuration() * 3600);
            node.put("caseName", item.getCaseName());
            testItemstNode.add(node);
        }
        parentNode.set("testItems", testItemstNode);
        return parentNode.toString();
    }

    public void retryPushTask(Integer taskId) {

    }

    public void stopPushTask(Integer taskId) {

    }

    public void stopAllPushTask(Integer processId) {

    }
}