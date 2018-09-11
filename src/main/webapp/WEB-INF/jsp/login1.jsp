<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/login1.css"/>

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
			
			function denglu(){
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
			<img id="headerbox_pic_logo"  src="img/u7.png"/>
			<img id="headerbox_pic_bottom" src="img/u16.png"/>
		</div>
	</header>
	
	<body>
		<div id="body_box">
			<div id="body_left">
				<img id="body_left_pic" src="img/u1758.jpg"/>
			</div>
			
			<div id="body_right">
			<span id="tishi">${info}</span>	</br>
				<form id="body_right_form" action="${pageContext.request.contextPath }/login.do" method="post">
					<input type="text" name="userAccount" id="body_right_form_phoneNum" placeholder="请输入手机号"  onblur="isPhone(this.value)"/>
					<input type="text" name="userPassword" id="body_right_form_pass" placeholder="请输入密码" />
					<input type="submit" id="body_right_form_submit" value="登录" onclick="return denglu()"/>
				</form>
			</div>
		</div>
		
		
	</body>
</html>