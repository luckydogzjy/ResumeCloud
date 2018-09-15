<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>简历新增页面</title>

<link rel="stylesheet" type="text/css" href="css/demo.css"/>
 <link rel="stylesheet" type="text/css" href="css/resumeCss/resume1-1.css"/>
 <script src="js/resume-1-1.js"></script>
</head>





<body>

		<div id="box">
		<div id="header">
		<jsp:include page="../common/header.jsp" flush="true"/>
		</div>
		<div id="lefter">
		<jsp:include page="../common/lefter.jsp" flush="true"/>
		</div>


		<div id="right">
			<div id="right-box">
			
			<div id="right_add"  style="text-align: left;" >
				
			<FONT size="6" color="#FF0000">基本信息</FONT>
			<HR size="4" id="addhengxian" width="" noshade>
			
	
		<form name="form1" method="post" action="${pageContext.request.contextPath }/resume_add.do" enctype="multipart/form-data"> 
		
		<table border="0" height="50">
			<tr>
				<td height="35"><span >姓名</span></td>
				<td><input name="resume_name" id="resume_name" type="text"  onblur="checkname();"/></td>
				<td><font id="s1" size="2" color="red"></font></td>
			</tr>
		
			<tr>
				<td height="35"><span >性别</span></td>
				<td><select name = "resume_sex" id="resume_sex" >
					<option value="1">男</option>
					<option value="0">女</option>
			</select></td>

			
			</tr>
			<tr>
				<td height="35"><span >电话</span></td>
				<td><input name="resume_phone" id="resume_phone" type="text" onblur="checkphone();"/></td>
				<td><font id="s2" size="2" color="red"></font></td>
			</tr>
			<tr>
				<td height="35"><span >邮箱</span></td>
				<td><input name="resume_email" id="resume_email" type="text" onblur="checkemail();"/></td>
				<td><font id="s3" size="2" color="red"></font></td>
			</tr>

			<tr>
				<td height="35"><span >学历</span></td>
				<td><select name = "resume_education" id="resume_education" >
				<option value="0">高中以及高中以下</option>
					<option value="1">专科</option>
					<option value="2">本科</option>
					<option value="3">研究生</option>
					<option value="4">硕士</option>
					<option value="5">博士</option>
					<option value="6">博士以上</option></td>
			</tr>
			<tr>
				<td height="35"><span >地址</span></td>
				<td><input name="resume_address" id="resume_address" type="text" onblur="checkaddress();"/></td>
				<td><font id="s4" size="2" color="red"></font></td>
			</tr>			
			<tr>
				<td height="35"><span >出生年月</span></td>
				<td><input name="resume_birthday" id="resume_birthday"  type="date" onblur="checkbir();"/></td>
				<td><font id="s5" size="2" color="red"></font></td>
			</tr>
			
			<tr>
				<td height="35"><span >求职意向</span></td>
				<td><input name="resume_job_intension" id="resume_job_intension" type="text" onblur="checkjob();"/></td>
				<td><font id="s6" size="2" color="red"></font></td>
			</tr>
			<tr>
				<td height="35"><span >毕业院校</span></td>
				<td><input name="resume_graduate_institution" id="resume_graduate_institution" type="text" onblur="checkschool();"/></td>
				<td><font id="s7" size="2" color="red"></font></td>
			</tr>
			<tr>
				<td height="35"><span >工作年限</span></td>
				<td><input name="resume_work_years" id="resume_work_years" type="text" onblur="checkyears();"/></td>
				<td><font id="s8" size="2" color="red"></font></td>
			</tr>
			
			
			<table id="right_wenbenyu" border="0">
			<tr>
				<tr><td><span >自我评价</span></td></tr>
			<tr>
				<td>
				<textarea  id="selfevaluation" name="resume_self_evaluation"  style="width: 180px;" onkeyup="check(this);" onchange="check(this);" >
     		
  				</textarea></td>
  				
			</tr>
			
			<tr>
				<td><span >工作经验</span></td>	
  				<tr>
  				<td>
				<textarea  id="experiencetext" name="resume_work_experience"  style="width: 180px;" onkeyup="check(this);" onchange="check(this);" >
  				</textarea></td>
  				</tr>
			</tr>	
		
			</table>
			
			
		</table>
		
		<FONT id="right_fujia" size="6" color="#FF0000">附加信息</FONT>
		<HR id="hengxian"	size="4" width="300" noshade>
		 <input id="wenjian" type="file" name="file_03" onchange="promote();"/>
		<font id="s9" size="2" color="red"></font>
		<font id="s10" size="2" color="red"></font>
		

			<input id="addbutton_div" type="submit" value="添加"  onclick="return checkinput()" />			
			<a href="javascript:history.back();"><input id="addbutton_div2" type="button" value="返回"/></a>
			
    
		
	</form>
	
		
		
		</div>	
	</div>
	</div>
	</div>
   
   



</body>
</html>