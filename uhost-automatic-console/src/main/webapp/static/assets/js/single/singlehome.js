// 存放主要的交互逻辑js代码
// javascript模块化

//Define
Date.prototype.format = function (fmt) {
    var o = {
        "M+": this.getMonth() + 1,                      //月份
        "d+": this.getDate(),                           //日
        "h+": this.getHours(),                          //小时
        "m+": this.getMinutes(),                        //分
        "s+": this.getSeconds(),                        //秒
        "q+": Math.floor((this.getMonth() + 3) / 3),    //季度
        "S": this.getMilliseconds()                     //毫秒
    };
    if (/(y+)/.test(fmt))
        fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt))
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
};

var single = {
    //定义常量
    ENUM: {
        Default: {
            defaultDate: 31,//默认查询近30天的数据
            timestampDay: 1000 * 60 * 60 * 24//默认一天的时间戳长度
        },
        NetAdaptability: {
            ALL: null,//全部
            DROPPED_CALL: 1,//通话掉线
            DROPPED_NET: 2,//掉网异常
            RAT_SWITCH: 3,//RAT切换异常
            PDP_ACTIVE: 4//PDP激活异常
        },
        Table: {
            ROW_NUM: 10,//默认一页显示表格的条数
            Title: {
                Performance: {
                    DROPPED_FRAME_SYS: "掉帧（内置）",
                    DROPPED_FRAME_3TH: "掉帧（三方）",
                    ANR_SYS_PRE: "ANR（前台内置）",
                    ANR_SYS_POST: "ANR（后台内置）",
                    ANR_3TH_PRE: "ANR（前台三方）",
                    ANR_3TH_POST: "ANR（后台三方）",
                    MEMORY_LEAK_SYS: "内存泄露（内置）",
                    MEMORY_LEAK_3TH: "内存泄露（三方）",
                    PARTITION_LACK_OF_SPACE_SYS: "data占用异常（内置）",
                    PARTITION_LACK_OF_SPACE_3TH: "data占用异常（三方）"
                }
            }
        }
    },

    // 封装URL的获取
    URL: {
        totality: function () {
            return "/single/" + single.PARM.imei() + "/totality";
        },
        stability: function () {
            return "/single/" + single.PARM.imei() + "/stability";
        },
        netAdaptability: function () {
            return "/single/" + single.PARM.imei() + "/netAdaptability";
        },
        performance: function () {
            return "/single/" + single.PARM.imei() + "/performance";
        },
        power: function () {
            return "/single/" + single.PARM.imei() + "/power";
        },
        thermal: function () {
            return "/single/" + single.PARM.imei() + "/thermal";
        },
        chart: {
            netAdaptability: function () {
                return "/single/" + single.PARM.imei() + "/netAdaptability/chart/line";
            },
            power: function () {
                return "/single/" + single.PARM.imei() + "/power/chart/line";
            },
            thermal: function () {
                return "/single/" + single.PARM.imei() + "/thermal/chart/line";
            }
        },
        table: {
            stability: {
                jeNeSys: function () {
                    return "/single/" + single.PARM.imei() + "/stability/sys/jeAndNe";
                },
                anrSys: function () {
                    return "/single/" + single.PARM.imei() + "/stability/sys/anr";
                },
                jeNe3TH: function () {
                    return "/single/" + single.PARM.imei() + "/stability/3th/jeAndNe";
                },
                anr3TH: function () {
                    return "/single/" + single.PARM.imei() + "/stability/3th/anr";
                }
            },
            performance: {
                droppedFrameSys: function () {
                    return "/single/" + single.PARM.imei() + "/performance/sys/droppedFrame";
                },
                droppedFrame3TH: function () {
                    return "/single/" + single.PARM.imei() + "/performance/3th/droppedFrame";
                },
                anrSysPre: function () {
                    return "/single/" + single.PARM.imei() + "/performance/sys/pre/anr";
                },
                anrSysPost: function () {
                    return "/single/" + single.PARM.imei() + "/performance/sys/post/anr";
                },
                anr3THPre: function () {
                    return "/single/" + single.PARM.imei() + "/performance/3th/pre/anr";
                },
                anr3THPost: function () {
                    return "/single/" + single.PARM.imei() + "/performance/3th/post/anr";
                },
                memoryLeakSys: function () {
                    return "/single/" + single.PARM.imei() + "/performance/sys/memoryLeak";
                },
                memoryLeak3TH: function () {
                    return "/single/" + single.PARM.imei() + "/performance/3th/memoryLeak";
                },
                partitionLackOfSpaceSys: function () {
                    return "/single/" + single.PARM.imei() + "/performance/sys/partitionLackOfSpace";
                },
                partitionLackOfSpace3TH: function () {
                    return "/single/" + single.PARM.imei() + "/performance/3th/partitionLackOfSpace";
                }
            }
        }
    },

    DOM: {
        //获取Dom
        //获取请求参数的Dom
        imeiCode: function () {
            return $('#imei')
        },
        ocurredFrom: function () {
            return $('#from')
        },
        ocurredTo: function () {
            return $('#to')
        },

        //获取总体情况的Dom
        stability_total: function () {
            return $('#stability_total_crashTimes');
        },
        netAdaptability_total: function () {
            return $('#netAdaptability_total_crashTimes');
        },
        performance_total: function () {
            return $('#performance_total_crashTimes');
        },
        power_total: function () {
            return $('#power_total_crashTimes')
        },
        thermal_total: function () {
            return $('#thermal_total_crashTimes')
        },

        //获取Panel打开关闭状态的Dom
        stability_collapse: function () {
            return $('#collapse1');
        },
        netAdaptability_collapse: function () {
            return $('#collapse2');
        },
        performance_collapse: function () {
            return $('#collapse3');
        },
        power_collapse: function () {
            return $('#collapse4');
        },
        thermal_collapse: function () {
            return $('#collapse5');
        },

        //Stability
        stability: {
            ke_title: function () {
                return $('#stability_ke_title');
            },
            swt_title: function () {
                return $('#stability_swt_title');
            },
            jeNeSys_title: function () {
                return $('#stability_jeNeSys_title');
            },
            anrSys_title: function () {
                return $('#stability_anrSys_title');
            },
            jeNe3Th_title: function () {
                return $('#stability_jeNe3Th_title');
            },
            anr3Th_title: function () {
                return $('#stability_anr3Th_title');
            }
        },

        //netAdaptability
        netAdaptability: {
            ratSwitch_title: function () {
                return $('#netAdaptability_ratSwitch_title');
            },
            droppedCall_title: function () {
                return $('#netAdaptability_droppedCall_title');
            },
            droppedNet_title: function () {
                return $('#netAdaptability_droppedNet_title');
            },
            pdpActive_title: function () {
                return $('#netAdaptability_pdpActive_title');
            },
        },

        //Performance
        performance: {
            memoryLeak_title: function () {
                return $('#performance_memoryLeak_title');
            },
            partitionLackOfSpace_title: function () {
                return $('#performance_partitionLackOfSpace_title');
            },
            droppedFrame_title: function () {
                return $('#performance_droppedFrame_title');
            },
            anr_title: function () {
                return $('#performance_anr_title');
            }
        },

        table: {
            stability: {
                jeNeSys: {
                    tableDom: function () {
                        return $('#stability_jeNeSys_table');
                    },
                    paperDiv: function () {
                        return "#stability_jeNeSys_pager";
                    },
                    fatherDom: function () {
                        return $('#stability_jeNeSys');
                    }
                },
                anrSys: {
                    tableDom: function () {
                        return $('#stability_anrSys_table');
                    },
                    paperDiv: function () {
                        return "#stability_anrSys_pager";
                    },
                    fatherDom: function () {
                        return $('#stability_anrSys');
                    }
                },
                jeNe3TH: {
                    tableDom: function () {
                        return $('#stability_jeNe3TH_table');
                    },
                    paperDiv: function () {
                        return "#stability_jeNe3TH_pager";
                    },
                    fatherDom: function () {
                        return $('#stability_jeNe3TH');
                    }
                },
                anr3TH: {
                    tableDom: function () {
                        return $('#stability_anr3TH_table');
                    },
                    paperDiv: function () {
                        return "#stability_anr3TH_pager";
                    },
                    fatherDom: function () {
                        return $('#stability_anr3TH');
                    }
                }
            },
            performance: {
                droppedFrameSys: {
                    tableDom: function () {
                        return $('#performance_droppedFrameSys_table');
                    },
                    paperDiv: function () {
                        return "#performance_droppedFrameSys_pager";
                    },
                    fatherDom: function () {
                        return $('#performance_droppedFrameSys');
                    }
                },
                droppedFrame3TH: {
                    tableDom: function () {
                        return $('#performance_droppedFrame3TH_table');
                    },
                    paperDiv: function () {
                        return "#performance_droppedFrame3TH_pager";
                    },
                    fatherDom: function () {
                        return $('#performance_droppedFrame3TH');
                    }
                },
                anrSysPre: {
                    tableDom: function () {
                        return $('#performance_anrSysPre_table');
                    },
                    paperDiv: function () {
                        return "#performance_anrSysPre_pager";
                    },
                    fatherDom: function () {
                        return $('#performance_anrSysPre');
                    }
                },
                anrSysPost: {
                    tableDom: function () {
                        return $('#performance_anrSysPost_table');
                    },
                    paperDiv: function () {
                        return "#performance_anrSysPost_pager";
                    },
                    fatherDom: function () {
                        return $('#performance_anrSysPost');
                    }
                },
                anr3THPre: {
                    tableDom: function () {
                        return $('#performance_anr3THPre_table');
                    },
                    paperDiv: function () {
                        return "#performance_anr3THPre_pager";
                    },
                    fatherDom: function () {
                        return $('#performance_anr3THPre');
                    }
                },
                anr3THPost: {
                    tableDom: function () {
                        return $('#performance_anr3THPost_table');
                    },
                    paperDiv: function () {
                        return "#performance_anr3THPost_pager";
                    },
                    fatherDom: function () {
                        return $('#performance_anr3THPost');
                    }
                },
                memoryLeakSys: {
                    tableDom: function () {
                        return $('#performance_memoryLeakSys_table');
                    },
                    paperDiv: function () {
                        return "#performance_memoryLeakSys_pager";
                    },
                    fatherDom: function () {
                        return $('#performance_memoryLeakSys');
                    }
                },
                memoryLeak3TH: {
                    tableDom: function () {
                        return $('#performance_memoryLeak3TH_table');
                    },
                    paperDiv: function () {
                        return "#performance_memoryLeak3TH_pager";
                    },
                    fatherDom: function () {
                        return $('#performance_memoryLeak3TH');
                    }
                },
                partitionLackOfSpaceSys: {
                    tableDom: function () {
                        return $('#performance_partitionLackOfSpaceSys_table');
                    },
                    paperDiv: function () {
                        return "#performance_partitionLackOfSpaceSys_pager";
                    },
                    fatherDom: function () {
                        return $('#performance_partitionLackOfSpaceSys');
                    }
                },
                partitionLackOfSpace3TH: {
                    tableDom: function () {
                        return $('#performance_partitionLackOfSpace3TH_table');
                    },
                    paperDiv: function () {
                        return "#performance_partitionLackOfSpace3TH_pager";
                    },
                    fatherDom: function () {
                        return $('#performance_partitionLackOfSpace3TH');
                    }
                }
            }
        }

    },

    ACTION: {
        totalitySearch: $("#submitButton").click(function () {
            single.Request.requestTotality();
            if (single.DOM.stability_collapse().attr("aria-expanded") === "true") {
                single.Request.requestStability();
                single.drawTable.draw.stability();
            }
            if (single.DOM.netAdaptability_collapse().attr("aria-expanded") === "true") {
                single.Request.requestNetAdaptability();
                single.Request.chart.netAdaptability();
            }
            if (single.DOM.performance_collapse().attr("aria-expanded") === "true") {
                single.Request.requestPerformance();
                single.drawTable.draw.performance();
            }
            if (single.DOM.power_collapse().attr("aria-expanded") === "true") {
                single.Request.requestPower();
                single.Request.chart.power();
            }
            if (single.DOM.thermal_collapse().attr("aria-expanded") === "true") {
                single.Request.requestThermal();
                single.Request.chart.thermal();
            }
        }),
        stabilityDetailSearch: $('#collapse1').on('shown.bs.collapse', function () {
            single.Request.requestStability();
            single.drawTable.draw.stability();
        }),
        netAdaptabilityDetailSearch: $('#collapse2').on('shown.bs.collapse', function () {
            single.Request.requestNetAdaptability();
            single.Request.chart.netAdaptability();
        }),
        performanceDetailSearch: $('#collapse3').on('shown.bs.collapse', function () {
            single.Request.requestPerformance();
            single.drawTable.draw.performance();
        }),
        powerDetailSearch: $('#collapse4').on('shown.bs.collapse', function () {
            if (single.DOM.power_total().text() === "待查询") {
                single.Request.requestPower();
            }
            single.Request.chart.power();
        }),
        thermalDetailSearch: $('#collapse5').on('shown.bs.collapse', function () {
            if (single.DOM.thermal_total().text() === "待查询") {
                single.Request.requestThermal();
            }
            single.Request.chart.thermal();
        })
    },

    PARM: {
        //IMEI
        imei: function () {
            return single.DOM.imeiCode().val() || '865407010000009';
        },
        ocurrendFrom: function () {
            return single.DOM.ocurredFrom().val();
        },
        ocurrendTo: function () {
            return single.DOM.ocurredTo().val();
        },
        //参数List获取
        requestParm: function () {
            var inputStartDate = single.PARM.ocurrendFrom();
            var inputEndDate = single.PARM.ocurrendTo();

            var currentDate = new Date();
            var defaultStartDate = new Date(currentDate.getTime() - single.ENUM.Default.defaultDate * single.ENUM.Default.timestampDay).format("yyyy-MM-dd");
            var defaultEndDate = currentDate.format("yyyy-MM-dd");

            if (!inputStartDate && !inputEndDate) {
                return {
                    from: defaultStartDate,
                    to: defaultEndDate
                }
            } else {
                return {
                    from: inputStartDate,
                    to: inputEndDate
                }
            }
        }

    },

    Request: {
        //URL请求发送
        requestTotality: function () {
            $.post(single.URL.totality(), single.PARM.requestParm(), function (result) {
                if (result && result["stat"] == 200) {
                    single.dataFill.fillTotal(result["data"])
                }
            }).error(function () {
                alert("IMEI为空或登录已失效，请确认后重试！")
            });
        },
        requestStability: function () {
            $.post(single.URL.stability(), single.PARM.requestParm(), function (result) {
                if (result["stat"] == 200) {
                    single.dataFill.stability.fillStabilityTitle(result["data"]);
                }
            });
        },
        requestNetAdaptability: function () {
            $.post(single.URL.netAdaptability(), single.PARM.requestParm(), function (result) {
                if (result["stat"] == 200) {
                    if (result["data"]) {
                        single.dataFill.netAdaptability.fillNetAdaptabilityTitle(result["data"]);
                    }
                }
            });
        },
        requestPerformance: function () {
            $.post(single.URL.performance(), single.PARM.requestParm(), function (result) {
                if (result["stat"] == 200) {
                    single.dataFill.performance.fillPerformanceTitle(result["data"]);
                }
            });
        },
        requestPower: function () {
            $.post(single.URL.power(), single.PARM.requestParm(), function (result) {
                if (result["stat"] == 200) {
                    if (result["data"] && result["data"][0]) {
                        single.dataFill.fillTotalLimit(result["data"][0], 'crashTimes', single.DOM.power_total());
                    }
                }
            });
        },
        requestThermal: function () {
            $.post(single.URL.thermal(), single.PARM.requestParm(), function (result) {
                if (result["stat"] == 200) {
                    if (result["data"] && result["data"][0]) {
                        single.dataFill.fillTotalLimit(result["data"][0], 'crashTimes', single.DOM.thermal_total());
                    }
                }
            });
        },
        //图表相关请求
        chart: {
            netAdaptability: function () {
                $.post(single.URL.chart.netAdaptability(), single.PARM.requestParm(), function (result) {
                    if (result["stat"] == 200) {
                        if (result["data"]) {
                            single.drawChart.drawLineChart(single.drawChart.OPTION.netAdaptabilityLineChart, result["data"]);
                        }
                    }
                });
            },
            power: function () {
                $.post(single.URL.chart.power(), single.PARM.requestParm(), function (result) {
                    if (result["stat"] == 200) {
                        if (result["data"]) {
                            single.drawChart.drawLineChart(single.drawChart.OPTION.powerLineChart, result["data"]);
                        }
                    }
                });
            },
            thermal: function () {
                $.post(single.URL.chart.thermal(), single.PARM.requestParm(), function (result) {
                    if (result["stat"] == 200) {
                        if (result["data"]) {
                            single.drawChart.drawLineChart(single.drawChart.OPTION.thermalLineChart, result["data"]);
                        }
                    }
                });
            }
        }
    },

    // 日历选择
    datePick: {
        //初始化日期选择
        init: function (fromPickId, toPickId, dateFieldId) {
            var dateFieldItem = $("#" + dateFieldId);
            var fromPickItem = $("#" + fromPickId);
            var toPickItem = $("#" + toPickId);
            dateFieldItem.on("change", function () {
                fromPickItem.val('');
                toPickItem.val('');
            });
            fromPickItem.on("focus", single.datePick.rangePick());
            toPickItem.on("focus", single.datePick.rangePick());
        },
        rangePick: function (dom) {
            var typeFmt = {
                "日": 'yyyy-MM-dd',
                "周": 'yyyy-MM',
                "月": 'yyyy-MM',
                "年": 'yyyy'
            };
            var id = $(dom).attr("id");

            // var dateFmt = typeFmt[$("#dateField").val()];
            var dateFmt = typeFmt["日"];

            if (id == "to") {
                WdatePicker({
                    onpicked: function () {
                        this.blur();
                    },
                    readOnly: true,
                    minDate: $("#from").val(),
                    isShowWeek: true,
                    qsEnabled: false,
                    dateFmt: dateFmt
                });
            }
            if (id == "from") {
                WdatePicker({
                    onpicked: function () {
                        this.blur();
                    },
                    readOnly: true,
                    maxDate: $("#to").val(),
                    isShowWeek: true,
                    qsEnabled: false,
                    dateFmt: dateFmt
                });
            }
        }
    },

    dataFill: {
        //数据填充
        fillTotal: function (data) {
            //填充稳定性
            single.dataFill.fillTotalLimit(data, 'stability', single.DOM.stability_total());
            single.dataFill.fillTotalLimit(data, 'netAdaptability', single.DOM.netAdaptability_total());
            single.dataFill.fillTotalLimit(data, 'performance', single.DOM.performance_total());
            single.dataFill.fillTotalLimit(data, 'power', single.DOM.power_total());
            single.dataFill.fillTotalLimit(data, 'thermal', single.DOM.thermal_total());


        },
        //填充指定dom
        fillTotalLimit: function (data, location, dom) {
            if (data[location] && data[location] > 0) {
                dom.hide().removeClass('label-success').addClass('label-danger').text("检测到" + data[location] + "次异常").show(200);
            } else {
                dom.hide().removeClass('label-danger').addClass('label-success').text("未检测到异常").show(200);
            }
        },

        fillTitleLimit: function (data, location, dom) {
            if (data[location] && data[location] > 0) {
                dom.hide().css("color", "red").text("检测到" + data[location] + "次异常").show(100);
            } else {
                dom.hide().css("color", "green").text("未检测到异常").show(100);
            }
        },
        //Stability
        stability: {
            //填充稳定性分类异常总数
            fillStabilityTitle: function (data) {
                single.dataFill.fillTitleLimit(data, "ke", single.DOM.stability.ke_title());
                single.dataFill.fillTitleLimit(data, "swt", single.DOM.stability.swt_title());
                single.dataFill.fillTitleLimit(data, "jeNeSys", single.DOM.stability.jeNeSys_title());
                single.dataFill.fillTitleLimit(data, "anrSys", single.DOM.stability.anrSys_title());
                single.dataFill.fillTitleLimit(data, "jeNe3Th", single.DOM.stability.jeNe3Th_title());
                single.dataFill.fillTitleLimit(data, "anr3Th", single.DOM.stability.anr3Th_title());
            }
        },

        //netAdaptability
        netAdaptability: {
            //填充网络适应性分类异常数
            fillNetAdaptabilityTitle: function (data) {
                var entirety = new Object();
                for (var index in data) {
                    switch (data[index]["crashType"]) {
                        case single.ENUM.NetAdaptability.RAT_SWITCH:
                            entirety.ratSwitch = data[index]["crashTimes"]
                            break;
                        case single.ENUM.NetAdaptability.DROPPED_CALL:
                            entirety.droppedCall = data[index]["crashTimes"]
                            break;
                        case single.ENUM.NetAdaptability.DROPPED_NET:
                            entirety.droppedNet = data[index]["crashTimes"]
                            break;
                        case single.ENUM.NetAdaptability.PDP_ACTIVE:
                            entirety.pdpActive = data[index]["crashTimes"]
                            break;
                        default:
                            break;
                    }
                }
                single.dataFill.fillTitleLimit(entirety, "ratSwitch", single.DOM.netAdaptability.ratSwitch_title());
                single.dataFill.fillTitleLimit(entirety, "droppedCall", single.DOM.netAdaptability.droppedCall_title());
                single.dataFill.fillTitleLimit(entirety, "droppedNet", single.DOM.netAdaptability.droppedNet_title());
                single.dataFill.fillTitleLimit(entirety, "pdpActive", single.DOM.netAdaptability.pdpActive_title());
            }
        },
        //Performance
        performance: {
            fillPerformanceTitle: function (data) {
                single.dataFill.fillTitleLimit(data, "memoryLeak", single.DOM.performance.memoryLeak_title());
                single.dataFill.fillTitleLimit(data, "partitionLackOfSpace", single.DOM.performance.partitionLackOfSpace_title());
                single.dataFill.fillTitleLimit(data, "droppedFrame", single.DOM.performance.droppedFrame_title());
                single.dataFill.fillTitleLimit(data, "anr", single.DOM.performance.anr_title());
            }
        }
    },

    drawChart: {
        OPTION: {
            netAdaptabilityLineChart: {
                type: "line",
                div: "netAdaptability_chart",
                width: 'auto',
                height: 250
            },
            powerLineChart: {
                type: "line",
                div: "power_chart",
                width: 'auto',
                height: 250
            },
            thermalLineChart: {
                type: "line",
                div: "thermal_chart",
                width: 'auto',
                height: 250
            }
        },
        drawLineChart: function (lineOption, data) {

            var lineChart = echarts.init(document.getElementById(lineOption.div), null, {
                width: lineOption.width || 'auto',
                height: lineOption.height || 300
            });

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
                    // formatter: "{a} <br />{b}:{c}次"
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
                    type: 'value',
                    minInterval: 1,
                    min: 0
                },
                xAxis: {
                    data: data["xAxis"]["data"],
                    type: 'category',
                    boundaryGap: false
                },
                title: {
                    text: "",
                    subText: ""
                },
                legend: {
                    data: data["legend"]["data"]
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
                            item.data = data["series"][i]["data"];
                            item.name = data["series"][i]["name"];
                            this.series.push(item);
                        }
                    }
                }
            };
            option.randerSeries();//设置多条线

            lineChart.setOption(option, true);//绘制

            //自适应大小监听
            $(window).resize(function () {
                lineChart.resize();
            });
            // window.onresize = lineChart.resize;
        }
    },

    drawTable: {
        draw: {
            stability: function () {
                single.drawTable.drawTable(
                    single.drawTable.OPTION.stability(
                        single.URL.table.stability.jeNeSys(),
                        single.PARM.requestParm(),
                        null,
                        single.ENUM.Table.ROW_NUM,
                        single.DOM.table.stability.jeNeSys.paperDiv(),
                        single.DOM.table.stability.jeNeSys.fatherDom()
                    ),
                    single.DOM.table.stability.jeNeSys.tableDom(),
                    single.DOM.table.stability.jeNeSys.fatherDom()
                );

                single.drawTable.drawTable(
                    single.drawTable.OPTION.stability(
                        single.URL.table.stability.anrSys(),
                        single.PARM.requestParm(),
                        null,
                        single.ENUM.Table.ROW_NUM,
                        single.DOM.table.stability.anrSys.paperDiv(),
                        single.DOM.table.stability.anrSys.fatherDom()
                    ),
                    single.DOM.table.stability.anrSys.tableDom(),
                    single.DOM.table.stability.anrSys.fatherDom()
                );

                single.drawTable.drawTable(
                    single.drawTable.OPTION.stability(
                        single.URL.table.stability.jeNe3TH(),
                        single.PARM.requestParm(),
                        null,
                        single.ENUM.Table.ROW_NUM,
                        single.DOM.table.stability.jeNe3TH.paperDiv(),
                        single.DOM.table.stability.jeNe3TH.fatherDom()
                    ),
                    single.DOM.table.stability.jeNe3TH.tableDom(),
                    single.DOM.table.stability.jeNe3TH.fatherDom()
                );

                single.drawTable.drawTable(
                    single.drawTable.OPTION.stability(
                        single.URL.table.stability.anr3TH(),
                        single.PARM.requestParm(),
                        null,
                        single.ENUM.Table.ROW_NUM,
                        single.DOM.table.stability.anr3TH.paperDiv(),
                        single.DOM.table.stability.anr3TH.fatherDom()
                    ),
                    single.DOM.table.stability.anr3TH.tableDom(),
                    single.DOM.table.stability.anr3TH.fatherDom()
                );
            },
            performance: function () {

                //掉帧
                single.drawTable.drawTable(
                    single.drawTable.OPTION.performance.droppedFrame(
                        single.URL.table.performance.droppedFrameSys(),
                        single.PARM.requestParm(),
                        single.ENUM.Table.Title.Performance.DROPPED_FRAME_SYS,
                        single.ENUM.Table.ROW_NUM,
                        single.DOM.table.performance.droppedFrameSys.paperDiv(),
                        single.DOM.table.performance.droppedFrameSys.fatherDom()
                    ),
                    single.DOM.table.performance.droppedFrameSys.tableDom(),
                    single.DOM.table.performance.droppedFrameSys.fatherDom()
                );

                single.drawTable.drawTable(
                    single.drawTable.OPTION.performance.droppedFrame(
                        single.URL.table.performance.droppedFrame3TH(),
                        single.PARM.requestParm(),
                        single.ENUM.Table.Title.Performance.DROPPED_FRAME_3TH,
                        single.ENUM.Table.ROW_NUM,
                        single.DOM.table.performance.droppedFrame3TH.paperDiv(),
                        single.DOM.table.performance.droppedFrame3TH.fatherDom()
                    ),
                    single.DOM.table.performance.droppedFrame3TH.tableDom(),
                    single.DOM.table.performance.droppedFrame3TH.fatherDom()
                );

                //anr
                single.drawTable.drawTable(
                    single.drawTable.OPTION.performance.anr(
                        single.URL.table.performance.anrSysPre(),
                        single.PARM.requestParm(),
                        single.ENUM.Table.Title.Performance.ANR_SYS_PRE,
                        single.ENUM.Table.ROW_NUM,
                        single.DOM.table.performance.anrSysPre.paperDiv(),
                        single.DOM.table.performance.anrSysPre.fatherDom()
                    ),
                    single.DOM.table.performance.anrSysPre.tableDom(),
                    single.DOM.table.performance.anrSysPre.fatherDom()
                );

                single.drawTable.drawTable(
                    single.drawTable.OPTION.performance.anr(
                        single.URL.table.performance.anrSysPost(),
                        single.PARM.requestParm(),
                        single.ENUM.Table.Title.Performance.ANR_SYS_POST,
                        single.ENUM.Table.ROW_NUM,
                        single.DOM.table.performance.anrSysPost.paperDiv(),
                        single.DOM.table.performance.anrSysPost.fatherDom()
                    ),
                    single.DOM.table.performance.anrSysPost.tableDom(),
                    single.DOM.table.performance.anrSysPost.fatherDom()
                );

                single.drawTable.drawTable(
                    single.drawTable.OPTION.performance.anr(
                        single.URL.table.performance.anr3THPre(),
                        single.PARM.requestParm(),
                        single.ENUM.Table.Title.Performance.ANR_3TH_PRE,
                        single.ENUM.Table.ROW_NUM,
                        single.DOM.table.performance.anr3THPre.paperDiv(),
                        single.DOM.table.performance.anr3THPre.fatherDom()
                    ),
                    single.DOM.table.performance.anr3THPre.tableDom(),
                    single.DOM.table.performance.anr3THPre.fatherDom()
                );

                single.drawTable.drawTable(
                    single.drawTable.OPTION.performance.anr(
                        single.URL.table.performance.anr3THPost(),
                        single.PARM.requestParm(),
                        single.ENUM.Table.Title.Performance.ANR_3TH_POST,
                        single.ENUM.Table.ROW_NUM,
                        single.DOM.table.performance.anr3THPost.paperDiv(),
                        single.DOM.table.performance.anr3THPost.fatherDom()
                    ),
                    single.DOM.table.performance.anr3THPost.tableDom(),
                    single.DOM.table.performance.anr3THPost.fatherDom()
                );

                //内存泄露
                single.drawTable.drawTable(
                    single.drawTable.OPTION.performance.memoryLeak(
                        single.URL.table.performance.memoryLeakSys(),
                        single.PARM.requestParm(),
                        single.ENUM.Table.Title.Performance.MEMORY_LEAK_SYS,
                        single.ENUM.Table.ROW_NUM,
                        single.DOM.table.performance.memoryLeakSys.paperDiv(),
                        single.DOM.table.performance.memoryLeakSys.fatherDom()
                    ),
                    single.DOM.table.performance.memoryLeakSys.tableDom(),
                    single.DOM.table.performance.memoryLeakSys.fatherDom()
                );

                single.drawTable.drawTable(
                    single.drawTable.OPTION.performance.memoryLeak(
                        single.URL.table.performance.memoryLeak3TH(),
                        single.PARM.requestParm(),
                        single.ENUM.Table.Title.Performance.MEMORY_LEAK_3TH,
                        single.ENUM.Table.ROW_NUM,
                        single.DOM.table.performance.memoryLeak3TH.paperDiv(),
                        single.DOM.table.performance.memoryLeak3TH.fatherDom()
                    ),
                    single.DOM.table.performance.memoryLeak3TH.tableDom(),
                    single.DOM.table.performance.memoryLeak3TH.fatherDom()
                );

                //data空间占用
                single.drawTable.drawTable(
                    single.drawTable.OPTION.performance.partitionLackOfSpace(
                        single.URL.table.performance.partitionLackOfSpaceSys(),
                        single.PARM.requestParm(),
                        single.ENUM.Table.Title.Performance.PARTITION_LACK_OF_SPACE_SYS,
                        single.ENUM.Table.ROW_NUM,
                        single.DOM.table.performance.partitionLackOfSpaceSys.paperDiv(),
                        single.DOM.table.performance.partitionLackOfSpaceSys.fatherDom()
                    ),
                    single.DOM.table.performance.partitionLackOfSpaceSys.tableDom(),
                    single.DOM.table.performance.partitionLackOfSpaceSys.fatherDom()
                );

                single.drawTable.drawTable(
                    single.drawTable.OPTION.performance.partitionLackOfSpace(
                        single.URL.table.performance.partitionLackOfSpace3TH(),
                        single.PARM.requestParm(),
                        single.ENUM.Table.Title.Performance.PARTITION_LACK_OF_SPACE_3TH,
                        single.ENUM.Table.ROW_NUM,
                        single.DOM.table.performance.partitionLackOfSpace3TH.paperDiv(),
                        single.DOM.table.performance.partitionLackOfSpace3TH.fatherDom()
                    ),
                    single.DOM.table.performance.partitionLackOfSpace3TH.tableDom(),
                    single.DOM.table.performance.partitionLackOfSpace3TH.fatherDom()
                );
            }
        },
        OPTION: {
            stability: function (url, postData, caption, rowNum, paperDiv, fatherDom) {
                return single.drawTable.createTableOption(url, postData,
                    ['包名', '异常类型', '异常次数'],
                    [
                        {name: 'packageName', index: 'packageName', align: "center", sortable: false, width: 50},
                        {name: 'crashType', index: 'crashType', align: "center", sortable: false, width: 25},
                        {name: 'crashTimes', index: 'crashTimes', align: "center", sortable: false, width: 25}
                    ],
                    caption, rowNum, paperDiv, fatherDom);
            },
            performance: {
                droppedFrame: function (url, postData, caption, rowNum, paperDiv, fatherDom) {
                    return single.drawTable.createTableOption(url, postData,
                        ['包名', '异常类型', '异常次数'],
                        [
                            {name: 'module', index: 'module', align: "center", sortable: false, width: 50},
                            {name: 'crashTypeVP', index: 'crashTypeVP', align: "center", sortable: false, width: 25},
                            {name: 'crashTimes', index: 'crashTimes', align: "center", sortable: false, width: 25}
                        ],
                        caption, rowNum, paperDiv, fatherDom);
                },
                anr: function (url, postData, caption, rowNum, paperDiv, fatherDom) {
                    //暂时跟掉帧一样的参数，因此直接调用掉帧的参数
                    return single.drawTable.OPTION.performance.droppedFrame(url, postData, caption, rowNum, paperDiv, fatherDom);
                },
                memoryLeak: function (url, postData, caption, rowNum, paperDiv, fatherDom) {
                    //暂时跟掉帧一样的参数，因此直接调用掉帧的参数
                    return single.drawTable.OPTION.performance.droppedFrame(url, postData, caption, rowNum, paperDiv, fatherDom);
                },
                partitionLackOfSpace: function (url, postData, caption, rowNum, paperDiv, fatherDom) {
                    //暂时跟掉帧一样的参数，因此直接调用掉帧的参数
                    return single.drawTable.OPTION.performance.droppedFrame(url, postData, caption, rowNum, paperDiv, fatherDom);
                }
            }
        },
        drawTable: function (option, tableDom, fatherDom) {
            /**
             * 绘制表格
             */
            tableDom.jqGrid(option);
            $(window).resize(function () {
                tableDom.setGridWidth(fatherDom.width());
            });

            /**
             * 请求参数变化时，重绘表格
             */
            tableDom.jqGrid('setGridParam', {
                url: option.url,
                postData: option.postData
            }).trigger("reloadGrid");
        },
        createTableOption: function (url, postData, colNames, colModel, caption, rowNum, paperDiv, fatherDom) {
            return {
                url: url,
                datatype: "json",
                mtype: "post",
                postData: postData,
                colNames: colNames,
                colModel: colModel,
                caption: caption,
                rowNum: rowNum,
                sortorder: 'desc',
                sortname: 'uniqid',
                viewrecords: true,
                pager: paperDiv,
                altRows: true,
                emptyrecords: "没有找到数据",
                loadComplete: function () {
                    /**
                     * 数据加载完成后的事件
                     * 更改表格底端上下页的图标，数据加载完成后重新设置大小
                     * @type {anrSysTable}
                     */
                    var table = this;
                    setTimeout(function () {
                        single.drawTable.updatePagerIcons(table);
                    }, 0);
                    $(this).jqGrid('setGridWidth', fatherDom.width());    //加载完数据后重新设置下宽度
                },
                autowidth: true,
                height: 'auto',
                resize: true,
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
            }
        },
        updatePagerIcons: function (table) {
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
    }
};