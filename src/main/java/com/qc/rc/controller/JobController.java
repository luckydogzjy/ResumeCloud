package com.qc.rc.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.qc.rc.common.GetUser;
import com.qc.rc.common.GetUuid;
import com.qc.rc.entity.Job;
import com.qc.rc.service.JobService;

@Controller
public class JobController {
	
	@Autowired
	private JobService jobService;
		
	Logger logging = Logger.getLogger(JobController.class);
	
	//userId from session
	private String userId = GetUser.getUser().getUserId();
	
	//搜索内容
	private static String searchName = null;
	
	/**
	 * 职位管理主页面-post
	 * @author ruin
	 * @param search
	 * @param page
	 * @return
	 */
	@RequestMapping(value="/JobManage.do",method=RequestMethod.POST)
	public ModelAndView ManageViewPost(String search,@RequestParam(required=true,defaultValue="1") Integer page){
		
		searchName = search;
	
		Map<String,Object> model= jobService.jobGetByName(userId,searchName,page);			
		model.put("search", searchName);
		
		return new ModelAndView("JobManage/JobManage",model);
			
	}
	/**
	 * 职位管理主页面-get
	 * @author ruin
	 * @param page
	 * @return
	 */
	@RequestMapping(value="/JobManage.do",method=RequestMethod.GET)
	public ModelAndView ManageViewGet(@RequestParam(required=true,defaultValue="1") Integer page){
		
		Map<String,Object> model= jobService.jobGetByName(userId,searchName,page);			
		model.put("search", searchName);
		
		return new ModelAndView("JobManage/JobManage",model);
			
	}
	/**
	 * 跳转添加页面
	 * @author ruin
	 * @return
	 */
	@RequestMapping("/jobAddView.do")
	public ModelAndView jobAddView(){
		return new ModelAndView("JobManage/JobAdd");
	}
	/**
	 * 职位添加
	 * @author ruin
	 * @param name
	 * @param count
	 * @param salary
	 * @param introduction
	 * @param condition
	 * @param endTime
	 * @return
	 */
	@RequestMapping("/jobAdd.do")
	public ModelAndView jobAdd(String name,Integer count,Integer salary,String introduction,String condition,String endTime){;
		
		Job job = new Job();
		try {
			job.setJOB_USER_ID(userId);
			job.setJOB_ID(GetUuid.getuuid32());
			job.setJOB_NAME(name);
			job.setJOB_COUNT(count);
			job.setJOB_SALARY(salary);
			job.setJOB_INTRODUCTION(introduction);
			job.setJOB_CONDITION(condition);	
			job.setJOB_END_TIME(new SimpleDateFormat("yyyy-MM-dd").parse(endTime));
		} catch (ParseException e) {
			logging.error("日期异常");
			//错误页面
			return null;
		}
		if (jobService.jobAdd(job)) {

			return new ModelAndView("redirect:/JobManage.do");
			
		}else
		
			return null;	
	}
	/**
	 * 跳转更新页面
	 * @author ruin
	 * @param jobId
	 * @return
	 */
	@RequestMapping("/jobUpdateView.do")
	public ModelAndView jobUpdateView(String jobId){
		
		Job job = jobService.jobGetOne(jobId);
		Map<String, Object> model = new HashMap<>();
		model.put("job", job);
		
		return new ModelAndView("JobManage/JobUpdate",model);
	}
	/**
	 * 职位更新
	 * @author ruin
	 * @param jobid
	 * @param count
	 * @param salary
	 * @param introduction
	 * @param condition
	 * @param endTime
	 * @return
	 */
	@RequestMapping("/jobUpdate.do")
	public ModelAndView jobUpdate(String jobid,Integer count,Integer salary,
			String introduction,String condition,String endTime){
		
		Job job = new Job();
			
		try {
			job.setJOB_USER_ID(userId);
			job.setJOB_ID(jobid);
			job.setJOB_COUNT(count);
			job.setJOB_SALARY(salary);
			job.setJOB_INTRODUCTION(introduction);
			job.setJOB_CONDITION(condition);	
			job.setJOB_END_TIME(new SimpleDateFormat("yyyy-MM-dd").parse(endTime));
		} catch (ParseException e) {
			logging.error("日期异常");
			//错误页面
			return null;
		}
		
		if (jobService.jobUpdate(job)) {
			
			return new ModelAndView("redirect:/JobManage.do");
			
		}else
		
			return null;
	}
	/**
	 * 职位删除
	 * @author ruin
	 * @param jobId
	 * @return
	 */
	@RequestMapping("/jobDelete.do")
	public ModelAndView jobDelete(String jobId){
		
		if (jobService.jobDelete(jobId)) {
			
			return new ModelAndView("redirect:/JobManage.do");
			
		}else
			
			return null;
	}
	/**
	 * 关闭职位
	 * @author ruin
	 * @param jobId
	 * @param jobStatus
	 * @return
	 */
	@RequestMapping("/jobChangeStatus.do")
	public ModelAndView jobChangeStatus(String jobId,Short jobStatus){
				
		if (jobService.jobChangeStatus(jobId, Integer.valueOf(jobStatus))) {
			
			return new ModelAndView("redirect:/JobManage.do");
			
		}else
			
			return null;
	}
	/**
	 * 职位详情
	 * @author ruin
	 * @param jobId
	 * @return
	 */
	@RequestMapping("/jobDetails.do")
	public ModelAndView jobGetOne(String jobId){	
		
		Job job = jobService.jobGetOne(jobId);
		Map<String, Object> model = new HashMap<>();
		model.put("job", job);
			
		return new ModelAndView("JobManage/JobDetails",model);
	}
	/**
	 * 跳转模板生成页面
	 * @author ruin
	 * @param jobId
	 * @return
	 */
	@RequestMapping("jobTemplateView.do")
	public ModelAndView jobTemplateView(String jobId){
		
		Job job = jobService.jobGetOne(jobId);
		Map<String, Object> model = new HashMap<>();
		model.put("job", job);
		
		return new ModelAndView("JobManage/JobTemplate",model);
	}
	/**
	 * 开启职位
	 * @author ruin
	 * @param jobId
	 * @param jobDate
	 * @return
	 */
	@RequestMapping("/jobStatusOpen.do")
	@ResponseBody
	public String jobStatusOpen(String jobId,String jobDate){
		
		try {
			boolean ok = jobService.jobStatusOpen(jobId, new SimpleDateFormat("yyyy-MM-dd").parse(jobDate));
			if (ok) {
				return "Success!";
			}
		} catch (ParseException e) {		
			logging.error("日期异常");
			//错误页面
			return null;
		}
		
		return "error";
		
	}
}
