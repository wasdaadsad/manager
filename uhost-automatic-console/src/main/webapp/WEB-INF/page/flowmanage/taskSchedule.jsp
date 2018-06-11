<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>vivo comm2后台</title>
    <!-- page specific plugin styles -->
    <link rel="stylesheet"
          href="${contextPath}/static/assets/css/jquery-ui.css"/>
    <link rel="stylesheet"
          href="${contextPath}/static/assets/css/ui.jqgrid.css"/>

    <link rel="stylesheet"
          href="${contextPath}/static/assets/plugin/select2/css/select2.css"/>

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

        .select2-search:after {
            content: '';
        }

        /* .select2-container--default .select2-selection--multiple {
            border: 1px solid #428BCA;
        }
        .select2-container--default.select2-container--focus .select2-selection--multiple {
            border: solid 1px;
        } */
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
            word-break:break-all;
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
                            <i class="ace-icon fa fa-signal"></i> 推送任务列表
                        </h5>

                        <div class="toolbar" id="toolbar">
                            <div class="clearfix">
                                <div class="row">
                                    <div class="col-xs-1 nopadding-right">
                                        <input type="text" name="processId" id="processId" class="input-sm col-xs-12"  readonly="true"
                                               placeholder='${process.id}' value="${process.id}">
                                    </div>
                                    <div class="col-xs-2 nopadding-right">
                                        <input type="text" name="flowName" class="input-sm col-xs-12" readonly="true"
                                               placeholder='${process.flowName}'>
                                    </div>
                                    <div class="col-xs-2 nopadding-right">
                                        <input type="text" name="model" id="model" class="input-sm col-xs-12" readonly="true"
                                               placeholder='${process.model}' value="${process.model}">
                                    </div>
                                    <div class="col-xs-2  pull-left" >
                                        <button id="refresh" class="btn btn-info btn-xs">
                                            <span class="ace-icon glyphicon glyphicon-refresh"></span> 刷新
                                        </button>
                                    </div>
                                    <div class="col-xs-2 nopadding-right" disabled="true">
                                        <input type="text" name="sum" id="sum" class="input-sm col-xs-12" readonly="true"
                                               placeholder='${process.sums}' value="${process.sums}" hidden>
                                    </div>
                                </div>
                                <%--<div class="row" style="margin-top: 10px">
                                    <div class="col-xs-12 nopadding-left">
                                        <c:if test="${process.status == '-1'}" var="condition" scope="request">
                                            <button id="start" class="btn btn-success btn-xs"><span class="ace-icon glyphicon glyphicon-play"></span> 启动任务</button>
                                        </c:if>
                                        <c:if test="${process.status == '1'}" var="condition" scope="request">
                                            <button id="stop" class="btn btn-danger btn-xs"><span class="ace-icon glyphicon glyphicon-stop"></span> 停止任务</button>
                                        </c:if>
                                        <c:if test="${process.status == '1'}" var="condition" scope="request">
                                            <button id="taskAgain" class="btn btn-info btn-xs"><span class="ace-icon glyphicon glyphicon-refresh"></span> 重试</button>
                                        </c:if>
                                        <c:if test="${process.status == '1'}" var="condition" scope="request">
                                            <button id="schedule" class="btn btn-success btn-xs"><span class="ace-icon glyphicon glyphicon-search"></span> 设备进度</button>
                                        </c:if>
                                        <c:if test="${process.status == '0'}" var="condition" scope="request">
                                            <button id="deciceFail" class="btn btn-warning btn-xs"><span class="ace-icon glyphicon glyphicon-exclamation-sign"></span> 失败设备</button>
                                        </c:if>
                                        <c:if test="${process.status == '0' || process.status == '1' || process.status == '2'}" var="condition" scope="request">
                                            <button id="checkReport" class="btn btn-info btn-xs"><span class="ace-icon glyphicon glyphicon-info-sign"></span> 测试报告</button>
                                        </c:if>
                                    </div>
                                </div>--%>
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

<div class="modal fade" id="deviceSchedule" tabindex="-1" role="dialog" aria-labelledby="myModalLabe"
     aria-hidden="true">
    <div class="modal-dialog" style="width: 90%;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×
                </button>
                <h4 class="modal-title" id="myModalLabe">
                    设备进度
                </h4>
            </div>
            <div class="modal-body" id="iframeContainer">

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
<script src="${contextPath}/static/assets/js/reconnecting-websocket.min.js"></script>

<!-- inline scripts related to this page -->

<script type="text/javascript">
    var grid_selector = "#grid-table";
    var pager_selector = "#grid-pager";
    var sum = $("#sum").val();
    $("#deviceSchedule").modal("hide");

    /*设备进度*/
    function deviceSchedule(cellvalue,rowObject) {
        var param = "?id=" + rowObject.id + "&taskId=" + cellvalue;
        window.location.href = "${contextPath}/uhostmanage/testmanage/deviceSchedule" + param + "";
    }
    /*失败设备*
    与设备进度一直 条件为
    1、测试状态字段：全部为【已关闭】
    2、任务状态字段：全部为【处理失败】或【已启动】*/
    function deciceFail(cellvalue,rowObject) {
        var param = "?id=" + rowObject.id ;
        window.location.href = "${contextPath}/uhostmanage/testmanage/deviceFail" + param + "";
    }
    function startTask(cellvalue,data){
        pushTask(cellvalue);
    }
    function pushTask(cellvalue){
        var taskId = cellvalue;
        $.ajax({
            type: 'GET',
            url: "${contextPath}/uhostmanage/taskPush/start?taskId=" + taskId,
            complete: function (data) {
                setTimeout(function () {
                    gridReload();
                },500);
            }
        });
    }

    jQuery(function ($) {
        //resize to fit page size
        $(window).on('resize.jqGrid', function () {
            $(grid_selector).jqGrid('setGridWidth', $("#tablecontent").width());
        });

        $(window).triggerHandler('resize.jqGrid');//trigger window resize to make the grid get the correct size

        var formData = {};
        $.each($("#toolbar :input").serializeArray(), function (i, item) {
            formData[item.name] = item.value;
        });

        $(grid_selector).jqGrid({
            url: "${contextPath}/admin/device/scheduleList.json",
            datatype: "json",
            mtype: "post",
            postData: formData,
            colNames: ['任务类型', '版本', '子任务', '总数', '成功数', '开始时间','更新时间','状态','操作'],
            colModel: [
                {name: 'type', index: 'type', align: "center", sortable: false, width: 25,formatter: typeFormatter},
                {name: 'testVersion', index: 'testVersion', align: "center", sortable: false, width: 30},
                {name: 'testName', index: 'testName', align: "center", sortable: false, width: 40},
                {name: 'sums', index:'sums', align: "center", sortable: false, width: 25,formatter: sumFormatter},
                {name: 'successNum', index: 'successNum', align: "center", sortable: false, width: 25},
                {name: 'startTime', index: 'startTime', align: "center", sortable: false, width: 50},
                {name: 'updateTime', index: 'updateTime', align: "center", sortable: false, width: 50},
                {
                    name: 'status',
                    index: 'status',
                    align: "center",
                    sortable: false,
                    width: 25,
                    formatter: statusFormatter
                },
                {
                    name: 'id',
                    index: 'id',
                    align: "center",
                    sortable: false,
                    width: 100,
                    formatter: doFormatter
                },
            ],
            //rowNum: all,
            //rowList: [20,50, 100, 1000],//用于改变显示行数的下拉列表框的元素数组。
            sortorder: 'desc',
            sortname: 'uniqid',
            //multiselect:true,//增加全选按钮
            //rownumbers: true,//给表格增加序号
            viewrecords: true,
            //pager: pager_selector,
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
                root: "data",
                total: "data.totalPages",
                page: "data.number",
                records: "data.totalElements",
                repeatitems: false
            }
        });
        //$(grid_selector).jqGrid('filterToolbar');
        /*$(grid_selector).jqGrid('navGrid',"#jqGridPager", {
            search: false, // show search button on the toolbar
            add: false,
            edit: false,
            del: false,
            refresh: true
        });*/

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

        function enableTooltips(table) {
            $('.navtable .ui-pg-button').tooltip({container: 'body'});
            $(table).find('.ui-pg-div').tooltip({container: 'body'});
        }

        $("#refresh").on("click", function (e) {
            gridReload();
        });


        //设置确定按钮点击事件，重新加载表格
        $("#submit").on("click", function (e) {
            gridReload();
        });

        //设置清空按钮点击事件，重新加载表格
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
    });

    function doFormatter(cellvalue, options, rowObject) {
        var data = JSON.parse(JSON.stringify(rowObject));
        var state = data.status;
        var type = data.type;
        if(state == 10 && type == 12){
            return "<div class='hidden-sm hidden-xs btn-group'>"
                + "<button onclick='startTask(" + cellvalue + "," + JSON.stringify(rowObject) + ")' class='btn btn-success btn-xs'><span class='ace-icon glyphicon glyphicon-play'></span> 下载APK</button>"
                + "</div>";
        }
        switch(state)
        {
            case 10:
                return "<div class='hidden-sm hidden-xs btn-group'>"
                    + "<button onclick='startTask(" + cellvalue + "," + JSON.stringify(rowObject) + ")' class='btn btn-success btn-xs'><span class='ace-icon glyphicon glyphicon-play'></span> 启动任务</button>"
                    + "</div>";
            case 13:
                return  "<div class='hidden-sm hidden-xs btn-group'>"
                    + "<button onclick='deciceFail(" + cellvalue + "," + JSON.stringify(rowObject) + ")' class='btn btn-warning btn-xs'><span class='ace-icon glyphicon glyphicon-exclamation-sign'></span> 失败设备</button>"
                    + "</div>"
                    + "<div class='hidden-sm hidden-xs btn-group'>"
                    + "<button onclick='checkReport(" + cellvalue + "," + JSON.stringify(rowObject) + ")' class='btn btn-info btn-xs'><span class='ace-icon glyphicon glyphicon-info-sign'></span> 测试报告</button>"
                    + "</div>";
            case 11:
                return "<div class='hidden-sm hidden-xs btn-group'>"
                    + "<button  onclick='taskStop(" + cellvalue + "," + JSON.stringify(rowObject) + ")' class='btn btn-danger btn-xs'><span class='ace-icon glyphicon glyphicon-stop'></span> 停止任务</button>"
                    + "</div>"
                    + "<div class='hidden-sm hidden-xs btn-group'>"
                    + "<button onclick='taskAgain(" + cellvalue + "," + JSON.stringify(rowObject) + ")' class='btn btn-info btn-xs'><span class='ace-icon glyphicon glyphicon-refresh'></span> 重试</button>"
                    + "</div>"
                    + "<div class='hidden-sm hidden-xs btn-group'>"
                    + "<button onclick='deviceSchedule(" + cellvalue + "," + JSON.stringify(rowObject) + ")' class='btn btn-success btn-xs'><span class='ace-icon glyphicon glyphicon-search'></span> 设备进度</button>"
                    + "</div>"
                    + "<div class='hidden-sm hidden-xs btn-group'>"
                    + "<button onclick='checkReport(" + cellvalue + "," + JSON.stringify(rowObject) + ")' class='btn btn-info btn-xs'><span class='ace-icon glyphicon glyphicon-info-sign'></span> 测试报告</button>"
                    + "</div>";
            case 12:
                return + "<div class='hidden-sm hidden-xs btn-group'>"
                    + "<button onclick='checkReport(" + cellvalue + "," + JSON.stringify(rowObject) + ")' class='btn btn-info btn-xs'><span class='ace-icon glyphicon glyphicon-info-sign'></span> 测试报告</button>"
                    + "</div>";
            default:
                return ''
        }
    }

    function statusFormatter(cellvalue, options, rowObject) {
        var state = cellvalue;
        switch(state)
        {
            case 10:
                return '未启动';
            case 13:
                return '已关闭';
            case 11:
                return '运行中';
            case 12:
                return '已完成';
            default:
                return ''
        }
    }

    function sumFormatter(cellvalue, options, rowObject) {
        return sum;
    }

    function typeFormatter(cellvalue, options, rowObject) {
        var type = cellvalue;
        switch(type)
        {
            case 11:
                return '升级';
            case 12:
                return '测试';
            case 13:
                return '测试';
            case 14:
                return '出厂升级';
            case 15:
                return '恢复出厂';
            default:
                return ''
        }
    }

    function doDelete(id) {
        bootbox.confirm("您确认要删除该记录吗?", function (result) {
            if (result) {
                $.ajax({
                    type: 'GET',
                    url: "${contextPath}/uhostmanage/machineMange/delete?id=" + id,
                    complete: function (data) {
                        if((JSON.stringify(data.responseJSON.stat)==400)){
                            bootbox.confirm("权限不够!", function () {
                            });
                        }
                        gridReload();
                    }
                })
            }
        });
    }


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
