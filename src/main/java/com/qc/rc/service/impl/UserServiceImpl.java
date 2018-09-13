package com.qc.rc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qc.rc.dao.UserMapper;
import com.qc.rc.entity.RechargeRecord;
import com.qc.rc.entity.User;
import com.qc.rc.service.UserService;



@Service("UserService")
public  class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper userMapper;

	@Override
	public User findUserByPhone(String userPhone, String password) {
		
		User user = userMapper.findUserByPhone(userPhone);
		
        if (user != null && user.getUserPassword().equals(password)) {
        	
            return user;
        }
        return null;
	}

	@Override
	public List<RechargeRecord> findRecord() {
		List<RechargeRecord> rechargeList=userMapper.findRecord();
		return rechargeList;
	}

	@Override
	public int userRegister(User user) {
		
		return userMapper.insertNewUser(user);
		
	}





}


