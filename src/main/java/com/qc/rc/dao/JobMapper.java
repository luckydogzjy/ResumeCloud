package com.qc.rc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qc.rc.entity.Job;

public interface JobMapper {
	
	/**
	 * 检索所有job 
	 * @return
	 */
	public List<Job>  jobGetAll(Integer userId);
	/**
	 * 添加一条job信息 
	 * @param job
	 * @return
	 */
	public int jobAdd(Job job);
	/**
	 * 更新一条job信息 
	 * @param job
	 * @return
	 */
	public int jobUpdate(Job job);
	/**
	 * 删除一条job信息
	 * @param jobId
	 * @return
	 */
	public int jobDelete(Integer jobId);
	/**
	 * 更改职位招聘状态
	 * @param jobId
	 * @param jobStatus
	 * @return
	 */
	public int jobChangeStatus(@Param("jobId")Integer jobId,@Param("jobStatus")Integer jobStatus);
	/**
	 * 模糊查询
	 * @param name
	 * @return
	 */
	public List<Job> jobGetByName(@Param("userId")Integer userId,@Param("name")String name);
	/**
	 * 查看详情
	 * @param jobId
	 * @return
	 */
	public Job jobGetOne(Integer jobId);
	
	
	//以下都是实验版，不提交
	public int insertJob(Job job);
}
