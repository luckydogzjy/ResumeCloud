<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- 引入格式化日期标签  -->
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>Insert title here</title>
	<link rel="stylesheet" type="text/css" href="../css/demo.css" />

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>显示resume的所有interviews</title>
		<link rel="stylesheet" type="text/css" href="../css/interviewCss/selectinterview.css" />
		<style type="text/css">
			#right-box-title {
				position: absolute;
				left: 70px;
				top: 25px;
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
			
			.msg {
				width: 540px;
				height: 360px;
				left: 75px;
				top: 135px;
				overflow-y: scroll;
				position: absolute;
			}
			
			.updatebtn {
				margin-left: 40px;
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
			
			.timespan {
				left: 710px;
				top: 160px;
				position: absolute;
			}
		</style>
	</head>
	<script src="../My97DatePicker/WdatePicker.js" type="text/javascript"></script>
	<script src="../My97DatePicker/WdatePicker.js" type="text/javascript"></script>
	<script src="../js/jquery-2.1.1.js" type="text/javascript" charset="utf-8"></script>
	<script type="text/javascript">
		function update(index) {
			var interviewId = document.getElementById(index).value;
			var updateid = "recodeInfo" + index;
			var recodeInfo = document.getElementById(updateid).value;
			
			var msg=$("input[name='recodeInfo']").eq(index).val();
			if(recodeInfo ==msg){
				alert("未编辑信息,请求中断");
				return;
			}
			$.ajax({
				type: 'post',
				url: '${pageContext.request.contextPath}/Interview/updateRecodeInfo.do',
				data: 'interviewId=' + interviewId + '&recodeInfo=' + recodeInfo,
				success: function(data) {
					if(data == 1) {
						alert("编辑成功");
					} else if(data == 0) {
						alert("编辑失败");
					} else {
						alert("interviewId为空");
					}
				}
			});

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
			<div id="right" class="righter">
				<div id="right-box" class="right-box">
					<div id="right-box-title">
						<h2 id="right-box-font1" class="right-box-title-font">	${resumeInterviews.resumeName}的面试记录如下</h2>
					</div>
					<hr id="right-box-hr1" />

					<div class="timespan">
						<font size="2">面试时间</font>
						<ul>
							<c:forEach items="${resumeInterviews.interviews}" var="interview">
								<li>
									<a href="#${interview.interviewTime}">
										<fmt:formatDate value="${interview.interviewTime}" pattern="yyyy年MM月dd日 HH:mm" /> </a>
								</li>
							</c:forEach>
						</ul>
					</div>
					<div class="msg">
						<c:forEach items="${resumeInterviews.interviews}" var="interview" begin="0" step="1" varStatus="status">
							面试时间：
							<a name="${interview.interviewTime}">
								<fmt:formatDate value="${interview.interviewTime}" pattern="yyyy年MM月dd日 HH:mm" /> </a> <br /> 面试职位：
							<font size="2">${interview.interviewJob}</font> <br /> 面试地点：
							<font size="2">${interview.interviewAddress}</font> <br /> 面试结果：
							<font size="2">
								<c:if test="${interview.interviewStatus==1}">成功 </c:if>
								<c:if test="${interview.interviewStatus==2}">待面试 </c:if>
								<c:if test="${interview.interviewStatus==3}">失败</c:if>
							</font> <br /> 面试记录：
							<br /> <br />
							<textarea style="width: 350px; height: 50px;" name="recodeInfo" id="recodeInfo${status.index}">${interview.interviewRecodeInfo}</textarea>
							<input type="button" class="updatebtn" name="" id="" value="保存编辑" onclick="update(${status.index})" /> <br />
							<input type="hidden" name="" id="${status.index}" value="${interview.interviewId}" " />
							<input name="recodeInfo" type="hidden" value="${interview.interviewRecodeInfo}" />
							 -------------------------------------------------------- <br />
						</c:forEach>
					</div>

				</div>
			</div>
		</div>
	</body>

</html>