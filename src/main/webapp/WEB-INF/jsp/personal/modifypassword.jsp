<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<link rel="stylesheet" type="text/css" href="css/demo.css" />
<title>Insert title here</title>
</head>
<body>

<div id="header">
		<jsp:include page="../common/header.jsp" flush="true" />
	</div>
	<div id="lefter">
		<jsp:include page="../common/lefter.jsp" flush="true" />
	</div>
<div id="right" >
	<p style="color:red">${msg}</p><hr>
<!-- 	<form action="modifypassword" method="post"> -->
	
		<form method="post" action="${pageContext.request.contextPath }/modifypassword.do"> 
		手机号：<input type="text" name="user_phone" /><br>
		密&nbsp码：<input type="password" name="password" /><br>
		新密码：<input type="password" name="new_password" /><br>
		<input type="submit" value="提交"><br>
	</form><hr>		
</div>
</body>
</html>