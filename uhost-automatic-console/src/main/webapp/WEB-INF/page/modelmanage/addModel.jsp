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
    <title>添加机型</title>
    <link rel="stylesheet" href="${contextPath}/static/assets/css/bootstrap.css">
    <script src="${contextPath}/static/assets/js/jquery.js"></script>
    <script src="${contextPath}/static/assets/plugin/jquery.form.js"></script>
    <script src="${contextPath}/static/assets/js/bootstrap.js"></script>
    <script src="${contextPath}/static/assets/plugin/layer-v3.1.1/layer.js"></script>
</head>
<body>
<div class="container">
    <form action="${contextPath}/uhostmanage/modelManage/add" method="post" id="add" accept-charset="UTF-8">
        <div class="form-group">
            <label for="modelName">机型名称</label>
            <input id="modelName" name="modelName" type="text" class="form-control" placeholder="如X21,指对外的机型名称，配置时请使用规范名称">
        </div>
        <div class="form-group">
            <label for="modelVersion">机型版本</label>
            <input id="modelVersion" name="modelVersion" type="text" value="如PD1709,请确保填入值和'adb shell getprop ro.vivo.product.model'获取的值一致,否则升级测试会找不到设备" class="form-control"
                   placeholder="如PD1709,请确保填入值和'adb shell getprop ro.vivo.product.model'获取的值一致,否则升级测试会找不到设备">
        </div>
        <div class="form-group">
            <label for="state">状态</label>
            <select id="state" name="state" class="form-control" select="" class="tooltip-test" data-toggle="tooltip" title="设为无效可禁止使用本系统对该机型做相关测试">
                <option value="1" >有效</option>
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
