package com.qc.rc.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.qc.rc.entity.Interview;
import com.qc.rc.entity.pojo.InterviewPojo;

public interface InterviewMapper {

//	分页查询
	//根据userid 查询面试安排
	ArrayList<InterviewPojo> selectAllInterviewsByUserId(RowBounds rb,@Param("userId")Integer userId); 

}
