package com.qc.rc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qc.rc.entity.DownloadRecord;
import com.qc.rc.entity.Pic;
import com.qc.rc.entity.Resume;
import com.qc.rc.entity.SharingCenter;
import com.qc.rc.entity.UserResume;
import com.qc.rc.entity.pojo.ResumePojo;
import com.qc.rc.entity.pojo.SharingCenterPojo;

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
	//执行完插入共享中心的操作后，继续执行更新RC_USER_RESUME表，让其中的共享标志UR_RESUME_SHARE_FLAG设为1
	void  updateUserResume(@Param("resumeId")Integer resumeId);
	//显示共享中心页面，取到全部信息
	List<SharingCenterPojo> getAllSharingResume();
	//显示当前用户所兑换过的简历列表
	List<DownloadRecord> getDownloadRecordById(@Param("userId")Integer userId);
	
	
	
	/*    zhang   */
	int insertResume(Resume resume);

	int updateResume(Resume resume);

	Resume selectResumeById(Integer resumeId);

	
	//liu
	int updateResumePhone(@Param("resumeId")Integer resumeId,@Param("resumePhone")String resumePhone);
}
