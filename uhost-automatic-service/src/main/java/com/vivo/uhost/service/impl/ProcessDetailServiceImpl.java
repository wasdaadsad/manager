package com.vivo.uhost.service.impl;

import com.vivo.uhost.dal.dao.IProcessDetailDao;
import com.vivo.uhost.dal.entity.ProcessDetail;
import com.vivo.uhost.service.IProcessDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author litingfa  2018/3/23 15:28
 * @version 1.0
 * @description
 */
@Service
public class ProcessDetailServiceImpl implements IProcessDetailService {

    @Autowired
    private IProcessDetailDao processDetailDao;

    @Override
    public List<ProcessDetail> findByPid(String processId) {
        return processDetailDao.findByPid(processId);
    }
}
