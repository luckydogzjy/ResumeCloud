package com.qc.rc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qc.rc.dao.ResumeMapper;
import com.qc.rc.entity.SharingCenter;
import com.qc.rc.entity.pojo.ResumePojo;
import com.qc.rc.service.ResumeService;


@Service("ResumeService")
public class ResumeServiceImpl implements ResumeService {
	
	@Autowired
	private ResumeMapper resumeMapper;
	
	@Autowired
	private ResumePojo resumePojo;
	
	
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
}
