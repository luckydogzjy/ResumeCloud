<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="../../../css/login1.css"/>

<script type="text/javascript">

			function isPhone(username){
			    //alert(phone)
			    var RegCellPhone = /^(1)([0-9]{10})?$/;
			    var  falg=username.search(RegCellPhone);
			    if (falg==-1){
			        window.alert("手机号不正确");
			        this.focus();
			    }
			}
			
			function login(){
				var username = document.getElementById("body_right_form_phoneNum").value;
				var password = document.getElementById("body_right_form_pass").value;
				console.log(username);
				console.log(password);
				if(username==null||username==""){
					window.alert("请输入账号！");
					return false;  
				}else if(password==null||password==""){
					window.alert("请输入密码！");
					return false;  
				} 
				return true;  
			}  

		</script>
</head>

	<header>
		<div id="header_box">
			<img id="headerbox_pic_logo"  src="../../../img/u7.png"/>
			<img id="headerbox_pic_bottom" src="../../../img/u16.png"/>
		</div>
	</header>
	
	<body>
			
			<div id="body_right">
<%-- 			<span id="tishi">${info}</span>	</br>
 --%>				<form id="body_right_form" action="${pageContext.request.contextPath }/login.action" method="post">
					<input type="text" name="userPhone" id="body_right_form_phoneNum" placeholder="请输入手机号"  onblur="isPhone(this.value)"/><br>
					<label class="err err-top40" id="login_phone_text">${msg}</label><br>					
					<input type="text" name="userPassword" id="body_right_form_pass" placeholder="请输入密码" />
					<input type="submit" id="body_right_form_submit" value="登录" onclick="return login()"/>
				</form>
				<div>${lastId}</div>
<%-- 		<a href="${pageContext.request.contextPath }/registe.jsp">去注册</a> --%>		
			<a href="registe.jsp">去注册</a>
				
				
			</div>
		</div>	
		
	</body>
</html>