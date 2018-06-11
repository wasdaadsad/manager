package com.vivo.uhost.dal.dao;

import com.vivo.uhost.dal.entity.SpotRecord;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author LuZhiWei
 * @version 1.0
 * @describe
 * @date 2018/1/11
 */
public interface ISpotRecordDao
{
    List<SpotRecord> selectAll();

    void insert(SpotRecord spotRecord);

    void update(SpotRecord spotRecord);

    void delete(Integer id);

    //搜索展示列表实现OK
    List<SpotRecord> queryList(SpotRecord spotRecord, Pageable pageable);

    //数据统计OK
    Long count(SpotRecord spotRecord);

    SpotRecord getBySpotRecordId(Integer id);
}
