<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- 引入格式化日期标签  -->
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<link rel="stylesheet" type="text/css" href="../css/demo.css" />
<script src="../My97DatePicker/WdatePicker.js" type="text/javascript"></script>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>显示interviews</title>
		<link rel="stylesheet" type="text/css" href="../css/interviewCss/selectinterview.css"/>

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
							<input class="Wdate" name="interviewTimeStart" type="search" value="${interviewTimeStart}" placeholder="选择起始时间" onClick="WdatePicker({el:this,dateFmt:'yyyy-MM-dd HH:mm:ss'})" /> 
							~
							<input class="Wdate" name="interviewTimeOver"  id="" value="${interviewTimeOver}"  placeholder="选择终止时间" type="search" onClick="WdatePicker({el:this,dateFmt:'yyyy-MM-dd HH:mm:ss'})" /> 
							
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
											<select name="timeOrder">
												<option value="default">默认</option>
												<option value="up">↑</option>
												<option value="down">↓</option>
											</select>
										</th>
										<th>
											<font size="2">面试结果</font>
											<select name="interviewResult">
												<option value="0">所有</option>
												<option value="1">成功</option>
												<option value="2">待面试</option>
												<option value="3">失败</option>
											</select>
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
												<a href="${pageContext.request.contextPath}/Interview/deleteById.do?id=${interview.interviewId}"><input type="button" value="删除" id="deleteBtn"  /></a>
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
							
							<c:if test="${iPageBean.allSize>0}">
							<div id="pageChange">

								<input type="button" name="" id="lastpage" value="上一页" onclick="lastchangepage()" />
								<input type="button" name="" id="nextpage" value="下一页" onclick="nextchangepage()" />
								<input type="hidden" name="pageNum" id="pageNum" value="${iPageBean.pageNum}" />
								<input type="hidden" id="allNum" value="${iPageBean.allPage}" />
								<span>
								第${iPageBean.pageNum}页 &nbsp;	共${iPageBean.allPage}页 
								</span>

							</div>
							</c:if>
						</form>
					</div>

				</div>
			</div>

		</div>
	</body>

</html>