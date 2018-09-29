<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- 引入格式化日期标签  -->
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>Insert title here</title>
	<link rel="stylesheet" type="text/css" href="../css/demo.css" />

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>显示interviews</title>
		<link rel="stylesheet" type="text/css" href="../css/interviewCss/selectinterview.css" />
		<style type="text/css">
			.msg{
				
				width: 790px;
				height: 360px;
				left: 136px;
				top: 106px;
				overflow:scroll; 
				position: absolute;
				
			}
		</style>
	</head>
	<script src="../My97DatePicker/WdatePicker.js" type="text/javascript"></script>
	<script src="../My97DatePicker/WdatePicker.js" type="text/javascript"></script>
	<script src="../js/jquery-2.1.1.js" type="text/javascript" charset="utf-8"></script>
	
	<body>
		<div id="box">
			<div id="header">
				<jsp:include page="../common/header.jsp" flush="true" />
			</div>
			<div id="lefter">
				<jsp:include page="../common/lefter.jsp" flush="true" />
			</div>

			<div id="right">
				<div id="right-box">
					<div id="right-box-title">
							<h2 id="right-box-font1" class="right-box-title-font">	${resumeInterviews.resumeName}的面试记录如下</h2>
							<hr id="right-box-hr1" />
					</div>
					
					<div class="msg">
						<c:forEach items="${resumeInterviews.interviews}" var="interview">
						面试职位：
						<font size="2">${interview.interviewJob}</font> <br />
						面试地点：
						<font size="2">${interview.interviewAddress}</font> <br />
						面试结果：
						<font size="2">
							<c:if test="${interview.interviewStatus==1}">成功 </c:if>
							<c:if test="${interview.interviewStatus==2}">待面试 </c:if>
							<c:if test="${interview.interviewStatus==3}">失败</c:if>
						</font> <br />
						面试结果记录：
						<font size="2">${interview.interviewRecodeInfo}</font> <br />
						面试时间：
						<fmt:formatDate value="${interview.interviewTime}" pattern="yyyy年MM月dd日 HH:mm" /> <br />
						<input type="button" name="" id="" value="修改" /> <br />
						-------------------------------------------------------- <br />
					</c:forEach>
					</div>
		
					
				</div>
			</div>
		</div>
	</body>

</html>