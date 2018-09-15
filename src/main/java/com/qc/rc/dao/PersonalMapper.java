package com.qc.rc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qc.rc.entity.Resume;
import com.qc.rc.entity.User;


public interface PersonalMapper {
	
	int  updataPassword(User user);
	
	//初次加载页面显示信息
	List<User> getUserInfo(@Param("userId")Integer userId);
	
	int updateUser(User user);

}
