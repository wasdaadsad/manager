package com.vivo.uhost.service;

import com.vivo.uhost.dal.entity.TestItem;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by gaojun on 2018/1/3.
 */
public interface ITestItemService {
    /**
     * 查询所有device
     *
     * @return
     */
    List<TestItem> findAll();

    /**
     * 添加一条device
     *
     * @param testItem
     */
    void add(TestItem testItem);


    /**
     * 删除一条device
     *
     * @param id
     */
    void delete(Long id);
    Long count(TestItem testItem);
    /**
     * 编辑device
     *
     * @param testItem
     */
    void edit(TestItem testItem);

    /**
     * 查询device列表
     *
     * @param testItem
     * @param pageable
     * @return
     */
    List<TestItem> findList(TestItem testItem, Pageable pageable);
}
