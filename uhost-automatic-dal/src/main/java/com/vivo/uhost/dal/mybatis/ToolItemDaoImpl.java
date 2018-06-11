package com.vivo.uhost.dal.mybatis;

import com.vivo.uhost.dal.dao.IToolItemDao;
import com.vivo.uhost.dal.entity.ToolItem;
import com.vivo.uhost.dal.entity.process.TestItem;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author litingfa  2018/3/27 17:15
 * @version 1.0
 * @description
 */
@Repository
public class ToolItemDaoImpl implements IToolItemDao {

    @Autowired
    private SqlSession sqlSession;

    @Override
    public void insertTestItem(ToolItem testItem) {
        sqlSession.insert("ToolItemMapper.addToolItem",testItem);

    }

    @Override
    public void updateItem(ToolItem testItem) {
        sqlSession.update("ToolItemMapper.updateItem",testItem);

    }

    @Override
    public List<ToolItem> findByTid(Long toolId) {
        Map<String, Object> params = new HashMap<>();
        params.put("toolId", toolId);
        params.put("state",1);
        List<ToolItem> toolItems = sqlSession.selectList("ToolItemMapper.findByTid", params);
        return toolItems;
    }

    @Override
    public void deleteById(Long id) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        sqlSession.delete("ToolItemMapper.deleteById",params);
    }

    @Override
    public List<ToolItem> getByTaskId(Integer taskId) {
        Map<String, Object> params = new HashMap<>();
        params.put("taskid", taskId);
        return sqlSession.selectList("ToolItemMapper.getByTaskId", params);
    }
}
