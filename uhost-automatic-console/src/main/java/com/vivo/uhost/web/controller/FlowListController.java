/*
*
* Created by IntelliJ IDEA.
  User: dongjiajin
  Date: 2018/1/9
  Time: 16:15
  To change this template use File | Settings | File Templates.
*
* */

package com.vivo.uhost.web.controller;

import com.bbkmobile.iqoo.common.util.DateUtil;
import com.vivo.console.data.domain.SimplePage;
import com.vivo.uhost.comm.web.BaseJsonResponse;
import com.vivo.uhost.core.domain.bo.FlowListBO;
import com.vivo.uhost.core.domain.bo.ModelInfoBO;
import com.vivo.uhost.core.domain.bo.ToolVersionBo;
import com.vivo.uhost.core.util.CfgContants;
import com.vivo.uhost.dal.entity.FlowList;
import com.vivo.uhost.dal.entity.ProcessStepItem;
import com.vivo.uhost.dal.entity.ToolInfo;
import com.vivo.uhost.dal.entity.process.TestProcess;
import com.vivo.uhost.service.*;
import com.vivo.uhost.service.controller.BaseController;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class FlowListController extends BaseController {
    private static Log log = LogFactory.getLog(FlowListController.class);

    @Autowired
    private IFlowListService flowListService;

    @Autowired
    private IProcessStepItemService processStepItemService;

    @Autowired
    private IModelInfoService modelInfoService;

    @Autowired
    private IToolInfoService toolInfoService;

    @Autowired
    private IToolVersionService toolVersionService;

    @Autowired
    private IProcessInfoService processInfoService;

    /*home.jsp*/
    @RequestMapping("/uhostmanage/testManage/testflow")
    public String home(ModelMap modelMap) {
        List<FlowList> res = flowListService.findAll();
        Set<String> modlesSet = new HashSet<>();
        for (FlowList re : res) {
            modlesSet.add(re.getModel());
        }
        List<String> modles = new ArrayList<>();
        modles.addAll(modlesSet);
        modelMap.addAttribute("modles",modles);
        return "flowmanage/home";
    }

    /*流程列表*/
    @RequestMapping("/uhostmanage/testmanage/flowlist")
    @ResponseBody
    public BaseJsonResponse list(FlowListBO flowListBO, Pageable pageable) {
        List<FlowList> flowLists = flowListService.findList(flowListBO, pageable);
        long count = flowListService.count(flowListBO);
        List<FlowListBO> boList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(flowLists)) {
            for (FlowList flowList : flowLists) {
                FlowListBO flowBo = new FlowListBO(flowList);
                boList.add(flowBo);
            }
        }
        SimplePage<FlowListBO> simplePage = new SimplePage<>(boList, pageable, count);
        return new BaseJsonResponse(BaseJsonResponse.OK, simplePage);
    }


    /*添加*/

    @RequestMapping("/uhostmanage/testmanage/addProcess")
    @ResponseBody
    public BaseJsonResponse addItem(TestProcess testProcess) {
        log.info(testProcess);
        try {
            if (StringUtils.isBlank(testProcess.getTaskTypes()) || testProcess.getModelId() == null
                    || MapUtils.isEmpty(testProcess.getPushTasks())) {
                return new BaseJsonResponse(BaseJsonResponse.INVALID_PARAM);
            }
            //首先存t_process 构建flow
            processInfoService.add(testProcess);
            //保存t_process_step 保存t_tool_detail
            processStepItemService.add(testProcess);
            return new BaseJsonResponse(BaseJsonResponse.OK);
        } catch (Exception e) {
            return new BaseJsonResponse(BaseJsonResponse.INVALID_PARAM);
        }
    }


    /*编辑*/

    @RequestMapping("/uhostmanage/testmanage/edit")
    @ResponseBody
    public BaseJsonResponse editUser(FlowListBO flowListBO) {
        FlowList flowList = new FlowList();
        try {
            flowList.setId(flowListBO.getId());
            flowList.setFlowName(flowListBO.getFlowName());
            if (flowListBO.getFactoryReset().equals("√")) {
                flowList.setFactoryReset("1");
            } else {
                flowList.setFactoryReset("0");
            }
            if (flowListBO.getFactoryUpgrade().equals("√")) {
                flowList.setFactoryUpgrade("1");
            } else {
                flowList.setFactoryUpgrade("0");
            }
            flowList.setModel(flowListBO.getModel());
            flowList.setStartTime(DateUtil.parseTime(flowListBO.getStartTime()));
            //flowList.setFinishTime(DateUtil.parseTime(flowListBO.getFinishTime()));

            switch (flowListBO.getStatus()) {
                case "未开始":
                    flowList.setStatus("-1");
                    break;
                case "运行中":
                    flowList.setStatus("1");
                    break;
                case "已完成":
                    flowList.setStatus("2");
                    break;
                case "已关闭":
                    flowList.setStatus("0");
                    break;
                default:
                    flowList.setStatus("");
                    break;
            }

            flowList.setSucceed(flowListBO.getSucceed());
            flowList.setSums(flowListBO.getSums());
            if (flowListBO.getTest().equals("√")) {
                flowList.setTest("1");
            } else {
                flowList.setTest("0");
            }
            if (flowListBO.getUpgrade().equals("√")) {
                flowList.setUpgrade("1");
            } else {
                flowList.setUpgrade("0");
            }
            flowListService.edit(flowList);
        } catch (Exception ex) {
            log.error("edit flow failed", ex);
            return new BaseJsonResponse(BaseJsonResponse.INVALID_PARAM, new ArrayList<>());
        }
        return new BaseJsonResponse(BaseJsonResponse.OK, new ArrayList<>());
    }

    /*删除*/

    @RequestMapping("/uhostmanage/testmanage/delete")
    @ResponseBody
    public BaseJsonResponse delete(@RequestParam("id") Long id) {
        try {
            flowListService.delete(id);
        } catch (Exception ex) {
            log.error("delete user failed", ex);
            return new BaseJsonResponse(BaseJsonResponse.INVALID_PARAM, new ArrayList<>());
        }
        return new BaseJsonResponse(BaseJsonResponse.OK, new ArrayList<>());
    }

    @RequestMapping("/uhostmanage/testmanage/editProcess ")
    public String editProcess(ModelMap modelMap,
                              @RequestParam(value = "id", required = false) Long id) {
        //return "flowmanage/editProcess";
        try {
            if (id != null) {

            }
            List<ModelInfoBO> modelInfoBOS = modelInfoService.findAll();
            List<ToolInfo> testTools = toolInfoService.getAllToolList(CfgContants.TOOL_STATE_VALID);
            List<ToolVersionBo> toolVersions = toolVersionService.getVersionByState(CfgContants.TOOL_VERSION_VALID);
            modelMap.addAttribute("modelInfos", modelInfoBOS);
            modelMap.addAttribute("testTools", testTools);
            modelMap.addAttribute("toolVersions", toolVersions);
        } catch (Exception e) {
            log.error("edit process error!", e);
        }
        return "flowmanage/process_edit";
    }

    @RequestMapping("/uhostmanage/testmanage/choiceDevice")
    public String choiceDevice(ModelMap modelMap,
                               @RequestParam("processId") String processid) {
        addModeMap(modelMap);
        addProcess(modelMap, processid);
        return "flowmanage/choiceDevice";
    }

    @RequestMapping("/uhostmanage/testmanage/selectedDevice")
    public String selectedDevice(ModelMap modelMap,
                                 @RequestParam("processId") String processid) {
        addModeMap(modelMap);
        FlowList process = flowListService.findById(processid);
        setTaskId(process, modelMap);

        addProcess(modelMap, processid);
        return "flowmanage/selectedDevice";
    }

    /**
     * 判断流程是否启动 设置taskId
     *
     * @param modelMap
     * @param process
     */
    private void setTaskId(FlowList process, ModelMap modelMap) {
        String status = process.getStatus();
        if (Integer.valueOf(status) == CfgContants.STATE_TASK_NOT_RUN) {
            modelMap.addAttribute("taskId", 0);
        } else {
            ProcessStepItem processStepItem = processStepItemService.runProcessDetail(process.getId(), CfgContants.STATE_TASK_RUNNING);
            if(processStepItem == null) {
                modelMap.addAttribute("taskId", 0);
            }else{
                modelMap.addAttribute("taskId", processStepItem.getId());
            }
        }
    }

    /**
     * 任务进度 点击跳转
     * @param modelMap
     * @param processid
     * @return
     */
    @RequestMapping("/uhostmanage/testmanage/taskSchedule")
    public String taskSchedule(ModelMap modelMap,
                               @RequestParam("processId") String processid) {
        addModeMap(modelMap);
        //
        FlowList flowList = flowListService.findById(processid);
        if (flowList.getStatus().equals("10")) {
            modelMap.addAttribute("taskId", 0);
        }
        addProcess(modelMap, processid);
        return "flowmanage/taskSchedule";
    }

    /**
     * 设备进度 点击跳转到已选设备页面
     * @param modelMap
     * @param id
     * @param taskId
     * @return
     */
    @RequestMapping("/uhostmanage/testmanage/deviceSchedule")
    public String deviceSchedule(ModelMap modelMap,
                                 @RequestParam("id") String id,
                                 @RequestParam(value = "taskId", required = false) Integer taskId) {
        ProcessStepItem processStepItem = processStepItemService.getById(id);
        if (taskId != null) {
            modelMap.addAttribute("taskId", taskId);
        }
        addModeMap(modelMap);
        addProcess(modelMap, String.valueOf(processStepItem.getProcessId()));
        return "flowmanage/selectedDevice";
    }

    /**
     * 失败设备
     *
     * @param modelMap
     * @param id
     * @return
     */
    @RequestMapping("/uhostmanage/testmanage/deviceFail")
    public String deviceFail(ModelMap modelMap,
                             @RequestParam("id") String id) {
        ProcessStepItem processStepItem = processStepItemService.getById(id);
        addModeMap(modelMap);
        addProcess(modelMap, String.valueOf(processStepItem.getProcessId()));
        return "flowmanage/failDevice";
    }

    public void addProcess(ModelMap modelMap, String processid) {
        List<String> ids = new ArrayList<>();
        ids.add(processid);
        List<FlowList> byIds = flowListService.findByIds(ids);
        modelMap.addAttribute("process", byIds.get(0));
    }

}
