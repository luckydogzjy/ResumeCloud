package com.qc.rc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qc.rc.entity.DownloadRecord;
import com.qc.rc.entity.pojo.ResumePojo;
import com.qc.rc.entity.pojo.SharingCenterPojo;

public interface SharingCenterMapper {
	
	//条件查询共享中心信息
	public List<SharingCenterPojo> getSharingResumeListByCondition(@Param("resumePojo")ResumePojo resumePojo);
	
	//显示当前用户所兑换过的简历列表
//	public List<DownloadRecord> getDownloadRecordById(@Param("userId")Integer userId);
	
	//兑换次数加一
	public Integer updateDownloadCount(@Param("scId")String scId);
	
}
