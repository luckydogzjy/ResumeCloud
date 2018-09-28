package com.qc.rc.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.qc.rc.common.ServerResponse;


public interface IPictureService {
	//上传图片
	String upload(MultipartFile file, String path);
	//获取图片
	String getPic(Integer PicId) throws IOException;
}
