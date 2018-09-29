<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<!-- <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script> -->
		<script type="text/javascript">
			function copy(){
				
				var content = new Array();
				content[0] = document.getElementById("namedtl").innerText;
				content[1] = document.getElementById("salarydtl").innerText;
				content[2] = document.getElementById("intro").innerText;
				content[3] = document.getElementById("con").innerText;
				
			    var oInput = document.createElement('textarea');
			    oInput.value = "职位名称："+content[0]+"\n薪资："+content[1]+"\n职位介绍:"+content[2]+"\n任职要求："+content[3];
			    document.body.appendChild(oInput);
			    oInput.select(); // 选择对象
			    document.execCommand("Copy"); // 执行浏览器复制命令
			    oInput.className = 'oInput';
			    oInput.style.display='none';
	          	alert('复制成功!\n'+oInput.value);
	        					
			}
			
			function backout(){
				location.href="${pageContext.request.contextPath}/JobManage.do"
			}
		</script>
		
		<link rel="stylesheet" type="text/css" href="css/jobCss/details.css"/>
		<link rel="stylesheet" type="text/css" href="css/demo.css"/>
	</head>
	
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<body>
	 <div id="box">
		<div id="header">
		<jsp:include page="../common/header.jsp" flush="true"/>
		</div>
		<div id="lefter">
		<jsp:include page="../common/lefter.jsp" flush="true"/>
		</div>
		
		
		<div id="right">
			<div id="right-box" style="position:relative;">
				
			<div id="details"  style="position: absolute;width:600px;height:500px;top:40%;margin-left:240px; margin-top:-100px;"> 
			
			<!-- <table style="text-align:center"> -->
				<div id="details-left">
					<div id="name">
						<font>职位名称:</font>
						<span id="namedtl">&nbsp;${job.JOB_NAME}</span>
					</div>
					<div id="count">
						<font>职位数量:</font>
						<span>&nbsp;${job.JOB_COUNT}</span>
					</div>
					<div id="status">
						<font>职位状态:</font>
						<span>&nbsp;${job.JOB_STATUS}</span>
					</div>
					<div id="salary">
						<font>薪资:</font>
						<span id="salarydtl">&nbsp;${job.JOB_SALARY}</span>
					</div>
					<div id="create">
						<font>创建时间:</font>
						<span>&nbsp;<fmt:formatDate value="${job.JOB_CREATE_TIME}" pattern="yyyy年MM月dd日" /></span>
					</div>
					<div id="end">
						<font>截止时间:</font>
						<span>&nbsp;<fmt:formatDate value="${job.JOB_END_TIME}" pattern="yyyy年MM月dd日" /></span>
					</div>
				</div>
				<div id="details-right">
					<div id="intr">
						<font>职位介绍:</font>
						<span id="intro">${job.JOB_INTRODUCTION}</span>
					</div >
					<div id="condi">
						<font>任职要求:</font>
						<span id="con">${job.JOB_CONDITION}</span>
					</div>
				</div>
				
			</div>  
			<div style="position: absolute;left:50%;top:90%;margin-left:-140px; margin-top:-30px;">
			<input id="upbtn" onclick="copy()" type="button" value="复制到剪切板">
			<input id="backbtn" onclick="backout()" type="button" value="返回">
			</div>
			
			</div>
		</div>
		</div>
		
	</body>
</html>
