package com.qc.rc.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qc.rc.entity.RechargeRecord;
import com.qc.rc.entity.User;
import com.qc.rc.entity.pojo.SharingCenterPojo;
import com.qc.rc.service.UserService;
import com.qc.rc.utils.BASE64Util;



@Controller
public class UserController {
	
	@Autowired  
	private UserService userService;
	@Autowired
	private User user;
		
	@RequestMapping(value="/login.action",method=RequestMethod.POST)
	public String login(String userPhone,String userPassword,Model model,HttpSession session) throws Exception {
		//System.out.println(userPhone+userPassword);
		  user=userService.findUserByPhone(userPhone, userPassword);
		if(user != null){
			//设置当前登录seesion
			user.setUserPassword(BASE64Util.getInstance().encode(userPassword));
			session.setAttribute("rcuser", user);
			model.addAttribute("msg", "登录成功！");
			return "user/first";
		}
		else{
			model.addAttribute("msg","× 账号或密码错误");
			return "user/login1";
		}			
	}
	
	@RequestMapping(value="/recharge.action",method=RequestMethod.GET)
	public ModelAndView rechargeList(@RequestParam(required = false,defaultValue = "1",value = "pn")Integer pn,
			ModelAndView modelAndView,HttpServletRequest request)throws Exception {
		//取session
		User user = (User)request.getSession().getAttribute("rcuser");
        PageHelper.startPage(pn,3);
		List<RechargeRecord> rechargeList = userService.findRecord(user.getUserId());
	/*	for(RechargeRecord rechargeList2: rechargeList){			
			System.out.println(rechargeList2);
		}*/
        PageInfo<RechargeRecord> pageInfo = new PageInfo<RechargeRecord>(rechargeList);	
        modelAndView.addObject("rechargeList", rechargeList);
		modelAndView.addObject("pageInfo", pageInfo);
		modelAndView.setViewName("user/rechargeList");	
		return modelAndView;
		
		}
	@RequestMapping(value="/searchPersonShare.action",method=RequestMethod.GET)
	public ModelAndView personShareList(@RequestParam(required = false,defaultValue = "1",value = "pn")Integer pn,
			ModelAndView modelAndView,HttpServletRequest request) throws Exception {
		
		//session获取当前用户ID
		User user = (User)request.getSession().getAttribute("rcuser");
		PageHelper.startPage(pn,1);
		List<SharingCenterPojo> personShareLists = userService.getPersonShareResume(user.getUserId());
		PageInfo<SharingCenterPojo> pageInfo = new PageInfo<SharingCenterPojo>(personShareLists);	
		if (personShareLists != null) {
		//	System.out.println(personShareLists.size());
			modelAndView.addObject("personShareLists", personShareLists);
			modelAndView.addObject("pageInfo", pageInfo);
		} else {
			
			modelAndView.addObject("msg","暂无共享数据");
		}
		modelAndView.setViewName("user/PersonalShareRecordList");
		return modelAndView;
	}
	
	@RequestMapping(value="/deleteRecharge.action",method=RequestMethod.POST)
	public String delRechargeRecord(String rrId) throws Exception {
		//System.out.println(rrId);
		
		userService.deleteRecharge(rrId);
		return "user/rechargeList";
	}
	
	@RequestMapping(value="/delPersonShare.action",method=RequestMethod.POST)
	public String delPersonShare(String scId) throws Exception {
		System.out.println(scId);
		
		userService.deleteShareById(scId);
		return "user/PersonalShareRecordList";
		
	}

	
	@RequestMapping(value="/goRegiste.action")
	public String goRegiste() throws Exception {
		
		return "user/registe";
	}
	
	@RequestMapping(value="/gologin1.action")
	public String goLogin1() throws Exception {
		
		return "user/login1";
	}
	
	@RequestMapping(value="/gologin2.action")
	public String goLogin2() throws Exception {
		
		return "user/login2";
	}
	
	@RequestMapping(value="/gorechargerecord.action")	
	public String gorechargerecord() throws Exception {
		
		return "user/rechargeList";
	}
	
	@RequestMapping(value="/gopersonshare.action")
	public String gopersonshare() throws Exception {
		
		return "user/PersonalShareRecordList";
	}
}
	
