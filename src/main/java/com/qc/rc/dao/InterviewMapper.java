package com.qc.rc.dao;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.qc.rc.entity.Interview;
import com.qc.rc.entity.pojo.InterviewPojo;
import com.qc.rc.entity.pojo.ResumeInterviews;

public interface InterviewMapper {
	
//	根据多个条件查询所有面试信息返回数量
	Integer selectCount(@Param("userId")String userId,@Param("startTime") Date startTime,
			@Param("overTime")Date overTime, @Param("interviewJob")String interviewJob, @Param("interviewInfo")String interviewInfo,@Param("sort")Integer sort); 

	//根据条件和userid查找当前用户所有的interview 分页
	List<InterviewPojo> selectByCondition(@Param("userId")String userId, @Param("startTime")Date startTime,
			@Param("overTime")Date overTime, @Param("interviewJob")String interviewJob, @Param("interviewInfo")String interviewInfo,@Param("sort")Integer sort,@Param("status") Integer status);

	//插入interviewPojo	
	Integer addInterview(InterviewPojo interviewPojo);

	//删除
	Integer deleteInterview(InterviewPojo interviewPojo);
	
	//根据ResumeId查询简历
	InterviewPojo selectInterviewsByResumeId(@Param("ResumeId")Integer ResumeId);
	
	//根据ResumeId查询简历
	int updateInterviewsByResumeId(InterviewPojo interviewPojo);
	
	//根据ResumeId查询所有简历
	ResumeInterviews getResumeInterviewsByRId(@Param("resumeId")String resumeId);
	
	//更新面试记录
	Integer updateInteviewRecodeInfo(@Param("interviewRecodeInfo")String interviewRecodeInfo,@Param("interviewId")String interviewId);



	//Liu
	//根据ResumeId查询面试安排
	InterviewPojo selectInterviewsByInterviewId(@Param("InterviewId")String InterviewId);
	
	//根据ResumeId更新面试安排
	int updateInterviewsByInterviewId(InterviewPojo interviewPojo);
	
	//根据userId查询所有的面试安排
	List<InterviewPojo> selectInterviewsByUserId(String userId);

}
