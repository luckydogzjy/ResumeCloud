<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>

	<head>
		<meta charset="UTF-8">
		<title></title>
		<script type="text/javascript" src="../js/jquery-2.1.1.js"></script>
		<script type="text/javascript" src="../js/personalJs/jquery.js"></script>
		<script type="text/javascript" src="../js/personalJs/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="../js/personalJs/easyui-lang-zh_CN.js"></script>
		<script type="text/javascript" src="../js/personalJs/jquery.validate.js"></script>
		<script type="text/javascript" src="../js/personalJs/jquery.metadata.js"></script>
		<script type="text/javascript" src="../js/personalJs/messages_cn.js"></script>	
		<script type="text/javascript" src="../bootstrap/js/jquery-3.3.1.min.js" ></script>
		<link rel="stylesheet" type="text/css" href="../css/demo.css" />		
		<link rel="stylesheet" type="text/css" href="../css/personal.css"/>
		<link rel="stylesheet" type="text/css" href="../bootstrap/css/bootstrap.css"/>
		<script type="text/javascript">
		
	/* 	function updateInfo(){
			var userPhone=$("#userPhone").val();
			var userName=$("#userName").val();
			var userBirthday=$("#userBirthday").val();
			var userCompany=$("#userCompany").val();
			var Data = {userPhone:userPhone,userName:userName,userBirthday:userBirthday,userCompany:userCompany};
			var url = "${pageContext.request.contextPath}/personal/select_update_info.do";
					
			$.post(url,Data,function(data,status){
				if(data==1){
					alert(data);
				}
			});
		} */
		

			
			function updateInfo(){
				$.ajax({
					url:'${pageContext.request.contextPath}/personal/select_update_info.do',
					type:'post',
					data:{
						userPhone:$("#userPhone").val(),
						userName:$("#userName").val(),
						userBirthday:$("#userBirthday").val(),
						userCompany:$("#userCompany").val(),
					},
					success:function(data){
						 alert(data);
					}
				});
				
			}

		</script>
		
	</head>

	<body>
		<div id="box">
	
		<div id="header">
			<jsp:include page="../common/header.jsp" flush="true" />
		</div>
		<div id="lefter">
			<jsp:include page="../common/lefter.jsp" flush="true" />
		</div>
		
		

		<div id="right">

			<div id="right-box" >
				<div id="modal-header">
					<h4 class="modal-title" id="myModalLabel">修改个人信息</h4>
					<HR size="4" width="360px" noshade>
				</div>
				<div id="upInfo" class="modal-body">
					
						<c:forEach items="${userList }" var="uesr">


							<div class="col-sm-10" id="Info">
								手机号:<input type="text" class="form-control" id="userPhone" name="userPhone" value="${uesr.userPhone}" />
							</div>

							<div class="col-sm-10" id="Info">
								姓&nbsp名:<input type="text" class="form-control" id="userName" name="userName" value="${uesr.userName}"/>
							</div>

							<div class="col-sm-10" id="Info">
							
								生&nbsp日:<input type="date" class="form-control" id="userBirthday" name="userBirthday" value="<fmt:formatDate value="${uesr.userBirthday}" pattern="yyyy-MM-dd" />"/>
							</div>																			

							<div class="col-sm-10" id="Info">
								公&nbsp司:<input type="text" class="form-control" id="userCompany" name="userCompany" value="${uesr.userCompany}"/>
							</div>

						
						<button type="button" id="but" onclick="updateInfo()">提交更改</button> 
						  
					</c:forEach>
					
				</div>

			

			<div id="other">
				<div id="">
					<h4 class="modal-title">其他操作</h4>
					<HR size="4" width="300px" noshade>
				</div>

				<div id="butten" >
					<div id="buts">
						<button type="button" class="btn btn-success">积分充值</button>&nbsp&nbsp
						<a href="${pageContext.request.contextPath}/personal/shopping_mall.do"><button type="button" class="btn btn-success">积分商城</button></a>
					</div>

					<div id="buts">

						<a href="${pageContext.request.contextPath}/personal/modifypassword.do"><button type="button" class="btn btn-warning">修改密码</button></a>&nbsp&nbsp
						<a href="${pageContext.request.contextPath }/searchPersonShare.action"><button type="button" class="btn btn-warning">共享记录</button></a>
					</div>

					<div id="buts">
						<a href="${pageContext.request.contextPath}/recharge.action?pn=1"><button type="button" class="btn btn-warning">充值记录</button></a>&nbsp&nbsp
						
						<a href="${pageContext.request.contextPath}/personal/ur_resume_exchange.do"><button type="button" class="btn btn-warning">简历兑换记录</button></a>
					</div>
				</div>
			</div>
			</div>
</div>
</div>
	</body>