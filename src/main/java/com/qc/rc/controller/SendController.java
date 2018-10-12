package com.qc.rc.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.httpclient.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qc.rc.common.GetUuid;
import com.qc.rc.common.MessageSender;
import com.qc.rc.entity.User;
import com.qc.rc.service.UserService;
import com.qc.rc.utils.DESUtil;



//查找是否被注册，发送短信 ，注册
/**
 * @author yuyang
 * 从数据库查找是否被注册
 * 如果没被注册{
 * 发送短信，保存验证码到缓存
 * }else{
 * 返回注册页面
 * 提示已经被注册
 * }
 * 
 *  将输入的验证码与缓存中的验证码比对
 *  如果一致{
 *  注册
 *  }不一致{
 *  }
 * 
 */
@Controller
public class SendController {

	@Autowired
	private  UserService userService;
	@Autowired
	private User user;

	@ResponseBody
	@RequestMapping(value="sendme.action" ,method = RequestMethod.POST)
	public HashMap<String, String> sendme(String inputphone,HashMap<String, String> m,
			HttpServletRequest request) throws Exception{
		
		//System.out.println(inputphone);
		//boolean b=userService.findUserIdByPhone(inputphone);
		//System.out.println(b);
		if(	userService.findUserIdByPhone(inputphone) == false ){//查找是否被注册
			
			m = MessageSender.batchSend(inputphone); //调用发送短信接口  
			int  result = Integer.parseInt(m.get("result")) ;   //获取到result值  
			if (result == HttpStatus.SC_OK) {  
				String code = m.get("code");
				//System.out.println("注册验证码："+code);		
				HttpSession  session = request.getSession(); //设置session  
				//session.setAttribute(phone+"code", code);    //将短信验证码放到session中保存，前面加phone的目的是做唯一标识
				session.setAttribute("vcode", code);
				session.setMaxInactiveInterval(60 * 3); //指定缓存释放时间
				m.put("message", "1");	
				m.put("inputphone", inputphone);
			}else{
				m.put("message", "2");
			}				
		}else{		
			m.put("message","3");
			System.out.println(m.toString());
			m.put("inputphone", inputphone);	
		}
		return m;

}

	/*if (result == HttpStatus.SC_OK) {
	 	InputStream in = method.getResponseBodyAsStream();
	 	ByteArrayOutputStream baos = new ByteArrayOutputStream();
	 	byte[] buffer = new byte[1024];
	 	int len = 0;
	 	while ((len = in.read(buffer)) != -1) {
		 	baos.write(buffer, 0, len);
	 	}
	 	System.out.println(URLDecoder.decode(baos.toString(), "UTF-8"));
	
	} else {
	 	throw new Exception("HTTP ERROR Status: " + method.getStatusCode() + ":" + method.getStatusText());
	} */


/*	@RequestMapping(value="comparecode.action" ,method = RequestMethod.POST)
	public HashMap<String, String> comparecode(String codelast,String inputphone, String password,
			HashMap<String, String> m,HttpServletRequest request){
		
		System.out.println(inputphone);
		System.out.println(password);
		System.out.println(codelast);
		//从session中比对发送的验证码
		HttpSession  session = request.getSession();//设置session
		//查询是否有注册时要把电话也查回来映射到实体类里
		String sessioncode =(String) session.getAttribute("vcode");
		System.out.println(sessioncode);
		if((codelast).equals(sessioncode)){//比对缓存
			user.setUserPhone(inputphone);
			user.setUserPassword(password);
			//调用service注册接口
			userService.userRegister(user);
			m.put("registeresult", "1");					
		}
		else{
			m.put("registeresult", "2");			
		}	
		return m;
	}*/
	
	@RequestMapping(value="/registe.action",method=RequestMethod.POST)
	public String registe(String phone,String password,String code,Model model,
			HttpSession session,HttpServletRequest request) throws Exception {
			//从session中比对发送的验证码
/*		System.out.println(code);
		System.out.println(phone);
		System.out.println(password);*/
			session = request.getSession();//设置session
			String sessioncode =(String) session.getAttribute("vcode");
			//System.out.println(sessioncode);
			if((code).equals(sessioncode)){//比对缓存
				String id = GetUuid.getuuid32();
				//System.out.println(id);
				user.setUserId(id);
				user.setUserPhone(phone);
				user.setUserPassword(DESUtil.getInstance().encode(password));
				//调用service注册接口
				userService.userRegister(user);
				model.addAttribute("phone", phone);
				model.addAttribute("password", password);
				model.addAttribute("msg", "√ 注册成功，请返回登陆");
		//model.addAttribute("lastId",lastId);
		}else{
			model.addAttribute("phone", phone);
			model.addAttribute("password", password);
			model.addAttribute("msg", "× 短信验证码错误，请重新输入");
		}
			return "user/registe";
	}
	
	@ResponseBody
	@RequestMapping(value="/login2.action",method=RequestMethod.POST)
	public HashMap<String, String> login2(String inputphone,HashMap<String, String> m,
			HttpServletRequest request,HttpSession  session) throws Exception{
		
		//System.out.println(inputphone);
		user = userService.findUserByPhone(inputphone);
		if(user != null){//查找是否被注册    
			m = MessageSender.batchSend(inputphone); //调用发送短信接口  
			int  result = Integer.parseInt(m.get("result")) ;   //获取到result值  
			if (result == HttpStatus.SC_OK) { 
				String code = m.get("code");
				//System.out.println("登录验证码："+code);		
				session = request.getSession(); //设置session  
				session.setAttribute("login2code", code);
				session.setMaxInactiveInterval(60 * 3); //指定缓存释放时间
				//验证码发送成功
				m.put("login2message", "1");	
				m.put("inputphone", inputphone);
			}else{
				//验证码发送失败
				m.put("login2message", "2");
			}				
		}else{
			//还未注册
			m.put("login2message", "3");
			//System.out.println(m.toString());
			m.put("inputphone", inputphone);	
		}
		return m;
}
	@RequestMapping(value="comparelogincode.action" ,method = RequestMethod.POST)
	public String comparelogin2(String inputphone,String code,Model model,
			HttpSession session,HttpServletRequest request) throws Exception {
			//从session中比对发送的验证码
			//System.out.println(phone);
			 //获取验证码session
			String sessioncode = (String)request.getSession().getAttribute("login2code");
			//System.out.println(sessioncode);
			if((code).equals(sessioncode)){//比对session缓存
				//设置登录id缓存
				String currentId = user.getUserId();
				request.getSession().setAttribute("currentUserId", currentId);
				model.addAttribute("inputphone", inputphone);
				model.addAttribute("msg", "√ 登录成功");
				return "user/first";
		//model.addAttribute("lastId",lastId);
		}else{
			model.addAttribute("inputphone", inputphone);
			model.addAttribute("msg", "× 短信验证码错误，请重新输入");
		}
			return "user/login2";
	}		
}


