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
	public List<ResumePojo> getAllResume(String userId);
	//条件查询返回简历list
	public List<ResumePojo> getResumeListByCondition(String userId,String resumeName,String resumeJobIntension,Integer resumeSex,
			Integer resumeEducation,Integer resumeWorkYears,String resumeGraduateInstitution);
	
	//根据id返回resume表的全部详细信息
	public ResumePojo getResumeDetailsById(String string);
	//根据id删除resume信息
	public void deleteResumeById(Integer resumeId);
	
	//点击共享按钮后将信息插入共享中心,返回主键
	Integer shareResume(SharingCenter sharingCenter);
	
	//执行完插入共享中心的操作后，继续执行更新RC_USER_RESUME表，让其中的共享标志UR_RESUME_SHARE_FLAG设为1
	public void  updateUserResume(Integer resumeId);
	
	//显示共享中心页面，取到全部信息
	public List<SharingCenterPojo> getAllSharingResume();
	
	//显示当前用户所兑换过的简历列表
	public List<DownloadRecord> getDownloadRecordById(String userId);
	
	
	/*   zhang   */
	
	/*
	 * 简历表增加
	 * */
	public int resumeAdd(Resume resume);

	/*
	 * 简历表更新
	 * */
	public int resumeUpdate(Resume resume);

	/*
	 * 简历表信息查询
	 * */
	public Resume resumeUpdateSelect(String resume_id);
	
	/*
	 * 文件表更新
	 * */
	public int resumeUpdatePic(Pic pic);
	
	/*
	 * 简历 用户关联表增加
	 * */
	public int resumeAddResumeUser(String userResumeId, String userId, String resumeId);
	
	/*
	 * 文件表新增
	 * */
	public int resumeAddPic(String picId, String resumeId, String piccresteuser, String fileway);
	
	/*
	 * 文件表新增（更改方式为新增）
	 * */
	public int resumeUpdateAddPic(Pic pic);
	
	
	public int deletePicById(String resumeId);
	
}
