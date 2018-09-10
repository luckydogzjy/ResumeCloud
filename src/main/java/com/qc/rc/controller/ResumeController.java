package com.qc.rc.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.qc.rc.common.FormParameterUtil;
import com.qc.rc.entity.Resume;
import com.qc.rc.entity.pojo.ResumePojo;
import com.qc.rc.service.ResumeService;

@Controller

public class ResumeController {

	@Autowired 
	private ResumeService resumeService;
	
	//正常应该在session里得到登录人的userId
	//这里只做测试
	public static Integer userId = 1;
	
	@RequestMapping("/resumeDisplay.do")
	public ModelAndView resumeDisplay(HttpServletRequest request,  HttpServletResponse response){
		
		
		
		List<ResumePojo> list = resumeService.getAllResume(userId);
		//HttpSession session = request.getSession();
		
	//	session.setAttribute("resumeList", list);
		
//		for (ResumePojo resumePojo : list) {
//			System.out.println(resumePojo.getInterview().getInterviewStatus());
//		}
		
		Map<String,Object> model = new HashMap<String,Object>(); 
		model.put("resumeList", list);
		return new ModelAndView("resumeDisplay",model);
		
	}
	
	@RequestMapping("/getResumeListByCondition.do")
	public ModelAndView getResumeListByCondition(HttpServletRequest request) {
		

		String resumeName = null;
		String resumeJobIntension = null;
		//性别
		String resumeSexStr = request.getParameter("resumeSex");
		Integer resumeSex = Integer.valueOf(resumeSexStr);
		
		String resumeEducationStr = request.getParameter("resumeEducation");
		Integer resumeEducation = Integer.valueOf(resumeEducationStr);
		
		String resumeWorkYearsStr = request.getParameter("resumeWorkYears");
		Integer resumeWorkYears = Integer.valueOf(resumeWorkYearsStr);
		
		String resumeGraduateInstitution = null;
		
		try {
			resumeGraduateInstitution = FormParameterUtil.changeCode(request.getParameter("resumeGraduateInstitution"));
			resumeName = FormParameterUtil.changeCode(request.getParameter("resumeName"));
			resumeJobIntension = FormParameterUtil.changeCode(request.getParameter("resumeJobIntension"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		System.out.println(resumeName);
		System.out.println(resumeJobIntension);
		System.out.println(resumeSex);
		System.out.println(resumeEducation);
		System.out.println(resumeWorkYears);
		System.out.println(resumeGraduateInstitution);
		
		List<ResumePojo> list = resumeService.getResumeListByCondition(userId,resumeName, resumeJobIntension, resumeSex, resumeEducation, resumeWorkYears, resumeGraduateInstitution);
		
		System.out.println("getResumeListByCondition里得到的list长度为" + list.size());
		
		Map<String,Object> model = new HashMap<String,Object>(); 
		model.put("resumeList", list);
		
		return new ModelAndView("resumeDisplay",model);
		
	}
	
	@RequestMapping("/resumeDetails.do")
	public ModelAndView resumeDetails(HttpServletRequest request,  HttpServletResponse response){
		
		String resumeId = request.getParameter("ResumeId");
				
		System.out.println("1111");
		
		System.out.println(resumeId);
		
		System.out.println("2222");
		
		Map<String,Object> model = new HashMap<String,Object>(); 
		return new ModelAndView("resumeDetails",model);
		
	}	
	
}
