<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
			
	<form method="post" action="${pageContext.request.contextPath }/login.do"> 
		<div id="center-zhuce">
			<span id="tishi">${info}</span>	</br>
			<span id="center-zhuce-top">登陆</span></br>
			<span id="center-zhuce-zhanghao">账号</span>
			<input name="userAccount" id="username" type="text"/></br>
			<span id="center-zhuce-mima">密码</span>
			<input name="userPassword" id="password" type="password"/> </br>	
			<input id="center-denglu" type="submit" value="登陆" onclick="return denglu()" />
		</div>
	</form>
</body>
</html>