<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>resumeDisplay显示全部信息</title>

</head>

<body>

	<form action="getResumeListByCondition.do">
		<table border="1" width="50%">
			<tr>
				<td>姓名:<input type="text" name="resumeName" /></td>
				<td>求职意向:<input type="text" name="resumeJobIntension"/></td>
				<td>性別:
					<select name="resumeSex">
						<option value="-1">全部</option>
						<option value="1">男</option>
						<option value="0">女</option>
					</select>
				</td>
				<td>学历:
					<select name="resumeEducation">
						<option value="-1">全部</option>
						<option value="0">专科</option>
						<option value="1">本科</option>
						<option value="2">硕士</option>
						<option value="3">博士</option>
					</select>
				</td>
			</tr>
			
			<tr>
				<td>工作年限:
					<select name="resumeWorkYears">
						<option value="-1">全部</option>
						<option value="0">0-3年</option>
						<option value="1">3-5年</option>
						<option value="2">5-10年</option>
						<option value="3">10年以上</option>
					</select>
				</td>
				
				<td colspan="2" >毕业院校:<input type="text" name="resumeGraduateInstitution" width=""/></td>
				<td ><input type="submit" value="查找"/></td>
			</tr>
		</table>
	</form>
	
	<table border="1" width="50%">
		<thead>
			<tr>
				<th>姓名</th>
				<th>性别</th>
				<th>电话</th>
				<th>工作年限</th>
				<th>毕业院校</th>
				<th>学历</th>
				<th>求职意向</th>
				<th>录入时间</th>
				<th>面试结果</th>
				<th>操作</th>				
			</tr>
		</thead>
		
		<tbody>
			<c:forEach items="${resumeList }" var="resume">
			
			<tr>
				<td><a href="<%=request.getContextPath() %>/resumeDetails.do?ResumeId=${resume.resumeId }" >${resume.resumeName }</a></td>
				<td>
					<c:if test="${resume.resumeSex == 1}">
						男
					</c:if>
					<c:if test="${resume.resumeSex == 0}">
						女
					</c:if>
				
				</td>
				<td>${resume.resumePhone }</td>
				<td>${resume.resumeWorkYears }</td>
				<td>${resume.resumeGraduateInstitution }</td>
				
				<td>
					<c:if test="${resume.resumeEducation == 0}">
						专科
					</c:if>
					<c:if test="${resume.resumeEducation == 1}">
						本科
					</c:if>
					<c:if test="${resume.resumeEducation == 2}">
						硕士
					</c:if>
					<c:if test="${resume.resumeEducation == 3}">
						博士
					</c:if>
				
				</td>
				
				<td>${resume.resumeJobIntension }</td>
				<td>${resume.resumeCreateTime }</td>
				
				<td>
				  
					<c:if test="${resume.interview.interviewStatus == 1}">
						成功
					</c:if>
					<c:if test="${resume.interview.interviewStatus == 2}">
						待面试
					</c:if>
					<c:if test="${resume.interview.interviewStatus == 3}">
						失败
					</c:if>
	<%-- 				<c:if test="${resume.interview.interviewStatus != 1 
									&& resume.interview.interviewStatus != 2 
										&& resume.interview.interviewStatus != 3}">
						未安排面试
					</c:if> --%>
					
					<c:if test="${resume.interview.interviewStatus == null}">
						未安排面试
					</c:if>
					
				</td>
				
				<td>
					<input type="button" value="修改"/>
					<input type="button" value="删除"/>
					<!-- 如果面试情况为待面试，则面试按钮不可点 -->
					<c:if test="${resume.interview.interviewStatus == 2}">
						<input type="button" value="待面试" disabled="disabled"/>
					</c:if>
					<c:if test="${resume.interview.interviewStatus != 2}">
						<input type="button" value="面试"/>
					</c:if>
					
					<!-- 如果简历是自己录入的(0),并且没有没有被共享过(0) 则按钮为共享 -->
					<c:if test="resume.">
					
					</c:if>
					<input type="button" value="分享"/>
					
				</td>
			</tr>
				
			</c:forEach>
		</tbody>
	</table>
</body>
</html>