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
<body>
<a href="${pageContext.request.contextPath }/recharge.action">查询充值记录</a>

<table width="50%" border=1>
<tr>
	<td>订单号</td>
	<td>金额(单位：元)</td>
	<td>支付方式</td>
	<td>日期</td>
	<td>操作</td>
</tr>
<c:forEach items="${rechargeList}" var="recharge">

<tr>
	<td>${recharge.RR_ORDERNO}</td>
	<td>${recharge.RR_MONEY}</td>
	<%-- <c:if test="${rechargeList.RR_TYPE eq 1}">	
	</c:if> --%>
	<td>${recharge.RR_TYPE}</td>

	<td><fmt:formatDate value="${recharge.RR_CREATE_TIME}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	<td><a href="${pageContext.request.contextPath }/recharge.action">删除</a></td>	
</tr>
</c:forEach>
</table>
	 	
</body>
</html>