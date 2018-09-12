package com.qc.rc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qc.rc.entity.Resume;
import com.qc.rc.entity.SharingCenter;
import com.qc.rc.entity.UserResume;
import com.qc.rc.entity.pojo.ResumePojo;

public interface ResumeMapper {
	
	//初次加载页面显示全部数据
	List<ResumePojo> getAllResume(@Param("userId")Integer userId);
	//条件查询
	List<ResumePojo> getResumeListByCondition(@Param("userId")Integer userId,@Param("resumeName")String resumeName,@Param("resumeJobIntension")String resumeJobIntension,@Param("resumeSex")Integer resumeSex,
			@Param("resumeEducation")Integer resumeEducation,@Param("resumeWorkYears")Integer resumeWorkYears,@Param("resumeGraduateInstitution")String resumeGraduateInstitution);
	
	//根据id返回resume表的全部详细信息
	ResumePojo getResumeDetailsById(@Param("resumeId")Integer resumeId);
	//根据id删除resume信息
	void deleteResumeById(@Param("resumeId")Integer resumeId);
	//点击共享按钮后将信息插入共享中心,返回主键
	Integer shareResume(SharingCenter sharingCenter);
	
	
	
	/*    zhang   */
	int insertResume(Resume resume);

	int updateResume(Resume resume);

	Resume selectResumeById(Integer resumeId);

	int selectResumeBestId();

	int resumeAddResumeUser(UserResume userresume);
	
	
	
	
}
