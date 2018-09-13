<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
	</head>
	<link rel="stylesheet" type="text/css" href="css/demo.css"/>
	<link rel="stylesheet" type="text/css" href="css/interviewCss/IVxiangqing.css"/>
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
						<h2 id="right-box-font1" class="right-box-title-font">查看</h2>
						<h2 id="right-box-font2">hello</h2>
						<h2 id="right-box-font3" class="right-box-title-font">面试安排详情</h2>
						<img id="right-box-img" src="img/u1426.png"/>
					</div>
					<hr id="right-box-hr1"/>
					<div id="right-box-title2">
						<h2 class="right-box-title-font">面试安排详情</h2>
					</div>
					<hr id="right-box-hr2"/>
					<div id="">
						<div id="right-box-text1">
							<p class="right-box-item">求职者姓名:</p>
							<p class="right-box-item">&nbsp;联系方式:</p>
							<p class="right-box-item">&nbsp;面试岗位:</p>
							<p class="right-box-item">&nbsp;面试时间:</p>
							<p class="right-box-item">&nbsp;备注信息:</p>
						</div>
						
						<div id="right-box-text2">
							<p class="right-box-item">微微</p>
							<p class="right-box-item">12345678912</p>
							<p class="right-box-item">JAVA开发工程师</p>
							<p class="right-box-item">2018.6.6 14:30</p>
							<p class="right-box-item">......</p>
						</div>
						
						<div id="right-box-text3">
							<p class="right-box-item">&nbsp;&nbsp;&nbsp;&nbsp;联系人:</p>
							<p class="right-box-item">联系人联系方式:</p>
							<p class="right-box-item">&nbsp;&nbsp;&nbsp;面试地点:</p>
						</div>
						
						<div id="right-box-text4">
							<p class="right-box-item">9527</p>
							<p class="right-box-item">13333333333</p>
							<p class="right-box-item">北京</p>
						</div>
						
						<div id="right-box-text5">
							<a href="#">点击查看（新增）本次面试结果记录</a>
						</div>
					</div>
					<hr id="right-box-hr3"/>
					<div id="right-box-button">
						<input id="right-box-button-xiugai" type="button" value="修改" />
						<input id="right-box-button-shanchu" type="button" value="删除" />
						
						
					</div>
				</div>
			</div>
		</div>
	</body>
</html>
