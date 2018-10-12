<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>登陆界面</title>
		<link rel="stylesheet" type="text/css" href="css/login1.css" />
		<script type="text/javascript" src="js/jquery-2.1.1.js"></script>

		<script type="text/javascript">
			$(document).ready(function() {
				$("#body_right_form_phoneNum").focus(function() {
					$("#phoneIsEmpty").text("");
					$("#alert").text("");
				});
				$("#body_right_form_pass").focus(function() {
					$("#passIsEmpty").text("");
					$("#alert").text("");
				});
			});

			function checkForm() {
				var myreg = /^(((13[0-9]{1})|(14[0-9]{1})|(17[0]{1})|(15[0-3]{1})|(15[5-9]{1})|(18[0-9]{1}))+\d{8})$/;
				/* 		var patrn=/^(\w){6,20}$/; 
				 */
				if($("#body_right_form_phoneNum").val().length == 0) {
					$("#phoneIsEmpty").text("× 手机号不能为空");
					$("#phoneIsEmpty").focus();
					return false;
				} else if($("#body_right_form_phoneNum").val().length != 11 || !myreg.test($("#body_right_form_phoneNum").val())) {
					$("#phoneIsEmpty").text("× 请输入有效的手机号码");
					return false;
				} else if($("#body_right_form_pass").val().length == 0) {
					$("#passIsEmpty").text("× 密码不能为空");
					$("#passIsEmpty").focus();
					return false;
				} else {
					return true;
				}
			}
		</script>
	</head>
	<header>
		<div id="header_box">
			<img id="headerbox_pic_logo" src="img/u7.png" />
			<img id="headerbox_pic_bottom" src="img/u16.png" />
		</div>
	</header>

	<body>
		<div id="body_box">
			<div id="body_left">
				<img id="body_left_pic" src="img/u1758.jpg" />
			</div>

			<div id="body_right">
				<form id="body_right_form" action="${pageContext.request.contextPath }/login.action" method="post" onsubmit="return checkForm()">
					<input type="text" name="userPhone" id="body_right_form_phoneNum" placeholder="请输入手机号" />
					<span id="phoneIsEmpty"></span>
					<input type="password" name="userPassword" id="body_right_form_pass" placeholder="请输入密码" />
					<span id="passIsEmpty"></span>
					<input id="body_right_form_submit" value="登录" type="submit" /><br>
					<span id="alert" style="color:red">${msg}</span><br>
				</form>
				<br><br>
			</div>
			<div id="goregisteDiv">
				<a id="goregisteA" href="${pageContext.request.contextPath}/goRegiste.action">免费注册</a>
				<a id="gologin2A" href="${pageContext.request.contextPath}/gologin2.action">短信验证码登陆</a>
			</div>
		</div>

	</body>

</html>