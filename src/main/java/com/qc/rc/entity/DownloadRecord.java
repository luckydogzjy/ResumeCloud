package com.qc.rc.entity;

import java.util.Date;

public class DownloadRecord {
	
	private String drId;
	private String drUserId;
	private String drSharingCenterId;

	private Date drCreateTime;
	private Date drUpdateTime;
	private Integer drDeleteFlag;
	

	public String getDrId() {
		return drId;
	}
	public void setDrId(String drId) {
		this.drId = drId;
	}
	public String getDrUserId() {
		return drUserId;
	}
	public void setDrUserId(String drUserId) {
		this.drUserId = drUserId;
	}
	public String getDrSharingCenterId() {
		return drSharingCenterId;
	}
	public void setDrSharingCenterId(String drSharingCenterId) {
		this.drSharingCenterId = drSharingCenterId;
	}
	public Date getDrCreateTime() {
		return drCreateTime;
	}
	public void setDrCreateTime(Date drCreateTime) {
		this.drCreateTime = drCreateTime;
	}
	public Date getDrUpdateTime() {
		return drUpdateTime;
	}
	public void setDrUpdateTime(Date drUpdateTime) {
		this.drUpdateTime = drUpdateTime;
	}
	public Integer getDrDeleteFlag() {
		return drDeleteFlag;
	}
	public void setDrDeleteFlag(Integer drDeleteFlag) {
		this.drDeleteFlag = drDeleteFlag;
	}
	/**
	 * @param drId
	 * @param drUserId
	 * @param drSharingCenterId
	 * @param drCreateTime
	 * @param drUpdateTime
	 * @param drDeleteFlag
	 */
	public DownloadRecord(String drId, String drUserId, String drSharingCenterId, Date drCreateTime,
			Date drUpdateTime, Integer drDeleteFlag) {
		super();
		this.drId = drId;
		this.drUserId = drUserId;
		this.drSharingCenterId = drSharingCenterId;
		this.drCreateTime = drCreateTime;
		this.drUpdateTime = drUpdateTime;
		this.drDeleteFlag = drDeleteFlag;
	}
	public DownloadRecord(){
		super();
	}
	@Override
	public String toString() {
		return "DownloadRecord [drId=" + drId + ", drUserId=" + drUserId + ", drSharingCenterId=" + drSharingCenterId
				+ ", drCreateTime=" + drCreateTime + ", drUpdateTime=" + drUpdateTime + ", drDeleteFlag=" + drDeleteFlag
				+ "]";
	}
	
}
