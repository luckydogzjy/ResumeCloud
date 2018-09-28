package com.qc.rc.entity;


import java.util.Date;

import org.springframework.stereotype.Component;




/*CREATE TABLE "RC_USER" 
(	"USER_ID" NUMBER(6,0), 
	"USER_PHONE" VARCHAR2(11 BYTE), 
	"USER_PASSWORD" VARCHAR2(20 BYTE), 
	"USER_NAME" VARCHAR2(20 BYTE), 
	"USER_SEX" NUMBER(1,0), 
	"USER_COMPANY" VARCHAR2(50 BYTE), 
	"USER_BIRTHDAY" DATE, 
	"USER_PIC" BLOB, 
	"USER_INTEGRAL" NUMBER(10,0), 
	"CREATE_TIME" DATE DEFAULT sysdate, 
	"UPDATE_TIME" DATE, 
	"USER_STATUS" NUMBER(1,0) DEFAULT 0*/

@Component
public class User {
	
	public User(String userId, String userPhone, String userPassword, String userName, Integer userSex,
			String userCompany, Date userBirthday, String userPic, Integer userIntegral, Date createTime,
			Date updateTime) {
		super();
		this.userId = userId;
		this.userPhone = userPhone;
		this.userPassword = userPassword;
		this.userName = userName;
		this.userSex = userSex;
		this.userCompany = userCompany;
		this.userBirthday = userBirthday;
		this.userPic = userPic;
		this.userIntegral = userIntegral;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}

	public User() {
        super();
        // TODO Auto-generated constructor stub
    }
	private String userId;
	private String userPhone;
	private String userPassword;
	private String userName;
	private Integer userSex;
	private String userCompany;
	private Date userBirthday;
	private String userPic;
	private Integer userIntegral;
	private Date createTime;
	private Date updateTime;	
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Integer getUserSex() {
		return userSex;
	}
	public void setUserSex(Integer userSex) {
		this.userSex = userSex;
	}
	public String getUserCompany() {
		return userCompany;
	}
	public void setUserCompany(String userCompany) {
		this.userCompany = userCompany;
	}
	public Date getUserBirthday() {
		return userBirthday;
	}
	public void setUserBirthday(Date userBirthday) {
		this.userBirthday = userBirthday;
	}
	public String getUserPic() {
		return userPic;
	}
	public void setUserPic(String userPic) {
		this.userPic = userPic;
	}
	public Integer getUserIntegral() {
		return userIntegral;
	}
	public void setUserIntegral(Integer userIntegral) {
		this.userIntegral = userIntegral;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
		

}
