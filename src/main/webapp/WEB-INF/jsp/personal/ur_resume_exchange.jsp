<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title></title>
		<link rel="stylesheet" type="text/css" href="../css/resumeCss/resume-ww.css"/>
		<link rel="stylesheet" type="text/css" href="../css/demo.css" />
	</head>
	
	<script type="text/javascript">

	
	function pageChange(page){
		
		var resumeName = document.getElementsByName("resumeName")[0].value;
		var resumeJobIntension = document.getElementsByName("resumeJobIntension")[0].value;
		var resumeSex = document.getElementsByName("resumeSex")[0].value;
		var resumeEducation = document.getElementsByName("resumeEducation")[0].value;
		var resumeWorkYears = document.getElementsByName("resumeWorkYears")[0].value;
		var resumeGraduateInstitution = document.getElementsByName("resumeGraduateInstitution")[0].value;
		
		var str = "&resumeName=" + resumeName+ "&resumeJobIntension=" + resumeJobIntension + "&resumeSex=" + resumeSex + "&resumeEducation=" + resumeEducation + "&resumeWorkYears=" + resumeWorkYears + "&resumeGraduateInstitution=" + resumeGraduateInstitution;
		var url = "${pageContext.request.contextPath}/personal/ur_resume_exchange.do?page=" + page;
		var urlEnd = url + str;
		location.href = urlEnd; 
		
	}
	</script>

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
					<div>
						<form action="${pageContext.request.contextPath }/personal/ur_resume_exchange.do">

							<div id="selectByName">
								<span>姓名</span>
								<input type="search" name="resumeName" value="${search.resumeName }" placeholder="请输入姓名" />
							</div>
							<div id="selectByJob">
								<span>求职意向</span>
								<input type="search" name="resumeJobIntension" value="${search.resumeJobIntension }" placeholder="请输入求职意向" />
							</div>
							<div id="selectBySex">
								<span>性别</span>
								<select name="resumeSex">
									<option value="-1" <c:if test="${'-1' eq search.resumeSex}">selected="selected"</c:if>>全部</option>
									<option value="1" <c:if test="${'1' eq search.resumeSex}">selected="selected"</c:if>>男</option>
									<option value="0" <c:if test="${'0' eq search.resumeSex}">selected="selected"</c:if>>女</option>
								</select>
							</div>

							<div id="selectByEdu">
								<span>学历</span>
								<select name="resumeEducation">
									<option value="-1" <c:if test="${'-1' eq search.resumeEducation}">selected="selected"</c:if>>全部</option>
									<option value="0" <c:if test="${'0' eq search.resumeEducation}">selected="selected"</c:if>>高中及高中以下</option>
									<option value="1" <c:if test="${'1' eq search.resumeEducation}">selected="selected"</c:if>>专科</option>
									<option value="2" <c:if test="${'2' eq search.resumeEducation}">selected="selected"</c:if>>本科</option>
									<option value="3" <c:if test="${'3' eq search.resumeEducation}">selected="selected"</c:if>>研究生</option>
									<option value="4" <c:if test="${'4' eq search.resumeEducation}">selected="selected"</c:if>>硕士</option>
									<option value="5" <c:if test="${'5' eq search.resumeEducation}">selected="selected"</c:if>>博士</option>
									<option value="6" <c:if test="${'6' eq search.resumeEducation}">selected="selected"</c:if>>博士以上</option>
								</select>
							</div>

							<div id="selectByWorkYears">
								<span>工作年限</span>
								<select name="resumeWorkYears">
									<option value="-1" <c:if test="${'-1' eq search.resumeWorkYears}">selected="selected"</c:if>>全部</option>
									<option value="0" <c:if test="${'0' eq search.resumeWorkYears}">selected="selected"</c:if>>0-3年</option>
									<option value="1" <c:if test="${'1' eq search.resumeWorkYears}">selected="selected"</c:if>>3-5年</option>
									<option value="2" <c:if test="${'2' eq search.resumeWorkYears}">selected="selected"</c:if>>5-10年</option>
									<option value="3" <c:if test="${'3' eq search.resumeWorkYears}">selected="selected"</c:if>>10年以上</option>
								</select>
							</div>
							<div id="selectByGradu">
								<span>毕业院校</span>
								<input type="search" name="resumeGraduateInstitution" value="${search.resumeGraduateInstitution }" style="width: 483px" placeholder="请输入毕业院校" />
							</div>
							<div id="searchBtn">
								<input type="submit" id="search_button" value="搜索" />
							</div>

							<div id="table">
								<table class="displayTable" width="1000px"  cellSpacing="6"  cellPadding="3">
									<thead>
										<tr>
											<th>姓名</th>
											<th>性别</th>
											<th>工作年限</th>
											<th>毕业院校</th>
											<th>学历</th>
											<th>求职意向</th>
											<th>兑换时间</th>

										</tr>
									</thead>

									<tbody>
										<c:forEach items="${resumeList }" var="resume">

											<tr>
												<td>
													<a href="<%=request.getContextPath() %>/Resume/resumeDetails.do?resumeId_Details=${resume.resumeId }">${resume.resumeName }</a>
													<%-- <a href="<%=request.getContextPath() %>${pageContext.request.contextPath}/Resume/resumeDetails.do?resumeId_Details=${resume.resumeId}">${resume.resumeName }</a> --%>
												</td>
												<td>
													<c:if test="${resume.resumeSex == 1}">
														男
													</c:if>
													<c:if test="${resume.resumeSex == 0}">
														女
													</c:if>
												</td>
												<td>${resume.resumeWorkYears }</td>
												<td>${resume.resumeGraduateInstitution }</td>

												<td>
													<c:if test="${resume.resumeEducation == 0}">
														高中及高中以下
													</c:if>
													<c:if test="${resume.resumeEducation == 1}">
														专科
													</c:if>
													<c:if test="${resume.resumeEducation == 2}">
														本科
													</c:if>
													<c:if test="${resume.resumeEducation == 3}">
														研究生
													</c:if>
													<c:if test="${resume.resumeEducation == 4}">
														硕士
													</c:if>
													<c:if test="${resume.resumeEducation == 5}">
														博士
													</c:if>
													<c:if test="${resume.resumeEducation == 6}">
														博士以上
													</c:if>
												</td>

												<td>${resume.resumeJobIntension }</td>
												<td>
													<fmt:formatDate value="${resume.resumeCreateTime }" pattern="yyyy-MM-dd" />
												</td>

											</tr>

										</c:forEach>
									</tbody>
								</table>

								<div id="pageDetail">
									<span>当前第${page.pageNum}页，一共${page.pages}页</span>
								</div>
								<div id="page">
									<span>
			       						<input type="button" value="首页" onclick="pageChange('1')"/>
			       						<input type="button" value="上一页" onclick="pageChange(${page.prePage})"/>
			       						<input type="button" value="下一页" onclick="pageChange(${page.nextPage})"/>
			       						<input type="button" value="尾页" onclick="pageChange(${page.pages})"/>
			       					 
          		 					</span>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</body>

</html>