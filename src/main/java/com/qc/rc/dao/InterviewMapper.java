package com.qc.rc.dao;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.qc.rc.entity.Interview;
import com.qc.rc.entity.pojo.InterviewPojo;

public interface InterviewMapper {
	


//	根据多个条件查询所有面试信息返回数量
	Integer selectCount(@Param("userId")Integer userId,@Param("startTime") Date startTime,
			@Param("overTime")Date overTime, @Param("interviewJob")String interviewJob, @Param("interviewInfo")String interviewInfo,@Param("sort")Integer sort); 

	//根据条件和userid查找当前用户所有的interview 分页
	List<InterviewPojo> selectByCondition(@Param("userId")Integer userId, @Param("startTime")Date startTime,
			@Param("overTime")Date overTime, @Param("interviewJob")String interviewJob, @Param("interviewInfo")String interviewInfo,@Param("sort")Integer sort,@Param("status") Integer status);

	//插入interviewPojo	
	Integer addInterview(InterviewPojo interviewPojo);

	//删除
	Integer deleteInterview(InterviewPojo interviewPojo);
	
	//Liu
	//根据ResumeId查询面试安排
	InterviewPojo selectInterviewsByResumeId(@Param("ResumeId")Integer ResumeId);
	
	//根据ResumeId更新面试安排
	int updateInterviewsByResumeId(InterviewPojo interviewPojo);
	
	//根据userId查询所有的面试安排
	List<InterviewPojo> selectInterviewsByUserId(Integer userId);
}
