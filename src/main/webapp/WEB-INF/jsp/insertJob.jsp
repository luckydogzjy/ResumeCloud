<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<form method="post" action="${pageContext.request.contextPath }/insertJob.do">
		<div id="insert-Job">
			<span >jobid</span>
				<input name="jobId" id="jobId" type="text"/>
			<span>jobuserid</span>
				<input name="jobUserId" id="jobUserId" type="text"/>
			<span>jobname</span>
				<input name="jobName" id="jobName" type="text"/>
			<span>jobcount</span>
				<input name="jobCount" id="jobCount" type="text"/>
			<span>jobSalary</span>
				<input name="jobSalary" id="jobSalary" type="text"/>
			<span>jobIntroduction</span>
				<input name="jobIntroduction" id="jobIntroduction" type="text"/>
			<span>jobCondition</span>
				<input name="jobCondition" id="jobCondition" type="text"/>
			<span>jobStatus</span>
				<input name="jobStatus" id="jobStatus" type="text"/>
			<span>jobCreateTime</span>
				<input name="jobCreateTime" id="jobCreateTime" type="text"/>
			<input type="submit" value="添加" onclick=" return insertJob()" />
		</div>
	</form>
</body>
</html>