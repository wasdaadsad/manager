/*
*
* Created by IntelliJ IDEA.
  User: dongjiajin
  Date: 2018/1/9
  Time: 16:15
  To change this template use File | Settings | File Templates.
  */

package com.vivo.uhost.service.impl;

import com.vivo.uhost.service.IUserListService;
import com.vivo.uhost.dal.dao.IUserListDao;
import com.vivo.uhost.dal.entity.UserList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service("userListService")
public class UserListServiceImpl implements IUserListService {
    @Autowired
    private IUserListDao userListDao;


    @Override
    public List<UserList> findAll() {
        return userListDao.selectAll();
    }

    @Override
    public void add(UserList userList) {
        userListDao.insert(userList);
    }

    @Override
    public void delete(Long id) {
        userListDao.delete(id);
    }

    @Override
    public Long count(UserList userList) {
        return userListDao.count(userList);
    }

    @Override
    public void edit(UserList userList) {
        userListDao.update(userList);
    }

    @Override
    public List<UserList> findList(UserList userList, Pageable pageable) {
        return userListDao.queryList(userList, pageable);
    }


}
