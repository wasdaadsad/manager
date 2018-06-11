/*
*
* Created by IntelliJ IDEA.
  User: dongjiajin
  Date: 2018/1/9
  Time: 16:15
  To change this template use File | Settings | File Templates.
  */


package com.vivo.uhost.dal.mybatis;

import com.vivo.uhost.dal.dao.IUserListDao;
import com.vivo.uhost.dal.entity.UserList;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserListDao implements IUserListDao {

    @Autowired
    private SqlSession sqlSession;


    @Override
    public List<UserList> selectAll() {
        return sqlSession.selectList("UserListMapper.selectAll");
    }

    @Override
    public void insert(UserList userList) {
        sqlSession.insert("UserListMapper.insert", userList);
    }

    @Override
    public void delete(Long id) {
        sqlSession.delete("UserListMapper.delete", id);
    }

    @Override
    public void update(UserList userList) {
        sqlSession.update("UserListMapper.update", userList);
    }

    @Override
    public List<UserList> queryList(UserList userList, Pageable pageable) {
        if(pageable != null){
            userList.setOffset(pageable.getOffset());
            userList.setPageSize(pageable.getPageSize());
        }
        return sqlSession.selectList("UserListMapper.selectList",userList);
    }

    @Override
    public Long count(UserList userList) {
        Map<String, Object> map = new HashMap<>();
        return sqlSession.selectOne("UserListMapper.count", userList);
    }
}
