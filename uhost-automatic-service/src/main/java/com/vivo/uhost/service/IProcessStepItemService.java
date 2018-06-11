package com.vivo.uhost.service;

import com.vivo.uhost.dal.entity.ProcessStepItem;
import com.vivo.uhost.dal.entity.process.TestProcess;

import java.util.List;

/**
 * @author litingfa  2018/3/23 19:23
 * @version 1.0
 * @description
 */
public interface IProcessStepItemService {
    List<ProcessStepItem> findByPid(String processId);

    ProcessStepItem getById(String id);

    void add(TestProcess testProcess);

    void updateState(ProcessStepItem processStepItem, Integer stateTaskRunning);

    ProcessStepItem runProcessDetail(long id, Integer stateTaskRunning);
}
