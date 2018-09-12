<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>简历新增页面</title>

<link rel="stylesheet" type="text/css" href="css/demo.css"/>

</head>
<body>


		<h2>简历新增</h2>


		<div id="header">
		<jsp:include page="../common/header.jsp" flush="true"/>
		</div>
		<div id="lefter">
		<jsp:include page="../common/lefter.jsp" flush="true"/>
		</div>



		<div id="right">
		<form method="post" action="${pageContext.request.contextPath }/resume_add.do"> 
		
			<span id="tishi">${info}</span>	</br>
			<span >姓名</span>
			<input name="resume_name" id="resume_name" type="text"/></br>
			<span >性别</span>		
			
			<select name = "resume_sex" id="resume_sex" >
					<option value="1">男</option>
					<option value="0">女</option>
			</select></br>
				
			<span >电话</span>
			<input name="resume_phone" id="resume_phone" type="text"/></br>
			<span >邮箱</span>
			<input name="resume_email" id="resume_email" type="text"/></br>
			<span >学历</span>
		
			
				<select name = "resume_education" id="resume_education" >
				<option value="0">高中以及高中以下</option>
					<option value="1">专科</option>
					<option value="2">本科</option>
					<option value="3">研究生</option>
					<option value="4">硕士</option>
					<option value="5">博士</option>
					<option value="6">博士以上</option>
					
					
			</select></br>
			
			<span >地址</span>
			<input name="resume_address" id="resume_address" type="text"/></br>
			<span >出生年月</span>
			<input name="resume_birthday" id="resume_birthday"  type="date" /></br>
			<span >求职意向</span>
			<input name="resume_job_intension" id="resume_job_intension" type="text"/></br>
			<span >毕业院校</span>
			<input name="resume_graduate_institution" id="resume_graduate_institution" type="text"/></br>
			<span >工作年限</span>
			<input name="resume_work_years" id="resume_work_years" type="text"/></br>
			<span >自我评价</span>
			<input name="resume_self_evaluation" id="resume_self_evaluation" type="text"/></br>
			<span >工作经验</span>
			<input name="resume_work_experience" id="resume_work_experience" type="text"/></br>
			
			<input  type="submit" value="添加" onclick="return check()" />
		
	</form>
	</div>



</body>
</html>