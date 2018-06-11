<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<title>500 Error Page - Ace Admin</title>

<meta name="description" content="500 Error Page" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />

<!-- bootstrap & fontawesome -->
<link rel="stylesheet"
	href="${contextPath}/aceadmin/assets/css/bootstrap.css" />
<link rel="stylesheet"
	href="${contextPath}/aceadmin/assets/css/font-awesome.css" />

<!-- page specific plugin styles -->

<!-- text fonts -->
<link rel="stylesheet"
	href="${contextPath}/aceadmin/assets/css/ace-fonts.css" />

<!-- ace styles -->
<link rel="stylesheet" href="${contextPath}/aceadmin/assets/css/ace.css"
	class="ace-main-stylesheet" id="main-ace-style" />

<!--[if lte IE 9]>
			<link rel="stylesheet" href="${contextPath}/aceadmin/assets/css/ace-part2.css" class="ace-main-stylesheet" />
		<![endif]-->

<!--[if lte IE 9]>
		  <link rel="stylesheet" href="${contextPath}/aceadmin/assets/css/ace-ie.css" />
		<![endif]-->

<!-- inline styles related to this page -->

<!-- ace settings handler -->
<script src="${contextPath}/aceadmin/assets/js/ace-extra.js"></script>

<!-- HTML5shiv and Respond.js for IE8 to support HTML5 elements and media queries -->

<!--[if lte IE 8]>
		<script src="${contextPath}/aceadmin/assets/js/html5shiv.js"></script>
		<script src="${contextPath}/aceadmin/assets/js/respond.js"></script>
		<![endif]-->
</head>

<body class="no-skin">
<div class="main-container" id="main-container">
	<div class="page-content">

		<div class="row">
			<div class="col-xs-12">
				<!-- PAGE CONTENT BEGINS -->

				<!-- #section:pages/error -->
				<div class="error-container">
					<div class="well">
						<h1 class="grey lighter smaller">
							<span class="blue bigger-125"> <i
								class="ace-icon fa fa-random"></i> 500
							</span> 服务器内部错误！！
						</h1>

						<hr />
						<h3 class="lighter smaller">
							请通知管理员 <i
								class="ace-icon fa fa-wrench icon-animated-wrench bigger-125"></i>
						</h3>

						<div class="space"></div>

						

						<hr />
						<div class="space"></div>

						<div class="center">
							<a href="javascript:history.back()" class="btn btn-grey"> <i
								class="ace-icon fa fa-arrow-left"></i> 后退
							</a> 
						</div>
					</div>
				</div>

				<!-- /section:pages/error -->

				<!-- PAGE CONTENT ENDS -->
			</div>
			<!-- /.col -->
		</div>
		<!-- /.row -->
	</div>
	<!-- /.page-content -->
</div>


	<!--[if !IE]> -->
	<script type="text/javascript">
			window.jQuery || document.write("<script src='${contextPath}/aceadmin/assets/js/jquery.js'>"+"<"+"/script>");
		</script>

	<!-- <![endif]-->

	<!--[if IE]>
<script type="text/javascript">
 window.jQuery || document.write("<script src='${contextPath}/aceadmin/assets/js/jquery1x.js'>"+"<"+"/script>");
</script>
<![endif]-->
	<script type="text/javascript">
			if('ontouchstart' in document.documentElement) document.write("<script src='${contextPath}/aceadmin/assets/js/jquery.mobile.custom.js'>"+"<"+"/script>");
		</script>
	<script src="${contextPath}/aceadmin/assets/js/bootstrap.js"></script>


</body>
</html>
