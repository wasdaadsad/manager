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
                            <i class="ace-icon fa fa-signal"></i> 诊断结果统计
                        </h5>

                        <div class="toolbar" id="toolbar">
                            <div class="clearfix">
                                <div class="row">
                                    <div class="col-xs-3 ">

                                        <label>内外销：</label>
                                        <select name="innerOut" id="innerOut" style="width: 80px">
                                            <c:forEach var="innerOutType" items="${innerOutTypes}">
                                                <option value='${innerOutType.index}'>${innerOutType.remark}</option>
                                            </c:forEach>
                                        </select>
                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

                                        <label>周期：</label>
                                        <select name="rangeType" id="rangeType" style="width: 80px" onchange="changeDatePicker()">
                                            <c:forEach var="rangeType" items="${rangeTypes}">
                                                <option value='${rangeType.index}'>${rangeType.remark}</option>
                                            </c:forEach>
                                        </select>
                                    </div>

                                    <div class="col-xs-3 ">
                                        <div class="input-daterange input-group" id="datePicker">
                                            <input type="text" class="input-sm form-control" name="from" id="from"
                                                   onclick="WdatePicker({maxDate:'#F{$dp.$D(\'to\',{d:-3});}',minDate:'#F{$dp.$D(\'to\',{d:-31});}',readOnly:true,isShowWeek: true})">
                                            <span
                                                    class="input-group-addon">
												<i class="fa fa-calendar"></i>
											</span>
                                            <input type="text" class="input-sm form-control" id="to" name="to"
                                                   value="${to}"
                                                   onclick="WdatePicker({minDate:'#F{$dp.$D(\'from\',{d:3});}',maxDate:'%y-%M-%d',readOnly:true,isShowWeek: true})">
                                        </div>
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
                            </div>
                        </div>
                    </div>

                    <div class="widget-body">
                        <div class="widget-main">
                            <div id="container">

                            </div>
                            <div id="container2">

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
<script src="${contextPath}/static/assets/plugin/highchart/code/highcharts.js"></script>
<script src="${contextPath}/static/assets/plugin/highchart/code/modules/exporting.js"></script>

<!-- inline scripts related to this page -->

<script type="text/javascript">

    $('#submit').click(function () {
        ajaxSatisfactionData();
    });

    Highcharts.chart('container', {
        chart: {
            type: 'spline'
        },
        title: {
            text: '诊断结果满意度统计'
        },
        colors: ['#492970', '#f28f43', '#77a1e5', '#c42525', '#a6c96a'],
        xAxis: {
            categories: []
        },
        yAxis: {
            title: {
                text: '满意度'
            },
            labels: {
                formatter: function () {
                    return this.value;
                }
            }
        },
        tooltip: {
            crosshairs: true,
            shared: true
        },
        plotOptions: {
            spline: {
                marker: {
                    radius: 4,
                    lineColor: '#666666',
                    lineWidth: 1
                }
            }
        },
        series: []
    });

    Highcharts.chart('container2', {
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false,
            type: 'pie'
        },
        title: {
            text: '诊断评价占比统计'
        },
        tooltip: {
            pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: true,
                    format: '<b>{point.name}</b>: {point.percentage:.1f} %',
                    style: {
                        color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                    }
                }
            }
        },
        series: [{
            name: 'Brands',
            colorByPoint: true,
            data: []
        }]
    });

    function ajaxSatisfactionData() {
        var formData = {};
        $.each($("#toolbar :input").serializeArray(), function (i, item) {
            formData[item.name] = item.value;
        });
        $.post('${contextPath}/analysis/checkData/checkedSatisfaction.json', formData, function (data) {
            if (data.stat == 200) {
                Highcharts.chart('container', {
                    chart: {
                        type: 'spline'
                    },
                    title: {
                        text: '诊断结果满意度统计'
                    },
                    colors: ['#492970', '#f28f43', '#77a1e5', '#c42525', '#a6c96a', 'red', 'orange', 'green', 'blue', 'purple', 'brown'],
                    xAxis: {
                        categories: data.data.categories
                    },
                    yAxis: {
                        title: {
                            text: '满意度'
                        },
                        labels: {
                            formatter: function () {
                                return this.value;
                            }
                        }
                    },
                    tooltip: {
                        crosshairs: true,
                        shared: true,
                    },
                    plotOptions: {
                        spline: {
                            marker: {
                                radius: 4,
                                lineColor: '#666666',
                                lineWidth: 1
                            }
                        }
                    },
                    series: data.data.yNodes
                });
            }
        });

        $.post('${contextPath}/analysis/checkData/satisfactionContent.json', formData, function (data) {
            if (data.stat == 200) {
                Highcharts.chart('container2', {
                    chart: {
                        plotBackgroundColor: null,
                        plotBorderWidth: null,
                        plotShadow: false,
                        type: 'pie'
                    },
                    title: {
                        text: '网点检测结果占比统计'
                    },
                    tooltip: {
                        pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
                    },
                    plotOptions: {
                        pie: {
                            allowPointSelect: true,
                            cursor: 'pointer',
                            dataLabels: {
                                enabled: true,
                                format: '<b>{point.name}</b>: {point.percentage:.1f} %',
                                style: {
                                    color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                                }
                            }
                        }
                    },
                    series: [{
                        name: 'Brands',
                        colorByPoint: true,
                        data: data.data
                    }]
                });
            }
        });
    }


    jQuery(function ($) {
        ajaxSatisfactionData();
    });

    function changeDatePicker() {
        var rangeType = $('#rangeType').val();
        if (rangeType == 0) {
            $('#datePicker').show();
        } else {
            $('#datePicker').hide();
        }
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
