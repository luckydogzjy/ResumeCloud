package com.qc.rc.controller;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.qc.rc.common.FormParameterUtil;
import com.qc.rc.entity.Pic;
import com.qc.rc.entity.Resume;
import com.qc.rc.entity.SharingCenter;
import com.qc.rc.entity.UserResume;
import com.qc.rc.entity.pojo.ResumePojo;
import com.qc.rc.service.ResumeService;

@Controller

public class ResumeController {

	@Autowired 
	private ResumeService resumeService;
	@Autowired 
	private ResumePojo resumePojo;
	@Autowired 
	private SharingCenter sharingCenter;
	@Autowired
	private Resume resume;
	@Autowired
	private UserResume userresume;
	
	//正常应该在session里得到登录人的userId
	//这里只做测试
	public static Integer userId = 1;
	
	@RequestMapping("/resumeDisplay.do")
	public ModelAndView resumeDisplay(HttpServletRequest request,  HttpServletResponse response){
		
		
		
		List<ResumePojo> list = resumeService.getAllResume(userId);
				
		Map<String,Object> model = new HashMap<String,Object>(); 
		model.put("resumeList", list);
		return new ModelAndView("resume/resumeDisplay",model);
		
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

		
//		System.out.println(resumeName);
//		System.out.println(resumeJobIntension);
//		System.out.println(resumeSex);
//		System.out.println(resumeEducation);
//		System.out.println(resumeWorkYears);
//		System.out.println(resumeGraduateInstitution);
		
		List<ResumePojo> list = resumeService.getResumeListByCondition(userId,resumeName, resumeJobIntension, resumeSex, resumeEducation, resumeWorkYears, resumeGraduateInstitution);
		
		System.out.println("getResumeListByCondition里得到的list长度为" + list.size());
		
		Map<String,Object> model = new HashMap<String,Object>(); 
		model.put("resumeList", list);
		
		model.put("resumeName", resumeName);
		model.put("resumeJobIntension", resumeJobIntension);
		model.put("resumeSex", resumeSex);
		model.put("resumeEducation", resumeEducation);
		model.put("resumeWorkYears", resumeWorkYears);
		model.put("resumeGraduateInstitution", resumeGraduateInstitution);
		
		return new ModelAndView("resume/resumeDisplay",model);
		
	}
	
	@RequestMapping("/resumeDetails.do")
	public ModelAndView resumeDetails(HttpServletRequest request,  HttpServletResponse response){
		
		String resumeIdStr = request.getParameter("resumeId_Details");
		Integer resumeId = Integer.valueOf(resumeIdStr);
//		System.out.println();
		//根据id取到要显示的resume
		resumePojo = resumeService.getResumeDetailsById(resumeId);
		
//		List<Pic> list = resumePojo.getlPics();
//		
//		for (Pic pic : list) {
//			System.out.println(pic.toString());
//		}
		
		Map<String,Object> model = new HashMap<String,Object>(); 
		model.put("resume", resumePojo);
		
		return new ModelAndView("resume/resumeDetails",model);
		
	}
	
	@RequestMapping("/resumeDelete.do")
	public ModelAndView resumeDelete(HttpServletRequest request,  HttpServletResponse response){
		
		String resumeIdStr = request.getParameter("resumeId_Delete");
		Integer resumeId = Integer.valueOf(resumeIdStr);
		
		System.out.println(resumeId);
		System.out.println("111");
//		System.out.println();
		//根据id删除resume
		resumeService.deleteResumeById(resumeId);
		//删除之后根据刚才所输入的信息遍历resumeList
		
		return getResumeListByCondition(request);
		
	}
	
	@RequestMapping("/resumeShare.do")
	public ModelAndView resumeShare(HttpServletRequest request,  HttpServletResponse response){
		
		String resumeIdStr = request.getParameter("resumeId_Share");
		Integer resumeId = Integer.valueOf(resumeIdStr);
		
		String integralStr = request.getParameter("integral");
		Integer integral = Integer.valueOf(integralStr);
		
		System.out.println(resumeId);
		System.out.println(userId);
		System.out.println(integral);
		System.out.println("111");
		
		sharingCenter.setScUserId(userId);
		sharingCenter.setScResumeId(resumeId);
		sharingCenter.setScIntegral(integral);
		
		Integer integer = resumeService.shareResume(sharingCenter);	
		
		System.out.println("返回的主键为"+ sharingCenter.getScId());
		
		
		
		return getResumeListByCondition(request);
		
	}
	
	
	
	
	
/*      zhang      */
	
	@RequestMapping("/resume_add.do")
	public ModelAndView resume_add(HttpServletRequest request){
		/*String resume_name, String resume_sex ,String resume_phone ,
			String resume_email, String resume_education ,String resume_address ,String resume_birthday,
			String resume_job_intension,String resume_graduate_institution,String resume_work_years,
			String resume_self_evaluation,String resume_work_experience){*/
		String resume_name = request.getParameter("resume_name");
		String resume_sex = request.getParameter("resume_sex");
		String resume_phone = request.getParameter("resume_phone");
		String resume_email = request.getParameter("resume_email");
		String resume_education =request.getParameter("resume_education");
		String resume_address = request.getParameter("resume_address");
		String resume_birthday = request.getParameter("resume_birthday");
		String resume_job_intension =request.getParameter("resume_job_intension");
		String resume_graduate_institution = request.getParameter("resume_graduate_institution");
		String resume_work_years =request.getParameter( "resume_work_years");
		String resume_self_evaluation = request.getParameter("resume_self_evaluation");
		String resume_work_experience = request.getParameter("resume_work_experience");			
	
		//需要更改	
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//小写的mm表示的是分钟
		java.util.Date date_Birthday;
		try {
			date_Birthday = sdf.parse(resume_birthday);
		} catch (Exception e) {
		
			date_Birthday = new Date();
			e.printStackTrace();
		}
	
		resume.setResumeName(resume_name);
		resume.setResumeSex(Integer.valueOf(resume_sex) );
		resume.setResumePhone(resume_phone);
		resume.setResumeEmail(resume_email);
		resume.setResumeEducation(Integer.valueOf(resume_education));
		resume.setResumeAddress(resume_address);
		resume.setResumeBirthday(date_Birthday);
		resume.setResumeJobIntension(resume_job_intension);
		resume.setResumeGraduateInstitution(resume_graduate_institution);
		resume.setResumeWorkYears(Integer.valueOf(resume_work_years));
		resume.setResumeSelfEvaluation(resume_self_evaluation);
		resume.setResumeWorkExperience(resume_work_experience);
		//需要从session中取
		resume.setResumeCreateUser("zhang");	
		
		
		
		
		
		Map<String,Object> model = new HashMap<String,Object>();
		//增加到resume表中
		int resultCount = resumeService.resumeAdd(resume);
		System.out.println("新增的id："  +  resume.getResumeId());
		int bestid = resume.getResumeId();
		if(resultCount != 0){
//			int bestid = resumeService.selectResumeBestId();
			System.out.println("最大值id：" + bestid);
			
			
			//需要从session中取
			int urUesrId = 1;
			userresume.setUrUesrId(urUesrId);
			userresume.setUrResumeId(bestid);
			int result = resumeService.resumeAddResumeUser(userresume);
			
			if( result == 1){
				
				model.put("info", "增加简历信息成功");
				return new ModelAndView("resume/resume_add",model);
				
			} else {
				model.put("info", "增加简历信息失败");
				return new ModelAndView("resume/resume_add",model);
			}
			
		}else {
			model.put("info", "增加简历信息失败");
			return new ModelAndView("resume/resume_add",model);
		}
	}
	
	
	
	
	@RequestMapping("/resume_update_show.do")
	public ModelAndView resumeUpdateShow(HttpServletRequest request){	

		String id = request.getParameter("resume_id");
		
		//假数据  需要前端传进来
//		id = "22";
		System.out.println(id);
		
		resume  = resumeService.resumeUpdateSelect(id);
		
		Map<String,Object> model = new HashMap<String,Object>();
		
		if(resume != null){

			System.out.println("有");
			model.put("resume",resume);				
			return new ModelAndView("resume/resume_update",model);
		}else {
			
			System.out.println("没有");
			model.put("info", "没找到");		
			return new ModelAndView("resume/resume_update",model);
		}
	
		
	}
		
	
	
	@RequestMapping("/resume_update.do")
	public ModelAndView resumeUpdate(HttpServletRequest request){
		
		
		String resume_id = request.getParameter("resume_id");
		String resume_name = request.getParameter("resume_name");
		String resume_sex = request.getParameter("resume_sex");
		String resume_phone = request.getParameter("resume_phone");
		String resume_email = request.getParameter("resume_email");
		String resume_education =request.getParameter("resume_education");
		String resume_address = request.getParameter("resume_address");
		String resume_birthday = request.getParameter("resume_birthday");
		String resume_job_intension =request.getParameter("resume_job_intension");
		String resume_graduate_institution = request.getParameter("resume_graduate_institution");
		String resume_work_years =request.getParameter( "resume_work_years");
		String resume_self_evaluation = request.getParameter("resume_self_evaluation");
		String resume_work_experience = request.getParameter("resume_work_experience");
		
		//需要更改
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//小写的mm表示的是分钟
		java.util.Date date_Birthday;
		try {
			date_Birthday = sdf.parse(resume_birthday);
		} catch (Exception e) {
		
			date_Birthday = new Date();
			e.printStackTrace();
		}
		
		resume.setResumeId(Integer.valueOf(resume_id));
		resume.setResumeName(resume_name);
		resume.setResumeSex(Integer.valueOf(resume_sex) );
		resume.setResumePhone(resume_phone);
		resume.setResumeEmail(resume_email);
		resume.setResumeEducation(Integer.valueOf(resume_education));
		resume.setResumeAddress(resume_address);
		resume.setResumeBirthday(date_Birthday);
		resume.setResumeJobIntension(resume_job_intension);
		resume.setResumeGraduateInstitution(resume_graduate_institution);
		resume.setResumeWorkYears(Integer.valueOf(resume_work_years));
		resume.setResumeSelfEvaluation(resume_self_evaluation);
		resume.setResumeWorkExperience(resume_work_experience);
		//需要改正  从seccion中取出
		resume.setResumeUpdateUser("zhang");
		
		int result  = resumeService.resumeUpdate(resume);
		
		Map<String,Object> model = new HashMap<String,Object>();
		
		if(result == 1){
			System.out.println("更改成功");	
			model.put("info", "成功");
			return new ModelAndView("resume/resume_update",model);
		}else {
			
			System.out.println("更改时出错");	
			model.put("info", "出错");
			return new ModelAndView("resume/resume_update",model);
		}
	
		
	}
	
	@RequestMapping("/tiaozhuan_add.do")
	public ModelAndView tiaozhuan_add(){
		Map<String,Object> model = new HashMap<String,Object>(); 
		return new ModelAndView("resume/resume_add",model);
	}

	@RequestMapping("/tiaozhuan_update.do")
	public ModelAndView tiaozhuan_update(){
		Map<String,Object> model = new HashMap<String,Object>(); 
		return new ModelAndView("resume/resume_update",model);
	}
	
	
	
	
	
	
	
}
