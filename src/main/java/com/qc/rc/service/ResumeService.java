package com.qc.rc.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qc.rc.entity.Resume;
import com.qc.rc.entity.SharingCenter;
import com.qc.rc.entity.pojo.ResumePojo;

public interface ResumeService {
	//返回全部简历
	public List<ResumePojo> getAllResume(Integer userId);
	//条件查询返回简历list
	public List<ResumePojo> getResumeListByCondition(Integer userId,String resumeName,String resumeJobIntension,Integer resumeSex,
			Integer resumeEducation,Integer resumeWorkYears,String resumeGraduateInstitution);
	
	//根据id返回resume表的全部详细信息
	public ResumePojo getResumeDetailsById(Integer resumeId);
	//根据id删除resume信息
	public void deleteResumeById(Integer resumeId);
	
	//点击共享按钮后将信息插入共享中心,返回主键
	Integer shareResume(SharingCenter sharingCenter);
}
