package com.qc.rc.entity;

import java.util.Date;

import javax.persistence.criteria.CriteriaBuilder.In;

public class RechargeRecord {
	

/*	  CREATE TABLE "RC_RECHARGE_RECORD" 
	   (	"RR_ID" NUMBER(6,0), 
		"RR_USER_ID" NUMBER(6,0), 
		"RR_ORDERNO" VARCHAR2(30 BYTE), 
		"RR_TYPE" NUMBER(1,0), 
		"RR_MONEY" NUMBER(5,0), 
		"RR_CREATE_TIME" DATE DEFAULT sysdate, 
		"RR_UPDATE_TIME" DATE, 
		"RR_DELETE_FLAG" NUMBER(1,0) DEFAULT 0
	   ) */
	private String RR_ID;//主键 自动生成 自增
	private String RR_USER_ID;//外键 RC_USER表USER_ID
	private String RR_ORDERNO;//订单号
	private Integer RR_TYPE;//充值类型1.微信。2支付宝
	private Integer RR_MONEY;//充值金额
	private Date RR_CREATE_TIME;
	private Date RR_UPDATE_TIME;
	private Integer RR_DELETE_FLAG;
	public String getRR_ID() {
		return RR_ID;
	}
	public void setRR_ID(String rR_ID) {
		RR_ID = rR_ID;
	}
	public String getRR_USER_ID() {
		return RR_USER_ID;
	}
	public void setRR_USER_ID(String rR_USER_ID) {
		RR_USER_ID = rR_USER_ID;
	}
	public String getRR_ORDERNO() {
		return RR_ORDERNO;
	}
	public void setRR_ORDERNO(String rR_ORDERNO) {
		RR_ORDERNO = rR_ORDERNO;
	}
	public Integer getRR_TYPE() {
		return RR_TYPE;
	}
	public void setRR_TYPE(Integer rR_TYPE) {
		RR_TYPE = rR_TYPE;
	}
	public Integer getRR_MONEY() {
		return RR_MONEY;
	}
	public void setRR_MONEY(Integer rR_MONEY) {
		RR_MONEY = rR_MONEY;
	}
	public Date getRR_CREATE_TIME() {
		return RR_CREATE_TIME;
	}
	public void setRR_CREATE_TIME(Date rR_CREATE_TIME) {
		RR_CREATE_TIME = rR_CREATE_TIME;
	}
	public Date getRR_UPDATE_TIME() {
		return RR_UPDATE_TIME;
	}
	public void setRR_UPDATE_TIME(Date rR_UPDATE_TIME) {
		RR_UPDATE_TIME = rR_UPDATE_TIME;
	}
	public Integer getRR_DELETE_FLAG() {
		return RR_DELETE_FLAG;
	}
	public void setRR_DELETE_FLAG(Integer rR_DELETE_FLAG) {
		RR_DELETE_FLAG = rR_DELETE_FLAG;
	}
	@Override
	public String toString() {
		return "RechargeRecord [RR_ID=" + RR_ID + ", RR_USER_ID=" + RR_USER_ID + ", RR_ORDERNO=" + RR_ORDERNO
				+ ", RR_TYPE=" + RR_TYPE + ", RR_MONEY=" + RR_MONEY + ", RR_CREATE_TIME=" + RR_CREATE_TIME
				+ ", RR_UPDATE_TIME=" + RR_UPDATE_TIME + ", RR_DELETE_FLAG=" + RR_DELETE_FLAG + "]";
	}


	

}
