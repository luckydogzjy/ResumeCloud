package com.qc.rc.entity.pojo;

import java.util.Date;

import com.qc.rc.entity.DownloadRecord;

public class DownloadRecordPojo extends DownloadRecord {

	private String scResumeId;  //兑换的简历id
	
	private String urResumeId;  //现在的id

	public String getScResumeId() {
		return scResumeId;
	}

	public void setScResumeId(String scResumeId) {
		this.scResumeId = scResumeId;
	}

	public String getUrResumeId() {
		return urResumeId;
	}

	public void setUrResumeId(String urResumeId) {
		this.urResumeId = urResumeId;
	}

	/**
	 * 
	 */
	public DownloadRecordPojo() {
		super();
	}

	/**
	 * @param drId
	 * @param drUserId
	 * @param drSharingCenterId
	 * @param drCreateTime
	 * @param drUpdateTime
	 * @param drDeleteFlag
	 * @param scResumeId
	 * @param urResumeId
	 */
	public DownloadRecordPojo(String drId, String drUserId, String drSharingCenterId, Date drCreateTime,
			Date drUpdateTime, Integer drDeleteFlag, String scResumeId, String urResumeId) {
		super(drId, drUserId, drSharingCenterId, drCreateTime, drUpdateTime, drDeleteFlag);
		this.scResumeId = scResumeId;
		this.urResumeId = urResumeId;
	}

	
	
}
