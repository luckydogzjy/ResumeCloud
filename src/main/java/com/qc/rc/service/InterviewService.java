package com.qc.rc.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.qc.rc.common.PageBean;
import com.qc.rc.entity.Interview;
import com.qc.rc.entity.pojo.InterviewPojo;

public interface InterviewService {
/*//	閺屻儴顕楅幍锟介張澶庝捍娴ｏ拷
	public ArrayList<Job> showAllJob();
//	閺嶈宓乯ob閼惧嘲绶遍惄绋垮彠resume
	public ArrayList<Resume> getResumeByJob(Job job);
//	閺屻儴顕楅幍锟介張澶愭桨鐠囨洖鐣ㄩ幒锟�
	public ArrayList<InterviewManage> getAllInterviewManages(int pageNum ,int pageSize);
//	閺傛澘顤冩稉锟介弶锟犳桨鐠囨洖鐣ㄩ幒锟� 
	public boolean createNewInterviewManage(int resumeId,int jobId,String interviewTime, String interviewInfo);
	
	//閺囧瓨鏌婃稉锟介弶锟犳桨鐠囨洖鐣ㄩ幒锟�
	public boolean updateInterviewManageService(InterviewManage interviewManage,String interviewTime,Job job,String result,String info);

//	閸掔娀娅庢稉锟介弶锟犳桨鐠囨洖鐣ㄩ幒锟�
	public boolean deleteInterviewManageService(InterviewManage interviewManage);
	
//	閺屻儲澹橀弰顖氭儊瀹歌尙绮＄�瑰甯撻敍宀冪箲閸ユ勘nterviewManage
	public InterviewManage getInterviewManageByRJService(Resume resume,Job job); 
	
//	閺堝娼禒鑸电叀鐠囷拷
	public ArrayList<InterviewManage> selectInterviewByConditionService(InterviewManage interviewManage, String reName,String jName);*/

	
//	根据userid查找当前用户所有的interview 返回pageBean
	public PageBean<InterviewPojo> getAllInterviews(Integer userId);

//	根据条件和userid查找当前用户所有的interview 返回pageBean
	public PageBean<InterviewPojo> selectByCondition(Integer pageNum,Integer userId,
					Date startTime,Date overTime,String interviewJob,String interviewInfo);

}
