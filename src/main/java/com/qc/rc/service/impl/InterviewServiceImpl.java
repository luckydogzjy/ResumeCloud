package com.qc.rc.service.impl;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qc.rc.common.PageBean;
import com.qc.rc.common.PageMessage;
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
//		��ѯ���������������ֶ���
		ipPageBean.setAllSize(size);
//		����ҳ������ҳ����Ϊ1
		ipPageBean.setPageNum(1);
//		����ÿҳҪ��ʾ������
		ipPageBean.setPageSize(PageMessage.interviewpageSize);
//		������������
		ipPageBean.setOffset();
//		��������ҳ��
		ipPageBean.setAllPage();
        RowBounds rb = new RowBounds(ipPageBean.getOffset(), ipPageBean.getPageSize());  
//      ��������
		List<InterviewPojo> interviewPojos= interviewMapper.selectAllInterviewsByUserId(rb, userId);
		ipPageBean.setDatalist(interviewPojos);
		
		return ipPageBean;
	}
	
	public PageBean<InterviewPojo> selectByCondition(Integer pageNum, Integer userId, Date startTime,
			Date overTime, String interviewJob, String interviewInfo) {
		
		Integer size = interviewMapper.getAllInterviewsByCondition(userId, startTime, overTime, interviewJob, interviewInfo);
		PageBean<InterviewPojo> ipPageBean = new PageBean<>();
//		��ѯ���������������ֶ���
		ipPageBean.setAllSize(size);
//		����ҳ������ҳ����Ϊ1
		ipPageBean.setPageNum(pageNum);
//		����ÿҳҪ��ʾ������
		ipPageBean.setPageSize(PageMessage.interviewpageSize);
//		������������
		ipPageBean.setOffset();
//		��������ҳ��
		ipPageBean.setAllPage();
        RowBounds rb = new RowBounds(ipPageBean.getOffset(), ipPageBean.getPageSize());  
//      ��������
		List<InterviewPojo> interviewPojos= interviewMapper.selectByCondition(rb, userId, startTime, overTime, interviewJob, interviewInfo);
		ipPageBean.setDatalist(interviewPojos);
		
		return ipPageBean;
	}

}
