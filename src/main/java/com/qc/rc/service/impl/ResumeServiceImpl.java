package com.qc.rc.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qc.rc.dao.PicMapper;
import com.qc.rc.dao.ResumeMapper;
import com.qc.rc.dao.UserResumeMapper;
import com.qc.rc.entity.Pic;
import com.qc.rc.entity.Resume;
import com.qc.rc.entity.SharingCenter;
import com.qc.rc.entity.pojo.ResumePojo;
import com.qc.rc.service.ResumeService;


@Service("ResumeService")
public class ResumeServiceImpl implements ResumeService {
	
	@Autowired
	private ResumeMapper resumeMapper;
	@Autowired
	private UserResumeMapper userresumeMapper;
	@Autowired
	private PicMapper picMapper;
	
	private static Integer pageShow = 5;
	
	@Override
	public Map<String, Object> getResumeListByCondition(String userId, ResumePojo resumePojo, Integer page) {
		//引入分页查询，使用PageHelper分页功能
        //在查询之前传入当前页，然后多少记录
		PageHelper.startPage(page,pageShow);	
		List<ResumePojo> list = resumeMapper.getResumeListByCondition(userId, resumePojo);
		
		PageInfo<ResumePojo> pageInfo = new PageInfo<ResumePojo>(list);
		
		Map<String,Object> model = new HashMap<String,Object>(); 
		model.put("resumeList", list);
		model.put("page", pageInfo);
		//返回查询条件
		model.put("search", resumePojo);
		
		return model;
	}
	//根据id查详情
	public ResumePojo getResumeDetailsById(String resumeId) {
	
		return resumeMapper.getResumeDetailsById(resumeId);	

	}
	//根据id删resume
	public void deleteResumeById(String resumeId){
		
		resumeMapper.deleteResumeById(resumeId);
	}	
	//点击共享按钮后将信息插入共享中心
	public Integer shareResume(SharingCenter sharingCenter){
		
		return resumeMapper.shareResume(sharingCenter);
	}
	//插入共享中心后更新user 和 resume的关联表
	public void  updateUserResume(String resumeId){
		
		resumeMapper.updateUserResume(resumeId);
	}
	
	
	
	
	
	
	
	/*    zhang   */
	/*
	 * 简历表新增
	 * */
	public int resumeAdd(Resume resume) {	
		Integer resultcount = resumeMapper.insertResume(resume);
		return resultcount;	
	}

	/*
	 * 简历表更新
	 * */
	public int resumeUpdate(Resume resume) {	
		int resultcount = resumeMapper.updateResume(resume);
		return resultcount;
	}

	/*
	 * 简历表信息查询
	 * */
	public Resume getResumeById(String resumeId) {	
		Resume resume = resumeMapper.selectResumeById(resumeId);
		return resume;			
	}


	/*
	 * 文件表更新
	 * */
	public int resumeUpdatePic(Pic pic) {
		int result = picMapper.resumeUpdatePic(pic);
		return result;
	}


	/*
	 * 简历 用户关联表增加
	 * */
	public int resumeAddResumeUser(String userResumeId, String userId, String resumeId) {
		int result = userresumeMapper.resumeAddResumeUser(userResumeId,userId,resumeId);
		return result;
	}

	/*
	 * 文件表新增
	 * */
	public int resumeAddPic(String picId, String resumeId, String piccresteuser, String fileway) {	
		
		int result = picMapper.resumeAddPic(picId,resumeId,piccresteuser,fileway);
		
		return result;
	}

	/*
	 * 文件表新增（更改方式为新增）
	 * */
	public int resumeUpdateAddPic(Pic pic) {
		int result = picMapper.resumeUpdateAddPic(pic);	
		return result;
	}

	
	
	public int deletePicById(String resumeId) {
		int result = picMapper.deletePicById(resumeId);	
		return result;
	}

	public int resumeAddfile(String fileId, String resumeId, String filecresteuser, String filepath) {
		int result = picMapper.resumeAddFile(fileId,resumeId,filecresteuser,filepath);	
		return result;
	}
	
	public int deleteFileById(String resumeId) {
		int result = picMapper.deleteFileById(resumeId);	
		return result;
	}
	

}
