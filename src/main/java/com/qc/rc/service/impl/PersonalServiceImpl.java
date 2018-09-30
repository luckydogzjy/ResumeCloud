package com.qc.rc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qc.rc.dao.PersonalMapper;
import com.qc.rc.entity.User;
import com.qc.rc.entity.pojo.ResumePojo;
import com.qc.rc.entity.pojo.UserExchangeResumePojo;
import com.qc.rc.service.PersonalService;

@Service("PersonalService")
public class PersonalServiceImpl implements PersonalService {
	
	@Autowired
	private PersonalMapper personalMapper;
	
	
//	//判断用户是否存在
//	
//			public boolean isUserExist(String userPhone) {
//				if (personalMapper.login(userPhone) == null) {
//					return false;
//				} else {
//					return true;
//				}
//			}
//
//			
//			//根据提供的用户手机号拿密码
//				public String getPasswordByUserphone(String userPhone) {
//					return personalMapper.login(userPhone).getUserPassword();
//				}
	
	
	public int passwordUpdate(User user){
		
		int resultcount=personalMapper.updataPassword(user);
		return resultcount;
		
	}
	
	
		public List<User> getUserInfo(Integer userId) {
		
		List<User> list = personalMapper.getUserInfo(userId);
		
//		System.out.println("ResumeServiceImpl:::" + list.size());
		
		return list;
	}
		
		public int updateInfo(User user){
			
			int result = personalMapper.updateUser(user);
			return result;
			
		}
		
		
		//获取积分兑换的简历
		public List<UserExchangeResumePojo> getAllExchangResume(Integer userId){
			
			List<UserExchangeResumePojo> exchangeResumeList= personalMapper.getAllExchangResume(userId);
			
			return exchangeResumeList;
		}


		@Override
		public List<ResumePojo> getResumeListByCondition(Integer userId, String resumeName, String resumeJobIntension,
				Integer resumeSex, Integer resumeEducation, Integer resumeWorkYears, String resumeGraduateInstitution) {
			List<ResumePojo> list = personalMapper.getResumeListByCondition(userId,resumeName, resumeJobIntension, resumeSex, resumeEducation, resumeWorkYears, resumeGraduateInstitution);
			
			System.out.println("ResumeServiceImpl->getResumeListByCondition :::" + list.size());
			
			return list;
			
		}

}
