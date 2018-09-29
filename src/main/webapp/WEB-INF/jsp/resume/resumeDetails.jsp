<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>简历详情</title>
		<link rel="stylesheet" type="text/css" href="../css/resumeCss/resume-ww.css"/>
		<link rel="stylesheet" type="text/css" href="../css/demo.css"/>
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
					<div id="right_jiben"  style="text-align: left;" >
						<FONT  size="6" color="#FF0000">基本信息</FONT>

						<HR size="4" width="" noshade>

						<table border="0">
			
							<tr>
								<td height="35" width="80">姓名：</td>
								<td width="240">${resume.resumeName }</td>
								<td>自我评价</td>
							</tr>

							<tr>
								<td height="35">性别：</td>
								<td> 

									<c:if test="${resume.resumeSex == 1}">
										男
									</c:if> <c:if test="${resume.resumeSex == 0}">
										女
									</c:if>
								</td>

								<td rowspan="4">${resume.resumeSelfEvaluation }</td>
							</tr>

							<tr>
							 	<td height="35">电话：</td>
							 	<td>${resume.resumePhone }</td>
						 	</tr>

						 	<tr>
							 	<td height="35">邮箱：</td>
							 	<td>${resume.resumeEmail }</td>
						 	</tr>

						 	<tr>
							 	<td  height="35">学历：</td>

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
								 		硕士
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
							 </tr>

							 <tr>
						 		<td  height="35">地址：</td>
						 		<td>${resume.resumeAddress }</td>
						 		<td >工作经验：</td>
						 	</tr>


						 	<tr>
						 		<td  height="35">出生日期：</td>
						 		<td><fmt:formatDate value="${resume.resumeBirthday }" pattern="yyyy-MM-dd" /></td>
						 		<td  rowspan="4">${resume.resumeWorkExperience }</td>
						 	</tr>

						 	<tr>
						 		<td height="35">求职意向：</td>
						 		<td>${resume.resumeJobIntension }</td>
						 	</tr>
			
						 	<tr>
							 	<td height="35">毕业院校：</td>
							 	<td>${resume.resumeGraduateInstitution }</td>
							 </tr>
		
							 <tr>
							 	<td height="35">工作年限：</td>
							 	<td>${resume.resumeWorkYears }</td>
						 	</tr>

						 </table>
					  </div>
					  <div id="right_fujia">
						 <FONT size="6" color="#FF0000">附加信息</FONT>
						 <HR id="hengxian"	size="4" width="300" noshade>

					 	<table id="table_pic_file" border="0">
			
					 		<tr>
							 	<td height="35" >图片地址</td>
						 	</tr>
						 	<c:forEach items="${resume.lPics }" var="resumePic">
							 	<tr>
								 <!--	<td >${resumePic.pPic }</td>  -->
									 <td ><img src="${resumePic.pPic }" id="table_pic" alt="图片"/></td>
								 </tr>		
							 </c:forEach>

						 	<tr>
								 <td height="35">文件地址</td>
							 </tr>

							 <c:forEach items="${resume.lFiles }" var="resumeFile">
							 	<tr>
							 		<td >${resumeFile.fFile }</td>
							 	</tr>		
						 	</c:forEach>
						 </table>

					   </div>
					   <a href="javascript:history.back();"><input id="backbtn" type="button" value="返回"/></a>
					
					
				</div>
			</div>
		</div>
		
	</body>
</html>