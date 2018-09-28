package com.qc.rc.service;

import java.util.Map;

import com.qc.rc.entity.SharingCenter;
import com.qc.rc.entity.User;
import com.qc.rc.entity.pojo.ResumePojo;

public interface SharingCenterService {
	
	//条件查询共享中心信息
	public Map<String, Object> getSharingResumeListByCondition(Integer userId,ResumePojo resumePojo,Integer page);
	//点击兑换按钮要做的事情
	public void exchangeResume(User user,ResumePojo searchResumePojo,SharingCenter sharingCenter) throws Exception;
	
}
