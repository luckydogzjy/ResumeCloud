<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>积分充值记录</title>
<%-- <script src="${pageContext.request.contextPath }/js/jquery-2.1.1.js"></script> --%>
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" href="bootstrap/css/bootstrap-responsive.min.css" />
<script type="text/javascript" src="bootstrap/js/bootstrap.min.js" ></script>
<script type="text/javascript" src="bootstrap/js/jquery-3.3.1.min.js" ></script>
<script type="text/javascript">
				
		function deleteRecharge(index) {
			var id=document.getElementById(index).value;
			if(confirm('确实要删除该条记录吗?')) {
				$.post("<%=request.getContextPath()%>/deleteRecharge.action",{"rrId":id},function(data){
					alert("记录删除成功！");
					window.location.reload();
				});
			}
		}
	</script>
<style type="text/css">
#page{
    text-align: center;
    margin-top: 50px;
    margin-left: 500px;
    width: 480px;
    height: 320px;
    position: fixed;
    bottom: 0;
}
</style>
</head>
<link rel="stylesheet" type="text/css" href="css/demo.css"/>
<body>

<div id="box">
		<div id="header">
			<jsp:include page="../common/header.jsp" flush="true" />
		</div>
		<div id="lefter">
			<jsp:include page="../common/lefter.jsp" flush="true" />
		</div>
			
		<div id="right">
<%-- 			<div style="text-align:center"><a href="${pageContext.request.contextPath }/recharge.action">查询充值记录</a></div>
 --%>			<div id="right-box">
 					<div id="recharge-table">
 						<table width="100%" class="table table-hover table-striped">
					<tr>
						<th>订单号</th>
						<th>金额(单位：元)</th>
						<th>支付方式</th>
						<th>日期</th>
						<th>操作</th>
					</tr>
				<c:forEach items="${rechargeList}" var="recharge" varStatus="status">

					<tr>
						<td>${recharge.RR_ORDERNO}</td>
						<td>${recharge.RR_MONEY}</td>	 
						<td> 
							<c:if test="${recharge.RR_TYPE eq 1}">微信支付</c:if>
							<c:if test="${recharge.RR_TYPE eq 2}">支付宝支付</c:if>
						</td>
						<td><fmt:formatDate value="${recharge.RR_CREATE_TIME}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						<td id="index${status.index}">
							<input type="hidden" value ="${recharge.RR_ID}" id="${status.index}">
							<input class="btn btn-danger" type="button" value="删除记录" onclick="deleteRecharge(${status.index})"/>
						</td>	
					</tr>
				</c:forEach>
			</table>		
    	</div>
				
			<!--显示分页信息-->  
			  <div class="row" id="page">
			         <!--点击分页-->   
			         <span>当前第 ${pageInfo.pageNum} 页.总共 ${pageInfo.pages} 页.一共 ${pageInfo.total} 条记录  </span>         
			            <a href="${pageContext.request.contextPath}/recharge.action?pn=1">首页</a>
			            <!--上一页-->    
			              <c:if test="${pageInfo.hasPreviousPage}">
			                <a href="${pageContext.request.contextPath}/recharge.action?pn=${pageInfo.pageNum-1}" >上一页</a>
			              </c:if>	          
			                 <!--下一页--> 
			           	<c:if test="${pageInfo.hasNextPage}"> 
			                 <a href="${pageContext.request.contextPath}/recharge.action?pn=${pageInfo.pageNum+1}">下一页</a> 
			             </c:if>
			               <a href="${pageContext.request.contextPath}/recharge.action?pn=${pageInfo.pages}">尾页</a>
		      </div>
		  </div>
	</div>
</div>
</body>
</html>