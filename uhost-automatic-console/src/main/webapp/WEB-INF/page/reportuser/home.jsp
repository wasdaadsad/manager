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

    </style>
</head>

<body class="main-container">

<div class="modal fade" id="statisticsModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    用户上线信息统计
                </h4>
            </div>
            <div class="modal-body">
                <iframe src="${contextPath}/admin/reportUser/findACStatisticsHome" frameborder="0" width="100%"
                        height="400px"></iframe>
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
                            <i class="ace-icon fa fa-signal"></i> 用户上线信息查询
                        </h5>

                        <div class="toolbar" id="toolbar">
                            <div class="clearfix">
                                <div class="row">

                                    <div class="col-xs-2 nopadding-right">
                                        <input type="text" name="registrant" class="input-sm col-xs-12"
                                               placeholder="用户名">
                                    </div>

                                    <div class="col-xs-2 nopadding-right">
                                        <input type="text" name="registrantPhone" class="input-sm col-xs-12"
                                               placeholder="手机号">
                                    </div>

                                    <div class="col-xs-3 nopadding-right">
                                        <label for="netpotSelect">网点编码：</label>
                                        <select name="ac" id="netpotSelect">
                                            <option value='all'>all</option>
                                            <c:forEach var="netPoint" items="${netPoints}">
                                                <option value='${netPoint}'>${netPoint}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="row">
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

                                    <div class="col-xs-3">
                                        <div class="col-xs-3">
                                            <button id="submit" class="btn btn-success btn-xs">
                                                <span class="ace-icon glyphicon glyphicon-ok"></span> 确定
                                            </button>
                                        </div>
                                        <div class="col-xs-3">
                                            <button id="undo" class="btn btn-warning btn-xs">
                                                <span class="ace-icon fa fa-undo"></span> 清空
                                            </button>
                                        </div>
                                        <div class="col-xs-3">
                                            <button id="statistics" class="btn btn-success btn-xs">
                                                <span class="ace-icon glyphicon glyphicon-ok"></span> 统计
                                            </button>
                                        </div>
                                        <div class="col-xs-3">
                                            <button id="export" class="btn btn-success btn-xs">
                                                <span class="ace-icon glyphicon glyphicon-ok"></span> 导出
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
<script src="${contextPath}/static/assets/plugin/My97DatePicker/WdatePicker.js"></script>
<script src="${contextPath}/static/assets/plugin/echarts3/echarts.min.js"></script>
<script src="${contextPath}/static/assets/plugin/jquery.form.js"></script>
<script src="${contextPath}/static/assets/plugin/jqGrid/jquery.jqGrid.src.js"></script>
<script src="${contextPath}/static/assets/plugin/jqGrid/i18n/grid.locale-cn.js"></script>
<script src="${contextPath}/static/assets/plugin/My97DatePicker/WdatePicker.js"></script>
<script src="${contextPath}/static/assets/plugin/bootbox.js"></script>
<script src="${contextPath}/static/assets/plugin/jquery.form.js"></script>
<script src="${contextPath}/static/assets/plugin/combo-select-master/jquery.combo.select.js"></script>

<!-- inline scripts related to this page -->

<script type="text/javascript">
    var grid_selector = "#grid-table";
    var pager_selector = "#grid-pager";
    var tablecontent = "#tablecontent";

    $('select').comboSelect();

    $("#statisticsModal").modal('hide');

    //////////////////////主表格//////////////////////////////
    $(window).on('resize.jqGrid', function () {
        $(grid_selector).jqGrid('setGridWidth', $(tablecontent).width());
    });

    $(window).triggerHandler('resize.jqGrid');//trigger window resize to make the grid get the correct size

    var formData = {};
    $.each($("#toolbar :input").serializeArray(), function (i, item) {
        formData[item.name] = item.value;
    });

    $(grid_selector).jqGrid({
        url: "${contextPath}/admin/reportUser/findList.json",
        datatype: "json",
        mtype: "post",
        postData: formData,
        colNames: ['id', '用户名', '联系方式', '网点', '用户类型', 'openId', '上线时间'],
        colModel: [
            {name: 'id', index: 'id', align: "center", sortable: false, width: 60},
            {name: 'registrant', index: 'registrant', align: "center", sortable: false, width: 60},
            {name: 'registrantPhone', index: 'registrantPhone', align: "center", sortable: false, width: 60},
            {name: 'ac', index: 'ac', align: "center", sortable: false, width: 60},
            {name: 'userTypeVP', index: 'userTypeVP', align: "center", sortable: false, width: 60},
            {name: 'openId', index: 'openId', align: "center", sortable: false, width: 90},
            {name: 'onlineTimeVP', index: 'onlineTimeVP', align: "center", sortable: false, width: 60},
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

            $(this).jqGrid('setGridWidth', $(tablecontent).width());    //加载完数据后重新设置下宽度

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

    $("#statistics").on("click", function () {
        $("#statisticsModal").modal('show');
    });

    $("#export").on("click", function () {

        var formData = {};
        $("#export").attr("disabled", "disabled");
        setTimeout("$('#export').removeAttr('disabled')", 3000);

        $.each($("#toolbar :input").serializeArray(), function (i, item) {
            formData[item.name] = item.value;
        });
        var url = "${contextPath}/admin/reportUser/export.xlsx";  //后缀决定如何解析View
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


    function enableTooltips(table) {
        $('.navtable .ui-pg-button').tooltip({container: 'body'});
        $(table).find('.ui-pg-div').tooltip({container: 'body'});
    }

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

</script>
</body>
</html>
