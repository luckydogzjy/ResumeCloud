<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<link rel="stylesheet" type="text/css" href="../css/demo.css" />
<link rel="stylesheet" type="text/css" href="../css/personal.css"/>


<script type="text/javascript" src="../js/jquery-2.1.1.js"></script>
<script type="text/javascript" src="../js/personalJs/jquery.js"></script>
<script type="text/javascript" src="../js/personalJs/jquery.easyui.min.js"></script>
<script type="text/javascript" src="../js/personalJs/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="../js/personalJs/jquery.validate.js"></script>
<script type="text/javascript" src="../js/personalJs/jquery.metadata.js"></script>
<script type="text/javascript" src="../js/personalJs/messages_cn.js"></script>

<script type="text/javascript" src="../js/personalJs/core.js"></script>
 
<script type="text/javascript">

    $(function () {

        $("#updateFrm").validate({
            rules: {
            	oldpwd: {
                    required: true,
                    minlength: 3,
                    maxlength: 16
                },

                newpwd: {
                    required: true,
                    minlength: 3,
                    maxlength: 16
                },
                confirm: {
                    required: true,
                    minlength: 3,
                    maxlength: 16,
                    equalTo: "#newpwd"
                }

            },
            messages: {

            	oldpwd: {
                    required: "请输入原密码",
                    maxlength: $.validator.format("密码不能小于{6}个字符"),  
                    minlength: $.validator.format("密码不能大于{16}个字符")

                },

                newpwd: {
                    required: "请输入密码",
                    maxlength: $.validator.format("密码不能小于{6}个字符"),  
                    minlength: $.validator.format("密码不能大于{16}个字符")
                },
                confirm: {
                    required: "请输入确认密码",
                    maxlength: $.validator.format("密码不能小于{6}个字符"),  
                    minlength: $.validator.format("密码不能大于{16}个字符"),
                    equalTo: "两次输入密码不一致"
/*                  equalTo:"#field"     输入值必须和#field相同
 */                }
            }
        });
    });


    function updatePwd() {
        var messageLabel = $("#errorcontent");
        messageLabel.html("");
        var o = $("#updateFrm").serializeObject();
        if (o.oldpwd == '') {
            messageLabel.html("<div style='color:red'>*原密码不能为空</div>");
            return false;
        }
        if (o.newpwd == '') {
            messageLabel.html("<div style='color:red'>*新密码不能为空</div>");
            return false;
        }
        if (o.confirm == '') {
            messageLabel.html("<div style='color:red'>*确认密码不能为空</div>");
            return false;
        }

        $.ajax({
            url: '${pageContext.request.contextPath}/personal/updatePassword.do',
            type: 'post',
 //          data:o,
 			data: {
 				oldpwd: $("#oldpwd").val(),
 				newpwd: $("#newpwd").val(),
 				confirm: $("#confirm").val()
 			},
            success: function (data) {
                var obj = JSON.parse(data);
                if (obj.status==1) {
                   alert(obj.message);
                    location.href = "${pageContext.request.contextPath}/gologin1.action";

                }
         		else if(obj.status==4){
         			 location.href = "${pageContext.request.contextPath}/gologin1.action";
                }
                else {
                    alert(obj.message);
                }
            }
        })

    }

</script>

<title></title>
</head>


<body>
<div id="box">
<div id="header">
			<jsp:include page="../common/header.jsp" flush="true" />
		</div>
		<div id="lefter">
			<jsp:include page="../common/lefter.jsp" flush="true" />
		</div>

		<div id="right">
		<div id="right-box" >

		
		<form method="post" id="updateFrm" action="${ctx}/updatePassword.do"> 
		 <p>
            <label for="oldpwd">原 密 码:</label>
            <input id="oldpwd" type="password" name="oldpwd"/>
       </p>


        <p>
            <label for="newpwd"> 新 密 码:</label>
            <input id="newpwd" type="password" name="newpwd"/>
        </p>

        <p>
            <label for="confirm">确认密码:</label>
            <input id="confirm" type="password" name="confirm"/>
        </p>

<br>
 
       <div class="mid-tips">
           <span id="errorcontent"></span>
       </div>
        <p style="text-align:center">
            <input class="submit" type="button" value="确定" onclick="updatePwd()"/>&nbsp;
            <input type="reset" value="重置">
        </p>
	</form>	
</div>
</div>
</div>
</body>
</html>