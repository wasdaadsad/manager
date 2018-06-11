package com.vivo.uhost.dal.mybatis;

import com.vivo.uhost.dal.dao.IProcessStepItemDao;
import com.vivo.uhost.dal.entity.ProcessStepItem;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author litingfa  2018/3/23 19:27
 * @version 1.0
 * @description
 */
@Repository
public class ProcessStepItemDaoImpl implements IProcessStepItemDao {

    @Autowired
    private SqlSession sqlSession;

    @Override
    public List<ProcessStepItem> findByPid(String processId) {
        Map<String, Object> params = new HashMap<>();
        params.put("processId", processId);
        return sqlSession.selectList("ProcessStepItemMapper.findByPid", params);
    }

    @Override
    public ProcessStepItem getById(String id) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        return sqlSession.selectOne("ProcessStepItemMapper.getById", params);
    }

    @Override
    public void add(ProcessStepItem processStepItem) {
        sqlSession.insert("ProcessStepItemMapper.addItem", processStepItem);
    }

    @Override
    public void updateState(ProcessStepItem processStepItem) {
        sqlSession.update("ProcessStepItemMapper.updateState", processStepItem);
    }

    @Override
    public ProcessStepItem runProcessDetail(long id, Integer stateTaskRunning) {
        Map<String, Object> params = new HashMap<>();
        params.put("processId", id);
        params.put("state", stateTaskRunning);
        return sqlSession.selectOne("ProcessStepItemMapper.runProcessDetail", params);
    }
}
