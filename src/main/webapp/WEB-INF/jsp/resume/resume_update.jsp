<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
     <%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>简历修改</title>
		<link rel="stylesheet" type="text/css" href="../css/demo.css"/>
		<link rel="stylesheet" type="text/css" href="../css/resumeCss/resume1-1.css"/>
 		<script type="text/javascript" src="../js/resumeJs/resume-1-2.js" ></script>
		<script type="text/javascript" src="../js/jquery-2.1.1.js"></script>
 
</head>

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

					<div id="right_update" style="text-align: left;">

						<FONT size="6" color="#FF0000">基本信息</FONT>

						<HR size="4" width="590" noshade>

						<%-- <form method="post" action="${pageContext.request.contextPath }/resume_update.do" enctype="multipart/form-data">  --%>

						<form method="POST" action="${pageContext.request.contextPath }/Resume/resume_update.do?page=${page}&resumeName=${resumeName}
										&resumeJobIntension=${resumeJobIntension}&resumeSex=${resumeSex}&resumeEducation=${resumeEducation}
										&resumeWorkYears=${resumeWorkYears}&resumeGraduateInstitution=${resumeGraduateInstitution}" enctype="multipart/form-data">

							<table border="0">

								<tr><input name="resumeId" id="resume_id" type="hidden" value="${resume.resumeId}" /></tr>
								<tr>
									<td height="35" width="80"><span>姓名</span></td>
									<td><input name="resumeName" id="resume_name" type="text" value="${resume.resumeName}" onblur="checkname();" /></td>
									<td>
										<font id="s1" size="2" color="red"></font>
									</td>
								</tr>

								<tr>
									<td height="35"><span>性别</span></td>
									<td>
										<select name="resumeSex" id="resume_sex">

											<option value="${resume.resumeSex}">
												<c:if test="${resume.resumeSex == 1}">男</c:if>
												<c:if test="${resume.resumeSex == 0}">女</c:if>
											</option>
											<option value="1">男</option>
											<option value="0">女</option>
										</select>
									</td>
								</tr>

								<tr>
									<td height="35"><span>电话</span></td>
									<td><input name="resumePhone" id="resume_phone" type="text" value="${resume.resumePhone}" onblur="checkphone();" /></td>
									<td>
										<font id="s2" size="2" color="red"></font>
									</td>
								</tr>

								<tr>
									<td height="35"><span>邮箱</span></td>
									<td><input name="resumeEmail" id="resume_email" type="text" value="${resume.resumeEmail}" onblur="checkemail();" /></td>
									<td>
										<font id="s3" size="2" color="red"></font>
									</td>
								</tr>

								<tr>
									<td height="35"><span>学历</span></td>
									<td>
										<select name="resumeEducation" id="resume_education">

											<option value="${resume.resumeEducation}">
												<c:if test="${resume.resumeEducation == 0 }">高中以及高中以下</c:if>
												<c:if test="${resume.resumeEducation == 1 }">专科</c:if>
												<c:if test="${resume.resumeEducation == 2 }">本科</c:if>
												<c:if test="${resume.resumeEducation == 3 }">研究生</c:if>
												<c:if test="${resume.resumeEducation == 4 }">硕士</c:if>
												<c:if test="${resume.resumeEducation == 5 }">博士</c:if>
												<c:if test="${resume.resumeEducation == 6 }">博士以上</c:if>

											</option>

											<option value="0">高中以及高中以下</option>
											<option value="1">专科</option>
											<option value="2">本科</option>
											<option value="3">研究生</option>
											<option value="4">硕士</option>
											<option value="5">博士</option>
											<option value="6">博士以上</option>

										</select>
								</tr>

								<tr>
									<td height="35"><span>地址</span></td>
									<td><input name="resumeAddress" id="resume_address" type="text" value="${resume.resumeAddress}" onblur="checkaddress();"></td>
									<td>
										<font id="s4" size="2" color="red"></font>
									</td>
								</tr>

								<tr>
									<td height="35"><span>出生年月</span></td>
									<td><input name="resumeBirthdayString" id="resume_birthday" type="date" value="<fmt:formatDate  value="${resume.resumeBirthday}" pattern="yyyy-MM-dd" />" onblur="checkbir();" /></td>
									<td>
										<font id="s5" size="2" color="red"></font>
									</td>
								</tr>

								<tr>
									<td height="35"><span>求职意向</span></td>
									<td><input name="resumeJobIntension" id="resume_job_intension" type="text" value="${resume.resumeJobIntension}" onblur="checkjob();" /></td>
									<td>
										<font id="s6" size="2" color="red"></font>
									</td>
								</tr>

								<tr>
									<td height="35"><span>毕业院校</span></td>
									<td><input name="resumeGraduateInstitution" id="resume_graduate_institution" type="text" value="${resume.resumeGraduateInstitution}" onblur="checkschool();" /></td>
									<td>
										<font id="s7" size="2" color="red"></font>
									</td>
								</tr>
								<tr>
									<td height="35"><span>工作年限</span></td>
									<td><input name="resumeWorkYears" id="resume_work_years" type="text" value="${resume.resumeWorkYears}" onblur="checkyears();" /></td>
									<td>
										<font id="s8" size="2" color="red"></font>
									</td>
								</tr>

								<table id="updateright_wenbenyu">
									<tr>
										<td><span>自我评价</span></td>
									</tr>
									<tr>
										<td><textarea id="selfevaluation" name="resumeSelfEvaluation" style="width: 200px;" onkeyup="check(this);" onchange="check(this);">${resume.resumeSelfEvaluation}
  				</textarea></td>
									</tr>
									<tr>
										<td><span>工作经验</span></td>
									</tr>
									<tr>
										<td><textarea id="experiencetext" name="resumeWorkExperience" style="width: 200px;" onkeyup="check(this);" onchange="check(this);">${resume.resumeWorkExperience}
  				</textarea></td>
									</tr>
								</table>

							</table>

							<FONT id="right_fujia" size="6" color="#FF0000">附加信息</FONT>
							<HR id="hengxian" size="4" width="300" noshade>

							<input id="wenjian" type="file" name="file_03" onchange="promote();" />
						
							<font id="s9" size="2" color="red"></font>
							<font id="s10" size="2" color="red"></font>
							
							<table border="1" id="radioway">
								<tr>
									<td>
							 			   <INPUT  name="changeway" type="radio" value="替换" checked>替换原有
										   <INPUT  name="changeway" type="radio" value="新增">新增
									</td>
								</tr>	
							</table>

							<input id="updatebutton_div" type="submit" value="修改" onclick="return checkinput()" />
							<a href="javascript:history.back();"><input id="updatebutton_div2" type="button" value="返回" /></a>

						</form>

					</div>
				</div>
			</div>
		</div>

	</body>

</html>