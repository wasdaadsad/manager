package com.vivo.uhost.service.impl;

import com.vivo.uhost.dal.dao.ICheckDeviceDao;
import com.vivo.uhost.dal.entity.CheckDevice;
import com.vivo.uhost.service.ICheckDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("checkDeviceService")
public class CheckDeviceServiceImpl implements ICheckDeviceService {

    @Autowired
    private ICheckDeviceDao checkdeviceDao;


    @Override
    public List<CheckDevice> findAll() {
        return checkdeviceDao.selectAll();
    }

    @Override
    public void add(CheckDevice device) {
        checkdeviceDao.insert(device);
    }

    @Override
    public void delete(Long id) {
        checkdeviceDao.delete(id);
    }

    @Override
    public Long count(CheckDevice device) {
        return checkdeviceDao.count(device);
    }

    @Override
    public void edit(CheckDevice device) {
        checkdeviceDao.update(device);
    }

    @Override
    public List<CheckDevice> findList(CheckDevice device, Pageable pageable) {
        return checkdeviceDao.queryList(device, pageable);
    }
}
