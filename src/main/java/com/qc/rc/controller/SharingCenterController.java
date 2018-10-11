package com.qc.rc.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.qc.rc.common.FormParameterUtil;
import com.qc.rc.common.GetUser;
import com.qc.rc.entity.SharingCenter;
import com.qc.rc.entity.User;
import com.qc.rc.entity.pojo.ResumePojo;
import com.qc.rc.service.SharingCenterService;

@Controller
@RequestMapping("SharingCenter")
public class SharingCenterController {
	
	@Autowired 
	private SharingCenterService sharingCenterService;
	//session 获取
	@Autowired
	private HttpSession session;
	
	@RequestMapping(value="/getSharingResumeListByCondition.do",method=RequestMethod.GET)
	public ModelAndView getSharingResumeListByCondition(ResumePojo searchResumePojo,@RequestParam(required=true,defaultValue="1") Integer page,HttpServletRequest request) {
	
	//	User user = GetUser.getUser();
		//	User user = (User) session.getAttribute("user");
		User user = (User)request.getSession().getAttribute("rcuser");
		
		//如果session失效或取不到user 转发到登录
		if (user == null) {
			return new ModelAndView("redirect:/gologin1.action");
		}
		else {

			try {
				
				if (searchResumePojo.getResumeSex() == null) {
					searchResumePojo.setResumeSex(-1);
				}
				if (searchResumePojo.getResumeEducation() == null) {
					searchResumePojo.setResumeEducation(-1);
				}
				if (searchResumePojo.getResumeWorkYears() == null) {
					searchResumePojo.setResumeWorkYears(-1);
				}
				
				if (searchResumePojo.getResumeJobIntension() != null) {
					searchResumePojo.setResumeJobIntension(FormParameterUtil.changeCode(searchResumePojo.getResumeJobIntension()));

				}
				if (searchResumePojo.getResumeGraduateInstitution() != null) {
					searchResumePojo.setResumeGraduateInstitution(FormParameterUtil.changeCode(searchResumePojo.getResumeGraduateInstitution()));

				}
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			
			
			Map<String,Object> model = new HashMap<String,Object>(); 
			model = sharingCenterService.getSharingResumeListByCondition(user.getUserId(),searchResumePojo, page);

			return new ModelAndView("resume/resumeSharingCenter",model);
		}			
		
	}
	
	@RequestMapping(value="/exchangeResume.do",method=RequestMethod.GET)
	public ModelAndView exchangeResume(ResumePojo searchResumePojo,SharingCenter sharingCenter,@RequestParam(required=true,defaultValue="1") Integer page,HttpServletRequest request) {
		
	//	User user = GetUser.getUser();
		
		User user = (User)request.getSession().getAttribute("rcuser");
		
		//如果session失效或取不到user 转发到登录
		if (user == null) {
			return new ModelAndView("redirect:/gologin1.action");
		}
		else{
			try {
				sharingCenterService.exchangeResume(user, searchResumePojo, sharingCenter);
					
				return getSharingResumeListByCondition(searchResumePojo, page,request);

			} catch (Exception e) {
				e.printStackTrace();
			}	
		}
		
		return null;
	}	
	
	@RequestMapping(value="/cancelSharingResume.do",method=RequestMethod.GET)
	public ModelAndView cancelSharingResume(ResumePojo searchResumePojo,String scId,String scResumeId,@RequestParam(required=true,defaultValue="1") Integer page,HttpServletRequest request) {
		
	//	User user = GetUser.getUser();
		User user = (User)request.getSession().getAttribute("rcuser");
		
		//如果session失效或取不到user 转发到登录
		if (user == null) {
			return new ModelAndView("redirect:/gologin1.action");
		} else {
			try {
				sharingCenterService.cancelSharingResume(scId,scResumeId);
					
				return getSharingResumeListByCondition(searchResumePojo, page,request);		
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}	
}
