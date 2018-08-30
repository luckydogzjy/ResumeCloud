package com.qc.rc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qc.rc.common.ServerResponse;
import com.qc.rc.dao.UserMapper;
import com.qc.rc.entity.User;
import com.qc.rc.service.IUserService;


@Service("iUserService")
public class UserServiceImpl implements IUserService {
	
	@Autowired
	private UserMapper userMapper;

	public ServerResponse<User> login(String account,String password){
		// TODO Auto-generated method stub
		User user = userMapper.login(account);
		if(user==null) {
			return ServerResponse.createByErrorMessage("用户名不存在！");
		}
		if(account.equals(user.getUserAccount())&&password.equals(user.getUserPassword())) {
			return ServerResponse.createBySuccess("登录成功",user);
		}else {
			return ServerResponse.createByErrorMessage("密码错误");
		}
	}

	public int userRegister(User user) {
		
		int resultcount = userMapper.insert(user);
		
		
		return resultcount;
	}


}
