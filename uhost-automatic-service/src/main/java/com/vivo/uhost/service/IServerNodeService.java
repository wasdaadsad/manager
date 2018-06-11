package com.vivo.uhost.service;

import com.vivo.uhost.dal.entity.ServerNode;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by gaojun on 2018/1/3.
 */
public interface IServerNodeService {
    /**
     * 查询所有device
     *
     * @return
     */
    List<ServerNode> findAll();

    /**
     * 添加一条serverNode
     *
     * @param serverNode
     */
    void add(ServerNode serverNode);


    //更新uhost设备连接数
    void updateConnectCount(Integer id, Integer count);

    /**
     * 删除一条serverNode
     *
     * @param id
     */
    void delete(Integer id);

    Long count(ServerNode serverNode);

    /**
     * 编辑device
     *
     * @param serverNode
     */
    void edit(ServerNode serverNode);

    /**
     * 查询serverNode列表
     *
     * @param device
     * @param pageable
     * @return
     */
    List<ServerNode> findList(ServerNode device, Pageable pageable);

    ServerNode getNodeByAddress(ServerNode serverNode);
}
