package com.vivo.uhost.service.impl;

import com.vivo.uhost.dal.dao.IServerNodeDao;
import com.vivo.uhost.dal.entity.ServerNode;
import com.vivo.uhost.service.IServerNodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by gaojun on 2018/1/3.
 */
@Service("serverNodeService")
public class ServerNodeServiceImpl implements IServerNodeService {
    @Autowired
    private IServerNodeDao serverNodeDao;

    @Override
    public List<ServerNode> findAll() {
        return serverNodeDao.selectAll();
    }

    @Override
    public void add(ServerNode serverNode) {
        serverNodeDao.insert(serverNode);
    }

    @Override
    public void delete(Integer id) {
        serverNodeDao.delete(id);
    }

    @Override
    public Long count(ServerNode serverNode) {
        return serverNodeDao.count(serverNode);
    }

    @Override
    public void edit(ServerNode serverNode) {
        serverNodeDao.update(serverNode);
    }

    @Override
    public List<ServerNode> findList(ServerNode serverNode, Pageable pageable) {
        return serverNodeDao.queryList(serverNode, pageable);
    }

    @Override
    public void updateConnectCount(Integer id, Integer count) {
        serverNodeDao.updateConnectCount(id, count);
    }

    @Override
    public ServerNode getNodeByAddress(ServerNode serverNode) {
        return serverNodeDao.getNodeByAddress(serverNode);
    }

}
