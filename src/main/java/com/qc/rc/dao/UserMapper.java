package com.qc.rc.dao;

import java.util.List;

import com.qc.rc.entity.RechargeRecord;
import com.qc.rc.entity.User;


public interface UserMapper {

	User findUserByPhone(String userPhone);
	List<RechargeRecord> findRecord();
	void deleteRecharge(Integer rrId);
	int insertNewUser(User user);
}