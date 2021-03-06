package com.qc.rc.service.impl;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qc.rc.common.PageMessage;
import com.qc.rc.common.ServerResponse;
import com.qc.rc.dao.InterviewMapper;
import com.qc.rc.entity.Resume;
import com.qc.rc.entity.pojo.InterviewPojo;
import com.qc.rc.service.InterviewService;
import com.qc.rc.utils.InterviewDateUtil;

@Service("iInterviewService")
public class InterviewServiceImpl implements InterviewService {

	@Autowired
	private InterviewMapper interviewMapper;
	 /**
     * @param pageNum 页码
     * @param userId 用户id
     * @param startTime 起始时间
     * @param overTime 终止时间
     * @param interviewJob 面试职位
     * @param interviewInfo 姓名，备注信息，面试地址
     * @param sort 时间升降排序
     * 
     */
	public PageInfo<InterviewPojo> selectByCondition(Integer pageNum, Integer userId, String startTime,
			String overTime, String interviewJob, String interviewInfo,Integer sort) throws ParseException {
		Date st = null;
		Date ot = null;

		if (StringUtils.isNotBlank(startTime)) {
			st = InterviewDateUtil.strToDate(startTime);
		}
		if (StringUtils.isNotBlank(overTime)) {
			ot  = InterviewDateUtil.strToDate(overTime);
		}
		
		
		 /**
	     * 包装Page对象
	     *
	     * @param list          page结果
	     * @param navigatePages 页码数量
	     */
		PageHelper.startPage(pageNum,PageMessage.interviewpageSize);
		
		List<InterviewPojo> interviewPojos = interviewMapper.selectByCondition(userId, st, ot, interviewJob, interviewInfo,sort);

		PageInfo<InterviewPojo> pageResumePojo = new PageInfo<InterviewPojo>(interviewPojos,5);
		
		return pageResumePojo;
		
	}
	
	public Integer addInterview(InterviewPojo iPojo,Resume resume,Integer userId) {
		iPojo.setResume(resume);
		iPojo.setInterviewCreateTime(new Date());
//		新建时设置状态为待面试
		iPojo.setInterviewStatus(2);
//		设置deleteflag为0
		iPojo.setInterviewDeleteFlag(0);
		iPojo.setInterviewUserId(userId);
//		应从缓存中获取username
		iPojo.setInterviewCreateUser("LING");
		return	interviewMapper.addInterview(iPojo);
	}

	public Integer deleteInterview(Integer interviewId){
		InterviewPojo iPojo = new InterviewPojo();
		iPojo.setInterviewId(interviewId);
		iPojo.setInterviewUpdateUser("LING");
		return interviewMapper.deleteInterview(iPojo);
	}
	


	public ServerResponse<InterviewPojo> getInterviewByResumeId(Integer ResumeId) {
		
		InterviewPojo interviewPojo = interviewMapper.selectInterviewsByResumeId(ResumeId);
		
		if(interviewPojo==null){
			return ServerResponse.createByErrorMessage("参数错误");
		}
		return ServerResponse.createBySuccess(interviewPojo);
	}

}
