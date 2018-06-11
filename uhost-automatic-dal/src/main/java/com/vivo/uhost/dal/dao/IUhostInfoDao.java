package com.vivo.uhost.dal.dao;

import com.vivo.uhost.dal.entity.DeviceInfo;
import com.vivo.uhost.dal.entity.UhostInfo;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author litingfa  2018/3/19 17:38
 * @version 1.0
 * @description
 */
public interface IUhostInfoDao {

    UhostInfo findByUhost(UhostInfo uhost);

    /**
     * 根据身份ID来查询Uhost
     *
     * @param identify 身份标识字符串
     * @return Uhost
     */
    UhostInfo findByIdentify(String identify);

    UhostInfo selectByToken(UhostInfo uhost);


    /**
     * 添加Uhost
     *
     * @param uhost
     */
    void addUhost(UhostInfo uhost);


    void editUhost(UhostInfo uhostInfo);

    UhostInfo findByToken(String token);

    UhostInfo findByDevice(Long deviceId);

    List<UhostInfo> findList(UhostInfo uhostInfo, Pageable pageable);

    long count(UhostInfo uhostInfo);

    List<UhostInfo> selectAll();
}
