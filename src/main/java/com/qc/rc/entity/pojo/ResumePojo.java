package com.qc.rc.entity.pojo;

import java.io.File;
import java.util.Date;
import java.util.List;


import com.qc.rc.entity.Interview;
import com.qc.rc.entity.Pic;
import com.qc.rc.entity.Resume;
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
	private List<File> lFiles;
	
	public List<File> getlFiles() {
		return lFiles;
	}

	public void setlFiles(List<File> lFiles) {
		this.lFiles = lFiles;
	}

	public List<Pic> getlPics() {
		return lPics;
	}

	public void setlPics(List<Pic> lPics) {
		this.lPics = lPics;
	}

}
