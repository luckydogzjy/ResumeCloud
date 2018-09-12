package com.qc.rc.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.qc.rc.common.PageBean;
import com.qc.rc.entity.Interview;
import com.qc.rc.entity.pojo.InterviewPojo;

public interface InterviewService {

	
//	根据userid查找当前用户所有的interview 返回pageBean
	public PageBean<InterviewPojo> getAllInterviews(Integer userId);

//	根据条件和userid查找当前用户所有的interview 返回pageBean
	public PageBean<InterviewPojo> selectByCondition(Integer pageNum,Integer userId,
					Date startTime,Date overTime,String interviewJob,String interviewInfo);

}
