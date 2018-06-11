package com.vivo.uhost.service.impl;

import com.vivo.uhost.service.ITestItemService;
import com.vivo.uhost.dal.entity.TestItem;
import com.vivo.uhost.dal.mybatis.TestItemDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gaojun on 2018/1/3.
 */
@Service("testItemService")
public class TestItemServiceImpl implements ITestItemService {
    @Autowired
    private TestItemDao testItemDao;
    @Override
    public List<TestItem> findAll() {
        List<TestItem> testItems=new ArrayList<TestItem>();
        testItems= testItemDao.selectAll();
        return testItems;
    }

    @Override
    public void add(TestItem testItem) {
        testItemDao.insert(testItem);
    }

    @Override
    public void delete(Long id) {
        testItemDao.delete(id);
    }

    @Override
    public Long count(TestItem testItem) {
       return testItemDao.count(testItem);
    }

    @Override
    public void edit(TestItem testItem) {
        testItemDao.update(testItem);
    }

    @Override
    public List<TestItem> findList(TestItem testItem, Pageable pageable) {
        return testItemDao.queryList(testItem,pageable);
    }

}
