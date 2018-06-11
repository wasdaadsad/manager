package com.vivo.uhost.dal.dao;

import com.vivo.uhost.dal.entity.TestResult;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by gaojun on 2018/1/3.
 */
public interface ITestResultDao {
    List<TestResult> selectAll();

    void insert(com.vivo.uhost.comm.pojo.TestResult testResult);

    void delete(Long id);

    void update(TestResult testResult);

    List<TestResult> queryList(TestResult testResult, Pageable pageable);

    Long count(TestResult testResult);

    List<TestResult> selectListByCondition(TestResult testResult);

    void updateByNodeId(List<TestResult> testResult);
}
