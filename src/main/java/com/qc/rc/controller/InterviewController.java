package com.qc.rc.controller;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qc.rc.common.FormParameterUtil;
import com.qc.rc.common.ServerResponse;
import com.qc.rc.entity.Resume;
import com.qc.rc.entity.User;
import com.qc.rc.entity.UserResume;
import com.qc.rc.entity.pojo.InterviewPojo;

import com.qc.rc.service.InterviewService;
import com.qc.rc.service.ResumeService;
import com.qc.rc.utils.InterviewDateUtil;

import sun.util.logging.resources.logging;

@Controller
@RequestMapping("Interview/")
public class InterviewController {
	private static Logger logger = Logger.getLogger(InterviewController.class);
	
	static Integer userId = 1001;	
	@Autowired  
	private InterviewService iInterviewService;
	
	@Autowired 
	private ResumeService resumeService;
	
	@RequestMapping("selectByCondition.do")
	public ModelAndView selectByCondition(String pageNum,String interviewInfo,
			String interviewJob,String startTime,String overTime,String sort,HttpServletRequest request,HttpSession session){
		Map<String,Object> model = new HashMap<String,Object>();
		try {
			User user = (User)session.getAttribute("user");
/*			if (user == null) {
				logger.debug("从session里找不到user");
//				session里的user失效，跳转登录页面
//				return new ModelAndView("redirect:/",model);
				user.setUserId(1001);
				System.out.println(user.getUserId());
			}*/
			Integer pn = 1;
			if (StringUtils.isNotBlank(pageNum)) {
				logger.debug("未接收到页码数");
				pn =  Integer.valueOf(pageNum);
			}
			if (StringUtils.isBlank(pageNum)) {
				logger.debug("未接收排序方式");
			}
			if (StringUtils.isNotBlank(interviewJob)) {
				interviewJob = FormParameterUtil.changeCode(interviewJob);
			}
			if(StringUtils.isNotBlank(interviewInfo)){
				interviewInfo = FormParameterUtil.changeCode(interviewInfo);
			}
			
			PageInfo<InterviewPojo> pageInfo = iInterviewService.selectByCondition(pn, userId, startTime, overTime,
					 interviewJob, interviewInfo, 0);
			model.put("pageInfo", pageInfo);
			model.put("startTime", startTime);
			model.put("interviewJob", interviewJob);
			model.put("overTime", overTime);
			model.put("interviewInfo", interviewInfo);

			return new ModelAndView("interviewJsps/showAllInterviews",model);
			
			
		} catch (ParseException e) {
			logger.debug("日期类型不匹配");
			model.put("message", "日期类型不匹配");
		}catch (NullPointerException e) {
			logger.debug("参数为空");
			model.put("message", "参数为空");
		} catch (UnsupportedEncodingException e) {
			logger.debug("不支持的字符");
			model.put("message", "不支持的字符");
		} catch (NumberFormatException e) {
			logger.debug("不支持的字符类型转整型");
			model.put("message", "不支持的字符类型转整型");
		}
		return new ModelAndView("interviewJsps/error",model);
		
		
		
	}
//	页面跳转
	@RequestMapping("addNewInterview.do")
	public ModelAndView addNewInterview(Integer resumeId,HttpServletRequest request){
		Resume resume = resumeService.getResumeDetailsById(resumeId);
		Map<String,Object> model = new HashMap<String,Object>();
		model.put("resume", resume);
		return new ModelAndView("interviewJsps/addnewinterview",model);
	}
	
//	为简历库中存在的简历安排面试
	@RequestMapping("newInterview.do")
	public ModelAndView newInterview(InterviewPojo iPojo,String resumePhone,String interviewTime,
			String interviewMessage,String ism,HttpServletRequest request){
		Map<String,Object> model = new HashMap<String,Object>();
		try {
			logger.debug(iPojo.toString()+resumePhone+interviewMessage+interviewTime+ism);
			System.out.println(iPojo.toString()+resumePhone+interviewMessage+interviewTime+ism);
			
			if("sendMessage".equals(ism)){
//				发送短信interviewMessage
				interviewMessage = FormParameterUtil.changeCode(request.getParameter("interviewMessage"));
				System.out.println("发送短信到"+resumePhone);
				System.out.println(interviewMessage);
			}
			Resume resume = resumeService.getResumeDetailsById(iPojo.getInterviewResumeId());

			Integer result = iInterviewService.addInterview(iPojo,resume,userId);
			if(result==0){
				model.put("message","添加面试失败");
				return new ModelAndView("interviewJsps/error",model);
			}else {

				if(!resumePhone.equals(resume.getResumePhone())){
//					更新简历库中resume的phone
					Integer res = resumeService.resumeUpdate(resume);
					if(res!=0){
						model.put("message","已更新该简历联系方式");
						return new ModelAndView("interviewJsps/addnewinterview",model);
						//return new ModelAndView("redirect:/Interview/showAllInterviews.do",model);
					}else {
						model.put("resume", resume);
						model.put("message","更新手机号失败");
						return new ModelAndView("interviewJsps/error",model);
					}
				}else {
					model.put("resume", resume);
					model.put("message","添加成功");
					System.out.println(iPojo.getInterviewId());
					return new ModelAndView("redirect:/Interview/showAllInterviews.do?userId="+userId+"&sort=1",model);
//					转发到详情页面
//					return new ModelAndView("redirect:/Interview/showInterviewDetail.do?interviewId="+iPojo.getInterviewId(),model);
				}
			}
		}catch (UnsupportedEncodingException e) {
			logger.debug("不支持的字符");
			model.put("message", "不支持的字符");
		} catch (NumberFormatException e) {
			logger.debug("不支持的字符类型转整型");
			model.put("message", "不支持的字符类型转整型");
		}
		return new ModelAndView("interviewJsps/error",model);
		
		
		
		

	}
	
	
	
//	为简历库中不存在的简历安排面试
	@RequestMapping("newResumeInterview.do")
	public ModelAndView newResumeInterview(HttpServletRequest request) throws UnsupportedEncodingException{
		Map<String,Object> model = new HashMap<String,Object>();
		try {
			String resumeName = FormParameterUtil.changeCode(request.getParameter("resumeName"));
			String resumePhone = request.getParameter("resumePhone");
			String interviewJob = FormParameterUtil.changeCode(request.getParameter("interviewJob"));
//			封装数据成resume 在数据库中插入返回resumeid
			Resume resume = new Resume();
			resume.setResumeName(resumeName);
			resume.setResumePhone(resumePhone);
			resume.setResumeJobIntension(interviewJob);

			int resultCount = resumeService.resumeAdd(resume);
//			创建UserResume对象	插入数据库
			if(resultCount != 0){
				UserResume ur = new UserResume();
				ur.setUrResumeId(resume.getResumeId());
				ur.setUrUesrId(userId);
				int result = resumeService.resumeAddResumeUser(ur);
				if(result !=0){
//					创建interviewPojo对象 插入数据库
					
					Date interviewTime =InterviewDateUtil.strToDate(request.getParameter("interviewTime"));
					String interviewAddress = FormParameterUtil.changeCode(request.getParameter("interviewAddress"));
					String interviewAssociateUsername = FormParameterUtil.changeCode(request.getParameter("interviewAssociateUsername"));
					String interviewAssociatePhone = FormParameterUtil.changeCode(request.getParameter("interviewAssociatePhone"));
					String interviewInfo = FormParameterUtil.changeCode(request.getParameter("interviewInfo"));
				
					InterviewPojo iPojo = new InterviewPojo();
					iPojo.setResume(resume);
					iPojo.setInterviewResumeId(resume.getResumeId());
					iPojo.setInterviewJob(interviewJob);
					iPojo.setInterviewTime(interviewTime);
					
					iPojo.setInterviewAddress(interviewAddress);
					iPojo.setInterviewAssociatePhone(interviewAssociatePhone);
					iPojo.setInterviewAssociateUsername(interviewAssociateUsername);
					iPojo.setInterviewInfo(interviewInfo);
					iPojo.setInterviewCreateTime(new Date());
//					新建时设置状态为待面试
					iPojo.setInterviewStatus(2);
//					设置deleteflag为0
					iPojo.setInterviewDeleteFlag(0);
					iPojo.setInterviewUserId(userId);
//					应从缓存中获取username
					iPojo.setInterviewCreateUser("LING");
					
					System.out.println(iPojo);
					
				}else {
					model.put("message", "新增用户简历失败");
				}
			}else {
				model.put("message", "新增简历失败");
			}
			return new ModelAndView("redirect:/Interview/showAllInterviews.do",model);
		} catch (ParseException e) {
			
		}
		return null;
	}
	
	@RequestMapping("deleteById.do")
	@ResponseBody
	public String deleteById(){

		return "interviewJsps/error";
		
	}
	
	
//	interviewId
	@RequestMapping("showInterviewDetail.do")
	public ModelAndView getInterviewByResumeId(Integer ResumeId){
		
		Map<String,Object> model = new HashMap<String,Object>();
		ServerResponse<InterviewPojo> interviewPojo = iInterviewService.getInterviewByResumeId(ResumeId);
		model.put("interviewPojo", interviewPojo.getData());
		return new ModelAndView("interviewJsps/IVxiangqing",model);	
	}
}
