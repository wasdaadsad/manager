<%--
  Created by IntelliJ IDEA.
  User: dongjiajin
  Date: 2018/2/7
  Time: 16:39
  To change this template use File | Settings | File Templates.
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en" ng-app="myApp" ng-controller="MyCtrl" ng-cloak>
<head>
    <title>vivo comm2后台</title>
    <!-- page specific plugin styles -->
    <link rel="stylesheet"
          href="${contextPath}/static/assets/css/jquery-ui.css"/>
    <link rel="stylesheet"
          href="${contextPath}/static/assets/css/ui.jqgrid.css"/>

    <link rel="stylesheet"
          href="${contextPath}/static/assets/plugin/select2/css/select2.css"/>
    <link rel="stylesheet" href="${contextPath}/static/assets/css/bootstrap.css">
    <script src="${contextPath}/static/assets/js/jquery.js"></script>
    <script src="${contextPath}/static/assets/js/bootstrap.js"></script>
    <script src="${contextPath}/static/assets/plugin/layer-v3.1.1/layer.js"></script>
    <%@include file="../common/common.jsp" %>
    <style>
        .tab tr{background: #f1f1f1;
            border: 5px;
        }
        .tab tr:nth-child(even){background: #ccc;}
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

<form id="editForm">
    <input name="id" id="toolId" type="hidden" value="${toolInfo.id}" />
    <div class="out">
        <%--<div class="main_right_title">编辑测试工具</div>
        <div class="clear_div"></div>--%>

        <table width="100%" border="0" cellspacing="1" class="sorts_table table_color tab" id="table1">
            <tr class="sorts_title" >
                <td colspan="12">编辑测试工具</td>
                <td></td>
            </tr>
            <tr class="tr_padding" style="margin-top: 20px">
                <td width="5%">工具名称：</td>
                <td colspan="11" style="margin: 10px"><input style="margin: 10px" name="toolName" type="text" maxlength="100" value="${toolInfo.toolName}"
                                        class="txt gonggao" id="toolName"/><font color="red">*必填</font></td>
                <td></td>
            </tr>
            <tr class="tr_padding " style="margin-top: 20px ">
                <td>包名：</td>
                <td colspan="11"><input style="margin: 10px" name="packageName" type="text" maxlength="100" onchange="packageNameChange()" value="${toolInfo.packageName}"
                                        class="txt gonggao" id="packageName"/><font color="red">*必填</font></td>
                <td></td>
            </tr>
            <tr class="tr_padding" style="margin-top: 20px">
                <td>启动命令：</td>
                <td colspan="11"><input style="margin: 10px" name="startCmd" type="text" maxlength="100" value="${toolInfo.startCmd}"
                                        class="txt gonggao" id="startCmd"/><font color="red">*必填</font></td>
                <td></td>
            </tr>
            <tr class="tr_padding" style="margin-top: 20px">
                <td>测试项：</td>
                <td width="50%" colspan="11">
                    <font style="margin: 10px" color="red">*默认项为默认配置测试项，至少一个。最后项为只能配置为最后的测试项，默认最后项最多一个</font>
                </td>
                <td>
                    <input style="margin: 10px" type="button" onclick="addrow()" value="添加">
                </td>
            </tr>
            <c:if test="${toolInfo == null}" >
                <tr class="tr_padding item_add" style="margin-top: 20px" id="testAdd" >
                    <td width="5%">编号：</td>
                    <td width="10%">
                        <input style="margin: 10px" type="text" style="width: 50px" class="txt sort_input testType" maxlength="100" name="toolItemBOs[0].testNum"
                               onchange="testTypeChange(this)"/>
                    </td>
                    <td width="5%" align="right">CaseName：</td>
                    <td class="caseName-td" >
                        <input  type="text" class="txt sort_input caseName" maxlength="45" name="toolItemBOs[0].caseName">
                    </td>
                    <td width="5%" align="right">名称：</td>
                    <td class="itemName-td" >
                        <input type="text" class="txt sort_input itemName" maxlength="100" name="toolItemBOs[0].testName">
                    </td>
                    <td width="8%" align="right">
                        默认项：
                        <input type="checkbox" value="1"  name="toolItemBOs[0].isDefault"  class="isDefault" onchange="defaultChange(this)" />
                    </td>
                    <td width="8%" align="right">
                        最后项：
                        <input type="checkbox" value="1"  name="toolItemBOs[0].isLast"  class="isLast" />
                    </td>
                    <td width="8%" align="right">
                        <div class="duration">
                            时长：
                            <select  onchange="$(this).prev('.testDuration').val($(this).val())" name="toolItemBOs[0].testDuration" class="testDuration" >
                                <option value="0" >0</option>
                                <option value="1" >1</option>
                                <option value="3" >3</option>
                                <option value="6" >6</option>
                                <option value="12" >12</option>
                                <option value="24" >24</option>
                                <option value="48" >48</option>
                            </select>
                            <span>时</span>
                        </div>
                    </td>
                    <td width="5%" align="right">
                        <input style="margin: 10px" type="button" onclick="deleteTest(this)" value="删除">
                    </td>
                </tr>
            </c:if>
            <c:set var="testItems" value="${toolItems}" />
            <c:if test="${testItems != null}">
                <c:forEach var="item" items="${testItems}" varStatus="status" >
                    <tr class="tr_padding item_add">
                        <td style="display:none"><input type="hidden" name="toolItemBOs[${status.index}].id" value="${item.id}"/></td>
                        <td width="5%" class="testType-td">编号：</td>
                        <td width="10%">
                            <input style="margin: 10px" type="text" style="width: 50px" class="txt sort_input testType" maxlength="100"
                                   name="toolItemBOs[${status.index}].testNum" value="${item.testNum}" />
                        </td>
                        <td width="5%" align="right">CaseName：</td>
                        <td class="caseName-td" >
                            <input type="text" class="txt sort_input caseName" maxlength="45"
                                   name="toolItemBOs[${status.index}].caseName" value="${item.caseName}">
                        </td>
                        <td width="5%" align="right">名称：</td>
                        <td class="itemName-td">
                            <input type="text" class="txt sort_input itemName" maxlength="20"
                                   name="toolItemBOs[${status.index}].testName" value="${item.testName}">
                        </td>
                        <td width="8%" align="right">
                            默认项：
                            <input type="checkbox" value="1"  name="toolItemBOs[${status.index}].isDefault"  class="isDefault"
                                   onchange="defaultChange(this)" <c:if test="${item.isDefault == 1}">checked</c:if> />
                        </td>
                        <td width="8%" align="right">
                            最后项：
                            <input type="checkbox" value="1"  name="toolItemBOs[${status.index}].isLast"  class="isLast"
                                   <c:if test="${item.isLast == 1}">checked</c:if>/>
                        </td>
                        <td width="8%" align="right">
                            <div class="duration">
                                时长：
                                <select onchange="$(this).prev('.testDuration').val($(this).val())" class="testDuration" name="toolItemBOs[${status.index}].testDuration">
                                    <c:if test="${item.isDefault == 1}"> <option value="<fmt:formatNumber type='number' value='${item.testDuration/3600}' maxFractionDigits='0'/>" >${item.testDuration/3600}</option> </c:if>
                                    <option value="0" >0</option>
                                    <option value="1" >1</option>
                                    <option value="3" >3</option>
                                    <option value="6" >6</option>
                                    <option value="12" >12</option>
                                    <option value="24" >24</option>
                                    <option value="48" >48</option>
                                </select>
                                <span>时</span>
                            </div>
                        </td>
                        <td width="5%" align="right">
                            <input style="margin: 10px" type="button" onclick="deleteTest(this)" value="删除">
                        </td>
                    </tr>
                </c:forEach>
            </c:if>

            <tr id="sub_btn" class="tr_padding " style="margin-top: 20px">
            </tr>
        </table>
            <div class="row">
                <div  class="col-xs-1" align="right">
                    <input style="margin: 10px" type="button" onclick="addTool()" value="提交">
                </div>
            </div>
        <div class="clear_div"></div>
    </div>
</form>
<div>
    <tr id="sub_btn1" class="tr_padding " style="margin-top: 20px">
    </tr>
</div>
<!-- /.main-container -->

<!-- page specific plugin scripts -->
<script src="${contextPath}/static/assets/plugin/jquery-ui.js"></script>
<script src="${contextPath}/static/assets/plugin/jquery.ui.touch-punch.js"></script>
<script src="${contextPath}/static/assets/plugin/ace/ace.searchbox-autocomplete.js"></script>
<script src="${contextPath}/static/assets/plugin/select2/js/select2.min.js"></script>
<script src="${contextPath}/static/assets/plugin/My97DatePicker/WdatePicker.js"></script>
<script src="${contextPath}/static/assets/plugin/echarts3/echarts.min.js"></script>
<script src="${contextPath}/static/assets/plugin/jquery.form.js"></script>
<script src="${contextPath}/static/assets/plugin/My97DatePicker/WdatePicker.js"></script>
<script src="${contextPath}/static/assets/plugin/bootbox.js"></script>
<!-- inline scripts related to this page -->


<script src="${contextPath}/static/assets/js/angular.min.js"></script>

</body>
</div>
</html>
<script>
    var n = 0;
    var app = angular.module('myApp', []);
    app.controller('MyCtrl', function($scope){});

    app.controller('formCtrl', function($scope) {

        $scope.modelList = [
            {selected : "PD1709", url : ""},
            {selected : "PD1621", url : ""},
            {selected : "PD1731", url : ""},
            {selected : "PD1731F_EX", url : ""}
        ];

        $scope.versionList = [
            {selected : "PD1503_A_A09.30.05_eng", url : ""},
            {selected : "PD1419C_A_1.4.0", url : ""}
        ];

        $scope.toolList = [
            {selected : "工具1", url : ""},
            {selected : "工具2", url : ""}
        ];
    });

    function addTool() {
        if (validate()) {
            var url = "${ctx}/uhostmanage/toolManage/toolAdd";

            if($("#toolId").val() != ""){
                url = "${ctx}/uhostmanage/toolManage/toolUpdate"
            }
            $.ajax({
                url:url,
                type:"POST",
                data:$('#editForm').serialize(),
                async:false,
                dataType:'json',
                success:function(data){
                    if(data.stat == '200') {
                        bootbox.confirm('保存成功!', function(result) {
                            parent.document.getElementById("tooladd").style.display = "none"
                            parent.location.reload()
                        })
                    }else {
                        layer.msg("保存失败！");
                    }
                }
            });
        }
    }
    function validate() {
        var result = true;
        if ("" == $("#toolName").val()) {
            layer.msg("请填写工具名称!");
            result = false;
        }
        if ("" == $("#packageName").val()) {
            layer.msg("请填写包名!");
            result = false;
        }
        if ("" == $("#startCmd").val()) {
            layer.msg("请填写启动命令!");
            result = false;
        }
        if ($(".item_add").length == 0) {
            layer.msg("请至少添加一个测试项!");
            result = false;
        }
        var defaultLast = 0;
        $(".item_add").each(function(){
            if($(this).find('.testType').val() == "" || $(this).find('.itemName').val() == ""){
                layer.msg("测试项编号和名称不能为空！");
                result = false;
            }
            if($(this).find('.isDefault').is(':checked') == true
                && ($(this).find('.testDuration').val() == "0" || $(this).find('.testDuration').val() == "")){
                layer.msg("默认项请选择测试时长！");
                result = false;
            }
            if($(this).find('.isDefault').is(':checked') == true && $(this).find('.isLast').is(':checked') == true){
                defaultLast++;
            }
        });
        if ($("input[class='isDefault']").is(':checked') == false) {
            layer.msg("请至少选择一个默认测试项！");
            result = false;
        }
        if(defaultLast > 1){
            layer.msg("默认项和最后项同时选择的测试项不能超过一个！");
            result = false;
        }

        return result;
    }

    function deleteTest(tr){
        $(tr).parent().parent().remove();
        n++;
    }

    function addrow(){
        var tables = $("#test");
        var num = $(".item_add").length;
        var index = num  + n;
        //var aa = $("#testAdd" ).clone().attr('id','testAdd'+num);
        //$("#sub_btn").before(aa)
        var html = $(
            "<tr class=\"tr_padding item_add\" style=\"margin-top: 20px\" id='testAdd"+ num +"'>"+
            "<td style='display:none'></td>" +
            "<td width=\"5%\" class='testType-td'>编号：</td>" +
            "<td width=\"10%\">" +
            "<input style=\"margin: 10px\" type=\"text\" style=\"width: 50px\" class=\"txt sort_input testType\" maxlength=\"100\" name=\"toolItemBOs[" + index + "].testNum\" " +
            "class=\"txt sort_input testType\" onchange=\"testTypeChange(this)\" />" +
            "</td>" +
            "<td width=\"5%\" align=\"right\">CaseName：</td>" +
            "<td class=\"caseName-td\" >" +
            "<input name=\"toolItemBOs[" + index + "].caseName\" type=\"text\" maxlength=\"100\"" +
            "class=\"txt sort_input caseName\" />" +
            "</td>" +
            "<td width=\"5%\" align=\"right\">名称：</td>" +
            "<td class=\"caseName-td\" >" +
            "<input name=\"toolItemBOs[" + index + "].testName\" type=\"text\" maxlength=\"100\"" +
            "class=\"txt sort_input itemName\" />" +
            "</td>" +
            "<td width=\"8%\" align=\"right\">" +
            "默认项：" +
            "<input type=\"checkbox\" value=\"1\"  name=\"toolItemBOs[" + index + "].isDefault\"  class=\"isDefault\" onchange=\"defaultChange(this)\" />" +
            "</td>" +
            "<td width=\"8%\" align=\"right\">" +
            "最后项：" +
            "<input type=\"checkbox\" value=\"1\"  name=\"toolItemBOs[" + index + "].isLast\"  class=\"isLast\" />" +
            "</td>" +
            "<td width=\"8%\" align=\"right\">" +
            "<div class=\"duration\" align=\"right\">时长：" +
            "<select onchange=\"$(this).prev('.testDuration').val($(this).val())\" name=\"toolItemBOs[" + index + "].testDuration\" class=\"testDuration\" " +
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
            "<td width=\"5%\" align=\"right\">" +
            "<input style='margin: 10px' type='button' onclick='deleteTest(this)' value='删除'>" +
            "</td>" +
            "</tr>");
        $("#sub_btn").before(html);
        //html.after(tables);


    }


</script>