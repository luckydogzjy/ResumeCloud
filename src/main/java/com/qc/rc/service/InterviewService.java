package com.qc.rc.service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageInfo;
import com.qc.rc.common.ServerResponse;
import com.qc.rc.entity.Resume;
import com.qc.rc.entity.User;
import com.qc.rc.entity.pojo.InterviewPojo;
import com.qc.rc.entity.pojoView.InterviewPojoView;

public interface InterviewService {

	
	//Liu
	//根据ResumeId查询详细信息
	public ServerResponse<InterviewPojo> getInterviewByInterviewId(String InterviewId);
		
	//根据ResumeId更新面试信息
	public ServerResponse updateInterviewsByInterviewId(InterviewPojo interviewPojo,String ResumeId,String resumePhone);
	
	//根据userId查询所有的面试安排
	public ServerResponse<List<InterviewPojoView>> selectAllInterviews(String userId);
	
}
