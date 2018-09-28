package com.qc.rc.service;

import java.util.Map;

import com.qc.rc.entity.Pic;
import com.qc.rc.entity.Resume;
import com.qc.rc.entity.SharingCenter;
import com.qc.rc.entity.pojo.ResumePojo;

public interface ResumeService {
	
	//条件查询返回简历list
	public Map<String, Object> getResumeListByCondition(String userId,ResumePojo resumePojo,Integer page);
	
	//根据id返回resume表的全部详细信息
	public Map<String, Object> getResumeDetailsById(String resumeId);
	
	//根据id删除resume信息
	public void deleteResumeById(String resumeId);
	
	//点击共享按钮后将信息插入共享中心,返回主键
	Integer shareResume(SharingCenter sharingCenter);
	
	//执行完插入共享中心的操作后，继续执行更新RC_USER_RESUME表，让其中的共享标志UR_RESUME_SHARE_FLAG设为1
	public void  updateUserResume(String resumeId);
	
	//ling
	public int resumeAddfromInterview(Resume resume);
	
	
	
	/*   zhang   */
	
	/**
	 * 简历表增加
	 * @param resume
	 * @return
	 */
	public int resumeAdd(Resume resume);

	/**
	 * 简历表更新
	 * @param resume
	 * @return
	 */
	public int resumeUpdate(Resume resume);

	/**
	 * 简历表信息查询
	 * @param resume_id
	 * @return
	 */
	public Resume getResumeById(String resume_id);

	/**
	 * 文件表更新
	 * @param pic
	 * @return
	 */
	public int resumeUpdatePic(Pic pic);

	/**
	 * 简历 用户关联表增加
	 * @param userResumeId
	 * @param userId
	 * @param resumeId
	 * @return
	 */
	public int resumeAddResumeUser(String userResumeId, String userId, String resumeId);
	

	/**
	 * 文件表新增
	 * @param picId
	 * @param resumeId
	 * @param piccresteuser
	 * @param fileway
	 * @return
	 */
	public int resumeAddPic(String picId, String resumeId, String piccresteuser, String fileway);
	

	/**
	 * 文件表新增（更改方式为新增）
	 * @param pic
	 * @return
	 */
	public int resumeUpdateAddPic(Pic pic);
	
	
}
