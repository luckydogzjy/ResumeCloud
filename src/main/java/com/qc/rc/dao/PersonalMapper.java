package com.qc.rc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qc.rc.entity.Resume;
import com.qc.rc.entity.User;
import com.qc.rc.entity.pojo.UserExchangeResumePojo;


public interface PersonalMapper {
	
	//修改密码
	int  updataPassword(User user);
	
	//初次加载页面显示信息
	List<User> getUserInfo(@Param("userId")Integer userId);
	
	//修改个人中心信息
	int updateUser(User user);
	
	//兑换的简历信息
	List<UserExchangeResumePojo> getAllExchangResume(@Param("userId")Integer userId);
	
}
