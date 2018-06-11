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
                            <i class="ace-icon fa fa-signal"></i> 设备列表
                        </h5>

                        <div class="toolbar" id="toolbar">
                            <div class="clearfix">
                                <div class="row">
                                    <div class="col-xs-1 nopadding-right">
                                        <input type="text" name="id" class="input-sm col-xs-12"
                                               placeholder="设备ID">
                                    </div>
                                    <div class="col-xs-1 nopadding-right">
                                        <input type="text" name="emmcId" class="input-sm col-xs-12"
                                               placeholder="EMMC_ID">
                                    </div>
                                    <div class="col-xs-1 nopadding-right">
                                        <input type="text" name="pcb" class="input-sm col-xs-12"
                                               placeholder="PCB号">
                                    </div>
                                    <div class="col-xs-1 nopadding-right">
                                        <input type="text" name="systemVersion" class="input-sm col-xs-12"
                                               placeholder="系统版本">
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
                                            <option value="all">全部</option>
                                            <c:forEach var="factory" items="${factorys}">
                                                <option value='${factory}'>${factory}</option>
                                            </c:forEach>
                                        </select>
                                    <%--</div>--%>
                                    <%--<div class="col-xs-2 nopadding-right">--%>
                                        <label for="workShopSelect">车间：</label>
                                        <select name="workShop" id="workShopSelect" class="selectpicker show-tick show-menu-arrow" data-live-search="true" data-width="fit" data-style="btn-success" data-live-search-placeholder="搜索..." >
                                            <option value="all">全部</option>
                                            <c:forEach var="workShop" items="${workShops}">
                                                <option value='${workShop}'>${workShop}</option>
                                            </c:forEach>
                                        </select>
                                    <%--</div>
                                    <div class="col-xs-2 nopadding-right">--%>
                                        <label for="agingRoom">老化房：</label>
                                        <select name="agingRoom" id="agingRoom" class="selectpicker show-tick show-menu-arrow" data-live-search="true" data-width="fit" data-style="btn-success" data-live-search-placeholder="搜索..." >
                                            <option value="all">全部</option>
                                            <c:forEach var="agingRoom" items="${agingRooms}">
                                                <option value='${agingRoom}'>${agingRoom}</option>
                                            </c:forEach>
                                        </select>
                                    <%--</div>
                                    <div class="col-xs-2 nopadding-right">--%>
                                        <label for="agingRack">老化架：</label>
                                        <select name="agingRack" id="agingRack" class="selectpicker show-tick show-menu-arrow" data-live-search="true" data-width="fit" data-style="btn-success" data-live-search-placeholder="搜索..." >
                                            <option value="all">全部</option>
                                            <c:forEach var="agingRack" items="${agingRacks}">
                                                <option value='${agingRack}'>${agingRack}</option>
                                            </c:forEach>
                                        </select>
                                    <%--</div>
                                    <div class="col-xs-2 nopadding-right">--%>
                                        <%--<label for="uhost">Uhost：</label>--%>
                                        <%--<select name="uhost" id="uhost">--%>
                                            <%--<option value="all">all</option>--%>
                                            <%--<c:forEach var="uhost" items="${uhosts}">--%>
                                                <%--<option value='${uhost}'>${uhost}</option>--%>
                                            <%--</c:forEach>--%>
                                        <%--</select>--%>
                                   <%-- </div>
                                    <div class="col-xs-2 nopadding-right">--%>
                                        <label for="model">机型：</label>
                                        <select name="model" id="model" class="selectpicker show-tick show-menu-arrow" data-live-search="true" data-width="fit" data-style="btn-success" data-live-search-placeholder="搜索..." >
                                            <option value="all">全部</option>
                                            <c:forEach var="modle" items="${modles}">
                                                <option value='${modle}'>${modle}</option>
                                            </c:forEach>
                                        </select>
                                    <%--</div>
                                    <div class="col-xs-1 nopadding-right">--%>
                                        <label for="state">状态：</label>
                                        <select name="state" id="state" class="selectpicker show-tick show-menu-arrow" data-live-search="true" data-width="fit" data-style="btn-success" data-live-search-placeholder="搜索..." >
                                            <option value="2">全部</option>
                                            <option value="1">在线</option>
                                            <option value="0">离线</option>

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
            url: "${contextPath}/admin/device/devicelist.json",
            datatype: "json",
            mtype: "post",
            postData: formData,
            colNames: ['id', '厂区', '车间', '老化房', '老化架', 'uhostToken','机型', 'pcb', 'emmcId', 'port','imei','serial','系统版本','状态','最后在线时间','查看','删除'],
            colModel: [
                {name: 'id', index: 'id', align: "center", sortable: false, width: 20},
                {name: 'factory', index: 'factory', align: "center", sortable: false, width: 45},
                {name: 'workShop', index: 'workShop', align: "center", sortable: false, width: 45},
                {name: 'agingRoom', index: 'agingRoom', align: "center", sortable: false, width: 45},
                {name: 'agingRack', index: 'agingRack', align: "center", sortable: false, width: 45},
                {name: 'uhost', index: 'uhost', align: "center", sortable: false, width: 80},
                {name: 'model', index: 'model', align: "center", sortable: false, width: 35},
                {name: 'pcb', index: 'pcb', align: "center", sortable: false, width: 30},
                {name: 'emmcId', index: 'emmcId', align: "center", sortable: false, width: 80},
                {name: 'portName', index: 'portName', align: "center", sortable: false, width: 25},
                {name: 'imei', index: 'imei', align: "center", sortable: false, width: 60},
                {name: 'serial', index: 'serial', align: "center", sortable: false, width: 30},
                {name: 'systemVersion', index: 'systemVersion', align: "center", sortable: false, width: 50},
                {name: 'stateVp', index: 'state1', align: "center", sortable: false, width: 15},
                {name: 'lastLoginTime', index: 'lastLoginTime', align: "center", sortable: false, width: 56},
                {
                    name: 'urlPath',
                    index: 'urlPath',
                    align: "center",
                    sortable: false,
                    width: 20,
                    formatter: toUpdateUser
                }, {
                    name: 'id',
                    index: 'id',
                    align: "center",
                    sortable: false,
                    width: 20,
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

    $("#addItem").on("click", function (e) {
        bootbox.confirm("<form id='addForm' ><table>" +
            "<tr>" +
            "<td>用户:</td><td>&nbsp;&nbsp;</td><td><input style='width: 220px' type='text' placeholder='请输入用户名' name='userName'/></td>" +
            "</tr>" +
            "<tr>" +
            "<td>区域ID:</td><td>&nbsp;&nbsp;</td><td><input style='width: 220px' type='text' placeholder='请输入区域ID' name='ccId'/></td>" +
            "</tr>" +
            "<tr>" +
            "<td>区域名称:</td><td>&nbsp;&nbsp;</td><td><input style='width: 220px' type='text' placeholder='请输入区域名称' name='ccMark'/></td>" +
            "</tr>" +
            "<tr>" +
            "<td>手机号:</td><td>&nbsp;&nbsp;</td><td><input style='width: 220px' type='text' placeholder='请输入手机号' name='phone'/></td>" +
            "</tr>" +
            "<tr>" +
            "<td>描述:</td><td>&nbsp;&nbsp;</td><td><input style='width: 220px' type='text' placeholder='请输入描述' name='remark'/></td>" +
            "</tr>" +
            "</table>" +
            "</form>", function (result) {
            var formData = {};
            $.each($("#addForm :input").serializeArray(), function (i, item) {
                formData[item.name] = item.value;
            });
            if (result) {
                $.ajax({
                    type: 'POST',
                    url: "${contextPath}/admin/loginAccount/addUser",
                    data: formData,
                    success: function () {
                        gridReload();
                    }
                })
            }
        });
    });

    function doUpdate(params) {
        bootbox.confirm("<form id='editForm' ><table>" +
            "<tr>" +
            "<td>ID:</td><td>&nbsp;&nbsp;</td><td><input style='width: 220px' type='text' readonly='readonly' value='" + params.id + "' name='id'/></td>" +
            "</tr>" +
            "<tr>" +
            "<td>厂区:</td><td>&nbsp;&nbsp;</td><td><input style='width: 220px' type='text' readonly='readonly' value='" + params.factory + "' name='factory'/></td>" +
            "</tr>" +
            "<tr>" +
            "<td>车间:</td><td>&nbsp;&nbsp;</td><td><input style='width: 220px' type='text' readonly='readonly' value='" + params.workShop + "' name='workShop'/></td>" +
            "</tr>" +
            "<tr>" +
            "<td>老化房:</td><td>&nbsp;&nbsp;</td><td><input style='width: 220px' type='text' readonly='readonly' value='" + params.agingRoom + "' name='agingRoom'/></td>" +
            "</tr>" +
            "<tr>" +
            "<td>老化架:</td><td>&nbsp;&nbsp;</td><td><input style='width: 220px' type='text' readonly='readonly' value='" + params.agingRack + "' name='agingRack'/></td>" +
            "</tr>" +
            "<tr>" +
            "<td>Uhost:</td><td>&nbsp;&nbsp;</td><td><input style='width: 220px' type='text' readonly='readonly' value='" + params.uhost + "' name='uhost'/></td>" +
            "</tr>" +
            "<tr>" +
            "<td>serial:</td><td>&nbsp;&nbsp;</td><td><input style='width: 220px' type='text' readonly='readonly' value='" + params.serial + "' name='serial'/></td>" +
            "</tr>" +
            "<tr>" +
            "<td>model:</td><td>&nbsp;&nbsp;</td><td><input style='width: 220px' type='text' readonly='readonly' value='" + params.model + "' name='model'/></td>" +
            "</tr>" +
            "<tr>" +
            "<td>pcb:</td><td>&nbsp;&nbsp;</td><td><input style='width: 220px' type='text' readonly='readonly' value='" + params.pcb + "' name='pcb'/></td>" +
            "</tr>" +
            "<tr>" +
            "<td>emmcid:</td><td>&nbsp;&nbsp;</td><td><input style='width: 220px' type='text' readonly='readonly' value='" + params.emmcId + "' name='emmcId'/></td>" +
            "</tr>" +
            "<tr>" +
            "<td>系统版本:</td><td>&nbsp;&nbsp;</td><td><input style='width: 220px' type='text' readonly='readonly' value='" + params.systemVersion + "' name='systemVersion'/></td>" +
            "</tr>" +
            "<tr>" +
            "<td>在线状态:</td><td>&nbsp;&nbsp;</td><td><input style='width: 220px' type='text' readonly='readonly' value='" + params.state + "' name='state'/></td>" +
            "</tr>" +
            "<tr>" +
            "<td>最后上线时间:</td><td>&nbsp;&nbsp;</td><td><input style='width: 220px' type='text' readonly='readonly' value='" + params.lastLoginTime + "' name='lastLoginTime'/></td>" +
            "</tr>" +
            "</table>" +
            "</form>", function (result) {
            var formData = {};
            $.each($("#editForm :input").serializeArray(), function (i, item) {
                formData[item.name] = item.value;
            });
            if (result) {
                $.ajax({
                    type: 'POST',
                    url: "${contextPath}/uhostmanage/machineMange/edit",
                    data: formData,
                    complete: function () {
                        gridReload();
                    }
                })
            }
        });
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
        gridReload();//这里主要保证断开重连之后可以自动刷新
        console.log("WebSocket连接成功server");
    };

    //接收到消息的回调方法
    websocket.onmessage = function (event) {
        console.log(event.data);
        if(event.data.indexOf("device") >= 0){//发过来的消息体中包含"device"才会刷新
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
