package com.qc.rc.controller;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.qc.rc.common.FormParameterUtil;
import com.qc.rc.common.GetUser;
import com.qc.rc.entity.User;
import com.qc.rc.entity.pojo.UserExchangeResumePojo;
import com.qc.rc.service.PersonalService;
import com.qc.rc.service.UserService;
import com.qc.rc.utils.DESUtil;


@Controller
@RequestMapping("personal")
public class PersonalController {

	@Autowired
	private PersonalService personalService;

	@Autowired
	UserService userService;

	// 日志
	public static final Logger log = Logger.getLogger(PersonalController.class);

    //修改密码方法,第一步直接跳到页面
    @RequestMapping("/modifypassword.do")
    public String updateToPassword() {
        return "personal/modifypassword";
    }

    @RequestMapping("/shopping_mall.do")
    public String selectGoods() {
        return "personal/shopping_mall";
    }
	
	@RequestMapping("/updatePassword.do")
	@ResponseBody
	public Map<String, Object> updataPassword(String oldpwd, String newpwd,HttpServletRequest request) {
		Map<String, Object> model = new HashMap<String, Object>();
		//合并有session后用
			User user = (User)request.getSession().getAttribute("rcuser");
			if (user == null) {
				model.put("status", 4);
				return  model;
			}
			String userPassword = DESUtil.getInstance().decode(user.getUserPassword());
			log.debug(user.getUserPassword()+"  " +oldpwd);
			String userId = user.getUserId();
			if (!oldpwd.equals(userPassword)) {
				model.put("status", 0);
				model.put("message", "原密码输入错误，请重试");
				return model;
			}
			user.setUserId(userId);
			user.setUserPassword(DESUtil.getInstance().encode(newpwd));
			int result = personalService.passwordUpdate(user);
			if (result == 1) {
				model.put("status", 1);
				model.put("message", "密码修改成功，请重新登录。");
			} else {
				model.put("status", 2);
				model.put("message", "密码修改失败");
				return  model;

			}
		
		return model;
		
		
	}

	/**
	 * 进入个人中心自动获取info
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/PersonalCenter.do")
	public ModelAndView UserInfo_show(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 获取session中的userId
		// HttpSession session=request.getSession();
		User user = (User)request.getSession().getAttribute("rcuser");
		
		//如果session失效或取不到user 转发到登录
		if (user == null) {
			return new ModelAndView("redirect:/gologin1.action");
		}
		String userId = user.getUserId();
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
	@ResponseBody
	public Integer UserInfo_update( String userPhone,String userName, String userCompany, String userBirthday,HttpServletRequest request
			){
		User user = (User)request.getSession().getAttribute("rcuser");
		user.setUserId(user.getUserId());
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
		int result = personalService.updateInfo(user);
		System.out.println(user.getUserPhone()+user.getUserName());
		return result;
	}

	/**
	 * 显示hr兑换的简历
	 * 
	 * @param session
	 * 
	 * @return
	 */
//	@RequestMapping("/ur_resume_exchange.do")
//	public ModelAndView resumeExchangShow(ServletRequest session,@RequestParam(required=true,defaultValue="1") Integer page) throws Exception {
//
//		// 根据登录的hr的Id的到他兑换的简历
//		String userId = (String) session.getAttribute("userId");
//		userId = "2b786bc41114f67ae059cea5f1789d";
////		User user = GetUser.getUser();
//		Map<String, Object> list = personalService.getAllExchangResume(userId, page);
//
//		Map<String, Object> model = new HashMap<String, Object>();
//
//		model.put("resumeList", list);
//
//		return new ModelAndView("personal/ur_resume_exchange", model);
//
//	}

	/**
	 * 多种查询
	 * @param userExchangeResumePojo
	 * @param page
	 * @return
	 */


	@RequestMapping("/ur_resume_exchange.do")
	public ModelAndView getResumeListByCondition(HttpServletRequest request,UserExchangeResumePojo userExchangeResumePojo,@RequestParam(required=true,defaultValue="1") Integer page) {

		
	//	User user = (User) session.getAttribute("user");
	//	User user = GetUser.getUser();
		User user = (User)request.getSession().getAttribute("rcuser");
		
		 if (user != null) {
			
			if (userExchangeResumePojo.getResumeSex() == null) {
				userExchangeResumePojo.setResumeSex(-1);
			}
			if (userExchangeResumePojo.getResumeEducation() == null) {
				userExchangeResumePojo.setResumeEducation(-1);
			}
			if (userExchangeResumePojo.getResumeWorkYears() == null) {
				userExchangeResumePojo.setResumeWorkYears(-1);
			}

			try {
				if (userExchangeResumePojo.getResumeName() != null) {
					userExchangeResumePojo.setResumeName(FormParameterUtil.changeCode(userExchangeResumePojo.getResumeName()));
				}
				if (userExchangeResumePojo.getResumeJobIntension() != null) {
					userExchangeResumePojo.setResumeJobIntension(FormParameterUtil.changeCode(userExchangeResumePojo.getResumeJobIntension()));

				}
				if (userExchangeResumePojo.getResumeGraduateInstitution() != null) {
					userExchangeResumePojo.setResumeGraduateInstitution(FormParameterUtil.changeCode(userExchangeResumePojo.getResumeGraduateInstitution()));

				}
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			
			
			Map<String,Object> model = new HashMap<String,Object>(); 
			model = personalService.getAllExchangResume(user.getUserId(), userExchangeResumePojo, page);	
			return new ModelAndView("personal/ur_resume_exchange",model);
			
		} else {
			
			
			System.out.println("登录");
			return new ModelAndView("redirect:/gologin1.action");
		}	
		
	}

}
