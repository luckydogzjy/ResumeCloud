package com.qc.rc.service;

import java.util.List;

import com.qc.rc.entity.Job;

public interface JobService {
	/**
	 * @author qiancheng
	 * 检索所有job
	 * @return
	 */
	public List<Job> jobGetAll(Integer userId);
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
	 * @param jobName
	 * @return
	 */
	public List<Job> jobGetByName(Integer userId,String jobName);
	/**
	 * 查看职位详情
	 * @param jobId
	 * @return
	 */
	public Job jobGetOne(Integer jobId);
}
