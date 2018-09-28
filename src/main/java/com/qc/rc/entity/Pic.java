package com.qc.rc.entity;

import java.util.Date;


public class Pic {
	
	private Integer pId;
	private Integer pResumeId;
	private String pPic;  //图片路径
	
	private String pCreateUser;
	private Date pCreateTime;
	private String pUpdateUser;
	private Date pUpdateTime;
	private Date pDeleteFlag;
	
	
	@Override
	public String toString() {
		return "Pic [pId=" + pId + ", pResumeId=" + pResumeId + ", pPic=" + pPic + ", pCreateUser=" + pCreateUser
				+ ", pCreateTime=" + pCreateTime + ", pUpdateUser=" + pUpdateUser + ", pUpdateTime=" + pUpdateTime
				+ ", pDeleteFlag=" + pDeleteFlag + "]";
	}
	public Integer getpId() {
		return pId;
	}
	public void setpId(Integer pId) {
		this.pId = pId;
	}
	public Integer getpResumeId() {
		return pResumeId;
	}
	public void setpResumeId(Integer pResumeId) {
		this.pResumeId = pResumeId;
	}
	public String getpPic() {
		return pPic;
	}
	public void setpPic(String pPic) {
		this.pPic = pPic;
	}
	public String getpCreateUser() {
		return pCreateUser;
	}
	public void setpCreateUser(String pCreateUser) {
		this.pCreateUser = pCreateUser;
	}
	public Date getpCreateTime() {
		return pCreateTime;
	}
	public void setpCreateTime(Date pCreateTime) {
		this.pCreateTime = pCreateTime;
	}
	public String getpUpdateUser() {
		return pUpdateUser;
	}
	public void setpUpdateUser(String pUpdateUser) {
		this.pUpdateUser = pUpdateUser;
	}
	public Date getpUpdateTime() {
		return pUpdateTime;
	}
	public void setpUpdateTime(Date pUpdateTime) {
		this.pUpdateTime = pUpdateTime;
	}
	public Date getpDeleteFlag() {
		return pDeleteFlag;
	}
	public void setpDeleteFlag(Date pDeleteFlag) {
		this.pDeleteFlag = pDeleteFlag;
	}
	/**
	 * @param pId
	 * @param pResumeId
	 * @param pPic
	 * @param pCreateUser
	 * @param pCreateTime
	 * @param pUpdateUser
	 * @param pUpdateTime
	 * @param pDeleteFlag
	 */
	public Pic(Integer pId, Integer pResumeId, String pPic, String pCreateUser, Date pCreateTime, String pUpdateUser,
			Date pUpdateTime, Date pDeleteFlag) {
		super();
		this.pId = pId;
		this.pResumeId = pResumeId;
		this.pPic = pPic;
		this.pCreateUser = pCreateUser;
		this.pCreateTime = pCreateTime;
		this.pUpdateUser = pUpdateUser;
		this.pUpdateTime = pUpdateTime;
		this.pDeleteFlag = pDeleteFlag;
	}

	public Pic(){
		super();
	}
	
}
