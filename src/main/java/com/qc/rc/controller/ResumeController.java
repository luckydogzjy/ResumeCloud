package com.qc.rc.controller;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qc.rc.common.FormParameterUtil;
import com.qc.rc.entity.DownloadRecord;
import com.qc.rc.entity.Pic;
import com.qc.rc.entity.Resume;
import com.qc.rc.entity.SharingCenter;
import com.qc.rc.entity.UserResume;
import com.qc.rc.entity.pojo.ResumePojo;
import com.qc.rc.entity.pojo.SharingCenterPojo;
import com.qc.rc.service.ResumeService;

@Controller
@RequestMapping("Resume")
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
	@Autowired
	private Pic pic;
	
	//正常应该在session里得到登录人的userId
	//这里只做测试
	public static Integer userId = 1;
	public static Integer pageShow = 5;  //一页显示几条数据
	@RequestMapping("/resumeDisplay.do")
	public ModelAndView resumeDisplay(HttpServletRequest request,@RequestParam(required=true,defaultValue="1") Integer page){
		
		//引入分页查询，使用PageHelper分页功能
        //在查询之前传入当前页，然后多少记录
		PageHelper.startPage(page,pageShow);
		
		List<ResumePojo> list = resumeService.getAllResume(userId);
				
		Map<String,Object> model = new HashMap<String,Object>(); 
		
		PageInfo<ResumePojo> pageResumePojo = new PageInfo<ResumePojo>(list);
		
		model.put("resumeList", list);
		model.put("page", pageResumePojo);
		return new ModelAndView("resume/resumeDisplay",model);
		
	}
	
	@RequestMapping("/getResumeListByCondition.do")
	public ModelAndView getResumeListByCondition(HttpServletRequest request,@RequestParam(required=true,defaultValue="1") Integer page) {
		//引入分页查询，使用PageHelper分页功能
        //在查询之前传入当前页，然后多少记录
		PageHelper.startPage(page,pageShow);

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
		
		PageInfo<ResumePojo> pageResumePojo = new PageInfo<ResumePojo>(list);
		model.put("page", pageResumePojo);
		
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
		
		String pageStr = request.getParameter("page");
		Integer page = Integer.valueOf(pageStr);
		
		System.out.println(resumeId);
		System.out.println("111");
//		System.out.println();
		//根据id删除resume
		resumeService.deleteResumeById(resumeId);
		//删除之后根据刚才所输入的信息遍历resumeList
		
		return getResumeListByCondition(request,page);
		
	}
	
	@RequestMapping("/resumeShare.do")
	public ModelAndView resumeShare(HttpServletRequest request,  HttpServletResponse response){
		
		String resumeIdStr = request.getParameter("resumeId_Share");
		Integer resumeId = Integer.valueOf(resumeIdStr);
		
		String pageStr = request.getParameter("page");
		Integer page = Integer.valueOf(pageStr);
		
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
		
		//返回主键 ，暂时没用
		System.out.println("返回的主键为"+ sharingCenter.getScId());
		//执行完插入后，要将RC_USER_RESUME表更新		
		resumeService.updateUserResume(resumeId);
		
		
		return getResumeListByCondition(request,page);
		
	}
	
	@RequestMapping("/resumeSharingCenter.do")
	public ModelAndView resumeSharingCenter(){
		
		List<SharingCenterPojo> list = resumeService.getAllSharingResume();
		//取到全部信息后，取当前用户所兑换过的简历列表
		List<DownloadRecord> downloadRecords = resumeService.getDownloadRecordById(userId);
		
		System.out.println(list.size());
		System.out.println(downloadRecords.size());
		
		for(int i = 0; i < downloadRecords.size(); i++){
			for(int j = 0; j < list.size(); j++){
				if (downloadRecords.get(i).getDrSharingCenterId() == list.get(j).getScId()) {
					//将当前用户兑换过的简历信息，在list里将标志位赋值为1，意思为当前用户已经兑换过，
					//在前台显示 已兑换 按钮
					
					list.get(j).setFlag(1);
				}
			}
		}
		
//		for (SharingCenterPojo sharingCenterPojo : list) {
//			System.out.println(sharingCenterPojo.getScId()+ "---------"+ sharingCenterPojo.getFalg());
//		}
		
		Map<String,Object> model = new HashMap<String,Object>(); 
		model.put("sharingList", list);
		
		return new ModelAndView("resume/resumeSharingCenter",model);	
	}
	
	

	
	
	
	
	
/*      zhang      */
	
	@RequestMapping("/resume_add.do")
	public ModelAndView resume_add(HttpServletRequest request){
		/*String resume_name, String resume_sex ,String resume_phone ,
			String resume_email, String resume_education ,String resume_address ,String resume_birthday,
			String resume_job_intension,String resume_graduate_institution,String resume_work_years,
			String resume_self_evaluation,String resume_work_experience){*/
	
		long startTime = System.currentTimeMillis();
		
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
				
				
				 //将当前上下文初始化给 CommonsMutipartResolver (多部分解析器)
			    CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
			    //检查form中是否有enctype="multipart/form-data"
			    if(multipartResolver.isMultipart(request))
			    {
			        //将request变成多request
			        MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;
			        //获取multiRequest中所有的文件名
			        Iterator iter = multiRequest.getFileNames();
			        while(iter.hasNext()){
			            //一次遍历所有的文件
			            MultipartFile files = multiRequest.getFile(iter.next().toString());
			            if(files!=null && !("".equals(files.getOriginalFilename())) && files.getOriginalFilename() != null){
			            	System.out.println(files.getOriginalFilename());
			            	
			                String path = "D:/Resumepicture/R"+ resume.getResumeId()+resume.getResumeName() + files.getOriginalFilename();
			                System.out.println(path);
			                //上传
			                try {
								files.transferTo(new File(path));
							} catch (Exception e) {
								e.printStackTrace();
								System.out.println("文件上传到文件夹出错");
								model.put("info", "新增简历信息添加文件时出错");
			        			return new ModelAndView("resume/resume_add",model);
							} 
			                //添加到数据库
			                pic.setpResumeId(resume.getResumeId());
			                pic.setpCreateUser("zhang");
			                pic.setpPic(path);
			                
			                int addpicresult = resumeService.resumeAddPic(pic);
			                
			                if(addpicresult == 0){
			                	//wenjia添加失败
			                	System.out.println("文件插入数据库时出错");
			                	model.put("info", "新增简历信息添加文件时出错");
			        			return new ModelAndView("resume/resume_add",model);
			                }
			                
			                
			                
			            }
			        }
			    }
			    
			    long endTime = System.currentTimeMillis();
			    System.out.println("图片上传运行时间："+String.valueOf(endTime-startTime)+"ms");
				
				model.put("info", "增加简历信息成功");
				return  resumeDisplay(request,1);
				
			/*	return new ModelAndView("resume/resume_add",model);*/
				
			} else {
				//resumeuser表添加失败
				model.put("info", "增加简历信息失败");
				return new ModelAndView("resume/resume_add",model);
			}
			
		}else {
			//resume表添加失败
			model.put("info", "增加简历信息失败");
			return new ModelAndView("resume/resume_add",model);
		}
		
	}
	
	
	
	
	@RequestMapping("/resume_update_show.do")
	public ModelAndView resumeUpdateShow(HttpServletRequest request){	
	/////以下ww 添加的	
		//取到页数
		String pageStr = request.getParameter("page");
		//取到输入的判断条件
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
		
	
	////以上ww 添加的
		
		
		String idstr = request.getParameter("resume_id");
		
		//假数据  需要前端传进来
//		id = "22";
		int id = Integer.valueOf(idstr);
		System.out.println(id);
		
		
		resume  = resumeService.resumeUpdateSelect(id);
		
		Map<String,Object> model = new HashMap<String,Object>();
		
		if(resume != null){

			System.out.println("有");
			model.put("resume",resume);	
			//以下ww 添加的
			model.put("page",pageStr);	
			model.put("resumeName", resumeName);
			model.put("resumeJobIntension", resumeJobIntension);
			model.put("resumeSex", resumeSex);
			model.put("resumeEducation", resumeEducation);
			model.put("resumeWorkYears", resumeWorkYears);
			model.put("resumeGraduateInstitution", resumeGraduateInstitution);
			//以上
			return new ModelAndView("resume/resume_update",model);
		}else {
			
			System.out.println("没有");
			model.put("info", "没找到");		
			return new ModelAndView("resume/resume_update",model);
		}
	
		
	}
		
	
	
	@RequestMapping("/resume_update.do")
	public ModelAndView resumeUpdate(HttpServletRequest request){
		
		
		//取到页数
		String pageStr = request.getParameter("page");
		Integer page = Integer.valueOf(pageStr);
		//.............
				
		long startTime = System.currentTimeMillis();
		
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
		resume.setResumeSex(Integer.valueOf(resume_sex));
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
		
			
			 //将当前上下文初始化给 CommonsMutipartResolver (多部分解析器)
		    CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
		    //检查form中是否有enctype="multipart/form-data"
		    if(multipartResolver.isMultipart(request))
		    {
		        //将request变成多request
		        MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;
		        //获取multiRequest中所有的文件名
		        Iterator iter = multiRequest.getFileNames();
		        System.out.println(iter);
		        while(iter.hasNext()){
		            //一次遍历所有的文件
		            MultipartFile files = multiRequest.getFile(iter.next().toString());
		            System.out.println(files);
		            if(files!=null && !("".equals(files.getOriginalFilename())) && files.getOriginalFilename() != null){
		            	System.out.println(files.getOriginalFilename());
		            	
		                String path = "D:/Resumepicture/RU"+ resume.getResumeId()+resume.getResumeName() + files.getOriginalFilename();
		                System.out.println(path);
		                //上传
		                try {
							files.transferTo(new File(path));
						} catch (Exception e) {
							e.printStackTrace();
							
							System.out.println("上传文件时出错");	
							model.put("info", "上传简历文件时出错");
							return new ModelAndView("resume/resume_update",model);
						} 
		                
		                //修改到数据库
		                pic.setpResumeId(resume.getResumeId());
		                pic.setpUpdateUser("zhang");
		                pic.setpPic(path);
		                
		                int addpicresult = resumeService.resumeUpdatePic(pic);
		                
		                if(addpicresult == 0){
		                	//文件修改失败
		                	System.out.println("修改文件路径出错(数据库)");
		                	model.put("info", "修改简历信息修改文件时出错");
		        			return new ModelAndView("resume/resume_update",model);
		                }            
		            }
		        }
		    
		    }
		    
		    long endTime = System.currentTimeMillis();
		    System.out.println("文件上传运行时间："+String.valueOf(endTime-startTime)+"ms");
			
			model.put("info", "修改简历成功");
			return getResumeListByCondition(request,page);
		  /*  return new ModelAndView("resume/resume_update",model);*/

		}else {
			
			System.out.println("更改时出错(resume表修改)");	
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
