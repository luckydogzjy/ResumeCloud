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

	public InterviewPojo(Integer interviewId, Integer interviewResumeId, String interviewJob, Date interviewTime,
			String interviewAssociateUsername, String interviewAssociatePhone, String interviewAddress,
			String interviewInfo, Integer interviewStatus, String interviewRecodeInfo, String interviewCreateUser,
			Date interviewCreateTime, String interviewUpdateUser, Date interviewUpdateTime, Integer interviewDeleteFlag,
			Integer interviewUserId, Resume resume) {
		super(interviewId, interviewResumeId, interviewJob, interviewTime, interviewAssociateUsername,
				interviewAssociatePhone, interviewAddress, interviewInfo, interviewStatus, interviewRecodeInfo,
				interviewCreateUser, interviewCreateTime, interviewUpdateUser, interviewUpdateTime, interviewDeleteFlag,
				interviewUserId);
		this.resume = resume;
	}

	public InterviewPojo() {

	}
	
	
}
