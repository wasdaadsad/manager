package com.vivo.uhost.dal.mybatis;

import com.vivo.uhost.dal.dao.IProcessInfoDao;
import com.vivo.uhost.dal.entity.process.TestProcess;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author litingfa  2018/3/31 19:40
 * @version 1.0
 * @description
 */
@Repository
public class ProcessInfoDaoImpl implements IProcessInfoDao {

    @Autowired
    private SqlSession sqlSession;

    @Override
    public void add(TestProcess testProcess) {
        sqlSession.insert("ProcessMapper.addProcess", testProcess);
    }
}
