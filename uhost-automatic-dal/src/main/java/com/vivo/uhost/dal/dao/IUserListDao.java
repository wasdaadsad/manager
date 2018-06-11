/*
*
* Created by IntelliJ IDEA.
  User: dongjiajin
  Date: 2018/1/9
  Time: 16:15
  To change this template use File | Settings | File Templates.
  */

package com.vivo.uhost.dal.dao;

import com.vivo.uhost.dal.entity.UserList;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IUserListDao {

    List<UserList> selectAll();

    void insert(UserList userList);

    void delete(Long id);

    void update(UserList userList);

    List<UserList> queryList(UserList userList, Pageable pageable);

    Long count(UserList userList);
}
