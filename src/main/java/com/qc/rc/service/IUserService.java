package com.qc.rc.service;

import com.qc.rc.common.ServerResponse;
import com.qc.rc.entity.User;

public interface IUserService {
	
	//登录
	public ServerResponse<User> login(String account,String password);
	
	//注册
	public int userRegister(User user);
	
}
