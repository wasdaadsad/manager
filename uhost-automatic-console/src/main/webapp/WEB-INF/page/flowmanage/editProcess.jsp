<%--
  Created by IntelliJ IDEA.
  User: dongjiajin
  Date: 2018/2/7
  Time: 16:39
  To change this template use File | Settings | File Templates.
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html lang="en" ng-app="myApp" ng-controller="MyCtrl" ng-cloak>
<head>
    <title>vivo comm2后台</title>
    <!-- page specific plugin styles -->
    <link rel="stylesheet"
          href="${contextPath}/static/assets/css/jquery-ui.css"/>
    <link rel="stylesheet"
          href="${contextPath}/static/assets/css/ui.jqgrid.css"/>

    <link rel="stylesheet"
          href="${contextPath}/static/assets/plugin/select2/css/select2.css"/>
    <link rel="stylesheet" href="${contextPath}/static/assets/css/bootstrap.css">
    <script src="${contextPath}/static/assets/js/jquery.js"></script>
    <script src="${contextPath}/static/assets/js/bootstrap.js"></script>
    <script src="${contextPath}/static/assets/plugin/layer-v3.1.1/layer.js"></script>
    <%@include file="../common/common.jsp" %>
    <style>

    </style>
</head>

<body class="main-container">

<div class="main-content-inner">
    <form id="editForm">
        <div class="page-content">
            <div class="row">
                <div class="col-xs-12">
                    <div class="widget-box">
                        <div class="widget-header widget-header-flat widget-header-small">
                            <h5 class="widget-title" id="title">
                                <i class="ace-icon fa fa-signal"></i> 编辑流程
                            </h5>

                            <div class="toolbar" id="toolbar">
                                <div class="clearfix">
                                    <div class="row">

                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="widget-body">
                        <div class="widget-main">
                            <div id="tablecontent">
                                <table id="grid-table"></table>

                                <div ng-controller="formCtrl">
                                    <div class="form-group">
                                        <input name="id" type="hidden" value="${process.id}" id="processId" />
                                        <label class="control-label">流程名称：</label>
                                        <input ng-model="data.flowName" name="processName" type="text" maxlength="100"
                                               class="txt gonggao" id="processName" value="${process.processName}" placeholder="请输入流程名称" />


                                        <label class="control-label">测试机型：</label>
                                        <select class="form-control" name="model" ng-model="data.model" ng-options="x.selected for x in modelList">
                                            <option value="">这里的值来自于： 首页 > 系统管理 > 机型列表</option>
                                        </select>



                                        <div class="checkbox">
                                            <label>
                                                <input  type="checkbox" value="11"  name="taskTypes"  id="taskUpdate" onchange="updateChange()"
                                                        <c:choose>
                                                            <c:when test="${process.id == null}">checked="checked"
                                                            </c:when>
                                                            <c:when test="${fn:contains(process.taskTypes, '11')}">checked="checked"</c:when>
                                                        </c:choose>/>任务-系统升级：
                                            </label>
                                        </div>
                                        <select class="form-control" name="pushTasks[11].osVersionId" id="versionId" ng-model="data.upgrade" ng-show="data.isUpdate" ng-options="y.selected for y in versionList">
                                            <option value="">这里的值来自于： 首页 > 系统管理 > 版本列表</option>
                                        </select>



                                        <div class="checkbox">
                                            <label>
                                                <input type="checkbox" value="14"  name="taskTypes"  id="taskUpdateReset" onchange="updateResetChange()"
                                                       <c:choose>
                                                           <c:when test="${process.id == null}">checked="checked" </c:when>
                                                           <c:when test="${fn:contains(process.taskTypes, '14')}">checked="checked" </c:when>
                                                       </c:choose>/>任务-出厂升级(正式版本)：
                                            </label>
                                        </div>
                                        <select class="form-control" name="pushTasks[14].osVersionId" id="versionIdReset" ng-model="data.factoryUpgrade"  ng-show="data.isFactoryUpdate"  ng-options="z.selected  for z in versionList">
                                            <option value="">这里的值来自于： 首页 > 系统管理 > 版本列表</option>
                                        </select>



                                        <div class="checkbox">
                                            <label>
                                                <input type="checkbox" value="15"  name="taskTypes"  id="taskReset"
                                                       <c:choose>
                                                           <c:when test="${process.id == null}">checked="checked" </c:when>
                                                           <c:when test="${fn:contains(process.taskTypes, '15')}">checked="checked" </c:when>
                                                       </c:choose>/>任务-恢复出厂设置：
                                            </label>
                                        </div>


                                        <div class="checkbox">
                                            <label>
                                                <input type="checkbox"  ng-model="data.isTest">任务-测试：
                                            </label>
                                        </div>
                                        <span ng-show="data.isTest">测试 1:{{data.factoryUpgrade}}</span>
                                        <select data-width="50px"  ng-model="data.factoryUpgrade"  ng-show="data.isTest"  ng-options="a.selected  for a in toolList">
                                            <option value="">请选择</option>
                                        </select>
                                    </div>

                                    <button type="button" onclick="addProcess()" class="btn btn-default">提交</button>
                                </div>
                            </div>
                        </div>
                        <!-- /.widget-main -->
                    </div>
                    <!-- /.widget-body -->
                </div>
                <!-- /.widget-box -->
            </div>
        </div>
        <!-- /.col -->
        </form>
</div>
</div>

<!-- /.main-container -->

<!-- page specific plugin scripts -->
<script src="${contextPath}/static/assets/plugin/jquery-ui.js"></script>
<script src="${contextPath}/static/assets/plugin/jquery.ui.touch-punch.js"></script>
<script src="${contextPath}/static/assets/plugin/ace/ace.searchbox-autocomplete.js"></script>
<script src="${contextPath}/static/assets/plugin/select2/js/select2.min.js"></script>
<script src="${contextPath}/static/assets/plugin/My97DatePicker/WdatePicker.js"></script>
<script src="${contextPath}/static/assets/plugin/echarts3/echarts.min.js"></script>
<script src="${contextPath}/static/assets/plugin/jquery.form.js"></script>
<script src="${contextPath}/static/assets/plugin/My97DatePicker/WdatePicker.js"></script>
<script src="${contextPath}/static/assets/plugin/bootbox.js"></script>
<!-- inline scripts related to this page -->


<script src="${contextPath}/static/assets/js/angular.min.js"></script>

</body>
</html>
<script>
    var app = angular.module('myApp', []);
    app.controller('MyCtrl', function($scope){});

    app.controller('formCtrl', function($scope) {

        $scope.modelList = [
            {selected : "PD1709", url : ""},
            {selected : "PD1621", url : ""},
            {selected : "PD1731", url : ""},
            {selected : "PD1731F_EX", url : ""}
        ];

        $scope.versionList = [
            {selected : "PD1503_A_A09.30.05_eng", url : ""},
            {selected : "PD1419C_A_1.4.0", url : ""}
        ];

        $scope.toolList = [
            {selected : "工具1", url : ""},
            {selected : "工具2", url : ""}
        ];
    });

    $("button").click(function(){
        $.post("${contextPath}/uhostmanage/testmanage/add",
            {
                flowName:"董家进",
                model:"PD1709",
                upgrade:"√",
                test:"√",
                factoryUpgrade:"√",
                factoryReset:"√",
                startTime:"2018-02-10 17:22:50",
                finishTime:"2018-02-10 17:22:50",
                sums:"10",
                succeed:"10",
                status:"未开始"
            },
            function(data,status){
                alert("数据: \n" + JSON.stringify(data) + "\n状态: " + status);
                return "${contextPath}/uhostmanage/testManage/testflow";
            });
    });
    function addProcess() {
        if (validate()) {
            var url = "${contextPath}/uhostmanage/toolManage/toolProcess";
            $.ajax({
                url:url,
                type:"POST",
                data:$('#editForm').serialize(),
                async:false,
                dataType:'json',
                success:function(data){
                    if(data.stat == '200') {
                        bootbox.confirm('保存成功!', function(result) {
                            parent.document.getElementById("tooladd").style.display = "none"
                            parent.location.reload()
                        })
                    }else {
                        layer.msg("保存失败！");
                    }
                }
            });
        }
    }


</script>