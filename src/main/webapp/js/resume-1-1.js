
	function checkname(){
  		var strname =document.getElementById('resume_name').value;
  		if(strname.length>6){
  			document.getElementById("s1").innerHTML="姓名不能超过10汉字";
  		}else{
  		if(strname==""|| strname.length ==0){
  			document.getElementById("s1").innerHTML="姓名不能为空"; 
  		}else 
		document.getElementById("s1").innerHTML=""; 	
		}
  	}
	
	
	
	function checkphone(){
		var strphone = document.getElementById('resume_phone').value;
		/*var phone = /^0*(1)\d{10}$/;*/
		var phone = /^1[1234567890]{10}$/;
  		if(!phone.exec(strphone)){
  			document.getElementById("s2").innerHTML="电话输入不正确";
  		}else{
  		if(strphone==""|| strphone.length ==0){
  			document.getElementById("s2").innerHTML="电话不能为空"; 
  		}else {
		document.getElementById("s2").innerHTML=""; 
		}
		}
  	}
	
	
	function checkemail(){
		var stremail = document.getElementById('resume_email').value;
		var email = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\.[a-zA-Z0-9_-])+/;
		if(!email.exec(stremail)){
  			document.getElementById("s3").innerHTML="邮箱输入不正确";
  		}else{
  		if(stremail==""|| stremail.length ==0){
  			document.getElementById("s3").innerHTML="邮箱不能为空"; 
  		}else {
		document.getElementById("s3").innerHTML=""; 
		}
		}
		
	}
	
	
		function checkaddress(){
		var straddress = document.getElementById('resume_address').value;
  		if(straddress.length>=99){
  			document.getElementById("s4").innerHTML="地址过长";
  		}else{
  		if(straddress==""|| straddress.length ==0){
  			document.getElementById("s4").innerHTML="地址不能为空"; 
  		}else {
		document.getElementById("s4").innerHTML=""; 
		}
		}
  	}
		
		
		
		
		
		function checkjob(){
		var strjob = document.getElementById('resume_job_intension').value;
  		if(strjob.length>=10){
  			document.getElementById("s6").innerHTML="意向输入过长";
  		}else{
  		if(strjob==""|| strjob.length ==0){
  			document.getElementById("s6").innerHTML="意向不能为空"; 
  		}else {
		document.getElementById("s6").innerHTML=""; 
		}
		}
  	}
		
		
		
		function checkschool(){
		var strschool = document.getElementById('resume_graduate_institution').value;
  		if(strschool.length>=100){
  			document.getElementById("s7").innerHTML="院校输入过长";
  		}else{
  		if(strschool==""|| strschool.length ==0){
  			document.getElementById("s7").innerHTML="院校不能为空"; 
  		}else {
		document.getElementById("s7").innerHTML=""; 
		}
		}
  	}
		
		
		
		
		  function checkbir() {
            var date = document.getElementById("resume_birthday").value;
            if (date == "") {
                document.getElementById("s5").innerHTML="日期输入不正确"; 
            } else {
                if (new Date(date).getDate() == date.substring(date.length - 2)) {
    				 document.getElementById("s5").innerHTML="";
                } else {
                    document.getElementById("s5").innerHTML="日期输入不正确";
                }
            }
        }
		
		
		
		
		
		
		function checkyears(){
		var stryears = document.getElementById('resume_work_years').value;
		var years = /^\d*$/;
  		if(!years.exec(stryears)){
  			document.getElementById("s8").innerHTML="年限输入不正确";
  		}else{
  		if(stryears==""|| stryears.length ==0){
  			document.getElementById("s8").innerHTML="年限不能为空"; 
  		}else {
		document.getElementById("s8").innerHTML=""; 
		}
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
			
			var s1 = document.getElementById('s1').innerHTML;
			var s2 = document.getElementById('s2').innerHTML;			
			var s3 = document.getElementById('s3').innerHTML;		
			var s4 = document.getElementById('s4').innerHTML;			
			var s5 = document.getElementById('s5').innerHTML;		
			var s6 = document.getElementById('s6').innerHTML;
			var s7 = document.getElementById('s7').innerHTML;
			var s8 = document.getElementById('s8').innerHTML;
			
			
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
			
	
			
		}
	



  String.prototype.getLength = function(){   
  //得到字符串的真实长度   
      var l=this.length;   
      var n=l   
      for (var i=0;i<l;i++){   
        if (this.charCodeAt(i)<0||this.charCodeAt(i)>255) 
  n++   
      }   
      return  n   
  }   
    
  function check(obj){   
    var   max=200;   
    if(obj.value.getLength()>max){   
    alert("最多可输入"+max/2+"字，请重新输入！");   
    obj.focus();   
    obj.select();   
    }   
  }  
