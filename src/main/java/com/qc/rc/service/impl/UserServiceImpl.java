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
	@Autowired
	User user;

	@Override
	public User findUserByPhone(String userPhone, String password) {
		
		//System.out.println("Service层:"+userPhone);
		//System.out.println("Service层:"+password);		
	    user = userMapper.findUserByPhone(userPhone);
	    System.out.println(user.toString());
		
        if (user != null && user.getUserPassword().equals(password)) {
        	
            return user;
        }
        return null;
	}

	@Override
	public List<RechargeRecord> findRecord(String currentId) {
		
		List<RechargeRecord> rechargeList=userMapper.findRecord(currentId);
		return rechargeList;
	}

	@Override
	public int userRegister(User user) {
		
		//System.out.println(user.toString());
		return userMapper.insertNewUser(user);
		
	}

	@Override
	public List<SharingCenterPojo> getPersonShareResume(String currentId) {
		
		//System.out.println("////////////" + currentId);
		List<SharingCenterPojo> personShareResumeList =userMapper.getPersonShareResume(currentId);
		
	//	List<SharingCenterPojo> personShareResumeList =userMapper.getPersonShareResume();
			
		//System.out.println(personShareResumeList.size());	
			
		return personShareResumeList;	
	}

	@Override
	public void deleteRecharge(String rrId) {
		
		userMapper.deleteRecharge(rrId);
		
	}


	@Override
	public void deleteShareById(String scId) {
		
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


