package com.qc.rc.entity;

import java.util.Date;

public class DownloadRecord {
	
	private Integer drId;
	private Integer drUserId;
	private Integer drSharingCenterId;

	private Date drCreateTime;
	private Date drUpdateTime;
	private Integer drDeleteFlag;
	
	public Integer getDrId() {
		return drId;
	}
	public void setDrId(Integer drId) {
		this.drId = drId;
	}
	public Integer getDrUserId() {
		return drUserId;
	}
	public void setDrUserId(Integer drUserId) {
		this.drUserId = drUserId;
	}
	public Integer getDrSharingCenterId() {
		return drSharingCenterId;
	}
	public void setDrSharingCenterId(Integer drSharingCenterId) {
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
	public DownloadRecord(Integer drId, Integer drUserId, Integer drSharingCenterId, Date drCreateTime,
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
	
}
