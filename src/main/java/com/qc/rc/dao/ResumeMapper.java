package com.qc.rc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qc.rc.entity.Resume;
import com.qc.rc.entity.pojo.ResumePojo;

public interface ResumeMapper {
	
	//初次加载页面显示全部数据
	List<ResumePojo> getAllResume();
	//条件查询
	List<ResumePojo> getResumeListByCondition(@Param("resumeName")String resumeName,@Param("resumeJobIntension")String resumeJobIntension,@Param("resumeSex")Integer resumeSex,
			@Param("resumeEducation")Integer resumeEducation,@Param("resumeWorkYears")Integer resumeWorkYears,@Param("resumeGraduateInstitution")String resumeGraduateInstitution);
	
	//根据id返回resume表的全部详细信息
	Resume getResumeDetailsById(Integer resumeId);
}
