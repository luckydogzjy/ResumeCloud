package com.qc.rc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qc.rc.entity.RechargeRecord;
import com.qc.rc.entity.User;
import com.qc.rc.entity.pojo.SharingCenterPojo;


public interface UserMapper {
	
	//登录
	User findUserByPhone(String userPhone);
	
	
	//查询积分充值记录
	List<RechargeRecord> findRecord(String currentId);
	
	//删除充值记录
	void deleteRecharge(String rrId);
	
	//注册，返回id
	int insertNewUser(User user);
	
	//查询个人共享记录
	List<SharingCenterPojo> getPersonShareResume(@Param("currentId")String currentId);
	
	//删除个人共享记录
	void deleteShareById(String scId);

	
}