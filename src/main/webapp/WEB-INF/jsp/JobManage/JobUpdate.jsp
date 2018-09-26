<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<link rel="stylesheet" type="text/css" href="css/demo.css"/>
		<link rel="stylesheet" type="text/css" href="css/jobCss/add.css">
		<script type="text/javascript" src="js/jquery-2.1.1.js"></script>
		<script type="text/javascript">
		function backout(){
			location.href="${pageContext.request.contextPath}/JobManage.do"
		}
		
	
		</script>
	</head>
	
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
				<h1>修改职位</h1>
				<hr>
					<form action="${pageContext.request.contextPath}/jobUpdate.do" method="POST">
				<div id="right-box-east">
					<div id="jobName">
						<font id="" >职位名称:</font><input type="text" value="${job.JOB_NAME}" name="name" disabled="true"><br>
					</div>
					<div id="jobCount">
						<font id="" >职位数量:</font><input type="number"  value="${job.JOB_COUNT}" name="count" min="0"><br>
					</div>
					<div id="jobSalary">
						<font id="" >职位薪资:</font><input type="number" value="${job.JOB_SALARY}" name="salary" min="0"><br>
					</div>
					<div id="jobDate">
						<font id="" >截止时间:</font><input  type="date" name="endTime" value="<fmt:formatDate value="${job.JOB_END_TIME}" pattern="yyyy-MM-dd" />"><br>
						
					</div>
					
				</div>
				<div id="right-box-west">
					<div id="jobIntroduciton">
						<div id="jobItro">
							<font>职位介绍:</font>
						</div>
						<textarea name="introduction" >${job.JOB_INTRODUCTION}</textarea><br>
					</div>
					<div id="jobCondition">
						<div  id="jobCod" >
							<font>任职要求:</font>
						</div>
						<textarea name="condition" >${job.JOB_CONDITION}</textarea><br>
					</div>
					
				</div>
				
				<div id="btn">
					<input type="hidden" name="jobid" value="${job.JOB_ID}"/>
					<input id="submit" type="submit" value="提交">
					<input id="cancel" type="button" value="取消" onclick="backout()" />
				</div>
			</form>
			</div>
		</div>
	</body>
</html>