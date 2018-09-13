package com.qc.rc.controller;


import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


import com.qc.rc.entity.RechargeRecord;
import com.qc.rc.entity.User;
import com.qc.rc.service.UserService;



@Controller
public class UserController {
	
	@Autowired  
	private UserService userService;
		
	@RequestMapping("/login.action")
	public String login(String userPhone,String userPassword,Model model,HttpSession session){
		
		User user=userService.findUserByPhone(userPhone, userPassword);
		if(user != null){
			session.setAttribute("user", user);
			model.addAttribute("msg", "登录成功！");
			return "user/success";
		}
		else{
			model.addAttribute("msg","账号或密码错误");
			return "user/login1";
		}			
	}
		
	@RequestMapping("/recharge.action")
	public ModelAndView itemsList() throws Exception{
		
		List<RechargeRecord> rechargeList=userService.findRecord();	
		ModelAndView modelAndView = new ModelAndView();		
		modelAndView.addObject("rechargeList", rechargeList);
		modelAndView.setViewName("user/rechargeList");	
		return modelAndView;
		
		}	
	
/*	@RequestMapping("/registe.action")
	public String regist(User user,Model model){
		
		System.out.println("用户注册："+user.getName()+user.getPassword());
		
		user.setId(1);
		userService.regist(user);	
		model.addAttribute("msg", "注册成功");
		//注册成功后跳转success.jsp页面
		return "login";
		
	
	}*/
	@RequestMapping("/registe.action")
	public String registe(String userPhone,String userPassword,Model model,HttpSession session){
		
		User user=new  User();
		user.setUserPhone(userPhone);
		user.setUserPassword(userPassword);
		int lastId=userService.userRegister(user);
		model.addAttribute("msg", "注册成功,请登录");
		model.addAttribute("lastId",lastId);
		return "user/login1";
	}
	}
	
