<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
	</head>
	<link rel="stylesheet" type="text/css" href="css/demo.css"/>
	<link rel="stylesheet" type="text/css" href="css/interviewCss/IVxiugai.css"/>
	<body>
		<div id="box">
		
			<div id="header">
			<jsp:include page="../common/header.jsp" flush="true"/>
			</div>
			<div id="lefter">
			<jsp:include page="../common/header.jsp" flush="true"/>
			</div>
			
			
			<div id="right">
				<div id="right-box">
					<div id="right-box-title">
						<h2 id="right-box-font1" class="right-box-title-font">修改</h2>
						<h2 id="right-box-font2">hello</h2>
						<h2 id="right-box-font3" class="right-box-title-font">面试安排详情</h2>
						<img id="right-box-img" src="img/u1501.png"/>
					</div>
					<hr id="right-box-hr1"/>
					<div id="">
						<div id="right-box-text">
							<div id="" class="right-box-text-item">
									<p class="right-box-item">姓名</p>
									<p class="right-box-item1">微微</p>
									<p id="right-box-text-jieguo">面试结果</p>
									<select name="">
										<option value="通过">通过</option>
										<option value="未通过">未通过</option>
										<option value="待面试">待面试</option>
									</select>
							</div>
						
							<div id="" class="right-box-text-item">
								<p class="right-box-item">求职者联系方式</p>
								<input type="" name="" id="" value="" />
								<span>提示信息</span>
							</div>
							<div id="" class="right-box-text-item">
								<p class="right-box-item">面试岗位</p>
								<input type="" name="" id="" value="" />
								<span>提示信息</span>
							</div>
							<div id="" class="right-box-text-item">
								<p class="right-box-item">面试时间</p>
								<input type="date" name="" id="" value="" />
								<span>提示信息</span>
							</div>
							<div id="" class="right-box-text-item">
								<p class="right-box-item">面试地点</p>
								<input type="" name="" id="" value="" />
								<span>提示信息</span>
							</div>
							<div id="" class="right-box-text-item">
								<p class="right-box-item">联系人</p>
								<input type="" name="" id="" value="" />
								<span>提示信息</span>
							</div>
							<div id="" class="right-box-text-item">
								<p class="right-box-item">联系人联系方式</p>
								<input type="" name="" id="" value="" />
								<span>提示信息</span>
							</div>
							<div id="" class="right-box-text-item">
								<p class="right-box-item">备注信息</p>
								<p class="right-box-item1"><textarea id="right-box-item1-beizhu"></textarea></p>
							</div>
							
						</div>

					</div>
					<div id="right-box-button">
						<input id="right-box-button-xiugai" type="button" value="提交" />
						<input id="right-box-button-shanchu" type="button" value="取消" />
					</div>
				</div>
			</div>
		</div>

		
	</body>
</html>