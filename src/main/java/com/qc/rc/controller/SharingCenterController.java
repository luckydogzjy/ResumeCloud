package com.qc.rc.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.qc.rc.service.SharingCenterService;

@Controller
@RequestMapping("SharingCenter")
public class SharingCenterController {
	
	
	@Autowired 
	private SharingCenterService sharingCenterService;
	
	
	@RequestMapping(value="/getSharingResumeListByCondition.do")
	public ModelAndView getSharingResumeListByCondition(String resumeJobIntension,Integer resumeSex,Integer resumeEducation,
													Integer resumeWorkYears,String resumeGraduateInstitution,@RequestParam(required=true,defaultValue="1") Integer page) {
		
		System.out.println(resumeJobIntension);
		System.out.println(resumeSex);
		System.out.println(resumeEducation);
		System.out.println(resumeWorkYears);
		System.out.println(resumeGraduateInstitution);
		
		if (resumeSex == null) {
			resumeSex = -1;
		}
		if (resumeEducation == null) {
			resumeEducation = -1;
		}
		if (resumeWorkYears == null) {
			resumeWorkYears = -1;
		}
		
		
		Map<String,Object> model = new HashMap<String,Object>(); 
		model = sharingCenterService.getSharingResumeListByCondition(resumeJobIntension, resumeWorkYears, resumeSex, resumeEducation, resumeGraduateInstitution, page);

		return new ModelAndView("resume/resumeSharingCenter",model);
	}
	
}
