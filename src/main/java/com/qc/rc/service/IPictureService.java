package com.qc.rc.service;

import org.springframework.web.multipart.MultipartFile;

public interface IPictureService {
	//上传图片
	String upload(MultipartFile file, String path);
}
