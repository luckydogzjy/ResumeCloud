package com.qc.rc.entity;

import java.util.Date;


public class SharingCenter {
	
	private String scId;
	private String scUserId;
	private String scResumeId;
	private Integer scIntegral;
	private Integer scDownloadCount;
	
	private Date scCreateTime;
	private Date scUpdateTime;
	private Integer scDeleteFlag;
	

	public String getScId() {
		return scId;
	}
	public void setScId(String scId) {
		this.scId = scId;
	}
	public String getScUserId() {
		return scUserId;
	}
	public void setScUserId(String scUserId) {
		this.scUserId = scUserId;
	}
	public String getScResumeId() {
		return scResumeId;
	}
	public void setScResumeId(String scResumeId) {
		this.scResumeId = scResumeId;
	}
	public Integer getScIntegral() {
		return scIntegral;
	}
	public void setScIntegral(Integer scIntegral) {
		this.scIntegral = scIntegral;
	}
	public Integer getScDownloadCount() {
		return scDownloadCount;
	}
	public void setScDownloadCount(Integer scDownloadCount) {
		this.scDownloadCount = scDownloadCount;
	}
	public Date getScCreateTime() {
		return scCreateTime;
	}
	public void setScCreateTime(Date scCreateTime) {
		this.scCreateTime = scCreateTime;
	}
	public Date getScUpdateTime() {
		return scUpdateTime;
	}
	public void setScUpdateTime(Date scUpdateTime) {
		this.scUpdateTime = scUpdateTime;
	}
	public Integer getScDeleteFlag() {
		return scDeleteFlag;
	}
	public void setScDeleteFlag(Integer scDeleteFlag) {
		this.scDeleteFlag = scDeleteFlag;
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
	 */
	public SharingCenter(String scId, String scUserId, String scResumeId, Integer scIntegral, Integer scDownloadCount,
			Date scCreateTime, Date scUpdateTime, Integer scDeleteFlag) {
		super();
		this.scId = scId;
		this.scUserId = scUserId;
		this.scResumeId = scResumeId;
		this.scIntegral = scIntegral;
		this.scDownloadCount = scDownloadCount;
		this.scCreateTime = scCreateTime;
		this.scUpdateTime = scUpdateTime;
		this.scDeleteFlag = scDeleteFlag;
	}
	public SharingCenter(){
		super();
	}
	@Override
	public String toString() {
		return "SharingCenter [scId=" + scId + ", scUserId=" + scUserId + ", scResumeId=" + scResumeId + ", scIntegral="
				+ scIntegral + ", scDownloadCount=" + scDownloadCount + ", scCreateTime=" + scCreateTime
				+ ", scUpdateTime=" + scUpdateTime + ", scDeleteFlag=" + scDeleteFlag + "]";
	}
	
}
