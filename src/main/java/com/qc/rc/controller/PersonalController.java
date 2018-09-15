package com.qc.rc.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.qc.rc.entity.User;
import com.qc.rc.entity.pojo.ResumePojo;
import com.qc.rc.service.PersonalService;

@Controller

public class PersonalController {
	
	@Autowired
	private PersonalService personalService;
	@Autowired
	private User user;
	
	public static String userPhone="15542431051";
	public static Integer userId = 1;
	
	
	@RequestMapping("/modifypassword.do")
	public ModelAndView  updataPassword(HttpServletRequest request){
		
		String user_phone = request.getParameter("user_phone");
		String password = request.getParameter("password");
		String new_password = request. getParameter("new_password");
		
		//查找数据库中 传user_phone
		
		Map<String,Object> model = new HashMap<String,Object>();
		
		if( userPhone==user_phone){
			
			user.setUserPassword(new_password);
			user.setUserPhone(user_phone);

			
			int result = personalService.passwordUpdate(user);	
			
			if(result==1){
				
				model.put("msg", "修改成功");
				return new ModelAndView("personal/modifypassword",model);
			}else {
				model.put("msg", "修改失败");
				return new ModelAndView("personal/modifypassword",model);
				
			}
		}else {
//			model.put("msg", "手机号或者密码不正确");
			return new ModelAndView("personal/modifypassword",model);
		}	
		
	}
	
	
//	public ModelAndView  modifyPassword(
//			@RequestParam("user_Phone") String userPhone,
//			@RequestParam("password")String password,
//			@RequestParam("new_password")String newpassword,ModelMap modelMap){
//	
//		int result =personalService.passwordUpdate(user);
//		
//		Map<String,Object> model = new HashMap<String,Object>();
//		
//	
////		int result = ModifyPasswordService.modifyPasswordByUserPhone(userPhone, newpassword);
////		System.out.println(result);
////		modelMap.addAttribute("msg", "修改密码成功！");
////		return new ModelAndView("modifypassword",model);
//		
//		
//		if () {
//			modelMap.addAttribute("msg", "用户手机号不正确！");
//			return new ModelAndView("modifypassword",model);
//		} else {
//			if (password.equals(ModifyPasswordService.getPasswordByUserphone(userPhone))) {
//				ModifyPasswordService.modifyPasswordByUserPhone(userPhone, newpassword);
//				modelMap.addAttribute("msg", "修改密码成功！");
//				return new ModelAndView("modifypassword",model);
//			} else {
//				modelMap.addAttribute("msg", "密码错误！");
//				return new ModelAndView("modifypassword",model);
//			}
//	}
		
	/**
	 * 进入个人中心自动获取info
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/PersonalCenter.do")
	public ModelAndView UserInfo_show(HttpServletRequest request,  HttpServletResponse response){
		
				
		List<User> list = personalService.getUserInfo(userId);
				
		Map<String,Object> model = new HashMap<String,Object>(); 
		model.put("userList", list);
		return new ModelAndView("personal/PersonalCenter",model);
	}
	
	
	/**
	 * 修改个人信息
	 * @param request
	 * @return
	 */
	@RequestMapping("/select_update_info.do")
	public ModelAndView UserInfo_update(HttpServletRequest request){
		
//		String userphone = request.getParameter("userPhone");
		String userName = request.getParameter("userName");
		String userCompany = request.getParameter("userCompany");
		String userBirthday = request.getParameter("userBirthday");
		
		SimpleDateFormat birthday=new SimpleDateFormat("yyyyMMdd");//小写的mm表示的是分钟
		java.util.Date date_birthday; //定义一个日期类型的date_birthday
		try {
			date_birthday = birthday.parse(userBirthday); //把传进来的userbirday转换成date_birther
		} catch (Exception e) {
			
			date_birthday= new Date();
			e.printStackTrace();
		}
		
		
		user.setUserName(userName);
		user.setUserCompany(userCompany);
		user.setUserBirthday(date_birthday);
		
		user.setUserId(userId);
		
		Map<String,Object> model = new HashMap<String,Object>(); 
		
//		user.setUserId(userId);
		int result= personalService.updateInfo(user);
		
		if(result==1){
			model.put("msg", "修改成功");
			return new ModelAndView("personal/PersonalCenter",model);
		}else {
			model.put("msg", "修改失败");
			return new ModelAndView("personal/PersonalCenter",model);
		}
	}
	
	
}
