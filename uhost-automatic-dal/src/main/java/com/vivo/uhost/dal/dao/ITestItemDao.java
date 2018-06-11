package com.vivo.uhost.dal.dao;

import com.vivo.uhost.dal.entity.TestItem;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by Administrator on 2018/1/16.
 */
public interface ITestItemDao {
    List<TestItem> selectAll();

    void insert(TestItem testItem);

    void delete(Long id);

    void update(TestItem testItem);

    List<TestItem> queryList(TestItem testItem, Pageable pageable);

    Long count(TestItem testItem);
}
