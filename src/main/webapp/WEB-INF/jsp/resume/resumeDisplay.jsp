<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title></title>

<link rel="stylesheet" type="text/css" href="css/resumeCss/resume-ww.css"/>
<link rel="stylesheet" type="text/css" href="css/demo.css"/>

<script type="text/javascript">

	function resumeDelete(resumeId){
		
		var resumeName = document.getElementsByName("resumeName")[0].value;
		var resumeJobIntension = document.getElementsByName("resumeJobIntension")[0].value;
		var resumeSex = document.getElementsByName("resumeSex")[0].value;
		var resumeEducation = document.getElementsByName("resumeEducation")[0].value;
		var resumeWorkYears = document.getElementsByName("resumeWorkYears")[0].value;
		var resumeGraduateInstitution = document.getElementsByName("resumeGraduateInstitution")[0].value; 
		var msg = "确认删除？";
		
		if (confirm(msg) == true) 
		{
			var url = "<%=request.getContextPath()%>/resumeDelete.do?resumeId_Delete=" + resumeId +  "&resumeName=" + resumeName+ "&resumeJobIntension=" + resumeJobIntension + "&resumeSex=" + resumeSex + "&resumeEducation=" + resumeEducation + "&resumeWorkYears=" + resumeWorkYears + "&resumeGraduateInstitution=" + resumeGraduateInstitution;
        	location.href = url; 
			return true;
		}
		else
		{
			return false;
		}
	}
	
	function resumeShare(resumeId){
		
		var resumeName = document.getElementsByName("resumeName")[0].value;
		var resumeJobIntension = document.getElementsByName("resumeJobIntension")[0].value;
		var resumeSex = document.getElementsByName("resumeSex")[0].value;
		var resumeEducation = document.getElementsByName("resumeEducation")[0].value;
		var resumeWorkYears = document.getElementsByName("resumeWorkYears")[0].value;
		var resumeGraduateInstitution = document.getElementsByName("resumeGraduateInstitution")[0].value; 
		var msg = "请输入可以兑换积分";
		integral = prompt(msg)
		if(integral){
			var url = "<%=request.getContextPath()%>/resumeShare.do?resumeId_Share=" + resumeId +  "&integral=" + integral + "&resumeName=" + resumeName+ "&resumeJobIntension=" + resumeJobIntension + "&resumeSex=" + resumeSex + "&resumeEducation=" + resumeEducation + "&resumeWorkYears=" + resumeWorkYears + "&resumeGraduateInstitution=" + resumeGraduateInstitution;
        	location.href = url; 
			return true;
		}
		else
		{
			return false;
		}
	}
	function resumeUpdate (resumeId){
		
		var url = "${pageContext.request.contextPath}/resume_update_show.do?resume_id=" + resumeId;
			
		location.href = url; 		
	}
</script>

</head>
<link rel="stylesheet" type="text/css" href="css/demo.css" />
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
				<form action="getResumeListByCondition.do">
					
					<div id="selectByName">
						<span>姓名</span>
						<input type="search" name="resumeName" value="${resumeName }" />
					</div>

					<div id="selectByJob">
						<span>求职意向</span>
						<input type="search" name="resumeJobIntension" value="${resumeJobIntension }" />
					</div>

					<div id="selectBySex">
						<span>性别</span>
						 <select name="resumeSex">
								<option value="-1" <c:if test="${'-1' eq resumeSex}">selected="selected"</c:if>>全部</option>
								<option value="1" <c:if test="${'1' eq resumeSex}">selected="selected"</c:if>>男</option>
								<option value="0" <c:if test="${'0' eq resumeSex}">selected="selected"</c:if>>女</option>
						</select>
					</div>

					<div id="selectByEdu">
						<span>学历</span>
						<select name="resumeEducation">
								<option value="-1"
									<c:if test="${'-1' eq resumeEducation}">selected="selected"</c:if>>全部</option>
								<option value="0"
									<c:if test="${'0' eq resumeEducation}">selected="selected"</c:if>>高中及高中以下</option>
								<option value="1"
									<c:if test="${'1' eq resumeEducation}">selected="selected"</c:if>>专科</option>
								<option value="2"
									<c:if test="${'2' eq resumeEducation}">selected="selected"</c:if>>本科</option>
								<option value="3"
									<c:if test="${'3' eq resumeEducation}">selected="selected"</c:if>>研究生</option>
								<option value="4"
									<c:if test="${'4' eq resumeEducation}">selected="selected"</c:if>>硕士</option>
								<option value="5"
									<c:if test="${'5' eq resumeEducation}">selected="selected"</c:if>>博士</option>
								<option value="6"
									<c:if test="${'6' eq resumeEducation}">selected="selected"</c:if>>博士以上</option>
						</select>
					</div>

					<div id="selectByWorkYears">
						<span>工作年限</span>
						<select name="resumeWorkYears">
								<option value="-1"
									<c:if test="${'-1' eq resumeWorkYears}">selected="selected"</c:if>>全部</option>
								<option value="0"
									<c:if test="${'0' eq resumeWorkYears}">selected="selected"</c:if>>0-3年</option>
								<option value="1"
									<c:if test="${'1' eq resumeWorkYears}">selected="selected"</c:if>>3-5年</option>
								<option value="2"
									<c:if test="${'2' eq resumeWorkYears}">selected="selected"</c:if>>5-10年</option>
								<option value="3"
									<c:if test="${'3' eq resumeWorkYears}">selected="selected"</c:if>>10年以上</option>
						</select>
					</div>

					<div id="selectByGradu">
						<span>毕业院校</span>
						<input type="search" name="resumeGraduateInstitution" value="${resumeGraduateInstitution }" style="width: 483px" />
					</div>

					<div id="searchBtn">
						<input type="submit" id="search_button" value="搜索" />
					</div>
					<div id="addBtn">
						<a href="${pageContext.request.contextPath}/tiaozhuan_add.do"><input type="button" id="add_button" value="新增"/></a>
					</div>
					
					<div id="table">
						<table class="displayTable" width="1000px"  cellSpacing="6"  cellPadding="3">
							<thead>
							<tr>
									<th width="9%">姓名</th>
									<th width="5%">性别</th>
									<th width="10%">电话</th>
									<th width="8%">工作年限</th>
									<th width="8%">毕业院校</th>
									<th width="8%">学历</th>
									<th width="8%">求职意向</th>
									<th width="10%">录入时间</th>
									<th width="10%">面试结果</th>
									<th width="24%">操作</th>
								</tr> 
							</thead>

							<tbody>
								
								<c:forEach items="${resumeList }" var="resume">

									<tr>
										<td title="${resume.resumeName }">
											<a href="<%=request.getContextPath() %>/resumeDetails.do?resumeId_Details=${resume.resumeId }">${resume.resumeName }</a>
										</td>
										<td>
											<c:if test="${resume.resumeSex == 1}">
												男
											</c:if> <c:if test="${resume.resumeSex == 0}">
												女
										 	</c:if>
										</td>
										<td>${resume.resumePhone }</td>
										<td>${resume.resumeWorkYears }</td>
										<td title="${resume.resumeGraduateInstitution }">${resume.resumeGraduateInstitution }</td>

										<td title="${resume.resumeEducation }">
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
											</c:if> <c:if test="${resume.resumeEducation == 4}">
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
											<fmt:formatDate value="${resume.resumeCreateTime }"
												pattern="yyyy-MM-dd" />
										</td>

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
											<c:if test="${resume.interview.interviewStatus == null}">
												未安排面试
											</c:if>
										</td>

										<td>
											<input type="button" value="修改" onclick="resumeUpdate(${resume.resumeId })"/>
										
										
											<input type="button" value="删除" onclick="resumeDelete(${resume.resumeId })" />
										
											<!-- 如果面试情况为待面试，则面试按钮不可点 -->
											<c:if test="${resume.interview.interviewStatus == 2}">
												<input type="button" value="待面试" disabled="disabled" />
											</c:if> 
											<c:if test="${resume.interview.interviewStatus != 2}">
													<input type="button" value="面试" />
											</c:if> 
										
										
											<!--如果简历是自己录入的(0),并且没没有被共享过(0) 则按钮为共享 -->
											<c:if test="${resume.userResume.urResumeGetway == 0 &&  resume.userResume.urResumeShareFlag == 0}">
												<input type="button" value="共享" onclick="resumeShare(${resume.resumeId })"/>
											</c:if> 
											<!-- 如果简历是自己录入的(0),并且已经被共享过(1) 则按钮为已共享 -->
											<c:if test="${resume.userResume.urResumeGetway == 0 &&  resume.userResume.urResumeShareFlag == 1}">
												<input type="button" value="已共享" disabled="disabled" />
											</c:if>
											<!-- 如果简历是在共享中心兑换的(1), 则按钮为不可共享 --> 
											<c:if test="${resume.userResume.urResumeGetway != 0 &&  resume.userResume.urResumeShareFlag == 1}">
												<input type="button" value="不可共享" disabled="disabled"/>
											</c:if>
										</td>
									</tr>
			
								</c:forEach>

							</tbody>
						</table>
					</div>
				</form>

			</div>

		</div>
	</div>
  </div>
</body>
</html>







































