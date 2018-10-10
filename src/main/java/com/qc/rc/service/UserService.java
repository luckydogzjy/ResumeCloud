package com.qc.rc.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qc.rc.entity.RechargeRecord;
import com.qc.rc.entity.SharingCenter;
import com.qc.rc.entity.User;
import com.qc.rc.entity.pojo.SharingCenterPojo;

public interface UserService {
	
	/**
	 * 账号密码登录
	 * @param userPhone
	 * @param password
	 * @return
	 */
	public User findUserByPhone(String userPhone,String password);
	
	/**
	 * 查询手机号是否注册
	 * @param userPhone
	 * @return
	 */
	public boolean findUserIdByPhone(String userPhone);
	
	/**
	 * 查询手机号是否注册，并返回User
	 * @param inputPhone
	 * @return
	 */
	public User findUserByPhone(String inputPhone);
	
	
	/**
	 * 查询积分充值记录
	 * @return
	 */
	public List<RechargeRecord> findRecord(String currentId);
	
	/**
	 * 注册
	 * @param user
	 * @return
	 */
	public int userRegister(User user);//注册
	/**
	 * 删除充值记录
	 * @param rrId
	 */
	public void deleteRecharge(String rrId);
	
	/**
	 * 查询个人共享简历
	 * @param currentId
	 * @return
	 */
	public List<SharingCenterPojo> getPersonShareResume(String currentId);
	
	/**
	 * 
	 * @param scId
	 */
	public void deleteShareById(String scId);
}
