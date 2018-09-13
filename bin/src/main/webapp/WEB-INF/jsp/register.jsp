<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

</head>
<body>
<!--中心-->
    <div class="content">
    	<div class="register-box">
        	<div class="wrap">
                <div class="register-box-con">
                    <div class="register-box-cen-form clearfix mar-bottom20">
                    	<input type="text" class="login-box-cen-form-input w358" placeholder="请输入手机号" id="reg_phone" />
                        <label class="err err-top40" id="reg_phone_text">手机号错误</label>
                    </div>
                    <div class="register-box-cen-form clearfix mar-bottom20">
                    	<input type="text" class="login-box-cen-form-input w358" placeholder="设置登录密码" id="reg_password" />
                        <label class="err err-top40" id="reg_password_text">密码错误</label>
                    </div>
                    <div class="register-box-cen-form clearfix mar-bottom20">
                    	<input type="text" class="login-box-cen-form-input w228" placeholder="短信验证码" id="reg_mescode" />
                        <button class="login-box-cen-form-mes w120 mar-left10" id="reg_mescode_btn" able="able">获取验证码</button>
                        <label class="err err-top40" id="reg_mescode_text">短信验证码错误</label>
                    </div>
                    <div class="register-box-cen-form clearfix mar-bottom20 mar-top50">
                    	<input type="submit" value="注册" class="login-box-cen-form-button w380" id="reg_submit" />
                    </div>
                </div>
            </div>        	
        </div>	
    </div>

	<a href="${pageContext.request.contextPath }/login.jsp">去登录</a>
	
</body>
</html>