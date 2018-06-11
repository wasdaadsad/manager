<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/4/24
  Time: 16:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>添加版本</title>
    <link rel="stylesheet" href="${contextPath}/static/assets/css/bootstrap.css">
    <script src="${contextPath}/static/assets/js/jquery.js"></script>
    <script src="${contextPath}/static/assets/js/bootstrap.js"></script>
    <script src="${contextPath}/static/assets/plugin/layer-v3.1.1/layer.js"></script>
</head>
<body>
<div class="container">
    <br>
    <form id="add" action="${contextPath}/uhostmanage/versionManage/add" method="post" accept-charset="UTF-8">
        <input type='hidden' name='id'>
        <div class="form-group">
            <label for="versionCode">版本编号</label>
            <input id="versionCode" name="versionCode" type="text" class="form-control" placeholder="用于给手机选择升级版本时，可以快速找到自己配置的版本">
        </div>
        <div class="form-group">
            <label for="modelId">机型</label>
            <select id="modelId" name="modelId"  class="form-control">
                <c:forEach var="listModel" items="${modelInfoList}">
                    <option value="${listModel.id}">${listModel.modelName}</option>
                </c:forEach>
            </select>
        </div>
        <div class="form-group">
            <label for="packageUrl">升级包地址</label>
            <input id="packageUrl" name="packageUrl" type="text" class="form-control">
        </div>
        <div class="form-group">
            <label for="osType">测试类型</label>
            <select id="osType" name="osType" class="form-control" select="" class="tooltip-test" data-toggle="tooltip" title="出厂升级只能刷正式版本">
                <option value="6">测试系统</option>
                <option value="7">正式系统</option>
            </select>
        </div>
        <div class="form-group">
            <label for="state">有效状态</label>
            <select id="state" name="state" class="form-control" select="">
                <option value="1">有效</option>
                <option value="0">无效</option>
            </select>
        </div>
        <br>
        <button type="button" class="btn btn-primary" onclick="add()">确认添加</button>
    </form>
</div>
</body>
</html>
<script>
    function add() {
        $("#add").submit();
        var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
        parent.layer.close(index);  // 关闭layer
        window.parent.location.reload(); //刷新父页面
    }
</script>
