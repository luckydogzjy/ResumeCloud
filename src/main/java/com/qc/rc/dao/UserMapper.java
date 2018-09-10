package com.qc.rc.dao;

import com.qc.rc.entity.User;
import java.math.BigDecimal;

import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    int deleteByPrimaryKey(BigDecimal userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(BigDecimal userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    User login(@Param("userAccount") String userAccount);
}