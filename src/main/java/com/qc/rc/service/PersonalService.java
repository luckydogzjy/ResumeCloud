package com.qc.rc.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qc.rc.entity.User;
import com.qc.rc.entity.pojo.ResumePojo;
import com.qc.rc.entity.pojo.UserExchangeResumePojo;

public interface PersonalService {
	
//	public boolean isUserExist(String userPhone);
//	public String getPasswordByUserphone(String userPhone);
	
	
	public int passwordUpdate(User user);

//	public List<User> getUserInfo(Integer userId);
	//个人中心显示个人信息
	public List<User> getUserInfo(String userId);
	//修改个人中心信息
	public int updateInfo(User user);
	//显示积分兑换的简历
	public List<UserExchangeResumePojo> getAllExchangResume(String userId);
	
}
