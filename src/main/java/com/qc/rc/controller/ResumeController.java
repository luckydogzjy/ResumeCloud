package com.qc.rc.controller;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qc.rc.common.FormParameterUtil;
import com.qc.rc.common.ServerResponse;
import com.qc.rc.entity.DownloadRecord;
import com.qc.rc.entity.Pic;
import com.qc.rc.entity.Resume;
import com.qc.rc.entity.SharingCenter;
import com.qc.rc.entity.pojo.ResumePojo;
import com.qc.rc.entity.pojo.SharingCenterPojo;
import com.qc.rc.service.IPictureService;
import com.qc.rc.service.ResumeService;

@Controller
@RequestMapping("Resume")
public class ResumeController {

	private static Logger log = Logger.getLogger(ResumeController.class);
	
	@Autowired 
	private ResumeService resumeService;
	@Autowired
	private IPictureService iPictureService;
	
	//正常应该在session里得到登录人的userId
	//这里只做测试
	public static String userId = "1b786bc41114f67ae059cea5f1789d";
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
	
//		System.out.println();
		//根据id取到要显示的resume
		ResumePojo resumePojo = resumeService.getResumeDetailsById(resumeIdStr);
		
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
		
		SharingCenter sharingCenter = null;
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
	/**
	 *   @Description   简历新增
	 * 包括简历表新增、关联表新增、以及文件表(如果上传了文件)
	 */ 
	@RequestMapping(value="/resume_add.do",method=RequestMethod.POST)
	@ResponseBody
	public ModelAndView resume_add(@RequestParam(value="upload_file" ,required = false) MultipartFile file,Resume resume,HttpServletRequest request,String resumeBirthdayString){
		Map<String,Object> model = new HashMap<String,Object>();	
		try{	
			
		
		    //resumeId = uuid生成
			String resumeId = getuuid32();
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
//			request.getSession().getAttribute("username");
//			request.getSession().setAttribute("person", person);
			//需要从session中取
			resume.setResumeCreateUser("zhang");
			if(checkResumeInFo(resume)){				
				//插入到resume表中
				int resultCount = resumeService.resumeAdd(resume);
				
				if(resultCount == 0){
					//插入到resume表失败
					model.put("msg","插入简历信息时出错");
					return new ModelAndView("resume/resume_error",model);
				}
				
				//上传文件
				
				
                String picId = getuuid32();
//	    		request.getSession().getAttribute("userId");
//	    		request.getSession().setAttribute("person", person);
	            String piccresteuser = "zhang";
	                
	             if(file!=null){
	            	 String path =request.getSession().getServletContext().getRealPath("upload");
				     String filepath = iPictureService.upload(file,path);
				     if(filepath==null){
				    	 System.out.println("未上传图片");
				     }
				     else if(filepath.equals("error")){
				    	 System.out.println("上传有问题");
				    	 model.put("msg","文件上传时出错");
						 return new ModelAndView("resume/resume_error",model);
				     } else{
				    		int result = resumeService.resumeAddPic(picId,resumeId,piccresteuser,filepath);
					    	if(result ==0){
					    		model.put("msg","插入简历信息图片插入时出错");
								return new ModelAndView("resume/resume_error",model);
					    	}
				     }

	             }   
				
				
				
				
				
			/*	String fileway = Fileuploading(request);
				if(StringUtils.isNotBlank(fileway)){
				    //添加到数据库
					
	                String picId = getuuid32();
//	    			request.getSession().getAttribute("userId");
//	    			request.getSession().setAttribute("person", person);
	                String piccresteuser = "zhang";
	                
	                int addpicresult = resumeService.resumeAddPic(picId,resumeId,piccresteuser,fileway);
	                if(addpicresult==0){
	                	//插入到文件表失败
	                	model.put("msg","插入简历信息时出错");
						return new ModelAndView("resume/resume_error",model);
	                }
//	                int addpicresult = resumeService.resumeAddPic(pic);
				}*/
				
				String userResumeId = getuuid32();
				//Session中取用户id		
				String userId = "1b786bc41114f67ae059cea5f1789d";	
//				request.getSession().getAttribute("userId");
//				request.getSession().setAttribute("person", person);
				//插入到连接表
				int result = resumeService.resumeAddResumeUser(userResumeId,userId,resumeId);
				if(result==0){
					//插入到关联表失败
					model.put("msg","插入简历信息时出错");
					return new ModelAndView("resume/resume_error",model);
				}
				
				}else {
				/*log.debug("数据验证有误");*/
					model.put("msg","输入的简历信息有误");
					return new ModelAndView("resume/resume_error",model);
				}		
		
			} catch ( Exception e){		
				//打印到log里
				e.printStackTrace();
				model.put("msg","插入简历信息时出错");
				return new ModelAndView("resume/resume_error",model);
		}	
		
		
		ResumePojo resumePojo = resumeService.getResumeDetailsById(resume.getResumeId());
		if(resumePojo != null){
			for (int i=0;i<resumePojo.getlPics().size();i++){
				resumePojo.getlPics().get(i).setpPic("https://kbase-1256168134.file.myqcloud.com/" + resumePojo.getlPics().get(i).getpPic());
			}
		}
		model.put("resume", resumePojo);
		return new ModelAndView("resume/resumeAddUpdateResult",model);
		
		
		
		
	}
		
	
	
	
	
	
	
	
	/**
	 * 
	 * @param 修改简历前查询队形简历信息
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/resume_update_show.do",method=RequestMethod.GET)
	public ModelAndView resumeUpdateShow(String resume_id,HttpServletRequest request){		
		Map<String,Object> model = new HashMap<String,Object>();
		try{
			System.out.println(resume_id);			
			ResumePojo resumePojo = resumeService.getResumeDetailsById(resume_id);		
			if(resumePojo != null){
				for (int i=0;i<resumePojo.getlPics().size();i++){
					resumePojo.getlPics().get(i).setpPic("https://kbase-1256168134.file.myqcloud.com/" + resumePojo.getlPics().get(i).getpPic());
				}				
				
				model.put("resume",resumePojo);			
				return new ModelAndView("resume/resume_update",model);
			}else {	
				model.put("msg","查找简历信息时出错");
				return new ModelAndView("resume/resume_error",model);
			}
						
		}catch ( Exception e){
			e.printStackTrace();
			model.put("msg","查找简历信息时出错");
			return new ModelAndView("resume/resume_error",model);
		}		
	}
		
	
	
	
	
	
	/**
	 * 修改简历信息   包括简历表以及文件表
	 * @return
	 */
	@RequestMapping(value="/resume_update.do",method=RequestMethod.POST)
	public ModelAndView resumeUpdate(@RequestParam(value="upload_file" ,required = false) MultipartFile file,Resume resume,Pic pic,String resumeBirthdayString,String changeway,HttpServletRequest request){
		
		System.out.println(file+"测试");
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
//		request.getSession().getAttribute("userName");
		resume.setResumeUpdateUser("zhang");
		
		if(checkResumeInFo(resume)){
			//更新resume表
			int result  = resumeService.resumeUpdate(resume);
			if(result == 0){
				//更新resume表失败
				model.put("msg","更新简历信息时出错");
				return new ModelAndView("resume/resume_error",model);
			}
			
			
			 if(file!=null){
			 String path =request.getSession().getServletContext().getRealPath("upload");
		     String filepath = iPictureService.upload(file,path);
		     System.out.println(filepath);
		     if(filepath==null){
		    	 System.out.println("未上传图片");
		     } else if(filepath.equals("error")){
		    	 System.out.println("上传有问题");
		    	 model.put("msg","文件上传时出错");
				 return new ModelAndView("resume/resume_error",model);
		     } else{
		    	 
		    	 	pic.setpId(getuuid32());
		            pic.setpResumeId(resume.getResumeId());
//					request.getSession().getAttribute("userName");
		            pic.setpUpdateUser("zhang");
		            pic.setpCreateUser("zhang");
		            pic.setpPic(filepath);
		    	 
		    	 
		    	 if(changeway.equals("替换")){
		    	    String picId = getuuid32();
//					request.getSession().getAttribute("userName");
		            String piccresteuser = "username";
		    		
		    		int deletepicresult =  resumeService.deletePicById(resume.getResumeId());
		    		 
		    		int picaddresult = resumeService.resumeAddPic(picId,resume.getResumeId(),piccresteuser,filepath);
	  	            if(picaddresult==0 || deletepicresult==0){
	  	            	//更新resume表失败
	  	            	model.put("msg","更新简历信息时出错");
	  					return new ModelAndView("resume/resume_error",model);
	  	            }
	            	
	            }else if(changeway.equals("新增")){
	            	
	            	String picId = getuuid32();
//					request.getSession().getAttribute("userName");
	            	String piccresteuser = "username";
		            
	            	int picaddresult = resumeService.resumeAddPic(picId,resume.getResumeId(),piccresteuser,filepath);
	            	
	  	            if(picaddresult==0){
	  	            	//更新resume表失败
	  	            	model.put("msg","更新简历信息时出错");
	  					return new ModelAndView("resume/resume_error",model);
	  	            }
	            	
	            }else {
	            	//获取文件修改方式失败
	            	model.put("msg","更新简历信息时出错");
					return new ModelAndView("resume/resume_error",model);
	            }
		     }
			
			 }
			
			
		} else {
			//插入数据验证失败
			model.put("msg","数据输入出错");
			return new ModelAndView("resume/resume_error",model);
		}
		
		ResumePojo resumePojo = resumeService.getResumeDetailsById(resume.getResumeId());
		if(resumePojo != null){
			for (int i=0;i<resumePojo.getlPics().size();i++){
				resumePojo.getlPics().get(i).setpPic("https://kbase-1256168134.file.myqcloud.com/" + resumePojo.getlPics().get(i).getpPic());
			}
		}
		model.put("resume", resumePojo);
		return new ModelAndView("resume/resumeAddUpdateResult",model);
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
	
	
	
	
	
	/*
	 * @Description  图片上传到指定文件夹
	 * @return 文件的路径
	 * */
	public String Fileuploading(HttpServletRequest request){
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
            	
                 path ="D:/Resumepicture/R" + getuuid32() + files.getOriginalFilename();
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
    return path;
    }
	
	
	
	
	
	
	/**
	 *  @Description 数据验证
	 */
	private boolean checkResumeInFo(Resume resume2) {
//		System.out.println(resume2.toString());
//		if(StringUtils.isBlank(resume)){}
	
		return true;	
	}
	
	
	public static String getuuid32(){
		return UUID.randomUUID().toString().replace("-", "").toLowerCase();
	}
	
}
