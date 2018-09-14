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
import com.qc.rc.entity.Interview;
import com.qc.rc.entity.pojo.InterviewPojo;
import com.qc.rc.service.InterviewService;

@Service("iInterviewService")
public class InterviewServiceImpl implements InterviewService {

	@Autowired
	private InterviewMapper interviewMapper;
		
	public PageBean<InterviewPojo> getAllInterviews(Integer userId) {
		Integer size = interviewMapper.getAllInterviewsByUserId(userId);
		PageBean<InterviewPojo> ipPageBean = new PageBean<>();
//		查询满足条件的所有字段数
		ipPageBean.setAllSize(size);
//		进入页面设置页码数为1
		ipPageBean.setPageNum(1);
//		设置每页要显示的数量
		ipPageBean.setPageSize(PageMessage.interviewpageSize);
//		设置跳过行数
		ipPageBean.setOffset();
//		计算所有页数
		ipPageBean.setAllPage();
        RowBounds rb = new RowBounds(ipPageBean.getOffset(), ipPageBean.getPageSize());  
//      载入数据
		List<InterviewPojo> interviewPojos= interviewMapper.selectAllInterviewsByUserId(rb, userId);
		ipPageBean.setDatalist(interviewPojos);
		
		return ipPageBean;
	}
	
	public PageBean<InterviewPojo> selectByCondition(Integer pageNum, Integer userId, Date startTime,
			Date overTime, String interviewJob, String interviewInfo) {
		
		Integer size = interviewMapper.getAllInterviewsByCondition(userId, startTime, overTime, interviewJob, interviewInfo);
		PageBean<InterviewPojo> ipPageBean = new PageBean<>();
//		查询满足条件的所有字段数
		ipPageBean.setAllSize(size);
//		进入页面设置页码数为1
		ipPageBean.setPageNum(pageNum);
//		设置每页要显示的数量
		ipPageBean.setPageSize(PageMessage.interviewpageSize);
//		设置跳过行数
		ipPageBean.setOffset();
//		计算所有页数
		ipPageBean.setAllPage();
        RowBounds rb = new RowBounds(ipPageBean.getOffset(), ipPageBean.getPageSize());  
//      载入数据
		List<InterviewPojo> interviewPojos= interviewMapper.selectByCondition(rb, userId, startTime, overTime, interviewJob, interviewInfo);
		ipPageBean.setDatalist(interviewPojos);
		
		return ipPageBean;
	}
	
	public void addInterview(InterviewPojo interviewPojo) {
		interviewMapper.addInterview(interviewPojo);
	}

	public void deleteInterview(Integer interviewId){
		interviewMapper.deleteInterview(interviewId);
	}
	


	public ServerResponse<InterviewPojo> getInterviewByResumeId(Integer ResumeId) {
		
		InterviewPojo interviewPojo = interviewMapper.selectInterviewsByResumeId(ResumeId);
		
		if(interviewPojo==null){
			return ServerResponse.createByErrorMessage("参数错误");
		}
		return ServerResponse.createBySuccess(interviewPojo);
	}	
}
