package com.qc.rc.controller;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

import com.qc.rc.common.FormParameterUtil;
import com.qc.rc.common.GetUser;
import com.qc.rc.common.GetUuid;
import com.qc.rc.entity.Pic;
import com.qc.rc.entity.Resume;
import com.qc.rc.entity.SharingCenter;
import com.qc.rc.entity.User;
import com.qc.rc.entity.pojo.ResumePojo;
import com.qc.rc.service.IPictureService;
import com.qc.rc.service.ResumeService;
import com.qc.rc.utils.COSUtil;

@Controller
@RequestMapping("Resume")
public class ResumeController {

	@Autowired 
	private ResumeService resumeService;
	@Autowired
	private HttpSession session;
	@Autowired
	private IPictureService iPictureService;
	
	@RequestMapping(value="/getResumeListByCondition.do",method=RequestMethod.GET)
	public ModelAndView getResumeListByCondition(ResumePojo searchResumePojo,@RequestParam(required=true,defaultValue="1") Integer page,HttpServletRequest request) {

		
	//	User user = (User) session.getAttribute("user");
	//	User user = GetUser.getUser();
		
		User user = (User)request.getSession().getAttribute("rcuser");
		
		//如果session失效或取不到user 转发到登录
		if (user == null) {
			return new ModelAndView("redirect:/gologin1.action");
		}
		
		else {
			
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
			
		} 		
	}
	
	
	@RequestMapping(value="/resumeDetails.do",method=RequestMethod.GET)
	public ModelAndView resumeDetails(String resumeId_Details){
		
		//根据id取到要显示的resume
		Map<String,Object> model = new HashMap<String,Object>(); 
		ResumePojo resumePojo = resumeService.getResumeDetailsById(resumeId_Details);	
	
		if(resumePojo != null){
			for (int i=0;i<resumePojo.getlPics().size();i++){
				resumePojo.getlPics().get(i).setpPic("https://kbase-1256168134.file.myqcloud.com/" + resumePojo.getlPics().get(i).getpPic());
			}
			for (int i = 0; i < resumePojo.getlFiles().size(); i++) {
				resumePojo.getlFiles().get(i).setfFile(COSUtil.generatePresignedUrl(resumePojo.getlFiles().get(i).getfFile()));
			}
		}
		model.put("resume", resumePojo);
		
		return new ModelAndView("resume/resumeDetails",model);
		
	}
	
	@RequestMapping(value="/resumeDelete.do",method=RequestMethod.GET)
	public ModelAndView resumeDelete(ResumePojo searchResumePojo,String resumeId_Delete,Integer page,HttpServletRequest request){

		//根据id删除resume
		resumeService.deleteResumeById(resumeId_Delete);
		//删除之后根据刚才所输入的信息遍历resumeList
		return getResumeListByCondition(searchResumePojo,page,request);
		
	}
	
	@RequestMapping(value="/resumeShare.do",method=RequestMethod.GET)
	public ModelAndView resumeShare(ResumePojo searchResumePojo,SharingCenter sharingCenter,Integer page,HttpServletRequest request){
		
		//User user = GetUser.getUser();
		
		User user = (User)request.getSession().getAttribute("rcuser");
		
		//如果session失效或取不到user 转发到登录
		if (user == null) {
			return new ModelAndView("redirect:/gologin1.action");
		}
		
		else {
			
			sharingCenter.setScId(GetUuid.getuuid32());
			//将从session得到的id set
			sharingCenter.setScUserId(user.getUserId());
			
			//将共享信息插入  返回主键 ，暂时没用
			Integer integer = resumeService.shareResume(sharingCenter);			
			//执行完插入后，要将RC_USER_RESUME表更新		
			resumeService.updateUserResume(sharingCenter.getScResumeId());
			return getResumeListByCondition(searchResumePojo,page,request);	
			
		}	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*      zhang      */
	/**
	 *   @Description   简历新增
	 * 包括简历表新增、关联表新增、以及文件表(如果上传了文件)
	 */ 
	@RequestMapping(value="/resume_add.do",method=RequestMethod.POST)
	@ResponseBody
	public ModelAndView resume_add(@RequestParam(value = "upload_file", required = false) MultipartFile file,Resume resume, HttpServletRequest request, String resumeBirthdayString) {
		Map<String, Object> model = new HashMap<String, Object>();
		try {

			resume.setResumeSelfEvaluation(resume.getResumeSelfEvaluation().replaceAll(" ", ""));
			resume.setResumeWorkExperience(resume.getResumeWorkExperience().replaceAll(" ", ""));

			// resumeId = uuid生成
			String resumeId = getuuid32();
			resume.setResumeId(resumeId);

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");// 小写的mm表示的是分钟
			java.util.Date date_Birthday;
			try {
				date_Birthday = sdf.parse(resumeBirthdayString);
			} catch (Exception e) {
				date_Birthday = new Date();
				e.printStackTrace();
			}

			resume.setResumeBirthday(date_Birthday);
			// request.getSession().getAttribute("username");
			// request.getSession().setAttribute("person", person);
			// 需要从session中取
			resume.setResumeCreateUser("zhang");
			if (checkResumeInFo(resume)) {
				// 插入到resume表中
				int resultCount = resumeService.resumeAdd(resume);

				if (resultCount == 0) {
					// 插入到resume表失败
					model.put("msg", "插入简历信息时出错");
					return new ModelAndView("resume/resume_error", model);
				}

				// 上传文件

				String picId = getuuid32();
				// request.getSession().getAttribute("userId");
				// request.getSession().setAttribute("person", person);
				String piccresteuser = "zhang";

				if (file != null) {
					String path = request.getSession().getServletContext().getRealPath("upload");
					String filepath = iPictureService.upload(file, path);
					if (filepath == null) {
						System.out.println("未上传图片");
					} else if (filepath.equals("error")) {
						System.out.println("上传有问题");
						model.put("msg", "文件上传时出错");
						return new ModelAndView("resume/resume_error", model);
					} else {

						String arr[] = filepath.split("\\.");
						if (arr[1].equals("jpg") || arr[1].equals("png") || arr[1].equals("gif")|| arr[1].equals("jpeg")) {
							int result = resumeService.resumeAddPic(picId, resumeId, piccresteuser, filepath);
							if (result == 0) {
								model.put("msg", "插入简历信息图片插入时出错");
								return new ModelAndView("resume/resume_error", model);
							}
						} else if (arr[1].equals("doc") || arr[1].equals("xls") || arr[1].equals("txt")|| arr[1].equals("docx") || arr[1].equals("xlsx")) {
							int result = resumeService.resumeAddfile(picId, resumeId, piccresteuser, filepath);
							if (result == 0) {
								model.put("msg", "插入简历信息图片插入时出错");
								return new ModelAndView("resume/resume_error", model);
							}

						} else {
							model.put("msg", "文件上传时出错");
							return new ModelAndView("resume/resume_error", model);
						}

					}

				}

				String userResumeId = getuuid32();
				// Session中取用户id
				// String userId = "1b786bc41114f67ae059cea5f1789d";
				String userId = GetUser.getUser().getUserId();
				// request.getSession().getAttribute("userId");
				// 插入到连接表
				int result = resumeService.resumeAddResumeUser(userResumeId, userId, resumeId);
				if (result == 0) {
					// 插入到关联表失败
					model.put("msg", "插入简历信息时出错");
					return new ModelAndView("resume/resume_error", model);
				}

			} else {
				model.put("msg", "输入的简历信息有误");
				return new ModelAndView("resume/resume_error", model);
			}

		} catch (Exception e) {
			// 打印到log里
			e.printStackTrace();
			model.put("msg", "插入简历信息时出错");
			return new ModelAndView("resume/resume_error", model);
		}

		ResumePojo resumePojo = resumeService.getResumeDetailsById(resume.getResumeId());
		if (resumePojo != null) {
			for (int i = 0; i < resumePojo.getlPics().size(); i++) {
				resumePojo.getlPics().get(i).setpPic("https://kbase-1256168134.file.myqcloud.com/" + resumePojo.getlPics().get(i).getpPic());
			}
			for (int i = 0; i < resumePojo.getlFiles().size(); i++) {
				resumePojo.getlFiles().get(i).setfFile(COSUtil.generatePresignedUrl(resumePojo.getlFiles().get(i).getfFile()));
			}
		}
		model.put("resume", resumePojo);
		return new ModelAndView("resume/resumeAddUpdateResult", model);

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
			ResumePojo resumePojo = resumeService.getResumeDetailsById(resume_id);		
			if(resumePojo != null){
				for (int i=0;i<resumePojo.getlPics().size();i++){
					resumePojo.getlPics().get(i).setpPic("https://kbase-1256168134.file.myqcloud.com/" + resumePojo.getlPics().get(i).getpPic());
				}
				for (int i = 0; i < resumePojo.getlFiles().size(); i++) {
					resumePojo.getlFiles().get(i).setfFile(COSUtil.generatePresignedUrl(resumePojo.getlFiles().get(i).getfFile()));;
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
	public ModelAndView resumeUpdate(@RequestParam(value = "upload_file", required = false) MultipartFile file,
			Resume resume, Pic pic, String resumeBirthdayString, String changeway, HttpServletRequest request) {

		Map<String, Object> model = new HashMap<String, Object>();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");// 小写的mm表示的是分钟
		java.util.Date date_Birthday;
		try {
			date_Birthday = sdf.parse(resumeBirthdayString);
		} catch (Exception e) {
			date_Birthday = new Date();
			e.printStackTrace();
		}

		resume.setResumeBirthday(date_Birthday);
		// request.getSession().getAttribute("userName");
		resume.setResumeUpdateUser("zhang");
		resume.setResumeSelfEvaluation(resume.getResumeSelfEvaluation().replaceAll(" ", ""));
		resume.setResumeWorkExperience(resume.getResumeWorkExperience().replaceAll(" ", ""));

		if (checkResumeInFo(resume)) {
			// 更新resume表
			int result = resumeService.resumeUpdate(resume);
			if (result == 0) {
				// 更新resume表失败
				model.put("msg", "更新简历信息时出错");
				return new ModelAndView("resume/resume_error", model);
			}

			if (file != null) {
				String path = request.getSession().getServletContext().getRealPath("upload");
				String filepath = iPictureService.upload(file, path);
				System.out.println(filepath);
				if (filepath == null) {

				} else if (filepath.equals("error")) {
					model.put("msg", "文件上传时出错");
					return new ModelAndView("resume/resume_error", model);
				} else {

					String arr[] = filepath.split("\\.");

					if (arr[1].equals("jpg") || arr[1].equals("png") || arr[1].equals("gif") || arr[1].equals("jpeg")) {

						pic.setpId(getuuid32());
						pic.setpResumeId(resume.getResumeId());
						// request.getSession().getAttribute("userName");
						pic.setpUpdateUser("zhang");
						pic.setpCreateUser("zhang");
						pic.setpPic(filepath);

						if (changeway.equals("替换")) {
							String picId = getuuid32();
							// request.getSession().getAttribute("userName");
							String piccresteuser = "username";

							int deletepicresult = resumeService.deletePicById(resume.getResumeId());

							int picaddresult = resumeService.resumeAddPic(picId, resume.getResumeId(), piccresteuser,
									filepath);
							if (picaddresult == 0) {
								// 更新resume表失败
								model.put("msg", "更新简历信息时出错");
								return new ModelAndView("resume/resume_error", model);
							}

						} else if (changeway.equals("新增")) {

							String picId = getuuid32();
							// request.getSession().getAttribute("userName");
							String piccresteuser = "username";

							int picaddresult = resumeService.resumeAddPic(picId, resume.getResumeId(), piccresteuser,
									filepath);

							if (picaddresult == 0) {
								// 更新resume表失败
								model.put("msg", "更新简历信息时出错");
								return new ModelAndView("resume/resume_error", model);
							}

						} else {
							// 获取文件修改方式失败
							model.put("msg", "更新简历信息时出错");
							return new ModelAndView("resume/resume_error", model);
						}
					} else if (arr[1].equals("doc") || arr[1].equals("xls") || arr[1].equals("txt")|| arr[1].equals("docx") || arr[1].equals("xlsx")) {
						String picId = getuuid32();
						// session 中取
						String piccresteuser = "zhang";
						if (changeway.equals("新增")) {

							int fileaddresult = resumeService.resumeAddfile(picId, resume.getResumeId(), piccresteuser,
									filepath);
							if (fileaddresult == 0) {
								model.put("msg", "更新简历信息时出错");
								return new ModelAndView("resume/resume_error", model);
							}
						} else if (changeway.equals("替换")) {

							int deletefileresult = resumeService.deleteFileById(resume.getResumeId());
							int fileupdateresult = resumeService.resumeAddfile(picId, resume.getResumeId(),piccresteuser, filepath);
							if (fileupdateresult == 0) {
								model.put("msg", "更新简历信息时出错");
								return new ModelAndView("resume/resume_error", model);
							}
						} else {
							model.put("msg", "获取更新方式时出错");
							return new ModelAndView("resume/resume_error", model);
						}

					} else {
						model.put("msg", "文件信息有误");
						return new ModelAndView("resume/resume_error", model);
					}
				}

			}

		} else {
			// 插入数据验证失败
			model.put("msg", "数据输入出错");
			return new ModelAndView("resume/resume_error", model);
		}

		ResumePojo resumePojo = resumeService.getResumeDetailsById(resume.getResumeId());
		if (resumePojo != null) {
			for (int i = 0; i < resumePojo.getlPics().size(); i++) {
				resumePojo.getlPics().get(i).setpPic("https://kbase-1256168134.file.myqcloud.com/" + resumePojo.getlPics().get(i).getpPic());
			}
			for (int i = 0; i < resumePojo.getlFiles().size(); i++) {
				resumePojo.getlFiles().get(i).setfFile(COSUtil.generatePresignedUrl(resumePojo.getlFiles().get(i).getfFile()));;
			}
		}
		model.put("resume", resumePojo);
		return new ModelAndView("resume/resumeAddUpdateResult", model);
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
