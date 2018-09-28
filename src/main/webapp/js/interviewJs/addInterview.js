function copy() {
	var e = document.getElementById("msg");
	e.select(); //选择对象 
	tag = document.execCommand("Copy"); //执行浏览器复制命令
	if(tag) {
		alert('复制内容成功');
	}
}
//手机号判定
function isPhoneNo(phone) {
	var pattern = /^1[34578]\d{9}$/;
	return pattern.test(phone);
}
//非空判定
function isNull(str) {
	if(str == "") return true;
	var regu = "^[ ]+$";
	var re = new RegExp(regu);
	return re.test(str);
}

function checkphone() {
	var phone = $("#phone").val();
	if(isNull(phone)) {
		$("#resumePhoneMsg").text("请输入手机号").attr("style", "color:red;");
		return false;
	} else if(isPhoneNo(phone) == false) {
		$("#resumePhoneMsg").text("手机号输入不合法").attr("style", "color:red;");
		return false;
	} else if(phone != "${resume.resumePhone}") {
		$("#resumePhoneMsg").text("与简历库中的手机号不一致，添加面试时会更新简历库中的手机号").attr("style", "color:orange;");
	} else {
		$("#resumePhoneMsg").text("√").attr("style", "color:green;");
	}
	return true;
}

function checkJob() {
	var value = $("#job").val();
	if(isNull(value)) {
		$("#interviewJobMsg").text("请输入面试职位").attr("style", "color:red;");
		return false;
	} else {
		$("#interviewJobMsg").text("√").attr("style", "color:green;");

	}
	return true;
}

function checkresumename() {
	var value = $("#name").val();
	if(isNull(value)) {
		$("#resumeNameMsg").text("请输入求职者姓名").attr("style", "color:red;");
		return false;
	} else {
		$("#resumeNameMsg").text("√").attr("style", "color:green;");

	}
	return true;
}



function checktime() {
	var value = $("#time").val();
	if(isNull(value)) {
		$("#interviewTimeMsg").text("请输入面试时间").attr("style", "color:red;");
		return false;
	} else {
		$("#interviewTimeMsg").text("√").attr("style", "color:green;");
	}
	return true;
}

function checkAddress() {
	var value = $("#adr").val();
	if(isNull(value)) {
		$("#interviewAddressMsg").text("请输入面试地点").attr("style", "color:red;");
		return false;
	} else {
		$("#interviewAddressMsg").text("√").attr("style", "color:green;");
	}
	return true;
}

function checkusername() {
	var value = $("#username").val();
	if(isNull(value)) {
		$("#interviewAssociateUsernameMsg").text("请输入联系人姓名").attr("style", "color:red;");
		return false;
	} else {
		$("#interviewAssociateUsernameMsg").text("√").attr("style", "color:green;");
	}
	return true;
}

function checkAssociatePhone() {
	var value = $("#userphone").val();
	if(isNull(value)) {
		$("#interviewAssociatePhoneMsg").text("请输入手机号").attr("style", "color:red;");
		return false;
	} else if(isPhoneNo(value) == false) {
		$("#interviewAssociatePhoneMsg").text("手机号输入不合法").attr("style", "color:red;");
		return false;
	} else {
		$("#interviewAssociatePhoneMsg").text("√").attr("style", "color:green;");
	}
	return true;

}

function formcheck() {
	return checkphone()&&checkJob() &&checktime() &&checkAddress() &&  checkusername() &&   checkAssociatePhone();
}