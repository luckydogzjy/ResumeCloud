<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

</head>
<link rel="stylesheet" type="text/css" href="css/demo.css"/>
<body>

		<div id="header">
		<jsp:include page="header.jsp" flush="true"/>
		</div>
		
		<div id="right">
			<div id="right-box">
				<form action="${pageContext.request.contextPath }/registe.action" method="post">
					<input type="text" name="userPhone" placeholder="请输入手机号"><br>
					<input type="password" name="userPassword" placeholder="请输入密码"><br>
					<input type="submit" value="注册" >
				</form>

			<%-- <a href="${pageContext.request.contextPath }/login1.jsp">去登录</a> --%>
				<a href="${pageContext.request.contextPath }/login1.jsp">去登录</a>
				
			</div>
		</div>

</body>
</html>