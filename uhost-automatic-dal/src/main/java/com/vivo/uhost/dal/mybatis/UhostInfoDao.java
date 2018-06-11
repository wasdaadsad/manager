package com.vivo.uhost.dal.mybatis;

import com.vivo.uhost.dal.dao.IUhostInfoDao;
import com.vivo.uhost.dal.entity.DeviceInfo;
import com.vivo.uhost.dal.entity.UhostInfo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author litingfa  2018/3/19 17:39
 * @version 1.0
 * @description
 */
@Repository
public class UhostInfoDao implements IUhostInfoDao {

    @Autowired
    private SqlSession sqlSession;

    @Override
    public UhostInfo findByUhost(UhostInfo uhost) {
        return sqlSession.selectOne("UhostInfoMapper.findByUhost", uhost);
    }

    @Override
    public UhostInfo findByIdentify(String identify) {
        UhostInfo uhostInfo = new UhostInfo();
        uhostInfo.setUidentify(identify);
        return sqlSession.selectOne("UhostInfoMapper.findByUhost", uhostInfo);
    }

    @Override
    public UhostInfo selectByToken(UhostInfo uhost) {
        return sqlSession.selectOne("UhostInfoMapper.selectByToken",uhost);
    }

    @Override
    public void addUhost(UhostInfo uhost) {
        sqlSession.insert("UhostInfoMapper.insert", uhost);
    }

    @Override
    public void editUhost(UhostInfo uhostInfo) {
        sqlSession.insert("UhostInfoMapper.update", uhostInfo);
    }

    @Override
    public UhostInfo findByToken(String token) {
        return sqlSession.selectOne("UhostInfoMapper.findByToken",token);
    }

    @Override
    public UhostInfo findByDevice(Long deviceId) {
        DeviceInfo deviceInfo = new DeviceInfo();
        deviceInfo.setId(deviceId);
        return sqlSession.selectOne("UhostInfoMapper.findByDevice",deviceInfo);
    }

    @Override
    public List<UhostInfo> findList(UhostInfo uhostInfo, Pageable pageable) {
        Map<String, Object> params = new HashMap<>();
        params.put("uhostInfo", uhostInfo);
        if (pageable != null) {
            params.put("offset", pageable.getOffset());
            params.put("pageSize", pageable.getPageSize());
        }
        return sqlSession.selectList("UhostInfoMapper.findList", params);
    }

    @Override
    public long count(UhostInfo uhostInfo) {
        return sqlSession.selectOne("UhostInfoMapper.count", uhostInfo);
    }

    @Override
    public List<UhostInfo> selectAll() {
        return sqlSession.selectList("UhostInfoMapper.selectAll");
    }
}
