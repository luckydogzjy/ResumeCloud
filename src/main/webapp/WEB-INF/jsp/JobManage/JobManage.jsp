<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
	<head>
		<meta charset="UTF-8">
		<title>JobManage</title>
		
		<link rel="stylesheet" type="text/css" href="css/demo.css"/>
		<link rel="stylesheet" type="text/css" href="css/jobCss/job.css">
		
		<style type="text/css" >
		
		a{
		 	text-decoration:none; 
		  	color:#333
		  	}
	
		
		</style>
		
		
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

					<div id="job-search">
						<img id="job-search-img" src="${pageContext.request.contextPath}/img/u607.png" />
						<form id="search" action="${pageContext.request.contextPath}/JobManage.do" method="POST">
							<input id="job-search-input" type="text" name="search" value="${search}"/>
							<input id="job-search-button" type="submit" value="搜索" />
						</form>
		
					<div id="job-add">
						<a id="job-add-button" href="${pageContext.request.contextPath}/jobAddView.do">添加职位</a>
						<img id="job-add-img" src="${pageContext.request.contextPath}/img/u603.png" />
					</div>
					</div>

		
		<div id="job-table">
				<table id="job-table-list" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<th>职位名称</th>
						<th>职位数量</th>
						<th>截止时间</th>
						<th>状态</th>
						<th>操作</th>
					</tr>
					
					<c:forEach items="${job}" var="job">
					<tr>
						<td><a style="text-decoration:none;color:blue" href="${pageContext.request.contextPath}/jobDetails.do?jobId=${job.JOB_ID}">${job.JOB_NAME}</a></td>
						<td>${job.JOB_COUNT}</td>
						<td><fmt:formatDate value="${job.JOB_END_TIME}" pattern="yyyy年MM月dd日" /></td>

						<td>
							<c:if test="${job.JOB_STATUS==1}">
							<a id="button-status1" href="${pageContext.request.contextPath}/jobChangeStatus.do?jobId=${job.JOB_ID}&jobStatus=${job.JOB_STATUS}">开启</a>
							</c:if>
							<c:if test="${job.JOB_STATUS==0}">
							<a id="button-status0" href="${pageContext.request.contextPath}/jobChangeStatus.do?jobId=${job.JOB_ID}&jobStatus=${job.JOB_STATUS}">关闭</a>
							</c:if>
							
						</td>
						<td>
						 	<a id="button-modify" href="${pageContext.request.contextPath}/jobUpdateView.do?jobId=${job.JOB_ID}">修改</a>
						 	<a id="button-delete" href="${pageContext.request.contextPath}/jobDelete.do?jobId=${job.JOB_ID}">删除</a>
						 	<a id="button-template" href="${pageContext.request.contextPath}/jobTemplateView.do?jobId=${job.JOB_ID}">生成模板</a>
						</td>
					</tr>	
					</c:forEach>
					
				</table>
		
		</div>
		
		<div id="page">
				<span>当前第${page.pageNum}页，一共${page.pages}页</span>
				<span>
					<a href="${pageContext.request.contextPath}/JobManage.do?page=${page.firstPage}">首页</a>
			        <a href="${pageContext.request.contextPath}/JobManage.do?page=${page.prePage}">上一页</a>
			        <a href="${pageContext.request.contextPath}/JobManage.do?page=${page.nextPage}">下一页</a>
			        <a href="${pageContext.request.contextPath}/JobManage.do?page=${page.lastPage}">尾页</a>			 
           </span>
				
		</div>
		
		
		</div>
		</div>


	</body>
</html>
