var createTable = function(obj){
	for(var i = 0; i < obj.colModel.length; i++){
		obj.colModel[i].index = obj.colModel[i].name || "";
		obj.colModel[i].align = obj.colModel[i].align || "center";
		obj.colModel[i].width = obj.colModel[i].width || 90;
		obj.colModel[i].sortable = obj.colModel[i].sortable ? true : false;
	}
	
	if(obj.jsonReader){
		obj.jsonReader.root = obj.jsonReader.root || "content";
		obj.jsonReader.total = obj.jsonReader.total || "totalPages";
		obj.jsonReader.page = obj.jsonReader.page || "number";
		obj.jsonReader.records = obj.jsonReader.records || "totalElements";
		obj.jsonReader.repeatitems = obj.jsonReader.repeatitems ? true : false;
	}else{
		obj.jsonReader = {
		   root: "content",
		   total: "totalPages",
		   page: "number",
		   records: "totalElements",
		   repeatitems: false
       	}
	}
	
	$(obj.gridSelector).jqGrid({
		url: obj.dataUrl,
		datatype: "json",
		mtype : "post",
		postData:{
			themeType : $("#themeType").val()
		},
		colNames: obj.colNames,
		colModel: obj.colModel,
		rowNum:25,
		viewrecords : true,
		pager : obj.pagerSelector,
		altRows: true,
		emptyrecords: "没有找到数据",
		loadComplete : function() {
			var table = this;
			setTimeout(function(){
				updatePagerIcons(table);
				$(window).triggerHandler('resize.jqGrid');//数据加载完成后调整表格宽度
			}, 0);
		},
		height: '100%',
		shrinkToFit: true,
		prmNames:{page: "page", rows:"size", sort: "sinx", order: "order", search: "search", nd:"nd", npage:null},
		jsonReader: obj.jsonReader
	});
	$(obj.gridSelector).closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" });
	//调整表格宽度适应容器
	$(window).on('resize.jqGrid', function () {
		$(obj.gridSelector).jqGrid( 'setGridWidth', $(obj.tableContainer).width() );
    });
}


//replace icons with FontAwesome icons like above
function updatePagerIcons(table) {
	var replacement = {
		'ui-icon-seek-first' : 'ace-icon fa fa-angle-double-left bigger-140',
		'ui-icon-seek-prev' : 'ace-icon fa fa-angle-left bigger-140',
		'ui-icon-seek-next' : 'ace-icon fa fa-angle-right bigger-140',
		'ui-icon-seek-end' : 'ace-icon fa fa-angle-double-right bigger-140'
	};
	$('.ui-pg-table:not(.navtable) > tbody > tr > .ui-pg-button > .ui-icon').each(function(){
		var icon = $(this);
		var $class = $.trim(icon.attr('class').replace('ui-icon', ''));
		
		if($class in replacement) icon.attr('class', 'ui-icon '+replacement[$class]);
	});
}
