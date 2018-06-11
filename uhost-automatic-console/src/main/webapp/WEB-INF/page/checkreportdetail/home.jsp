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
            height:auto;
            vertical-align:text-top;
            padding-top:2px;
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
                            <i class="ace-icon fa fa-signal"></i> 检测报告详情
                        </h5>

                        <div class="toolbar" id="toolbar">
                            <div class="clearfix">
                                <div class="row">
                                    <input type="hidden" name="checkReportId" class="input-sm col-xs-12" value="${checkReport.id}"
                                               placeholder="检测报告Id">
                                    <div class="col-xs-1 nopadding-right">
                                        用户：
                                        <input type="text" readonly class="input-sm col-xs-12" value="${checkReport.registrant}">
                                    </div>
                                    <div class="col-xs-2 nopadding-right">
                                        检测时间：
                                        <input type="text" readonly class="input-sm col-xs-12" value="${checkReport.checkDate}">
                                    </div>
                                    <div class="col-xs-2 nopadding-right">
                                        网点编码
                                        <input type="text" readonly class="input-sm col-xs-12" value="${checkReport.netPotCode}">
                                    </div>
                                    <div class="col-xs-2 nopadding-right">
                                        用户类型
                                        <input type="text" readonly class="input-sm col-xs-12" value="${checkReport.userTypeVP}">
                                    </div>
                                    <div class="col-xs-1 nopadding-right">
                                        外部机型
                                        <input type="text" readonly class="input-sm col-xs-12" value="${checkReport.model}">
                                    </div>
                                    <div class="col-xs-2 nopadding-right">
                                        imei
                                        <input type="text" readonly class="input-sm col-xs-12" value="${checkReport.imei}">
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
        url: "${contextPath}/admin/checkreportdetail/findCheckReportDetails.json",
        datatype: "json",
        mtype: "post",
        postData: formData,
        colNames: ['编号', '检测项', '检测结果', '检测建议', '检测状态', '检测报告id'],
        colModel: [
            {name: 'number', index: 'number', align: "center", sortable: false, width: 30},
            {name: 'checkItem', index: 'checkItem', align: "center", sortable: false, width: 50},
            {name: 'checkResult', index: 'checkResult', align: "center", sortable: false, width: 40},
            {name: 'checkSuggestion', index: 'checkSuggestion', align: "center", sortable: false, width: 120},
            {name: 'checkStatus', index: 'checkStatus', align: "center", sortable: false, width: 120},
            {name: 'checkReportId', index: 'checkReportId', align: "center", sortable: false, width: 30},
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
