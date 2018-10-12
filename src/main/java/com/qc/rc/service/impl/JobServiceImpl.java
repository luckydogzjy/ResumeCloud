package com.qc.rc.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qc.rc.common.Const;
import com.qc.rc.common.PageMessage;
import com.qc.rc.dao.JobMapper;
import com.qc.rc.entity.Job;
import com.qc.rc.service.JobService;


@Service
public class JobServiceImpl implements JobService {
	
	Logger logging = Logger.getLogger(JobServiceImpl.class);
	
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
	public boolean jobDelete(String jobId) {
		
		return JobMapper.jobDelete(jobId)==1?true:false;
	}

	@Override
	public boolean jobChangeStatus(String jobId, Integer jobStatus) {
		
		int ok=0;
		
		switch (jobStatus) {
		//当前为开启状态
		case Const.OPEN:
			ok=JobMapper.jobChangeStatus(jobId,Const.CLOSE);
			break;
		//当前为关闭状态
		case Const.CLOSE:
			ok=JobMapper.jobChangeStatus(jobId,Const.OPEN);
			break;
		default:
			logging.error("职位状态异常");
			break;
		}
		
		return ok==1?true:false;	
	}

	@Override
	public Map<String, Object> jobGetByName(String userId,String jobName,Integer page) {
		
		for (Job j : JobMapper.jobGetByName(userId,jobName)) {
			if (new Date().compareTo(j.getJOB_END_TIME())>Const.CLOSE) {
				JobMapper.jobChangeStatus(j.getJOB_ID(), Const.CLOSE);
			}
		}
		Map<String, Object> map = new HashMap<>();
		PageHelper.startPage(page, PageMessage.JOBPAGESIZE);
		List<Job> newjob = JobMapper.jobGetByName(userId,jobName);	
		
		PageInfo<Job> pageJob = new PageInfo<Job>(newjob);	
		map.put("job", newjob);
		map.put("page", pageJob);
		
		return  map;
	}

	@Override
	public Job jobGetOne(String jobId) {
		
		return JobMapper.jobGetOne(jobId);
	}

	@Override
	public boolean jobStatusOpen(String jobId, Date jobEndTime) {
		
		return JobMapper.jobStatusOpen(jobId, jobEndTime)==1?true:false;
		
	}

	
}
