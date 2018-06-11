/**
 *
 * @param option
 * type : map | line | pie
 * url : xxx
 * chart: xxx
 * names : []
 * validParams : function()
 * customOption : {}
 */
function fetchChartData(option) {
    if (!option.chart) {
        alert("chart没有初始化")
    }
    if (!option.url) {
        alert("网络配置错误");
    }
    var ajaxOption = {
        url: option.url,
        data: getParamsByNames(option.names),
        beforeSend: function () {
            if (option.validParams) {
                var result = option.validParams();
                if (result == false) {
                    return false;
                }
            }
            var loadingOption = {
                color: '#008cd6',
                maskColor: 'rgba(255, 255, 255, 0.8)'
            };
            option.chart.showLoading('default', loadingOption);
        },
        success: function (response) {


            if (option.type == "line" || option.type == "bar") {
                drawLineChart(option, response.data);
            }
            if (option.type == "map") {
                drawMapChart(option, response.data);
            }
            if (option.type == "pie") {
                drawPieChart(option, response.data);
            }
            if (option.type == "map2") {
                drawMapChart2(option, response.data);
            }
        },
        complete: function () {
            option.chart.hideLoading();
            if (option.trigger) {
                option.trigger();
            }
        }
    };
    fetchJsonData(ajaxOption);
}


//初始化地图
function initChart(option) {
    if (option.type == "line" || option.type == "bar") {
        drawLineChart(option, null);
    }
    if (option.type == "map") {
        drawMapChart(option, null);
    }
    if (option.type == "pie") {
        drawPieChart(option, null);
    }
    if (option.type == "map2") {
        drawMapChart2(option, null)
    }
}

//画地图,分布图
function drawMapChart2(mapOption, data) {
    var option = {
        title: {
            left: 'center'
        },
        tooltip: {
            trigger: 'item',
            formatter: function (params) {
                if (!params) {
                    return '';
                }
                var spanStart = "<span style='display:inline-block;margin-right:5px;border-radius:10px;width:9px;height:9px;background-color:";
                var spanEnd = "'></span>";
                var result = '';
                result += params.data.name + '<br/>';
                result += spanStart + params.data.color + spanEnd + params.seriesName + ' : ' + params.data.value;
                if (params.data && params.data.extra) {
                    result += '<br/>' + params.data.extra;
                }
                return result;
            }
        },
        legend: {
            orient: 'vertical',
            left: 'left'
        },
        visualMap: {
            min: 0,
            max: $("#warningValue").val(),
            left: 'left',
            top: 'bottom',
            text: ['高', '低'],           // 文本，默认为数值文本
            calculable: true
        },
        toolbox: {
            show: true,
            orient: 'vertical',
            left: 'right',
            top: 'center',
            feature: {
                myBack: {
                    show: true,
                    title: '返回主界面',
                    icon: 'path://M432.45,595.444c0,2.177-4.661,6.82-11.305,6.82c-6.475,0-11.306-4.567-11.306-6.82s4.852-6.812,11.306-6.812C427.841,588.632,432.452,593.191,432.45,595.444L432.45,595.444z M421.155,589.876c-3.009,0-5.448,2.495-5.448,5.572s2.439,5.572,5.448,5.572c3.01,0,5.449-2.495,5.449-5.572C426.604,592.371,424.165,589.876,421.155,589.876L421.155,589.876z M421.146,591.891c-1.916,0-3.47,1.589-3.47,3.549c0,1.959,1.554,3.548,3.47,3.548s3.469-1.589,3.469-3.548C424.614,593.479,423.062,591.891,421.146,591.891L421.146,591.891zM421.146,591.891',
                    onclick: function () {
                        if (mapOption.onClickByBack) {
                            mapOption.onClickByBack()
                        }
                    }
                },
                dataView: {readOnly: false},
                restore: {},
                saveAsImage: {}

            }
        },
        series: [],
        randerSeries: function () {
            if (!data || !data.series) {
                return;
            }
            var seriesItem = {};
            for (var i = 0; i < data.series.length; i++) {
                seriesItem.type = 'map';
                seriesItem.roam = false;
                seriesItem.label = {
                    normal: {
                        show: true
                    },
                    emphasis: {
                        show: true
                    }
                }
                this.series.push(seriesItem);
            }
        }
    };
    option.randerSeries();
    if (mapOption.customOption) {
        $.extend(true, option, mapOption.customOption); //再用自定义页面项覆盖
    }
    if (data) {
        $.extend(true, option, data);
    }

    drawChartByOption(mapOption.chart, option); //绘制

    if (mapOption.isCache == true) { //缓存必要缓存的
        mapOption.cache = {
            chart: mapOption.chart,
            option: option
        };
    }

}

function drawChartByOption(chart, option) {
    chart.clear();
    chart.setOption(option);
}

//画地图散点图
function drawMapChart(mapOption, data) {

    var option = {
        backgroundColor: '#404a59',
        title: {
            text: "",
            subText: "",
            left: 'center',
            textStyle: {
                color: '#fff'
            }
        },
        tooltip: {
            trigger: 'item'
        },
        legend: {
            data: [],
            orient: 'vertical',
            y: 'bottom',
            x: 'right',
            textStyle: {
                color: '#fff'
            }
        },
        geo: {
            map: 'china',
            label: {
                emphasis: {
                    show: false
                }
            },
            roam: true,
            itemStyle: {
                normal: {
                    areaColor: '#323c48',
                    borderColor: '#111'
                },
                emphasis: {
                    areaColor: '#2a333d'
                }
            }
        },

        series: [],

        randerSeries: function () {
            var colors = ['#008cd6', '#c23531', '#2f4554', '#61a0a8', '#d48265',
                '#91c7ae', '#749f83', '#ca8622', '#bda29a', '#6e7074',
                '#546570', '#c4ccd3'];
            if (data && data.series) {
                for (var i = 0; i < data.series.length; i++) {
                    var series = {};
                    series.type = 'scatter';
                    series.coordinateSystem = 'geo';
                    series.symbolSize = function (val) {
                        return val[2] / 10;
                    };
                    series.label = {
                        normal: {
                            formatter: '{b}',
                            position: 'right',
                            show: false
                        },
                        emphasis: {
                            show: true
                        }
                    }
                    series.itemStyle = {
                        normal: {
                            color: colors[i]
                        }
                    }
                    this.series.push(series);
                }
            }
        }
    };

    option.randerSeries();
    if (mapOption.customOption) {
        $.extend(true, option, mapOption.customOption); //再用自定义页面项覆盖
    }
    if (data) {
        $.extend(true, option, data);
    }

    mapOption.chart.setOption(option);
}

/**
 * @param lineOption 视图相关
 * @param data 数据相关
 */
function drawLineChart(lineOption, data) {
    var option = {
        color: ['#008cd6', '#c23531', '#2f4554', '#61a0a8', '#d48265',
            '#91c7ae', '#749f83', '#ca8622', '#bda29a', '#6e7074',
            '#546570', '#c4ccd3'],
        toolbox: {
            show: true,
            feature: {
                dataZoom: {
                    yAxisIndex: 'none'
                },
                dataView: {readOnly: true},
                magicType: {type: ['line', 'bar']},
                restore: {},
                saveAsImage: {}
            }
        },
        tooltip: {
            trigger: 'axis',
            formatter: function (params) {
                if (!params) {
                    return '';
                }
                var spanStart = "<span style='display:inline-block;margin-right:5px;border-radius:10px;width:9px;height:9px;background-color:";
                var spanEnd = "'></span>";
                var result = '';

                for (var i in params) {
                    var item = params[i];
                    if (i == 0) {
                        result += item.name + '<br/>';
                    }
                    result += spanStart + item.color + spanEnd
                    + item.seriesName + ' : ' + item.value;

                    if (item.data && item.data.extra) {
                        result += '<br/>' + item.data.extra;
                    }
                    result += '<br/>';
                }
                return result;
            }
        },
        grid: {
            left: '15',
            right: '30',
            bottom: '20',
            containLabel: true
        },
        yAxis: {
            type: 'value'
        },

        xAxis: {
            data: [],
            type: 'category',
            boundaryGap: false
        },
        title: {
            text: "",
            subText: ""
        },
        legend: {
            data: []
        },
        series: [],
        //渲染series的默认配置
        randerSeries: function () {
            if (data && data.series) {
                for (var i = 0; i < data.series.length; i++) {
                    var item = {};
                    item.smooth = true;
                    item.type = lineOption.type;
                    item.symbolSize = 8;
                    this.series.push(item);
                }
            }
        }
    };
    option.randerSeries();
    if (lineOption.customOption) {
        $.extend(true, option, lineOption.customOption); //自定义页面项覆盖
    }
    if (data) {
        $.extend(true, option, data); //先用返回值覆盖
    }
    drawChartByOption(lineOption.chart, option); //绘制

    if (lineOption.isCache == true) { //有必要缓存就缓存的
        lineOption.cache = {
            chart: lineOption.chart,
            option: option
        };
    }
}

//画饼状图
function drawPieChart(pieOption, data) {
    var option = {
        title: {
            x: 'center'
        },
        toolbox: {
            show: true,
            feature: {
                dataView: {readOnly: true},
                saveAsImage: {}
            }
        },
        tooltip: {
            trigger: 'item',
            formatter: function (params) {
                return params.data.extra + ":<br/>" + params.name + " : " + params.value + " (" + params.percent + "%)"
            }
        },

        series: [{ //饼状图的series一般只有一个
            type: 'pie',
            radius: ['0%', '50%'],
            label: {
                normal: {
                    show: true,
                    formatter: "{b}"
                }
            },
            labelLine: {
                normal: {
                    length: 5,
                    length2: 10,
                    smooth: true
                }
            },
            itemStyle: {
                emphasis: {
                    shadowBlur: 10,
                    shadowOffsetX: 0,
                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                }
            }
        }]
    };

    if (pieOption.customOption) {
        $.extend(true, option, pieOption.customOption); //自定义页面项覆盖
    }
    if (data) {
        $.extend(true, option, data); //先用返回值覆盖
    }
    drawChartByOption(pieOption.chart, option); //绘制
}


/**
 *
 * @param option
 *      isRadio 是否为单选
 *      select select的jquery对象
 */
function initMultipleSelect(option) {
    var id = option.select.attr("id");
    $("#" + id + " option").remove();
    var $select = option.select;
    if (!option.isRadio) {
        $select.attr("multiple", "multiple");
    }

    $select.select2();
}

//获取多选框数据
function fetchMultipleSelectData(option) {
    var op = option;
    op.data = getParamsByNames(option.names);
    op.success = function (data) {
        op.select.select2({
            data: data
        });
    };
    op.error = function () {
        alert("获取数据失败");
    };
    op.beforeSend = function () {
        $("#" + op.loadIconId).show();
    };
    op.complete = function () {
        $("#" + op.loadIconId).hide();
    };
    fetchJsonData(op);
}

//初始化日期选择
function initDatePick(fromPickId, toPickId, dateFieldId) {
    var dateFieldItem = $("#" + dateFieldId);
    var fromPickItem = $("#" + fromPickId);
    var toPickItem = $("#" + toPickId);
    dateFieldItem.on("change", function () {
        fromPickItem.val('');
        toPickItem.val('');
    });
    fromPickItem.on("focus", dateRangePick);
    toPickItem.on("focus", dateRangePick);
}

//日历选择
function dateRangePick() {
    var typeFmt = {
        "日": 'yyyy-MM-dd',
        "周": 'yyyy-MM',
        "月": 'yyyy-MM',
        "年": 'yyyy'
    };
    var id = $(this).attr("id");

    var dateFmt = typeFmt[$("#dateField").val()];

    if(id == "toPick"){
        WdatePicker({
            onpicked: function () {
                this.blur();
            },
            readOnly: true,
            minDate:$("#fromPick").val(),
            isShowWeek: true,
            qsEnabled: false,
            dateFmt: dateFmt
        });
    }
    if(id = "fromPick"){
        WdatePicker({
            onpicked: function () {
                this.blur();
            },
            readOnly: true,
            maxDate:$("#toPick").val(),
            isShowWeek: true,
            qsEnabled: false,
            dateFmt: dateFmt
        });
    }

}

//获取Json数据
function fetchJsonData(option) {
    if (!option.url) {
        alert("url配置错误");
        return;
    }
    $.ajax({
        dataType: "json",
        url: option.url,
        type: "post",
        data: option.data || '',
        error: function (response) {
            alert("获取数据出错，url:" + option.url);
        },
        beforeSend: option.beforeSend || '',
        success: function(response){
            if(response.stat == 400){
                bootbox.alert("无效的参数"+response.msg);
                return;
            }
            if(response.stat == 500){
                bootbox.alert("服务器错误"+response.msg);
                return;
            }
            option.success(response);
        },
        complete: option.complete || ''
    });
}

//通过name获取参数
function getParamsByNames(names) {
    if (names == null) {
        return null;
    }
    var params = {};
    for (var i = 0; i < names.length; i++) {
        var name = names[i];
        var val = getParamByName(name);
        if (val) {
            if (typeof val == "object") {
                params[name] = JSON.stringify(val);
            } else {
                params[name] = val;
            }
        }
    }
    return params;
}

function getParamByName(name) {
    if (name == null) return null;
    var val = $("[name='" + name + "']").val();
    if (val && val != "") {
        if (typeof val == "object") {
            return JSON.stringify(val);
        } else {
            return val;
        }
    } else {
        return null;
    }
}