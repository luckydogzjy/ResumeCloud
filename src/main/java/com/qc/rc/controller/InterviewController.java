package com.qc.rc.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.qc.rc.common.ServerResponse;

import com.qc.rc.entity.pojo.InterviewPojo;
import com.qc.rc.entity.pojoView.InterviewPojoView;
import com.qc.rc.service.InterviewService;
import com.qc.rc.service.ResumeService;
import com.qc.rc.utils.DateUtil;
import com.qc.rc.utils.InterviewDateUtil;

@Controller
@RequestMapping("Interview/")
public class InterviewController {
	
	private static Logger log = Logger.getLogger(InterviewController.class);

	@Autowired  
	private InterviewService iInterviewService;
	
	@Autowired  
	private ResumeService resumeService;

	
	/**
	 * 显示面试安排详情
	 * @param ResumeId
	 * @return
	 */
	@RequestMapping("showInterviewDetail.do")
	public ModelAndView getInterviewByResumeId(Integer ResumeId){
		
		Map<String,Object> model = new HashMap<String,Object>();
		ServerResponse<InterviewPojo> interviewPojo = iInterviewService.getInterviewByResumeId(ResumeId);
		model.put("interviewPojo", interviewPojo.getData());
		return new ModelAndView("interviewJsps/IVxiangqing",model);	
	}
	
	/**
	 * 更新页显示面试安排详情
	 * @param ResumeId
	 * @return
	 */
	@RequestMapping("showInterviewDetailUpdate.do")
	public ModelAndView getInterviewByResumeIdUpdate(Integer ResumeId){
		
		Map<String,Object> model = new HashMap<String,Object>();
		ServerResponse<InterviewPojo> interviewPojo = iInterviewService.getInterviewByResumeId(ResumeId);
		model.put("interviewPojo", interviewPojo.getData());
		return new ModelAndView("interviewJsps/IVxiugai",model);	
	}
	
	/**
	 * 更新面试安排
	 * @param interviewPojo
	 * @param StringinterviewTime
	 * @param ResumeId
	 * @param resumePhone
	 * @return
	 */
	@RequestMapping(value="updateInterviewDetail.do",method = RequestMethod.POST)
	public ModelAndView updateInterviewsByResumeId(InterviewPojo interviewPojo,String StringinterviewTime,Integer ResumeId,String resumePhone){
		
		//把前端传回来的时间，转换成Date类型
		Date interviewTime = DateUtil.parseStrToDate(StringinterviewTime, "yyyy-MM-dd HH:mm:ss");
		interviewPojo.setInterviewTime(interviewTime);
		
		Map<String,Object> model = new HashMap<String,Object>();
		ServerResponse result = iInterviewService.updateInterviewsByResumeId(interviewPojo,ResumeId,resumePhone);
		model.put("info",result.getMsg());
		return new ModelAndView("interviewJsps/IVxiugai",model);
	}
	
	@RequestMapping(value="selectAllInterviews.do",method = RequestMethod.GET)
	@ResponseBody
	public List<InterviewPojoView> selectAllInterviews(){
		
		//TODO user登录验证，userId获取
		Integer userId =1;
		
		ServerResponse<List<InterviewPojoView>> InterviewPojoViews = iInterviewService.selectAllInterviews(userId);
		
		return InterviewPojoViews.getData();
		
	}
}
