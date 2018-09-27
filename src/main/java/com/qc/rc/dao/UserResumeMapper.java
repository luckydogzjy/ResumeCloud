package com.qc.rc.dao;

import org.apache.ibatis.annotations.Param;
import com.qc.rc.entity.UserResume;


public interface UserResumeMapper {
	
	int resumeAddResumeUser(@Param("userResumeId")int userResumeId, @Param("userId")int userId,@Param("resumeId") int resumeId);
	
	int SharingInsertUserResume(UserResume userResume);
	
}
