package com.qc.rc.controller;

import java.io.UnsupportedEncodingException;import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageInfo;
import com.qc.rc.common.FormParameterUtil;
import com.qc.rc.common.GetUser;
import com.qc.rc.common.GetUuid;
import com.qc.rc.common.ServerResponse;
import com.qc.rc.entity.Resume;
import com.qc.rc.entity.User;
import com.qc.rc.entity.pojo.InterviewPojo;
import com.qc.rc.entity.pojo.ResumeInterviews;
import com.qc.rc.entity.pojoView.InterviewPojoView;
import com.qc.rc.service.InterviewService;
import com.qc.rc.service.PersonalService;
import com.qc.rc.service.ResumeService;
import com.qc.rc.utils.DateUtil;


@Controller
@RequestMapping("Interview/")
public class InterviewController {
	private static Logger logger = Logger.getLogger(InterviewController.class);
	@Autowired  
	private InterviewService iInterviewService;
	
	@Autowired 
	private ResumeService resumeService;
	
	@Autowired
	private PersonalService personalService;
	/**
	 * 查询相关面试安排
	 * @param pageNum
	 * @param interviewInfo
	 * @param interviewJob
	 * @param startTime
	 * @param overTime
	 * @param sort 按面试时间排序
	 * @param status 面试状态
	 */
	@RequestMapping("selectByCondition.do")
	public ModelAndView selectByCondition(String pageNum,String interviewInfo,
			String interviewJob,String startTime,String overTime,String sort,String status,HttpServletRequest request,HttpSession session){
		Map<String,Object> model = new HashMap<String,Object>();
		try {
			User user = (User)request.getSession().getAttribute("rcuser");
			//如果session失效或取不到user 转发到登录
			if (user == null) {
				return new ModelAndView("redirect:/gologin1.action");
			}
			Integer pn = 1;
			Integer st = -1;
			Integer sta = 0;
			
			if (StringUtils.isNotBlank(pageNum)) {
				pn =  Integer.valueOf(pageNum);
			}
			if (StringUtils.isNotBlank(sort)) {
				st = Integer.valueOf(sort);
			}
			if (StringUtils.isNotBlank(interviewJob)) {
				interviewJob = FormParameterUtil.changeCode(interviewJob);
			}
			if(StringUtils.isNotBlank(interviewInfo)){
				interviewInfo = FormParameterUtil.changeCode(interviewInfo);
			}
			if(StringUtils.isBlank(status)){
				logger.debug("未接收到面试结果筛选");
			}
			
			if (StringUtils.isNotBlank(status)) {
				sta = Integer.valueOf(status);
			}
			
			PageInfo<InterviewPojo> pageInfo = iInterviewService.selectByCondition(pn, user.getUserId(), startTime, overTime,
					 interviewJob, interviewInfo, st,sta);
			model.put("pageInfo", pageInfo);
			model.put("startTime", startTime);
			model.put("interviewJob", interviewJob);
			model.put("overTime", overTime);
			model.put("interviewInfo", interviewInfo);
			model.put("sort", st);
			model.put("status", sta);
			return new ModelAndView("interviewJsps/showAllInterviews",model);

		} catch (ParseException e) {
			logger.debug("日期类型不匹配");
			model.put("message", "日期类型不匹配");
		}catch (NullPointerException e) {
			logger.debug("参数为空");
			model.put("message", "参数为空");
		} catch (UnsupportedEncodingException e) {
			logger.debug("不支持的字符");
			model.put("message", "不支持的字符");
		} catch (NumberFormatException e) {
			logger.debug("不支持的字符类型转整型");
			model.put("message", "不支持的字符类型转整型");
		}
		return new ModelAndView("interviewJsps/error",model);	
	}
	
	
	/**
	 * 页面跳转
	 * @param resumeId
	 * @return 跳转 有resumeId添加面试页面	/ 无resumeId添加面试页面
	 */
	@RequestMapping("addNewInterview.do")
	public ModelAndView addNewInterview(String resumeId,HttpServletRequest request){
		Map<String,Object> model = new HashMap<String,Object>();
		try {
			User user = (User)request.getSession().getAttribute("rcuser");
			//如果session失效或取不到user 转发到登录
			if (user == null) {
				return new ModelAndView("redirect:/gologin1.action");
			}
			String company = personalService.getUserInfo(user.getUserId()).get(0).getUserCompany();
			if (StringUtils.isNotBlank(resumeId)) {
				Resume resume =  resumeService.getResumeById(resumeId);
				model.put("resume", resume);
				model.put("company", company);
				return new ModelAndView("interviewJsps/addnewinterview",model);
	
			}else {

//				没有resumeId 转到添加临时添加页面
				model.put("company", company);
				return new ModelAndView("interviewJsps/addnewTempinterview",model);
			}
		} catch (NumberFormatException e) {
			logger.debug("不支持的字符类型转整型");
			model.put("message", "不支持的字符类型转整型");
		}
		return new ModelAndView("interviewJsps/error",model);
		
		

	}
	
	/**
	 * 为简历库中存在的简历安排面试
	 * @param resumeName
	 * @param resumePhone
	 * @param interviewJob
	 * @param interviewAddress
	 * @param interviewAssociateUsername
	 * @param interviewAssociatePhone
	 * @param interviewInfo
	 * @param interviewTime
	 * @param interviewMessage 短信内容
	 * @param ism 是否同步发送短信
	 * @return 
	 */
	@RequestMapping("newInterview.do")
	public ModelAndView newInterview(String resumeId,String resumePhone ,String interviewJob,
			String interviewAddress,String interviewAssociateUsername,
			String interviewAssociatePhone,String interviewInfo,String interviewTime,
			String interviewMessage,String ism,HttpServletRequest request){
		
		Map<String,Object> model = new HashMap<String,Object>();
		try {
//			临时设置user  应从缓存中得到数据
			User user =	GetUser.getUser();


			String errorMsg = "";
			if(StringUtils.isBlank(resumeId)){
				logger.debug("resumeId为空");
				errorMsg += "resumeId为空";
			}
			if (StringUtils.isBlank(resumePhone)) {
				logger.debug("resumePhone为空");
				errorMsg += "resumePhone为空";
			
			}
			if (StringUtils.isBlank(interviewJob)) {
				logger.debug("interviewJob为空");
				errorMsg += "interviewJob为空";
			
			}
			if (StringUtils.isBlank(interviewAddress)) {
				logger.debug("interviewAddress为空");
				errorMsg += "interviewAddress为空";
			
			}
			if (StringUtils.isBlank(interviewAssociateUsername)) {
				logger.debug("interviewAssociateUsername为空");
				errorMsg += "interviewAssociateUsername为空";

			}
			if (StringUtils.isBlank(interviewAssociatePhone)) {
				logger.debug("interviewAssociatePhone为空");
				errorMsg += "interviewAssociatePhone为空";
		
			}
			if (StringUtils.isBlank(interviewTime)) {
				logger.debug("interviewTime为空");
				errorMsg += "interviewTime为空";
			}
			if (!"".equals(errorMsg)) {
				model.put("message",errorMsg);
				return new ModelAndView("interviewJsps/error",model);
			}
//			从前台获取id查询resume
			Resume resume = resumeService.getResumeById(resumeId);
			String interviewId = GetUuid.getuuid32();
			Integer result = iInterviewService.addInterview(interviewId,resumeId, FormParameterUtil.changeCode(interviewJob), 
					FormParameterUtil.changeCode(interviewTime), FormParameterUtil.changeCode(interviewAssociateUsername), FormParameterUtil.changeCode(interviewAssociatePhone),FormParameterUtil.changeCode(interviewAddress) , FormParameterUtil.changeCode(interviewInfo), resume, user);
			if(result==0){
				model.put("message","添加面试失败");
				return new ModelAndView("interviewJsps/error",model);
			}else {

				if(!resumePhone.equals(resume.getResumePhone())){
//					更新简历库中resume的phone
					resume.setResumePhone(resumePhone);
					Integer res = resumeService.resumeUpdate(resume);
					if(res!=0){
						model.put("message","已更新该简历联系方式,面试添加成功");
						if("sendMessage".equals(ism)){
//							发送短信interviewMessage
							interviewMessage = FormParameterUtil.changeCode(request.getParameter("interviewMessage"));
							logger.debug("发送短信到"+resumePhone);
							logger.debug(interviewMessage);
						}
//						转发到详情页面
						return new ModelAndView("redirect:/Interview/showInterviewDetail.do?InterviewId="+interviewId);
					}else {
						model.put("resume", resume);
						model.put("message","更新手机号失败");
						return new ModelAndView("interviewJsps/error",model);
					}
				}else {
					model.put("resume", resume);
					if("sendMessage".equals(ism)){
//						发送短信interviewMessage
						interviewMessage = FormParameterUtil.changeCode(request.getParameter("interviewMessage"));
						logger.debug("发送短信到"+resumePhone);
						logger.debug(interviewMessage);
					}
//					转发到详情页面
					return new ModelAndView("redirect:/Interview/showInterviewDetail.do?InterviewId="+interviewId);
				}
			}
		}catch (UnsupportedEncodingException e) {
			logger.debug("不支持的字符");
			model.put("message", "不支持的字符");
		} catch (NumberFormatException e) {
			logger.debug("不支持的字符类型转整型");
			model.put("message", "不支持的字符类型转整型");
		} catch (ParseException e) {
			logger.debug("不支持的字符类型转日期类型");
			model.put("message", "不支持的字符类型转整型");
		}
		return new ModelAndView("interviewJsps/error",model);

	}
	
	/**
	 * 为简历库中不存在的简历安排面试
	 * @param resumeName
	 * @param resumePhone
	 * @param interviewJob
	 * @param interviewAddress
	 * @param interviewAssociateUsername
	 * @param interviewAssociatePhone
	 * @param interviewInfo
	 * @param interviewTime
	 * @param interviewMessage 短信内容
	 * @param ism 是否同步发送短信
	 * @return 
	 */
	@RequestMapping("newResumeInterview.do")
	public ModelAndView newResumeInterview(String resumeName,String resumePhone ,String interviewJob,
			String interviewAddress,String interviewAssociateUsername,
			String interviewAssociatePhone,String interviewInfo,String interviewTime,
			String interviewMessage,String ism,HttpServletRequest request){
		Map<String,Object> model = new HashMap<String,Object>();
		try {
			User user = (User)request.getSession().getAttribute("rcuser");
			//如果session失效或取不到user 转发到登录
			if (user == null) {
				return new ModelAndView("redirect:/gologin1.action");
			}
			
			String errorMsg = "";

			if (StringUtils.isBlank(resumeName)) {
				logger.debug("resumeName为空");
				errorMsg += "resumeName为空";
			
			}
			if (StringUtils.isBlank(resumePhone)) {
				logger.debug("resumePhone为空");
				errorMsg += "resumePhone为空";
			
			}
			if (StringUtils.isBlank(interviewJob)) {
				logger.debug("interviewJob为空");
				errorMsg += "interviewJob为空";
			
			}
			if (StringUtils.isBlank(interviewAddress)) {
				logger.debug("interviewAddress为空");
				errorMsg += "interviewAddress为空";
			
			}
			if (StringUtils.isBlank(interviewAssociateUsername)) {
				logger.debug("interviewAssociateUsername为空");
				errorMsg += "interviewAssociateUsername为空";

			}
			if (StringUtils.isBlank(interviewAssociatePhone)) {
				logger.debug("interviewAssociatePhone为空");
				errorMsg += "interviewAssociatePhone为空";
		
			}
			if (StringUtils.isBlank(interviewTime)) {
				logger.debug("interviewTime为空");
				errorMsg += "interviewTime为空";
			}
			if (!"".equals(errorMsg)) {
				model.put("message",errorMsg);
				return new ModelAndView("interviewJsps/error",model);
			}
			
			
//			封装数据成resume 在数据库中插入返回resumeid
			Resume resume = new Resume();
			resume.setResumeId(GetUuid.getuuid32());
			resume.setResumeName(FormParameterUtil.changeCode(resumeName));
			resume.setResumePhone(FormParameterUtil.changeCode(resumePhone));
			resume.setResumeJobIntension(FormParameterUtil.changeCode(interviewJob));
			resume.setResumeCreateUser(user.getUserName());
		
			int resultCount = resumeService.resumeAddfromInterview(resume);
		
//			创建UserResume对象	插入数据库
			if(resultCount != 0){
				
				int result = resumeService.resumeAddResumeUser(GetUuid.getuuid32(), user.getUserId(), resume.getResumeId());
				if(result !=0){
					String interviewId = GetUuid.getuuid32();
					result = iInterviewService.addInterview(interviewId,resume.getResumeId(), FormParameterUtil.changeCode(interviewJob), 
							FormParameterUtil.changeCode(interviewTime), FormParameterUtil.changeCode(interviewAssociateUsername),
							FormParameterUtil.changeCode(interviewAssociatePhone)
							,FormParameterUtil.changeCode(interviewAddress) , FormParameterUtil.changeCode(interviewInfo), resume, user);
					if(result==0){
						model.put("message","添加面试失败");
						return new ModelAndView("interviewJsps/error",model);
					}else {

						if(!resumePhone.equals(resume.getResumePhone())){
//							更新简历库中resume的phone
							resume.setResumePhone(resumePhone);
							Integer res = resumeService.resumeUpdate(resume);
							if(res!=0){
								model.put("message","已更新该简历联系方式,面试添加成功");
								if("sendMessage".equals(ism)){
//									发送短信interviewMessage
									interviewMessage = FormParameterUtil.changeCode(interviewMessage);
									logger.debug("发送短信到"+resumePhone);
									logger.debug(interviewMessage);
								}
//								转发到详情页面
								return new ModelAndView("redirect:/Interview/showInterviewDetail.do?InterviewId="+interviewId);
							}else {
								model.put("resume", resume);
								model.put("message","更新手机号失败");
								return new ModelAndView("interviewJsps/error",model);
							}
						}else {
							model.put("resume", resume);
							model.put("message","添加成功");
							if("sendMessage".equals(ism)){
//								发送短信interviewMessage
								interviewMessage = FormParameterUtil.changeCode(request.getParameter("interviewMessage"));
								
								logger.debug("发送短信到"+resumePhone);
								logger.debug(interviewMessage);
							}

//							转发到详情页面
							return new ModelAndView("redirect:/Interview/showInterviewDetail.do?InterviewId="+interviewId);
						}
					}
				}else {
					model.put("message", "新增用户简历关联失败");
				}
			}else {
				model.put("message", "新增简历失败");
			}
			return new ModelAndView("interviewJsps/error",model);
		} catch (UnsupportedEncodingException e) {
			logger.debug("不支持的字符");
			model.put("message", "不支持的字符");
		} catch (NumberFormatException e) {
			logger.debug("不支持的字符类型转整型");
			model.put("message", "不支持的字符类型转整型");
		} catch (ParseException e) {
			logger.debug("不支持的字符类型转日期类型");
			model.put("message", "不支持的字符类型转整型");
		}
		return new ModelAndView("interviewJsps/error",model);
	}
	
	/**
	 *	删除面试安排
	 *@param id:面试id
	 *@return 1成功,-1或其他失败
	 */
	@RequestMapping("deleteById.do")
	@ResponseBody
	public Integer deleteById(String id,HttpServletRequest request){
		User user = (User)request.getSession().getAttribute("rcuser");
		//如果session失效或取不到user 转发到登录
		if (user == null) {
			return -2;
		}
		Integer msg = -1;
		if (StringUtils.isBlank(id)) {
			return msg;
		}
		msg = iInterviewService.deleteInterview(id);
		return msg;
		
	}
	
	/**
	 *	跳转面试记录页面
	 *@param resumeId:简历id
	 *@return 
	 */
	@RequestMapping("resumeInterviews.do")
	public ModelAndView getResumeInterviewsByRId(String resumeId,HttpServletRequest request){
		
		Map<String,Object> model = new HashMap<String,Object>();
		try {
			User user = (User)request.getSession().getAttribute("rcuser");
			//如果session失效或取不到user 转发到登录
			if (user == null) {
				return new ModelAndView("redirect:/gologin1.action");
			}
			if(StringUtils.isBlank(resumeId)){
				model.put("message", "resumeId为空");
				return new ModelAndView("interviewJsps/error",model);
			}else {
				ResumeInterviews resumeInterviews = iInterviewService.getResumeInterviewsByRId(resumeId);
				if(StringUtils.isBlank(resumeInterviews.getResumeName())){
					model.put("message", "不存在的resumeId");
					return new ModelAndView("interviewJsps/error",model);
				}
				model.put("resumeInterviews", resumeInterviews);
				return new ModelAndView("interviewJsps/RISdetail",model);	
			}
			
		}catch (NumberFormatException e) {
			logger.debug("不支持的字符类型转整型");
			model.put("message", "不支持的字符类型转整型");
		}
		return new ModelAndView("interviewJsps/error",model);
	}
	
	/**
	 *	更新面试记录 
	 *@param interviewId:面试id
	 *@param recodeInfo:面试记录
	 *@return -2session失效,1成功,-1或其他失败
	 */
	@RequestMapping("updateRecodeInfo.do") 
	@ResponseBody
	public Integer updateRecodeInfo(String interviewId,String recodeInfo,HttpServletRequest request){ 
		User user = (User)request.getSession().getAttribute("rcuser");
		//如果session失效或取不到user 转发到登录
		if (user == null) {
			return -2;
		}
		Integer msg = -1;
		if (StringUtils.isNotBlank(interviewId)) { 
			msg = iInterviewService.updateInteviewRecodeInfo(recodeInfo, interviewId);	
		}
		return msg;
		
	}
	
	
	/**
	 * 显示简历详情
	 * @param ResumeId
	 * @return
	 */
	@RequestMapping("showInterviewDetail.do")
	public ModelAndView getInterviewByResumeId(String InterviewId,HttpServletRequest request){
		User user = (User)request.getSession().getAttribute("rcuser");
		//如果session失效或取不到user 转发到登录
		if (user == null) {
			return new ModelAndView("redirect:/gologin1.action");
		}
		Map<String,Object> model = new HashMap<String,Object>();
		ServerResponse<InterviewPojo> interviewPojo = iInterviewService.getInterviewByInterviewId(InterviewId);
		model.put("interviewPojo", interviewPojo.getData());
		return new ModelAndView("interviewJsps/IVxiangqing",model);	
	}
	
	/**
	 * 更新页显示面试安排详情
	 * @param ResumeId
	 * @return
	 */
	@RequestMapping(value="showInterviewDetailUpdate.do",method = RequestMethod.GET)
	public ModelAndView getInterviewByResumeIdUpdate(String InterviewId,HttpServletRequest request){
		User user = (User)request.getSession().getAttribute("rcuser");
		//如果session失效或取不到user 转发到登录
		if (user == null) {
			return new ModelAndView("redirect:/gologin1.action");
		}
		Map<String,Object> model = new HashMap<String,Object>();
		ServerResponse<InterviewPojo> interviewPojo = iInterviewService.getInterviewByInterviewId(InterviewId);
		model.put("interviewPojo", interviewPojo.getData());
		return new ModelAndView("interviewJsps/IVxiugai",model);	
	}
	
	/**
	 * 更新面试安排
	 * @param interviewPojo
	 * @param StringinterviewTime
	 * @param ResumeId
	 * @param resumePhone
	 * @return
	 */
	@RequestMapping(value="updateInterviewDetail.do",method = RequestMethod.POST)
	public ModelAndView updateInterviewsByResumeId(InterviewPojo interviewPojo,String StringinterviewTime,String ResumeId,String resumePhone,HttpServletRequest request){
		
		Map<String,Object> model = new HashMap<String,Object>();
		try{
			User user = (User)request.getSession().getAttribute("rcuser");
			//如果session失效或取不到user 转发到登录
			if (user == null) {
				return new ModelAndView("redirect:/gologin1.action");
			}
			//判断简历手机号空
			if(StringUtils.isBlank(resumePhone)){
				logger.info("ResumePhone为空");
			}
			//判断面试工作空
			if(StringUtils.isBlank(interviewPojo.getInterviewJob())){
				logger.info("InterviewJob为空");
			}
			//判断面试时间空
			if(StringUtils.isBlank(StringinterviewTime)){
				logger.info("StringinterviewTime为空");
			}else{
				//把前端传回来的时间，转换成Date类型
				Date interviewTime = DateUtil.parseStrToDate(StringinterviewTime, "yyyy-MM-dd HH:mm:ss");
				interviewPojo.setInterviewTime(interviewTime);
			}
			if(StringUtils.isBlank(interviewPojo.getInterviewAddress())){
				logger.info("InterviewAddress为空");
			}
			if(StringUtils.isBlank(interviewPojo.getInterviewAssociatePhone())){
				logger.info("InterviewAssociatePhone为空");
			}
			if(StringUtils.isBlank(interviewPojo.getInterviewAssociateUsername())){
				logger.info("InterviewAssociateUsername为空");
			}
			if(StringUtils.isBlank(interviewPojo.getInterviewInfo())){
				logger.info("interviewInfo为空");
			}
			ServerResponse result = iInterviewService.updateInterviewsByInterviewId(interviewPojo,ResumeId,resumePhone);
			model.put("info",result.getMsg());
			ServerResponse<InterviewPojo> interviewPojoDetail = iInterviewService.getInterviewByInterviewId(interviewPojo.getInterviewId());
			model.put("interviewPojo", interviewPojoDetail.getData());
		}catch (NumberFormatException e) {
			logger.error("不支持的字符类型转整型");
		}
		return new ModelAndView("interviewJsps/IVxiangqing",model);
	}
	
	/**
	 * 获取所有的面试安排
	 * @return
	 */
	@RequestMapping(value="selectAllInterviews.do",method = RequestMethod.GET)
	@ResponseBody
	public List<InterviewPojoView> selectAllInterviews(HttpSession session){
		
		User user = (User) session.getAttribute("rcuser");
		
		ServerResponse<List<InterviewPojoView>> InterviewPojoViews = iInterviewService.selectAllInterviews(user.getUserId());
		
		return InterviewPojoViews.getData();
		
	}
	
	/**
	 * 跳转日程页面
	 * @return
	 */
	@RequestMapping(value="richengPage.do",method = RequestMethod.GET)
	public ModelAndView richengPage(HttpServletRequest request){
		User user = (User)request.getSession().getAttribute("rcuser");
		//如果session失效或取不到user 转发到登录
		if (user == null) {
			return new ModelAndView("redirect:/gologin1.action");
		}
		return new ModelAndView("interviewJsps/IVricheng");
	}
}
