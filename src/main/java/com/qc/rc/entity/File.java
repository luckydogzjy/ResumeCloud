package com.qc.rc.entity;

import java.util.Date;

public class File {
	
	private Integer fId;
	private Integer fResumeId;
	private String fFile;  //文件路径
	
	private String fCreateUser;
	private Date fCreateTime;
	private String fUpdateUser;
	private Date fUpdateTime;
	private Date fDeleteFlag;
	public Integer getfId() {
		return fId;
	}
	public void setfId(Integer fId) {
		this.fId = fId;
	}
	public Integer getfResumeId() {
		return fResumeId;
	}
	public void setfResumeId(Integer fResumeId) {
		this.fResumeId = fResumeId;
	}
	public String getfFile() {
		return fFile;
	}
	public void setfFile(String fFile) {
		this.fFile = fFile;
	}
	public String getfCreateUser() {
		return fCreateUser;
	}
	public void setfCreateUser(String fCreateUser) {
		this.fCreateUser = fCreateUser;
	}
	public Date getfCreateTime() {
		return fCreateTime;
	}
	public void setfCreateTime(Date fCreateTime) {
		this.fCreateTime = fCreateTime;
	}
	public String getfUpdateUser() {
		return fUpdateUser;
	}
	public void setfUpdateUser(String fUpdateUser) {
		this.fUpdateUser = fUpdateUser;
	}
	public Date getfUpdateTime() {
		return fUpdateTime;
	}
	public void setfUpdateTime(Date fUpdateTime) {
		this.fUpdateTime = fUpdateTime;
	}
	public Date getfDeleteFlag() {
		return fDeleteFlag;
	}
	public void setfDeleteFlag(Date fDeleteFlag) {
		this.fDeleteFlag = fDeleteFlag;
	}
	/**
	 * @param fId
	 * @param fResumeId
	 * @param fFile
	 * @param fCreateUser
	 * @param fCreateTime
	 * @param fUpdateUser
	 * @param fUpdateTime
	 * @param fDeleteFlag
	 */
	public File(Integer fId, Integer fResumeId, String fFile, String fCreateUser, Date fCreateTime, String fUpdateUser,
			Date fUpdateTime, Date fDeleteFlag) {
		super();
		this.fId = fId;
		this.fResumeId = fResumeId;
		this.fFile = fFile;
		this.fCreateUser = fCreateUser;
		this.fCreateTime = fCreateTime;
		this.fUpdateUser = fUpdateUser;
		this.fUpdateTime = fUpdateTime;
		this.fDeleteFlag = fDeleteFlag;
	}
	
	public File(){
		super();
	}
	
	
}
