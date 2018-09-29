package com.qc.rc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qc.rc.entity.Resume;
import com.qc.rc.entity.SharingCenter;
import com.qc.rc.entity.pojo.ResumePojo;

public interface ResumeMapper {
	
	//条件查询
	List<ResumePojo> getResumeListByCondition(@Param("userId")String userId,@Param("resumePojo")ResumePojo resumePojo);
	
	//根据id返回resume表的全部详细信息
	ResumePojo getResumeDetailsById(@Param("resumeId")String resumeId);
	
	//根据id删除resume信息
	void deleteResumeById(@Param("resumeId")String resumeId);
	
	//点击共享按钮后将信息插入共享中心,返回主键
	Integer shareResume(SharingCenter sharingCenter);
	
	//执行完插入共享中心的操作后，继续执行更新RC_USER_RESUME表，让其中的共享标志UR_RESUME_SHARE_FLAG设为1
	void  updateUserResume(@Param("resumeId")String resumeId);
	
	//ling
	int addResume(Resume resume);



	/*    zhang   */
	int insertResume(Resume resume);

	int updateResume(Resume resume);

	Resume selectResumeById(String resumeId);
	
}
