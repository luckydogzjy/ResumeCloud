<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- 引入格式化日期标签  -->
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/demo.css"/>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/interviewCss/fullcalendar.css">
		<style type="text/css">
			#calendar{width:960px;height:550px; margin:20px auto 10px auto}
			body{font-size:12px;}
			a{color:#666;text-decoration:none;}
		</style>
		<script src='${pageContext.request.contextPath}/js/interviewJs/jquery-1.9.1.min.js'></script>
		<script src='${pageContext.request.contextPath}/js/interviewJs/jquery-ui-1.10.2.custom.min.js'></script>
		<script src='${pageContext.request.contextPath}/js/interviewJs/fullcalendar.min.js'></script>
		<script type="text/javascript">
			$(function() {
				
				function formatDate(now) { 	
					var year=now.getFullYear(); 	
					var month=now.getMonth()+1; 	
					var date=now.getDate(); 	
					var hour=now.getHours(); 	
					var minute=now.getMinutes(); 	
					var second=now.getSeconds(); 	
					return year+"-"+month+"-"+date+" "+hour+":"+minute+":"+second; 
				} 
							
				var date = new Date();
				var d = date.getDate();
				var m = date.getMonth();
				var y = date.getFullYear();
				$.get("http://localhost:8080/ResumeCloud/Interview/selectAllInterviews.do",function(data){
					console.log(data[0])
					$('#calendar').fullCalendar({
						header: {
							left: 'prev,next today',
							center: 'title',
							right: 'month,agendaWeek,agendaDay'
						},
						
						firstDay:1,
						editable: true,
						timeFormat: 'H:mm',
						axisFormat: 'H:mm',
						events:data					 
					});
					
				})
				
				
			});
		</script>
	</head>

	
	<body>
		<div id="box">
			<div id="header">
			<jsp:include page="../common/header.jsp" flush="true"/>
			</div>
			<div id="lefter" style="height:820px;background-color:#0099CB">
			<jsp:include page="../common/lefter.jsp" flush="true"/>
			</div>
			
			
			<div id="right" style="height:820px">
				<div id="right-box" style="height:780px">
					<div id="main" style="width:1060px">
					<div id='calendar'></div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>
