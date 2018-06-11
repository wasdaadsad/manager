/*
*
* Created by IntelliJ IDEA.
  User: dongjiajin
  Date: 2018/1/9
  Time: 16:15
  To change this template use File | Settings | File Templates.
  */

package com.vivo.uhost.web.controller;

import com.vivo.console.data.domain.SimplePage;
import com.vivo.uhost.comm.web.BaseJsonResponse;
import com.vivo.uhost.core.domain.bo.UserListBO;
import com.vivo.uhost.dal.entity.UserList;
import com.vivo.uhost.service.controller.BaseController;
import com.vivo.uhost.service.impl.UserListServiceImpl;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;


@Controller
public class UserListController extends BaseController {

    private static Log log = LogFactory.getLog(UserListController.class);

    @Autowired
    private UserListServiceImpl userListServiceImpl;


    /*home.jsp*/


    @RequestMapping("/uhostmanage/userManage/home")
    public String home() {
        return "usermanage/home";
    }



    /*用户列表*/

    @RequestMapping("/admin/usermanage/userlist")
    @ResponseBody
    public BaseJsonResponse list(UserList userList, Pageable pageable) {

        List<UserList> userLists = userListServiceImpl.findList(userList, pageable);
        long count = userListServiceImpl.count(userList);
        List<UserListBO> boList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(userLists)) {
            for (UserList userList1 : userLists) {
                UserListBO userBo = new UserListBO();
                userBo.setId(userList1.getId());
                userBo.setUserName(userList1.getUserName());
                userBo.setUserCode(userList1.getUserCode());
                userBo.setDepartment(userList1.getDepartment());
                userBo.setAuthority(userList1.getAuthority());
                boList.add(userBo);
            }
        }
        SimplePage<UserListBO> simplePage = new SimplePage<>(boList, pageable, count);
        return new BaseJsonResponse(BaseJsonResponse.OK, simplePage);
    }


    /*添加*/

    @RequestMapping("/uhostmanage/userManage/add")
    @ResponseBody
    public BaseJsonResponse addItem(UserListBO userListBO) {
        UserList userList = new UserList();
        try {
            userList.setUserName(userListBO.getUserName());
            userList.setUserCode(userListBO.getUserCode());
            userList.setDepartment(userListBO.getDepartment());
            userList.setAuthority(userListBO.getAuthority());
            userListServiceImpl.add(userList);
        } catch (Exception ex) {
            log.error("add user failed", ex);
            return new BaseJsonResponse(BaseJsonResponse.INVALID_PARAM, new ArrayList<>());
        }
        return new BaseJsonResponse(BaseJsonResponse.OK, new ArrayList<>());
    }


    /*编辑*/

    @RequestMapping("/uhostmanage/userManage/edit")
    @ResponseBody
    public BaseJsonResponse editUser(UserListBO userListBO) {
        UserList userList = new UserList();
        try {
            userList.setId(userListBO.getId());
            userList.setUserName(userListBO.getUserName());
            userList.setUserCode(userListBO.getUserCode());
            userList.setDepartment(userListBO.getDepartment());
            userList.setAuthority(userListBO.getAuthority());
            userListServiceImpl.edit(userList);
        } catch (Exception ex) {
            log.error("edit user failed", ex);
            return new BaseJsonResponse(BaseJsonResponse.INVALID_PARAM, new ArrayList<>());
        }
        return new BaseJsonResponse(BaseJsonResponse.OK, new ArrayList<>());
    }

    /*删除*/

    @RequestMapping("/uhostmanage/userManage/delete")
    @ResponseBody
    public BaseJsonResponse delete(@RequestParam("id") Long id) {
        try {
            userListServiceImpl.delete(id);
        } catch (Exception ex) {
            log.error("delete user failed", ex);
            return new BaseJsonResponse(BaseJsonResponse.INVALID_PARAM, new ArrayList<>());
        }
        return new BaseJsonResponse(BaseJsonResponse.OK, new ArrayList<>());
    }
}
