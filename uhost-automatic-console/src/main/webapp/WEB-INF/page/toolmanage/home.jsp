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
<div class="main-content-inner">
    <div class="page-content">
        <div class="row">
            <div class="col-xs-12">
                <div class="widget-box">
                    <div class="widget-header widget-header-flat widget-header-small">
                        <div class="toolbar" id="toolbar">
                            <div class="clearfix">
                                <div class="row">

                                    <div class="col-xs-2 nopadding-right">
                                        <input type="text" name="modelName" class="input-sm col-xs-12"
                                               placeholder="工具">
                                    </div>

                                    <div class="col-xs-2 nopadding-right">
                                        <input type="text" name="modelVersion" class="input-sm col-xs-12"
                                               placeholder="工具包名">
                                    </div>

                                    <div class="col-xs-3">

                                        <button id="submit" class="btn btn-success btn-xs">
                                            <span class="ace-icon glyphicon glyphicon-ok"></span> 搜索
                                        </button>

                                        <button id="addTool" class="btn btn-success btn-xs">
                                            <span class="ace-icon glyphicon glyphicon-ok"></span> 新增
                                        </button>
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
<div class="modal fade" id="tooladd" tabindex="-1" role="dialog" aria-labelledby="myModalLabe"
     aria-hidden="true">
    <div class="modal-dialog" style="width: 90%;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×
                </button>
                <h4 class="modal-title" id="myModalLabe">
                    编辑测试工具
                </h4>
            </div>
            <div class="modal-body" id="iframeContainer">

            </div>
            <div class="nopadding-right">
            </div><div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">
                关闭
            </button>
        </div>
        </div>
    </div>
</div>
<div class="modal fade" id="toolVersion" tabindex="-1" role="dialog" aria-labelledby="myModalLabe"
     aria-hidden="true">
    <div class="modal-dialog" style="width: 90%;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×
                </button>
                <h4 class="modal-title" id="myModalLabe2">
                    工具版本
                </h4>
            </div>
            <div class="modal-body" id="iframeContainer2">

            </div>
            <div class="nopadding-right">
            </div><div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">
                关闭
            </button>
        </div>
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
<script src="${contextPath}/static/assets/plugin/My97DatePicker/WdatePicker.js"></script>
<script src="${contextPath}/static/assets/plugin/echarts3/echarts.min.js"></script>
<script src="${contextPath}/static/assets/plugin/jquery.form.js"></script>
<script src="${contextPath}/static/assets/plugin/jqGrid/jquery.jqGrid.src.js"></script>
<script src="${contextPath}/static/assets/plugin/jqGrid/i18n/grid.locale-cn.js"></script>
<script src="${contextPath}/static/assets/plugin/My97DatePicker/WdatePicker.js"></script>
<script src="${contextPath}/static/assets/plugin/bootbox.js"></script>
<script src="${contextPath}/static/assets/plugin/jquery.form.js"></script>
<!-- inline scripts related to this page -->

<script type="text/javascript">
    var grid_selector = "#grid-table";
    var pager_selector = "#grid-pager";
    var id = "id";
    var state = "state";

    $("#tooladd").modal("hide");
    $("#toolVersion").modal("hide");

    function versionEdit(data){
        var i = data.id;
        $("#iframeContainer2").html("<iframe src='${contextPath}/uhostmanage/toolManage/ToolVersion?id=" +  i + "' width='100%'  frameborder='0'  height='600px'></iframe>");
        $("#toolVersion").modal("show");
    }

    function editTool(data){
        var i = data.id;
        $("#iframeContainer2").html("<iframe src='${contextPath}/uhostmanage/toolManage/editTool?id=" +  i + "' width='100%'  frameborder='0'  height='600px'></iframe>");
        $("#toolVersion").modal("show");
    }

    $("#addTool").on("click", function (e) {
        $("#iframeContainer").html("<iframe src='${contextPath}/uhostmanage/toolManage/addTool' width='100%'  frameborder='0'  height='600px'></iframe>");
        $("#tooladd").modal("show");
    });


    //执行编辑删除和添加操作
    function editFormatter(cellvalue, options, rowObject) {
        return "<a href='javascript:void(0)' onclick='versionEdit(" + JSON.stringify(rowObject) + ")'>版本列表</a>&nbsp;| " +
            "<a href='javascript:void(0)' onclick='changeState(" + JSON.stringify(rowObject) + ")'>切换状态</a>";
    }
    //执行查看操作
    function updateFormatter(cellvalue, options, rowObject) {
        return "<a href='javascript:void(0)' onclick='editTool(" + JSON.stringify(rowObject) + ")'>"+ cellvalue +"</a>";
    }

    function changeState(data) {
        bootbox.confirm("是否切换状态", function (result) {
            var i = data.id;
            var s = data.state;
            var formData = {};
            formData[id] = i;
            formData[state] = s;
            if (result) {
                $.ajax({
                    type: 'POST',
                    url:"${contextPath}/uhostmanage/toolManage/changeState",
                    data:formData,
                    complete:function(res){
                        gridReload();
                    }
                })
            }
        });
    }

    jQuery(function ($) {

        //resize to fit page size
        $(window).on('resize.jqGrid', function () {
            $(grid_selector).jqGrid('setGridWidth', $("#tablecontent").width());
        });

        $(window).triggerHandler('resize.jqGrid');//trigger window resize to make the grid get the correct size

        //初始化其从他页面带查询参数跳转过来时异常类型的选中
        $("select[name='crashTypeCode'] option").each(function () {
            if ($(this).val() == "${crashTypeCode}") {
                $(this).attr("selected", "selected");
            }
        });

        var formData = {};
        $.each($("#toolbar :input").serializeArray(), function (i, item) {
            formData[item.name] = item.value;
        });


        $(grid_selector).jqGrid({
            url: "${contextPath}/uhostmanage/toolManage/toolList.json",
            datatype: "json",// 从服务器端返回的数据类型
            mtype: "post",// ajax提交方式。POST或者GET，默认GET
            postData: formData,// 此数组内容直接赋值到url上，参数类型：{name1:value1…} 默认空array{}
            colNames: ['名称','包名', '启动命令', '创建时间','状态','操作'],//表头
            // 设置表格列的属性
            colModel: [
                {name: 'toolName', index: 'toolName', align: "center", sortable: false, width:30, formatter: updateFormatter},
                {name: 'packageName', index: 'packageName', align: "center", sortable: false, width: 40},
                {name: 'startCmd', index: 'startCmd', align: "center", sortable: false, width: 50},
                {
                    name: 'createTimeVp',
                    index: 'createTimeVp',
                    align: "center",
                    sortable: false,
                    width: 30,
                    //解决时间戳的格式化问题
                    formatter:'date', formatoptions: {srcformat:'Y-m-d H:i:s',newformat:'Y-m-d H:i:s'}
                },
                {
                    name: 'state',
                    index: 'state',
                    align: "center",
                    sortable: false,
                    width: 20,
                    search:true,
                    //页面选择显示
                    edittype:'select',
                    formatter:'select',
                    editoptions:{value:"1:有效;0:无效;"},
                    stype: "select", sopt:["eq"],searchoptions: {multiple:true, value: ":全部状态;1:有效;0:无效"}
                },
                {
                    name: 'id',
                    index: 'id', //索引,其和后台交互的参数为sidxv
                    align: "center",
                    sortable: false, //是否可排序
                    width: 30,
                    formatter: editFormatter //对列进行格式化时设置的函数名或者类型
                },
            ],
            rowNum: 20, // 默认每页记录的数据
            rowList: [10,20,50,100], //可选设置每页记录的数据
            rownumbers: true, //行编号
            sortorder: 'desc',
            sortname: 'uniqid', // 默认的排序列。可以是列名称或者是一个数字，这个参数会被提交到后台
            viewrecords: true, // 定义是否要显示总记录数
            pager: pager_selector,// 分页标签
            altRows: true, //设置表格 zebra-striped 值
            // 当返回的数据行数为0时显示的信息。只有当属性 viewrecords 设置为ture时起作用
            //caption:"机型列表", //小标题
            toolbar:[true,"top"], //点击向上收起excel
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
            // 如果为ture时，则当表格在首次被创建时会根据父元素比例重新调整表格宽度。
            // 如果父元素宽度改变，为了使表格宽度能够自动调整则需要实现函数：setGridWidth
            autowidth: true,
            height: 'auto',
            //当参数为null时不会被发到服务器端
            prmNames: {
                page: "page",
                rows: "size",
                sort: "sinx",
                order: "order",
                search: "search",
                nd: "nd",
                npage: null
            },
            //描述json 数据格式的数组
            jsonReader: {
                root: "data.content",
                total: "data.totalPages", //总页数
                page: "data.number", //当前页
                records: "data.totalElements", //查询出的记录数
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
