package com.qc.rc.service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.PageInfo;
import com.qc.rc.common.ServerResponse;
import com.qc.rc.entity.Resume;
import com.qc.rc.entity.User;
import com.qc.rc.entity.pojo.InterviewPojo;
import com.qc.rc.entity.pojo.ResumeInterviews;
import com.qc.rc.entity.pojoView.InterviewPojoView;

public interface InterviewService {

//	根据条件和userid查找当前用户所有的interview
	public PageInfo<InterviewPojo> selectByCondition(Integer pageNum, String userId, String startTime,
			String overTime, String interviewJob, String interviewInfo,Integer sort,Integer status) throws ParseException;
//	添加面试
	public Integer addInterview(String interviewResumeId,String interviewJob,String interviewTime,
			String interviewAssociateUsername,String interviewAssociatePhone,String interviewAddress,String interviewInfo,
			Resume resume,User user) throws ParseException;
//	删除面试
	public Integer deleteInterview(String interviewId);
	
	//根据ResumeId查询详细信息
	public ServerResponse<InterviewPojo> getInterviewByResumeId(Integer ResumeId);
	
	//根据ResumeId查询所有面试信息
	public ResumeInterviews getResumeInterviewsByRId(String resumeId);
	//更新面试记录
	public Integer updateInteviewRecodeInfo(String interviewRecodeInfo,String interviewId);


	//Liu
	//根据InterviewId查询详细信息
	public ServerResponse<InterviewPojo> getInterviewByInterviewId(String InterviewId);
			
	//根据InterviewId更新面试信息
	public ServerResponse updateInterviewsByInterviewId(InterviewPojo interviewPojo,String ResumeId,String resumePhone);
	
	//根据userId查询所有的面试安排
	public ServerResponse<List<InterviewPojoView>> selectAllInterviews(String userId);
	
}
