<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/header.css"/>
	</head>
	<body>
		<div id="left">
			<div id="left-head-pic" align="center">
				<img src="${pageContext.request.contextPath}/img/u5.png" />
			</div>
			<div id="left-item">
				<div id="left-item-share" class="left-item" >
					<div class="left-item-box">
						<img class="left-item-pic"  src="${pageContext.request.contextPath}/img/u21.png" />
						<a class="left-item-a" href="${pageContext.request.contextPath}/SharingCenter/getSharingResumeListByCondition.do">共享中心</a>
					</div>
				</div>

				<div id="left-item-resume" class="left-item" >
					<div class="left-item-box">
					<img class="left-item-pic" src="${pageContext.request.contextPath}/img/u13.png" />
					<a class="left-item-a" href="${pageContext.request.contextPath}/Resume/resumeDisplay.do">简历管理</a>
					</div>
				</div>

				<div id="left-item-job" class="left-item">
					<div class="left-item-box">
					<img class="left-item-pic" src="${pageContext.request.contextPath}/img/u12.png" />
					<a class="left-item-a" href="#">职位管理</a>
					</div>
				</div>

				<div id="left-item-interview"  class="left-item">
					<div class="left-item-box">
					<img class="left-item-pic" src="${pageContext.request.contextPath}/img/u15.png" />
					<a class="left-item-a" href="${pageContext.request.contextPath}/Interview/showAllInterviews.do">面试安排</a>

					</div>
				</div>

				<div id="left-item-person"  class="left-item" >
					<div class="left-item-box">
					<img class="left-item-pic" src="${pageContext.request.contextPath}/img/u14.png" />
					<a class="left-item-a" href="#">个人中心</a>
					</div>
				</div>

				<div id="left-item-quit" class="left-item">
					<div class="left-item-box">
					<img class="left-item-pic" src="${pageContext.request.contextPath}/img/u19.png" />
					<a class="left-item-a" href="#">退出登录</a>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>
