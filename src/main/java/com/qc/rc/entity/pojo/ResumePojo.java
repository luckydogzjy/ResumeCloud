package com.qc.rc.entity.pojo;

import java.util.Date;

import com.qc.rc.entity.Interview;
import com.qc.rc.entity.Resume;

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
	 */
	public ResumePojo(Integer resumeId, String resumeName, Integer resumeSex, Date resumeBirthday, String resumePhone,
			String resumeEmail, String resumeAddress, String resumeGraduateInstitution, Integer resumeEducation,
			String resumeJobIntension, String resumeSelfEvaluation, String resumeWorkExperience,
			Integer resumeWorkYears, String resumeCreateUser, Date resumeCreateTime, String resumeUpdateUser,
			Date resumeUpdateTime, Integer resumeDeleteFlag, Interview interview) {
		super(resumeId, resumeName, resumeSex, resumeBirthday, resumePhone, resumeEmail, resumeAddress,
				resumeGraduateInstitution, resumeEducation, resumeJobIntension, resumeSelfEvaluation,
				resumeWorkExperience, resumeWorkYears, resumeCreateUser, resumeCreateTime, resumeUpdateUser,
				resumeUpdateTime, resumeDeleteFlag);
		this.interview = interview;
	}

	public ResumePojo(){
		
	}
	
	public Interview interview;

	public Interview getInterview() {
		return interview;
	}

	public void setInterview(Interview interview) {
		this.interview = interview;
	}
	

	

}
