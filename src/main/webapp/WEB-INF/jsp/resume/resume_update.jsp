<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>简历修改</title>
</head>
<body>


<h2>简历修改</h2>









		<form method="post" action="${pageContext.request.contextPath }/resume_update.do"> 
		<div>
		 <span id="tishi">${info}</span>	</br> 
			 <input name="resume_id" id="resume_id" type="text" value="${resume.resumeId}" /></br>
			<span >姓名</span>
			<input name="resume_name" id="resume_name" type="text" value="${resume.resumeName}" /></br>
			<span >性别</span>		
			
			<select name = "resume_sex" id="resume_sex" >
			
		
					<option value="${resume.resumeSex}"> <c:if test="${resume.resumeSex == 1}">男</c:if>     <c:if test="${resume.resumeSex == 0}">女</c:if></option>
					<option value="1">男</option>
					<option value="0">女</option>
			</select></br>
				
			<span >电话</span>
			<input name="resume_phone" id="resume_phone" type="text"  value="${resume.resumePhone}"/></br>
			<span >邮箱</span>
			<input name="resume_email" id="resume_email" type="text" value="${resume.resumeEmail}"/></br>
			<span >学历</span>
		
			
				<select name = "resume_education" id="resume_education" >
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
					
			</select></br>
			
			<span >地址</span>
			<input name="resume_address" id="resume_address" type="text" value="${resume.resumeAddress}"/></br>
			<span >出生年月</span>
			<input name="resume_birthday" id="resume_birthday" type="date" value="${resume.resumeBirthday}"/></br>
			<span >求职意向</span>
			<input name="resume_job_intension" id="resume_job_intension" type="text" value="${resume.resumeJobIntension}"/></br>
			<span >毕业院校</span>
			<input name="resume_graduate_institution" id="resume_graduate_institution" type="text" value="${resume.resumeGraduateInstitution}"/></br>
			<span >工作年限</span>
			<input name="resume_work_years" id="resume_work_years" type="text" value="${resume.resumeWorkYears}"/></br>
			<span >自我评价</span>
			<input name="resume_self_evaluation" id="resume_self_evaluation" type="text" value="${resume.resumeSelfEvaluation}"/></br>
			<span >工作经验</span>
			<input name="resume_work_experience" id="resume_work_experience" type="text" value="${resume.resumeWorkExperience}"/></br>
			
			<input  type="submit" value="修改" onclick="return check()" />
		</div> 
	</form>
		

  

</body>
</html>