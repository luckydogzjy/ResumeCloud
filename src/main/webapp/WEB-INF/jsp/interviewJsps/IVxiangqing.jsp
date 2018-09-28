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
	</head>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/demo.css"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/interviewCss/IVxiangqing.css"/>
	<body>
		<div id="box">
			<div id="header">
			<jsp:include page="../common/header.jsp" flush="true"/>
			</div>
			<div id="lefter">
			<jsp:include page="../common/lefter.jsp" flush="true"/>
			</div>
			
			
			<div id="right">
				<div id="right-box">
					<div id="right-box-title">
						<h2 id="right-box-font1" class="right-box-title-font">查看</h2>
						<h2 id="right-box-font2">${interviewPojo.resume.resumeName}</h2>
						<h2 id="right-box-font3" class="right-box-title-font">面试安排详情</h2>
						<img id="right-box-img" src="../img/u1426.png"/>
					</div>
					<hr id="right-box-hr1"/>
					<div id="">
						<div id="right-box-text1">
							<p class="right-box-item">求职者姓名:</p>
							<p class="right-box-item">&nbsp;联系方式:</p>
							<p class="right-box-item">&nbsp;面试岗位:</p>
							<p class="right-box-item">&nbsp;面试时间:</p>
							<p class="right-box-item">&nbsp;备注信息:</p>
						</div>
						
						<div id="right-box-text2">
							<p class="right-box-item">${interviewPojo.resume.resumeName}</p>
							<p class="right-box-item">${interviewPojo.resume.resumePhone}</p>
							<p class="right-box-item">${interviewPojo.interviewJob}</p>
							<p class="right-box-item"><fmt:formatDate value="${interviewPojo.interviewTime}" pattern="yyyy-MM-dd HH:mm"/></p>
							<p class="right-box-item">${interviewPojo.interviewInfo}</p>
						</div>
						
						<div id="right-box-text3">
							<p class="right-box-item">联系人:</p>
							<p class="right-box-item">联系人联系方式:</p>
							<p class="right-box-item">面试地点:</p>
						</div>
						
						<div id="right-box-text4">
							<p class="right-box-item">${interviewPojo.interviewAssociateUsername}</p>
							<p class="right-box-item">${interviewPojo.interviewAssociatePhone}</p>
							<p class="right-box-item">${interviewPojo.interviewAddress}</p>
						</div>
						
						<div id="right-box-text5">
							<a href="#">点击查看（新增）本次面试结果记录</a>
						</div>
					</div>
					<hr id="right-box-hr3"/>
					<div id="right-box-button">
						<input id="right-box-button-xiugai" type="button" value="修改" />
						<input id="right-box-button-shanchu" type="button" value="删除" />
					</div>
				</div>
			</div>
		</div>
	</body>
</html>
