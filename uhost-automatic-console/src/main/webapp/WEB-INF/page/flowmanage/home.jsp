<%--
  Created by IntelliJ IDEA.
  User: dongjiajin
  Date: 2018/1/9
  Time: 16:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>测试流程列表</title>
    <!-- page specific plugin styles -->
    <link rel="stylesheet"
          href="${contextPath}/static/assets/css/jquery-ui.css"/>
    <link rel="stylesheet"
          href="${contextPath}/static/assets/css/ui.jqgrid.css"/>
    <link rel="stylesheet"
          href="${contextPath}/static/assets/plugin/select2/css/select2.css"/>
    <link rel="stylesheet"
          href="${contextPath}/static/assets/css/bootstrap-select.min.css"/>

    <link rel="stylesheet" href="${contextPath}/static/assets/css/bootstrap.css">
    <script src="${contextPath}/static/assets/js/jquery.js"></script>
    <script src="${contextPath}/static/assets/js/bootstrap.js"></script>
    <%@include file="../common/common.jsp" %>
    <style>
        .pd-2 {
            padding: 2px;
        }

        .toolbar {
            margin: 10px auto;
        }

        .nopadding-right {
            padding-right: 0px
        }

        .toolbar .pull-right {
            margin-right: 10px;
        }

        .select-header {
            width: 80px;
            text-align: center;
        }

        .tag-input-style + .chosen-container-multi .chosen-choices li.search-choice {
            background-color: #428bca;
            color: #FFFFFF;
            padding: 3px 20px 3px 9px;
        }

        .tag-input-style + .chosen-container-multi .chosen-choices li.search-choice .search-choice-close {
            line-height: 22px;
        }

        .toolbar-label {
            text-align: right;
            padding: 5px 4px 6px;
        }

        .item-list > li {
            padding: 0px;
        }

        #dateType li.active a {
            color: #478fca;
        }

        #dateType li.active i.invisible {
            visibility: visible;
        }

        .ui-jqgrid-sortable {
            text-align: center !important;
        }

        .ui-autocomplete {
            max-height: 200px;
            overflow-y: auto;
            /* 防止水平滚动条 */
            overflow-x: hidden;
        }

        /* readonly状态下input的鼠标显示 */
        .form-control[disabled], .form-control[readonly], fieldset[disabled] .form-control {
            cursor: pointer;
        }

        .ui-jqgrid tr.jqgrow td {
            white-space: normal !important;
            height: auto;
            vertical-align: text-top;
            padding-top: 2px;
        }


    </style>
</head>

<body class="main-container">

<div class="main-content-inner">
    <div class="page-content">
        <div class="row">
            <div class="col-xs-12">
                <div class="widget-box">
                    <div class="widget-header widget-header-flat widget-header-small">
                        <h5 class="widget-title" id="title">
                            <i class="ace-icon fa fa-signal"></i> 流程列表
                        </h5>

                        <div class="toolbar" id="toolbar">
                            <div class="clearfix">
                                <div class="row">

                                    <div class="col-xs-2 nopadding-right">
                                        <input type="text" name="flowName" class="input-sm col-xs-12"
                                               placeholder="输入流程名">
                                    </div>

                                    <div class="col-xs-3">


                                        <button id="submit" class="btn btn-success btn-xs">
                                            <span class="ace-icon glyphicon glyphicon-ok"></span> 确定
                                        </button>&nbsp;&nbsp;&nbsp;&nbsp;

                                        <button id="undo" class="btn btn-warning btn-xs">
                                            <span class="ace-icon fa fa-undo"></span> 清空
                                        </button>&nbsp;&nbsp;&nbsp;&nbsp;

                                        <button id="addItem" class="btn btn-success btn-xs">
                                            <span class="ace-icon glyphicon glyphicon-ok"></span> 新增
                                        </button>
                                    </div>
                                </div>
                                <div class="row" style="margin-top: 10px">
                                    <div class="col-xs-2 nopadding-right">
                                        <label for="model">机型：</label>
                                        <select name="model" id="model" class="selectpicker show-tick show-menu-arrow" data-live-search="true" data-width="fit" data-style="btn-success" data-live-search-placeholder="搜索...">
                                            <option value="">全部</option>
                                            <c:forEach var="modle" items="${modles}">
                                                <option value='${modle}'>${modle}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="widget-body">
                        <div class="widget-main">
                            <div id="tablecontent">
                                <table id="grid-table"></table>

                                <div id="grid-pager"></div>
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
    </div>
</div>
<div class="modal fade" id="malfunctionInfoModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" style="width: 90%;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    新建测试
                </h4>
            </div>
            <div class="modal-body" id="iframeContainer">

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">
                    关闭
                </button>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="choiceDevice" tabindex="-1" role="dialog" aria-labelledby="myModalLabe"
     aria-hidden="true">
    <div class="modal-dialog" style="width: 90%;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×
                </button>
                <h4 class="modal-title" id="myModalLabe">
                    选择设备
                </h4>
            </div>
            <div class="modal-body" id="iframeContainer1">

            </div>
            <div class="nopadding-right">
            </div><div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">
                    关闭
                </button>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="selectedDevice" tabindex="-1" role="dialog" aria-labelledby="myModalLabe2"
     aria-hidden="true">
    <div class="modal-dialog" style="width: 90%;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×
                </button>
                <h4 class="modal-title" id="myModalLabe2">
                    已选设备
                </h4>
            </div>
            <div class="modal-body" id="iframeContainer2">

            </div>
            <div class="nopadding-right">
            </div><div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">
                    关闭
                </button>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="taskSchedule" tabindex="-1" role="dialog" aria-labelledby="myModalLabe3"
     aria-hidden="true">
    <div class="modal-dialog" style="width: 90%;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×
                </button>
                <h4 class="modal-title" id="myModalLabe3">
                    任务进度
                </h4>
            </div>
            <div class="modal-body" id="iframeContainer3">

            </div>
            <div class="nopadding-right">
            </div><div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">
                    关闭
                </button>
            </div>
        </div>
    </div>
</div>

<!-- /.main-container -->

<!-- page specific plugin scripts -->
<script src="${contextPath}/static/assets/plugin/jquery-ui.js"></script>
<script src="${contextPath}/static/assets/plugin/jquery.ui.touch-punch.js"></script>
<script src="${contextPath}/static/assets/plugin/jqGrid/jquery.jqGrid.src.js"></script>
<script src="${contextPath}/static/assets/plugin/jqGrid/i18n/grid.locale-cn.js"></script>
<script src="${contextPath}/static/assets/plugin/date-time/bootstrap-datepicker.js"></script>
<script src="${contextPath}/static/assets/plugin/ace/ace.searchbox-autocomplete.js"></script>
<script src="${contextPath}/static/assets/plugin/select2/js/select2.min.js"></script>
<script src="${contextPath}/static/assets/plugin/My97DatePicker/WdatePicker.js"></script>
<script src="${contextPath}/static/assets/plugin/echarts3/echarts.min.js"></script>
<script src="${contextPath}/static/assets/plugin/jquery.form.js"></script>
<script src="${contextPath}/static/assets/plugin/jqGrid/jquery.jqGrid.src.js"></script>
<script src="${contextPath}/static/assets/plugin/jqGrid/i18n/grid.locale-cn.js"></script>
<script src="${contextPath}/static/assets/plugin/My97DatePicker/WdatePicker.js"></script>
<script src="${contextPath}/static/assets/plugin/bootbox.js"></script>
<script src="${contextPath}/static/assets/plugin/jquery.form.js"></script>
<script src="${contextPath}/static/assets/plugin/layer-v3.1.1/layer.js"></script>
<script src="${contextPath}/static/assets/js/bootstrap-select.min.js"></script>
<script src="${contextPath}/static/assets/js/bootstrap.js"></script>
<script src="${contextPath}/static/assets/js/reconnecting-websocket.min.js"></script>
<!-- inline scripts related to this page -->

<script type="text/javascript">
    var grid_selector = "#grid-table";
    var pager_selector = "#grid-pager";


    $("#malfunctionInfoModal").modal("hide");
    $("#choiceDevice").modal("hide");
    $("#selectedDevice").modal("hide");
    $("#taskSchedule").modal("hide");


    /*选择设备*/
    function choiceDevice (cellvalue,rowObject) {
        var param = "?processId=" + rowObject.id ;
        $("#iframeContainer1").html("<iframe src='${contextPath}/uhostmanage/testmanage/choiceDevice" + param + "'rowObject.id' width='100%'  frameborder='0'  height='600px'></iframe>");
        $("#choiceDevice").modal("show");
    }

    /*任务进度*/
    function taskSchedule (cellvalue,rowObject) {
        var param = "?processId=" + rowObject.id ;
        $("#iframeContainer3").html("<iframe src='${contextPath}/uhostmanage/testmanage/taskSchedule" + param + "' width='100%'  frameborder='0'  height='800px'></iframe>");
        $("#taskSchedule").modal("show");
    }

    /*选择设备*/
    function selectedDevice (cellvalue,rowObject) {
        var param = "?processId=" + rowObject.id;
        $("#iframeContainer2").html("<iframe src='${contextPath}/uhostmanage/testmanage/selectedDevice" + param + "' width='100%'  frameborder='0'  height='600px'></iframe>");
        $("#selectedDevice").modal("show");
    }


    /*取消*/
    function cancel(cellvalue,rowObject){
        bootbox.confirm("确定是否删除？", function (result) {
            if (result) {
                $.ajax({
                    type: 'GET',
                    url:"${contextPath}/uhostmanage/testmanage/delete?id=" + cellvalue,
                    complete:function(){
                        gridReload();
                    }
                })
            }
        });
    }

    /*操作*/
    function handleFormatter(cellvalue,option,rowObject) {
        var res = '';
        var data = JSON.stringify(rowObject);
        var status = JSON.parse(data).status;
        var no1 = "<a href='javascript:void(0)' onclick='choiceDevice(" + cellvalue + "," + JSON.stringify(rowObject) + ")'>选择设备&nbsp;&nbsp;</a>";
        var no2 = "<a href='javascript:void(0)' onclick='taskSchedule(" + cellvalue + "," + JSON.stringify(rowObject) + ")'>任务进度&nbsp;&nbsp;</a>";
        var no3 = "<a href='javascript:void(0)' onclick='selectedDevice(" + cellvalue + "," + JSON.stringify(rowObject) + ")'>已选设备&nbsp;&nbsp;</a> ";
        var no4 = "<a href='javascript:void(0)' onclick='cancel(" + cellvalue + "," + JSON.stringify(rowObject) + ")'>取消&nbsp;&nbsp;</a>";
        if(status == '10'){
            res = no1 + no2 + no3 + no4;
        }if(status == '13'){
            res = no2 + no3;
        }if(status == '11'){
            res = no2 + no3 +no4;
        }if(status =='12'){
            res = no2 + no3;
        }
        return res;
    }


    $("#addItem").on("click", function (e) {
        $("#iframeContainer").html("<iframe src='${contextPath}/uhostmanage/testmanage/editProcess' width='100%'  frameborder='0'  height='600px'></iframe>");
        $("#malfunctionInfoModal").modal("show");
    });


    jQuery(function ($) {

        //resize to fit page size
        $(window).on('resize.jqGrid', function () {
            $(grid_selector).jqGrid('setGridWidth', $("#tablecontent").width());
        });

        $(window).triggerHandler('resize.jqGrid');//trigger window resize to make the grid get the correct size

        //初始化其从他页面带查询参数跳转过来时异常类型的选中
        $("select[name='crashTypeCode'] option").each(function () {
            if ($(this).val() == "${crashTypeCode}") {
                $(this).attr("selected", "selected");
            }
        });

        var formData = {};
        $.each($("#toolbar :input").serializeArray(), function (i, item) {
            formData[item.name] = item.value;
        });

        $(grid_selector).jqGrid({
            url: "${contextPath}/uhostmanage/testmanage/flowlist.json",
            datatype: "json",
            mtype: "post",
            postData: formData,
            colNames: ['流程名', '机型', '升级','测试','出厂升级','恢复出厂设置', '开始时间', '完成时间','总数','成功','状态','操作'],
            colModel: [
                {name: 'flowName', index: 'flowName', align: "center", sortable: false, width: 100},
                {name: 'model', index: 'model', align: "center", sortable: false, width: 60},
                {name: 'upgrade', index: 'upgrade', align: "center", sortable: false, width: 40},
                {name: 'test', index: 'test', align: "center", sortable: false, width: 40},
                {name: 'factoryUpgrade', index: 'factoryUpgrade', align: "center", sortable: false, width: 40},
                {name: 'factoryReset', index: 'factoryReset', align: "center", sortable: false, width: 40},
                {name: 'startTime', index: 'startTime', align: "center", sortable: false, width: 60},
                {name: 'updateTime', index: 'updateTime', align: "center", sortable: false, width: 60},
                {name: 'sums', index: 'sums', align: "center", sortable: false, width: 40},
                {name: 'succeed', index: 'succeed', align: "center", sortable: false, width: 40},
                {name: 'statusVP', index: 'statusVP', align: "center", sortable: false, width: 40},
                {name: 'id', index: 'id', align: "center", sortable: false, width: 100,formatter: handleFormatter}
            ],
            rowNum: 20,
            rowList:[10,20,50,100],//可以自己选择展示多少行
            multiselect:true,//增加全选按钮
            rownumbers: true,//给表格增加序号
            sortorder: 'desc',
            sortname: 'uniqid',
            viewrecords: true,
            pager: pager_selector,
            altRows: true,
            emptyrecords: "没有找到数据",
            loadComplete: function (response) {
                var table = this;
                setTimeout(function () {
                    updatePagerIcons(table);
                }, 0);

                $(this).jqGrid('setGridWidth', $("#tablecontent").width());    //加载完数据后重新设置下宽度

                if (!response.stat || response.stat != '200') {
                    var msg = response.msg || "";
                    bootbox.alert("加载失败!" + msg);
                    return false;
                }
            }, serializeGridData: function (postData) {  //请求前触发
                return postData;
            },
            autowidth: true,
            height: 'auto',
            prmNames: {
                page: "page",
                rows: "size",
                sort: "sinx",
                order: "order",
                search: "search",
                nd: "nd",
                npage: null
            },
            jsonReader: {
                root: "data.content",
                total: "data.totalPages",
                page: "data.number",
                records: "data.totalElements",
                repeatitems: false
            }
        });



        //replace icons with FontAwesome icons like above
        function updatePagerIcons(table) {
            var replacement =
                {
                    'ui-icon-seek-first': 'ace-icon fa fa-angle-double-left bigger-140',
                    'ui-icon-seek-prev': 'ace-icon fa fa-angle-left bigger-140',
                    'ui-icon-seek-next': 'ace-icon fa fa-angle-right bigger-140',
                    'ui-icon-seek-end': 'ace-icon fa fa-angle-double-right bigger-140'
                };
            $('.ui-pg-table:not(.navtable) > tbody > tr > .ui-pg-button > .ui-icon').each(function () {
                var icon = $(this);
                var $class = $.trim(icon.attr('class').replace('ui-icon', ''));

                if ($class in replacement) icon.attr('class', 'ui-icon ' + replacement[$class]);
            })
        }

        //设置确定按钮点击事件，重新加载表格
        $("#submit").on("click", function (e) {
            gridReload();
        });

        // //设置清空按钮点击事件，重新加载表格
        $("#undo").on("click", function (e) {
            $('.toolbar input').val("");
            $('.toolbar select').get(0).selectedIndex = 0;
        });

        //给toolbar下面的输入框绑定回车键事件
        $('.toolbar input').keypress(function (event) {
            var keycode = (event.keyCode ? event.keyCode : event.which);
            if (keycode == '13') {
                gridReload();
            }
        });

        $('select').change(function () {//下拉框的值发生变化时刷新表格
            gridReload();
        });

    });
    //jquery

    function gridReload() {
        var formData = {};
        $.each($("#toolbar :input").serializeArray(), function (i, item) {
            formData[item.name] = item.value;
        });
        $(grid_selector).jqGrid('setGridParam', {
            postData: formData
        }).trigger("reloadGrid");
    }

    /*
   * 使用webSocket使浏览器与远程server保持长连接。（这里统一与web server保持连接，方便ip和端口的自动识别）
   * */
    var websocket = null;
    //判断当前浏览器是否支持WebSocket
    if ('WebSocket' in window) {
        websocket = new ReconnectingWebSocket("ws://" + document.location.host +"/refresh");
    }else {
        alert('当前浏览器 Not support websocket')
    }

    //连接发生错误的回调方法
    websocket.onerror = function () {
        console.log("WebSocket连接发生错误");
    };

    //连接成功建立的回调方法
    websocket.onopen = function () {
        gridReload();
        console.log("WebSocket连接成功server");
    };


    //接收到消息的回调方法
    websocket.onmessage = function (event) {
        console.log(event.data);
        if(event.data.indexOf("taskState") >= 0){//发过来的消息体中包含"taskState"才会刷新
            gridReload();
        }
    };

    //连接关闭的回调方法
    websocket.onclose = function () {
        console.log("WebSocket与push server连接关闭");
    };

    //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
    window.onbeforeunload = function () {
        websocket.close();
    };

    //发送消息给server
    // websocket.send(message);
</script>
</body>
</html>
