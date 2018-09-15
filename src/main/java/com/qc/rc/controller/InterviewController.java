package com.qc.rc.controller;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.jws.soap.SOAPBinding.Use;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.servlet.ModelAndView;

import com.qc.rc.common.FormParameterUtil;
import com.qc.rc.common.PageBean;
import com.qc.rc.common.ServerResponse;
import com.qc.rc.entity.Resume;
import com.qc.rc.entity.User;
import com.qc.rc.entity.pojo.InterviewPojo;

import com.qc.rc.service.InterviewService;
import com.qc.rc.service.ResumeService;
import com.qc.rc.utils.InterviewDateUtil;

@Controller
@RequestMapping("Interview/")
public class InterviewController {
		
	@Autowired  
	private InterviewService iInterviewService;
	
	@Autowired 
	private ResumeService resumeService;
	
	@RequestMapping("showAllInterviews.do")
	public ModelAndView showAllInterviews(HttpServletRequest request,HttpSession session){
//		User user = (User)session.getAttribute("user");
//		if(user==null){
//			user.setUserId(1001);
//		}
		Map<String,Object> model = new HashMap<String,Object>();
		PageBean<InterviewPojo> iPageBean = iInterviewService.getAllInterviews(1001);
		model.put("iPageBean", iPageBean);
		return new ModelAndView("interviewJsps/showAllInterviews",model);
	}
	
	@RequestMapping("selectByCondition.do")
	public ModelAndView selectByCondition(HttpServletRequest request,HttpSession session) throws UnsupportedEncodingException{
//		User user = (User)session.getAttribute("user");
//		if(user==null){
//			user.setUserId(1001);
//		}
		Map<String,Object> model = new HashMap<String,Object>();
		String startTime =request.getParameter("interviewTimeStart");
		String overTime = request.getParameter("interviewTimeOver");
		Date st= null;
		Date ot = null;
		if(!"".equals(startTime)){
			st=InterviewDateUtil.strToDate(startTime);
		}
		if(!"".equals(overTime)){
		ot=InterviewDateUtil.strToDate(overTime);
		}
		String interviewJob = FormParameterUtil.changeCode(request.getParameter("interviewJob"));
		String interviewInfo = FormParameterUtil.changeCode(request.getParameter("interviewInfo"));
		String pageNum = request.getParameter("pageNum");
		Integer pNum=1;
		if(!"".equals(pageNum) && pageNum!=null){
			pNum = Integer.valueOf(pageNum);
		}
		
		PageBean<InterviewPojo> iPageBean = iInterviewService.selectByCondition(pNum, 1001,st,ot, interviewJob, interviewInfo);
		
		model.put("interviewTimeStart", startTime);
		model.put("interviewTimeOver", overTime);
		model.put("interviewJob", interviewJob);
		model.put("interviewInfo", interviewInfo);
		model.put("iPageBean", iPageBean);
		return new ModelAndView("interviewJsps/showAllInterviews",model);
		
	}
	
	@RequestMapping("addNewInterview.do")
	public ModelAndView addNewInterview(Integer resumeId,HttpServletRequest request){
		Resume resume = resumeService.getResumeDetailsById(resumeId);
		Map<String,Object> model = new HashMap<String,Object>();
		model.put("resume", resume);
		return new ModelAndView("interviewJsps/addnewinterview",model);
	}
	
//	为简历库中存在的简历安排面试
	@RequestMapping("newInterview.do")
	public String newInterview(Integer userId,HttpServletRequest request) throws UnsupportedEncodingException{
		userId = 1001;
		Integer resumeId = Integer.valueOf(request.getParameter("resumeId"));
		String resumePhone = request.getParameter("resumePhone");
		String interviewJob = FormParameterUtil.changeCode(request.getParameter("interviewJob"));
		Date interviewTime =InterviewDateUtil.strToDate(request.getParameter("interviewTime"));
		String interviewAddress = FormParameterUtil.changeCode(request.getParameter("interviewAddress"));
		String interviewAssociateUsername = FormParameterUtil.changeCode(request.getParameter("interviewAssociateUsername"));
		String interviewAssociatePhone = FormParameterUtil.changeCode(request.getParameter("interviewAssociatePhone"));
		String interviewInfo = FormParameterUtil.changeCode(request.getParameter("interviewInfo"));
		
		String ism = request.getParameter("isSendMsg");
		if("sendMessage".equals(ism)){
//			发送短信interviewMessage
			String interviewMessage = FormParameterUtil.changeCode(request.getParameter("interviewMessage"));
			System.out.println("发送短信到"+resumePhone);
			System.out.println(interviewMessage);
		}
		Resume resume = resumeService.getResumeDetailsById(resumeId);
	
		InterviewPojo iPojo = new InterviewPojo();
		System.out.println();
		
		
		iPojo.setResume(resume);
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
//		iInterviewService.addInterview(iPojo);
		Map<String,Object> model = new HashMap<String,Object>();
		
		if(!resumePhone.equals(resume.getResumePhone())){
//			更新简历库中resume的phone
		}
		
		return "redirect:/Interview/showAllInterviews.do";

	}
	
	
	
//	为简历库中不存在的简历安排面试
	@RequestMapping("newResumeInterview.do")
	public ModelAndView newResumeInterview(Integer userId,HttpServletRequest request) throws UnsupportedEncodingException{
		userId = 1001;
		String resumeName = FormParameterUtil.changeCode(request.getParameter("resumeName"));
		String resumePhone = request.getParameter("resumePhone");
		String interviewJob = FormParameterUtil.changeCode(request.getParameter("interviewJob"));
//		封装数据成resume 在数据库中插入返回resumeid
		Resume resume = new Resume();
		resume.setResumeName(resumeName);
		resume.setResumePhone(resumePhone);
		resume.setResumeJobIntension(interviewJob);
		resume.setResumeCreateTime(new Date());

//		创建UserResume对象	插入数据库
		
//		创建interviewPojo对象 插入数据库
		
		Date interviewTime =InterviewDateUtil.strToDate(request.getParameter("interviewTime"));
		String interviewAddress = FormParameterUtil.changeCode(request.getParameter("interviewAddress"));
		String interviewAssociateUsername = FormParameterUtil.changeCode(request.getParameter("interviewAssociateUsername"));
		String interviewAssociatePhone = FormParameterUtil.changeCode(request.getParameter("interviewAssociatePhone"));
		String interviewInfo = FormParameterUtil.changeCode(request.getParameter("interviewInfo"));

//		Resume resume = resumeService.getResumeDetailsById(resumeId);
	
		InterviewPojo iPojo = new InterviewPojo();
		iPojo.setResume(resume);
//		iPojo.setInterviewResumeId(resumeId);
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
	@RequestMapping("deleteById.do")
	public String deleteById(Integer interviewId){
		InterviewPojo iPojo = new InterviewPojo();
		iPojo.setInterviewId(interviewId);
		iPojo.setInterviewUpdateUser("LING");
		iInterviewService.deleteInterview(iPojo);
		return "redirect:/Interview/showAllInterviews.do";
	}
	
	
	
	@RequestMapping("showInterviewDetail.do")
	public ModelAndView getInterviewByResumeId(Integer ResumeId){
		
		Map<String,Object> model = new HashMap<String,Object>();
		ServerResponse<InterviewPojo> interviewPojo = iInterviewService.getInterviewByResumeId(ResumeId);
		model.put("interviewPojo", interviewPojo.getData());
		return new ModelAndView("interviewJsps/IVxiangqing",model);	
	}
}
