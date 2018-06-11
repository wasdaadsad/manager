/*
*
* Created by IntelliJ IDEA.
  User: dongjiajin
  Date: 2018/1/9
  Time: 16:15
  To change this template use File | Settings | File Templates.
  */


package com.vivo.uhost.service;

import com.vivo.uhost.dal.entity.UserList;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IUserListService {


    /**
     * 查询用户
     *
     * @return
     */
    List<UserList> findAll();



    /**
     * 添加用户
     *
     * @param userList
     */
    void add(UserList userList);


    /**
     * 删除用户
     *
     * @param id
     */
    void delete(Long id);
    Long count(UserList userList);


    /**
     * 编辑用户
     *
     * @param userList
     */
    void edit(UserList userList);



    /**
     * 查询用户列表
     *
     * @param userList
     * @param pageable
     *
     * @return
     */
    List<UserList> findList(UserList userList, Pageable pageable);
}
