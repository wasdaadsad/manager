package com.vivo.uhost.dal.mybatis;

import com.vivo.uhost.dal.dao.IServerNodeDao;
import com.vivo.uhost.dal.entity.ServerNode;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gaojun on 2018/1/3.
 */
@Repository
public class ServerNodeDaoImpl implements IServerNodeDao {

    @Autowired
    private SqlSession sqlSession;

    @Override
    public List<ServerNode> selectAll() {
        return sqlSession.selectList("ServerNodeMapper.selectAll");
    }

    @Override
    public void insert(ServerNode serverNode) {
        sqlSession.insert("ServerNodeMapper.insert", serverNode);
    }

    @Override
    public void updateConnectCount(Integer id, Integer count) {
        ServerNode serverNode = new ServerNode();
        serverNode.setId(id);
        serverNode.setConnectCount(count);
        sqlSession.update("ServerNodeMapper.updateConnectCount", serverNode);
    }

    @Override
    public void delete(Integer id) {
        sqlSession.delete("ServerNodeMapper.delete", id);
    }

    @Override
    public void update(ServerNode device) {
        sqlSession.update("ServerNodeMapper.update", device);
    }

    @Override
    public List<ServerNode> queryList(ServerNode device, Pageable pageable) {
        Map<String, Object> map = new HashMap<>();
        map.put("offset", pageable.getOffset());
        map.put("pageSize", pageable.getPageSize());
        map.put("serverAdress", device.getServerAddress());
        map.put("tcpPort", device.getTcpPort());
        map.put("state", device.getState());
        return sqlSession.selectList("ServerNodeMapper.selectList", map);
    }

    @Override
    public Long count(ServerNode device) {
        return sqlSession.selectOne("ServerNodeMapper.count", device);
    }

    @Override
    public ServerNode getNodeByAddress(ServerNode serverNode) {
        return sqlSession.selectOne("ServerNodeMapper.getNodeByAddress", serverNode);
    }
}
