 	function checkname(){
  		
  		var strname = $("#resume_name").val();
  		if(strname.length>6){
  			$("#s1").text("姓名不能超过10汉字");
  		}else{
  			if(strname.length==0){
  				$("#s1").text("姓名不能为空");
  			}else			
  			$("#s1").text("");
  		}
  	}
 	
 		
 	function checkphone(){
  		
  		var strphone = $("#resume_phone").val();
  		var phone = /^1[1234567890]{10}$/;
  		if(strphone.length==0){
  			$("#s2").text("电话不能为空");
  		}else{
  			if(!phone.exec(strphone)){
  				$("#s2").text("电话输入不正确");
  			}else			
  			$("#s2").text("");
  		}
  	}

	
 	function checkemail(){
  		
  		var stremail = $("#resume_email").val();
  		var email = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\.[a-zA-Z0-9_-])+/;
  		if(stremail.length==0){
  			$("#s3").text("邮箱不能为空");
  		}else{
  			if(!email.exec(stremail)){
  				$("#s3").text("邮箱输入不正确");
  			}else			
  			$("#s3").text("");
  		}
  	}
 	


 	function checkaddress(){
  		
  		var straddress = $("#resume_address").val();
  		if(straddress.length==0){
  			$("#s4").text("地址不能为空");
  		}else{
  			if(straddress.length>=99){
  				$("#s4").text("地址输入超过限制");
  			}else			
  			$("#s4").text("");
  		}
  	}
 	

	
	function checkbir(){	
  		var date = $("#resume_birthday").val();
  		if(date.length==0 || date==""){
  			$("#s5").text("生日不能为空");
  		}else{
  			if (new Date(date).getDate() == date.substring(date.length - 2)) {
  				$("#s5").text("");
  			}else			
  			$("#s6").text("生日输入不正确");
  		}
  	}
	
 	function checkjob(){	
  		var strjob = $("#resume_job_intension").val();
  		if(strjob.length==0){
  			$("#s6").text("意向不能为空");
  		}else{
  			if(strjob.length>=99){
  				$("#s6").text("意向输入超过限制");
  			}else			
  			$("#s6").text("");
  		}
  	}
 	

	
	function checkschool(){	
  		var strschool = $("#resume_graduate_institution").val();
  		if(strschool.length==0){
  			$("#s7").text("院校不能为空");
  		}else{
  			if(strschool.length>=99){
  				$("#s7").text("院校输入超过限制");
  			}else			
  			$("#s7").text("");
  		}
  	}
	

		
	function checkyears(){	
  		var stryears = $("#resume_work_years").val();
  		var years = /^\d*$/;
  		if(stryears.length==0){
  			$("#s8").text("年限不能为空");
  		}else{
  			if(!years.exec(stryears)){
  				$("#s8").text("年限输入不正确");
  			}else			
  			$("#s8").text("");
  		}
  	}
	
	function promote()
		{	
			var pic = $("#wenjian").val();
			var patn = /\.jpg$|\.jpeg$|\.png$|\.gif$/i;
			var wenjian = /\.doc$|\.docx$|\.xls$|\.xlsx$|\.txt$/;
			if (!patn.test(pic)||!wenjian.test(pic)) 
			{
				$("#s9").text("图片仅支持jpg、png、gif、jpeg格式");
				$("#s10").text("文件仅支持dox、xls、txt格式");
			}
			if(patn.test(pic)||wenjian.test(pic)){
				$("#s9").text("");
				$("#s10").text("");
			}
		}
		
function checkinput(){
				checkname();
				checkaddress();
				checkemail();
				checkphone();
				checkbir();
				checkjob();
				checkschool();
				checkyears();
				
				var s1 = $("#s1").text();
				var s2 = $("#s2").text();
				var s3 = $("#s3").text();
				var s4 = $("#s4").text();
				var s5 = $("#s5").text();
				var s6 = $("#s6").text();
				var s7 = $("#s7").text();
				var s8 = $("#s8").text();
				var s9 = $("#s9").text();
			
				if(s1.length!=0){
					return false;
				}
				if(s2.length!=0){
					return false;
				}
				if(s3.length!=0){
					return false;
				}
				if(s4.length!=0){
					return false;
				}
				if(s5.length!=0){
					return false;
				}
				if(s6.length!=0){
					return false;
				}
				if(s7.length!=0){
					return false;
				}
				if(s8.length!=0){
					return false;
				}
				if(s9.length!=0){
					return false;
				}
				
				
				
		
				
			}