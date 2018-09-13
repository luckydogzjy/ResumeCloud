package com.qc.rc.controller;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.servlet.ModelAndView;

import com.qc.rc.common.FormParameterUtil;
import com.qc.rc.common.PageBean;
import com.qc.rc.common.Util;
import com.qc.rc.entity.Resume;
import com.qc.rc.entity.User;
import com.qc.rc.entity.pojo.InterviewPojo;

import com.qc.rc.service.InterviewService;
import com.qc.rc.service.ResumeService;

@Controller
@RequestMapping("Interview")
public class InterviewController {
		
	@Autowired  
	private InterviewService iInterviewService;
	
	@Autowired 
	private ResumeService resumeService;
	
	@RequestMapping("/showAllInterviews.do")
	public ModelAndView showAllInterviews(Integer userId,HttpServletRequest request){
		Map<String,Object> model = new HashMap<String,Object>();
		PageBean<InterviewPojo> iPageBean = iInterviewService.getAllInterviews(userId);
		
		
		
		model.put("iPageBean", iPageBean);
		return new ModelAndView("interviewJsps/showAllInterviews",model);
	}
	
	@RequestMapping("/selectByCondition.do")
	public ModelAndView selectByCondition(HttpServletRequest request) throws UnsupportedEncodingException{
		
		Integer userId = 1001;
		Map<String,Object> model = new HashMap<String,Object>();
		String startTime =request.getParameter("interviewTimeStart");
		String overTime = request.getParameter("interviewTimeOver");
		System.out.println(startTime);
		System.out.println(overTime);
		Date st= null;
		Date ot = null;

		if("".equals(startTime)){
			startTime="2018-01-01";
		}
		st=Util.strToDate(startTime+" 00:00:00");

		
		if("".equals(overTime)){
			overTime="2018-12-31";
		}
		ot=Util.strToDate(overTime+" 23:59:59");

		System.out.println(st);
		System.out.println(ot);
		String interviewJob = FormParameterUtil.changeCode(request.getParameter("interviewJob"));
		String interviewInfo = FormParameterUtil.changeCode(request.getParameter("interviewInfo"));
		Integer pageNum = Integer.valueOf(request.getParameter("pageNum"));
		
		PageBean<InterviewPojo> iPageBean = iInterviewService.selectByCondition(pageNum, 1001,st,ot, interviewJob, interviewInfo);
		
		model.put("interviewTimeStart", startTime);
		model.put("interviewTimeOver", overTime);
		model.put("interviewJob", interviewJob);
		model.put("interviewInfo", interviewInfo);
		model.put("iPageBean", iPageBean);
		return new ModelAndView("interviewJsps/showAllInterviews",model);
		
	}
	
	@RequestMapping("/addNewInterview.do")
	public ModelAndView addNewInterview(Integer resumeId,HttpServletRequest request){
		Resume resume = resumeService.getResumeDetailsById(resumeId);
		Map<String,Object> model = new HashMap<String,Object>();
		model.put("resume", resume);
		return new ModelAndView("interviewJsps/addnewinterview",model);
	}
	
	@RequestMapping("/newInterview.do")
	public ModelAndView newInterview(Integer userId,HttpServletRequest request) throws UnsupportedEncodingException{
		userId = 1001;
		Integer resumeId = Integer.valueOf(request.getParameter("resumeId"));
		String resumePhone = request.getParameter("resumePhone");
		String interviewJob = FormParameterUtil.changeCode(request.getParameter("interviewJob"));
		Date interviewTime =Util.strToDate(request.getParameter("interviewTime"));
		String interviewAddress = FormParameterUtil.changeCode(request.getParameter("interviewAddress"));
		String interviewAssociateUsername = FormParameterUtil.changeCode(request.getParameter("interviewAssociateUsername"));
		String interviewAssociatePhone = FormParameterUtil.changeCode(request.getParameter("interviewAssociatePhone"));
		String interviewInfo = FormParameterUtil.changeCode(request.getParameter("interviewInfo"));
		
		System.out.println(interviewTime);
		Resume resume = resumeService.getResumeDetailsById(resumeId);
	
		InterviewPojo iPojo = new InterviewPojo();
		iPojo.setResume(resume);
	/*	INTERVIEW_ID,INTERVIEW_RESUME_ID,INTERVIEW_JOB,INTERVIEW_TIME,INTERVIEW_ASSOCIATE_USERNAME
	  	,INTERVIEW_ASSOCIATE_PHONE,INTERVIEW_ADDRESS,INTERVIEW_INFO,INTERVIEW_STATUS,INTERVIEW_RECODE_INFO,INTERVIEW_CREATE_USER,INTERVIEW_CREATE_TIME
	  	,INTERVIEW_DELETE_FLAG,INTERVIEW_USER_ID*/
		iPojo.setInterviewResumeId(resumeId);
		iPojo.setInterviewJob(interviewJob);
		iPojo.setInterviewTime(interviewTime);
		
		iPojo.setInterviewAddress(interviewAddress);
		iPojo.setInterviewAssociatePhone(interviewAssociatePhone);
		iPojo.setInterviewAssociateUsername(interviewAssociateUsername);
		iPojo.setInterviewInfo(interviewInfo);
		iPojo.setInterviewCreateTime(new Date());
//		新建时设置状态为待面试
		iPojo.setInterviewStatus(2);
//		设置deleteflag为0
		iPojo.setInterviewDeleteFlag(0);
		iPojo.setInterviewUserId(userId);
//		应从缓存中获取username
		iPojo.setInterviewCreateUser("LING");
		
		System.out.println(iPojo);
		iInterviewService.addInterview(iPojo);
		Map<String,Object> model = new HashMap<String,Object>();
		model.put("message", "添加成功");
		
		if(!resumePhone.equals(resume.getResumePhone())){
//			更新简历库中resume的phone
		}
		
		return new ModelAndView("interviewJsps/addnewinterview",model);

	}
	
}
