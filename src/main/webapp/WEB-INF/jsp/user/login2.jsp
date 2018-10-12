<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>HR注册界面</title>
		<link rel="stylesheet" type="text/css" href="css/login2.css" />
		<style type="text/css">
			#login2Message {
				border: 0px;
				position: absolute;
				top: 305px;
				left: 60px;
				width: 309px;
				height: 20px;
				font-size: 12px;
				color: red;
				border-radius: 3px;
			}
		</style>
		<script type="text/javascript" src="js/jquery-2.1.1.js"></script>
	</head>
	<header>
		<div id="header_box">
			<img id="headerbox_pic_logo" src="img/u7.png" />
			<img id="headerbox_pic_bottom" src="img/u16.png" />
		</div>
	</header>

	<body>
		<script type="text/javascript">
			$(document).ready(function() {
				$("#inputphone").focus(function() {
					$("#IsRegiste").text("");
				});
				$("#code").focus(function() {
					$("#jbPhoneTip").text("");
				});
			});
			var InterValObj; //timer变量，控制时间
			var count = 60; //间隔函数，1秒执行
			var curCount; //当前剩余秒数
			function sendMessage() {
				var myreg = /^(((13[0-9]{1})|(14[0-9]{1})|(17[0]{1})|(15[0-3]{1})|(15[5-9]{1})|(18[0-9]{1}))+\d{8})$/;
				if($("#inputphone").val() == null || !myreg.test($("#inputphone").val())) {
					$("#IsRegiste").text("× 请输入有效的手机号码");
				} else {
					curCount = count;
					// ，开始计时
					$("#btn_send").css('background', '#CFDDEA');
					$("#btn_send").attr("disabled", true);
					$("#btn_send").val(curCount + "后再获取");
					InterValObj = window.setInterval(SetRemainTime, 1000); // 启动计时器timer处理函数，1秒执行一次
					// 向后台发送处理数据
					$.ajax({
						type: "POST", // 用POST方式传输
						dataType: "json", // 数据格式:JSON
						url: "login2.action", // 目标地址
						data: {
							"inputphone": $("#inputphone").val()
						},
						success: function(data) {
							var dat = data.login2message;
							alert(dat);
							if(data.login2message == "1") {
								$("#jbPhoneTip").text("√ 短信验证码已发到您的手机,请查收");
							} else if(data.login2message == "2") {
								$("#IsRegiste").text("× 短信验证码发送失败，请重新发送");
							} else if(data.login2message == "3") {
								$("#jbPhoneTip").text("× 该手机号码还未注册");
							}
						}
					});

				}
			}

			//timer处理函数

			function SetRemainTime() {
				if(curCount == 0) {
					window.clearInterval(InterValObj); // 停止计时器
					$("#btn_send").css('background', '#199ED8');
					$("#btn_send").removeAttr("disabled");
					$("#btn_send").val("重新发送");
				} else {
					curCount--;
					$("#btn_send").val(curCount + "秒后再获取");
					//document.getElementById("btn_send").value=curCount + "秒后再获取";
				}

				function checkForm() {
					if($("#code").val == null || $("#code").val == "") {
						$("#jbPhoneTip").text("× 验证码不能为空！");
						return false;
					} else {
						return true;
					}
				}
			}
		</script>

		<div id="body_box">
			<div id="body_left">
				<img id="body_left_pic" src="img/u1758.jpg" />
			</div>
			<div id="body_right">
				<form id="body_right_form" action="${pageContext.request.contextPath}/comparelogincode.action" method="post" onsubmit="return checkForm()">
					<span id="IsRegiste"></span>
					<input type="text" name="phone" id="inputphone" value="${inputphone}" placeholder="请输入手机号"><br>
					<input type="text" name="code" id="code" placeholder="请输入验证码">
					<input type="button" value="发送验证码" id="btn_send" onclick="sendMessage()"><br>
					<span id="jbPhoneTip"></span>
					<input id="btn_login2" value="验证登录" type="submit"></br>
					<span id="login2Message">${msg}</span>
				</form>
				<br><br>
			</div>
			<div id="goregisteDiv">
				<a id="goregisteA" href="${pageContext.request.contextPath}/goRegiste.action">免费注册</a>
				<a id="gologin1A" href="${pageContext.request.contextPath}/gologin1.action">账号密码登陆</a>
			</div>
		</div>
	</body>

</html>