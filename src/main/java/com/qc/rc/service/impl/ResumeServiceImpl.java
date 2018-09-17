package com.qc.rc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qc.rc.dao.ResumeMapper;
import com.qc.rc.entity.DownloadRecord;
import com.qc.rc.entity.Pic;
import com.qc.rc.entity.Resume;
import com.qc.rc.entity.SharingCenter;
import com.qc.rc.entity.UserResume;
import com.qc.rc.entity.pojo.ResumePojo;
import com.qc.rc.entity.pojo.SharingCenterPojo;
import com.qc.rc.service.ResumeService;


@Service("ResumeService")
public class ResumeServiceImpl implements ResumeService {
	
	@Autowired
	private ResumeMapper resumeMapper;
	
	
	private ResumePojo resumePojo;
	
	
	private Resume resume;
	
	
	public List<ResumePojo> getAllResume(Integer userId) {
		
		List<ResumePojo> list = resumeMapper.getAllResume(userId);
		
		System.out.println("ResumeServiceImpl:::" + list.size());
		
		return list;
	}


	
	public List<ResumePojo> getResumeListByCondition(Integer userId,String resumeName, String resumeJobIntension, Integer resumeSex,
			Integer resumeEducation, Integer resumeWorkYears, String resumeGraduateInstitution) {
		
		List<ResumePojo> list = resumeMapper.getResumeListByCondition(userId,resumeName, resumeJobIntension, resumeSex, resumeEducation, resumeWorkYears, resumeGraduateInstitution);
		
		System.out.println("ResumeServiceImpl->getResumeListByCondition :::" + list.size());
		
		return list;
	}
	
	public ResumePojo getResumeDetailsById(Integer resumeId){
		
		resumePojo = resumeMapper.getResumeDetailsById(resumeId);
		
		return resumePojo;
		
	}
	
	public void deleteResumeById(Integer resumeId){
		
		resumeMapper.deleteResumeById(resumeId);
	}
	
	//点击共享按钮后将信息插入共享中心
	public Integer shareResume(SharingCenter sharingCenter){
		
		return resumeMapper.shareResume(sharingCenter);
	}
	public void  updateUserResume(Integer resumeId){
		
		resumeMapper.updateUserResume(resumeId);
	}
	//显示共享中心页面，取到全部信息
	public List<SharingCenterPojo> getAllSharingResume(){
		
		return resumeMapper.getAllSharingResume();
	}
	//显示当前用户所兑换过的简历列表
	public List<DownloadRecord> getDownloadRecordById(Integer userId){
		
		return resumeMapper.getDownloadRecordById(userId);
	}
	
	
/*    zhang   */
	
	public int resumeAdd(Resume resume) {
		
		Integer resultcount = resumeMapper.insertResume(resume);
		return resultcount;
		
	}

	
	public int resumeUpdate(Resume resume) {
		
		int resultcount = resumeMapper.updateResume(resume);
		return resultcount;
	}

	
	public Resume resumeUpdateSelect(Integer resumeId) {
		
		resume = resumeMapper.selectResumeById(resumeId);
		return resume;
		
		
	}




	public int selectResumeBestId() {
		int result = resumeMapper.selectResumeBestId();
		return result;
	}



	public int resumeAddResumeUser(UserResume userresume) {
		int result = resumeMapper.resumeAddResumeUser(userresume);
		return result;
	}



	@Override
	public int resumeAddPic(Pic pic) {
		int result = resumeMapper.resumeAddPic(pic);
		return result;
	}



	@Override
	public int resumeUpdatePic(Pic pic) {
		int result = resumeMapper.resumeUpdatePic(pic);
		return result;
	}
	
	
	
	
	
}
