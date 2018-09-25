package com.qc.rc.dao;

import org.apache.ibatis.annotations.Param;
import com.qc.rc.entity.Pic;


public interface PicMapper {
	
	int resumeAddPic(@Param("picId")int picId, @Param("resumeId")int resumeId, @Param("pCreateUser")String pCreateUser, @Param("pPic")String pPic);
	
	/*int resumeAddPic(Pic pic);*/

	int resumeUpdatePic(Pic pic);
}
