package com.qc.rc.entity.pojo;

import java.util.List;

import com.qc.rc.entity.Interview;
import com.qc.rc.entity.Resume;

public class ResumeInterviews extends Resume{
	private List<Interview> interviews;

	public List<Interview> getInterviews() {
		return interviews;
	}

	public void setInterviews(List<Interview> interviews) {
		this.interviews = interviews;
	}

	@Override
	public String toString() {
		return "ResumeInterviews [interviews=" + interviews + ", getInterviews()=" + getInterviews()
				+ ", getResumeId()=" + getResumeId() + ", getResumeName()=" + getResumeName() + ", getResumeSex()="
				+ getResumeSex() + ", getResumeBirthday()=" + getResumeBirthday() + ", getResumePhone()="
				+ getResumePhone() + ", getResumeEmail()=" + getResumeEmail() + ", getResumeAddress()="
				+ getResumeAddress() + ", getResumeGraduateInstitution()=" + getResumeGraduateInstitution()
				+ ", getResumeEducation()=" + getResumeEducation() + ", getResumeJobIntension()="
				+ getResumeJobIntension() + ", getResumeSelfEvaluation()=" + getResumeSelfEvaluation()
				+ ", getResumeWorkExperience()=" + getResumeWorkExperience() + ", getResumeWorkYears()="
				+ getResumeWorkYears() + ", getResumeCreateUser()=" + getResumeCreateUser() + ", getResumeCreateTime()="
				+ getResumeCreateTime() + ", getResumeUpdateUser()=" + getResumeUpdateUser()
				+ ", getResumeUpdateTime()=" + getResumeUpdateTime() + ", getResumeDeleteFlag()="
				+ getResumeDeleteFlag() + ", toString()=" + super.toString() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + "]";
	}
	
}
