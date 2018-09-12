package com.qc.rc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qc.rc.dao.JobMapper;
import com.qc.rc.entity.Job;
import com.qc.rc.service.JobService;

@Service
public class JobServiceImpl implements JobService {
	@Autowired
	private JobMapper JobMapper;
	
	@Override
	public List<Job> jobGetAll(Integer userId) {
		// TODO Auto-generated method stub
		
		return JobMapper.jobGetAll(userId);
	}

	@Override
	public boolean jobAdd(Job job) {
		// TODO Auto-generated method stub
	
		return JobMapper.jobAdd(job)==1?true:false;
	}

	@Override
	public boolean jobUpdate(Job job) {
		// TODO Auto-generated method stub
		
		return JobMapper.jobUpdate(job)==1?true:false;
	}

	@Override
	public boolean jobDelete(Integer jobId) {
		// TODO Auto-generated method stub
		
		return JobMapper.jobDelete(jobId)==1?true:false;
	}

	@Override
	public boolean jobChangeStatus(Integer jobId, Integer jobStatus) {
		// TODO Auto-generated method stub
		
		int ok=-1;
		
		switch (jobStatus) {
		case 1:
			ok=JobMapper.jobChangeStatus(jobId,0);
			break;
		case 0:
			ok=JobMapper.jobChangeStatus(jobId,1);
			break;
		default:
			break;
		}
		
		return ok==1?true:false;	
	}

	@Override
	public List<Job> jobGetByName(Integer userId,String jobName) {
		// TODO Auto-generated method stub
		
		return JobMapper.jobGetByName(userId,jobName);
	}

	@Override
	public Job jobGetOne(Integer jobId) {
		// TODO Auto-generated method stub
		
		return JobMapper.jobGetOne(jobId);
	}
	
}
