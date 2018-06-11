package com.vivo.uhost.service.impl;

import com.vivo.uhost.comm.thread.ThreadDistribution;
import com.vivo.uhost.core.util.CfgContants;
import com.vivo.uhost.dal.dao.IDeviceDao;
import com.vivo.uhost.dal.dao.IDeviceTaskDao;
import com.vivo.uhost.dal.dao.IFlowListDao;
import com.vivo.uhost.dal.dao.IProcessStepItemDao;
import com.vivo.uhost.dal.entity.DeviceInfo;
import com.vivo.uhost.dal.entity.DeviceProcessDetail;
import com.vivo.uhost.dal.entity.ProcessStepItem;
import com.vivo.uhost.service.IDeviceTaskService;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * @author litingfa  2018/3/30 14:37
 * @version 1.0
 * @description
 */
@Service
public class DeviceTaskServiceImpl implements IDeviceTaskService {

    private static Logger logger = LoggerFactory.getLogger(DeviceTaskServiceImpl.class);

    @Autowired
    private IDeviceTaskDao deviceTaskDao;

    @Autowired
    private IDeviceDao deviceDao;

    @Autowired
    private IProcessStepItemDao processStepItemDao;

    @Autowired
    private IFlowListDao flowListDao;

    @Override
    public void changeTaskState(final Integer msgId, String token, Map<String, Integer> resultMap) {
        //更新设备状态 获取设备
        logger.info("chang Task State msgId:{}, token:{},", msgId, token);
        DeviceProcessDetail deviceProcessDetail = new DeviceProcessDetail();
        for (Map.Entry<String, Integer> entry : resultMap.entrySet()) {
            DeviceInfo deviceInfo = deviceDao.findByEmecid(entry.getKey());
            if (deviceInfo != null) {
                deviceProcessDetail.setDetailId(Long.valueOf(msgId));
                deviceProcessDetail.setStatus(entry.getValue());
                deviceProcessDetail.setDeviceId(deviceInfo.getId());
                deviceTaskDao.changeTaskState(deviceProcessDetail);
            }
        }
        //判断测试任务是否全部完成 以更新流程状态
        try {
            ThreadDistribution.getInstance().submit(new Callable<Object>() {
                @Override
                public Object call() throws Exception {
                    List<DeviceProcessDetail> result = deviceTaskDao.findByRun(msgId, CfgContants.DEVICE_STATUS_RUN);
                    if (CollectionUtils.isEmpty(result)) {
                        //所有设备完成测试 更新状态
                        ProcessStepItem processStepItem = processStepItemDao.getById(String.valueOf(msgId));
                        flowListDao.updateState(String.valueOf(processStepItem.getProcessId()), CfgContants.STATE_TASK_END);
                        processStepItem.setStatus(CfgContants.STATE_TASK_END);
                        processStepItemDao.updateState(processStepItem);
                    }
                    return null;
                }
            });
        } catch (Exception ex) {
            logger.info("change process,processStepItem fail", ex);
        }
    }
}
