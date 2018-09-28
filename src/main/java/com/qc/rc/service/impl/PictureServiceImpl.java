package com.qc.rc.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.collect.Lists;
import com.qc.rc.common.ServerResponse;
import com.qc.rc.service.IPictureService;
import com.qc.rc.utils.COSUtil;


@Service("iPictureService")
public class PictureServiceImpl implements IPictureService{
	
	private Logger logger = LoggerFactory.getLogger(PictureServiceImpl.class);
	
	
	//上传广告图片
    public String upload(MultipartFile file, String path){
    	//获取上传文件的原名
        String fileName =file.getOriginalFilename();
        
        //获取文件扩展名
        String fileExtensionName = fileName.substring(fileName.lastIndexOf(".")+1);
        
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
            return null;
        }
        
        
        //传数据库写这里
        System.out.println(targetFile.getName());
        return targetFile.getName();
    }


	@Override
	public String getPic(Integer PicId) throws IOException {
         String key = "08e1883b-dccc-4e22-9999-a3b70bdc09bb.jpg";
         String image = "https://kbase-1256168134.file.myqcloud.com/"+key;
         return image;
	}
}
