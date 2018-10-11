<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- 引入格式化日期标签  -->
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
	</head>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/demo.css"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/interviewCss/IVxiugai.css"/>
	<script src="${pageContext.request.contextPath}/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
	<script type="text/javascript">
			var isok = true;
			
			function isPhone(phone){
			    var info = document.getElementById("isphone1");	    
			    var RegCellPhone = /^(1)([0-9]{10})?$/;
			    var  falg=phone.search(RegCellPhone);
			    if (falg==-1){
			        info.innerText = "手机格式不正确";
			        info.style.color = 'red';
			        this.focus();
			        isok = false;
			    }else{
			    	info.innerText = "ok";
			    	info.style.color = 'green';
			    	isok = true;
			    }
			}
			
			function isPhone2(phone){
			    var info = document.getElementById("isphone2");	    
			    var RegCellPhone = /^(1)([0-9]{10})?$/;
			    var  falg=phone.search(RegCellPhone);
			    if (falg==-1){
			        info.innerText = "手机格式不正确";
			        info.style.color = 'red';
			        this.focus(); 
			        isok = false;
			    }else{
			    	info.innerText = "ok";
			    	info.style.color = 'green';
			    	isok = true;
			    }
			}
			
			function isJob(job){
			    var info = document.getElementById("isJob");	    

			    if (job==null||job==""){
			        info.innerText = "职位不能为空";
			        info.style.color = 'red';
			        this.focus();
			        isok = false;
			    }else{
			    	info.innerText = "ok";
			    	info.style.color = 'green';
			    	isok = true;
			    }
			}
			
			function isTime(time){
			    var info = document.getElementById("isTime");	    

			    if (time==null||time==""){
			        info.innerText = "时间不能为空";
			        info.style.color = 'red';
			        this.focus();
			        isok = false;
			    }else{
			    	info.innerText = "ok";
			    	info.style.color = 'green';
			    	isok = true;
			    }
			}
			
			function isPlace(place){
			    var info = document.getElementById("isPlace");	    

			    if (place==null||place==""){
			        info.innerText = "地点不能为空";
			        info.style.color = 'red';
			        this.focus();
			        isok = false;
			    }else{
			    	info.innerText = "ok";
			    	info.style.color = 'green';
			    	isok = true;
			    }
			}
			
			function isName(name){
			    var info = document.getElementById("isName");	    

			    if (name==null||name==""){
			        info.innerText = "姓名不能为空";
			        info.style.color = 'red';
			        this.focus();
			        isok = false;
			    }else{
			    	info.innerText = "ok";
			    	info.style.color = 'green';
			    	isok = true;
			    }
			}

	
		function xiugai(){
			if(isok){
				if(window.confirm('你确定要提交修改吗？')){
	                 //alert("确定");
	                 return true;
	              }else{
	                 //alert("取消");
	                 return false;
	             }
			}else{
				window.alert("参数错误！")
				return false;
			}
			
		} 
		
	</script>
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
					<div id="right-box-title">
						<h2 id="right-box-font1" class="right-box-title-font">修改</h2>
						<h2 id="right-box-font2">${interviewPojo.resume.resumeName}</h2>
						<h2 id="right-box-font3" class="right-box-title-font">面试安排详情</h2>
						<img id="right-box-img" src="${pageContext.request.contextPath}/img/u1501.png"/>
					</div>
					<hr id="right-box-hr1"/>
					<div id="">
						<div id="right-box-text">
						
						<form action="${pageContext.request.contextPath}/Interview/updateInterviewDetail.do"  method="post" >
							<input type="hidden" name="interviewId" value="${interviewPojo.interviewId}" />
							<input type="hidden" name="ResumeId" value="${interviewPojo.resume.resumeId}" />
							<div id="" class="right-box-text-item">
								<p class="right-box-item">姓名</p>
								<p class="right-box-item1">${interviewPojo.resume.resumeName}</p>
								<p id="right-box-text-jieguo">面试结果</p>
								
								<select name="interviewStatus">
									<option value="1">通过</option>
									<option value="2">待面试</option>
									<option value="3">未通过</option>
									
								</select>
							</div>
						
							<div id="" class="right-box-text-item">
								<p class="right-box-item">求职者联系方式</p>
								<input type="" name="resumePhone" id="" value="${interviewPojo.resume.resumePhone}" onblur="isPhone(this.value)"/>
								<span id="isphone1"></span>
							</div>
							<div id="" class="right-box-text-item">
								<p class="right-box-item">面试岗位</p>
								<input type="" name="interviewJob" id="" value="${interviewPojo.interviewJob}" onblur="isJob(this.value)"/>
								<span id="isJob"></span>
							</div>
							<div id="" class="right-box-text-item">
								<p class="right-box-item">面试时间</p>
								<input class="Wdate" name="StringinterviewTime" value="<fmt:formatDate value="${interviewPojo.interviewTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" type="text" onClick="WdatePicker({el:this,dateFmt:'yyyy-MM-dd HH:mm:ss'})" onblur="isTime(this.value)"/>
								<span id="isTime"></span>
							</div>
							<div id="" class="right-box-text-item">
								<p class="right-box-item">面试地点</p>
								<input type="" name="interviewAddress" id="" value="${interviewPojo.interviewAddress}" onblur="isPlace(this.value)"/>
								<span id="isPlace"></span>
							</div>
							<div id="" class="right-box-text-item">
								<p class="right-box-item">联系人</p>
								<input type="" name="interviewAssociateUsername" id="" value="${interviewPojo.interviewAssociateUsername}" onblur="isName(this.value)"/>
								<span id="isName"></span>
							</div>
							<div id="" class="right-box-text-item">
								<p class="right-box-item">联系人联系方式</p>
								<input type="" name="interviewAssociatePhone" id="" value="${interviewPojo.interviewAssociatePhone}" onblur="isPhone2(this.value)" />
								<span id="isphone2"></span>
							</div>
							<div id="" class="right-box-text-item">
								<p class="right-box-item">备注信息</p>
								<p class="right-box-item1"><textarea id="right-box-item1-beizhu" name="interviewInfo">${interviewPojo.interviewInfo}</textarea></p>
							</div>
							<div id="right-box-button">
								<input id="right-box-button-xiugai" type="submit" value="提交" onclick=" return xiugai()"/>
								<a id="right-box-button-shanchu" href="${pageContext.request.contextPath}/Interview/selectByCondition.do">取消</a>
							</div>
						</form>

							
						</div>

					</div>

				</div>
			</div>
		</div>

		
	</body>
</html>