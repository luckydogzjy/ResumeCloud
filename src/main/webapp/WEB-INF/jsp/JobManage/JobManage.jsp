<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
	<head>
		<meta charset="UTF-8">
		<title>JobManage</title>
		
		<script type="text/javascript">
			
		
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
				<div>
		****************************************************************************<br> 
		<a href="${pageContext.request.contextPath}/jobAddView.do">添加</a><br> 
		****************************************************************************<br> 
		
		<form id="search" action="${pageContext.request.contextPath}/jobGetByName.do" method="POST">
			<input type="text"  name="search"><input type="submit" value="搜索">
		</form>
		
		</div>
 	 	****************************************************************************<br> 
		
		<div style="text-align:center">
			<table>
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
					 
					 <td><a href="${pageContext.request.contextPath}/jobChangeStatus.do?jobId=${job.JOB_ID}&jobStatus=${job.JOB_STATUS}">${job.JOB_STATUS}</a></td>
					 
					 <td><a href="${pageContext.request.contextPath}/jobUpdateView.do?jobId=${job.JOB_ID}">修改</a>&nbsp&nbsp<a href="${pageContext.request.contextPath}/jobDelete.do?jobId=${job.JOB_ID}">删除</a>&nbsp&nbsp<a href="${pageContext.request.contextPath}/jobTemplateView.do?jobId=${job.JOB_ID}">生成模板</a></td>
				    </tr>
				</c:forEach>
			
			</table>
		</div>
		****************************************************************************<br> 
		<p>当前第${page.pageNum}页，一共${page.pages}页</p>
        <a href="${pageContext.request.contextPath}/JobManage.do?page=${page.firstPage}">首页</a>
        <a href="${pageContext.request.contextPath}/JobManage.do?page=${page.prePage}">上一页</a>
        <a href="${pageContext.request.contextPath}/JobManage.do?page=${page.nextPage}">下一页</a>
        <a href="${pageContext.request.contextPath}/JobManage.do?page=${page.lastPage}">尾页</a>
		
			</div>
		</div>
	</body>
</html>
