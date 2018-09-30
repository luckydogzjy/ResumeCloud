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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.github.pagehelper.PageInfo;
import com.qc.rc.common.FormParameterUtil;
import com.qc.rc.entity.User;
import com.qc.rc.entity.pojo.ResumePojo;
import com.qc.rc.entity.pojo.UserExchangeResumePojo;
import com.qc.rc.service.PersonalService;
import com.qc.rc.service.UserService;

@Controller
@RequestMapping("/personal")
public class PersonalController {

	@Autowired
	private PersonalService personalService;
	@Autowired
	private User user;
	@Autowired
	UserService userService;

	// 日志
	public static final Logger log = Logger.getLogger(PersonalController.class);

    //修改密码方法,第一步直接跳到页面
    @RequestMapping("/modifypassword.do")
    public String updateToPassword() {
        return "personal/modifypassword";
    }

	
	@RequestMapping("/updatePassword.do")
	@ResponseBody
	public Map<String, Object> updataPassword(HttpSession session,String oldpwd, String newpwd, String confirm) {

		log.info("修改密码" + user.getUserPassword());// 记录日志

		Map<String, Object> model = new HashMap<String, Object>();
		//合并有session后用
//		String userPassword = (String) session.getAttribute("userPassword");
//		String userId = (String) session.getAttribute("userId");
		 String userPassword="qwer123456";
		 String userId="1b786bc41114f67ae059cea5f1789d";

		if (!userPassword.equals(oldpwd)) {
			model.put("status", Boolean.FALSE);
			model.put("message", "原密码输入错误，请重试");
			return model;
		}

		if (!newpwd.equals(confirm)) {
			model.put("status", Boolean.FALSE);
			model.put("message", "两次密码输入不一致，请重试");
			return model;

		}
		user.setUserPassword(confirm);
		user.setUserId(userId);
		int result = personalService.passwordUpdate(user);
		if (result == 1) {

			model.put("status", Boolean.TRUE);
			model.put("message", "密码修改成功，请重新登录。");
			return model;
		} else {
			model.put("status",Boolean.FALSE);
			return  model;

		}

	}

	/**
	 * 进入个人中心自动获取info
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/PersonalCenter.do")
	public ModelAndView UserInfo_show(HttpServletRequest request, HttpServletResponse response, HttpSession session)
			throws Exception {
		// 获取session中的userId
		// HttpSession session=request.getSession();
		String userId = (String) session.getAttribute("userId");
		// 传入一个userId，返回一个User集合
		userId = "1b786bc41114f67ae059cea5f1789d";
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
	public ModelAndView UserInfo_update(HttpSession session, String userPhone,String userName, String userCompany, String userBirthday,
			ModelMap modelMap) throws Exception {

		SimpleDateFormat birthday = new SimpleDateFormat("yyyyMMdd");// 小写的mm表示的是分钟
		java.util.Date date_birthday; // 定义一个日期类型的date_birthday
		try {
			date_birthday = birthday.parse(userBirthday); // 把传进来的userbirday转换成date_birther
		} catch (Exception e) {

			date_birthday = new Date();
			e.printStackTrace();
		}
		user.setUserPhone(userPhone);
		user.setUserName(userName);
		user.setUserCompany(userCompany);
		user.setUserBirthday(date_birthday);

		user.setUserId("1b786bc41114f67ae059cea5f1789d");

		// String userId= (String) session.getAttribute("userId");

		Map<String, Object> model = new HashMap<String, Object>();

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
	 * 
	 * @param session
	 * 
	 * @return
	 */
	@RequestMapping("/ur_resume_exchange.do")
	public ModelAndView resumeExchangShow(ServletRequest session) throws Exception {

		// 根据登录的hr的Id的到他兑换的简历
		String userId = (String) session.getAttribute("userId");
		userId = "1b786bc41114f67ae059cea5f1789d";
		List<UserExchangeResumePojo> list = personalService.getAllExchangResume(userId);

		Map<String, Object> model = new HashMap<String, Object>();

		model.put("resumeList", list);

		return new ModelAndView("personal/ur_resume_exchange", model);

	}

	/**
	 * 多种查询方式
	 * 
	 * @param request
	 * @param session
	 * @return
	 */

	@RequestMapping("/getHrExchangeResume.do")
	public ModelAndView getResumeListByCondition(HttpServletRequest request, HttpSession session, Integer page) {

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

		// 获取session中的userId
		String userId = (String) session.getAttribute("userId");
		userId = "1b786bc41114f67ae059cea5f1789d";
		List<ResumePojo> list = personalService.getResumeListByCondition(userId, resumeName, resumeJobIntension,
				resumeSex, resumeEducation, resumeWorkYears, resumeGraduateInstitution);

		System.out.println("getResumeListByCondition里得到的list长度为" + list.size());

		Map<String, Object> model = new HashMap<String, Object>();

		PageInfo<ResumePojo> pageResumePojo = new PageInfo<ResumePojo>(list);
		model.put("page", pageResumePojo);

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
