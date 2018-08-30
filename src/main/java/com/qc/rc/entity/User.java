package com.qc.rc.entity;

import java.math.BigDecimal;
import java.util.Date;

public class User {
    private BigDecimal userId;

    private String userAccount;

    private String userName;

    private String userPassword;

    private Short userSex;

    private Short userAge;

    private Date createDate;

    private Date updateDate;

    private Short deteletFlag;

    public User(BigDecimal userId, String userAccount, String userName, String userPassword, Short userSex, Short userAge, Date createDate, Date updateDate, Short deteletFlag) {
        this.userId = userId;
        this.userAccount = userAccount;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userSex = userSex;
        this.userAge = userAge;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.deteletFlag = deteletFlag;
    }

    public User() {
        super();
    }

    public BigDecimal getUserId() {
        return userId;
    }

    public void setUserId(BigDecimal userId) {
        this.userId = userId;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount == null ? null : userAccount.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword == null ? null : userPassword.trim();
    }

    public Short getUserSex() {
        return userSex;
    }

    public void setUserSex(Short userSex) {
        this.userSex = userSex;
    }

    public Short getUserAge() {
        return userAge;
    }

    public void setUserAge(Short userAge) {
        this.userAge = userAge;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Short getDeteletFlag() {
        return deteletFlag;
    }

    public void setDeteletFlag(Short deteletFlag) {
        this.deteletFlag = deteletFlag;
    }
}