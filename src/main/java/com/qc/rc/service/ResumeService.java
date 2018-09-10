package com.qc.rc.service;

import java.util.List;

import com.qc.rc.entity.Resume;
import com.qc.rc.entity.pojo.ResumePojo;

public interface ResumeService {
	//返回全部简历
	public List<ResumePojo> getAllResume(Integer userId);
	//条件查询返回简历list
	public List<ResumePojo> getResumeListByCondition(Integer userId,String resumeName,String resumeJobIntension,Integer resumeSex,
			Integer resumeEducation,Integer resumeWorkYears,String resumeGraduateInstitution);
}
