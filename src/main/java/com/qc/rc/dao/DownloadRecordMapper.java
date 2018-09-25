package com.qc.rc.dao;

import org.apache.ibatis.annotations.Param;

public interface DownloadRecordMapper {
	
	public Integer insertDownloadRecord(@Param("id")Integer id,@Param("userId")Integer userId,@Param("sharingCenterId")Integer sharingCenterId);
}
