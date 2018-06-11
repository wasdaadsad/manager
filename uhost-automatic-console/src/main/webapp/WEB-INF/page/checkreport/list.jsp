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

    <link rel="stylesheet" href="${contextPath}/static/assets/plugin/combo-select-master/combo.select.css">

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

<div class="modal fade" id="malfunctionInfoModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" style="width: 90%;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    用户故障信息列表
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

<div class="main-content-inner">
    <div class="page-content">
        <div class="row">
            <div class="col-xs-12">
                <div class="widget-box">
                    <div class="widget-header widget-header-flat widget-header-small">
                        <h5 class="widget-title" id="title">
                            <i class="ace-icon fa fa-signal"></i> 检测记录列表
                        </h5>
                        <input type="text" style="display: none" id="totalElements">
                        <div class="toolbar" id="toolbar">
                            <div class="clearfix">
                                <div class="row">
                                    <div class="col-xs-1 nopadding-right">
                                        <input type="text" name="model" class="input-sm col-xs-12"
                                               placeholder="输入机型">
                                    </div>

                                    <div class="col-xs-1 nopadding-right">
                                        <input type="text" name="imei" class="input-sm col-xs-12"
                                               placeholder="输入imei">
                                    </div>

                                    <div class="col-xs-1 nopadding-right">
                                        <input type="text" name="registrant" class="input-sm col-xs-12"
                                               placeholder="输入用户名">
                                    </div>

                                    <div class="col-xs-3 ">
                                        <div class="input-daterange input-group">
                                            <input type="text" class="input-sm form-control" name="from" id="from"
                                                   onFocus="dateRangePick();">
                                            <span
                                                    class="input-group-addon">
												<i class="fa fa-calendar"></i>
											</span>
                                            <input type="text" class="input-sm form-control" id="to" name="to"
                                                   onFocus="dateRangePick();">
                                        </div>
                                    </div>

                                </div>
                                <div class="row" style="margin-top: 10px">
                                    <div class="col-xs-2 nopadding-right">
                                        <label for="netpotSelect">网点编码：</label>
                                        <select name="netPotCode" id="netpotSelect" placeholder="输入网点编码">
                                            <option>all</option>
                                            <c:forEach var="netPoint" items="${netPoints}">
                                                <option value='${netPoint}'>${netPoint}</option>
                                            </c:forEach>
                                        </select>
                                    </div>

                                    <div class="col-xs-2">
                                        <label for="valid">有效性</label>
                                        <select name="valid" id="valid" style="width: 200px">
                                            <option value="-1">全部</option>
                                            <c:forEach var="checkEnum" items="${checkEnums}">
                                                <option value='${checkEnum.val}'>${checkEnum.remark}</option>
                                            </c:forEach>
                                        </select>
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
                                        <div class="col-xs-4">
                                            <button id="export" class="btn btn-success btn-xs">
                                                <span class="ace-icon glyphicon glyphicon-ok"></span> 数据导出
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
<script src="${contextPath}/static/assets/plugin/combo-select-master/jquery.combo.select.js"></script>


<!-- inline scripts related to this page -->

<script type="text/javascript">
    var grid_selector = "#grid-table";
    var pager_selector = "#grid-pager";

    jQuery(function ($) {

        $('select').comboSelect();

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
            url: "${contextPath}/admin/checkReport/list.json",
            datatype: "json",
            mtype: "post",
            postData: formData,
            colNames: ['外部机型', 'imei', '检测日期', '网店编码', '用户名', '检测有效性', '描述', 'openId', '用户类型', '详情查看', '故障查看', '下载报告', '日志详情'],
            colModel: [
                {name: 'model', index: 'model', align: "center", sortable: false, width: 35},
                {name: 'imei', index: 'imei', align: "center", sortable: false, width: 55},
                {name: 'checkDate', index: 'checkDate', align: "center", sortable: false, width: 60},
                {name: 'netPotCode', index: 'netPotCode', align: "center", sortable: false, width: 45},
                {name: 'registrant', index: 'registrant', align: "center", sortable: false, width: 45},
                {name: 'validVP', index: 'validVP', align: "center", sortable: false, width: 80},
                {name: 'description', index: 'description', align: "center", sortable: false, width: 80},
                {name: 'openId', index: 'openId', align: "center", sortable: false, width: 80},
                {name: 'userTypeVP', index: 'userType', align: "center", sortable: false, width: 30},
                {
                    name: 'id',
                    index: 'id',
                    align: "center",
                    sortable: false,
                    width: 30,
                    formatter: toDetailFormatter
                }, {
                    name: 'id',
                    index: 'id',
                    align: "center",
                    sortable: false,
                    width: 30,
                    formatter: malfunctionFormatter
                }, {
                    name: 'urlPath',
                    index: 'urlPath',
                    align: "center",
                    sortable: false,
                    width: 30,
                    formatter: toZipPathFmatter
                }, {
                    name: 'id',
                    index: 'id',
                    align: "center",
                    sortable: false,
                    width: 30,
                    formatter: toZipUserLogFmatter
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
                //更新当前记录数
                $('#totalElements').val(response.data.totalElements);
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

    $('.js-select').change(function (e, v) {
        $('.idx').text(e.target.selectedIndex)
        $('.val').text(e.target.value)
    })

    /**
     * Open select
     */

    $('.js-select-open').click(function (e) {
        $('.js-select').focus();
        e.preventDefault();
    });

    /**
     * Open select
     */

    $('.js-select-close').click(function (e) {
        $('.js-select').trigger('comboselect:close')
        e.preventDefault();
    })

    $("#malfunctionInfoModal").modal("hide");

    function toDetailFormatter(cellvalue, options, rowObject) {
        return "<a target='_blank' href='${contextPath}/admin/checkreportdetail/home?checkReportId=" + cellvalue + "'>详情查看</a>";
    }

    function malfunctionFormatter(cellvalue, options, rowObject) {
        return "<a href='#' onclick='openmalfunctionInfoDailog(" + rowObject.imei + ")'>故障查看</a>";
    }


    /**
     * 下载用户日志
     * @param cellvalue
     * @param options
     * @param rowObject
     * @returns {string}
     */
    function toZipUserLogFmatter(cellvalue, options, rowObject) {
        return "<a href='#' onclick='downloadUserLog(" + cellvalue + ")'>下载</a>";
    }


    /**
     * 获取检查报告对应的当天的最新的日志
     * @param reportId
     */
    function downloadUserLog(reportId) {
        window.location.href = '${contextPath}/admin/checkReport/download?reportId=' + reportId;
    }


    function openmalfunctionInfoDailog(imei) {
        $("#iframeContainer").html("<iframe src='${contextPath}/admin/malfunction/detail?imei=" + imei + "' width='100%'  frameborder='0'  height='400px'></iframe>");
        $("#malfunctionInfoModal").modal("show");
    }

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

    $("#export").on("click", function () {
        var num = parseInt($('#totalElements').val());
        if (0 == num) {
            bootbox.alert("当前数据为空,请重新选择查询条件！！！");
            return false;
        } else if (num > 2000) {
            bootbox.alert("单次导出量不能超过2000条,请重新选择查询条件！！！");
            return false;
        }
        var formData = {};
        $("#export").attr("disabled", "disabled");
        setTimeout("$('#export').removeAttr('disabled')", 3000);

        $.each($("#toolbar :input").serializeArray(), function (i, item) {
            formData[item.name] = item.value;
        });
        var url = "${contextPath}/admin/checkReport/export.xlsx";  //后缀决定如何解析View
        var isFirst = true;
        var params = "";
        for (var name in formData) {
            if (!formData[name] || formData[name] == "") {
                continue;
            }
            if (isFirst) {
                params += "?";
                isFirst = false;
            } else {
                params += "&";
            }
            params += (name + "=" + formData[name]);
        }
        ;
        window.location.href = encodeURI(encodeURI(url + params));
    });

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
