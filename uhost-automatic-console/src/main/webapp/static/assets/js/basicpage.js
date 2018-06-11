
//初始化控件事件
function initEventCallBack(option) {
	selectOnchange(option);
	comparisOnClick(option);
	distOnClick(option);
}

//选择控件改变事件回调
function selectOnchange(option) {
	var selectId = option.select.id;
	if (!selectId) {
		return;
	}

	$("#" + selectId).on("change", function(e) {
		var selected = JSON.stringify($(this).val());
		if (!selected || selected == "null") {
			return;
		}
		var op = option.comparison;
		op.data = option.formData;
		op.data[option.comparison.selectedProperty] = selected;
		op.success = function(response) {
			comparisonSuccess(response, option);
		};
		fetchJsonData(op);
	});
};

//对比数据图数据加载成功的回调
function comparisonSuccess(response, option) {
	var chartData = response.data;
	var chart = option.comparison.getChart();
	if (!chart) {
		return;
	}

	drawLineChart(chart, chartData, option.comparison.chartOption);

	if(!chartData||!chartData.xaxis){
		return;
	}
	var op = option.dist;
	op.data = option.formData;
	var xaxisData = chartData.xaxis.data;
	if (xaxisData != null && xaxisData.length > 0) {
		op.data[option.dist.selectedProperty] = xaxisData[0];
		if (!option.dist.getChart()) {
			return;
		}
		op.success = function(response) {
			drawDist(response, option, xaxisData[0]);
		}
		fetchJsonData(op);
	}else{
		if (option.dist.getChart()) {
			option.dist.getChart().clear();
		}
	}
	

};

//对比数据图数据点击的回调
function comparisOnClick(option) {
	var chart = option.comparison.getChart();
	if (!chart) {
		return;
	}
	var op = option.dist;
	op.data = option.formData;
	chart.on('click',function(params) {
				op.data[option.dist.selectedProperty] = params.name;
				if (option.dist.getChart() && option.dist.getChart().getOption().title[0].subtext == params.name) {
					return;
				}
				op.success = function(response) {
					drawDist(response, option, params.name);
				};
				fetchJsonData(op);
			});
}


//分布数据图数据点击的回调
function distOnClick(option) {
	var chart = option.dist.getChart();
	var onClick = option.dist.onClick;
	if (!chart || !onClick) {
		return;
	}
	
	chart.on('click',function(params) {
				onClick(params);
			});
}




//绘制分布数据图
function drawDist(response, option, selected) {
	var op = option.dist.chartOption;
	op.title.subtext = selected;
	var chartData = response.data;
	var chart = option.dist.getChart();
	if (!chart) {
		return;
	}
	drawPieChart(chart, chartData, op);

}

function fetchJsonData(option) {
	if (!option.url) {
		alert("获取数据方法配置错误");
		return;
	}
	$.ajax({
		dataType : "json",
		url : option.url,
		type : "post",
		data : option.data || '',
		error : function(response) {
			alert("获取数据出错，url:" + option.url);
		},
		beforeSend : option.beforeSend || '',
		success : option.success || '',
		complete : option.complete || ''
	});
};

//绘制线型图
function drawLineChart(chart, data) {
	var option = {
		color : [ '#008cd6', '#c23531', '#2f4554', '#61a0a8', '#d48265',
				'#91c7ae', '#749f83', '#ca8622', '#bda29a', '#6e7074',
				'#546570', '#c4ccd3' ],
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
		tooltip : {
			trigger : 'axis',
			formatter : function(params) {
				if (!params) {
					return '';
				}
				if(params.name == 'imei变更'){
					return params.data.name+":<br/>"+params.data.value;
				}

				var spanStart = "<span style='display:inline-block;margin-right:5px;border-radius:10px;width:9px;height:9px;background-color:";
				var spanEnd = "'></span>";
				var result = '';

				for ( var i in params) {
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
		grid : {
			left : '15',
			right : '30',
			bottom : '20',
			containLabel : true
		},
		yAxis : {
			type : 'value'
		},
        series:[],
		xAxis:{

		},
		legend:{

		}
	};
    if(data&&data.xaxis != null){
        var series = [];

        for ( var i in data.series) {
            var item = data.series[i];
            item.smooth = true;
            item.type = 'line';
            item.symbolSize = 8;
            series.push(item);
        }

        option.xAxis = {
            type : 'category',
            data : data.xaxis.data,
            boundaryGap : false
        };
        option.legend = {
            data : data.legend
        };
        option.series = series;
    }
	chart.setOption(option, true);
}

//绘制图表组
function drawGroupChart(option) {

	var op = option.select;
	op.data = option.formData;

	//获取选择控件数据成功回调
	op.success = function(data) {
		$("#" + option.select.id + " option").remove();
		var $select = $("#" + option.select.id).attr("multiple","multiple").select2({
			width : '80%',
			height : '200%',
			data : data
		});
		if (data != null && data.length > 0) {
			$select.val(data[0]).trigger("change");
		}
	};

	//获取选择控件数据
	fetchJsonData(op);
}

function initChart(chartId) {
	var chart = echarts.init(document.getElementById(chartId));
	return chart;
}

function drawPieChart(chart, data, op) {
	var chartView = data;

	var option = {
		title : {
			text : op.title.text || '',
			subtext : op.title.subtext || '',
			x : 'center'
		},
		toolbox: {
	        show: true,
	        feature: {
	            dataView: {readOnly: true},
	            saveAsImage: {}
	        }
	    },
		tooltip : {
			trigger : 'item',
			formatter : "{a} <br/>{b} : {c} ({d}%)"
		},
		series : [ {
			name : op.series.name || '',
			data : chartView.data, //data
			type : 'pie',
			radius : ['0%','50%'],
			label : {
				normal : {
					show : true,
					formatter : "{b}\n ——{c}",
				}
			},
			labelLine : {
				normal : {
					length : 5,
					length2 : 10,
					smooth : true
				}
			},
			itemStyle : {
				emphasis : {
					shadowBlur : 10,
					shadowOffsetX : 0,
					shadowColor : 'rgba(0, 0, 0, 0.5)'
				}
			}
		} ]
	};

	chart.setOption(option, true);

}

//日期选择控件
function dateRangePick(obj) {
	var typeFmt = {
		"日" : 'yyyy-MM-dd',
		"周" : 'yyyy-MM',
		"月" : 'yyyy-MM',
		"年" : 'yyyy'
	};

	var id = $(obj).attr("id");

	var dateFmt = typeFmt[$("#dateField").val()];

	if(id == "from"){
		WdatePicker({
			onpicked : function() {
				this.blur();
			},
			readOnly : true,
			isShowWeek : true,
			qsEnabled : false,
			dateFmt : dateFmt,
			maxDate:$("#to").val()
		});
	}
	if(id == "to"){
		WdatePicker({
			onpicked : function() {
				this.blur();
			},
			readOnly : true,
			isShowWeek : true,
			qsEnabled : false,
			dateFmt : dateFmt,
			minDate:$("#from").val()
		});
	}
}

//日期选择控件
function datePick() {
	var typeFmt = {
		"日" : 'yyyy-MM-dd',
		"周" : 'yyyy-MM',
		"月" : 'yyyy-MM',
		"年" : 'yyyy'
	}
	var dateFmt = typeFmt[$("#dateField").val()];
	WdatePicker({
		onpicked : function() {
			this.blur();
		},
		readOnly : true,
		isShowWeek : true,
		qsEnabled : false,
		dateFmt : dateFmt
	});
}


//日期类型改变的时候清空日期选择控件
function dateFieldOnChange() {
	$("#dateField").on('change', function(e) {
		$("#from").val('');
		$("#to").val('');
	})
}