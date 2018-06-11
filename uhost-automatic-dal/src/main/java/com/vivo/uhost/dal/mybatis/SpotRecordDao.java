package com.vivo.uhost.dal.mybatis;

import com.vivo.uhost.dal.dao.ISpotRecordDao;
import com.vivo.uhost.dal.entity.SpotRecord;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author LuZhiWei
 * @version 1.0
 * @describe
 * @date 2018/1/11
 */
@Repository
public class SpotRecordDao implements ISpotRecordDao{

    @Autowired
    private SqlSession sqlSession;

    @Override
    public List<SpotRecord> selectAll() {
        return sqlSession.selectList("SpotRecord.selectAll");
    }

    @Override
    public void insert(SpotRecord spotRecord) {
        sqlSession.insert("SpotRecord.insert", spotRecord);
    }

    @Override
    public void update(SpotRecord spotRecord) {
        sqlSession.update("SpotRecord.update", spotRecord);
    }

    @Override
    public void delete(Integer id) {
        sqlSession.delete("SpotRecord.delete", id);
    }

    @Override
    public List<SpotRecord> queryList(SpotRecord spotRecord, Pageable pageable) {
            Map<String, Object> map = new HashMap<>();
            map.put("spotRecord", spotRecord);
            map.put("offset", pageable.getOffset());
            map.put("pageSize", pageable.getPageSize());
            return sqlSession.selectList("SpotRecord.selectList", map);
    }

    @Override
    public Long count(SpotRecord spotRecord) {
        return sqlSession.selectOne("SpotRecord.count", spotRecord);
    }

    @Override
    public SpotRecord getBySpotRecordId(Integer id) {
        return  sqlSession.selectOne("SpotRecord.getById",id);
    }
}
