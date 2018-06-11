package com.vivo.uhost.dal.dao;

import com.vivo.uhost.dal.entity.ProcessStepItem;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author litingfa  2018/3/23 19:26
 * @version 1.0
 * @description
 */
public interface IProcessStepItemDao {
    List<ProcessStepItem> findByPid(String processId);

    ProcessStepItem getById(String id);

    void add(ProcessStepItem processStepItem);

    void updateState(ProcessStepItem processStepItem);

    ProcessStepItem runProcessDetail(long id, Integer stateTaskRunning);
}
