package com.qc.rc.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.collect.Lists;
import com.qc.rc.service.IPictureService;
import com.qc.rc.utils.COSUtil;


@Service("iPictureService")
public class PictureServiceImpl implements IPictureService{
	
	private Logger logger = LoggerFactory.getLogger(PictureServiceImpl.class);
	
	
	//上传广告图片
    public String upload(MultipartFile file, String path){
    	//获取上传文件的原名
        String fileName =file.getOriginalFilename();
        if(StringUtils.isBlank(fileName)){
        	
        	return null;
        }
        
        //获取文件扩展名
        String fileExtensionName = fileName.substring(fileName.lastIndexOf(".")+1);
        if(StringUtils.isBlank(fileExtensionName)){
        	
        	return null;
        }
        
        //使用UUID生成新的文件名
        String uploadFileName = UUID.randomUUID().toString()+"."+fileExtensionName;
        
        logger.info("开始上传文件",fileName,path,uploadFileName);

        File fileDir = new File(path);
        if(!fileDir.exists()){
            fileDir.setWritable(true);
            fileDir.mkdirs();
        }
        File targetFile = new File(path,uploadFileName);

        try {
            file.transferTo(targetFile);
            //文件已经上传成功到upload

            COSUtil.uploadFile(Lists.newArrayList(targetFile));
            //已经上传到Cos服务器上

            targetFile.delete();

        } catch (IOException e) {
            logger.error("上传文件异常",e);
            return "error";
        } catch (Exception e){
        	 logger.error("上传文件出错",e);
        	 return "error";
        }
    	
    	return targetFile.getName();
        
//        return targetFile.getName();
    }   
}
