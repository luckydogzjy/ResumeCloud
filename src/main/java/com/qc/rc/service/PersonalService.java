package com.qc.rc.service;

import java.util.List;
import java.util.Map;



import com.qc.rc.entity.User;
import com.qc.rc.entity.pojo.UserExchangeResumePojo;
public interface PersonalService {
	
//	public boolean isUserExist(String userPhone);
//	public String getPasswordByUserphone(String userPhone);
	
	//修改密码
	public int passwordUpdate(User user);

//	public List<User> getUserInfo(Integer userId);
	//个人中心显示个人信息
	public List<User> getUserInfo(String userId);
	//修改个人中心信息
	public int updateInfo(User user);
	//显示积分兑换的简历
//	public  Map<String, Object> getAllExchangResume(String userId,Integer page);
	//条件查询返回简历list
	public Map<String, Object> getAllExchangResume(String userId,UserExchangeResumePojo userExchangeResumePojo,Integer page);
		
	
}
