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
				content[0] = document.getElementById("name").innerText;
				content[1] = document.getElementById("salary").innerText;
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
		</script>
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
				  
			<table>
					<tr>
					 <th>职位名称:</th>
					 <td id="name">${job.JOB_NAME}</td>
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
					  <td id="salary">${job.JOB_SALARY}</td>
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
					  <td id="intro">${job.JOB_INTRODUCTION}</td>
				    </tr>
				    <tr>
					 <th>任职要求:</th>
					  <td id="con">${job.JOB_CONDITION}</td>
				    </tr>
					
			
			</table>
			<div>
			<input onclick="copy()" type="button" value="复制到剪切板">
			</div>
			</div>
		</div>
		
	</body>
</html>
