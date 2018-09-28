package com.qc.rc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qc.rc.entity.pojo.DownloadRecordPojo;

public interface DownloadRecordMapper {
	
	//插入数据
	public Integer insertDownloadRecord(@Param("id")String id,@Param("userId")String userId,@Param("sharingCenterId")String sharingCenterId);

	//显示当前用户所兑换过的简历列表
	public List<DownloadRecordPojo> getDownloadRecordById(@Param("userId")String userId);
}
