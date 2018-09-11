package com.qc.rc.controller;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.servlet.ModelAndView;

import com.qc.rc.common.FormParameterUtil;
import com.qc.rc.common.PageBean;
import com.qc.rc.common.PageMessage;
import com.qc.rc.common.Util;
import com.qc.rc.entity.pojo.InterviewPojo;

import com.qc.rc.service.InterviewService;

@Controller
@RequestMapping("Interview")
public class InterviewController {
		
	@Autowired  
	private InterviewService iInterviewService;
	
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
		Date st= null;
		Date ot = null;
//		如果格式不对取默认时间
		if(Util.isValidTime(startTime)){
			st=Util.strToDate(startTime);
		}else {
			st=Util.strToDate("2018.1.1 00:00:00");
		}
		
		if(Util.isValidTime(overTime)){
			st=Util.strToDate(overTime);
		}else {
			ot=Util.strToDate("2018.12.31 00:00:00");
		}
		
		String interviewJob = FormParameterUtil.changeCode(request.getParameter("interviewJob"));
		String interviewInfo = FormParameterUtil.changeCode(request.getParameter("interviewInfo"));
		Integer pageNum = Integer.valueOf(request.getParameter("pageNum"));
		
		PageBean<InterviewPojo> iPageBean = iInterviewService.selectByCondition(pageNum, 1001,st,ot, interviewJob, interviewInfo);
		
		model.put("interviewTimeStart", st);
		model.put("interviewTimeOver", ot);
		model.put("interviewJob", interviewJob);
		model.put("interviewInfo", interviewInfo);
		model.put("iPageBean", iPageBean);
		return new ModelAndView("interviewJsps/showAllInterviews",model);
		
	}
}
