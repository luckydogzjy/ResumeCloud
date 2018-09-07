package com.qc.rc.controller;

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

import com.qc.rc.entity.Resume;
import com.qc.rc.service.ResumeService;

@Controller
public class ResumeController {

	@Autowired 
	private ResumeService resumeService;
	
	@RequestMapping("/resumeDisplay.do")
	public ModelAndView ResumeDisplay(HttpServletRequest request,  HttpServletResponse response){
		
		List<Resume> list = resumeService.getAllResume();
		HttpSession session = request.getSession();
		
		session.setAttribute("resumeList", list);
		Map<String,Object> model = new HashMap<String,Object>(); 
		return new ModelAndView("resumeDisplay",model);
		
	}
}
