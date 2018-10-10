package com.qc.rc.entity.pojo;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.qc.rc.entity.Resume;
import com.qc.rc.entity.UserResume;

@Component
public class UserExchangeResumePojo extends Resume {

	private Integer urResumeGetway;
	private Integer urUesrId;
	private Integer urResumeId;
	private Date urCreateTime;
	private UserResume userresume;
	public Integer getUrResumeGetway() {
		return urResumeGetway;
	}
	public void setUrResumeGetway(Integer urResumeGetway) {
		this.urResumeGetway = urResumeGetway;
	}
	public Integer getUrUesrId() {
		return urUesrId;
	}
	public void setUrUesrId(Integer urUesrId) {
		this.urUesrId = urUesrId;
	}
	public Integer getUrResumeId() {
		return urResumeId;
	}
	public void setUrResumeId(Integer urResumeId) {
		this.urResumeId = urResumeId;
	}
	public Date getUrCreateTime() {
		return urCreateTime;
	}
	public void setUrCreateTime(Date urCreateTime) {
		this.urCreateTime = urCreateTime;
	}
	public UserResume getUserresume() {
		return userresume;
	}
	public void setUserresume(UserResume userresume) {
		this.userresume = userresume;
	}
	@Override
	public String toString() {
		return "UserExchangeResumePojo [urResumeGetway=" + urResumeGetway + ", urUesrId=" + urUesrId + ", urResumeId="
				+ urResumeId + ", urCreateTime=" + urCreateTime + ", userresume=" + userresume + "]";
	}
	public UserExchangeResumePojo(String resumeId, String resumeName, Integer resumeSex, Date resumeBirthday,
			String resumePhone, String resumeEmail, String resumeAddress, String resumeGraduateInstitution,
			Integer resumeEducation, String resumeJobIntension, String resumeSelfEvaluation,
			String resumeWorkExperience, Integer resumeWorkYears, String resumeCreateUser, Date resumeCreateTime,
			String resumeUpdateUser, Date resumeUpdateTime, Integer resumeDeleteFlag, Integer urResumeGetway,
			Integer urUesrId, Integer urResumeId, Date urCreateTime, UserResume userresume) {
		super(resumeId, resumeName, resumeSex, resumeBirthday, resumePhone, resumeEmail, resumeAddress,
				resumeGraduateInstitution, resumeEducation, resumeJobIntension, resumeSelfEvaluation,
				resumeWorkExperience, resumeWorkYears, resumeCreateUser, resumeCreateTime, resumeUpdateUser,
				resumeUpdateTime, resumeDeleteFlag);
		this.urResumeGetway = urResumeGetway;
		this.urUesrId = urUesrId;
		this.urResumeId = urResumeId;
		this.urCreateTime = urCreateTime;
		this.userresume = userresume;
	}
	public UserExchangeResumePojo() {
		super();
	
	}
	public UserExchangeResumePojo(String resumeId, String resumeName, Integer resumeSex, Date resumeBirthday,
			String resumePhone, String resumeEmail, String resumeAddress, String resumeGraduateInstitution,
			Integer resumeEducation, String resumeJobIntension, String resumeSelfEvaluation,
			String resumeWorkExperience, Integer resumeWorkYears, String resumeCreateUser, Date resumeCreateTime,
			String resumeUpdateUser, Date resumeUpdateTime, Integer resumeDeleteFlag) {
		super(resumeId, resumeName, resumeSex, resumeBirthday, resumePhone, resumeEmail, resumeAddress,
				resumeGraduateInstitution, resumeEducation, resumeJobIntension, resumeSelfEvaluation, resumeWorkExperience,
				resumeWorkYears, resumeCreateUser, resumeCreateTime, resumeUpdateUser, resumeUpdateTime, resumeDeleteFlag);

	}

	
	

}
