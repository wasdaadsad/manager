package com.vivo.uhost.dal.mybatis;

import com.vivo.uhost.dal.dao.IToolVersionDao;
import com.vivo.uhost.dal.entity.ToolVersion;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author litingfa  2018/3/26 20:25
 * @version 1.0
 * @description
 */
@Repository
public class ToolVersionDaoImpl implements IToolVersionDao{

    @Autowired
    private SqlSession sqlSession;

    @Override
    public List<ToolVersion> findList(ToolVersion toolVersion, Pageable pageable) {
        Map<String, Object> params = new HashMap<>();
        params.put("toolVersion", toolVersion);
        if (pageable != null) {
            params.put("offset", pageable.getOffset());
            params.put("pageSize", pageable.getPageSize());
        }
        return sqlSession.selectList("ToolVersionMapper.findList",params);
    }

    @Override
    public long count(ToolVersion toolVersion) {
        return sqlSession.selectOne("ToolVersionMapper.count",toolVersion);
    }

    @Override
    public void addToolVersion(ToolVersion toolVersion) {
        sqlSession.insert("ToolVersionMapper.addToolVersion",toolVersion);
    }

    @Override
    public void changeVersionState(ToolVersion toolVersion) {
        sqlSession.update("ToolVersionMapper.changeVersionState",toolVersion);
    }

    @Override
    public List<ToolVersion> getByState(Integer toolVersionValid) {
        Map<String, Object> params = new HashMap<>();
        params.put("state", toolVersionValid);
        return sqlSession.selectList("ToolVersionMapper.getByState",params);
    }

    @Override
    public List<ToolVersion> getVersionList(ToolVersion toolVersion) {
        return sqlSession.selectList("ToolVersionMapper.getVersionList",toolVersion);
    }

    @Override
    public ToolVersion findById(Long aLong) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", aLong);
        return sqlSession.selectOne("ToolVersionMapper.findById",params);
    }

    @Override
    public ToolVersion findVersion(ToolVersion toolVersion) {
        return sqlSession.selectOne("ToolVersionMapper.findVersion",toolVersion);
    }
}
