package com.vivo.uhost.service.impl;

import com.vivo.uhost.core.type.ResetTestWayEunmType;
import com.vivo.uhost.core.type.TestWayEunmType;
import com.vivo.uhost.core.util.CfgContants;
import com.vivo.uhost.dal.dao.*;
import com.vivo.uhost.dal.entity.*;
import com.vivo.uhost.dal.entity.process.PushTask;
import com.vivo.uhost.dal.entity.process.TestItem;
import com.vivo.uhost.dal.entity.process.TestProcess;
import com.vivo.uhost.service.IProcessStepItemService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author litingfa  2018/3/23 19:24
 * @version 1.0
 * @description
 */
@Service
public class ProcessStepItemServiceImpl implements IProcessStepItemService {

    @Autowired
    private IProcessStepItemDao processStepItemDao;

    @Autowired
    private IOsVersionDao osVersionDao;

    @Autowired
    private IToolInfoDao toolInfoDao;

    @Autowired
    private IToolVersionDao toolVersionDao;

    @Autowired
    private IToolItemDao toolItemDao;

    @Override
    public List<ProcessStepItem> findByPid(String processId) {
        return processStepItemDao.findByPid(processId);
    }

    @Override
    public ProcessStepItem getById(String id) {
        return processStepItemDao.getById(id);
    }

    /**
     * 保存详细流程
     *
     * @param testProcess
     */
    @Override
    public void add(TestProcess testProcess) {
        String[] taskTypes = StringUtils.split(testProcess.getTaskTypes(), ",");
        int order = 1;
        for (String taskType : taskTypes) {
            try {
                ProcessStepItem processStepItem = new ProcessStepItem();
                Integer type = Integer.valueOf(taskType);
                if (type.equals(CfgContants.PUSH_TYPE_UPDATE)) {
                    //测试类型为系统升级
                    processStepItem = addProcessStepItem(taskType, testProcess, processStepItem);
                    setTestWay(processStepItem, type, testProcess);
                    processStepItem.setOrder(order++);
                    processStepItemDao.add(processStepItem);
                    continue;
                }
                if (type.equals(CfgContants.PUSH_TYPE_FACTORY_UPDATE)) {
                    //测试类型为出厂升级
                    processStepItem = addProcessStepItem(taskType, testProcess, processStepItem);
                    setTestWay(processStepItem, type, testProcess);
                    processStepItem.setOrder(order++);
                    processStepItemDao.add(processStepItem);
                    continue;
                }
                if (type.equals(CfgContants.PUSH_TYPE_FACTORY_RESET)) {
                    //测试类型为恢复出厂
                    processStepItem.setProcessId(testProcess.getId());
                    processStepItem.setType(Integer.parseInt(taskType));
                    PushTask pushTask = testProcess.getPushTasks().get(Long.valueOf(type));
                    ResetTestWayEunmType testWayEunmType = ResetTestWayEunmType.getByIndex(pushTask.getTestWay());
                    processStepItem.setTestName(testWayEunmType.getVal());
                    processStepItem.setStatus(CfgContants.STATE_TASK_NOT_RUN);
                    processStepItem.setOrder(order++);
                    processStepItemDao.add(processStepItem);
                    continue;
                }
                if (type.equals(CfgContants.PUSH_TYPE_TEST)) {
                    Long pushKey = Long.valueOf(taskType);
                    PushTask pushTask = testProcess.getPushTasks().get(pushKey);
                    List<PushTask> childTasks = pushTask.getChildTasks();
                    for (PushTask childTask : childTasks) {
                        //测试类型为测试 保存安装apk
                        processStepItem.setProcessId(testProcess.getId());
                        processStepItem.setType(CfgContants.PUSH_TYPE_INSTALL);//安装apk
                        processStepItem.setStatus(CfgContants.STATE_TASK_NOT_RUN);
                        processStepItem.setOrder(order++);
                        setToolInfo(childTask, processStepItem);
                        processStepItemDao.add(processStepItem);
                        //保存测试工具的流程
                        ProcessStepItem psItem = new ProcessStepItem();
                        //获取工具的信息
                        psItem = setToolInfo(childTask, psItem);
                        psItem.setStatus(CfgContants.STATE_TASK_NOT_RUN);
                        psItem.setProcessId(testProcess.getId());
                        psItem.setType(CfgContants.PUSH_TYPE_RUN_TEST);
                        psItem.setOrder(order++);
                        processStepItemDao.add(psItem);
                        List<TestItem> testItems = childTask.getTestItems();
                        for (TestItem testItem : testItems) {
                            if (testItem.getState() != null && testItem.getItemOrder() != null) {
                                //保存toolItem
                                if (testItem.getState().equals(CfgContants.STATE_VALID)) {
                                    ToolItem toolItem = new ToolItem(testItem);
                                    toolItem.setToolId(Long.valueOf(childTask.getToolId()));
                                    Integer taskId = Integer.valueOf(psItem.getId().toString());
                                    toolItem.setTaskId(taskId);
                                    toolItemDao.insertTestItem(toolItem);
                                }

                            }
                        }
                    }
                    continue;
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
    }

    private void setTestWay(ProcessStepItem processStepItem, Integer type, TestProcess testProcess) {
        //升级的方式
        PushTask pushTask = testProcess.getPushTasks().get(Long.valueOf(type));
        TestWayEunmType testWayEunmType = TestWayEunmType.getByIndex(pushTask.getTestWay());
        processStepItem.setTestName(testWayEunmType.getVal());
    }

    @Override
    public void updateState(ProcessStepItem processStepItem, Integer stateTaskRunning) {
        processStepItem.setStatus(stateTaskRunning);
        processStepItemDao.updateState(processStepItem);
    }

    /**
     * 查询运行中的流程正在执行的测试
     *
     * @param id
     * @param stateTaskRunning
     * @return
     */
    @Override
    public ProcessStepItem runProcessDetail(long id, Integer stateTaskRunning) {
        return processStepItemDao.runProcessDetail(id, stateTaskRunning);
    }

    private ProcessStepItem setToolInfo(PushTask childTask, ProcessStepItem psItem) {
        ToolInfo toolInfo = new ToolInfo();
        toolInfo.setId(Long.valueOf(childTask.getToolId()));
        ToolInfo byId = toolInfoDao.findById(toolInfo);
        ToolVersion toolVersion = toolVersionDao.findById(Long.valueOf(childTask.getToolVersionId()));
        psItem.setTestName(byId.getToolName());
        psItem.setTestVersion(toolVersion.getVersionName());
        psItem.setToolId(byId.getId());
        return psItem;
    }

    private ProcessStepItem addProcessStepItem(String taskType, TestProcess testProcess, ProcessStepItem processStepItem) {
        Long pushKey = Long.valueOf(taskType);
        PushTask pushTask = testProcess.getPushTasks().get(pushKey);
        OsVersion osVersion = osVersionDao.getOsVersionById(pushTask.getOsVersionId());
        processStepItem.setProcessId(testProcess.getId());
        processStepItem.setType(Integer.parseInt(taskType));
        processStepItem.setTestVersion(osVersion.getVersionCode());
        processStepItem.setStatus(CfgContants.STATE_TASK_NOT_RUN);

        return processStepItem;
    }


}
