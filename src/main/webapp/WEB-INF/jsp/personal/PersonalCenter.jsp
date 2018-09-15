<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>

	<head>
		<meta charset="UTF-8">
		<title></title>

		<script type="text/javascript" src="../../css/jquery-3.2.1.min.js"></script>
		<script type="text/javascript" src="../../css/ajax.js"></script>
		<script type="text/javascript" src="../../css/bootstrap.min.js"></script>

		<link rel="stylesheet" type="text/css" href="css/demo.css" />
		
		<script>
			
			
		</script>
		
	</head>

	<body>
		<div id="header">
			<jsp:include page="../common/header.jsp" flush="true" />
		</div>
		<div id="lefter">
			<jsp:include page="../common/lefter.jsp" flush="true" />
		</div>

		<div id="right">

			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title" id="myModalLabel">修改个人信息</h4>
				</div>
				<div id="u1837" class="ax_default line">
					<img id="u1837_img" class="img " src="img/u1837.png" />
				</div>
				 <span style="color:red">${msg}</span></br>
				<div id="upInfo" class="modal-body">
					<form method="post" action="${pageContext.request.contextPath }/select_update_info.do">
						<c:forEach items="${userList }" var="uesr">

							<label for="UserPhone" class="col-sm-2 control-label"></label>
							<div class="col-sm-10">
								手机号:<input type="text" class="form-control" id="userPhone" name="userPhone" value="${uesr.userPhone}" readonly="true"/>
							</div>

							<label for="UserName" class="col-sm-2 control-label"></label>
							<div class="col-sm-10">
								姓&nbsp名:<input type="text" class="form-control" id="userName" name="userName" value="${uesr.userName}"/ >
							</div>

							<label for="UserBirthday" class="col-sm-2 control-label"></label>
							<div class="col-sm-10">
								生&nbsp日:<input type="text" class="form-control" id="userBirthday" name="userBirthday" value="${uesr.userBirthday}"/>
							</div>																			
							<label for="UserCompany" class="col-sm-2 control-label"></label>
							<div class="col-sm-10">
								公&nbsp司:<input type="text" class="form-control" id="userCompany" name="userCompany" value="${uesr.userCompany}"/>
							</div>

						
<!--  						<input type="hidden" name="method" value="提交更改" onClick="showValue()" />
 --> 						<button type="submit" class="btn btn-primary" >提交更改</button> 
						  
					</c:forEach>
					</form>
				</div>

			</div>

			<div id="other">
				<div class="modal-header">
					<h4 class="modal-title">其他操作</h4>
				</div>

				<div id="u1837" class="ax_default line">
					<img id="u1837_img" class="img " src="img/u1837.png" />
				</div>

				<div id="butten" class="right-item">
					<div>
						<button type="button" class="btn btn-success">积分充值</button>
						<button type="button" class="btn btn-success">积分商城</button>
					</div>

					<div>
						<a href="${pageContext.request.contextPath}/modifypassword.do"><input type="button" value="修改密码" /></a>
						<button type="button" class="btn btn-warning">共享记录</button>
					</div>

					<div>
						<button type="button" class="btn btn-warning">充值记录</button>
						<button type="button" class="btn btn-warning">简历兑换记录</button>
					</div>
				</div>
			</div>

	</body>