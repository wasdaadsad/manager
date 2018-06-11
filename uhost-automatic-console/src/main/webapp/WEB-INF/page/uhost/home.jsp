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
    <link rel="stylesheet"
          href="${contextPath}/static/assets/css/bootstrap-select.min.css"/>
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
                            <i class="ace-icon fa fa-signal"></i> Uhost列表
                        </h5>

                        <div class="toolbar" id="toolbar">
                            <div class="clearfix">
                                <div class="row">
                                    <div class="col-xs-1 nopadding-right">
                                        <input type="text" name="server" class="input-sm col-xs-12"
                                               placeholder="服务器id">
                                    </div>
                                    <div class="col-xs-2">
                                        <div class="col-xs-4">
                                            <button id="submit" class="btn btn-success btn-xs">
                                                <span class="ace-icon glyphicon glyphicon-ok"></span> 确定
                                            </button>
                                        </div>
                                        <div class="col-xs-4">
                                            <button id="undo" class="btn btn-warning btn-xs">
                                                <span class="ace-icon fa fa-undo"></span> 清空
                                            </button>
                                        </div>
                                    </div>
                                </div>
                                <div class="clearfix">
                                <div class="row" style="margin-top: 10px">
                                    <div class="col-xs-10  nopadding-right">
                                        <label for="factorySelect">厂区：</label>
                                        <select name="factory" id="factorySelect" class="selectpicker show-tick show-menu-arrow" data-live-search="true" data-width="fit" data-style="btn-success" data-live-search-placeholder="搜索..." >
                                            <option value="">全部</option>
                                            <c:forEach var="factory" items="${factorys}">
                                                <option value='${factory}'>${factory}</option>
                                            </c:forEach>
                                        </select>
                                    <%--</div>--%>
                                    <%--<div class="col-xs-2 nopadding-right">--%>
                                        <label for="workShopSelect">车间：</label>
                                        <select name="workShop" id="workShopSelect" class="selectpicker show-tick show-menu-arrow" data-live-search="true" data-width="fit" data-style="btn-success" data-live-search-placeholder="搜索..." >
                                            <option value="">全部</option>
                                            <c:forEach var="workShop" items="${workShops}">
                                                <option value='${workShop}'>${workShop}</option>
                                            </c:forEach>
                                        </select>
                                    <%--</div>
                                    <div class="col-xs-2 nopadding-right">--%>
                                        <label for="agingRoom">老化房：</label>
                                        <select name="agingRoom" id="agingRoom" class="selectpicker show-tick show-menu-arrow" data-live-search="true" data-width="fit" data-style="btn-success" data-live-search-placeholder="搜索..." >
                                            <option value="">全部</option>
                                            <c:forEach var="agingRoom" items="${agingRooms}">
                                                <option value='${agingRoom}'>${agingRoom}</option>
                                            </c:forEach>
                                        </select>
                                    <%--</div>
                                    <div class="col-xs-2 nopadding-right">--%>
                                        <label for="agingRack">老化架：</label>
                                        <select name="agingRack" id="agingRack" class="selectpicker show-tick show-menu-arrow" data-live-search="true" data-width="fit" data-style="btn-success" data-live-search-placeholder="搜索..." >
                                            <option value="">全部</option>
                                            <c:forEach var="agingRack" items="${agingRacks}">
                                                <option value='${agingRack}'>${agingRack}</option>
                                            </c:forEach>
                                        </select>
                                    <%--</div>
                                    <div class="col-xs-2 nopadding-right">--%>
                                   <%-- </div>
                                    <div class="col-xs-2 nopadding-right">--%>
                                    <%--</div>
                                    <div class="col-xs-1 nopadding-right">--%>
                                        <label for="status">状态：</label>
                                        <select name="status" id="status" class="selectpicker show-tick show-menu-arrow" data-live-search="true" data-width="fit" data-style="btn-success" data-live-search-placeholder="搜索..." >
                                            <option value="">全部</option>
                                            <option value="在线">在线</option>
                                            <option value="离线">离线</option>
                                        </select>
                                    </div>
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
<script src="${contextPath}/static/assets/js/bootstrap-select.min.js"></script>
<script src="${contextPath}/static/assets/js/reconnecting-websocket.min.js"></script>
<!-- inline scripts related to this page -->

<script type="text/javascript">
    var grid_selector = "#grid-table";
    var pager_selector = "#grid-pager";

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
            url: "${contextPath}/uhostmanage/uhostMange/list.json",
            datatype: "json",
            mtype: "post",
            postData: formData,
            colNames: ['服务器id', '厂区', '车间', '老化房', '老化架', 'uidentify','ip地址', 'token', '描述','状态','创建时间','跟新时间','操作'],
            colModel: [
                {name: 'serverId', index: 'serverId', align: "center", sortable: false, width: 20},
                {name: 'factory', index: 'factory', align: "center", sortable: false, width: 45},
                {name: 'workShop', index: 'workShop', align: "center", sortable: false, width: 45},
                {name: 'agingRoom', index: 'agingRoom', align: "center", sortable: false, width: 45},
                {name: 'agingRack', index: 'agingRack', align: "center", sortable: false, width: 45},
                {name: 'uidentify', index: 'uidentify', align: "center", sortable: false, width: 60},
                {name: 'ipAdress', index: 'ipAdress', align: "center", sortable: false, width: 40},
                {name: 'token', index: 'token', align: "center", sortable: false, width: 50},
                {name: 'remark', index: 'remark', align: "center", sortable: false, width: 70},
                {name: 'status', index: 'status', align: "center", sortable: false, width: 20},
                {name: 'createTimeVP', index: 'createTimeVP', align: "center", sortable: false, width: 50},
                {name: 'updateTimeVP', index: 'updateTimeVP', align: "center", sortable: false, width: 50},
                {
                    name: 'id',
                    index: 'id',
                    align: "center",
                    sortable: false,
                    width: 30,
                    formatter: toDeleteUser
                },
            ],
            rowNum: 20,
            rowList: [20,50, 100, 1000],//用于改变显示行数的下拉列表框的元素数组。
            sortorder: 'desc',
            sortname: 'uniqid',
            /*multiselect: true,//是否多选*/
            /*caption: "设备在线管理列表",//表名*/
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

        $('select').change(function () {//下拉框的值发生变化时刷新表格
            gridReload();
        });
    });


    function toDeleteUser(cellvalue, options, rowObject) {
        return "<a href='#' onclick='doDelete(" + cellvalue + ")'>删除</a>";
    }

    function toUpdateUser(cellvalue, options, rowObject) {
        return "<a href='javascript:void(0)' onclick='doUpdate(" + JSON.stringify(rowObject) + ")'>详细</a> ";
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
        console.log("WebSocket连接成功");
    };

    //接收到消息的回调方法
    websocket.onmessage = function (event) {
        console.log(event.data);
        if(event.data.indexOf("uhost") >= 0){//发过来的消息体中包含"uhost"才会刷新
            gridReload();
        }
    };

    //连接关闭的回调方法
    websocket.onclose = function () {
        console.log("WebSocket连接关闭");
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
