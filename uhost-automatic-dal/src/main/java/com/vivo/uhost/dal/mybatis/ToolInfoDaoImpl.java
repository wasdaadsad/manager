package com.vivo.uhost.dal.mybatis;

import com.vivo.uhost.dal.dao.IToolInfoDao;
import com.vivo.uhost.dal.entity.ToolInfo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author litingfa  2018/3/26 11:47
 * @version 1.0
 * @description
 */
@Repository
public class ToolInfoDaoImpl implements IToolInfoDao {

    @Autowired
    private SqlSession sqlSession;

    @Override
    public long count(ToolInfo toolInfo) {
        return sqlSession.selectOne("ToolInfoMapper.count",toolInfo);
    }

    @Override
    public List<ToolInfo> findList(ToolInfo toolInfo, Pageable pageable) {
        Map<String, Object> params = new HashMap<>();
        params.put("toolInfo", toolInfo);
        if (pageable != null) {
            params.put("offset", pageable.getOffset());
            params.put("pageSize", pageable.getPageSize());
        }
        return sqlSession.selectList("ToolInfoMapper.findList", params);
    }

    @Override
    public void changeState(ToolInfo toolInfo) {
        sqlSession.update("ToolInfoMapper.changeState", toolInfo);
    }

    @Override
    public ToolInfo findById(ToolInfo toolInfo) {
        return sqlSession.selectOne("ToolInfoMapper.findById",toolInfo);
    }

    @Override
    public long addTool(ToolInfo toolInfo) {
        return sqlSession.insert("ToolInfoMapper.addTool", toolInfo);
    }

    @Override
    public ToolInfo findByName(String toolName) {
        Map<String, Object> params = new HashMap<>();
        params.put("toolName",toolName);
        return sqlSession.selectOne("ToolInfoMapper.findByName", params);
    }

    @Override
    public void update(ToolInfo toolInfo) {
        sqlSession.update("ToolInfoMapper.updateTool",toolInfo);
    }

    @Override
    public List<ToolInfo> getAllToolList(Integer toolStateValid) {
        Map<String, Object> params = new HashMap<>();
        params.put("state",toolStateValid);
        return sqlSession.selectList("ToolInfoMapper.getAllList",params);
    }
}
