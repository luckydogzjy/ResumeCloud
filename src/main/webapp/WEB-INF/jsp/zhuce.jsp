<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="css/zhuce.css"/>
</head>
<body>
<div id="all">
	
		<form method="post" action="${pageContext.request.contextPath}/register.do">
		<div id="center-zhuce" >
			<span id="center-zhuce-top">新用户注册</span>
				<span id="tishi">${info}</span>	</br> 
				<span >账号</span>
				<input name="userAccount" id="userAccount" type="text"/></br>  
				<span >密码</span>
				<input name="userPassword"  id="password"  type="password"/>  </br>  
				<span >确认密码</span>
				<input name="confirm"  id="confirm"  type="password"/></br>  
				<span >姓名</span>
				<input name="userName" id="userName" type="text" /></br>  
				<span>性别</span>
				<input name="userSex" id="userSex" type="text" /></br>  
				<span >年龄</span>
				<input name="userAge" id="userAge" type="text" /></br>  
				<span id="tishi">${info}</span>	</br> 
				<input type="submit" value="立即免费注册" onclick=" return zhuce()" />
		</div>
	</form>
</div>
	
	
</body>
</html>