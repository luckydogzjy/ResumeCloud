<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>resumeDisplay显示全部信息</title>
</head>
<body>
	<table border="1" >
		<thead>
			<tr>
				<th>姓名</th>
				<th>性别</th>
				<th>电话</th>
				<th>工作年限</th>
				<th>毕业院校</th>
				<th>学历</th>
				<th>求职意向</th>
				<th>录入时间</th>
				<th>安排情况</th>
				<th>操作</th>				
			</tr>
		</thead>
		
		<tbody>
			<c:forEach items="${resumeList }" var="resume">
			
			<tr>
				<td><a href="#" >${resume.resumeName }</a></td>
				<td>${resume.resumeSex }</td>
				<td>${resume.resumePhone }</td>
				<td>${resume.resumeWorkYears }</td>
				<td>${resume.resumeGraduateInstitution }</td>
				<td>${resume.resumeEducation }</td>
				<td>${resume.resumeJobIntension }</td>
				<td>${resume.resumeCreateTime }</td>
				<td></td>
				<td></td>
			</tr>
				
			</c:forEach>
		</tbody>
	</table>
</body>
</html>