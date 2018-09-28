package com.qc.rc.entity.pojo;

import java.util.Date;

import com.qc.rc.entity.Resume;
import com.qc.rc.entity.SharingCenter;

public class SharingCenterPojo extends SharingCenter{
	
	private Resume resume;
	private Integer flag; //当前用户的兑换状态 未兑换为0，若兑换了，则赋值为1

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
	 */
	public SharingCenterPojo(Integer scId, String scUserId, Integer scResumeId, Integer scIntegral,
			Integer scDownloadCount, Date scCreateTime, Date scUpdateTime, Integer scDeleteFlag, Resume resume) {
		super(scId, scUserId, scResumeId, scIntegral, scDownloadCount, scCreateTime, scUpdateTime, scDeleteFlag);
		this.resume = resume;
	}
	
	public SharingCenterPojo(){	
		super();
		flag = 0;
	}
	
}
