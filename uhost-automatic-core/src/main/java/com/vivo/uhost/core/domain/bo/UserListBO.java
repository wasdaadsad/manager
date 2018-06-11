/*
*
* Created by IntelliJ IDEA.
  User: dongjiajin
  Date: 2018/1/9
  Time: 16:15
  To change this template use File | Settings | File Templates.
  */

package com.vivo.uhost.core.domain.bo;

public class UserListBO {
    private Long id;
    private String userName;//用户名
    private String userCode;//工号
    private String department;//部门
    private String authority;//权限
    private int offset;
    private int pageSize;

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public Long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserCode() {
        return userCode;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
//    public UserListBO(){}
//
//    public UserListBO(UserList userList){
//
//        this.userName = userList.getUserName();
//        this.userCode = userList.getUserCode();
//        this.id = userList.getId();
//    }
//
//    public UserList toUserList(){
//        UserList userList = new UserList();
//        userList.setUserName(this.userName);
//        userList.setUserCode(this.userCode);
//        userList.setId(this.id);
//        return userList;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public void setUserName(String userName) {
//        this.userName = userName;
//    }
//
//    public void setUserCode(String userCode) {
//        this.userCode = userCode;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public String getUserName() {
//        return userName;
//    }
//
//    public String getUserCode() {
//        return userCode;
//    }
}
