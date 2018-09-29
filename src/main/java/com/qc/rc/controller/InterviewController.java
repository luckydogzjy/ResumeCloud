package com.qc.rc.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



import org.apache.commons.lang3.StringUtils;
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
import com.qc.rc.utils.DateUtil;



@Controller
@RequestMapping("Interview/")
public class InterviewController {
	
	private static Logger log = Logger.getLogger(InterviewController.class);
	
	@Autowired  
	private InterviewService iInterviewService;


	

	/**
	 * 显示简历详情
	 * @param ResumeId
	 * @return
	 */
	@RequestMapping("showInterviewDetail.do")
	public ModelAndView getInterviewByResumeId(String InterviewId){
		
		Map<String,Object> model = new HashMap<String,Object>();
		ServerResponse<InterviewPojo> interviewPojo = iInterviewService.getInterviewByInterviewId(InterviewId);
		model.put("interviewPojo", interviewPojo.getData());
		return new ModelAndView("interviewJsps/IVxiangqing",model);	
	}
	
	/**
	 * 更新页显示面试安排详情
	 * @param ResumeId
	 * @return
	 */
	@RequestMapping(value="showInterviewDetailUpdate.do",method = RequestMethod.GET)
	public ModelAndView getInterviewByResumeIdUpdate(String InterviewId){
		
		Map<String,Object> model = new HashMap<String,Object>();
		ServerResponse<InterviewPojo> interviewPojo = iInterviewService.getInterviewByInterviewId(InterviewId);
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
	public ModelAndView updateInterviewsByResumeId(InterviewPojo interviewPojo,String StringinterviewTime,String ResumeId,String resumePhone){
		Map<String,Object> model = new HashMap<String,Object>();
		try{
			//判断简历手机号空
			if(StringUtils.isBlank(resumePhone)){
				log.info("ResumePhone为空");
			}
			//判断面试工作空
			if(StringUtils.isBlank(interviewPojo.getInterviewJob())){
				log.info("InterviewJob为空");
			}
			//判断面试时间空
			if(StringUtils.isBlank(StringinterviewTime)){
				log.info("StringinterviewTime为空");
			}else{
				//把前端传回来的时间，转换成Date类型
				Date interviewTime = DateUtil.parseStrToDate(StringinterviewTime, "yyyy-MM-dd HH:mm:ss");
				interviewPojo.setInterviewTime(interviewTime);
			}
			if(StringUtils.isBlank(interviewPojo.getInterviewAddress())){
				log.info("InterviewAddress为空");
			}
			if(StringUtils.isBlank(interviewPojo.getInterviewAssociatePhone())){
				log.info("InterviewAssociatePhone为空");
			}
			if(StringUtils.isBlank(interviewPojo.getInterviewAssociateUsername())){
				log.info("InterviewAssociateUsername为空");
			}
			if(StringUtils.isBlank(interviewPojo.getInterviewInfo())){
				log.info("interviewInfo为空");
			}
			ServerResponse result = iInterviewService.updateInterviewsByInterviewId(interviewPojo,ResumeId,resumePhone);
			model.put("info",result.getMsg());
			ServerResponse<InterviewPojo> interviewPojoDetail = iInterviewService.getInterviewByInterviewId(interviewPojo.getInterviewId());
			model.put("interviewPojo", interviewPojoDetail.getData());
		}catch (NumberFormatException e) {
			log.error("不支持的字符类型转整型");
		}
		return new ModelAndView("interviewJsps/IVxiangqing",model);
	}
	
	//获取所有的面试安排
	@RequestMapping(value="selectAllInterviews.do",method = RequestMethod.GET)
	@ResponseBody
	public List<InterviewPojoView> selectAllInterviews(){
		
		log.info("hello log4j");
		//TODO user登录验证，userId获取
		String userId ="1b786bc41114f67ae059cea5f1789d";
		
		ServerResponse<List<InterviewPojoView>> InterviewPojoViews = iInterviewService.selectAllInterviews(userId);
		
		return InterviewPojoViews.getData();
		
	}
	

}
