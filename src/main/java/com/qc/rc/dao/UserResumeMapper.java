package com.qc.rc.dao;

import org.apache.ibatis.annotations.Param;
import com.qc.rc.entity.UserResume;


public interface UserResumeMapper {
	
	int resumeAddResumeUser(@Param("userResumeId")String userResumeId, @Param("userId")String userId,@Param("resumeId") String resumeId);
	
	int SharingInsertUserResume(UserResume userResume);
	
}
