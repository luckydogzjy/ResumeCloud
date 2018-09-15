package com.qc.rc.service;

import java.util.List;

import com.qc.rc.entity.RechargeRecord;
import com.qc.rc.entity.User;

public interface UserService {
	
	
	public User findUserByPhone(String userPhone,String password);//验证登录
	public List<RechargeRecord> findRecord();	
	public int userRegister(User user);//注册
	//public void deleteRecharge(Integer rrId);
	
}
