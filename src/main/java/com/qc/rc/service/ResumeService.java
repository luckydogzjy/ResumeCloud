package com.qc.rc.service;

import java.util.Map;

import com.qc.rc.entity.Pic;
import com.qc.rc.entity.Resume;
import com.qc.rc.entity.SharingCenter;
import com.qc.rc.entity.pojo.ResumePojo;

public interface ResumeService {
	
	//条件查询返回简历list
	public Map<String, Object> getResumeListByCondition(Integer userId,ResumePojo resumePojo,Integer page);
	
	//根据id返回resume表的全部详细信息
	public Map<String, Object> getResumeDetailsById(Integer resumeId);
	
	//根据id删除resume信息
	public void deleteResumeById(Integer resumeId);
	
	//点击共享按钮后将信息插入共享中心,返回主键
	Integer shareResume(SharingCenter sharingCenter);
	
	//执行完插入共享中心的操作后，继续执行更新RC_USER_RESUME表，让其中的共享标志UR_RESUME_SHARE_FLAG设为1
	public void  updateUserResume(Integer resumeId);
	
	
	
	
	/*   zhang   */
	public int resumeAdd(Resume resume);

	public int resumeUpdate(Resume resume);

	public Resume resumeUpdateSelect(Integer resume_id);
	
	public int resumeUpdatePic(Pic pic);
	
	public int resumeAddResumeUser(int userResumeId, int userId, int resumeId);
	
	public int resumeAddPic(int picId, int resumeId, String piccresteuser, String fileway);
	
	
}
