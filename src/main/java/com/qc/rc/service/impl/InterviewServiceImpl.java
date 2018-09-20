package com.qc.rc.service.impl;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qc.rc.common.PageBean;
import com.qc.rc.common.PageMessage;
import com.qc.rc.common.ServerResponse;
import com.qc.rc.common.Util;
import com.qc.rc.dao.InterviewMapper;
import com.qc.rc.dao.ResumeMapper;
import com.qc.rc.entity.Interview;
import com.qc.rc.entity.pojo.InterviewPojo;
import com.qc.rc.service.InterviewService;

@Service("iInterviewService")
public class InterviewServiceImpl implements InterviewService {

	@Autowired
	private InterviewMapper interviewMapper;
	
	@Autowired
	private ResumeMapper resumeMapper;


	public ServerResponse<InterviewPojo> getInterviewByResumeId(Integer ResumeId) {
		
		InterviewPojo interviewPojo = interviewMapper.selectInterviewsByResumeId(ResumeId);
		
		if(interviewPojo==null){
			return ServerResponse.createByErrorMessage("参数错误");
		}
		return ServerResponse.createBySuccess(interviewPojo);
	}	
	
	public ServerResponse updateInterviewsByResumeId(InterviewPojo interviewPojo,Integer ResumeId,String resumePhone){
		
		int InterviewResultCount = interviewMapper.updateInterviewsByResumeId(interviewPojo);
		
		int ResumeResultCount = resumeMapper.updateResumePhone(ResumeId,resumePhone);
		
		if(InterviewResultCount>0&&ResumeResultCount>0){
			return ServerResponse.createBySuccess("更新成功");
		}else{
			return ServerResponse.createByErrorMessage("更新失败");
		}	

	}
}
