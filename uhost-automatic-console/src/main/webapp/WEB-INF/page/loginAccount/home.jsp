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
                            <i class="ace-icon fa fa-signal"></i> 检测记录列表
                        </h5>

                        <div class="toolbar" id="toolbar">
                            <div class="clearfix">
                                <div class="row">
                                    <div class="col-xs-1 nopadding-right">
                                        <input type="text" name="userName" class="input-sm col-xs-12"
                                               placeholder="输入用户名">
                                    </div>
                                    <div class="col-xs-1">
                                        <div class="col-xs-4">
                                            <button id="submit" class="btn btn-success btn-xs">
                                                <span class="ace-icon glyphicon glyphicon-ok"></span> 确定
                                            </button>
                                        </div>
                                    </div>
                                    <div class="col-xs-1">
                                        <div class="col-xs-4">
                                            <button id="addItem" class="btn btn-success btn-xs">
                                                <span class="ace-icon glyphicon glyphicon-ok"></span> 新增
                                            </button>
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
            url: "${contextPath}/admin/loginAccount/list.json",
            datatype: "json",
            mtype: "post",
            postData: formData,
            colNames: ['id', '账号', '区域ID', '区域名称', '联系方式', '创建时间', '更新时间', '描述', '更新', '删除'],
            colModel: [
                {name: 'id', index: 'id', align: "center", sortable: false, width: 60},
                {name: 'userName', index: 'userName', align: "center", sortable: false, width: 55},
                {name: 'ccId', index: 'ccId', align: "center", sortable: false, width: 80},
                {name: 'ccMark', index: 'ccMark', align: "center", sortable: false, width: 45},
                {name: 'phone', index: 'phone', align: "center", sortable: false, width: 45},
                {name: 'createTime', index: 'validVP', align: "center", sortable: false, width: 45},
                {name: 'updateTime', index: 'description', align: "center", sortable: false, width: 80},
                {name: 'remark', index: 'openId', align: "center", sortable: false, width: 80},
                {
                    name: 'urlPath',
                    index: 'urlPath',
                    align: "center",
                    sortable: false,
                    width: 30,
                    formatter: toUpdateUser
                }, {
                    name: 'id',
                    index: 'id',
                    align: "center",
                    sortable: false,
                    width: 30,
                    formatter: toDeleteUser
                },
            ],
            rowNum: 20,
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
    });

    function toDeleteUser(cellvalue, options, rowObject) {
        return "<a href='#' onclick='doDelete(" + cellvalue + ")'>删除</a>";
    }

    function toUpdateUser(cellvalue, options, rowObject) {
        return "<a href='javascript:void(0)' onclick='doUpdate(" + JSON.stringify(rowObject) + ")'>修改</a> ";
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
            "<td>用户:</td><td>&nbsp;&nbsp;</td><td><input style='width: 220px' type='text' value='" + params.userName + "' name='userName'/></td>" +
            "</tr>" +
            "<tr>" +
            "<td>区域ID:</td><td>&nbsp;&nbsp;</td><td><input style='width: 220px' type='text' value='" + params.ccId + "' name='ccId'/></td>" +
            "</tr>" +
            "<tr>" +
            "<td>区域名称:</td><td>&nbsp;&nbsp;</td><td><input style='width: 220px' type='text' value='" + params.ccMark + "' name='ccMark'/></td>" +
            "</tr>" +
            "<tr>" +
            "<td>手机号:</td><td>&nbsp;&nbsp;</td><td><input style='width: 220px' type='text' value='" + params.phone + "' name='phone'/></td>" +
            "</tr>" +
            "<tr>" +
            "<td>描述:</td><td>&nbsp;&nbsp;</td><td><input style='width: 220px' type='text' value='" + params.remark + "' name='remark'/></td>" +
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
                    url: "${contextPath}/admin/loginAccount/editUser",
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
                    url: "${contextPath}/admin/loginAccount/deleteUser?id=" + id,
                    complete: function () {
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

</script>
</body>
</html>
