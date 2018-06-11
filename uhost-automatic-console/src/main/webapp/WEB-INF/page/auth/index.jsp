<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html lang="en">
	<head>
		<title>Uhost自动升级及老化测试系统</title>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta charset="utf-8" />
		<meta name="description" content="overview &amp; stats" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />

		<!-- bootstrap & fontawesome -->
		<link rel="stylesheet" href="${contextPath}/static/assets/css/bootstrap.css" />
		<link rel="stylesheet" href="${contextPath}/static/assets/css/font-awesome.css" />

		<!-- page specific plugin styles -->

		<!-- text fonts -->
		<link rel="stylesheet" href="${contextPath}/static/assets/css/ace-fonts.css" />
		<!-- ace styles -->
		<link rel="stylesheet" href="${contextPath}/static/assets/css/ace.css" class="ace-main-stylesheet" id="main-ace-style" />
		<!--[if lte IE 9]>
			<link rel="stylesheet" href="${contextPath}/static/assets/css/ace-part2.css" class="ace-main-stylesheet" />
		<![endif]-->
		<!--[if lte IE 9]>
		  <link rel="stylesheet" href="${contextPath}/static/assets/css/ace-ie.css" />
		<![endif]-->

		<!-- basic scripts -->

		<!--[if !IE]> -->
		<script src="${contextPath}/static/assets/plugin/jquery.js"></script>
		<!-- <![endif]-->

		<!--[if IE]>
		<script type="text/javascript">
		 	window.jQuery || document.write("<script src='${contextPath}/static/assets/plugin/jquery1x.js'><\/script>");
		</script>
		<![endif]-->
		<script type="text/javascript">
			if('ontouchstart' in document.documentElement) document.write("<script src='${contextPath}/static/assets/plugin/jquery.mobile.custom.js'>"+"<"+"/script>");
		</script>
		<script src="${contextPath}/static/assets/plugin/bootstrap.js"></script>

		<!-- page specific plugin scripts -->

		<!--[if lte IE 8]>
		  <script src="${contextPath}/static/assets/plugin/excanvas.js"></script>
		<![endif]-->
		
		<script src="${contextPath}/static/assets/plugin/jquery-ui.custom.js"></script>
		<script src="${contextPath}/static/assets/plugin/jquery.ui.touch-punch.js"></script>
		<!-- ace scripts -->
		<script src="${contextPath}/static/assets/plugin/ace/elements.scroller.js"></script>
		<script src="${contextPath}/static/assets/plugin/ace/ace.js"></script>
		<script src="${contextPath}/static/assets/plugin/ace/ace.sidebar.js"></script>
		<script src="${contextPath}/static/assets/plugin/ace/ace.sidebar-scroll-1.js"></script>
		<script src="${contextPath}/static/assets/plugin/ace/ace.submenu-hover.js"></script>
		
		<!-- inline styles related to this page -->

		<!-- ace settings handler -->
		<script src="${contextPath}/static/assets/plugin/ace-extra.js"></script>

		<!-- HTML5shiv and Respond.js for IE8 to support HTML5 elements and media queries -->

		<!--[if lte IE 8]>
		<script src="${contextPath}/static/assets/plugin/html5shiv.js"></script>
		<script src="${contextPath}/static/assets/plugin/respond.js"></script>
		<![endif]-->
	</head>

	<body class="no-skin">
		<!-- #section:basics/navbar.layout -->
		<div id="navbar" class="navbar navbar-default">

			<div class="navbar-container" id="navbar-container">
				<!-- #section:basics/sidebar.mobile.toggle -->
				<button type="button" class="navbar-toggle menu-toggler pull-left" id="menu-toggler" data-target="#sidebar">
					<span class="sr-only">Toggle sidebar</span>

					<span class="icon-bar"></span>

					<span class="icon-bar"></span>

					<span class="icon-bar"></span>
				</button>

				<!-- /section:basics/sidebar.mobile.toggle -->
				<div class="navbar-header pull-left">
					<!-- #section:basics/navbar.layout.brand -->
					<a href="#" class="navbar-brand">
						<small>Uhost自动升级及老化测试系统</small>
					</a>

					<!-- /section:basics/navbar.layout.brand -->
				</div>

				<!-- #section:basics/navbar.dropdown -->
				<div class="navbar-buttons navbar-header pull-right" role="navigation">
					<ul class="nav ace-nav">
						<!-- #section:basics/navbar.user_menu -->
						<c:if test="${othersystems!=null && fn:length(othersystems) > 0}">
							<li class="light-blue">
							<a data-toggle="dropdown" class="dropdown-toggle" href="#">
								<i class="ace-icon fa fa-exchange icon-animated-vertical"></i>
								<span class="msg-title">切换系统</span>
							</a>

							<ul class="dropdown-menu-right dropdown-navbar dropdown-menu dropdown-caret dropdown-close">
								<li class="dropdown-header">
									<i class="ace-icon glyphicon glyphicon-user"></i>
									当前登录账号拥有权限的系统
								</li>

								<li class="dropdown-content">
									<ul class="dropdown-menu dropdown-navbar">
										
										<c:forEach items="${othersystems}" var="item">
											<li>
											<a href="${item.systemUrl}" class="clearfix">
												<span >
													${item.systemName}
												</span>
											</a>
										</li>
										
										</c:forEach>

									</ul>
								</li>

								<li class="dropdown-footer">
									<!-- <a href="#">
										See all messages
										<i class="ace-icon fa fa-arrow-right"></i>
									</a> -->
								</li>
							</ul>
						</li>
						
						</c:if>
						
						<li class="light-blue">
							<a data-toggle="dropdown" href="#" class="dropdown-toggle">
								<img class="nav-user-photo" src="${contextPath}/static/assets/avatars/avatar5.png"  />
								<span class="user-info">
									<small>Welcome,</small>
									${user}
								</span>

								<i class="ace-icon fa fa-caret-down"></i>
							</a>

							<ul class="user-menu dropdown-menu-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
								<!-- <li>
									<a href="#"><i class="ace-icon fa fa-cog"></i>设置</a>
								</li>
								<li>
									<a href="#"><i class="ace-icon fa fa-user"></i>帐号信息</a>
								</li> -->
								<li class="divider"></li>
								<li>
									<a href="/logout"><i class="ace-icon fa fa-power-off"></i>退出</a>
								</li>
							</ul>
						</li>

						<!-- /section:basics/navbar.user_menu -->
					</ul>
				</div>

				<!-- /section:basics/navbar.dropdown -->
			</div><!-- /.navbar-container -->
		</div>

		<!-- /section:basics/navbar.layout -->

			<!-- #section:basics/sidebar -->
			<div id="sidebar" class="sidebar responsive">
				<script type="text/javascript">
					try{ace.settings.check('sidebar' , 'fixed')}catch(e){}
				</script>
				
				<ul class="nav nav-list" id="mainNav">
					<c:forEach items="${menus}" var="menu">
					<li >
				  		<a href="#" class="dropdown-toggle" >
							<i class="${menu.iconClass == null ? 'menu-icon fa fa-pencil-square-o' : menu.iconClass}"></i>
							<span class="menu-text">${menu.name}</span>
							<b class="arrow fa fa-angle-down"></b>
					  	</a>
					  	<b class="arrow"></b>
					  	<ul class="submenu">
							<c:forEach items="${menu.subitems}" var="sub">
								<c:choose>
									<c:when test="${not empty sub.url}">
										<li id="z${sub.code }">
											<a target="mainFrame" href='${contextPath}${sub.url}' onclick="menuActive(this);"> 
												<i class="menu-icon fa fa-caret-right"></i>${sub.name }
											</a>
										</li>
									</c:when>
									<c:otherwise>
										<li><a href="javascript:void(0);"><i class="icon-double-angle-right"></i>${sub.name }</a></li>
									</c:otherwise>
								</c:choose>
							</c:forEach>
			  			</ul>
					</li>
					</c:forEach>
				</ul><!-- /.nav-list -->

				<!-- #section:basics/sidebar.layout.minimize -->
				<div class="sidebar-toggle sidebar-collapse" id="sidebar-collapse">
					<i class="ace-icon fa fa-angle-double-left" data-icon1="ace-icon fa fa-angle-double-left" data-icon2="ace-icon fa fa-angle-double-right"></i>
				</div>
				 <script type="text/javascript">
					try {
						ace.settings.check('sidebar', 'collapsed')
					} catch (e) {
					}
				</script>
			</div>

			<!-- /section:basics/sidebar -->
			<div class="main-content">
				<div class="main-content-inner">
			 		<iframe name="mainFrame" id="mainFrame" frameborder="0" marginheight="0" height="100%" width="100%" style="margin-bottom:-5px" onload="cmainFrame();"  src="${contextPath}/welcome.jsp"></iframe>
				</div>
			</div><!-- /.main-content -->

			 <a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
				<i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
			</a> 
		<!-- /.main-container -->

		
		<script type="text/javascript">
			 function cmainFrame(){
				var hmain = window.innerHeight - $("#navbar").height() - 1;
				$("#mainFrame").height(hmain);
			} 
			 
			$(function(){
				window.onresize = function(){
					//console.log(window.document.body.scrollHeight, window.document.body.clientHeight, window.document.body.offsetHeight);
					cmainFrame();
				}
				
				//应策划的需求，首页默认选中菜单的第一条页面显示
				/* var firstNav = $("#mainNav > li:first-child").addClass("open active").find("ul").show().find("li:first-child").addClass("active").find("a");
				$("#mainFrame").attr("src", firstNav.attr("href")); */
			}) 
			
			function menuActive(item){
				var new_active = $(item).parent();
				//remove ".active" class from all (previously) ".active" elements
				$('.nav-list li.active').removeClass('active');
				//add ".active" class to our newly selected item and all its parent "LI" elements
				new_active.addClass('active').parents('.nav-list li').addClass('active');
			}
		</script>
	</body>
</html>
