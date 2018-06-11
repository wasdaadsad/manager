<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html lang="zh-cn">
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta charset="utf-8" />
		<title>vivo统一后台模板</title>

		<meta name="description" content="vivo统一后台模板" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />

		<!-- bootstrap & fontawesome -->
		<link rel="stylesheet" href="${contextPath}/static/dist/css/bootstrap.min.css" />
		<link rel="stylesheet" href="${contextPath}/static/dist/css/font-awesome.min.css" />

		<!-- text fonts -->
		<link rel="stylesheet" href="${contextPath}/static/dist/css/ace-fonts.min.css" />

		<!-- ace styles -->
		<link rel="stylesheet" href="${contextPath}/static/dist/css/ace.min.css" />

		<!--[if lte IE 9]>
			<link rel="stylesheet" href="${contextPath}/static/dist/css/ace-part2.min.css" />
		<![endif]-->
		<link rel="stylesheet" href="${contextPath}/static/dist/css/ace-rtl.min.css" />

		<!--[if lte IE 9]>
		  <link rel="stylesheet" href="${contextPath}/static/dist/css/ace-ie.min.css" />
		<![endif]-->

		<!-- HTML5 shim and Respond.min.js IE8 support of HTML5 elements and media queries -->
		<!--[if lt IE 9]>
		<script src="${contextPath}/static/dist/js/html5shiv.min.js"></script>
		<script src="${contextPath}/static/dist/js/respond.min.js"></script>
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
											
											<h4 class="header red lighter bigger center">
												检测到使用默认密码登录，请先修改密码！
											</h4>

											<div class="space-6"></div>

											<form id="login"  method="POST" action="modifyuser">
												<fieldset>
													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input id="username" name="username" type="text" class="form-control" placeholder="帐号" data-rel="tooltip" data-placement="right" />
															<i class="ace-icon fa fa-user"></i>
														</span>
													</label>
													
													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input id="oldPassword" name="oldPassword" type="password" class="form-control" placeholder="旧密码" />
															<i class="ace-icon fa fa-lock"></i>
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
															<input id="repassword" name="repassword" type="password" class="form-control" placeholder="确认密码" />
															<i class="ace-icon fa fa-retweet"></i>
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
															<i class="ace-icon fa fa-check"></i>
															<span class="bigger-110">提交</span>
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
			window.jQuery || document.write("<script src='${contextPath}/static/dist/js/jquery.min.js'>"+"<"+"/script>");
		</script>
		<!-- <![endif]-->

		<!--[if IE]>
		<script type="text/javascript">
 			window.jQuery || document.write("<script src='${contextPath}/static/dist/js/jquery1x.min.js'>"+"<"+"/script>");
		</script>
		<![endif]-->
		
		<script type="text/javascript">
			if('ontouchstart' in document.documentElement) document.write("<script src='${contextPath}/static/dist/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
		</script>
		
		<script type="text/javascript" src="${contextPath}/static/dist/js/jquery.validate.min.js"></script>
		
		<script type="text/javascript">
		jQuery(function($) {
			jQuery.validator.addMethod("regexPassword", function(value, element) {
    		    return this.optional(element) || /^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[^a-zA-Z0-9]).{6,20}.*$/.test(value);
    		}, "密码必须6-20位,包含数字、字母和特殊字符");
			
			$("#login").validate({
			rules: {
			  username: {
			    required: true,
			   },
			   oldPassword: {
				    required: true
				   },
			   password: {
				regexPassword:true,
				required: true,
			   },
			   repassword: {
				   regexPassword:true,
				   required: true,
				    equalTo: "#password"
				   }
			  },
			messages: {
			   username: {
				    required: "请输入用户名"
				   },
					password: {
					
					required: "请输入密码",
			   },
				   	oldPassword: {
					    required: "请输入旧密码"
					   },
					   
				   	repassword: {
				   		required: "请输入确认密码",
					    equalTo: "两次输入密码不一致"
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
