package com.vivo.uhost.service.impl;

import com.vivo.uhost.core.domain.bo.UhostInfoBO;
import com.vivo.uhost.dal.dao.IUhostInfoDao;
import com.vivo.uhost.dal.entity.DeviceInfo;
import com.vivo.uhost.dal.entity.UhostInfo;
import com.vivo.uhost.service.IUhostInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author litingfa  2018/3/19 17:37
 * @version 1.0
 * @description
 */
@Service("uhostInfoService")
public class UhostInfoServiceImpl implements IUhostInfoService {

    @Autowired
    private IUhostInfoDao uhostDao;

    @Override
    public UhostInfoBO findByUhost(UhostInfo uhost) {
        UhostInfo res = uhostDao.findByUhost(uhost);
        return new UhostInfoBO(res);
    }

    @Override
    public void editUhost(UhostInfo uhostInfo) {
        uhostDao.editUhost(uhostInfo);
    }

    @Override
    public UhostInfo findByDevice(Long deviceId) {
        return uhostDao.findByDevice(deviceId);
    }

    @Override
    public List<UhostInfo> findList(UhostInfo uhostInfo, Pageable pageable) {
        return uhostDao.findList(uhostInfo, pageable);
    }

    @Override
    public long count(UhostInfo uhostInfo) {
        return uhostDao.count(uhostInfo);
    }

    @Override
    public List<UhostInfo> findAll() {
       return uhostDao.selectAll();
    }

    @Override
    public UhostInfo selectByToken(UhostInfo uhost) {
        return uhostDao.selectByToken(uhost);
    }

    @Override
    public UhostInfo findByIdentify(String identify) {
        return uhostDao.findByIdentify(identify);
    }

    @Override
    public void addUhost(UhostInfo uhost) {
        uhostDao.addUhost(uhost);
    }
}
