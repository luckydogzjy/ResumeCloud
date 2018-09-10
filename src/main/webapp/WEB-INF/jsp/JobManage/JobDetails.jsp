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
		<table>
					<tr>
					 <th>职位名称:</th>
					 <td>${job.JOB_NAME}</td>
				    </tr>
				    <tr>
					 <th>职位数量:</th>
					  <td>${job.JOB_COUNT}</td>
				    </tr>
				    <tr>
					 <th>职位状态:</th>
					  <td>${job.JOB_STATUS}</td>
				    </tr>
				    <tr>
					 <th>薪资:</th>
					  <td>${job.JOB_SALARY}</td>
				    </tr>
				    <tr>
					 <th>创建时间:</th>
					  <td><fmt:formatDate value="${job.JOB_CREATE_TIME}" pattern="yyyy年MM月dd日" /></td>
				    </tr>
				    <tr>
					 <th>截止时间:</th>
					  <td><fmt:formatDate value="${job.JOB_END_TIME}" pattern="yyyy年MM月dd日" /></td>
				    </tr>
				    <tr>
					 <th>职位介绍:</th>
					  <td>${job.JOB_INTRODUCTION}</td>
				    </tr>
				    <tr>
					 <th>任职要求:</th>
					  <td>${job.JOB_CONDITION}</td>
				    </tr>
					
				   
				    
				
			
			</table>
</body>
</html>