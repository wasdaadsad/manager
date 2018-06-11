package com.vivo.uhost.dal.mybatis;

import com.vivo.uhost.dal.dao.IOsVersionDao;
import com.vivo.uhost.dal.entity.OsVersion;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author LuZhiWei
 * @version 1.0
 * @describe
 * @date 2018/2/8
 */
@Repository
public class OsVersionDao implements IOsVersionDao{

    @Autowired
    private SqlSession sqlSession;

    @Override
    public List<OsVersion> selectAll() {
        return sqlSession.selectList("OsVersion.selectAll");
    }

    @Override
    public void insert(OsVersion osVersion) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = sdf.format(Calendar.getInstance().getTime());
        osVersion.setCreateTime(time);
        sqlSession.insert("OsVersion.insert", osVersion);
    }

    @Override
    public void update(OsVersion osVersion) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = sdf.format(Calendar.getInstance().getTime());
        osVersion.setCreateTime(time);
        sqlSession.update("OsVersion.update",osVersion);
    }

    @Override
    public void delete(Integer id) {
        sqlSession.delete("OsVersion.delete",id);
    }

    @Override
    public List<OsVersion> queryList(OsVersion osVersion, Pageable pageable) {
        Map<String, Object> map = new HashMap<>();
        map.put("osVersion", osVersion);
        map.put("offset", pageable.getOffset());
        map.put("pageSize", pageable.getPageSize());
        return sqlSession.selectList("OsVersion.selectList", map);
    }

    @Override
    public Long count(OsVersion osVersion) {
        return sqlSession.selectOne("OsVersion.count", osVersion);
    }

    @Override
    public OsVersion getOsVersionById(Integer id) {
        return sqlSession.selectOne("OsVersion.selectById", id);
    }

    @Override
    public OsVersion getByVersionCode(String versionCode) {
        return sqlSession.selectOne("OsVersion.selectByVersionCode", versionCode);
    }

    @Override
    public List<OsVersion> getVersions(OsVersion osVersion) {
        return sqlSession.selectList("OsVersion.getVersions",osVersion);
    }

    @Override
    public List<OsVersion> selectAllDistinctModelId() {
        return sqlSession.selectList("OsVersion.selectAllDistinctModelId");
    }

}
