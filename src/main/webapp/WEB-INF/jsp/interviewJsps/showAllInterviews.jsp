<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- 引入格式化日期标签  -->
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>显示interviews</title>
	</head>
	<script type="text/javascript">
		function lastchangepage(){
			var p = document.getElementById("pageNum").value;
			if(p==1){
				alert("已经是第一页了c");
				
			}
			else{
				p=p-1;
				document.getElementById("pageNum").value=p;	
				document.getElementById("actionform").submit();
			}
		}
		function nextchangepage(){
			var p = document.getElementById("pageNum").value;
			var sum = document.getElementById("allNum").value;
			if(p<sum){
				p = Number(p)+1;
				document.getElementById("pageNum").value=p;	
			
				document.getElementById("actionform").submit();
			}
			else{
				alert("已经是最后一页了c");
			}
		}
	</script>
	
	<body>
		<form id="actionform" action="${pageContext.request.contextPath }/Interview/selectByCondition.do">
			<div id="search_condition">
				<div id="interview_time">
					<span id="">
					面试时间
				</span>
					<input type="text" name="interviewTimeStart" id="" value=" <fmt:formatDate value="${interviewTimeStart}" pattern="yyyy-MM-dd HH:mm"/>" placeholder="请输入起始时间" />
					~~
					<input type="text" name="interviewTimeOver" id="" value=" <fmt:formatDate value="${interviewTimeOver}" pattern="yyyy-MM-dd HH:mm"/>" placeholder="请输入终止时间" />
				</div>
				<div id="interview_Job">
					<span id="">
					面试职位
				</span>
					<input type="text" name="interviewJob" id="" value="${interviewJob}" placeholder="请输入面试职位" />
				</div>
				<div id="interview_name_address_info">
					<input type="text" name="interviewInfo" id="" value="${interviewInfo}" placeholder="请输入姓名，面试地点，面试信息等相关信息" />
				</div>

				<div id="search_message">
					<input type="submit" id="search_button" value="search" />
				</div>

			</div>

			<table border="1" cellspacing="" cellpadding="2">
				<tr>
					<th>姓名</th>
					<th>面试地点</th>
					<th>面试职位</th>
					<th>面试时间</th>
					<th>面试结果</th>
					<th colspan="2">操作</th>
				</tr>
				<c:forEach items="${iPageBean.datalist}" var="interview">
					<tr>
						<td>
							<a href="">${interview.resume.resumeName}</a>
						</td>
						<td>${interview.interviewAddress}</td>
						<td>${interview.interviewJob}</td>
						<!-- 格式化日期输出 -->
						<td> <fmt:formatDate value="${interview.interviewTime}" pattern="yyyy-MM-dd HH:mm"/></td>
						<td>
							<c:if test="${interview.interviewStatus==1}">成功 </c:if>
							<c:if test="${interview.interviewStatus==2}">待面试 </c:if>
							<c:if test="${interview.interviewStatus==3}">失败</c:if>
						</td>
						<td>
							<a href="updateById.do?id=${interview.interviewId}"><input type="button" value="修改" id="addBtn" /></a>
							<a href="${pageContext.request.contextPath}/Interview/deleteById.do?id=${interview.interviewId}"><input type="button" value="删除" id="deleteBtn" /></a>
						</td>
					</tr>

				</c:forEach>

			</table>
			<div id="pageChange">
				
				<input type="button" name="" id="lastpage" value="上一页" onclick="lastchangepage()"/>
				<input type="button" name="" id="nextpage" value="下一页" onclick="nextchangepage()"/>
				<input type="hidden" name="pageNum" id="pageNum" value="${iPageBean.pageNum}" />
				<input type="hidden"  id="allNum" value="${iPageBean.allPage}" />
				<span >
					第${iPageBean.pageNum}页 &nbsp;	共${iPageBean.allPage}页 
				</span>
			</div>

		</form>
	</body>

</html>