/*
*
* Created by IntelliJ IDEA.
  User: dongjiajin
  Date: 2018/1/9
  Time: 16:15
  To change this template use File | Settings | File Templates.
*
* */

package com.vivo.uhost.dal.mybatis;

import com.vivo.uhost.dal.dao.IFlowListDao;
import com.vivo.uhost.dal.entity.FlowList;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class FlowListDao implements IFlowListDao {

    @Autowired
    private SqlSession sqlSession;


    @Override
    public List<FlowList> selectAll() {
        return sqlSession.selectList("ProcessMapper.selectAll");
    }

    @Override
    public void insert(FlowList flowlist) {
        sqlSession.insert("FlowListMapper.insert", flowlist);
    }

    @Override
    public void delete(Long id) {
        sqlSession.delete("FlowListMapper.delete", id);
    }

    @Override
    public void update(FlowList flowlist) {
        sqlSession.update("FlowListMapper.update", flowlist);
    }

    @Override
    public List<FlowList> queryList(FlowList flowlist, Pageable pageable) {
        if (pageable != null) {
            flowlist.setOffset(pageable.getOffset());
            flowlist.setPageSize(pageable.getPageSize());
        }
        return sqlSession.selectList("ProcessMapper.selectList", flowlist);
    }

    @Override
    public Long count(FlowList flowlist) {
        Map<String, Object> map = new HashMap<>();
        return sqlSession.selectOne("ProcessMapper.count", flowlist);
    }

    @Override
    public List<FlowList> findByIds(List<String> processIds) {
        return sqlSession.selectList("ProcessMapper.findByIds", processIds);
    }

    @Override
    public FlowList findById(String processId) {
        Map<String, Object> map = new HashMap<>();
        map.put("pid", processId);
        return sqlSession.selectOne("ProcessMapper.findById", map);
    }

    @Override
    public void updateState(String processId, Integer state) {
        Map<String, Object> map = new HashMap<>();
        map.put("pid", processId);
        map.put("state", state);
        map.put("startTime", new Date());
        sqlSession.update("ProcessMapper.updateState", map);
    }

    @Override
    public void setCount(int count, String processId) {
        Map<String, Object> map = new HashMap<>();
        map.put("pid", processId);
        map.put("total", count);
        sqlSession.update("ProcessMapper.setCount", map);
    }

}
