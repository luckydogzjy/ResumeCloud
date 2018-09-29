<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
	<head>
		<meta charset="UTF-8">
		<title>ResumeCloud</title>
					
		<link rel="stylesheet" type="text/css" href="css/demo.css"/> 
		<!-- <link rel="stylesheet" type="text/css" href="../css/demo.css"/> -->
		
		<link rel="stylesheet" type="text/css" href="css/jobCss/job.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css">	
		
		<!-- <script type="text/javascript" src="js/jquery-2.1.1.js"></script> -->
		<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.js"></script>
 		<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
		<script src="${pageContext.request.contextPath}/bootstrap/js/jquery-3.3.1.min.js"></script>
		
		<script type="text/javascript">
		

		var jobId=0;

		function showDiv(ev,jId,jDate){
	       // alert(jDate);
        	var oEvent=ev||event;
        	var cD=$("#DivChange");
        	jobId=jId;
        	$('#jDate').val(jDate)
        	$('#DivChange').css({'left':oEvent.clientX+'px','top':oEvent.clientY+'px','display':'block'});
        }
   		
   		function cancelC(){
   			$('#DivChange').css({'display':'none'});
   			init();
   		}
   		
   		function subTime(){
   			var jobDate = $("#jDate").val()
   			//alert(jobDate)
   			$.ajax({
   				type:"POST",
   				url:"${pageContext.request.contextPath}/jobStatusOpen.do",
   				data:{
   					"jobId":jobId,
   					"jobDate":jobDate
   				},
   				success:function(data){
   					alert(data);
   					location.href="${pageContext.request.contextPath}/jobChangeStatus.do?jobId="+jobId+"&jobStatus=0"
   				}
   			})
   		}
   		
   		function init(){
   			jobId=0;
   			$('#jDate').val(null)
   		}
   		
   		$(document).ready(function(){
			//查询职位名
			$("#search").click(function(){
				var msg = $("#searchName").val();
				location.href="${pageContext.request.contextPath}/JobManage.do?searchName=" + msg;
				//$(location).attr("href","${pageContext.request.contextPath}/JobManage.do");
			});
			//添加职位
			$("#add").click(function(){
				location.href="${pageContext.request.contextPath}/jobAddView.do";
			});
		});
		
		//改变状态为关闭
		function jspopen(id,status){
			//alert("您已关闭该职位的招聘");
			location.href="${pageContext.request.contextPath}/jobChangeStatus.do?jobId="+id+"&jobStatus="+status;
		}

		//改变状态为打开
		function jspclose(id,status){
			location.href="${pageContext.request.contextPath}/jobChangeStatus.do?jobId="+id+"&jobStatus="+status;
		}
		
		//修改职位
		function modify(id){
			
			location.href="${pageContext.request.contextPath}/jobUpdateView.do?jobId="+id;
		}
		
		//删除职位
		function jspdelete(id){
			location.href="${pageContext.request.contextPath}/jobDelete.do?jobId="+id;
		}
		
		//职位模板
		function jsptemp(id){
			location.href="${pageContext.request.contextPath}/jobTemplateView.do?jobId="+id;
		}
   		
   		
   		
		</script>

		
		
		
		
		<style type="text/css">
			a:link{
			text-decoration:none;
			}
			a:visited{
			text-decoration:none;
			}
			a:hover{
			text-decoration:none;
			}
			a:active{
			text-decoration:none;
			}
		</style>

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

					
				
<!--开启-->			<div id="DivChange" style="display: none;left:20px; top: 5px; border:1px solid #51a351; border-radius:5px; position: fixed; width: 240px;height: 100px;background-color: #51a351;margin-top:30px;">
							<font style="color:#ffffff">截止时间：</font><input id="jDate" type="date" value="" /><br />
							<input  class="btn btn-default" type="button" value="提交"  onclick="subTime()" />
							<input class="btn btn-default" type="button" value="取消" onclick="cancelC()" />
					</div>
					
					<div id="job-search">


					 <%-- <div id="job-search">

						<img id="job-search-img" src="${pageContext.request.contextPath}/img/u607.png" />
						<form id="search" action="${pageContext.request.contextPath}/JobManage.do" method="POST">
							<input id="job-search-input" type="text" name="search" value="${search}"/>
							<input id="job-search-button" type="submit" value="搜索" />
						</form> --%>
						<div style="padding: 20px 20px 10px; float:left;">	
							 
									<div class="col-lg-6">
									
										<div class="input-group">
										
											<span class="input-group-btn">
												<button id="add" class="btn btn-default" type="button" >
													添加职位
												</button>
											</span>
										
										</div><!-- /input-group -->
										
									</div><!-- /.col-lg-6 -->
									
						</div>
						
						<div style="padding: 20px 20px 10px; float:right; margin-right:50px;">	
							 
									<div class="col-lg-6">
									
										<div class="input-group">
										
											<input type="text" class="form-control" name="search" id="searchName" placeholder="请输入职位名称" >
											<span class="input-group-btn">
												<button id="search" class="btn btn-default" type="button" >
													搜索
												</button>
											</span>
										
										</div><!-- /input-group -->
										
									</div><!-- /.col-lg-6 -->
									
						</div>
						
				 <%-- 	<div id="job-add">
						<input id="job-add-button" type="button" value="添加职位" onClick="location.href='${pageContext.request.contextPath}/jobAddView.do'"/> 
						<img id="job-add-img" src="${pageContext.request.contextPath}/img/u603.png" />
					</div>
					</div> --%>

					
		
		<div id="table-box">
				<table class="table table-striped" >
					<thead>
					<tr>
						<th style="text-align:center;">职位名称</th>
						<th style="text-align:center;">职位数量</th>
						<th style="text-align:center;">截止时间</th>
						<th style="text-align:center;">状态</th>
						<th style="text-align:center;">操作</th>
					</tr>
					</thead>
					<tbody>
					<c:forEach items="${job}" var="job">
					<tr>
						<td style="text-align:center;"><a href="${pageContext.request.contextPath}/jobDetails.do?jobId=${job.JOB_ID}">${job.JOB_NAME}</a></td>
						<td style="text-align:center;">${job.JOB_COUNT}</td>
						<td style="text-align:center;"><fmt:formatDate value="${job.JOB_END_TIME}" pattern="yyyy年MM月dd日" /></td>

						<td style="text-align:center;">
							<c:if test="${job.JOB_STATUS==1}">
								<%-- onClick="location.href='${pageContext.request.contextPath}/jobChangeStatus.do?jobId=${job.JOB_ID}&jobStatus=${job.JOB_STATUS}'" --%>
								<input class="btn btn-success active" id="button-status1" type="button" value="开启" onclick="jspopen(${job.JOB_ID},${job.JOB_STATUS})" />
							</c:if>
							<c:if test="${job.JOB_STATUS==0}">
				
								<input class="btn btn-danger active" id="button-status0" type="button" value="关闭" onclick="showDiv(null,${job.JOB_ID},'<fmt:formatDate value="${job.JOB_END_TIME}" pattern="yyyy-MM-dd" />')"/>

								
								<%-- <input class="btn btn-danger active" id="button-status0" type="button" value="关闭" onClick="jspclose(${job.JOB_ID},${job.JOB_STATUS})"/> --%>

							</c:if>
							
						</td>
						<td style="text-align:center;">
							<c:if test="${job.JOB_STATUS==1}">
								<%-- onClick="location.href='${pageContext.request.contextPath}/jobUpdateView.do?jobId=${job.JOB_ID}'" --%>
						 		<input class="btn btn-warning" id="button-modify" type="button" value="修改" onClick="modify(${job.JOB_ID})"/>
						 		<%-- onClick="location.href='${pageContext.request.contextPath}/jobDelete.do?jobId=${job.JOB_ID}'" --%>
						 		<input class="btn btn-danger" id="button-delete" type="button" value="删除" onClick="jspdelete(${job.JOB_ID})"/>
						 		<%-- onClick="location.href='${pageContext.request.contextPath}/jobTemplateView.do?jobId=${job.JOB_ID}'" --%>
						 		<input class="btn btn-info" id="button-template" type="button" value="生成模板" onClick="jsptemp(${job.JOB_ID})"/>
							</c:if>
							<c:if test="${job.JOB_STATUS==0}">
								<input class="btn btn-warning" id="button-modify" type="button" data-toggle="tooltip" data-placement="bottom" title="此时不允许修改" disabled="disabled" value="修改" onClick="modify(${job.JOB_ID})"/>
								<input class="btn btn-danger" id="button-delete" type="button" data-toggle="tooltip" data-placement="bottom" title="此时不允许删除" disabled="disabled" value="删除" onClick="jspdelete(${job.JOB_ID})"/>
								<input class="btn btn-info" id="button-template" type="button" data-toggle="tooltip" data-placement="bottom" title="此时不允许生成模板" disabled="disabled" value="生成模板" onClick="jsptemp(${job.JOB_ID})"/>
							</c:if>
								
						</td>
					</tr>	
					</c:forEach>
					</tbody>
				</table>
		</div>
		
		
		<div id="page" style="margin-top:350px;margin-left:700px;">
			<span>当前第${page.pageNum}页，一共${page.pages}页</span>
				<span>
					<a href="${pageContext.request.contextPath}/JobManage.do?page=${page.firstPage}">首页</a>
			        <a href="${pageContext.request.contextPath}/JobManage.do?page=${page.prePage}">上一页</a>
			        <a href="${pageContext.request.contextPath}/JobManage.do?page=${page.nextPage}">下一页</a>
			        <a href="${pageContext.request.contextPath}/JobManage.do?page=${page.lastPage}">尾页</a>			 
           </span>  
            
				
		</div>
		
		<!--<ul class="pagination pagination-lg">
			<li><a href="#">&laquo;</a></li>
		    <li><a href="#">1</a></li>
		    <li><a href="#">2</a></li>
		    <li><a href="#">3</a></li>
		    <li><a href="#">4</a></li>
		    <li><a href="#">5</a></li>
		    <li><a href="#">&raquo;</a></li>
		</ul><br>-->
		
		
		</div>
		</div>
		</div>

	</div>
	</body>
</html>
