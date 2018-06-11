<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
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
	<script src="${contextPath}/static/assets/js/jquery.js"></script>
	<script src="${contextPath}/static/assets/js/bootstrap.js"></script>
	<script src="${contextPath}/static/assets/plugin/layer-v3.1.1/layer.js"></script>

	<%@include file="../common/common.jsp" %>
	<style>
		.tab tr{background: #f1f1f1;
		}
		.tab tr:nth-child(even){background: #ccc;}

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
			white-space: normal !important;
			height: auto;
			vertical-align: text-top;
			padding-top: 2px;
		}
	</style>
</head>

<body class="main-container">
<%--<div class="main-content-inner">--%>
<form action="#" method="post" id="editForm">
	<input name="id" type="hidden" value="${process.id}" id="processId" />
	<div class="out">
		<table width="100%" border="0" class="tab"  cellspacing="1" class="sorts_table table_color" id="table1">
			<tr class="sorts_title" style="margin-top: 20px">
				<td colspan="6" style="margin: 10px">编辑流程</td>
			</tr>
			<tr class="tr_padding " style="margin-top: 10px">
				<td>流程名称：</td>
				<td colspan="5"><input style="margin-top:10px;margin-bottom:10px  " name="processName" type="text" maxlength="100"
									   class="txt gonggao" id="processName" value="${process.processName}" /><font color="red">*必填(最大长度100)</font></td>
			</tr>
			<tr class="tr_padding " style="margin-top: 10px" >
				<td>测试机型：</td>
				<td colspan="5" text-align=center" vertical-align="middle">
					<input type="hidden" <%--name="modelId"--%> id="modelIdInput"/>
					<select id="modelId" name="modelId" onchange="modelChange()" <c:if test="${process.totalNum > 0}" >disabled</c:if>  style="margin: 15px" >
						<option value="" >请选择...</option>
						<c:forEach items="${modelInfos}" var="model" >
							<option value="${model.id}" <c:if test="${process.modelId == model.id}">selected="selected"</c:if> >${model.modelVersion}</option>
						</c:forEach>
					</select>
					<font color="red">*必填</font>
				</td>
			</tr>
			<tr class="tr_padding " style="margin-top: 10px" >
				<td colspan="1" height="30px">任务-系统升级：</td>
				<td colspan="1">
					<input type="checkbox" value="11"  name="taskTypes"  id="taskUpdate" onchange="updateChange()"
						   <c:choose><%--<c:when test="${process.id == null}">checked="checked" </c:when>--%>
						   <c:when test="${fn:contains(process.taskTypes, '11')}">checked="checked" </c:when>
					</c:choose>/>
				</td>
				<td  colspan="1" height="30px">
					<div id="updateWay">
						升级方式：
						<select  name="pushTasks[11].testWay" id="wayId"  style="margin: 15px">
							<option value="" >请选择...</option>
							<option value="0" >T卡升级</option>
							<option value="1" >fastboot烧写分区--YES</option>
							<option value="2" >fastboot烧写分区--NO</option>
						</select>
						<font color="red">*必填</font>
					</div>
				</td>
				<td  colspan="2" height="30px">
					<div id="versionIdTr">
						系统版本：
						<select  name="pushTasks[11].osVersionId" id="versionId"  style="margin: 15px;width: 200px">
							<option value="" >请选择...</option>
						</select>
						<font color="red">*必填</font>
					</div>
				</td>
			</tr>
			<tr class="tr_padding " style="margin-top: 10px" id="test">
				<td height="30px">任务-测试：</td>
				<td>
					<input type="checkbox" value="20"  name="taskTypes"  id="taskTest" onchange="testChange()"
						   <c:choose><%--<c:when test="${process.id == null}">checked </c:when>--%>
						   <c:when test="${fn:contains(process.taskTypes, '20')}">checked </c:when>
					</c:choose>/>
				</td>
				<td colspan="1" width="45%"></td>
				<td>
					<input type="button" onclick="addrow()" value="添加" id="add_test_btn">
				</td>
				<td>
				</td>
			</tr>
			<c:if test="${process == null}" >
				<tr class="tr_padding test_add" id="testAdd">
					<td><input type="hidden" class="task_index" value="0"></td>
					<td colspan="1">测试1：</td>
					<td colspan="1">
						测试工具：
						<select  name="pushTasks[20].childTasks[0].toolId" class="toolId" onchange="toolIdChange(this)" style="margin: 15px">
							<option value="" >请选择...</option>
							<c:forEach items="${testTools}" var="tool" >
								<option value="${tool.id}" <c:if test="${process.pushTasks[12].toolId == tool.id}">selected="selected"</c:if> >${tool.toolName}</option>
							</c:forEach>
						</select>
						<font color="red">*必填</font>
					</td>
					<td colspan="1">
						版本：
						<select  name="pushTasks[20].childTasks[0].toolVersionId" class="toolVersionId"  style="margin: 15px">
							<option value="" >请选择...</option>
						</select>
						<font color="red">*必填</font>
					</td>
					<td colspan="1">
						<input type="button" onclick="deleteTest(this)" value="删除">
					</td>
					<td>
					</td>
				</tr>
			</c:if>
			<tr class="tr_padding " id="reset_tr">
				<td width="20%" height="30px">任务-出厂升级(正式版本)：</td>
				<td>
					<input type="checkbox" value="14"  name="taskTypes"  id="taskUpdateReset" onchange="updateResetChange()"
						   <c:choose><%--<c:when test="${process.id == null}">checked="checked" </c:when>--%>
						   <c:when test="${fn:contains(process.taskTypes, '14')}">checked="checked" </c:when>
					</c:choose>/>
				</td>
				<td  width="20%" height="30px">
					<div id="updateFactoryWay">
						升级方式：
						<select  name="pushTasks[14].testWay" id="updateFactoryWayId"  style="margin: 15px">
							<option value="" >请选择...</option>
							<option value="0" >T卡升级</option>
							<option value="1" >fastboot烧写分区--YES</option>
							<option value="2" >fastboot烧写分区--NO</option>
						</select>
						<font color="red">*必填</font>
					</div>
				</td>
				<td colspan="4">
					<div id="versionIdResetTr">
						正式系统版本：
						<select  name="pushTasks[14].osVersionId" id="versionIdReset"  style="margin: 15px;width: 200px">
							<option value="" >请选择...</option>
						</select>
						<font color="red">*必填</font>
					</div>
				</td>
			</tr>
			<tr class="tr_padding" id="">
				<td width="20%" height="30px">任务-恢复出厂设置：</td>
				<td>
					<input type="checkbox" value="15"  name="taskTypes"  id="taskReset" onchange="ResetChange()"
						   <c:choose><%--<c:when test="${process.id == null}">checked="checked" </c:when>--%>
						   <c:when test="${fn:contains(process.taskTypes, '15')}">checked="checked"</c:when>
					</c:choose>/>
				</td>
				<td colspan="3">
					<div id="resetWay">
						执行方式：
						<select  name="pushTasks[15].testWay" id="reset"  style="margin: 15px">
							<option value="" >请选择...</option>
							<option value="0" >广播升级</option>
							<option value="1" >指令升级</option>
						</select>
						<font color="red">*必填</font>
					</div>
				</td>
			</tr>
		</table>
		<div class="row">
			<div  class="col-xs-1" align="right">
				<input style="margin: 10px" type="button" onclick="addProcess()" value="提交">
			</div>
		</div>
		<div class="clear_div"></div>
	</div>
</form>
<!-- /.col -->
<%--</div>
</div>--%>
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
<script src="${contextPath}/static/assets/plugin/combo-select-master/jquery.combo.select.js"></script>

<!-- inline scripts related to this page -->

<script type="text/javascript">
    $(function(){
        updateChange();
        modelChange(<c:choose>
            <c:when test="${process.pushTasks[11].osVersionId == null}">null</c:when>
            <c:otherwise>${process.pushTasks[11].osVersionId}</c:otherwise>
            </c:choose>
            , <c:choose>
            <c:when test="${process.pushTasks[14].osVersionId == null}">null</c:when>
            <c:otherwise>${process.pushTasks[14].osVersionId}</c:otherwise>
            </c:choose>);
        updateResetChange();
        testChange();
        ResetChange()

        if($("#processId").val() != ""){
            $(".test_add").each(function(){
                toolIdChange($(this).find(".toolId"), true);
            });

        }
    });
	var grid_selector = "#grid-table";
	var pager_selector = "#grid-pager";
	$("#testselect").modal("hide");

    function updateChange(){
        if($("input[id='taskUpdate']").is(':checked') == false){
            $('#versionIdTr').hide();
            $('#updateWay').hide();
            $('#versionId').val("");
        }else{
            $('#versionIdTr').show();
            $('#updateWay').show();
        }
    }

    function updateResetChange(){
        if($("input[id='taskUpdateReset']").is(':checked') == false){
            $('#versionIdResetTr').hide();
            $('#updateFactoryWay').hide();
            $('#versionIdReset').val("");
        }else{
            $('#versionIdResetTr').show();
            $('#updateFactoryWay').show();
        }
    }

    function ResetChange(){
        if($("input[id='taskReset']").is(':checked') == false){
            $('#resetWay').hide();
        }else{
            $('#resetWay').show();
        }
    }

    function testChange(){
        if($("input[id='taskTest']").is(':checked') == true){
            $('#add_test_btn').show();
            addrow();
        }else{
            $('#add_test_btn').hide();
            $('.test_add').remove();
            $('.items_config').remove();
        }
    }

    function validate() {
        var result = true;
        if ("" == $("#processName").val()) {
            layer.msg("请填写流程名称!");
            return false;
        }
        if ("" == $("#modelId").val()) {
            layer.msg("请选择机型!");
            return false;
        }
        if ($("input[id='taskUpdate']").is(':checked') == false
            && $("input[id='taskTest']").is(':checked') == false
            && $("input[id='taskUpdateReset']").is(':checked') == false
            && $("input[id='taskReset']").is(':checked') == false) {
            layer.msg("请至少选择一个任务！");
            return false;
        }
        if ($("input[id='taskUpdate']").is(':checked') == true
            && $('#versionId').val() == "") {
            layer.msg("请选择系统版本！");
            return false;
        }
        if ($("input[id='taskTest']").is(':checked') == true) {
            if($(".test_add").length == 0){
                layer.msg("请至少添加一个测试或者取消选择测试！");
                return false;
            }
            $(".test_add").each(function(){
                if($(this).find('.toolId').val() == "" || $(this).find('.toolVersionId').val() == ""){
                    layer.msg("测试工具和版本不能为空！");
                    result = false;
                    return;
                }

                if($(this).next("tr").hasClass("items_config")){
                    $(this).next("tr").find(".item_config").each(function(){
                        if($(this).find(".item_check").is(":checked") == false){
                            if($(this).index() == 0){
                                layer.msg("请至少选择一个测试项！");
                                result = false;
                            }
                        }else{
                            if($(this).find(".test_duration").val() == "0"){
                                layer.msg("已选测试项的测试时长不能为0！");
                                result = false;
                            }
                        }
                    });
                }
            });
        }
        if ($("input[id='taskUpdateReset']").is(':checked') == true
            && $('#versionIdReset').val() == "") {
            layer.msg("请选择正式出厂系统版本！");
            return false;
        }

        return result;


    }

	//$('select').comboSelect();

    function modelChange(versionId, resetVersionId) {
        $('#modelIdInput').val($('#modelId').val());
        var modleid = $('#modelId').val();
        $.ajax({
            url:'${contextPath}/uhostmanage/toolManage/sysVersion',
            data:'modelId=' + modleid,
            dataType:'json',
            success:function(data) {
                if (data.stat == '200') {
                    var html = "<option value=\"\" >请选择...</option>";
                    var versionSelect = $('#versionId');
                    var versionResetSelect = $('#versionIdReset');
                    versionSelect.html(html);
                    versionResetSelect.html(html);
                    $.each(data.data, function (n, value) {
                        if(value.id == versionId){
                            html = "<option selected=\"selected\" value=\" " + value.id + "\" >" + value.versionCode + "</option>";
                        }else{
                            html = "<option value=\" " + value.id + "\" >" + value.versionCode + "</option>";
                        }
                        versionSelect.append(html);
                        if(value.osType == 7){
                            if(value.id == resetVersionId){
                                html = "<option selected=\"selected\" value=\" " + value.id + "\" >" + value.versionCode + "</option>";
                            }else{
                                html = "<option value=\" " + value.id + "\" >" + value.versionCode + "</option>";
                            }
                            versionResetSelect.append(html);
                        }
                    });
                }
            }
        });
    }

	//resize to fit page size
	$(window).on('resize.jqGrid', function () {
		$(grid_selector).jqGrid('setGridWidth', $("#tablecontent").width());
	});

	$(window).triggerHandler('resize.jqGrid');//trigger window resize to make the grid get the correct size

	var formData = {};
	$.each($("#toolbar :input").serializeArray(), function (i, item) {
		formData[item.name] = item.value;
	});

    function itemChange(dom){
        var len = $('input[class="item_check"]:checked').length;
        var $tr = $(dom).closest(".item_config");
        if($(dom).is(":checked") == true){
            if(len > 1 && $tr.find(".last_item").length > 0){
                if($tr.closest(".sorts_table").find("tr").eq(len - 2).find(".last_item").length > 0){
                    layer.msg("最后测试项最多选择一个！");
                    $(dom).attr("checked", false);
                    return;
                }
            }
            $tr.removeClass("no_select");
            $tr.addClass("selected");
            $tr.find(".item_move").show();
            $tr.find(".test_duration").removeAttr("disabled");
            while($tr.index() > 0){
                if($tr.prev().hasClass("no_select") || $tr.prev().find(".last_item").length > 0){
                    $tr.fadeOut();
                    $tr.prev().before($tr);
                    if($tr.prev().hasClass("selected") && $tr.find(".last_item").length > 0){
                        break;
                    }
                }else{
                    break;
                }
            }
            $tr.fadeIn();
        }else{
            $tr.addClass("no_select");
            $tr.removeClass("selected");
            $tr.find(".item_move").hide();
            $tr.find(".test_duration").val("0");
            $tr.find(".test_duration").attr("disabled", "disabled");
            while($tr.index() < len){
                if(!$tr.next().hasClass("no_select")){
                    $tr.fadeOut();
                    $tr.next().after($tr);
                }else{
                    break;
                }
            }
            $tr.fadeIn();
        }
    }

    function addProcess() {
        if (validate()) {
            $(".items_config").each(function(){
                $(this).find(".item_config").each(function(){
                    if($(this).find(".item_check").is(":checked") == true){
                        $(this).find(".item_order").val($(this).index() + 1);
                    }
                });
            });
            var url = "${contextPath}/uhostmanage/testmanage/addProcess";
            $.ajax({
                url:url,
                type:"POST",
                data:$('#editForm').serialize(),
                async:false,
                dataType:'json',
                success:function(data){
                    if(data.stat == '200') {
                        bootbox.confirm('保存成功!', function(result) {
                            window.location.href = "${contextPath}/uhostmanage/testManage/testflow";
                            parent.location.reload()
                        })
                    }else {
                        layer.msg("保存失败！");
                    }
                }
            });
        }
    }

    function itemChange(dom){
        var len = $('input[class="item_check"]:checked').length;
        var $tr = $(dom).closest(".item_config");
        if($(dom).is(":checked") == true){
            if(len > 1 && $tr.find(".last_item").length > 0){
                if($tr.closest(".sorts_table").find("tr").eq(len - 2).find(".last_item").length > 0){
                    layer.msg("最后测试项最多选择一个！");
                    $(dom).attr("checked", false);
                    return;
                }
            }
            $tr.removeClass("no_select");
            $tr.addClass("selected");
            $tr.find(".item_move").show();
            $tr.find(".test_duration").removeAttr("disabled");
            while($tr.index() > 0){
                if($tr.prev().hasClass("no_select") || $tr.prev().find(".last_item").length > 0){
                    $tr.fadeOut();
                    $tr.prev().before($tr);
                    if($tr.prev().hasClass("selected") && $tr.find(".last_item").length > 0){
                        break;
                    }
                }else{
                    break;
                }
            }
            $tr.fadeIn();
        }else{
            $tr.addClass("no_select");
            $tr.removeClass("selected");
            $tr.find(".item_move").hide();
            $tr.find(".test_duration").val("0");
            $tr.find(".test_duration").attr("disabled", "disabled");
            while($tr.index() < len){
                if(!$tr.next().hasClass("no_select")){
                    $tr.fadeOut();
                    $tr.next().after($tr);
                }else{
                    break;
                }
            }
            $tr.fadeIn();
        }
    }

    function toolIdChange(dom, isUpdateInit){
        if(isUpdateInit != true){
            var sum = 0;
            $(".test_add").each(function(){
                if($(this).find('.toolId').val() == $(dom).val()){
                    sum++;
                }
            });
            if(sum > 1){
                layer.msg("请勿重复选择测试工具！");
                $(dom).val("");
                return;
            }

            var html = "<option value=\"\" >请选择...</option>";
            $(dom).closest('td').next().find('select').html(html);

            if($(dom).val() != "") {
                $.ajax({
                    url: '${ctcontextPathx}/uhostmanage/toolManage/getVersionList',
                    data: 'toolId=' + $(dom).val(),
                    dataType: 'json',
                    success: function (data) {
                        if (data.stat == '200') {
                            $.each(data.data, function (n, value) {
                                html = "<option value=\" " + value.id + "\" >" + value.versionName + "</option>";
                                $(dom).closest('td').next().find('select').append(html);
                            });
                        }
                    }
                });
            }
        }

        if($(dom).closest('.test_add').next("tr").hasClass("items_config")){
            $(dom).closest('.test_add').next("tr").remove();
            $(dom).closest('.test_add').find('.items_up').hide();
            $(dom).closest('.test_add').find('.items_down').hide();
        }

        if($(dom).val() != ""){
            var taskId = "";
            if(isUpdateInit == true){
                taskId = $(dom).closest(".test_add").find(".task_id").val();
            }
            $.ajax({
                url:'${ctcontextPathx}/uhostmanage/toolManage/getItemList',
                data:'toolId=' + $(dom).val() + '&taskId=' + taskId,
                dataType:'json',
                success:function(data) {
                    if (data.stat == '200') {
                        var taskIndex = $(dom).closest(".test_add").find(".task_index").val();
                        var trHtml = "<tr class=\"tr_padding sorts_gray items_config\">" +
                            "<td></td>" +
                            "<td>测试项配置:</td>" +
                            "<td colspan=\"3\">" +
                            "<table width=\"100%\" class=\"sorts_table \" cellspacing=\"1\">";
                        $.each(data.data, function (n, item) {
                            var defaultChecked = "";
                            var moveStyle = "style=\"display: none\"";
                            var selectClass = "no_select";
                            var durationDisabled = "disabled";
                            if((isUpdateInit != true && item.isDefault == 1) || item.taskId != null){
                                defaultChecked = "checked";
                                moveStyle = "";
                                selectClass = "selected";
                                durationDisabled = "";
                            }
                            trHtml += "<tr id=\"itemTr_" + item.id + "\" class=\"item_config " + selectClass + "\" >" +
                                "<input type=\"hidden\" name=\"pushTasks[20].childTasks[" + taskIndex + "].testItems[" + n + "].testType\" value=\"" + item.testType + "\" />" +
                                "<input type=\"hidden\" name=\"pushTasks[20].childTasks[" + taskIndex + "].testItems[" + n + "].caseName\" value=\"" + item.caseName + "\" />" +
                                "<input type=\"hidden\" name=\"pushTasks[20].childTasks[" + taskIndex + "].testItems[" + n + "].itemName\" value=\"" + item.itemName + "\" />" +
                                "<input type=\"hidden\" class=\"item_order\" name=\"pushTasks[20].childTasks[" + taskIndex + "].testItems[" + n + "].itemOrder\" value=\"\" />" +
                                "<td width=\"5%\"><input type=\"checkbox\" class=\"item_check\" name=\"pushTasks[20].childTasks[" + taskIndex + "].testItems[" + n + "].state\" value=\"1\" onchange=\"itemChange(this)\" " + defaultChecked + " /></td>" +
                                "<td width=\"15%\">" +
                                "编号: " + item.testType +
                                "</td>" +
                                "<td width=\"25%\" style='display:none'>" +
                                "Case Name: " + item.caseName +
                                "</td>" +
                                "<td width=\"25%\">" +
                                "名称: " + item.itemName +
                                "</td>" +
                                "<td width=\"25%\">" +
                                "<div class=\"duration\">时长：" +
                                "<input onchange=\"$(this).val($(this).val().replace(/\\D/g,''))\" name=\"pushTasks[20].childTasks[" + taskIndex + "].testItems[" + n + "].testDuration\" class=\"test_duration\"  value='" + item.testDuration + "' " + durationDisabled + " >" +
                                "<select onchange=\"$(this).prev('.test_duration').val($(this).val())\" class=\"test_duration\" " + durationDisabled +
                                "><option value=\"0\" >0</option>" +
                                "<option value=\"1\" >1</option>" +
                                "<option value=\"3\" >3</option>" +
                                "<option value=\"6\" >6</option>" +
                                "<option value=\"12\" >12</option>" +
                                "<option value=\"24\" >24</option>" +
                                "<option value=\"48\" >48</option>" +
                                "</select>" +
                                "<span>时</span></div>" +
                                "</td>" +
                                "<td width=\"20%\">";
                            if(item.isDefault == 1){
                                trHtml += "<font color=\"red\" class=\"default_item\">*默认项</font>";
                            }
                            if(item.isLast == 1){
                                trHtml += "<font color=\"red\" class=\"last_item\">*最后项</font>";
                            }
                            trHtml += "</td>" +
                                "<td width=\"10%\">" +
                                "<img src=\"${ctx}/images/up.png\" width=\"20\" height=\"20\"" +
                                "border=\"0\" onclick=\"up(this)\" class=\"item_move\" " + moveStyle + " />" +
                                "<img src=\"${ctx}/images/down.png\" width=\"20\" height=\"20\"" +
                                "border=\"0\" onclick=\"down(this)\" class=\"item_move\" " + moveStyle + " />" +
                                "</td>" +
                                "</tr>";
                        });

                        trHtml += "</table>" +
                            "</td>" +
                            "<td></td>" +
                            "</tr>";
                        $(dom).closest('.test_add').after(trHtml);
                        $(dom).closest('.test_add').find('.items_up').show();
                    }
                }
            });
        }
    }

    var testToolOptions = "";
    <c:forEach var="tool" items="${testTools}" >
    testToolOptions += "<option value='${tool.id}' >${tool.toolName}</option>";
    </c:forEach>

	function addrow(){
		var tables = $("#test");
		var index = $(".test_add").length;
		var num = index + 1;
		/*var aa = $("#testAdd" ).clone().attr('id','test'+num).attr('')
        $("#reset_tr").before(aa)*/
		var html = $("<tr class='tr_padding test_add' id='testAdd"+ index +"'>"+
			"<td><input type='hidden' class='task_index' value='"+index+"'></td>"+
			"<td height='30px'>测试 "+ num +"：</td>" +
            "<td width='20%'>" +
            "测试工具：" +
            "&nbsp<select name='pushTasks[20].childTasks["+ index +"].toolId' class='toolId' onchange='toolIdChange(this)'  style='margin: 15px'>" +
            "<option value='' >请选择...</option>" +testToolOptions+
            "</select>" +
            "<font color='red'>*必填</font>" +
            "</td>"+
			"<td width='20%'>" +
            "版本：" +
            "<select  name='pushTasks[20].childTasks["+ index +"].toolVersionId' class='toolVersionId'  style='margin: 15px'>" +
            "<option value='' >请选择...</option>"+
            "</select>" +
            "<font color='red'>*必填</font>" +
            "</td>"+
			"<td>" +
            "<input type='button' onclick='deleteTest(this)' value='删除'>" +
            "</td>"+
			"</tr>");
		$("#reset_tr").before(html);
        //$('select').comboSelect();
		//html.after(tables);

	}

	function deleteTest(dom){
        if($(dom).closest(".test_add").next("tr").hasClass("items_config") == true){
            $(dom).closest(".test_add").next("tr").remove();
        }
        $(dom).closest(".test_add").remove();

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
