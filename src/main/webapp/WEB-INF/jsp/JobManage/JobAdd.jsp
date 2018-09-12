<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
	</head>
	<link rel="stylesheet" type="text/css" href="css/demo.css"/>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<body>
		<div id="header">
		<jsp:include page="header.jsp" flush="true"/>
		</div>
		<div id="lefter">
		<jsp:include page="lefter.jsp" flush="true"/>
		</div>
		
		
		<div id="right">
			<div id="right-box">
				<form action="${pageContext.request.contextPath}/jobAdd.do" method="POST">
			
			职位名称:<input type="text"  name="name"><br>
			职位数量:<input type="number" name="count" min="0"><br>
			职位薪资:<input type="number" name="salary" min="0"><br>
			截止时间:<input type="date" name="endTime" ><br>
			职位介绍:<textarea name="introduciton" ></textarea><br>
			任职要求:<textarea name="condition" ></textarea><br>
			<input type="submit" value="提交">
		
		</form>
			</div>
		</div>
	</body>
</html>
