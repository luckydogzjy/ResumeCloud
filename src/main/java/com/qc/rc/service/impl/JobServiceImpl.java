package com.qc.rc.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qc.rc.dao.JobMapper;
import com.qc.rc.entity.Job;
import com.qc.rc.service.JobService;

@Service
public class JobServiceImpl implements JobService {
	@Autowired
	private JobMapper JobMapper;
	
	@Override
	public boolean jobAdd(Job job) {
		
		return JobMapper.jobAdd(job)==1?true:false;
	}

	@Override
	public boolean jobUpdate(Job job) {
		
		return JobMapper.jobUpdate(job)==1?true:false;
	}

	@Override
	public boolean jobDelete(Integer jobId) {
		
		return JobMapper.jobDelete(jobId)==1?true:false;
	}

	@Override
	public boolean jobChangeStatus(Integer jobId, Integer jobStatus) {
		
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
	public Map<String, Object> jobGetByName(Integer userId,String jobName,Integer page) {
		
		for (Job j : JobMapper.jobGetByName(userId,jobName)) {
			if (new Date().compareTo(j.getJOB_END_TIME())>0) {
				JobMapper.jobChangeStatus(j.getJOB_ID(), 0);
			}
		}
		Map<String, Object> map = new HashMap<>();
		PageHelper.startPage(page, 6);
		List<Job> newjob = JobMapper.jobGetByName(userId,jobName);			
		PageInfo<Job> pageJob = new PageInfo<Job>(newjob);	
		map.put("job", newjob);
		map.put("page", pageJob);
		
		
		return  map;
	}

	@Override
	public Job jobGetOne(Integer jobId) {
		
		return JobMapper.jobGetOne(jobId);
	}

	@Override
	public boolean jobStatusOpen(Integer jobId, Date jobEndTime) {
		
		return JobMapper.jobStatusOpen(jobId, jobEndTime)==1?true:false;
		
	}

	
}
