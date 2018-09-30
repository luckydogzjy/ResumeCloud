package com.qc.rc.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.qc.rc.common.Const;
import com.qc.rc.common.GetUser;
import com.qc.rc.entity.Job;
import com.qc.rc.service.JobService;

@Controller
public class JobController {
	
	@Autowired
	private JobService jobService;
	/**
	 * userId from session
	 */
//	private Integer userId = 1 ;
	private String userId = GetUser.getUser().getUserId();
	
	//private static String searchName = null;
	
	@RequestMapping(value="/JobManage.do",method=RequestMethod.POST)

	public ModelAndView ManageViewPost(String searchName,@RequestParam(required=true,defaultValue="1") Integer page){

		//searchName = search;

		//searchName = req.getParameter("search");
		

		
		Map<String,Object> model = new HashMap<>();
		Map<String,Object> map= jobService.jobGetByName(userId,searchName,page);		
		
		model.put("job", map.get("job"));
		model.put("page", map.get("page"));
		model.put("search", searchName);
		return new ModelAndView("JobManage/JobManage",model);
			
	}
	
	@RequestMapping(value="/JobManage.do",method=RequestMethod.GET)
	public ModelAndView ManageViewGet(String searchName,@RequestParam(required=true,defaultValue="1") Integer page){
		
		Map<String,Object> model = new HashMap<>();
		Map<String,Object> map= jobService.jobGetByName(userId,searchName,page);		
		
		model.put("job", map.get("job"));
		model.put("page", map.get("page"));
		model.put("search", searchName);
		return new ModelAndView("JobManage/JobManage",model);
			
	}
	
	@RequestMapping("/jobAddView.do")
	public ModelAndView jobAddView(){
		return new ModelAndView("JobManage/JobAdd");
	}
	
	@RequestMapping("/jobAdd.do")
	public ModelAndView jobAdd(String name,Integer count,Integer salary,String introduction,String condition,String endTime){;
		
		Job job = new Job();
		try {
			job.setJOB_USER_ID(userId);
			job.setJOB_NAME(name);
			job.setJOB_COUNT(count);
			job.setJOB_SALARY(salary);
			job.setJOB_INTRODUCTION(introduction);
			job.setJOB_CONDITION(condition);	
			job.setJOB_END_TIME(new SimpleDateFormat("yyyy-MM-dd").parse(endTime));
		} catch (ParseException e) {
			return null;
		}
		
		System.out.println(job.toString());
		if (jobService.jobAdd(job)) {

			return new ModelAndView("redirect:/JobManage.do");
			
		}else
		
			return null;	
	}
	
	@RequestMapping("/jobUpdateView.do")
	public ModelAndView jobUpdateView(Integer jobId){
		
		Job job = jobService.jobGetOne(jobId);
		Map<String, Object> model = new HashMap<>();
		model.put("job", job);
		
		return new ModelAndView("JobManage/JobUpdate",model);
	}
	
	@RequestMapping("/jobUpdate.do")
	public ModelAndView jobUpdate(Integer jobid,Integer count,Integer salary,
			String introduction,String condition,String endTime){
		
		Job job = new Job();
			
		try {
			job.setJOB_ID(jobid);
			job.setJOB_COUNT(count);
			job.setJOB_SALARY(salary);
			job.setJOB_INTRODUCTION(introduction);
			job.setJOB_CONDITION(condition);	
			job.setJOB_END_TIME(new SimpleDateFormat("yyyy-MM-dd").parse(endTime));
		} catch (ParseException e) {
			return null;
		}
		
		if (jobService.jobUpdate(job)) {
			
			return new ModelAndView("redirect:/JobManage.do");
			
		}else
		
			return null;
	}
	
	@RequestMapping("/jobDelete.do")
	public ModelAndView jobDelete(Integer jobId){
		
		if (jobService.jobDelete(jobId)) {
			
			return new ModelAndView("redirect:/JobManage.do");
			
		}else
			
			return null;
	}
	
	@RequestMapping("/jobChangeStatus.do")
	public ModelAndView jobChangeStatus(Integer jobId,Short jobStatus){
				
		if (jobService.jobChangeStatus(jobId, Integer.valueOf(jobStatus))) {
			
			return new ModelAndView("redirect:/JobManage.do");
			
		}else
			
			return null;
	}
	
	@RequestMapping("/jobDetails.do")
	public ModelAndView jobGetOne(Integer jobId){	
		
		Job job = jobService.jobGetOne(jobId);
		Map<String, Object> model = new HashMap<>();
		model.put("job", job);
			
		return new ModelAndView("JobManage/JobDetails",model);
	}
	
	@RequestMapping("jobTemplateView.do")
	public ModelAndView jobTemplateView(Integer jobId){
		
		Job job = jobService.jobGetOne(jobId);
		Map<String, Object> model = new HashMap<>();
		model.put("job", job);
		
		return new ModelAndView("JobManage/JobTemplate",model);
	}
	
	@RequestMapping("/jobStatusOpen.do")
	@ResponseBody
	public String jobStatusOpen(String jobId,String jobDate){
//		System.out.println(jobId);
//		System.out.println(jobDate);
		try {
			//将状态转换为开启成功
			boolean ok = jobService.jobStatusOpen(Integer.valueOf(jobId), new SimpleDateFormat("yyyy-MM-dd").parse(jobDate));
			if (ok) {
				return "suceess";
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return "error";
		
	}
}
