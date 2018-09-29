package com.qc.rc.utils;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.exception.CosClientException;
import com.qcloud.cos.exception.CosServiceException;
import com.qcloud.cos.http.HttpMethodName;
import com.qcloud.cos.model.*;
import com.qcloud.cos.region.Region;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.List;

/**
 * Created by Jayne
 * Data:2018/7/1
 * TIME:10:50
 */
public  class COSUtil {

    private static final Logger logger = LoggerFactory.getLogger(COSUtil.class);

    private static String secretId = "AKIDqpVIA4DOrImJsUivT6kP3gxJDBCE8OA7";
    private static String secretKey = "MvaJ5umL1ofM3vBXXb4b8iKZCMSQD6rc";
    private static String bucketPlace = "ap-beijing";
    private static String bucketName = "kbase-1256168134";


    public static boolean uploadFile(List<File> fileList)throws IOException {
        COSUtil cosUtil = new COSUtil();
        logger.info("开始连接cos服务器");
        cosUtil.getCOSClient();
        boolean result = cosUtil.upload(fileList);
        logger.info("上传完成");
        return result;
    }

    public static String getFile(String key)throws IOException {
        COSUtil cosUtil = new COSUtil();
        logger.info("开始连接cos服务器");
        cosUtil.getCOSClient();
        String result = cosUtil.generatePresignedUrl(key);
        logger.info("获取完成");
        return result;
    }

    //连接cos服务器
    private static  COSClient getCOSClient() {
        // 1 初始化用户身份信息(secretId, secretKey)
        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
        // 2 设置bucket的区域, COS地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
        ClientConfig clientConfig = new ClientConfig(new Region(bucketPlace));
        // 3 生成cos客户端
        COSClient cosclient = new COSClient(cred, clientConfig);

        return cosclient;
    }


    private boolean upload(List<File> fileList){

        boolean uploaded = true;
        FileInputStream fis = null;

        try {
            for (File fileItem : fileList) {
                fis = new FileInputStream(fileItem);
                PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, fileItem.getName(), fileItem);
                getCOSClient().putObject(putObjectRequest);
            }
        }catch (IOException e) {
            logger.error("上传文件异常", e);
            uploaded = false;
            e.printStackTrace();
        } finally{
            // 关闭COS连接
            getCOSClient().shutdown();
        }
        return uploaded;
    }




    public static String generatePresignedUrl(String key) throws CosClientException {
        // 生成一个下载链接
        // bucket 的命名规则为{name}-{appid} ，此处填写的存储桶名称必须为此格式
        GeneratePresignedUrlRequest req = new GeneratePresignedUrlRequest(bucketName, key, HttpMethodName.GET);
        // 设置签名过期时间(可选), 若未进行设置, 则默认使用 ClientConfig 中的签名过期时间(5分钟)
        // 这里设置签名在半个小时后过期
        Date expirationDate = new Date(System.currentTimeMillis() + 30L * 60L * 1000L);
        req.setExpiration(expirationDate);
        URL downloadUrl = getCOSClient().generatePresignedUrl(req);
        String downloadUrlStr = downloadUrl.toString();

        return  downloadUrlStr;
    }
}
