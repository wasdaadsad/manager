package com.vivo.uhost.service.impl;

import com.bbkmobile.iqoo.common.util.DateUtil;
import com.vivo.uhost.core.util.CfgContants;
import com.vivo.uhost.dal.dao.IDeviceDao;
import com.vivo.uhost.dal.dao.IUhostInfoDao;
import com.vivo.uhost.dal.entity.DeviceInfo;
import com.vivo.uhost.dal.entity.DeviceProcessDetail;
import com.vivo.uhost.dal.entity.FlowList;
import com.vivo.uhost.dal.entity.UhostInfo;
import com.vivo.uhost.service.IDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.events.Event;

import java.util.Date;
import java.util.List;

/**
 * Created by gaojun on 2018/1/3.
 */
@Service("deviceService")
public class DeviceServiceImpl implements IDeviceService {

    @Autowired
    private IDeviceDao IDeviceDao;

    @Autowired
    private IUhostInfoDao uhostInfoDao;

    @Override
    public List<DeviceInfo> findAll() {
        return IDeviceDao.selectAll();
    }

    @Override
    public void add(DeviceInfo device) {
        IDeviceDao.insert(device);
    }

    @Override
    public List<DeviceInfo> selectListByUhostId(DeviceInfo device) {
        return IDeviceDao.selectListByUhostId(device);
    }

    @Override
    public void updateByNodeId(List<DeviceInfo> devices) {
        IDeviceDao.updateByNodeId(devices);
    }

    @Override
    public void delete(Long id) {
        IDeviceDao.delete(id);
    }

    @Override
    public Long count(DeviceInfo device) {
        return IDeviceDao.count(device);
    }

    @Override
    public void edit(DeviceInfo device) {
        IDeviceDao.update(device);
    }

    @Override
    public List<DeviceInfo> findList(DeviceInfo device, Pageable pageable) {
        return IDeviceDao.queryList(device, pageable);
    }

    @Override
    public List<FlowList> findByIds(List<String> deviceIds) {
        return null;
    }

    @Override
    public void addProcessDetail(DeviceProcessDetail detail) {
        IDeviceDao.addProcessDetail(detail);
    }

    @Override
    public List<DeviceProcessDetail> findByDetail(DeviceProcessDetail detail) {
        return IDeviceDao.findByDetail(detail);
    }

    @Override
    public List<DeviceProcessDetail> findDetailByPid(FlowList flowList, Pageable pageable) {
        Integer status = null;
        String statusRes = flowList.getStatus();
        if (Integer.parseInt(statusRes) == CfgContants.STATE_TASK_NOT_RUN) {
            status = 0;
            flowList.setTaskId(null);
        }
        return IDeviceDao.findByProcess(flowList, pageable, status);
    }

    @Override
    public long countDetail(FlowList flowList) {
        return IDeviceDao.countDetail(flowList);
    }

    @Override
    public void setDeviceState(DeviceInfo deviceInfo, Integer state, String token) {
        DeviceInfo res = IDeviceDao.selectListByEntity(deviceInfo);
        deviceInfo.setState(state);
        if (res == null) {
            //新增
            UhostInfo uhostInfo = uhostInfoDao.findByToken(token);
            deviceInfo.setAgingRack(uhostInfo.getAgingRack());
            deviceInfo.setAgingRoom(uhostInfo.getAgingRoom());
            deviceInfo.setFactory(uhostInfo.getFactory());
            deviceInfo.setUhost(uhostInfo.getToken());
            deviceInfo.setUhostId(uhostInfo.getId());
            deviceInfo.setWorkShop(uhostInfo.getWorkShop());
            IDeviceDao.insert(deviceInfo);
        } else {
            //更新
            UhostInfo uhostInfo = uhostInfoDao.findByToken(token);
            deviceInfo.setAgingRack(uhostInfo.getAgingRack());
            deviceInfo.setAgingRoom(uhostInfo.getAgingRoom());
            deviceInfo.setFactory(uhostInfo.getFactory());
            deviceInfo.setUhost(uhostInfo.getToken());
            deviceInfo.setUhostId(uhostInfo.getId());
            deviceInfo.setWorkShop(uhostInfo.getWorkShop());
            deviceInfo.setId(res.getId());
            IDeviceDao.update(deviceInfo);
        }
    }

    @Override
    public DeviceInfo selectListByEntity(DeviceInfo deviceInfo) {
        return IDeviceDao.selectListByEntity(deviceInfo);
    }

    @Override
    public List<DeviceProcessDetail> findFailList(DeviceProcessDetail deviceProcessDetail, Pageable pageable) {
        return IDeviceDao.findFailList(deviceProcessDetail, pageable);
    }

    @Override
    public DeviceInfo getById(Long deviceId) {
        return IDeviceDao.getById(deviceId);
    }


    @Override
    public List<DeviceInfo> selectListByToken(String token) {
        return IDeviceDao.selectListByToken(token);
    }

    @Override
    public void updateUhost(DeviceInfo deviceInfo) {
        IDeviceDao.updateUhost(deviceInfo);
    }


}
