package com.qc.rc.entity.pojo;

import java.io.File;
import java.util.Date;
import java.util.List;


import com.qc.rc.entity.Interview;
import com.qc.rc.entity.Pic;
import com.qc.rc.entity.Resume;
import com.qc.rc.entity.ResumeFile;
import com.qc.rc.entity.UserResume;

public class ResumePojo extends Resume {
	


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
	 * @param interview
	 * @param userResume
	 * @param lPics
	 * @param lFiles
	 */
	public ResumePojo(String resumeId, String resumeName, Integer resumeSex, Date resumeBirthday, String resumePhone,
			String resumeEmail, String resumeAddress, String resumeGraduateInstitution, Integer resumeEducation,
			String resumeJobIntension, String resumeSelfEvaluation, String resumeWorkExperience,
			Integer resumeWorkYears, String resumeCreateUser, Date resumeCreateTime, String resumeUpdateUser,
			Date resumeUpdateTime, Integer resumeDeleteFlag, Interview interview, UserResume userResume,
			List<Pic> lPics, List<ResumeFile> lFiles) {
		super(resumeId, resumeName, resumeSex, resumeBirthday, resumePhone, resumeEmail, resumeAddress,
				resumeGraduateInstitution, resumeEducation, resumeJobIntension, resumeSelfEvaluation,
				resumeWorkExperience, resumeWorkYears, resumeCreateUser, resumeCreateTime, resumeUpdateUser,
				resumeUpdateTime, resumeDeleteFlag);
		this.interview = interview;
		this.userResume = userResume;
		this.lPics = lPics;
		this.lFiles = lFiles;
	}

	public ResumePojo(){
		super();
	}
	
	private Interview interview;

	public Interview getInterview() {
		return interview;
	}

	public void setInterview(Interview interview) {
		this.interview = interview;
	}
		
	private  UserResume userResume;
	
	public UserResume getUserResume() {
		return userResume;
	}

	public void setUserResume(UserResume userResume) {
		this.userResume = userResume;
	}
	

	//查询详情的图片和文件 一对多
	private	List<Pic> lPics;
	private List<ResumeFile> lFiles;
	
	public List<ResumeFile> getlFiles() {
		return lFiles;
	}

	public void setlFiles(List<ResumeFile> lFiles) {
		this.lFiles = lFiles;
	}

	public List<Pic> getlPics() {
		return lPics;
	}

	public void setlPics(List<Pic> lPics) {
		this.lPics = lPics;
	}

}
