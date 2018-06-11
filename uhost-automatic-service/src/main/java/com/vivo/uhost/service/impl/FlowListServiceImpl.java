/*
*
* Created by IntelliJ IDEA.
  User: dongjiajin
  Date: 2018/1/9
  Time: 16:15
  To change this template use File | Settings | File Templates.
*
* */

package com.vivo.uhost.service.impl;

import com.vivo.uhost.core.domain.bo.FlowListBO;
import com.vivo.uhost.service.IFlowListService;
import com.vivo.uhost.dal.dao.IFlowListDao;
import com.vivo.uhost.dal.entity.FlowList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service("flowListService")
public class FlowListServiceImpl implements IFlowListService{

    @Autowired
    private IFlowListDao flowListDao;


    @Override
    public List<FlowList> findAll() {
        List<FlowList> lists = new ArrayList<FlowList>();
        return flowListDao.selectAll();
    }

    @Override
    public void add(FlowList flowlist) {
        flowListDao.insert(flowlist);
    }

    @Override
    public void delete(Long id) {
        flowListDao.delete(id);
    }

    @Override
    public Long count(FlowListBO flowlistBO) {
        FlowList flowList = flowlistBO.toEntity();
        return flowListDao.count(flowList);
    }

    @Override
    public void edit(FlowList flowlist) {
        flowListDao.update(flowlist);
    }

    @Override
    public List<FlowList> findList(FlowListBO flowlistBO, Pageable pageable) {
        FlowList flowList = flowlistBO.toEntity();
        return flowListDao.queryList(flowList,pageable);
    }

    @Override
    public List<FlowList> findByIds(List<String> processIds) {
        return flowListDao.findByIds(processIds);
    }

    @Override
    public FlowList findById(String processId) {
        return flowListDao.findById(processId);
    }

    @Override
    public void updateState(String processId, Integer state) {
        flowListDao.updateState(processId, state);
    }

    @Override
    public void setCount(int count, String processId) {
        flowListDao.setCount(count, processId);
    }

}
