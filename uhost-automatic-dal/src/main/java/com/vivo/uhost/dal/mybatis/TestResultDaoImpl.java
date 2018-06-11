package com.vivo.uhost.dal.mybatis;

import com.vivo.uhost.dal.dao.ITestResultDao;
import com.vivo.uhost.dal.entity.TestResult;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gaojun on 2018/1/3.
 */
@Repository
public class TestResultDaoImpl implements ITestResultDao {
    @Autowired
    private SqlSession sqlSession;
    @Override
    public List<TestResult> selectAll() {
        return sqlSession.selectList("TestResultsMapper.selectAll");
    }

    @Override
    public void insert(com.vivo.uhost.comm.pojo.TestResult testResult) {
      sqlSession.insert("TestResultsMapper.insert",testResult);
    }

    @Override
    public void delete(Long id) {
      sqlSession.delete("TestResultsMapper.delete",id);
    }

    @Override
    public void update(TestResult testResult) {
      sqlSession.update("TestResultsMapper.update",testResult);
    }

    @Override
    public List<TestResult> queryList(TestResult testResult, Pageable pageable) {

         if (pageable!=null){
             testResult.setOffset(pageable.getOffset());
             testResult.setPageSize(pageable.getPageSize());
         }
        return sqlSession.selectList("TestResultsMapper.selectList", testResult);
    }

    @Override
    public Long count(TestResult testResult) {
        Map<String, Object> map = new HashMap<>();
       /* map.put("userName", device.getState());
        map.put("ccId", device.getFactory());*/
        return sqlSession.selectOne("TestResultMapper.count", testResult);
    }

    @Override
    public List<TestResult> selectListByCondition(TestResult device) {
        return sqlSession.selectList("TestResultsMapper.selectListByCondition", device);
    }

    @Override
    public void updateByNodeId(List<TestResult> device) {
        sqlSession.update("TestResultsMapper.updateByNodeId",device);
    }
}
