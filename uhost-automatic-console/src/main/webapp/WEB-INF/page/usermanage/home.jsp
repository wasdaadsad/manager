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
    <title>vivo comm2后台</title>
    <!-- page specific plugin styles -->
    <link rel="stylesheet"
          href="${contextPath}/static/assets/css/jquery-ui.css"/>
    <link rel="stylesheet"
          href="${contextPath}/static/assets/css/ui.jqgrid.css"/>

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
                            <i class="ace-icon fa fa-signal"></i> 用户列表
                        </h5>

                        <div class="toolbar" id="toolbar">
                            <div class="clearfix">
                                <div class="row">

                                    <div class="col-xs-2 nopadding-right">
                                        <input type="text" name="userName" class="input-sm col-xs-12"
                                               placeholder="输入用户名">
                                    </div>

                                    <div class="col-xs-2 nopadding-right">
                                        <input type="text" name="userCode" class="input-sm col-xs-12"
                                               placeholder="工号">
                                    </div>
                                    <div class="col-xs-2 nopadding-right">
                                        <input type="text" name="department" class="input-sm col-xs-12"
                                               placeholder="部门">
                                    </div>
                                    <div class="col-xs-2 nopadding-right">
                                        <input type="text" name="authority" class="input-sm col-xs-12"
                                               placeholder="权限">
                                    </div>

                                    <div class="col-xs-3">


                                            <button id="submit" class="btn btn-success btn-xs">
                                                <span class="ace-icon glyphicon glyphicon-ok"></span> 确定
                                            </button>

                                             <button id="undo" class="btn btn-warning btn-xs">
                                                 <span class="ace-icon fa fa-undo"></span> 清空
                                             </button>

                                            <button id="addItem" class="btn btn-success btn-xs">
                                                <span class="ace-icon glyphicon glyphicon-ok"></span> 新增
                                            </button>

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
<script src="${contextPath}/static/assets/plugin/My97DatePicker/WdatePicker.js"></script>
<script src="${contextPath}/static/assets/plugin/echarts3/echarts.min.js"></script>
<script src="${contextPath}/static/assets/plugin/jquery.form.js"></script>
<script src="${contextPath}/static/assets/plugin/jqGrid/jquery.jqGrid.src.js"></script>
<script src="${contextPath}/static/assets/plugin/jqGrid/i18n/grid.locale-cn.js"></script>
<script src="${contextPath}/static/assets/plugin/My97DatePicker/WdatePicker.js"></script>
<script src="${contextPath}/static/assets/plugin/bootbox.js"></script>
<script src="${contextPath}/static/assets/plugin/jquery.form.js"></script>

<!-- inline scripts related to this page -->

<script type="text/javascript">
    var grid_selector = "#grid-table";
    var pager_selector = "#grid-pager";


    function execEdit(params) {
        bootbox.confirm("" +
            "<form id='editForm' >" +
            "<input type='hidden' name='id' value='"+params.id+"'>" +
            "用户名:<input type='text' name='userName' value='"+params.userName+"'><br/>" +
            "工号:<input type='text' name='userCode' value='"+params.userCode+"'/><br/>" +
            "部门:<input type='text' name='department' value='"+params.department+"'/><br/>" +
            "权限:<input type='text' name='authority' value='"+params.authority+"'/><br/>" +
            "</form>",
            function (result) {
            var formData = {};
            $.each($("#editForm :input").serializeArray(), function (i, item) {
                formData[item.name] = item.value;
            });
            if (result) {
                $.ajax({
                    type: 'POST',
                    url:"${contextPath}/uhostmanage/userManage/edit",
                    data:formData,
                    complete:function(){
                        gridReload();
                    }
                })
            }
        });
    }

    function execDelete(id){
        bootbox.confirm("确定是否删除？", function (result) {
            if (result) {
                $.ajax({
                    type: 'GET',
                    url:"${contextPath}/uhostmanage/userManage/delete?id="+id,
                    complete:function(){
                        gridReload();
                    }
                })
            }
        });
    }


    $("#addItem").on("click", function (e) {
        bootbox.confirm("" +
            "<form id='addForm' >" +
            "用户名:<input type='text' name='userName'><br/>" +
            "工号:<input type='text' name='userCode'/><br/>" +
            "部门:<input type='text' name='department'/><br/>" +
            "权限:<input type='text' name='authority'/><br/>" +
            "</form>",
            function (result) {
            var formData = {};
            $.each($("#addForm :input").serializeArray(), function (i, item) {
                formData[item.name] = item.value;
            });
            if (result) {
                console.log(formData);
                $.ajax({
                    type: 'POST',
                    url:"${contextPath}/uhostmanage/userManage/add",
                    data:formData,
                    complete:function(){
                        gridReload();
                    }
                })
            }
        });
    });



    //执行编辑删除和添加操作
    function editFormatter(cellvalue, options, rowObject) {
        return "<a href='javascript:void(0)' onclick='execEdit(" + JSON.stringify(rowObject) + ")'>编辑</a> ";
    }

    function deleteFormatter(cellvalue, options, rowObject) {
        return "<a href='javascript:void(0)' onclick='execDelete("+cellvalue+")'>删除</a> ";
    }




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
            url: "${contextPath}/admin/usermanage/userlist.json",
            datatype: "json",
            mtype: "post",
            postData: formData,
            colNames: ['用户名', '工号', '部门','权限','编辑', '删除'],
            colModel: [
                {name: 'userName', index: 'userName', align: "center", sortable: false, width: 60, stype: "select", sopt:["eq"],searchoptions: {multiple:true, value: ":[ALL];董家进:董家进;高军军:高军军;卢志伟:卢志伟" }},
                {name: 'userCode', index: 'userCode', align: "center", sortable: false, width: 60},
                {name: 'department', index: 'department', align: "center", sortable: false, width: 60},
                {name: 'authority', index: 'authority', align: "center", sortable: false, width: 60,stype: "select", sopt:["eq"],searchoptions: {multiple:true, value: ":[ALL];超级管理员:超级管理员;管理员:管理员;用户:用户" }},
                {
                    name: 'id',
                    index: 'id',
                    align: "center",
                    sortable: false,
                    width: 30,
                    formatter: editFormatter
                },
                {
                    name: 'id',
                    index: 'id',
                    align: "center",
                    sortable: false,
                    width: 30,
                    formatter: deleteFormatter
                }
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

        $(grid_selector).jqGrid('filterToolbar');//激活筛选框
        $(grid_selector).jqGrid('navGrid',"#jqGridPager", {
            search: false, // show search button on the toolbar
            add: false,
            edit: false,
            del: false,
            refresh: true
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
</script>
</body>
</html>
