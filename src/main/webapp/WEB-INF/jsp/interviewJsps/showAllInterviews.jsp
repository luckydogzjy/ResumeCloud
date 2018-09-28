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
		<link rel="stylesheet" type="text/css" href="../css/interviewCss/selectinterview.css"/>

	</head>
	<script src="../My97DatePicker/WdatePicker.js" type="text/javascript"></script>
	<script src="../My97DatePicker/WdatePicker.js" type="text/javascript"></script>
	<script src="../js/jquery-2.1.1.js" type="text/javascript" charset="utf-8"></script>
	<script type="text/javascript">
		function pageChange(p) {
			$("#pageNum").val(p);
			$("#actionform").submit();
		}
		function resert(){
			location.href="${pageContext.request.contextPath}/Interview/selectByCondition.do";
		}
		
		function sub(){
			$("#actionform").submit();
		}
		function del(index){
		var msg = "确认删除？";
		if(confirm(msg) == true){
			var interviewId=document.getElementById(index).value;
			var delid="index"+index;
			document.getElementById(delid).parentElement.remove(); 
			$.ajax({
				type:'post',
			    url:'${pageContext.request.contextPath}/Interview/deleteById.do',	
				data:'id='+interviewId,
				success:function(data){
					if(data == 1){
						alert("删除成功");
					}
					else{
						alert("删除失败");
					}
				} 
			});
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
							<input class="Wdate" id="startTime" name="startTime" type="search" value="${startTime}" placeholder="选择起始时间" onClick="WdatePicker({el:this,dateFmt:'yyyy-MM-dd HH:mm:ss'})" /> 
							~
							<input class="Wdate" id="overTime" name="overTime"  value="${overTime}"  placeholder="选择终止时间" type="search" onClick="WdatePicker({el:this,dateFmt:'yyyy-MM-dd HH:mm:ss'})" /> 
							
							</div>
							<div id="interview_Job">
								<span id="">
							面试职位
							</span>
								<input type="search" name="interviewJob" id="interviewJob" value="${interviewJob}" placeholder="请输入面试职位" />
							</div>
							<div id="addnewInterview">
								<a href="${pageContext.request.contextPath}/Interview/addNewInterview.do"><input type="button" id="addbutton" value="添加新的面试" /></a>
							</div>
							<div id="interview_name_address_info">
								<input type="search" name="interviewInfo" id="interviewInfo" value="${interviewInfo}" placeholder="请输入姓名，面试地点，面试信息等相关信息" />
							</div>
							<div id="s">
								<input type="submit" id="search_button" value="搜索" />

							</div>
							<div id="r">
								<input type="button" id="reset_button" value="重置" onclick="resert()"/>
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
											<select name="sort" onchange="sub()">
											<c:if test = "${sort == 1}">
													<option value="1">↑</option>
													<option value="0">↓</option>												
											</c:if>
											<c:if test = "${sort == 0}">												
													<option value="0">↓</option>
													<option value="1">↑</option>	
											</c:if>
											</select>
										</th>
										<th>
											<font size="2">面试结果</font>
											<select name="status" onchange="sub()">
											<c:if test = "${status == 0}">
												<option value="0">所有</option>
												<option value="1">成功</option>
												<option value="2">待面试</option>
												<option value="3">失败</option>
											</c:if>
											<c:if test = "${status == 1}">
												<option value="1">成功</option>
												<option value="0">所有</option>
												<option value="2">待面试</option>
												<option value="3">失败</option>
											</c:if>
											<c:if test = "${status == 2}">
												<option value="2">待面试</option>
												<option value="0">所有</option>
												<option value="1">成功</option>
												<option value="3">失败</option>
											</c:if>
											<c:if test = "${status == 3}">
												<option value="3">失败</option>
												<option value="0">所有</option>
												<option value="1">成功</option>
												<option value="2">待面试</option>
												
											</c:if>
											
												
											</select>
										</th>
										<th colspan="2">操作</th>
									</tr>
									<c:forEach items="${pageInfo.list}" var="interview" begin="0" step="1" varStatus="status" >
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
											<td class="right" id="index${status.index}">
												<input type="hidden" value ="${interview.interviewId}" id="${status.index}">
												<a href="updateById.do?id=${interview.interviewId}"><input type="button" value="修改" id="updateBtn" /></a>
												<input type="button" value="删除" id="deleteBtn" onclick="del(${status.index})" />
												<a href="resumeInterviews.do?resumeId=${interview.resume.resumeId}"><input type="button" value="面试结果" id="interviewBtn" /></a>
																			
											</td>
										</tr>

									</c:forEach>

								</table>
							</div>

								
							
							<div id="message">
								<span class="color :lightblue">
								共检索到${pageInfo.total}条数据
								</span>
							</div>
							<c:if test="${pageInfo.total<=0}">
							<input type="hidden" name="pageNum" id="pageNum" value="1" />
							</c:if>
							<c:if test="${pageInfo.total>0}">
							<div id="pageChange">
								<input type="hidden" name="pageNum" id="pageNum" value="" />
								<input type="button" value="首页" onclick="pageChange('1')"/>
			       				<input type="button" value="上一页" onclick="pageChange(${pageInfo.prePage})"/>
			       				<input type="button" value="下一页" onclick="pageChange(${pageInfo.nextPage})"/>
			       				<input type="button" value="尾页" onclick="pageChange(${pageInfo.pages})"/>
								<span>
								第${pageInfo.pageNum}页 &nbsp;	共${pageInfo.pages}页 
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