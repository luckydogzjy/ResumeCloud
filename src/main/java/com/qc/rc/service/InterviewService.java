package com.qc.rc.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.qc.rc.common.PageBean;
import com.qc.rc.common.ServerResponse;
import com.qc.rc.entity.Interview;
import com.qc.rc.entity.pojo.InterviewPojo;

public interface InterviewService {

	
//	根据userid查找当前用户所有的interview 返回pageBean
	public PageBean<InterviewPojo> getAllInterviews(Integer userId);

//	根据条件和userid查找当前用户所有的interview 返回pageBean
	public PageBean<InterviewPojo> selectByCondition(Integer pageNum,Integer userId,
					Date startTime,Date overTime,String interviewJob,String interviewInfo);
//	添加面试
	public Integer addInterview(InterviewPojo interviewPojo);
//	删除面试
	public void deleteInterview(InterviewPojo interviewPojo);
	
	//Liu
	//根据ResumeId查询详细信息
	public ServerResponse<InterviewPojo> getInterviewByResumeId(Integer ResumeId);
		
	//根据ResumeId更新面试信息
	public ServerResponse updateInterviewsByResumeId(InterviewPojo interviewPojo,Integer ResumeId,String resumePhone);
		
}
