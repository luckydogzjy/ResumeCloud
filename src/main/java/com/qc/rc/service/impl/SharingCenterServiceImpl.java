package com.qc.rc.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qc.rc.dao.SharingCenterMapper;
import com.qc.rc.entity.DownloadRecord;
import com.qc.rc.entity.pojo.SharingCenterPojo;
import com.qc.rc.service.SharingCenterService;

@Service("SharingCenterService")
public class SharingCenterServiceImpl implements SharingCenterService {
	
	@Autowired
	private SharingCenterMapper sharingCenterMapper;
	
	private static Integer pageShow = 5;
		
	@Override
	public Map<String, Object> getSharingResumeListByCondition(String resumeJobIntension, Integer resumeWorkYears,
			Integer resumeSex, Integer resumeEducation, String resumeGraduateInstitution,Integer page) {
		
		//引入分页查询，使用PageHelper分页功能
        //在查询之前传入当前页，然后多少记录
		PageHelper.startPage(page,pageShow);
		List<SharingCenterPojo> list = sharingCenterMapper.getSharingResumeListByCondition(resumeJobIntension, resumeWorkYears, resumeSex, resumeEducation, resumeGraduateInstitution);
		//取到符合条件信息后，取当前用户所兑换过的简历列表
		List<DownloadRecord> downloadRecords = sharingCenterMapper.getDownloadRecordById(1);
		PageInfo<SharingCenterPojo> pageInfo = new PageInfo<SharingCenterPojo>(list);
		
		for(int i = 0; i < downloadRecords.size(); i++){
			for(int j = 0; j < list.size(); j++){
				if (downloadRecords.get(i).getDrSharingCenterId() == list.get(j).getScId()) {
					//将当前用户兑换过的简历信息，在list里将标志位赋值为1，意思为当前用户已经兑换过，
					//在前台显示 已兑换 按钮
					
					list.get(j).setFlag(1);
				}
			}
		}
		
		Map<String,Object> model = new HashMap<String,Object>(); 
		model.put("sharingList", list);
		model.put("page", pageInfo);
		
		model.put("resumeJobIntension", resumeJobIntension);
		model.put("resumeWorkYears", resumeWorkYears);
		model.put("resumeSex", resumeSex);
		model.put("resumeEducation", resumeEducation);
		model.put("resumeGraduateInstitution", resumeGraduateInstitution);
		
		return model;
	}

}
