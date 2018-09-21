package com.qc.rc.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qc.rc.entity.DownloadRecord;
import com.qc.rc.entity.Pic;
import com.qc.rc.entity.Resume;
import com.qc.rc.entity.SharingCenter;
import com.qc.rc.entity.UserResume;
import com.qc.rc.entity.pojo.ResumePojo;
import com.qc.rc.entity.pojo.SharingCenterPojo;

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
	
	//执行完插入共享中心的操作后，继续执行更新RC_USER_RESUME表，让其中的共享标志UR_RESUME_SHARE_FLAG设为1
	public void  updateUserResume(Integer resumeId);
	
	//显示共享中心页面，取到全部信息
	public List<SharingCenterPojo> getAllSharingResume();
	
	//显示当前用户所兑换过的简历列表
	public List<DownloadRecord> getDownloadRecordById(Integer userId);
	
	
	/*   zhang   */
	public int resumeAdd(Resume resume);

	public int resumeUpdate(Resume resume);

	public Resume resumeUpdateSelect(Integer resume_id);

	public int selectResumeBestId();
	
	public int resumeUpdatePic(Pic pic);
	
	public int resumeAddResumeUser(int userResumeId, int userId, int resumeId);
	
	public int resumeAddPic(int picId, int resumeId, String piccresteuser, String fileway);
	
}
