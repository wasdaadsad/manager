package com.vivo.uhost.service;

import com.vivo.uhost.dal.entity.TestResult;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by gaojun on 2018/1/3.
 */
public interface ITestResultService {
    /**
     * 查询所有testResult
     *
     * @return
     */
    List<TestResult> findAll();

    List<TestResult> selectListByCondition(TestResult testResult);

    void updateByNodeId(List<TestResult> testResult);


    /**
     * 添加一条 testResult
     *
     * @param testResult
     */
    void add(com.vivo.uhost.comm.pojo.TestResult testResult);


    /**
     * 删除一条testResult
     *
     * @param id
     */
    void delete(Long id);

    Long count(TestResult testResult);

    /**
     * 编辑device
     *
     * @param testResult
     */
    void edit(TestResult testResult);

    /**
     * 查询testResult列表
     *
     * @param testResult
     * @param pageable
     * @return
     */
    List<TestResult> findList(TestResult testResult, Pageable pageable);
}
