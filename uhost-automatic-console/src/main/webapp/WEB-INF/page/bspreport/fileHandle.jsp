<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>文件上传</title>
</head>
<body>
<form id="subdata" action="${contextPath}/uhostmanage/recordManage/autoLoad" enctype="multipart/form-data" method="post">
    报告文件1：<input type="file" name="files"><br/>
    报告文件2：<input type="file" name="files"><br/>
    报告文件3：<input type="file" name="files"><br/>
    机器信息：<input type="text" name="string"><br/>
    <input type="submit" value="提交">
</form>
<%--<iframe name="iUpload"  style="display:none"></iframe>--%>
</body>
</html>
