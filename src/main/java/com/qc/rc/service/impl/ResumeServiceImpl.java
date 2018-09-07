package com.qc.rc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qc.rc.dao.ResumeMapper;
import com.qc.rc.entity.Resume;
import com.qc.rc.service.ResumeService;


@Service("ResumeService")
public class ResumeServiceImpl implements ResumeService {
	
	@Autowired
	private ResumeMapper resumeMapper;
	
	
	public List<Resume> getAllResume() {
		
		List<Resume> list = resumeMapper.getAllResume();
		
		System.out.println("ResumeServiceImpl:::" + list.size());
		
		return list;
	}

}
