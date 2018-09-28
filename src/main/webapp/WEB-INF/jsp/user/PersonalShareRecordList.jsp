/<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>积分充值记录</title>
<%-- <script src="${pageContext.request.contextPath }/js/jquery-2.1.1.js"></script>--%>
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" href="bootstrap/css/bootstrap-responsive.min.css" />
<script type="text/javascript" src="bootstrap/js/bootstrap.min.js" ></script>
<script type="text/javascript" src="bootstrap/js/jquery-3.3.1.min.js" ></script>
<script type="text/javascript">
				
		function deleteRecharge(scId) {
			if(confirm('确实要删除该条记录吗?')) {
				$.post("<%=request.getContextPath()%>/delPersonShare.action",{"scId":scId},function(data){
					alert("记录删除成功！");
					window.location.reload();
				});
			}
		}
	</script>
</head>
<link rel="stylesheet" type="text/css" href="css/demo.css"/>
<body>

	<div id="box">
		<div id="header">
			<jsp:include page="../common/header.jsp" flush="true" />
		</div>
		<div id="lefter">
			<jsp:include page="../common/lefter.jsp" flush="true" />
		</div>
		
		
		<div id="right">
			<div style="text-align:center"><a href="${pageContext.request.contextPath }/searchPersonShare.action">查询个人共享记录</a></div>
			<div id="right-box">
				<table width="100%" class="table table-hover table-striped">
					<tr>
						<td>姓名</td>
						<td>性别</td>
						<td>工作年限</td>
						<td>毕业院校</td>
						<td>学历</td>
						<td>求职意向</td>
						<td>共享时间</td>
						<td>所需积分</td>
						<td>下载次数</td>
						<td>操作</td>
					</tr>
				<c:forEach items="${personShareLists}" var="personShare">
					<tr>
						<td>${personShare.resume.resumeName}</td>
						<td>		
 							<c:if test="${personShare.resume.resumeSex eq 1}">男</c:if>
					 		<c:if test="${personShare.resume.resumeSex eq 0}">女</c:if>
					 	</td>
					 	<td>${personShare.resume.resumeWorkYears}</td>
					 	<td>${personShare.resume.resumeGraduateInstitution}</td>
					 	<td>
					 		<c:if test="${personShare.resume.resumeEducation eq 0}">高中及高中以下</c:if> 
							<c:if test="${personShare.resume.resumeEducation eq 1}">专科</c:if> 
							<c:if test="${personShare.resume.resumeEducation eq 2}">本科</c:if> 
							<c:if test="${personShare.resume.resumeEducation eq 3}">研究生</c:if>
							<c:if test="${personShare.resume.resumeEducation eq 4}">硕士</c:if> 
							<c:if test="${personShare.resume.resumeEducation eq 5}">博士</c:if> 
							<c:if test="${personShare.resume.resumeEducation eq 6}">博士以上</c:if>
						</td>
						<td>${personShare.resume.resumeJobIntension}</td>
						<td><fmt:formatDate value="${personShare.scCreateTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						<td>${personShare.scIntegral}</td>
						<td>${personShare.scDownloadCount}</td>
					 	<td><input class="btn btn-danger" type="button" value="删除记录" onclick="deleteRecharge(${personShare.scId})"></td>	 				
					</tr>
				</c:forEach>
				</table>
				<div>
					<h4>${msgs}</h4>
				</div>
			</div>
		</div>
	</div>
	 	
</body>
</html>