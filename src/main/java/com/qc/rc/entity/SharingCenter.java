package com.qc.rc.entity;

import java.util.Date;

import org.springframework.stereotype.Component;
@Component
public class SharingCenter {
	
	private Integer scId;
	private Integer scUserId;
	private Integer scResumeId;
	private Integer scIntegral;
	private Integer scDownloadCount;
	
	private Date scCreateTime;
	private Date scUpdateTime;
	private Integer scDeleteFlag;
	
	public Integer getScId() {
		return scId;
	}
	public void setScId(Integer scId) {
		this.scId = scId;
	}
	public Integer getScUserId() {
		return scUserId;
	}
	public void setScUserId(Integer scUserId) {
		this.scUserId = scUserId;
	}
	public Integer getScResumeId() {
		return scResumeId;
	}
	public void setScResumeId(Integer scResumeId) {
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
	public SharingCenter(Integer scId, Integer scUserId, Integer scResumeId, Integer scIntegral,
			Integer scDownloadCount, Date scCreateTime, Date scUpdateTime, Integer scDeleteFlag) {
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
}
