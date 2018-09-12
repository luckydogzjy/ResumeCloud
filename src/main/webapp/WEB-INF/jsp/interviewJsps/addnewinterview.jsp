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
<style type="text/css">
	input{
		width: 250px;
	}
	.info{
		height: 80px;
	}
	.left{
		text-align: right;
	}
</style>
</head>
<link rel="stylesheet" type="text/css" href="../css/demo.css"/>
	<body>
		<div id="header">
		<jsp:include page="header.jsp" flush="true"/>
		</div>
		<div id="lefter">
		<jsp:include page="lefter.jsp" flush="true"/>
		</div>
		
		
		<div id="right" class="righter">
			<div id="right-box"class="right-box">
				<div id="title">
					准备为&nbsp;${resume.resumeName}&nbsp;安排面试
				</div>
				<div id="body">
					<form action="">
						<table>
							<tr>
								<td class="left">求职者姓名</td>
								<td colspan="2"><input type="text" name="" id="" value="${resume.resumeName}" /></td>
								<td>验证信息</td>
							</tr>
							<tr>
								<td class="left">求职者联系方式</td>
								<td colspan="2"><input type="text" name="" id="" value="${resume.resumePhone}" /></td>
								<td>验证信息</td>
							</tr>
							<tr>
								<td class="left">面试职位</td>
								<td colspan="2"><input type="text" name="" id="" value="${resume.resumeJobIntension}" /></td>
								<td>验证信息</td>
							</tr>
							<tr>
								<td class="left">面试时间</td>
								<td colspan="2"><input type="text" name="" id="" value="" placeholder="请输入面试时间"/></td>
								<td>验证信息</td>
							</tr>
							<tr>
								<td class="left">面试地点</td>
								<td colspan="2"><input type="text" name="" id="" value="" placeholder="请输入面试地点"/></td>
								<td>验证信息</td>
							</tr>
							<tr>
								<td class="left">联系人</td>
								<td colspan="2"><input type="text" name="" id="" value="" placeholder="请输入联系人"/></td>
								<td>验证信息</td>
							</tr>
							<tr>
								<td class="left">联系人联系方式</td>
								<td colspan="2"><input type="text" name="" id="" value="" placeholder="请输入联系人联系方式"/></td>
								<td>验证信息</td>
							</tr>
							<tr>
								<td class="left">备注信息</td>
								<td colspan="2" ><input class="info" name="" id="info" value="" /></td>
								<td>验证信息</td>
							</tr>
							
						</table>
						
						<input type="submit" value="提交"/>
						<input type="reset" value="取消"/>
					</form>
				</div>
			</div>
		</div>
	</body>
</html>