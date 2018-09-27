package com.qc.rc.service;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.github.pagehelper.PageInfo;
import com.qc.rc.common.ServerResponse;
import com.qc.rc.entity.Interview;
import com.qc.rc.entity.Resume;
import com.qc.rc.entity.User;
import com.qc.rc.entity.pojo.InterviewPojo;

public interface InterviewService {

//	根据条件和userid查找当前用户所有的interview
	public PageInfo<InterviewPojo> selectByCondition(Integer pageNum, Integer userId, String startTime,
			String overTime, String interviewJob, String interviewInfo,Integer sort,Integer status) throws ParseException;
//	添加面试
	public Integer addInterview(Integer interviewResumeId,String interviewJob,String interviewTime,
			String interviewAssociateUsername,String interviewAssociatePhone,String interviewAddress,String interviewInfo,
			Resume resume,User user) throws ParseException;
//	删除面试
	public Integer deleteInterview(Integer interviewId);
	
	//根据ResumeId查询详细信息
	public ServerResponse<InterviewPojo> getInterviewByResumeId(Integer ResumeId);
}
