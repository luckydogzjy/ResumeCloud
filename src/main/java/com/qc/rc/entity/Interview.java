package com.qc.rc.entity;

import java.util.Date;


public class Interview {
	/*
	 * (
  INTERVIEW_ID NUMBER(6, 0) NOT NULL 
, INTERVIEW_RESUME_ID NUMBER(6, 0) 
, INTERVIEW_JOB VARCHAR2(20 BYTE) 
, INTERVIEW_TIME DATE 
, INTERVIEW_ASSOCIATE_USERNAME VARCHAR2(20 BYTE) 
, INTERVIEW_ASSOCIATE_PHONE VARCHAR2(11 BYTE) 
, INTERVIEW_ADDRESS VARCHAR2(100 BYTE) 
, INTERVIEW_INFO VARCHAR2(200 BYTE) 
, INTERVIEW_STATUS NUMBER(1, 0) 
, INTERVIEW_RECODE_INFO VARCHAR2(200 BYTE) 
, INTERVIEW_CREATE_USER VARCHAR2(20 BYTE) 
, INTERVIEW_CREATE_TIME DATE DEFAULT sysdate NOT NULL 
, INTERVIEW_UPDATE_USER VARCHAR2(20 BYTE) 
, INTERVIEW_UPDATE_TIME DATE 
, INTERVIEW_DELETE_FLAG NUMBER(1, 0) DEFAULT 0 NOT NULL 
, INTERVIEW_USER_ID NUMBER(6, 0) NOT NULL 
)
	 */
	private Integer interviewId;//id
	private Integer interviewResumeId;
	private String interviewJob;
	private Date interviewTime;
	private String interviewAssociateUsername;
	private String interviewAssociatePhone;
	private String interviewAddress;
	private String interviewInfo;
	private Integer interviewStatus;
	private String interviewRecodeInfo;
	private String interviewCreateUser;
	private Date interviewCreateTime;
	private String interviewUpdateUser;
	private Date interviewUpdateTime;
	private Integer interviewDeleteFlag;
	private Integer interviewUserId;
	
	
	public Integer getInterviewId() {
		return interviewId;
	}
	public void setInterviewId(Integer interviewId) {
		this.interviewId = interviewId;
	}
	public Integer getInterviewResumeId() {
		return interviewResumeId;
	}
	public void setInterviewResumeId(Integer interviewResumeId) {
		this.interviewResumeId = interviewResumeId;
	}
	public String getInterviewJob() {
		return interviewJob;
	}
	public void setInterviewJob(String interviewJob) {
		this.interviewJob = interviewJob;
	}
	public Date getInterviewTime() {
		return interviewTime;
	}
	public void setInterviewTime(Date interviewTime) {
		this.interviewTime = interviewTime;
	}
	public String getInterviewAssociateUsername() {
		return interviewAssociateUsername;
	}
	public void setInterviewAssociateUsername(String interviewAssociateUsername) {
		this.interviewAssociateUsername = interviewAssociateUsername;
	}
	public String getInterviewAssociatePhone() {
		return interviewAssociatePhone;
	}
	public void setInterviewAssociatePhone(String interviewAssociatePhone) {
		this.interviewAssociatePhone = interviewAssociatePhone;
	}
	public String getInterviewAddress() {
		return interviewAddress;
	}
	public void setInterviewAddress(String interviewAddress) {
		this.interviewAddress = interviewAddress;
	}
	public String getInterviewInfo() {
		return interviewInfo;
	}
	public void setInterviewInfo(String interviewInfo) {
		this.interviewInfo = interviewInfo;
	}
	public Integer getInterviewStatus() {
		return interviewStatus;
	}
	public void setInterviewStatus(Integer interviewStatus) {
		this.interviewStatus = interviewStatus;
	}
	public String getInterviewRecodeInfo() {
		return interviewRecodeInfo;
	}
	public void setInterviewRecodeInfo(String interviewRecodeInfo) {
		this.interviewRecodeInfo = interviewRecodeInfo;
	}
	public String getInterviewCreateUser() {
		return interviewCreateUser;
	}
	public void setInterviewCreateUser(String interviewCreateUser) {
		this.interviewCreateUser = interviewCreateUser;
	}
	public Date getInterviewCreateTime() {
		return interviewCreateTime;
	}
	public void setInterviewCreateTime(Date interviewCreateTime) {
		this.interviewCreateTime = interviewCreateTime;
	}
	public String getInterviewUpdateUser() {
		return interviewUpdateUser;
	}
	public void setInterviewUpdateUser(String interviewUpdateUser) {
		this.interviewUpdateUser = interviewUpdateUser;
	}
	public Date getInterviewUpdateTime() {
		return interviewUpdateTime;
	}
	public void setInterviewUpdateTime(Date interviewUpdateTime) {
		this.interviewUpdateTime = interviewUpdateTime;
	}
	public Integer getInterviewDeleteFlag() {
		return interviewDeleteFlag;
	}
	public void setInterviewDeleteFlag(Integer interviewDeleteFlag) {
		this.interviewDeleteFlag = interviewDeleteFlag;
	}
	public Integer getInterviewUserId() {
		return interviewUserId;
	}
	public void setInterviewUserId(Integer interviewUserId) {
		this.interviewUserId = interviewUserId;
	}
	public Interview(Integer interviewId, Integer interviewResumeId, String interviewJob, Date interviewTime,
			String interviewAssociateUsername, String interviewAssociatePhone, String interviewAddress,
			String interviewInfo, Integer interviewStatus, String interviewRecodeInfo, String interviewCreateUser,
			Date interviewCreateTime, String interviewUpdateUser, Date interviewUpdateTime, Integer interviewDeleteFlag,
			Integer interviewUserId) {
		super();
		this.interviewId = interviewId;
		this.interviewResumeId = interviewResumeId;
		this.interviewJob = interviewJob;
		this.interviewTime = interviewTime;
		this.interviewAssociateUsername = interviewAssociateUsername;
		this.interviewAssociatePhone = interviewAssociatePhone;
		this.interviewAddress = interviewAddress;
		this.interviewInfo = interviewInfo;
		this.interviewStatus = interviewStatus;
		this.interviewRecodeInfo = interviewRecodeInfo;
		this.interviewCreateUser = interviewCreateUser;
		
		this.interviewCreateTime = interviewCreateTime;
		this.interviewUpdateUser = interviewUpdateUser;
		
		
		this.interviewUpdateTime = interviewUpdateTime;
		
		
		this.interviewDeleteFlag = interviewDeleteFlag;
		this.interviewUserId = interviewUserId;
	}
	
	
	
	public Interview() {
		super();
	}
	
	
	
}
