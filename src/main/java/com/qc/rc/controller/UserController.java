package com.qc.rc.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.qc.rc.common.ServerResponse;
import com.qc.rc.entity.User;
import com.qc.rc.service.IUserService;

@Controller
public class UserController extends HttpServlet{
	
	@Autowired  
	private IUserService iuserService;
	
	
	@RequestMapping("/login.do")
	public ModelAndView login(String userAccount, String userPassword,HttpServletRequest request){
		System.out.println(userAccount);
		ServerResponse<User> user=iuserService.login(userAccount, userPassword);
		Map<String,Object> model = new HashMap<String,Object>();
		if(user.getData()!=null) {
			HttpSession session = request.getSession();
			session.setAttribute("sessionId", user);
			model.put("info", user.getMsg());
			return new ModelAndView("login1",model);
		}else{
			model.put("info", user.getMsg());
			return new ModelAndView("login1",model);
		}	
	}
	
	@RequestMapping("/register.do")
	public ModelAndView userRegister(String userAccount,String userPassword,
								String userName,Short userSex,Short userAge){
		
		User user = new User();
		user.setUserAccount(userAccount);
		user.setUserPassword(userPassword);
		user.setUserName(userName);
		user.setUserSex(userSex);
		user.setUserAge(userAge);
		
		int resultCount = iuserService.userRegister(user);
		
		Map<String,Object> model = new HashMap<String,Object>(); 
		if(resultCount==1) {
			model.put("info", "注册成功");
			return new ModelAndView("zhuce",model);
		}else {
			model.put("info", "注册失败");
			return new ModelAndView("zhuce",model);
		}
	}
	
	@RequestMapping("/test.do")
	public ModelAndView test(){
		Map<String,Object> model = new HashMap<String,Object>(); 
		return new ModelAndView("zhuce",model);
	}

	@RequestMapping("/test2.do")
	public ModelAndView test2(){
		Map<String,Object> model = new HashMap<String,Object>(); 
		return new ModelAndView("login1",model);
	}
}
