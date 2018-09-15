<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
	<head>
		<meta charset="UTF-8">
		<title>ResumeCloud</title>
					
		<link rel="stylesheet" type="text/css" href="css/demo.css"/>
		<link rel="stylesheet" type="text/css" href="css/jobCss/job.css">
		
		
		<!-- <style type="text/css" >
		
		a{
		 	text-decoration:none; 
		  	color:#333;
		  	}
	z
		
		</style> -->
		
		
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
						<input id="job-add-button" type="button" value="添加职位" onClick="location.href='${pageContext.request.contextPath}/jobAddView.do'"/> 
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
						<td><a href="${pageContext.request.contextPath}/jobDetails.do?jobId=${job.JOB_ID}">${job.JOB_NAME}</a></td>
						<td>${job.JOB_COUNT}</td>
						<td><fmt:formatDate value="${job.JOB_END_TIME}" pattern="yyyy年MM月dd日" /></td>

						<td>
							<c:if test="${job.JOB_STATUS==1}">
<%-- 							<a id="button-status1" href="${pageContext.request.contextPath}/jobChangeStatus.do?jobId=${job.JOB_ID}&jobStatus=${job.JOB_STATUS}">开启</a>
 --%>							<input id="button-status1" type="button" value="开启" onClick="location.href='${pageContext.request.contextPath}/jobChangeStatus.do?jobId=${job.JOB_ID}&jobStatus=${job.JOB_STATUS}'"/>
							</c:if>
							<c:if test="${job.JOB_STATUS==0}">
								
								<input id="button-status0" type="button" value="关闭" onClick="location.href='${pageContext.request.contextPath}/jobChangeStatus.do?jobId=${job.JOB_ID}&jobStatus=${job.JOB_STATUS}'"/>
							</c:if>
							
						</td>
						<td>
						 	<input id="button-modify" type="button" value="修改" onClick="location.href='${pageContext.request.contextPath}/jobUpdateView.do?jobId=${job.JOB_ID}'"/>
						 	<input id="button-delete" type="button" value="删除" onClick="location.href='${pageContext.request.contextPath}/jobDelete.do?jobId=${job.JOB_ID}'"/>
						 	<input id="button-template" type="button" value="生成模板" onClick="location.href='${pageContext.request.contextPath}/jobTemplateView.do?jobId=${job.JOB_ID}'"/>	
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
