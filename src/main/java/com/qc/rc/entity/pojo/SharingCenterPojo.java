package com.qc.rc.entity.pojo;

import java.util.Date;

import com.qc.rc.entity.Resume;
import com.qc.rc.entity.SharingCenter;

public class SharingCenterPojo extends SharingCenter{
	
	private Resume resume;
	private Integer flag; //当前用户的兑换状态 未兑换为0，若兑换了，则赋值为1
	private String nowResumeId; //已兑换信息的简历id

	public Resume getResume() { 
		return resume;
	}

	public void setResume(Resume resume) {
		this.resume = resume;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	

	public String getNowResumeId() {
		return nowResumeId;
	}

	public void setNowResumeId(String nowResumeId) {
		this.nowResumeId = nowResumeId;
	}


	


	/**
	 * @param scId
	 * @param scUserId
	 * @param scResumeId
	 * @param scIntegral
	 * @param scDownloadCount
	 * @param scCreateTime
	 * @param scUpdateTime
	 * @param scDeleteFlag
	 * @param resume
	 * @param flag
	 * @param nowResumeId
	 */
	public SharingCenterPojo(String scId, String scUserId, String scResumeId, Integer scIntegral,
			Integer scDownloadCount, Date scCreateTime, Date scUpdateTime, Integer scDeleteFlag, Resume resume,
			Integer flag, String nowResumeId) {
		super(scId, scUserId, scResumeId, scIntegral, scDownloadCount, scCreateTime, scUpdateTime, scDeleteFlag);
		this.resume = resume;
		this.flag = flag;
		this.nowResumeId = nowResumeId;
	}

	public SharingCenterPojo(){	
		super();
		flag = 0;
		nowResumeId = "0";
	}
	
}
