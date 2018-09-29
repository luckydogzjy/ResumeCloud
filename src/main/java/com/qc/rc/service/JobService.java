package com.qc.rc.service;

import java.util.List;
import java.util.Map;

import com.qc.rc.entity.Job;

public interface JobService {
	/**
	 * 添加一个job、
	 * @param job
	 * @return
	 */
	public boolean jobAdd(Job job);
	/**
	 * 更新一条job
	 * @param job
	 * @return
	 */
	public boolean jobUpdate(Job job);
	/**
	 * 根据jobId删除一条job
	 * @param jobId
	 * @return
	 */
	public boolean jobDelete(Integer jobId);
	/**
	 * 变更状态
	 * @param jobId
	 * @param jobStatus
	 * @return
	 */
	public boolean jobChangeStatus(Integer jobId,Integer jobStatus);
	/**
	 * 模糊查询
	 * @param userId
	 * @param jobName
	 * @return
	 */
	public Map<String, Object> jobGetByName(String userId,String jobName,Integer page);
	/**
	 * 查看职位详情
	 * @param jobId
	 * @return
	 */
	public Job jobGetOne(Integer jobId);
	
	
	
	List<Job> jobGetByName(String userId, String jobName);
	
}
