<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>简历详情</title>
</head>
<body>
	<table border="1">
		<tr>
			<td>姓名：${resume.resumeName }</td>
			<td rowspan="5">自我评价：${resume.resumeSelfEvaluation }</td>
		</tr>
		<tr>
			<td>性别： <c:if test="${resume.resumeSex == 1}">
					男
				</c:if> <c:if test="${resume.resumeSex == 0}">
					女
				</c:if>
			</td>
		</tr>
		<tr>
			<td>出生日期：<fmt:formatDate value="${resume.resumeBirthday }"
					pattern="yyyy-MM-dd" /></td>
		</tr>
		<tr>
			<td>电话：${resume.resumePhone }</td>
		</tr>
		<tr>
			<td>邮箱：${resume.resumeEmail }</td>
		</tr>
		<tr>
			<td>地址：${resume.resumeAddress }</td>
			<td rowspan="5">工作经验：${resume.resumeWorkExperience }</td>
		</tr>
		<tr>
			<td>学历： <c:if test="${resume.resumeEducation == 0}">
					高中及高中以下
				</c:if> <c:if test="${resume.resumeEducation == 1}">
					专科
				</c:if> <c:if test="${resume.resumeEducation == 2}">
					本科
				</c:if> <c:if test="${resume.resumeEducation == 3}">
					硕士
				</c:if> <c:if test="${resume.resumeEducation == 4}">
					硕士
				</c:if> <c:if test="${resume.resumeEducation == 5}">
					博士
				</c:if> <c:if test="${resume.resumeEducation == 6}">
					博士以上
				</c:if>

			</td>
		</tr>
		<tr>
			<td>毕业院校：${resume.resumeGraduateInstitution }</td>
		</tr>
		<tr>
			<td>求职意向：${resume.resumeJobIntension }</td>
		</tr>
		<tr>
			<td>工作年限：${resume.resumeWorkYears }</td>
		</tr>

	</table>

	<hr />

	<table border="1">
		<tr>
			<td>图片地址</td>
		</tr>
		<c:forEach items="${resume.lPics }" var="resumePic">
			<tr>
				<td>${resumePic.pPic }</td>
			</tr>		
		</c:forEach>

	</table> 
	
	<hr />
	
		<table border="1">
		<tr>
			<td>文件地址</td>
		</tr>
		<c:forEach items="${resume.lFiles }" var="resumeFile">
			<tr>
				<td>${resumeFile.fFile }</td>
			</tr>		
		</c:forEach>

	</table> 
	<hr />
	
	<a href="javascript:history.back();">返回上一页</a>
</body>
</html>