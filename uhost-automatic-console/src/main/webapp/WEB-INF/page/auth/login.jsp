<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html lang="zh-cn">
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta charset="utf-8" />
		<title>vivo后台</title>

		<meta name="description" content="vivo comm2后台" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />

		<!-- bootstrap & fontawesome -->
		<link rel="stylesheet" href="${contextPath}/static/assets/css/bootstrap.css" />
		<link rel="stylesheet" href="${contextPath}/static/assets/css/font-awesome.css" />

		<!-- text fonts -->
		<link rel="stylesheet" href="${contextPath}/static/assets/css/ace-fonts.css" />

		<!-- ace styles -->
		<link rel="stylesheet" href="${contextPath}/static/assets/css/ace.css" />

		<!--[if lte IE 9]>
			<link rel="stylesheet" href="${contextPath}/static/assets/css/ace-part2.css" />
		<![endif]-->
		<link rel="stylesheet" href="${contextPath}/static/assets/css/ace-rtl.css" />

		<!--[if lte IE 9]>
		  <link rel="stylesheet" href="${contextPath}/static/assets/css/ace-ie.css" />
		<![endif]-->

		<!-- HTML5 shim and Respond.min.js IE8 support of HTML5 elements and media queries -->
		<!--[if lt IE 9]>
		<script src="${contextPath}/static/assets/js/html5shiv.js"></script>
		<script src="${contextPath}/static/assets/js/respond.js"></script>
		<![endif]-->
	</head>

	<body class="login-layout light-login">
		<div class="main-container">
			<div class="main-content">
				<div class="row">
					<div class="col-sm-10 col-sm-offset-1">
						<div class="login-container">
							 
							<div class="center">
								<h1>
									<span class="red">&nbsp;</span>
								</h1>
							</div>

							<div class="space-6"></div>

							<div class="position-relative">
								<div id="login-box" class="login-box visible widget-box no-border">
									<div class="widget-body">
										<div class="widget-main">
											<div class="center">
												<img src="${contextPath}/static/assets/images/vivo-logo.png" width="130" height="80"/>
											</div>
											
											<h3 class="header blue lighter bigger center">
												vivo后台
											</h3>

											<div class="space-6"></div>

											<form id="login"  method="POST" action="login">
												<fieldset>
													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input id="username" name="username" type="text" class="form-control" placeholder="帐号" data-rel="tooltip" data-placement="right" />
															<i class="ace-icon fa fa-user"></i>
														</span>
													</label>

													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input id="password" name="password" type="password" class="form-control" placeholder="密码" />
															<i class="ace-icon fa fa-lock"></i>
														</span>
													</label>
													
													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<span id="loginTip" style="color:#A94442">${msg}</span>
														</span>
													</label>

													<div class="space"></div>

													<div class="clearfix">
														<!-- <label class="inline">
															<input id="remember-me" name="remember-me" type="checkbox" class="ace" />
															<span class="lbl">下次自动登录</span>
														</label> -->

														<button  type="submit" class="width-35 pull-right btn btn-sm btn-primary">
															<i class="ace-icon fa fa-key"></i>
															<span class="bigger-110">登录</span>
														</button>
													</div>

													<div class="space-4"></div>
												</fieldset>
											</form>

										</div><!-- /.widget-main -->

										<div class="toolbar clearfix">
											<div class="space-6"></div>
										</div>
									</div><!-- /.widget-body -->
								</div><!-- /.login-box -->


							</div><!-- /.position-relative -->
						</div>
					</div><!-- /.col -->
				</div><!-- /.row -->
			</div><!-- /.main-content -->
			<div class="footer">
                <div class="footer-inner">
                    <!-- #section:basics/footer -->
                    <div class="footer-content-nobordertop">
                        <span class="bigger-120">
                            <span class="blue bolder">
                                 vivo互联网软件
                            </span>
                            &copy; 
                            2015-2017
                        </span>
                    </div>
                    <!-- /section:basics/footer -->
                </div>
            </div>
		</div><!-- /.main-container -->

		<!-- basic scripts -->

		<!--[if !IE]> -->
		<script type="text/javascript">
			window.jQuery || document.write("<script src='${contextPath}/static/assets/plugin/jquery.js'>"+"<"+"/script>");
		</script>
		<!-- <![endif]-->

		<!--[if IE]>
		<script type="text/javascript">
 			window.jQuery || document.write("<script src='${contextPath}/static/assets/plugin/jquery1x.js'>"+"<"+"/script>");
		</script>
		<![endif]-->
		
		<script type="text/javascript">
			if('ontouchstart' in document.documentElement) document.write("<script src='${contextPath}/static/dist/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
		</script>
		
		<script type="text/javascript" src="${contextPath}/static/assets/plugin/jqueryValidate/jquery.validate.min.js"></script>
		
		<script type="text/javascript">
		jQuery(function($) {
			 $("#login").validate({
			rules: {
			  username: {
			    required: true,
			   },
			   password: {
			    required: true,
			    minlength: 6
			   }
			  },
			messages: {
			   username: {
			    required: "请输入用户名"
			   },
				password: {
			    required: "请输入密码",
			    minlength: "密码不能小于6个字符"
			   }
			  },
			highlight : function(e) {
					$(e).closest("label").removeClass(
							"has-info").addClass("has-error")
				},
		    success : function(e) {
					$(e).closest("label").removeClass(
							"has-error"), $(e).remove()
				},
			errorElement : "div",
			errorClass : "help-block",
			    });
			});
		</script>
		
	</body>
</html>
