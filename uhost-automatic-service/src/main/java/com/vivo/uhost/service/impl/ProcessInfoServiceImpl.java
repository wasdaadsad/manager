package com.vivo.uhost.service.impl;

import com.vivo.uhost.core.util.CfgContants;
import com.vivo.uhost.dal.dao.IDeviceDao;
import com.vivo.uhost.dal.dao.IProcessInfoDao;
import com.vivo.uhost.dal.entity.DeviceInfo;
import com.vivo.uhost.dal.entity.process.TestProcess;
import com.vivo.uhost.service.IProcessInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author litingfa  2018/3/31 19:38
 * @version 1.0
 * @description
 */
@Service
public class ProcessInfoServiceImpl implements IProcessInfoService {

    @Autowired
    private IProcessInfoDao processInfoDao;

    @Autowired
    private IDeviceDao deviceDao;

    @Override
    public void add(TestProcess testProcess) {
        String modelVersion = deviceDao.getMedelVersionById(Long.valueOf(testProcess.getModelId()));
        testProcess.setTotalNum(0);
        testProcess.setSuccessNum(0);
        testProcess.setState(CfgContants.STATE_TASK_NOT_RUN);
        testProcess.setCreateTime(new Date());
        testProcess.setProductName(modelVersion);
        testProcess.setModelVersion("");
        processInfoDao.add(testProcess);
    }
}
