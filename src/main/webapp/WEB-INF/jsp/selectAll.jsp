<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	
	<form method="post" action="${pageContext.request.contextPath }/selectAll.do">
		<div id="select-All">
			<span id="tip">${jobInfo}</span>
			<input name="jobId" id="jobId" type="text" value="${job.jobId}"/>
			<input name="jobUserId" id="jobUserId" type="text" value="${job.jobUserId}"/>
			<input name="jobName" id="jobName" type="text" value="${job.jobName}"/>
			<input name="jobCount" id="jobCount" type="text" value="${job.jobCount}"/>
			<input name="jobSalary" id="jobSalary" type="text" value="${job.jobSalary}"/>
			<input name="jobIntroduction" id="jobIntroduction" type="text" value="${job.jobIntroduction}"/>
			<input name="jobCondition" id="jobCondition" type="text" value="${job.jobCondition}"/>
			<input name="jobStatus" id="jobStatus" type="text" value="${job.jobStatus}"/>
			<input name="jobEndTime" id="jobEndTime" type="text" value="${job.jobEndTime}"/>
			<input name="jobCreateTime" id="jobCreateTime" type="text" value="${job.jobCreateTime}"/>		
		</div>
	</form>
</body>
</html>