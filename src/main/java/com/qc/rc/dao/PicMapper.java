package com.qc.rc.dao;

import org.apache.ibatis.annotations.Param;
import com.qc.rc.entity.Pic;


public interface PicMapper {
	
	int resumeAddPic(@Param("picId")String picId, @Param("resumeId")String resumeId, @Param("pCreateUser")String pCreateUser, @Param("pPic")String pPic);
	
	/*int resumeAddPic(Pic pic);*/

	int resumeUpdatePic(Pic pic);

	int resumeUpdateAddPic(Pic pic);

	int deletePicById(String resumeId);
}
