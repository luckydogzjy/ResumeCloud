package com.qc.rc.service.impl;

import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.qc.rc.common.Const;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.qc.rc.common.PageMessage;
import com.qc.rc.common.ServerResponse;
import com.qc.rc.dao.InterviewMapper;
import com.qc.rc.dao.ResumeMapper;
import com.qc.rc.entity.Resume;
import com.qc.rc.entity.User;
import com.qc.rc.entity.pojo.InterviewPojo;
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



	public ServerResponse<InterviewPojo> getInterviewByInterviewId(String InterviewId) {
		
		InterviewPojo interviewPojo = interviewMapper.selectInterviewsByInterviewId(InterviewId);
		
		if(interviewPojo==null){
			return ServerResponse.createByErrorMessage("参数错误");
		}
		return ServerResponse.createBySuccess(interviewPojo);

	}	
	
	public ServerResponse updateInterviewsByInterviewId(InterviewPojo interviewPojo,String ResumeId,String resumePhone){
		
		int InterviewResultCount = interviewMapper.updateInterviewsByInterviewId(interviewPojo);
		
		int ResumeResultCount = resumeMapper.updateResumePhone(ResumeId,resumePhone);
		
		if(InterviewResultCount>0&&ResumeResultCount>0){
			return ServerResponse.createBySuccess("更新成功");
		}else{
			return ServerResponse.createByErrorMessage("更新失败");
		}	

	}

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

