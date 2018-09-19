package com.qc.rc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qc.rc.entity.DownloadRecord;
import com.qc.rc.entity.pojo.SharingCenterPojo;

public interface SharingCenterMapper {
	
	//条件查询共享中心信息
	public List<SharingCenterPojo> getSharingResumeListByCondition(@Param("resumeJobIntension")String resumeJobIntension,@Param("resumeWorkYears")Integer resumeWorkYears,@Param("resumeSex")Integer resumeSex,
			@Param("resumeEducation")Integer resumeEducation,@Param("resumeGraduateInstitution")String resumeGraduateInstitution);
	//显示当前用户所兑换过的简历列表
	List<DownloadRecord> getDownloadRecordById(@Param("userId")Integer userId);
}
