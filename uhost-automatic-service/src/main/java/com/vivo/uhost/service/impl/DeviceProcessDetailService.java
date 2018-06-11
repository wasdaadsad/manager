package com.vivo.uhost.service.impl;

import com.vivo.uhost.dal.dao.IDeviceProcessDetailDao;
import com.vivo.uhost.dal.entity.DeviceProcessDetail;
import com.vivo.uhost.dal.entity.ProcessDetail;
import com.vivo.uhost.service.IDeviceProcessDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author litingfa  2018/4/8 14:49
 * @version 1.0
 * @description
 */
@Service
public class DeviceProcessDetailService implements IDeviceProcessDetailService {

    @Autowired
    private IDeviceProcessDetailDao deviceProcessDetailDao;

    @Override
    public List<DeviceProcessDetail> findByPid(String pId) {
        return deviceProcessDetailDao.findByPid(pId);
    }
}
