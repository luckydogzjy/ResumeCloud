package com.qc.rc.service;

import java.util.List;

import com.qc.rc.entity.User;
import com.qc.rc.entity.pojo.ResumePojo;

public interface PersonalService {
	
//	public boolean isUserExist(String userPhone);
//	public String getPasswordByUserphone(String userPhone);
	
	public int passwordUpdate(User user);

//	public List<User> getUserInfo(Integer userId);

	public List<User> getUserInfo(Integer userId);
	
	public int updateInfo(User user);
}
