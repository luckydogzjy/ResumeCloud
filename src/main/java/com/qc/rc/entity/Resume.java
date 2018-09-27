package com.qc.rc.entity;

import java.util.Date;

import org.springframework.stereotype.Component;

public class Resume {
	
	private Integer resumeId;
	private String resumeName;
	private Integer resumeSex;
	private Date resumeBirthday;
	private String resumePhone;
	private String resumeEmail;
	private String resumeAddress;
	private String resumeGraduateInstitution; 	//毕业院校
	private Integer resumeEducation;			  	//学历
	private String resumeJobIntension;		    //求职意向
	private String resumeSelfEvaluation;		//自我评价
	private String resumeWorkExperience;		//工作经验
	private Integer resumeWorkYears;			//工作年
		
	private String resumeCreateUser;
	private Date resumeCreateTime; 
	private String resumeUpdateUser;
	private Date resumeUpdateTime; 
	private Integer resumeDeleteFlag;
	public Integer getResumeId() {
		return resumeId;
	}
	public void setResumeId(Integer resumeId) {
		this.resumeId = resumeId;
	}
	public String getResumeName() {
		return resumeName;
	}
	public void setResumeName(String resumeName) {
		this.resumeName = resumeName;
	}
	public Integer getResumeSex() {
		return resumeSex;
	}
	public void setResumeSex(Integer resumeSex) {
		this.resumeSex = resumeSex;
	}
	public Date getResumeBirthday() {
		return resumeBirthday;
	}
	public void setResumeBirthday(Date resumeBirthday) {
		this.resumeBirthday = resumeBirthday;
	}
	public String getResumePhone() {
		return resumePhone;
	}
	public void setResumePhone(String resumePhone) {
		this.resumePhone = resumePhone;
	}
	public String getResumeEmail() {
		return resumeEmail;
	}
	public void setResumeEmail(String resumeEmail) {
		this.resumeEmail = resumeEmail;
	}
	public String getResumeAddress() {
		return resumeAddress;
	}
	public void setResumeAddress(String resumeAddress) {
		this.resumeAddress = resumeAddress;
	}
	public String getResumeGraduateInstitution() {
		return resumeGraduateInstitution;
	}
	public void setResumeGraduateInstitution(String resumeGraduateInstitution) {
		this.resumeGraduateInstitution = resumeGraduateInstitution;
	}
	public Integer getResumeEducation() {
		return resumeEducation;
	}
	public void setResumeEducation(Integer resumeEducation) {
		this.resumeEducation = resumeEducation;
	}
	public String getResumeJobIntension() {
		return resumeJobIntension;
	}
	public void setResumeJobIntension(String resumeJobIntension) {
		this.resumeJobIntension = resumeJobIntension;
	}
	public String getResumeSelfEvaluation() {
		return resumeSelfEvaluation;
	}
	public void setResumeSelfEvaluation(String resumeSelfEvaluation) {
		this.resumeSelfEvaluation = resumeSelfEvaluation;
	}
	public String getResumeWorkExperience() {
		return resumeWorkExperience;
	}
	public void setResumeWorkExperience(String resumeWorkExperience) {
		this.resumeWorkExperience = resumeWorkExperience;
	}
	public Integer getResumeWorkYears() {
		return resumeWorkYears;
	}
	public void setResumeWorkYears(Integer resumeWorkYears) {
		this.resumeWorkYears = resumeWorkYears;
	}
	
	public String getResumeCreateUser() {
		return resumeCreateUser;
	}
	public void setResumeCreateUser(String resumeCreateUser) {
		this.resumeCreateUser = resumeCreateUser;
	}
	public Date getResumeCreateTime() {
		return resumeCreateTime;
	}
	public void setResumeCreateTime(Date resumeCreateTime) {
		this.resumeCreateTime = resumeCreateTime;
	}
	public String getResumeUpdateUser() {
		return resumeUpdateUser;
	}
	public void setResumeUpdateUser(String resumeUpdateUser) {
		this.resumeUpdateUser = resumeUpdateUser;
	}
	public Date getResumeUpdateTime() {
		return resumeUpdateTime;
	}
	public void setResumeUpdateTime(Date resumeUpdateTime) {
		this.resumeUpdateTime = resumeUpdateTime;
	}
	public Integer getResumeDeleteFlag() {
		return resumeDeleteFlag;
	}
	public void setResumeDeleteFlag(Integer resumeDeleteFlag) {
		this.resumeDeleteFlag = resumeDeleteFlag;
	}

	
	/**
	 * @param resumeId
	 * @param resumeName
	 * @param resumeSex
	 * @param resumeBirthday
	 * @param resumePhone
	 * @param resumeEmail
	 * @param resumeAddress
	 * @param resumeGraduateInstitution
	 * @param resumeEducation
	 * @param resumeJobIntension
	 * @param resumeSelfEvaluation
	 * @param resumeWorkExperience
	 * @param resumeWorkYears
	 * @param resumeCreateUser
	 * @param resumeCreateTime
	 * @param resumeUpdateUser
	 * @param resumeUpdateTime
	 * @param resumeDeleteFlag
	 */
	public Resume(Integer resumeId, String resumeName, Integer resumeSex, Date resumeBirthday, String resumePhone,
			String resumeEmail, String resumeAddress, String resumeGraduateInstitution, Integer resumeEducation,
			String resumeJobIntension, String resumeSelfEvaluation, String resumeWorkExperience,
			Integer resumeWorkYears, String resumeCreateUser, Date resumeCreateTime, String resumeUpdateUser,
			Date resumeUpdateTime, Integer resumeDeleteFlag) {
		super();
		this.resumeId = resumeId;
		this.resumeName = resumeName;
		this.resumeSex = resumeSex;
		this.resumeBirthday = resumeBirthday;
		this.resumePhone = resumePhone;
		this.resumeEmail = resumeEmail;
		this.resumeAddress = resumeAddress;
		this.resumeGraduateInstitution = resumeGraduateInstitution;
		this.resumeEducation = resumeEducation;
		this.resumeJobIntension = resumeJobIntension;
		this.resumeSelfEvaluation = resumeSelfEvaluation;
		this.resumeWorkExperience = resumeWorkExperience;
		this.resumeWorkYears = resumeWorkYears;
		this.resumeCreateUser = resumeCreateUser;
		this.resumeCreateTime = resumeCreateTime;
		this.resumeUpdateUser = resumeUpdateUser;
		this.resumeUpdateTime = resumeUpdateTime;
		this.resumeDeleteFlag = resumeDeleteFlag;
	}
	public Resume(){
		super();
	}
	@Override
	public String toString() {
		return "Resume [resumeId=" + resumeId + ", resumeName=" + resumeName + ", resumeSex=" + resumeSex
				+ ", resumeBirthday=" + resumeBirthday + ", resumePhone=" + resumePhone + ", resumeEmail=" + resumeEmail
				+ ", resumeAddress=" + resumeAddress + ", resumeGraduateInstitution=" + resumeGraduateInstitution
				+ ", resumeEducation=" + resumeEducation + ", resumeJobIntension=" + resumeJobIntension
				+ ", resumeSelfEvaluation=" + resumeSelfEvaluation + ", resumeWorkExperience=" + resumeWorkExperience
				+ ", resumeWorkYears=" + resumeWorkYears + ", resumeCreateUser=" + resumeCreateUser
				+ ", resumeCreateTime=" + resumeCreateTime + ", resumeUpdateUser=" + resumeUpdateUser
				+ ", resumeUpdateTime=" + resumeUpdateTime + ", resumeDeleteFlag=" + resumeDeleteFlag + "]";
	}
	
}
