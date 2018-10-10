package com.qc.rc.service.impl;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import com.alibaba.fastjson.serializer.UUIDCodec;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qc.rc.common.Const;
import com.qc.rc.common.GetUuid;
import com.qc.rc.common.PageMessage;
import com.qc.rc.common.ServerResponse;
import com.qc.rc.common.Util;
import com.qc.rc.dao.InterviewMapper;
import com.qc.rc.dao.ResumeMapper;
import com.qc.rc.entity.Resume;
import com.qc.rc.entity.User;
import com.qc.rc.entity.pojo.InterviewPojo;
import com.qc.rc.entity.pojo.ResumeInterviews;
import com.qc.rc.entity.pojoView.InterviewPojoView;
import com.qc.rc.service.InterviewService;
import com.qc.rc.utils.DateUtil;
import com.qc.rc.utils.InterviewDateUtil;

@Service("iInterviewService")
public class InterviewServiceImpl implements InterviewService {

	@Autowired
	private InterviewMapper interviewMapper;
	
	@Autowired
	private ResumeMapper resumeMapper;
	 /**
     * @param pageNum 页码
     * @param userId 用户id
     * @param startTime 起始时间
     * @param overTime 终止时间
     * @param interviewJob 面试职位
     * @param interviewInfo 姓名，备注信息，面试地址
     * @param sort 时间升降排序
     * 
     */
	public PageInfo<InterviewPojo> selectByCondition(Integer pageNum, String userId, String startTime,
			String overTime, String interviewJob, String interviewInfo,Integer sort,Integer status) throws ParseException {
		Date st = null;
		Date ot = null;

		if (StringUtils.isNotBlank(startTime)) {
			st = InterviewDateUtil.strToDate(startTime);
		}
		if (StringUtils.isNotBlank(overTime)) {
			ot  = InterviewDateUtil.strToDate(overTime);
		}
		
		
		 /**
	     * 包装Page对象
	     *
	     * @param list          page结果
	     * @param navigatePages 页码数量
	     */
		PageHelper.startPage(pageNum,PageMessage.interviewpageSize);
		
		List<InterviewPojo> interviewPojos = interviewMapper.selectByCondition(userId, st, ot, interviewJob, interviewInfo,sort,status);

		PageInfo<InterviewPojo> pageResumePojo = new PageInfo<InterviewPojo>(interviewPojos,5);
		
		return pageResumePojo;
		
	}
	
	public Integer addInterview(String interviewResumeId,String interviewJob,String interviewTime,
			String interviewAssociateUsername,String interviewAssociatePhone,String interviewAddress,String interviewInfo,
			Resume resume,User user) throws ParseException {
		InterviewPojo iPojo = new InterviewPojo();
		iPojo.setInterviewId(GetUuid.getuuid32());
		iPojo.setInterviewResumeId(interviewResumeId);
		iPojo.setInterviewJob(interviewJob);
		iPojo.setInterviewTime(InterviewDateUtil.strToDate(interviewTime));
		iPojo.setInterviewAssociateUsername(interviewAssociateUsername);
		iPojo.setInterviewAssociatePhone(interviewAssociatePhone);
		iPojo.setInterviewAddress(interviewAddress);
		iPojo.setInterviewInfo(interviewInfo);
		iPojo.setResume(resume);
		iPojo.setInterviewCreateTime(new Date());
//		新建时设置状态为待面试
		iPojo.setInterviewStatus(2);
//		设置deleteflag为0
		iPojo.setInterviewDeleteFlag(0);
		iPojo.setInterviewUserId(user.getUserId());
		iPojo.setInterviewCreateUser(user.getUserName());
		return	interviewMapper.addInterview(iPojo);
	}

	public Integer deleteInterview(String interviewId){
		InterviewPojo iPojo = new InterviewPojo();
		iPojo.setInterviewId(interviewId);
		iPojo.setInterviewUpdateUser("LING");
		return interviewMapper.deleteInterview(iPojo);
	}
	


	public ServerResponse<InterviewPojo> getInterviewByResumeId(Integer ResumeId) {
		
		InterviewPojo interviewPojo = interviewMapper.selectInterviewsByResumeId(ResumeId);
		
		if(interviewPojo==null){
			return ServerResponse.createByErrorMessage("参数错误");
		}
		return ServerResponse.createBySuccess(interviewPojo);
	}


	public ResumeInterviews getResumeInterviewsByRId(String ResumeId) {
		ResumeInterviews resumeInterviews= interviewMapper.getResumeInterviewsByRId(ResumeId);
		return resumeInterviews;
	}


	public Integer updateInteviewRecodeInfo(String interviewRecodeInfo, String interviewId) {

		return interviewMapper.updateInteviewRecodeInfo(interviewRecodeInfo, interviewId);
	}




	//Liu
	//通过InterviewId获取简历详情
	public ServerResponse<InterviewPojo> getInterviewByInterviewId(String InterviewId) {
		
		InterviewPojo interviewPojo = interviewMapper.selectInterviewsByInterviewId(InterviewId);
		
		if(interviewPojo==null){
			return ServerResponse.createByErrorMessage("参数错误");
		}
		return ServerResponse.createBySuccess(interviewPojo);

	}	
	
	//更新简历
	public ServerResponse updateInterviewsByInterviewId(InterviewPojo interviewPojo,String ResumeId,String resumePhone){
		
		int InterviewResultCount = interviewMapper.updateInterviewsByInterviewId(interviewPojo);
		
		int ResumeResultCount = resumeMapper.updateResumePhone(ResumeId,resumePhone);
		
		if(InterviewResultCount>0&&ResumeResultCount>0){
			return ServerResponse.createBySuccess("更新成功");
		}else{
			return ServerResponse.createByErrorMessage("更新失败");
		}	

	}
	
	//查询所有简历
	@Override
	public ServerResponse<List<InterviewPojoView>> selectAllInterviews(String userId) {
		
		List<InterviewPojo> InterviewPojos  = interviewMapper.selectInterviewsByUserId(userId);
		
		List<InterviewPojoView> InterviewPojoViews = new ArrayList<InterviewPojoView>();
		
		for(InterviewPojo item : InterviewPojos){
			InterviewPojoView interviewPojoView = new InterviewPojoView();
			interviewPojoView.setId(item.getInterviewId());
			interviewPojoView.setTitle(item.getResume().getResumeName()+" "+item.getInterviewJob()+" "+DateUtil.parseDateToStr(item.getInterviewTime(),DateUtil.DATE_TIME_FORMAT_YYYY_MM_DD_HH_MI));
			interviewPojoView.setStart(DateUtil.parseDateToStr(item.getInterviewTime(),DateUtil.DATE_TIME_FORMAT_YYYY_MM_DD_HH_MI));
			
			//结束时间加1小时
			Date date =  DateUtil.addHourOfDate(item.getInterviewTime(), Const.ADD_HOUR);
			interviewPojoView.setEnd(DateUtil.parseDateToStr(date,DateUtil.DATE_TIME_FORMAT_YYYY_MM_DD_HH_MI));
			
			interviewPojoView.setAllDay(Const.FALSE);
			
			InterviewPojoViews.add(interviewPojoView);
		}
		return ServerResponse.createBySuccess(InterviewPojoViews);	
	}

}
