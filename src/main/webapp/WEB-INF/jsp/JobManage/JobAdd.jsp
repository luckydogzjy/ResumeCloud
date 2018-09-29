<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<link rel="stylesheet" type="text/css" href="css/demo.css"/>
		<link rel="stylesheet" type="text/css" href="css/jobCss/add.css">
		
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css">	
		
		
		<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.js"></script>
 		<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
		<script src="${pageContext.request.contextPath}/bootstrap/js/jquery-3.3.1.min.js"></script>
		
		
		<script type="text/javascript">
		
				function backout(){
					location.href="${pageContext.request.contextPath}/JobManage.do"
				}
				
				function checkValue(e){
					//alert($(e).val());
					if($(e).val()==""){
						$(e).attr('placeholder','不能为空');
					} 					
				}
				
				function sub(){
					var fal = 0
					var errArr = new Array()
					$("input").each(function(){
						if($(this).val()==""){
							errArr.push($(this).attr("name"))
							fal=1
							
						}
					})
					if(fal==0){
						document.getElementById("jobFormId").submit()
						alert("添加成功")
					}else{
						alert(errArr+"\n内容为空")
					}
				}
				
		</script>
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
				<h1>添加职位</h1>
				<hr>
				<form id="jobFormId" action="${pageContext.request.contextPath}/jobAdd.do" method="POST">
				<div id="right-box-east">
					<div id="jobName">
						<font id="" >职位名称:</font><input  onblur="checkValue(this)" type="text"  name="name"><br>
					</div>
					<div id="jobCount">
						<font id="" >职位数量:</font><input onblur="checkValue(this)" type="number" name="count" min="0"><br>
					</div>
					<div id="jobSalary">
						<font id="" >职位薪资:</font><input onblur="checkValue(this)" type="number" name="salary" min="0"><br>
					</div>
					<div id="jobDate">
						<font id="" >截止时间:</font><input id="timeV" type="date" name="endTime" ><br>
					</div>
					
				</div>
				<div id="right-box-west">
					<div id="jobIntroduciton">
						<div id="jobItro">
							<font>职位介绍:</font>
						</div>
						<textarea name="introduction"></textarea><br>
					</div>
					<div id="jobCondition">
						<div  id="jobCod" >
							<font>任职要求:</font>
						</div>
						<textarea name="condition"></textarea><br>
					</div>
					
				</div>
				
				<div id="btn">
					<input class="btn btn-default" id="submit00" type="button" value="提交" onclick="sub()">
					<input class="btn btn-default" id="cancel" type="button" value="取消" onclick="backout()" />
				</div>
			</form>
			</div>
		</div>
		</div>
	</body>
</html>
 
