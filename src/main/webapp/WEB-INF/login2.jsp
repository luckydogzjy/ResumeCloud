<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<form action="${pageContext.request.contextPath}/login.action" method="post">
		手机号：<input type="text" name="userPhone"><br>
		验证码：<input type="password" name="userPassword">
				<input type="button" name="yanzhengma" value="获取验证码"><br>
			<input type="submit" value="登录">
	</form>
	<a href="login.jsp">账号密码登录</a>&nbsp &nbsp<a href="login2.jsp">验证功能登录</a>
	
</body>
</html>