package com.qc.rc.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qc.rc.entity.Job;
import com.qc.rc.service.JobService;

@Controller
public class JobController {
	
	@Autowired
	private JobService jobService;
	/**
	 * userId from session
	 */
	private static Integer userId = 1 ;
	
	private static String searchName = null;
	
	@RequestMapping(value="/JobManage.do",method=RequestMethod.POST)
	public ModelAndView ManageViewPost(HttpServletRequest req,@RequestParam(required=true,defaultValue="1") Integer page){
		
		searchName = req.getParameter("search");
		
		PageHelper.startPage(page, 6);
		
		Map<String,Object> model = new HashMap<>();
		List<Job> job= jobService.jobGetByName(userId,searchName);
		
		PageInfo<Job> pageJob = new PageInfo<Job>(job);	
		
		model.put("job", job);
		model.put("page", pageJob);
		return new ModelAndView("JobManage/JobManageTest",model);
			
	}
	
	@RequestMapping(value="/JobManage.do",method=RequestMethod.GET)
	public ModelAndView ManageViewGet(@RequestParam(required=true,defaultValue="1") Integer page){
		
		PageHelper.startPage(page, 6);
		
		Map<String,Object> model = new HashMap<>();
		List<Job> job= jobService.jobGetByName(userId,searchName);
		
		PageInfo<Job> pageJob = new PageInfo<Job>(job);	
		
		model.put("job", job);
		model.put("page", pageJob);
		return new ModelAndView("JobManage/JobManageTest",model);
			
	}
	
	@RequestMapping("/jobAddView.do")
	public ModelAndView jobAddView(){
		return new ModelAndView("JobManage/JobAdd");
	}
	
	@RequestMapping("/jobAdd.do")
	public ModelAndView jobAdd(HttpServletRequest req){;
		
		Job job = new Job();
		
		job.setJOB_USER_ID(userId);
		job.setJOB_NAME(req.getParameter("name"));
		job.setJOB_COUNT(Integer.valueOf(req.getParameter("count")));
		job.setJOB_SALARY(Integer.valueOf(req.getParameter("salary")));
		job.setJOB_INTRODUCTION(req.getParameter("introduciton"));
		job.setJOB_CONDITION(req.getParameter("condition"));		
		try {
			job.setJOB_END_TIME(new SimpleDateFormat("yyyy-MM-dd").parse(req.getParameter("endTime")));
		} catch (ParseException e) {
			return null;
		}
	
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
	public ModelAndView jobUpdate(HttpServletRequest req){
		
		Job job = new Job();
		
		job.setJOB_ID(Integer.valueOf(req.getParameter("id")));
		job.setJOB_COUNT(Integer.valueOf(req.getParameter("count")));
		job.setJOB_SALARY(Integer.valueOf(req.getParameter("salary")));
		job.setJOB_INTRODUCTION(req.getParameter("introduciton"));
		job.setJOB_CONDITION(req.getParameter("condition"));		
		try {
			job.setJOB_END_TIME(new SimpleDateFormat("yyyy-MM-dd").parse(req.getParameter("endTime")));
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
	
	/*	@RequestMapping("/jobGetByName.do")
	public ModelAndView jobGetByName(HttpServletRequest req,@RequestParam(required=true,defaultValue="1") Integer page){
		
		PageHelper.startPage(page, 5);
		
		Map<String,Object> model = new HashMap<>();
		List<Job> job= jobService.jobGetByName(userId,req.getParameter("search"));
		
		PageInfo<Job> pageJob = new PageInfo<Job>(job);	
		
		model.put("job", job);
		model.put("page", pageJob);
		return new ModelAndView("JobManage/JobManageTest",model);
	}*/
}
