package com.qc.rc.service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import com.github.pagehelper.PageInfo;
import com.qc.rc.common.ServerResponse;
import com.qc.rc.entity.Resume;
import com.qc.rc.entity.pojo.InterviewPojo;

public interface InterviewService {

//	根据条件和userid查找当前用户所有的interview
	public PageInfo<InterviewPojo> selectByCondition(Integer pageNum,Integer userId,
		String startTime,String overTime,String interviewJob,String interviewInfo,Integer sort)throws ParseException;
//	添加面试
	public Integer addInterview(InterviewPojo iPojo,Resume resume,Integer userId);
//	删除面试
	public Integer deleteInterview(Integer interviewId);
	
	//根据ResumeId查询详细信息
	public ServerResponse<InterviewPojo> getInterviewByResumeId(Integer ResumeId);
}
