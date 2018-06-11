package com.vivo.uhost.dal.mybatis;

import com.vivo.uhost.dal.dao.ITestItemDao;
import com.vivo.uhost.dal.entity.TestItem;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/16.
 */
@Repository
public class TestItemDao implements ITestItemDao {
    @Autowired
    private SqlSession sqlSession;
    @Override
    public List<TestItem> selectAll() {
        return sqlSession.selectList("TestItemMapper.selectAll");
    }

    @Override
    public void insert(TestItem testItem) {
        sqlSession.insert("TestItemMapper.insert",testItem);
    }

    @Override
    public void delete(Long id) {
        sqlSession.delete("TestItemMapper.delete",id);
    }

    @Override
    public void update(TestItem device) {
        sqlSession.update("TestItemMapper.update",device);
    }

    @Override
    public List<TestItem> queryList(TestItem testItem, Pageable pageable) {

        if (pageable!=null){
            testItem.setOffset(pageable.getOffset());
            testItem.setPageSize(pageable.getPageSize());
        }
        return sqlSession.selectList("TestItemMapper.selectList", testItem);
    }

    @Override
    public Long count(TestItem testItem) {
        Map<String, Object> map = new HashMap<>();
       /* map.put("userName", device.getState());
        map.put("ccId", device.getFactory());*/
        return sqlSession.selectOne("TestItemMapper.count", testItem);
    }
}
