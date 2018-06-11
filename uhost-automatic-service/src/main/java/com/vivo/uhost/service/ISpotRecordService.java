package com.vivo.uhost.service;

import com.vivo.console.data.domain.SimplePage;
import com.vivo.uhost.core.domain.bo.SpotRecordBO;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author LuZhiWei
 * @version 1.0
 * @describe
 * @date 2018/1/11
 */
public interface ISpotRecordService {
    /**
     * 查询历史点检记录表
     *
     * @return
     */
    List<SpotRecordBO> findAll();

    /**
     * 添加一条历史点检记录
     *
     * @param spotRecordBO
     */
    void insertSpotRecord(SpotRecordBO spotRecordBO);

    /**
     *更新一条历史点检记录
     *
     * @param spotRecordBO
     */
    void updateSpotRecord(SpotRecordBO spotRecordBO);

    /**
     *删除一条历史点检记录
     *
     * @param id
     */
    void deleteSpotRecord(Integer id);

    /**
     * 查询点检列表
     *
     * @param spotRecordBO
     * @param pageable
     * @return
     */
    SimplePage<SpotRecordBO> getSpotRecordList(SpotRecordBO spotRecordBO, Pageable pageable);

    /**
     * 根据id查询SpotRecordBO
     *
     * @param id
     * @return
     */
    SpotRecordBO getBySpotRecordId(Integer id);
}
