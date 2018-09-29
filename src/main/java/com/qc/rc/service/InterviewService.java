package com.qc.rc.service;


import java.util.List;
import com.qc.rc.common.ServerResponse;
import com.qc.rc.entity.pojo.InterviewPojo;
import com.qc.rc.entity.pojoView.InterviewPojoView;

public interface InterviewService {

	
	//Liu
	//根据InterviewId查询详细信息
	public ServerResponse<InterviewPojo> getInterviewByInterviewId(String InterviewId);
		
	//根据InterviewId更新面试信息
	public ServerResponse updateInterviewsByInterviewId(InterviewPojo interviewPojo,String ResumeId,String resumePhone);
	
	//根据userId查询所有的面试安排
	public ServerResponse<List<InterviewPojoView>> selectAllInterviews(String userId);
	
}
