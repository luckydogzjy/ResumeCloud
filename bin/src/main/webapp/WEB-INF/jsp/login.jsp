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
		账号：<input type="text" name="userPhone"><br>
		密码：<input type="password" name="userPassword"><br>
			<input type="submit" value="登录">
	</form>
	<a href="/login2.jsp">去登录</a>
	<a href="<%=request.getRealPath("/")%>/login2.jsp">跳转</a><br> 
	
   
	
</body>
</html>