package com.qc.rc.service;

import java.util.Map;

public interface SharingCenterService {
	
	//条件查询共享中心信息
	public Map<String, Object> getSharingResumeListByCondition(String resumeJobIntension,Integer resumeWorkYears,Integer resumeSex,
			Integer resumeEducation,String resumeGraduateInstitution,Integer page);
	
}
