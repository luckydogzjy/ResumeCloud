package com.qc.rc.entity.pojo;

import java.util.Date;

import com.qc.rc.entity.Interview;
import com.qc.rc.entity.Resume;
import com.qc.rc.entity.User;

public class InterviewPojo extends Interview {

	private Resume resume;

	public Resume getResume() {
		return resume;
	}

	public void setResume(Resume resume) {
		this.resume = resume;
	}

	public InterviewPojo() {

	}

	@Override
	public String toString() {
		return "InterviewPojo [getInterviewId()="
				+ getInterviewId() + ", getInterviewResumeId()=" + getInterviewResumeId() + ", getInterviewJob()="
				+ getInterviewJob() + ", getInterviewTime()=" + getInterviewTime()
				+ ", getInterviewAssociateUsername()=" + getInterviewAssociateUsername()
				+ ", getInterviewAssociatePhone()=" + getInterviewAssociatePhone() + ", getInterviewAddress()="
				+ getInterviewAddress() + ", getInterviewInfo()=" + getInterviewInfo() + ", getInterviewStatus()="
				+ getInterviewStatus() + ", getInterviewRecodeInfo()=" + getInterviewRecodeInfo()
				+ ", getInterviewCreateUser()=" + getInterviewCreateUser() + ", getInterviewCreateTime()="
				+ getInterviewCreateTime() + ", getInterviewUpdateUser()=" + getInterviewUpdateUser()
				+ ", getInterviewUpdateTime()=" + getInterviewUpdateTime() + ", getInterviewDeleteFlag()="
				+ getInterviewDeleteFlag() + ", getInterviewUserId()=" + getInterviewUserId();
	}
	
	
}
