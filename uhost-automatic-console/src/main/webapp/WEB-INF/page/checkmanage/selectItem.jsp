<%--
  Created by IntelliJ IDEA.
  User: dongjiajin
  Date: 2018/1/10
  Time: 18:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
<!DOCTYPE html>
<html ng-app="myApp" ng-controller="MyCtrl" ng-cloak>
<head>
    <meta charset="utf-8">
    <title></title>
    <link rel="stylesheet" href="${contextPath}/static/assets/css/bootstrap.css">
    <script src="${contextPath}/static/assets/js/jquery.js"></script>
    <script src="${contextPath}/static/assets/js/bootstrap.js"></script>
    <script src="${contextPath}/static/assets/plugin/layer-v3.1.1/layer.js"></script>
    <style>
        body {position: relative;padding-top: 55px;}
        h3 {text-align: center;}
        h4 {text-align: center;}
        li {list-style-type: none;}
    </style>
</head>
<body>
<nav class="navbar navbar-default navbar-fixed-top navbar-inverse" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#"><span class="glyphicon glyphicon-glass"></span><font color="white" style="font-size:16px">配置点检项</font></a>
        </div>
        <ul class="nav nav-pills">
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown"><font color="white"><span class="glyphicon glyphicon-signal"></span>&nbsp;&nbsp;BSP点检</font> <b class="caret"></b></a>
                <ul class="dropdown-menu">
                    <li><a style="color: red" name="BSP" ng-click="appendAllChild($event)"><span class="glyphicon glyphicon-ok-sign"></span>&nbsp;选择全部</a></li>
                    <li ng-repeat="module in bspList">
                        <a style="color: #585cf6" id="{{module.name}}" ng-click="append($event)" name="BSP"><span class="glyphicon glyphicon-plus"></span>&nbsp;{{module.translate}}</a>
                    </li>
                </ul>
            </li>
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown"><font color="white"><span class="glyphicon glyphicon-signal"></span>&nbsp;&nbsp;ATS点检</font> <b class="caret"></b></a>
                <ul class="dropdown-menu">
                    <li><a style="color: red" name="ATS" ng-click="appendAllChild($event)"><span class="glyphicon glyphicon-ok-sign"></span>&nbsp;选择全部</a></li>
                    <li ng-repeat="module in atsList">
                        <a style="color: #585cf6" id="{{module.name}}" ng-click="append($event)"  name="ATS"><span class="glyphicon glyphicon-plus"></span>&nbsp;{{module.translate}}</a>
                    </li>
                    <%--<li><a href="#">返工前处理位</a></li>--%>
                </ul>
            </li>
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown"><font color="white"><span class="glyphicon glyphicon-signal"></span>&nbsp;&nbsp;驱动点检</font> <b class="caret"></b></a>
                <ul class="dropdown-menu">
                    <li><a style="color: red" name="" ><span class="glyphicon glyphicon-ok-sign"></span>&nbsp;选择全部</a></li>
                    <li><a href="#"><span class="glyphicon glyphicon-plus"></span>&nbsp;某子模块</a></li>
                </ul>
            </li>
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown"><font color="white"><span class="glyphicon glyphicon-signal"></span>&nbsp;&nbsp;其他点检</font> <b class="caret"></b></a>
                <ul class="dropdown-menu">
                    <li><a style="color: red" name="" ><span class="glyphicon glyphicon-ok-sign"></span>&nbsp;选择全部</a></li>
                    <li><a href="#"><span class="glyphicon glyphicon-plus"></span>&nbsp;某子模块</a></li>
                </ul>
            </li>
            <button type="button" class="btn btn-sm btn-success" style="margin-top: 0.6%;float: left" ng-click="appendAll()">选择全部</button>
            <button type="button" class="btn btn-sm btn-success" style="margin-top: 0.6%;float: right" onclick="submit()">提交</button>
        </ul>
        <div>
</nav>
<%--{{currentTime}}--%>
<div style="text-align: center" ng-if="Prompt.show">
    <marquee width=500 behavior=alternate direction=left align=middle><FONT face=楷体_GB2312 color=#ff0000 size=6>暂无点检项，请在导航栏的下拉框中根据模块添加点检项...</FONT></marquee>
</div>
<table class="table table-condensed" style="margin-top: 20px">
    <tr>
        <td><font style="font-weight: bolder">测试编号：</font>{{time}}</td>
        <td><font style="font-weight: bolder">ip地址：</font>${ip}</td>
        <td><font style="font-weight: bolder">token：</font>${token}</td>
        <td><font style="font-weight: bolder">机型：</font>${model}</td>
    </tr>
    <tr>
        <td><font style="font-weight: bolder">emmcId：</font>${emmcId}</td>
        <td><font style="font-weight: bolder">状态：</font>${state}</td>
        <td><font style="font-weight: bolder">系统版本：</font>${systemVersion}</td>
        <td><font style="font-weight: bolder">上次登录时间：</font>${lastLoginTime}</td>
    </tr>
</table>


<%--BSP点检--%>
<div ng-if="BSP.show" class="panel panel-danger" style="margin-top: 20px">
    <div class="panel-heading" data-toggle="collapse" href="#BSP" style="background: #2aa198">
        <h3 class="panel-title">
            <span class="glyphicon glyphicon-tower"></span>&nbsp;&nbsp;<font style="color: yellow">BSP点检</font>
            <button ng-click="BSP.show=false" type="button" class="btn btn-danger btn-xs" style="float: right"><span class="glyphicon glyphicon-trash"></span>&nbsp;删除
        </h3>
    </div>
    <div id="BSP" class="panel-collapse in">
        <div class="panel-body">
            <child-module tag="BSP" module="log" translate="log"></child-module>
            <child-module tag="BSP" module="emmc" translate="emmc"></child-module>
            <child-module tag="BSP" module="ufsSd" translate="ufsSd"></child-module>
            <child-module tag="BSP" module="ddrSubarea" translate="ddrSubarea"></child-module>
            <child-module tag="BSP" module="fastboot" translate="fastboot"></child-module>
            <child-module tag="BSP" module="recovery" translate="recovery"></child-module>
            <child-module tag="BSP" module="security" translate="security"></child-module>
            <child-module tag="BSP" module="usb" translate="usb"></child-module>
            <child-module tag="BSP" module="coreBsp" translate="coreBsp"></child-module>
            <child-module tag="BSP" module="linuxBsp" translate="linuxBsp"></child-module>
            <child-module tag="BSP" module="fsTf" translate="fsTf"></child-module>
            <child-module tag="BSP" module="ats" translate="ats"></child-module>
        </div>
    </div>
</div>
<%--ATS点检--%>
<div ng-if="ATS.show"  class="panel panel-danger">
    <div class="panel-heading" data-toggle="collapse" href="#ATS" style="background: #2aa198">
        <h3 class="panel-title">
            <span class="glyphicon glyphicon-tower"></span>&nbsp;&nbsp;<font style="color: yellow">ATS点检</font>
            <button ng-click="ATS.show=false"  type="button" class="btn btn-danger btn-xs" style="float: right"><span class="glyphicon glyphicon-trash"></span>&nbsp;删除
        </h3>
    </div>
    <div id="ATS" class="panel-collapse in">
        <div class="panel-body">
            <child-module tag="ATS" module="download" translate="下载开机位"></child-module>
            <child-module tag="ATS" module="globalFunction" translate="全功能测试位"></child-module>
            <child-module tag="ATS" module="calibration" translate="校准位"></child-module>
            <child-module tag="ATS" module="syntheticTest" translate="综测位"></child-module>
            <child-module tag="ATS" module="conduction" translate="射频传导位"></child-module>
            <child-module tag="ATS" module="electricity" translate="电流位"></child-module>
            <child-module tag="ATS" module="flashCalibration" translate="闪光灯校准位"></child-module>
            <child-module tag="ATS" module="audio" translate="音频位"></child-module>
            <child-module tag="ATS" module="mmi" translate="MMI位"></child-module>
            <child-module tag="ATS" module="photographTest" translate="拍照测试位"></child-module>
            <child-module tag="ATS" module="couplingAntenna" translate="耦合切换位"></child-module>
            <child-module tag="ATS" module="orderFulfillment" translate="订单执行位"></child-module>
            <child-module tag="ATS" module="gpsWifi" translate="GPS/WIFI位"></child-module>
            <child-module tag="ATS" module="captcha" translate="打码位"></child-module>
        </div>
    </div>
</div>
<%--drive点检--%>
<%--other点检--%>
</body>
<script src="${contextPath}/static/assets/js/angular.min.js"></script>
</html>
<script>
    var app = angular.module('myApp', []);

    app.controller('MyCtrl', function($rootScope,$scope,$filter, $interval,$timeout) {
        $interval(function () {$scope.currentTime = $filter("date")(new Date(), "yy-MM-dd HH-mm-ss");}, 1000);
        $scope.time = $filter("date")(new Date(), "yyMMddHHmmss");

        $scope.bspList =[{name:"log",translate:"log"},{name:"emmc",translate:"emmc"},{name:"ufsSd",translate:"ufsSd"},
            {name:"ddrSubarea",translate:"ddrSubarea"},{name:"fastboot",translate:"fastboot"},{name:"recovery",translate:"recovery"},
            {name:"security",translate:"security"},{name:"usb",translate:"usb"},{name:"coreBsp",translate:"coreBsp"},
            {name:"linuxBsp",translate:"linuxBsp"},{name:"fsTf",translate:"fsTf"},{name:"ats",translate:"ats"}
        ];

        $scope.atsList =[{name:"download",translate:"下载开机位"},{name:"globalFunction",translate:"全功能测试位"},{name:"calibration",translate:"校准位"},{name:"syntheticTest",translate:"综测位"},
            {name:"conduction",translate:"射频传导位"},{name:"electricity",translate:"电流位"},{name:"flashCalibration",translate:"闪光灯校准位"},{name:"audio",translate:"音频位"},
            {name:"mmi",translate:"MMI位"},{name:"photographTest",translate:"拍照测试位"},{name:"couplingAntenna",translate:"耦合切换位"},{name:"orderFulfillment",translate:"订单执行位"},
            {name:"gpsWifi",translate:"GPS/WIFI位"},{name:"captcha",translate:"打码位"}
        ];
        $rootScope.BSP = {show:false};
        $rootScope.ATS = {show:false};
        $rootScope.Prompt = {show:true};

        $scope.append = function ($event) {
            switch($event.target.name){
                case "BSP":$rootScope.BSP = {show:true};
                    break;
                case "ATS":$rootScope.ATS = {show:true};
                    break;
            }
            $timeout(function(){
                $scope.$broadcast ("export", $event.target.id);
            });
        };

        $scope.appendAllChild = function ($event) {
            switch($event.target.name){
                case "BSP":$rootScope.BSP = {show:true};
                    break;
                case "ATS":$rootScope.ATS = {show:true};
                    break;
            }
            $timeout(function(){
                $scope.$broadcast ("exportAllChild", $event.target.name);
            });
        };

        $scope.appendAll = function () {
            $rootScope.BSP = {show:true};
            $rootScope.ATS = {show:true};
            $timeout(function(){
                $scope.$broadcast ("exportAll");
            });
        };
    });

    app.directive('childModule', function($http){
        return{
            restrict:'E',
            scope :true ,
            link:function(scope, element, attrs) {
                scope.module ={
                    name:attrs.module,
                    translate:attrs.translate,
                    tag:attrs.tag,
                    show:false
                };
                scope.isAll = true;//全选框默认选中
                scope.All = true;//默认显示“取消全选”
                scope.selected = [];//用于保存选中的复选框的id
                $http.get('${contextPath}/uhostmanage/checkManage/testItem?testItem=' + attrs.module).success(function(resp){
                    scope.list = resp;
                    angular.forEach(scope.list, function(data){scope.selected.push(data.id);});
                    scope.isSelected = function(id){return scope.selected.indexOf(id)>=0;};
                });
            },
            controller: function ($rootScope,$scope) {
                $scope.updateSelection = function($event, id){
                    var action = ($event.target.checked?'checked':'unchecked');
                    if(action === 'checked' && $scope.selected.indexOf(id) === -1){
                        $scope.selected.push(id);
                    }
                    if(action === 'unchecked' && $scope.selected.indexOf(id)!==-1){
                        var idx = $scope.selected.indexOf(id);
                        $scope.selected.splice(idx,1);
                    }

                    if($scope.selected.length === $scope.list.length){
                        $scope.isAll = true;
                        $scope.All = true;
                    }else{
                        $scope.isAll = false;
                        $scope.All = false;
                    }
                };

                $scope.selectAll = function ($event) {
                    if($event.target.checked){
                        $scope.selected.length = 0;//记得要先清空数组
                        angular.forEach($scope.list, function(data){$scope.selected.push(data.id);});
                        $scope.All = true;
                        $scope.isAll = true;
                    }else {
                        $scope.selected.length = 0;
                        $scope.All = false;
                        $scope.isAll = false;
                    }
                };

                $scope.init = function () {
                    $scope.selected.length = 0;//记得要先清空数组
                    angular.forEach($scope.list, function(data){$scope.selected.push(data.id);});
                    $scope.isAll = true;
                    $scope.All = true;
                    $rootScope.Prompt = {show:false};
                    $scope.module.show = true;
                };

                $scope.$on ("export", function(e, Child) {
                    if($scope.module.name === Child){
                        $scope.init();
                    }
                });

                $scope.$on ("exportAll", function() {
                    $scope.init();
                });

                $scope.$on ("exportAllChild", function(e, Child) {
                    if($scope.module.tag === Child) {
                        $scope.init();
                    }
                });
            },
            templateUrl:'childTemplate',
            replace:true
        };
    });
    /*此处做了AJAX防重复提交处理，详情参见https://www.cnblogs.com/qinxingnet/p/5748171.html*/
    var post_flag = false; //设置一个对象来控制是否进入AJAX过程


    function submit() {
        layer.confirm("确认要提交选中的测试项吗？", {
            title: "确认提交"
            // area: ['500px', '250px'],
            // shadeClose: true //点击遮罩关闭
            // shade: false //不显示遮罩
        }, function (index) {
            layer.close(index);
            var data= {};/*所有的数据*/
            var devicesIDs = [];/*保存设备的id*/
            devicesIDs.push(${devicesID});
            data.devicesIDs = devicesIDs;
            data.testCode = $('html[ng-controller="MyCtrl"]').scope().time;
            /*注意数组里面的值要跟页面上<child-module>中的module的值对应相同*/
            var modelList = ["log","emmc","ufsSd","ddrSubarea","fastboot","recovery","security","usb","coreBsp","linuxBsp","fsTf","ats",//BSP模块的
                "download", "globalFunction","calibration","syntheticTest","conduction", "electricity", "flashCalibration",//ATS模块的
                "audio","mmi","photographTest","couplingAntenna","orderFulfillment","gpsWifi","captcha"
            ];
            for(var i = 0;i < modelList.length;i++){
                if($('input:checkbox[name=' + modelList[i] + ']:checked').length){
                    data[modelList[i]] = [];
                    $('input:checkbox[name=' + modelList[i] + ']:checked').each(function(){
                        data[modelList[i]].push(JSON.parse(this.id));
                    });
                }
            }
            var dataString = JSON.stringify(data);
            // alert(dataString);
            if(post_flag) return; //如果正在提交则直接返回，停止执行
            post_flag = true;//标记当前状态为正在提交状态
            $.ajax({
                url : '${contextPath}/uhostmanage/checkManage/dataString',
                type : 'post',
                async: true,
                contentType : 'application/json',
                data:dataString,
                dataType: "json",
                success : function(data){
                    post_flag =false; //在提交成功之后将标志标记为可提交状态
                    alert("数据已经传到后台啦，哈哈哈！" + JSON.stringify(data));
                },
                error: function(){
                    post_flag =false; //在提交成功之后将标志标记为可提交状态
                }
            });
            return false;
        });
    }
</script>
<script type="text/ng-template" id="childTemplate">
    <div ng-if="module.show">
        <div class="panel panel-info" style="width: 293px;float: left;margin-left: 5px">
            <div class="panel-heading" data-toggle="collapse" href="{{'#' + module.name + 's'}}">
                <h4 class="panel-title">
                    <span class="glyphicon glyphicon-signal"></span>&nbsp;&nbsp;{{module.translate}}&nbsp;(<font color="#ff1493">{{selected.length}}</font>/{{list.length}})
                    <button ng-click="module.show = false" type="button" class="btn btn-danger btn-xs" style="float: right"><span class="glyphicon glyphicon-trash"></span>&nbsp;删除</button>
                </h4>
            </div>
            <div id="{{module.name + 's'}}" class="panel-collapse collapsing">
                <div class="panel-body" style="max-height: 200px;overflow:auto">
                    <ul>
                        <li ng-repeat="testItem in list">
                            <a href="#"><span class="glyphicon glyphicon-heart"></span></a>
                            <input type="checkbox" id="{{testItem}}" name="{{module.name}}" class="test" ng-checked="isSelected(testItem.id)" ng-click="updateSelection($event,testItem.id)">{{testItem.testName}}
                        </li>
                    </ul>
                </div>
                <div class="panel-footer">
                    <input ng-checked="isAll" type="checkbox" ng-click="selectAll($event)">&nbsp;
                    <span ng-show="All">取消全选</span>
                    <span ng-hide="All">全选</span>
                </div>
            </div>
        </div>
    </div>
</script>