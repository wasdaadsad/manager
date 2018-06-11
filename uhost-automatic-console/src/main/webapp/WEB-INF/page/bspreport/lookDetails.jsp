<%--
  Created by IntelliJ IDEA.
  User: dongjiajin
  Date: 2018/1/10
  Time: 18:06
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
            /*text-overflow : ellipsis;*/
            /*white-space: normal !important;*/
            /*height: auto;*/
            vertical-align: text-top;
            padding-top: 2px;
        }


        .btn-myStyle{
            width: 1000px;
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
                            <i class="ace-icon fa fa-signal"></i> 查看详情
                        </h5>

                        <div class="toolbar" id="toolbar">
                            <div class="clearfix">
                                <div class="row">

                                    </div>
                                    <%--<div class="col-xs-1">
                                        <div class="col-xs-4">
                                            <button id="addItem" class="btn btn-success btn-xs">
                                                <span class="ace-icon glyphicon glyphicon-ok"></span> 新增
                                            </button>
                                        </div>
                                    </div>--%>
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
<script src="${contextPath}/static/assets/plugin/ace/ace.searchbox-autocomplete.js"></script>
<script src="${contextPath}/static/assets/plugin/select2/js/select2.min.js"></script>
<script src="${contextPath}/static/assets/plugin/My97DatePicker/WdatePicker.js"></script>
<script src="${contextPath}/static/assets/plugin/echarts3/echarts.min.js"></script>
<script src="${contextPath}/static/assets/plugin/jquery.form.js"></script>
<script src="${contextPath}/static/assets/plugin/jqGrid/jquery.jqGrid.src.js"></script>
<script src="${contextPath}/static/assets/plugin/jqGrid/i18n/grid.locale-cn.js"></script>
<script src="${contextPath}/static/assets/plugin/My97DatePicker/WdatePicker.js"></script>
<script src="${contextPath}/static/assets/plugin/bootbox.js"></script>
<script src="${contextPath}/static/assets/plugin/layer-v3.1.1/layer.js"></script>

<!-- inline scripts related to this page -->

<script type="text/javascript">
    var grid_selector = "#grid-table";
    var pager_selector = "#grid-pager";
    // function escape(html){
    //     var elem = document.createElement('div');
    //     var txt = document.createTextNode(html);
    //     elem.appendChild(txt);
    //     return elem.innerHTML;
    // }
    function lookDetails(rowObject){
        // alert(JSON.stringify(rowObject.comment));
        // var a = escape(rowObject.comment);
        layer.open({
            title:"查看测试信息",
            type: 2,
            shadeClose: true,
            shade: 0.5,
            // area: '1600px',
            area: ['90%', '85%'],
            fixed: false, //不固定
            maxmin: true,
            anim: 1,
            success: function(layero, index) {
                layer.iframeAuto(index);
            },
            content: '${contextPath}/uhostmanage/recordManage/lookTestDetails?comment=' + rowObject.comment
        });
    }


    function detailFormatter(cellvalue, options, rowObject) {
        return "<a href='javascript:void(0)' onclick='lookDetails(" + JSON.stringify(rowObject) + ")'>查看测试信息</a> ";
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
            url: '${contextPath}/uhostmanage/recordManage/details?testId=' + ${testCode},
            datatype: "json",
            mtype: "post",
            postData: formData,
            colNames: ['测试编号', '测试名称', '子模块','测试信息','测试方法', '测试命令', '是否通过', '结果类型', '预期结果','测试时间'],
            colModel: [
                {name: 'testId', index: 'testId', align: "center", sortable: false, width: 60},
                {name: 'testName', index: 'testName', align: "center", sortable: false, width: 60},
                {name: 'childModel', index: 'childModel', align: "center", sortable: false, width: 60},
                {name: 'comment', index: 'comment', align: "center", formatter: detailFormatter, width: 60},
                {name: 'testMethod', index: 'testMethod', align: "center", sortable: false, width: 60},
                {name: 'testCmd', index: 'testCmd', align: "center", sortable: false, width: 60},
                {name: 'res', index: 'res', align: "center", sortable: false, width: 60},
                {name: 'resultState', index: 'resultState', align: "center", sortable: false, width: 60},
                {name: 'exceptResult', index: 'exceptResult', align: "center", sortable: false, width: 60},
                {name: 'testTime', index: 'testTime', align: "center", sortable: false, width: 60}
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

                // if (!response.stat || response.stat != '200') {
                //     var msg = response.msg || "";
                //     bootbox.alert("加载失败!" + msg);
                //     return false;
                // }
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
        // $(grid_selector).jqGrid('filterToolbar');
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

    });



</script>
</body>
</html>
