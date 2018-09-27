package com.qc.rc.controller;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageInfo;
import com.qc.rc.common.FormParameterUtil;
import com.qc.rc.entity.User;
import com.qc.rc.entity.pojo.ResumePojo;
import com.qc.rc.entity.pojo.UserExchangeResumePojo;
import com.qc.rc.service.PersonalService;
import com.qc.rc.service.UserService;

@Controller
public class PersonalController {

	@Autowired
	private PersonalService personalService;
	@Autowired
	private User user;
	@Autowired
	UserService userService;
	@Autowired
	UserController userController;

	// 日志
	public static final Logger log = Logger.getLogger(PersonalController.class);
	
	@RequestMapping("/modifypassword.do")
	public ModelAndView updataPassword(HttpSession session,
			 @RequestParam("oldpwd") String oldpwd,
			 @RequestParam("newpwd")String newpwd,
			 @RequestParam("confirm")String confirm,ModelMap modelMap){
		
		 log.info("修改密码" + user.getUserPassword());//记录日志

		Map<String, Object> model = new HashMap<String, Object>();
		Object passWord= session.getAttribute("passWord");
		
		try {
			if(passWord==oldpwd){
				model.put("msg1", "密码正确");
			}
			try {
				if(newpwd==confirm){
					model.put("msg2", "密码一致");
					user.setUserPassword(confirm);
					
					int result = personalService.passwordUpdate(user);
					
					if (result == 1) {
						
						model.put("msg", "修改成功");
						return new ModelAndView("personal/modifypassword", model);
					}else{
						model.put("msg", "修改失败");
						return new ModelAndView("personal/modifypassword", model);
					}
				}
			} catch (Exception e) {
				model.put("msg2", "密码不一致");
				return new ModelAndView("personal/modifypassword", model);
			}
			
		} catch (Exception e) {
			model.put("msg1", "密码不正确");
			return new ModelAndView("personal/modifypassword", model);
		}
		return new ModelAndView("personal/PersonalCenter", model);

	}


	/**
	 * 进入个人中心自动获取info
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/PersonalCenter.do")
	public ModelAndView UserInfo_show(HttpServletRequest request, HttpServletResponse response) throws Exception{
		//获取session中的userId
		HttpSession session=request.getSession();
		Integer userId= (Integer) session.getAttribute("userId");
		//传入一个userId，返回一个User集合
		List<User> list = personalService.getUserInfo(userId);

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("userList", list);
		return new ModelAndView("personal/PersonalCenter", model);
	}

	/**
	 * 修改个人信息
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/select_update_info.do")
	public ModelAndView UserInfo_update(HttpSession session,
			@RequestParam("userName") String userName,
			 @RequestParam("userCompany")String userCompany,
			 @RequestParam("userBirthday")String userBirthday,ModelMap modelMap) throws Exception {

//		String userphone = request.getParameter("userPhone");
//		String userName = request.getParameter("userName");
//		String userCompany = request.getParameter("userCompany");
//		String userBirthday = request.getParameter("userBirthday");
//
		SimpleDateFormat birthday = new SimpleDateFormat("yyyyMMdd");// 小写的mm表示的是分钟
		java.util.Date date_birthday; // 定义一个日期类型的date_birthday
		try {
			date_birthday = birthday.parse(userBirthday); // 把传进来的userbirday转换成date_birther
		} catch (Exception e) {

			date_birthday = new Date();
			e.printStackTrace();
		}

		user.setUserName(userName);
		user.setUserCompany(userCompany);
		user.setUserBirthday(date_birthday);

//		user.setUserId(userId);

		Integer userId= (Integer) session.getAttribute("userId");
		
		Map<String, Object> model = new HashMap<String, Object>();

		// user.setUserId(userId);
		int result = personalService.updateInfo(user);

		if (result == 1) {
			model.put("msg", "修改成功");
			return new ModelAndView("personal/PersonalCenter", model);
		} else {
			model.put("msg", "修改失败");
			return new ModelAndView("personal/PersonalCenter", model);
		}
	}

	/**
	 * 显示hr兑换的简历
	 * @param session 
	 * 
	 * @return
	 */
	@RequestMapping("/PersonalCenter.do")
	public ModelAndView resumeExchangShow(ServletRequest session) throws Exception{

		// 根据登录的hr的Id的到他兑换的简历
		Integer userId= (Integer) session.getAttribute("userId");
		List<UserExchangeResumePojo> list = personalService.getAllExchangResume(userId);

		Map<String, Object> model = new HashMap<String, Object>();

		model.put("resumeList", list);

		return new ModelAndView("personal/ur_resume_exchange", model);

	}

	/**
	 * 多种查询方式
	 * @param request
	 * @param session
	 * @return
	 */
	
	@RequestMapping("/getHrExchangeResume.do")
	public ModelAndView getResumeListByCondition(HttpServletRequest request,HttpSession session) {
		
		String resumeName = null;
		String resumeJobIntension = null;
		// 性别
		String resumeSexStr = request.getParameter("resumeSex");
		Integer resumeSex = Integer.valueOf(resumeSexStr);

		String resumeEducationStr = request.getParameter("resumeEducation");
		Integer resumeEducation = Integer.valueOf(resumeEducationStr);

		String resumeWorkYearsStr = request.getParameter("resumeWorkYears");
		Integer resumeWorkYears = Integer.valueOf(resumeWorkYearsStr);

		String resumeGraduateInstitution = null;

		try {
			resumeGraduateInstitution = FormParameterUtil.changeCode(request.getParameter("resumeGraduateInstitution"));
			resumeName = FormParameterUtil.changeCode(request.getParameter("resumeName"));
			resumeJobIntension = FormParameterUtil.changeCode(request.getParameter("resumeJobIntension"));
		} catch (UnsupportedEncodingException e) {
			
			e.printStackTrace();
		}
		
		//获取session中的userId
		Integer userId= (Integer) session.getAttribute("userId");
		
		List<ResumePojo> list = personalService.getResumeListByCondition(userId, resumeName, resumeJobIntension,
				resumeSex, resumeEducation, resumeWorkYears, resumeGraduateInstitution);

		System.out.println("getResumeListByCondition里得到的list长度为" + list.size());

		Map<String, Object> model = new HashMap<String, Object>();

//		PageInfo<ResumePojo> pageResumePojo = new PageInfo<ResumePojo>(list);
//		model.put("page", pageResumePojo);

		model.put("resumeList", list);

		model.put("resumeName", resumeName);
		model.put("resumeJobIntension", resumeJobIntension);
		model.put("resumeSex", resumeSex);
		model.put("resumeEducation", resumeEducation);
		model.put("resumeWorkYears", resumeWorkYears);
		model.put("resumeGraduateInstitution", resumeGraduateInstitution);

		return new ModelAndView("personal/ur_resume_exchange", model);

	}

}
