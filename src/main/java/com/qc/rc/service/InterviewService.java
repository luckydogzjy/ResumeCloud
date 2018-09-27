package com.qc.rc.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.qc.rc.common.PageBean;
import com.qc.rc.common.ServerResponse;
import com.qc.rc.entity.Interview;
import com.qc.rc.entity.pojo.InterviewPojo;
import com.qc.rc.entity.pojoView.InterviewPojoView;

public interface InterviewService {
	
	//Liu
	//根据ResumeId查询详细信息
	public ServerResponse<InterviewPojo> getInterviewByResumeId(Integer ResumeId);
		
	//根据ResumeId更新面试信息
	public ServerResponse updateInterviewsByResumeId(InterviewPojo interviewPojo,Integer ResumeId,String resumePhone);
	
	//根据userId查询所有的面试安排
	public ServerResponse<List<InterviewPojoView>> selectAllInterviews(Integer userId);
		
}
