package com.qc.rc.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qc.rc.dao.PersonalMapper;
import com.qc.rc.entity.User;
import com.qc.rc.entity.pojo.ResumePojo;
import com.qc.rc.entity.pojo.UserExchangeResumePojo;
import com.qc.rc.service.PersonalService;

@Service("PersonalService")
public class PersonalServiceImpl implements PersonalService {
	
	@Autowired
	private PersonalMapper personalMapper;
	private static Integer pageShow = 8;
	
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
	
	
		public List<User> getUserInfo(String userId) {
		
		List<User> list = personalMapper.getUserInfo(userId);
		
//		System.out.println("ResumeServiceImpl:::" + list.size());
		
		return list;
	}
		
		public int updateInfo(User user){
			
			int result = personalMapper.updateUser(user);
			return result;
			
		}
		
		
//		//获取积分兑换的简历
//		public List<UserExchangeResumePojo> getAllExchangResume(String userId,Integer page){
//			PageHelper.startPage(page,pageShow);	
//			List<UserExchangeResumePojo> exchangeResumeList= personalMapper.getAllExchangResume(userId);
//			PageInfo<UserExchangeResumePojo> pageInfo = new PageInfo<UserExchangeResumePojo>(exchangeResumeList);
//			
//			Map<String,Object> model = new HashMap<String,Object>(); 
//			model.put("page", pageInfo);
//			return exchangeResumeList;
//			
//		}
//


		@Override
		public Map<String, Object> getAllExchangResume(String userId,UserExchangeResumePojo userExchangeResumePojo, Integer page) {
			PageHelper.startPage(page,pageShow);	
			
			List<UserExchangeResumePojo> list = personalMapper.getAllExchangResume(userId, userExchangeResumePojo);
			
			PageInfo<UserExchangeResumePojo> pageInfo = new PageInfo<UserExchangeResumePojo>(list);
			
			Map<String,Object> model = new HashMap<String,Object>(); 
			model.put("resumeList", list);
			model.put("page", pageInfo);
			//返回查询条件
			model.put("search", userExchangeResumePojo);
			
			return model;
		}

}
