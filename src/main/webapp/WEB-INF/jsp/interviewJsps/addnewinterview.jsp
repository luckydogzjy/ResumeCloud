<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- 引入格式化日期标签  -->
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>添加面试安排</title>
		<link rel="stylesheet" type="text/css" href="../css/demo.css" />
		<link rel="stylesheet" type="text/css" href="../css/interviewCss/addinterview.css" />

	</head>
	<script src="../js/jquery-2.1.1.js" type="text/javascript" charset="utf-8"></script>
	<script src="../My97DatePicker/WdatePicker.js" type="text/javascript"></script>
	<script src="../js/vue.js" type="text/javascript" charset="utf-8"></script>
	<script src="../js/interviewJs/addInterview.js" type="text/javascript" charset="utf-8"></script>
	<script type="text/javascript">
	window.onload = function() {
		new Vue({
			el: "#div0", //绑定标签
			data: { //定义数据
				username: "${resume.resumeName}",
				interviewTime: "",
				interviewJob: "${resume.resumeJobIntension}",
				interviewAddress: "",
				interviewAssociateUsername: "",
				interviewAssociatePhone: ""
			}
		});
	}
	</script>
	<body>
		<div id="div0">
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

							<form action="${pageContext.request.contextPath }/Interview/newInterview.do" onsubmit="return formcheck()">
								<textarea id="msg" name="interviewMessage">{{username}} 您好！&#13;&#10 ${user.company}公司提醒您&#13;&#10请携带简历于  {{interviewTime}}参加面试 &#13;&#10面试职位:{{interviewJob}}&#13;&#10地址:{{interviewAddress}}&#13;&#10联系人:{{interviewAssociateUsername}}&#13;&#10手机号:{{interviewAssociatePhone}}</textarea>
								<input type="button" id="jscopy" value="点我复制信息" onclick="copy()" />
								<input type="hidden" name="resumeId" id="resumeId" value="${resume.resumeId}" />
								<input type="hidden" name="resumePhone" id="resumePhone" value="${resume.resumePhone}" />
								<input type="hidden" name="interviewJob" id="interviewJob" value="${resume.resumeJobIntension}" />
								<input type="hidden" name="interviewTime" id="interviewTime" />
								<input type="hidden" name="interviewAddress" id="interviewAddress" />
								<input type="hidden" name="interviewAssociateUsername" id="interviewAssociateUsername" />
								<input type="hidden" name="interviewAssociatePhone" id="interviewAssociatePhone" />

								<table cellSpacing="5" cellPadding="4">
									<tr>
										<td class="left">求职者姓名</td>
										<td colspan="2">{{username}}</td>
									</tr>
									<tr>
										<td class="left">求职者联系方式</td>
										<td colspan="2"><input type="text" id="phone" onblur="checkphone(this.value)" onchange="check(this.value,'resumePhone')" value="${resume.resumePhone}" /></td>
										<td><span id="resumePhoneMsg">
									</span></td>

									</tr>
									<tr>
										<td class="left">面试职位</td>
										<td colspan="2"><input type="text" id="job" onblur="checkJob(this.value)" onchange="check(this.value,'interviewJob')" value="${resume.resumeJobIntension}" v-model="interviewJob" name="interviewJob" id="" /></td>
										<td><span id="interviewJobMsg">
									
								</span></td>

									</tr>
									<tr>

										<!--此处不好用voe-->
										<td class="left">面试时间</td>
										<td colspan="2"><input class="Wdate" id="time" onblur="checktime(this.value)" onchange="check(this.value,'interviewTime')" type="text" onClick="WdatePicker({el:this,dateFmt:'yyyy-MM-dd HH:mm:ss'})" />

										</td>

										<td><span id="interviewTimeMsg">
									
								</span></td>
									</tr>
									<tr>
										<td class="left">面试地点</td>
										<td colspan="2"><input type="text" id="adr" onblur="checkAddress(this.value)" onchange="check(this.value,'interviewAddress')" v-model="interviewAddress" placeholder="请输入面试地点" /></td>
										<td><span id="interviewAddressMsg">
									
								</span></td>
									</tr>
									<tr>
										<td class="left">联系人</td>
										<td colspan="2"><input type="text" id="username" onblur="checkusername(this.value)" onchange="check(this.value,'interviewAssociateUsername')" v-model="interviewAssociateUsername" placeholder="请输入联系人" /></td>
										<td><span id="interviewAssociateUsernameMsg">
									
								</span></td>
									</tr>
									<tr>
										<td class="left">联系人联系方式</td>
										<td colspan="2"><input type="text" id="userphone" onblur="checkAssociatePhone(this.value)" v-model="interviewAssociatePhone" onchange="check(this.value,'interviewAssociatePhone')" placeholder="请输入联系人联系方式" /></td>
										<td><span id="interviewAssociatePhoneMsg">
									
								</span></td>
									</tr>
									<tr>
										<td class="left">备注信息</td>
										<td colspan="2"><textarea id="textarea" name="interviewInfo"></textarea></td>
										<td><span id="">
									
								</span></td>
									</tr>

								</table>
								<div id="sendbox">
									<input type="checkbox" name="isSendMsg" id="isSendMsg" value="sendMessage" />
									<font size="2">同步发送短信</font>
								</div>
								<input type="submit" id="right-box-button-tijiao" value="提交" />
								<input type="reset" id="right-box-button-quxiao" value="取消" />
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>

</html>