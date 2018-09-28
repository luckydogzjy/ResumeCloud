package com.qc.rc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qc.rc.dao.UserMapper;
import com.qc.rc.entity.RechargeRecord;
import com.qc.rc.entity.SharingCenter;
import com.qc.rc.entity.User;
import com.qc.rc.entity.pojo.SharingCenterPojo;
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
		
		System.out.println(user.toString());
		return userMapper.insertNewUser(user);
		
	}

	@Override
	public List<SharingCenterPojo> getPersonShareResume(Integer currentId) {
		
		//System.out.println("////////////" + currentId);
		List<SharingCenterPojo> personShareResumeList =userMapper.getPersonShareResume(currentId);
		
	//	List<SharingCenterPojo> personShareResumeList =userMapper.getPersonShareResume();
			
		//System.out.println(personShareResumeList.size());	
			
		return personShareResumeList;	
	}

	@Override
	public void deleteRecharge(Integer rrId) {
		
		userMapper.deleteRecharge(rrId);
		
	}


	@Override
	public void deleteShareById(Integer scId) {
		
		userMapper.deleteShareById(scId);
		
	}
	
	/**
	 * 查询手机号是否注册
	 */
	@Override
	public boolean findUserIdByPhone(String userPhone) {
		
		boolean flag = false;
		if(userMapper.findUserByPhone(userPhone) != null){
			flag=true;
		}
		return flag;
	}

	@Override
	public User findUserByPhone(String inputPhone) {
		
		return userMapper.findUserByPhone(inputPhone);
	}
	
}


