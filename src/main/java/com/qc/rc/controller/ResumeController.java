package com.qc.rc.controller;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import com.qc.rc.common.FormParameterUtil;
import com.qc.rc.entity.Pic;
import com.qc.rc.entity.Resume;
import com.qc.rc.entity.SharingCenter;
import com.qc.rc.entity.User;
import com.qc.rc.entity.pojo.ResumePojo;
import com.qc.rc.service.ResumeService;

@Controller
@RequestMapping("Resume")
public class ResumeController {

	@Autowired 
	private ResumeService resumeService;
	@Autowired
	private HttpSession session;
	
	User user = new User();
	
	@RequestMapping(value="/getResumeListByCondition.do",method=RequestMethod.GET)
	public ModelAndView getResumeListByCondition(ResumePojo searchResumePojo,@RequestParam(required=true,defaultValue="1") Integer page) {

		user.setUserId(1);	
	//	User user = (User) session.getAttribute("user");
		
		if (user != null) {
			
			if (searchResumePojo.getResumeSex() == null) {
				searchResumePojo.setResumeSex(-1);
			}
			if (searchResumePojo.getResumeEducation() == null) {
				searchResumePojo.setResumeEducation(-1);
			}
			if (searchResumePojo.getResumeWorkYears() == null) {
				searchResumePojo.setResumeWorkYears(-1);
			}

			try {
				if (searchResumePojo.getResumeName() != null) {
					searchResumePojo.setResumeName(FormParameterUtil.changeCode(searchResumePojo.getResumeName()));
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
			model = resumeService.getResumeListByCondition(user.getUserId(),searchResumePojo,page);		
			return new ModelAndView("resume/resumeDisplay",model);
			
		} else {
			
			//调到登录页面
			System.out.println("登录");
		}
		System.out.println("空");
		return null;
		
	}
	
	
	@RequestMapping(value="/resumeDetails.do",method=RequestMethod.GET)
	public ModelAndView resumeDetails(Integer resumeId_Details){
		
		//根据id取到要显示的resume
		Map<String,Object> model = new HashMap<String,Object>(); 
		model = resumeService.getResumeDetailsById(resumeId_Details);	
		
		return new ModelAndView("resume/resumeDetails",model);
		
	}
	
	@RequestMapping(value="/resumeDelete.do",method=RequestMethod.GET)
	public ModelAndView resumeDelete(ResumePojo searchResumePojo,Integer resumeId_Delete,Integer page){

		//根据id删除resume
		resumeService.deleteResumeById(resumeId_Delete);
		//删除之后根据刚才所输入的信息遍历resumeList
		return getResumeListByCondition(searchResumePojo,page);
		
	}
	
	@RequestMapping(value="/resumeShare.do",method=RequestMethod.GET)
	public ModelAndView resumeShare(ResumePojo searchResumePojo,SharingCenter sharingCenter,Integer page){
		
		user.setUserId(1);
		
		if (user != null) {
			
			//将从session得到的id set
			sharingCenter.setScUserId(user.getUserId());
			
			//将共享信息插入  返回主键 ，暂时没用
			Integer integer = resumeService.shareResume(sharingCenter);			
			//执行完插入后，要将RC_USER_RESUME表更新		
			resumeService.updateUserResume(sharingCenter.getScResumeId());
			return getResumeListByCondition(searchResumePojo,page);	
			
		} else {
			System.out.println("登录");
		}
		return null;	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
/*      zhang      */
	/*
	 * 简历新增
	 * 包括简历表新增、关联表新增、以及文件表(如果上传了文件)
	 * */
	@RequestMapping(value="/resume_add.do",method=RequestMethod.POST)
	public ModelAndView resume_add(Resume resume,HttpServletRequest request,String resumeBirthdayString){
		Map<String,Object> model = new HashMap<String,Object>();	
		try{	
			
		
		    //resumeId = uuid生成
			int resumeId = 129;
			resume.setResumeId(resumeId);
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//小写的mm表示的是分钟
			java.util.Date date_Birthday;
			try {
				date_Birthday = sdf.parse(resumeBirthdayString);
			} catch (Exception e) {	
				date_Birthday = new Date();
				e.printStackTrace();
				}

			resume.setResumeBirthday(date_Birthday);
//			request.getSession().getAttribute("userId");
			//需要从session中取
			resume.setResumeCreateUser("zhang");
			if(checkResumeInFo(resume)){
				
				//插入到resume表中
				int resultCount = resumeService.resumeAdd(resume);
				//插入到pic表
				
				if(resultCount == 0){
					//插入到resume表失败
					return new ModelAndView("resume/resume_error",model);
				}
				
				String fileway = Fileuploading(request);
				if(StringUtils.isNotBlank(fileway)){
				    //添加到数据库
	               
	               //uuid生成
	                int picId = 222;
	                String piccresteuser = "zhang";
	                
	                int addpicresult = resumeService.resumeAddPic(picId,resumeId,piccresteuser,fileway);
	                if(addpicresult==0){
	                	//插入到文件表失败
						return new ModelAndView("resume/resume_error",model);
	                }
//	                int addpicresult = resumeService.resumeAddPic(pic);
				}
				
				int userResumeId = 130;
				//Session中取用户id		
				int userId = 1;		
				//插入到连接表
				int result = resumeService.resumeAddResumeUser(userResumeId,userId,resumeId);
				if(result==0){
					//插入到关联表失败
					return new ModelAndView("resume/resume_error",model);
				}
				
				}else {
				/*log.debug("数据验证有误");*/
					System.out.println("数据验证有误");
				}		
		
			} catch ( Exception e){		
				//打印到log里
				e.printStackTrace();
		}
		return new ModelAndView("resume/resume_add",model);
	}
		
	



	/*
	 * 修改之前通过id查找信息，返回到前端页面
	 * 方便用户修改
	 * */
	@RequestMapping(value="/resume_update_show.do",method=RequestMethod.GET)
	public ModelAndView resumeUpdateShow(String resume_id,HttpServletRequest request){		
		Map<String,Object> model = new HashMap<String,Object>();
		try{		
		
			int id = Integer.valueOf(resume_id);
			
			Resume resume  = resumeService.resumeUpdateSelect(id);
			if(resume != null){
				model.put("resume",resume);	
				return new ModelAndView("resume/resume_update",model);
			}else {	
				//查找失败
				return new ModelAndView("resume/resume_update",model);
			}
						
		}catch ( Exception e){
			e.printStackTrace();
			return new ModelAndView("resume/resume_update",model);
		}		
	}
		
	
	
	
	/*
	 * 修改简历信息
	 * 包括简历表以及文件表
	 * */
	@RequestMapping(value="/resume_update.do",method=RequestMethod.POST)
	public ModelAndView resumeUpdate(Resume resume,Pic pic,String resumeBirthdayString,HttpServletRequest request){
		
		Map<String,Object> model = new HashMap<String,Object>();
	
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//小写的mm表示的是分钟
		java.util.Date date_Birthday;
		try {
			date_Birthday = sdf.parse(resumeBirthdayString);
		} catch (Exception e) {	
			date_Birthday = new Date();
			e.printStackTrace();
			}
		
		resume.setResumeBirthday(date_Birthday);
		resume.setResumeUpdateUser("zhang");
		
		if(checkResumeInFo(resume)){
			//更新resume表
			int result  = resumeService.resumeUpdate(resume);
			if(result == 0){
				//更新resume表失败
				return new ModelAndView("resume/resume_error",model);
			}
			
			String fileway = Fileuploading(request);
			if(StringUtils.isNotBlank(fileway)){
				 //修改到数据库
	            pic.setpResumeId(resume.getResumeId());
	            pic.setpUpdateUser("zhang");
	            pic.setpPic(fileway);
	            
	            int addpicresult = resumeService.resumeUpdatePic(pic);
	            if(addpicresult==0){
	            	//更新resume表失败
					return new ModelAndView("resume/resume_error",model);
	            }
		
			}
			
			
		} else {
			//插入数据验证失败
			return new ModelAndView("resume/resume_error",model);
		}
		return null;
	}
	
	
	
	
	@RequestMapping(value="/tiaozhuan_add.do",method=RequestMethod.GET)
	public ModelAndView tiaozhuan_add(){
		Map<String,Object> model = new HashMap<String,Object>(); 
		return new ModelAndView("resume/resume_add",model);
	}

	@RequestMapping(value="/tiaozhuan_update.do",method=RequestMethod.GET)
	public ModelAndView tiaozhuan_update(){
		Map<String,Object> model = new HashMap<String,Object>(); 
		return new ModelAndView("resume/resume_update",model);
	}
	
	
	
	
	

	public String Fileuploading(HttpServletRequest request){
	long startTime = System.currentTimeMillis();
	String path = null;
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
            	
                 path ="D:/Resumepicture/R" + files.getOriginalFilename();
                System.out.println(path);
                //上传
                try {
					files.transferTo(new File(path));
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("文件上传到文件夹出错");     			
				} 
                
            }
        }
    }
    long endTime = System.currentTimeMillis();
    System.out.println("文件上传运行时间："+String.valueOf(endTime-startTime)+"ms");  
    System.out.println(path);
    return path;
    }
	
	
	
	
	
	
	
	private boolean checkResumeInFo(Resume resume2) {
		System.out.println(resume2.toString());
//		if(StringUtils.isBlank(resume)){}
	
		return true;	
	}
}
