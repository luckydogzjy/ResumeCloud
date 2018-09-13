<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- 引入格式化日期标签  -->
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<link rel="stylesheet" type="text/css" href="../css/demo.css" />

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>显示interviews</title>
		<style type="text/css">
			th {
				width: 150px;
				BORDER-RIGHT: lightblue 1px dashed;
				BORDER-TOP: lightblue 1px dashed;
				BORDER-LEFT: lightblue 1px dashed;
				BORDER-BOTTOM: lightblue 1p dashed;
				background-color: lightblue;
			}
			
			button.hover {
				cursor: pointer;
			}
			
			#normal {
				width: 120px;
			}
			
			#right {
				width: 180px;
			}
			
			#interview_time {
				left: 50px;
				;
				top: 20px;
				position: absolute;
				width: 400px;
			}
			
			#interview_Job {
				left: 460px;
				top: 20px;
				position: absolute;
				width: 250px;
			}
			
			#addnewInterview {
				left: 830px;
				top: 20px;
				position: absolute;
				width: 130px;
				height: 30px;
			}
			
			#addbutton {
				width: 100%;
				height: 100%;
				border-radius: 5px;
				border: 0px;
				background-color: rgba(0, 204, 204, 1);
				font-family: 'Microsoft YaHei Negreta', 'Microsoft YaHei Normal', 'Microsoft YaHei';
				font-weight: 700;
				font-style: normal;
				font-size: 14px;
				color: white;
			}
			
			#interview_name_address_info {
				left: 55px;
				top: 60px;
				position: absolute;
				width: 300px;
				width: 650px;
				height: 30px;
				font: "微软雅黑";
			}
			
			#interviewInfo {
				width: 100%;
				height: 100%;
			}
			
			#s {
				left: 720px;
				top: 60px;
				width: 117px;
				height: 30px;
				position: absolute;
			}
			
			#r {
				left: 850px;
				top: 60px;
				width: 110px;
				height: 30px;
				position: absolute;
			}
			
			#search_button {
				width: 100%;
				height: 100%;
				border-radius: 5px;
				border: 0px;
				background-color: rgba(255, 102, 0, 1);
				font-family: 'Microsoft YaHei Negreta', 'Microsoft YaHei Normal', 'Microsoft YaHei';
				font-weight: 700;
				font-style: normal;
				font-size: 14px;
				color: white;
			}
			
			#reset_button {
				width: 100%;
				height: 100%;
				border-radius: 5px;
				border: 0px;
				background-color: rgba(0, 204, 204, 1);
				font-family: 'Microsoft YaHei Negreta', 'Microsoft YaHei Normal', 'Microsoft YaHei';
				font-weight: 700;
				font-style: normal;
				font-size: 14px;
				color: white;
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
			
			#pageChange {
				left: 50px;
				top: 500px;
				position: absolute;
			}
			
			#message {
				left: 880px;
				top: 500px;
				position: absolute;
			}
		</style>

	</head>
	<script src="../My97DatePicker/WdatePicker.js" type="text/javascript"></script>
	<script type="text/javascript">
		function lastchangepage() {
			var p = document.getElementById("pageNum").value;
			if(p == 1) {
				alert("已经是第一页了c");

			} else {
				p = p - 1;
				document.getElementById("pageNum").value = p;
				document.getElementById("actionform").submit();
			}
		}

		function nextchangepage() {
			var p = document.getElementById("pageNum").value;
			var sum = document.getElementById("allNum").value;
			if(p < sum) {
				p = Number(p) + 1;
				document.getElementById("pageNum").value = p;

				document.getElementById("actionform").submit();
			} else {
				alert("已经是最后一页了c");
			}
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

					<div id="">
						<form id="actionform" action="${pageContext.request.contextPath }/Interview/selectByCondition.do">

							<div id="interview_time">
								<span id="">
							面试时间
							</span>
								<input type="date" name="interviewTimeStart" id="" value="${interviewTimeStart}" placeholder="请输入起始时间" /> ~
								<input type="date" name="interviewTimeOver" id="" value="${interviewTimeOver}" placeholder="请输入终止时间" />
							</div>
							<div id="interview_Job">
								<span id="">
							面试职位
							</span>
								<input type="search" name="interviewJob" id="" value="${interviewJob}" placeholder="请输入面试职位" />
							</div>
							<div id="addnewInterview">
								<a href="${pageContext.request.contextPath}/Interview/addNewInterview.do?userId=1001&resumeId=3"><input type="button" id="addbutton" value="添加新的面试" /></a>
							</div>
							<div id="interview_name_address_info">
								<input type="search" name="interviewInfo" id="interviewInfo" value="${interviewInfo}" placeholder="请输入姓名，面试地点，面试信息等相关信息" />
							</div>
							<div id="s">
								<input type="submit" id="search_button" value="搜索" />

							</div>
							<div id="r">
								<input type="reset" id="reset_button" value="重置" />
							</div>

							<div id="table">
								<table cellSpacing="5" cellPadding="4">
									<tr>
										<th>
											<font size="2">姓名</font>
										</th>
										<th>
											<font size="2">面试地点</font>
										</th>
										<th>
											<font size="2">面试职位</font>
										</th>
										<th>
											<font size="2">面试时间</font>
										</th>
										<th>
											<font size="2">面试结果</font>
										</th>
										<th colspan="2">操作</th>
									</tr>
									<c:forEach items="${iPageBean.datalist}" var="interview">
										<tr>
											<td class="normal">
												<font size="2">
													<a href="">${interview.resume.resumeName}</a>
												</font>
											</td>
											<td class="normal">
												<font size="2">${interview.interviewAddress}</font>
											</td>
											<td class="normal">
												<font size="2">${interview.interviewJob}</font>
											</td>
											<!-- 格式化日期输出 -->
											<td class="normal">
												<fmt:formatDate value="${interview.interviewTime}" pattern="yyyy-MM-dd HH:mm" />
											</td>
											<td class="normal">
												<font size="2">
													<c:if test="${interview.interviewStatus==1}">成功 </c:if>
													<c:if test="${interview.interviewStatus==2}">待面试 </c:if>
													<c:if test="${interview.interviewStatus==3}">失败</c:if>
												</font>
											</td>
											<td class="right">
												<a href="updateById.do?id=${interview.interviewId}"><input type="button" value="修改" id="addBtn" /></a>
												<a href="${pageContext.request.contextPath}/Interview/deleteById.do?id=${interview.interviewId}"><input type="button" value="删除" id="deleteBtn" /></a>
												<a href=""><input type="button" value="面试结果" id="deleteBtn" /></a>
											</td>
										</tr>

									</c:forEach>

								</table>
							</div>

							<div id="message">
								<span class="color :lightblue">
							共检索到${iPageBean.allSize}条数据
						</span>
							</div>
							<div id="pageChange">

								<input type="button" name="" id="lastpage" value="上一页" onclick="lastchangepage()" />
								<input type="button" name="" id="nextpage" value="下一页" onclick="nextchangepage()" />
								<input type="hidden" name="pageNum" id="pageNum" value="${iPageBean.pageNum}" />
								<input type="hidden" id="allNum" value="${iPageBean.allPage}" />
								<span>
							第${iPageBean.pageNum}页 &nbsp;	共${iPageBean.allPage}页 
						</span>

							</div>

						</form>
					</div>

				</div>
			</div>

		</div>
	</body>

</html>