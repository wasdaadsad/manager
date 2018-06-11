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

        /* 单元格内容进行换行 word-break(数字强制换行)*/
        .ui-jqgrid tr.jqgrow td {
            white-space: normal !important;
            height:auto;
            vertical-align:text-top;
            padding-top:2px;
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
                        <div class="toolbar" id="toolbar">
                            <div class="clearfix">
                                <div class="row">
                                    <div class="col-xs-2 nopadding-right">
                                        <input type="text" name="testNum" class="input-sm col-xs-12"
                                               placeholder="测试编号">
                                    </div>

                                    <div class="col-xs-2 nopadding-right">
                                        <input type="text" name="recordIp" class="input-sm col-xs-12"
                                               placeholder="电脑IP">
                                    </div>

                                    <div class="col-xs-2 nopadding-right">
                                        <input type="text" name="modelVersion" class="input-sm col-xs-12"
                                               placeholder="机型版本">
                                    </div>

                                    <div class="col-xs-2 nopadding-right">
                                        <input type="text" name="softVersion" class="input-sm col-xs-12"
                                               placeholder="软件版本">
                                    </div>

                                    <div class="col-xs-3">

                                        <button id="submit" class="btn btn-success btn-xs">
                                            <span class="ace-icon glyphicon glyphicon-ok"></span> 搜索
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
<script src="${contextPath}/static/assets/plugin/layer-v3.1.1/layer.js"></script>
<!-- inline scripts related to this page -->

<script type="text/javascript">
    var grid_selector = "#grid-table";
    var pager_selector = "#grid-pager";


    function lookDetails(cellvalue,rowObject){
        layer.open({
            title:"查看详情",
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
            content: '${contextPath}/uhostmanage/recordManage/lookDetails?testCode=' + rowObject.testNum
        });
    }

    function execDown(params){
        console.log("id:"+params.id)
        location.href="${contextPath}/uhostmanage/recordManage/loadFile?id="+params.id
    }

    function showFormatter(cellvalue, options, rowObject) {
        return "<a href='javascript:void(0)' onclick='lookDetails(" + JSON.stringify(cellvalue) + "," + JSON.stringify(rowObject) + ")'>查看详情</a> ";
    }

    function downFormatter(cellvalue, options, rowObject) {
        return "<a href='javascript:void(0)' id='reportUrl' onclick='execDown(" + JSON.stringify(rowObject) + ")'><font color=blue>报告下载</a> ";
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
            url: "${contextPath}/uhostmanage/recordManage/list.json",
            datatype: "json",// 从服务器端返回的数据类型
            mtype: "post",// ajax提交方式。POST或者GET，默认GET
            postData: formData,// 此数组内容直接赋值到url上，参数类型：{name1:value1…} 默认空array{}
            colNames: ['测试编号','IP地址', '机型版本', '软件版本','更新时间','点检报告','查看详情'],//表头
            // 设置表格列的属性
            colModel: [
                {name: 'testNum', index: 'testNum', align: "center", sortable: false, width:30},
                {name: 'recordIp', index: 'recordIp', align: "center", sortable: false, width: 40},
                {name: 'modelVersion', index: 'modelVersion', align: "center", sortable: false, width: 50},
                {name: 'softVersion', index: 'softVersion', align: "center", sortable: false, width: 50},
                {name: 'updateTime', index: 'updateTime', align: "center", sortable: false, width: 50, formatter:'date', formatoptions: {srcformat:'Y-m-d H:i:s',newformat:'Y-m-d H:i:s'}},
                {
                    name: 'recordUrl',
                    index: 'recordUrl',
                    align: "center", sortable: false, width: 50,
                    formatter: downFormatter
                },
                {
                    name: 'id',
                    index: 'id',
                    align: "center", sortable: false, width: 30,
                    formatter: showFormatter
                }
            ],
            rowNum: 20, // 默认每页记录的数据
            rowList: [10,20,50,100], //可选设置每页记录的数据
            sortorder: 'desc',
            sortname: 'uniqid', // 默认的排序列。可以是列名称或者是一个数字，这个参数会被提交到后台
            viewrecords: true, // 定义是否要显示总记录数
            pager: pager_selector,// 分页标签
            altRows: true, //设置表格 zebra-striped 值
            // 当返回的数据行数为0时显示的信息。只有当属性 viewrecords 设置为ture时起作用
            caption:"点检报告列表", //小标题
            toolbar:[true,"top"], //点击向上收起excel
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
            // 如果为ture时，则当表格在首次被创建时会根据父元素比例重新调整表格宽度。
            // 如果父元素宽度改变，为了使表格宽度能够自动调整则需要实现函数：setGridWidth
            autowidth: true,
            height: 'auto',
            //当参数为null时不会被发到服务器端
            prmNames: {
                page: "page",
                rows: "size",
                sort: "sinx",
                order: "order",
                search: "search",
                nd: "nd",
                npage: null
            },
            //描述json 数据格式的数组
            jsonReader: {
                root: "data.content",
                total: "data.totalPages", //总页数
                page: "data.number", //当前页
                records: "data.totalElements", //查询出的记录数
                repeatitems: false
            }
        });

        $(grid_selector).jqGrid('filterToolbar');//激活筛选框

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
    });

    //jquery

    function toZipPathFmatter(cellvalue, options, rowObject) {
        return "<a href='" + cellvalue + "'>下载</a>";
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


    //日期选择控件
    function dateRangePick() {
        WdatePicker({
            onpicked: function () {
                this.blur();
            },
            readOnly: true,
            isShowWeek: true,
            qsEnabled: false,
            dateFmt: "yyyy-MM-dd"
        });
    }

</script>
</body>
</html>
