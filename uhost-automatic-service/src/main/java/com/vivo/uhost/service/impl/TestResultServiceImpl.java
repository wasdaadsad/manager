package com.vivo.uhost.service.impl;

import com.vivo.uhost.dal.dao.ITestResultDao;
import com.vivo.uhost.dal.entity.TestResult;
import com.vivo.uhost.service.ITestResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by gaojun on 2018/1/3.
 */
@Service("testResultService")
public class TestResultServiceImpl implements ITestResultService {
    @Autowired
    private ITestResultDao ITestResultDao;

    @Override
    public List<TestResult> findAll() {
        return ITestResultDao.selectAll();
    }

    @Override
    public void add(com.vivo.uhost.comm.pojo.TestResult testResult) {
        ITestResultDao.insert(testResult);
    }

    @Override
    public List<TestResult> selectListByCondition(TestResult testResult) {
        return ITestResultDao.selectListByCondition(testResult);
    }

    @Override
    public void updateByNodeId(List<TestResult> devices) {
        ITestResultDao.updateByNodeId(devices);
    }


    @Override
    public void delete(Long id) {
        ITestResultDao.delete(id);
    }

    @Override
    public Long count(TestResult testResult) {
        return ITestResultDao.count(testResult);
    }

    @Override
    public void edit(TestResult testResult) {
        ITestResultDao.update(testResult);
    }

    @Override
    public List<TestResult> findList(TestResult testResult, Pageable pageable) {
        return ITestResultDao.queryList(testResult, pageable);
    }

}
