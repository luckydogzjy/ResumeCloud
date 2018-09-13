<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- 引入格式化日期标签  -->
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>Insert title here</title>
		<link rel="stylesheet" type="text/css" href="../css/demo.css" />

		<style type="text/css">
			input[type="text"] {
				width: 250px;
			}
			
			.info {
				height: 80px;
			}
			
			.left {
				text-align: right;
			}
			
			#textarea {
				width: 250px;
				height: 60px;
			}
			
			#right-box-title {
				position: absolute;
				left: 70px;
				top: 45px;
			}
			
			.right-box-title-font {
				color: #0099CB;
			}
			
			#right-box-font1 {
				float: left;
			}
			
			#right-box-font2 {
				position: relative;
				left: 20px;
				float: left;
			}
			
			#right-box-font3 {
				position: relative;
				left: 50px;
				float: left;
			}
			
			#right-box-img {
				position: absolute;
				left: 900px;
			}
			
			#right-box-hr1 {
				position: absolute;
				top: 110px;
				left: 33px;
				size: 1;
				width: 1000px;
			}
			
			#right-box-hr2 {
				position: absolute;
				top: 450px;
				left: 33px;
				size: 1;
				width: 1000px;
			}
			
			#body {
				left: 126px;
				top: 128px;
				position: absolute;
			}
			
			#table {
				BORDER-RIGHT: lightblue 1px solid;
				BORDER-TOP: lightblue 1px solid;
				BORDER-LEFT: lightblue 1px solid;
				BORDER-BOTTOM: lightblue 1px solid;
				height: 380px;
				left: 50px;
				top: 110px;
				position: absolute;
			}
			
			#right-box-button-tijiao {
				position: absolute;
				left: 0px;
				top: 360px;
				border-radius: 5px;
				border: 0px;
				width: 109px;
				height: 38px;
				background-color: #0099FF;
				font-family: 'Microsoft YaHei Negreta', 'Microsoft YaHei Normal', 'Microsoft YaHei';
				font-weight: 700;
				font-style: normal;
				font-size: 14px;
				color: #FFFFFF;
			}
			
			#right-box-button-quxiao {
				position: absolute;
				left: 160px;
				top: 360px;
				border-radius: 5px;
				border: 0px;
				width: 109px;
				height: 38px;
				background-color: #FF0033;
				font-family: 'Microsoft YaHei Negreta', 'Microsoft YaHei Normal', 'Microsoft YaHei';
				font-weight: 700;
				font-style: normal;
				font-size: 14px;
				color: #FFFFFF;
			}
		</style>

		
	</head>
		<script src="../My97DatePicker/WdatePicker.js" type="text/javascript"></script>
	<body>
		<div id="box">
			<div id="header">
				<jsp:include page="../common/header.jsp" flush="true" />
			</div>
			<div id="lefter">
				<jsp:include page="../common/lefter.jsp" flush="true" />
			</div>

			<div id="right" class="righter">
				<div id="right-box" class="right-box">

					<div id="right-box-title">
						<h2 id="right-box-font1" class="right-box-title-font">准备为</h2>
						<h2 id="right-box-font2">${resume.resumeName}</h2>
						<h2 id="right-box-font3" class="right-box-title-font">安排面试</h2>
					</div>
					<hr id="right-box-hr1" />
					<hr id="right-box-hr2" />
					<div id="body">
						<form action="${pageContext.request.contextPath }/Interview/newInterview.do">
							<table cellSpacing="5" cellPadding="4">
								<input type="hidden" name="resumeId" value="${resume.resumeId}" />
								<tr>
									<td class="left">求职者姓名</td>
									<td colspan="2"><input type="text" id="" value="${resume.resumeName}" /></td>
									<td><span id="">
									
								</span></td>
								</tr>
								<tr>
									<td class="left">求职者联系方式</td>
									<td colspan="2"><input type="text" name="resumePhone" id="" value="${resume.resumePhone}" /></td>
									<td><span id="">
									
								</span></td>
								</tr>
								<tr>
									<td class="left">面试职位</td>
									<td colspan="2"><input type="text" name="interviewJob" id="" value="${resume.resumeJobIntension}" /></td>
									<td><span id="">
									
								</span></td>
								</tr>
								<tr>
									<td class="left">面试时间</td>
									<td colspan="2"><input class="Wdate" name="interviewTime" type="text" onClick="WdatePicker({el:this,dateFmt:'yyyy-MM-dd HH:mm:ss'})" /> 

									</td>

									<td><span id="">
									
								</span></td>
								</tr>
								<tr>
									<td class="left">面试地点</td>
									<td colspan="2"><input type="text" name="interviewAddress" id="" value="" placeholder="请输入面试地点" /></td>
									<td><span id="">
									
								</span></td>
								</tr>
								<tr>
									<td class="left">联系人</td>
									<td colspan="2"><input type="text" name="interviewAssociateUsername" id="" value="" placeholder="请输入联系人" /></td>
									<td><span id="">
									
								</span></td>
								</tr>
								<tr>
									<td class="left">联系人联系方式</td>
									<td colspan="2"><input type="text" name="interviewAssociatePhone" id="" value="" placeholder="请输入联系人联系方式" /></td>
									<td><span id="">
									
								</span></td>
								</tr>
								<tr>
									<td class="left">备注信息</td>
									<td colspan="2"><textarea id="textarea" name="interviewInfo"></textarea></td>
									<td><span id="">
									
								</span></td>
								</tr>

							</table>

							<input type="submit" id="right-box-button-tijiao" value="提交" />
							<input type="reset" id="right-box-button-quxiao" value="取消" />
						</form>
					</div>
				</div>
			</div>
		</div>
	</body>

</html>