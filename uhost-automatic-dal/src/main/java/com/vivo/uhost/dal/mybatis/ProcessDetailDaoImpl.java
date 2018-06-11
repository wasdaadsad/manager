package com.vivo.uhost.dal.mybatis;

import com.vivo.uhost.dal.dao.IProcessDetailDao;
import com.vivo.uhost.dal.entity.ProcessDetail;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author litingfa  2018/3/23 16:10
 * @version 1.0
 * @description
 */
@Repository
public class ProcessDetailDaoImpl implements IProcessDetailDao {

    @Autowired
    private SqlSession sqlSession;

    @Override
    public List<ProcessDetail> findByPid(String processId) {
        Map<String, Object> params = new HashMap<>();
        params.put("processId", processId);
        return sqlSession.selectList("ProcessDetailMapper.findByPid", params);
    }
}
