<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
		<form action="${pageContext.request.contextPath}/jobUpdate.do" method="POST">
			
			职位名称:<input type="text"  name="name" value="${job.JOB_NAME}" disabled="true"><br>
			职位数量:<input type="number" name="count" min="0" value="${job.JOB_COUNT}"><br>
			职位薪资:<input type="number" name="salary" min="0" value="${job.JOB_SALARY}"><br>
			截止时间:<input type="date" name="endTime" value="<fmt:formatDate value="${job.JOB_END_TIME}" pattern="yyyy-MM-dd" />"><br>
			职位介绍:<textarea name="introduciton" >${job.JOB_INTRODUCTION}</textarea><br>
			任职要求:<textarea name="condition" >${job.JOB_CONDITION}</textarea><br>
			<input type="hidden" name="id" value="${job.JOB_ID}"/>
			<input type="submit" value="提交">
		
		</form>
</body>
</html>