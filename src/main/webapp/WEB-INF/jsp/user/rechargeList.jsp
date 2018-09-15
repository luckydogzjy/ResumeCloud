<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>积分充值记录</title>

</head>
<link rel="stylesheet" type="text/css" href="css/demo.css"/>
<body>

<div id="header">
		<jsp:include page="header.jsp" flush="true"/>
		</div>
		<div id="lefter">
		<jsp:include page="lefter.jsp" flush="true"/>
		</div>
		
		
		<div id="right">
			<div><a href="${pageContext.request.contextPath }/recharge.action">查询充值记录</a></div>
			<div id="right-box">
				<table width="100%" border=1>
					<tr>
						<td>订单号</td>
						<td>金额(单位：元)</td>
						<td>支付方式</td>
						<td>日期</td>
					</tr>
				<c:forEach items="${rechargeList}" var="recharge">

					<tr>
						<td>${recharge.RR_ORDERNO}</td>
						<td>${recharge.RR_MONEY}</td>	 
						<td> 
							<c:if test="${recharge.RR_TYPE eq 1}">微信支付</c:if>
							<c:if test="${recharge.RR_TYPE eq 2}">支付宝支付</c:if>
						</td>
						<td><fmt:formatDate value="${recharge.RR_CREATE_TIME}" pattern="yyyy-MM-dd HH:mm:ss"/></td>	
					</tr>
				</c:forEach>
				</table>
			</div>
		</div>


	 	
</body>
</html>