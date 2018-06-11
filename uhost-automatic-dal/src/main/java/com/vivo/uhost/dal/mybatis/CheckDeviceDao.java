package com.vivo.uhost.dal.mybatis;

import com.vivo.uhost.dal.dao.ICheckDeviceDao;
import com.vivo.uhost.dal.entity.CheckDevice;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository
public class CheckDeviceDao implements ICheckDeviceDao {

    @Autowired
    private SqlSession sqlSession;


    @Override
    public List<CheckDevice> selectAll() {
        return sqlSession.selectList("CheckDeviceMapper.selectAll");
    }

    @Override
    public void insert(CheckDevice device) {
        sqlSession.insert("CheckDeviceMapper.insert", device);
    }

    @Override
    public void delete(Long id) {
        sqlSession.delete("CheckDeviceMapper.delete", id);
    }

    @Override
    public void update(CheckDevice device) {
        sqlSession.update("CheckDeviceMapper.update", device);
    }

    @Override
    public List<CheckDevice> queryList(CheckDevice device, Pageable pageable) {
        if(pageable != null){
            device.setOffset(pageable.getOffset());
            device.setPageSize(pageable.getPageSize());
        }
        return sqlSession.selectList("CheckDeviceMapper.selectList",device);
    }

    @Override
    public Long count(CheckDevice device) {
        Map<String, Object> map = new HashMap<>();
        return sqlSession.selectOne("CheckDeviceMapper.count", device);
    }
}
