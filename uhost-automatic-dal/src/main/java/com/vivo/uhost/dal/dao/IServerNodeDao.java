package com.vivo.uhost.dal.dao;

import com.vivo.uhost.dal.entity.ServerNode;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by gaojun on 2018/1/3.
 */
public interface IServerNodeDao {
    List<ServerNode> selectAll();

    void insert(ServerNode serverNode);

    void delete(Integer id);

    void update(ServerNode serverNode);

    //更新uhost设备连接数
    void updateConnectCount(Integer id, Integer count);

    List<ServerNode> queryList(ServerNode serverNode, Pageable pageable);

    Long count(ServerNode serverNode);

    ServerNode getNodeByAddress(ServerNode serverNode);
}
