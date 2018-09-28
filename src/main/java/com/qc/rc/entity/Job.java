package com.qc.rc.entity;

import java.util.Date;

import org.springframework.stereotype.Component;


public class Job {
	
	private Integer JOB_ID;
	private Integer JOB_USER_ID;
	private String JOB_NAME;
	private Integer JOB_COUNT;
	private Integer JOB_SALARY;
	private String JOB_INTRODUCTION;
	private String JOB_CONDITION;
	private short JOB_STATUS;
	private Date JOB_CREATE_TIME;
	private Date JOB_END_TIME;
	
	public Integer getJOB_ID() {
		return JOB_ID;
	}
	public void setJOB_ID(Integer jOB_ID) {
		JOB_ID = jOB_ID;
	}
	public Integer getJOB_USER_ID() {
		return JOB_USER_ID;
	}
	public void setJOB_USER_ID(Integer jOB_USER_ID) {
		JOB_USER_ID = jOB_USER_ID;
	}
	public String getJOB_NAME() {
		return JOB_NAME;
	}
	public void setJOB_NAME(String jOB_NAME) {
		JOB_NAME = jOB_NAME;
	}
	public Integer getJOB_COUNT() {
		return JOB_COUNT;
	}
	public void setJOB_COUNT(int jOB_COUNT) {
		JOB_COUNT = jOB_COUNT;
	}
	public Integer getJOB_SALARY() {
		return JOB_SALARY;
	}
	public void setJOB_SALARY(int jOB_SALARY) {
		JOB_SALARY = jOB_SALARY;
	}
	public String getJOB_INTRODUCTION() {
		return JOB_INTRODUCTION;
	}
	public void setJOB_INTRODUCTION(String jOB_INTRODUCTION) {
		JOB_INTRODUCTION = jOB_INTRODUCTION;
	}
	public String getJOB_CONDITION() {
		return JOB_CONDITION;
	}
	public void setJOB_CONDITION(String jOB_CONDITION) {
		JOB_CONDITION = jOB_CONDITION;
	}
	public short getJOB_STATUS() {
		return JOB_STATUS;
	}
	public void setJOB_STATUS(short jOB_STATUS) {
		JOB_STATUS = jOB_STATUS;
	}
	public Date getJOB_CREATE_TIME() {
		return JOB_CREATE_TIME;
	}
	public void setJOB_CREATE_TIME(Date jOB_CREATE_TIME) {
		JOB_CREATE_TIME = jOB_CREATE_TIME;
	}
	public Date getJOB_END_TIME() {
		return JOB_END_TIME;
	}
	public void setJOB_END_TIME(Date jOB_END_TIME) {
		JOB_END_TIME = jOB_END_TIME;
	}
	
	
}
