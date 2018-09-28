package com.qc.rc.dao;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.qc.rc.entity.Interview;
import com.qc.rc.entity.pojo.InterviewPojo;

public interface InterviewMapper {

	
	//Liu
	//根据ResumeId查询面试安排
	InterviewPojo selectInterviewsByInterviewId(@Param("InterviewId")String InterviewId);
	
	//根据ResumeId更新面试安排
	int updateInterviewsByInterviewId(InterviewPojo interviewPojo);
	
	//根据userId查询所有的面试安排
	List<InterviewPojo> selectInterviewsByUserId(String userId);
}
