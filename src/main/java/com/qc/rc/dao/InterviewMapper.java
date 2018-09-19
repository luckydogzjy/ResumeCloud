package com.qc.rc.dao;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.qc.rc.entity.Interview;
import com.qc.rc.entity.pojo.InterviewPojo;

public interface InterviewMapper {

//	根据userid查询所有面试信息返回数量
	Integer getAllInterviewsByUserId(@Param("userId")Integer userId); 
	
//	根据多个条件查询所有面试信息返回数量
	Integer getAllInterviewsByCondition(@Param("userId")Integer userId,@Param("startTime") Date startTime,
			@Param("overTime")Date overTime, @Param("interviewJob")String interviewJob, @Param("interviewInfo")String interviewInfo); 
	
	//根据userid 查询面试安排 分页
	List<InterviewPojo> selectAllInterviewsByUserId(RowBounds rb,@Param("userId")Integer userId); 
	
	//根据条件和userid查找当前用户所有的interview 分页
	List<InterviewPojo> selectByCondition(RowBounds rb,@Param("userId")Integer userId, @Param("startTime")Date startTime,
			@Param("overTime")Date overTime, @Param("interviewJob")String interviewJob, @Param("interviewInfo")String interviewInfo);

	//插入interviewPojo	
	Integer addInterview(InterviewPojo interviewPojo);

	//删除
	void deleteInterview(InterviewPojo interviewPojo);
	
	//Liu
	//根据ResumeId查询简历
	InterviewPojo selectInterviewsByResumeId(@Param("ResumeId")Integer ResumeId);
	
	//根据ResumeId更新简历
	int updateInterviewsByResumeId(InterviewPojo interviewPojo);
}
