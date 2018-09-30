<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>

	<head>
		<meta charset="UTF-8">
		<title></title>

		
		<script type="text/javascript" src="../bootstrap/js/bootstrap.min.js" ></script>
		<script type="text/javascript" src="../bootstrap/js/jquery-3.3.1.min.js" ></script>
		
		
		<link rel="stylesheet" type="text/css" href="../css/demo.css" />
		
		<link rel="stylesheet" type="text/css" href="../css/personal.css"/>
		
	</head>

	<body>
		<div id="header">
			<jsp:include page="../common/header.jsp" flush="true" />
		</div>
		<div id="lefter">
			<jsp:include page="../common/lefter.jsp" flush="true" />
		</div>

		<div id="right">

			<div id="right-box" >
				<div id="modal-header">
					<h4 class="modal-title" id="myModalLabel">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp修改个人信息</h4>
				</div>
				<div id="u1836" class="ax_default line">
					&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<img id="u1837_img" class="img " src="../img/u1837.png" />
				</div>
				 &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<span style="color:red">${msg}</span></br>
				<div id="upInfo" class="modal-body">
					<form method="post" action="${pageContext.request.contextPath }/personal/select_update_info.do">
						<c:forEach items="${userList }" var="uesr">

							<label for="UserPhone" class="col-sm-2 control-label"></label>
							<div class="col-sm-10" id="Info">
								手机号:<input type="text" class="form-control" id="userPhone" name="userPhone" value="${uesr.userPhone}" />
							</div>

							<label for="UserName" class="col-sm-2 control-label"></label>
							<div class="col-sm-10" id="Info">
								姓&nbsp 名:<input type="text" class="form-control" id="userName" name="userName" value="${uesr.userName}"/>
							</div>

							<label for="UserBirthday" class="col-sm-2 control-label"></label>
							<div class="col-sm-10" id="Info">
							
							<%-- <fmt:formatDate value="${uesr.userBirthday}" pattern="yyyy-MM-dd" /> --%>
								生&nbsp 日:<input type="date" class="form-control" id="userBirthday" name="userBirthday" value="<fmt:formatDate value="${uesr.userBirthday}" pattern="yyyy-MM-dd" />"/>
							</div>																			
							<label for="UserCompany" class="col-sm-2 control-label"></label>
							<div class="col-sm-10" id="Info">
								公&nbsp 司:<input type="text" class="form-control" id="userCompany" name="userCompany" value="${uesr.userCompany}"/>
							</div>

						
<!--  						<input type="hidden" name="method" value="提交更改" onClick="showValue()" />
 --> 						<button type="submit" id="but" >提交更改</button> 
						  
					</c:forEach>
					</form>
				</div>

			

			<div id="other">
				<div class="modal-header">
					<h4 class="modal-title">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp其他操作</h4>
				</div>

				<div id="u1837" class="ax_default line">
					&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<img id="u1837_img" class="img " src="../img/u1837.png" /></br>
				</div>

				<div id="butten" >
					<div id="buts">
						<button type="button" class="btn btn-success">积分充值</button>&nbsp&nbsp
						<button type="button" class="btn btn-success">积分商城</button>
					</div>

					<div id="buts">
						<a href="${pageContext.request.contextPath}/personal/modifypassword.do"><input type="button" value="修改密码" /></a>&nbsp&nbsp
						<button type="button" class="btn btn-warning">共享记录</button>
					</div>

					<div id="buts">
						<button type="button" class="btn btn-warning">充值记录</button>&nbsp&nbsp
						
						<a href="${pageContext.request.contextPath}/personal/ur_resume_exchange.do"><button type="button" class="btn btn-warning">简历兑换记录</button></a>
					</div>
				</div>
			</div>
</div>
	</body>