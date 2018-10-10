package com.qc.rc.common;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URLDecoder;
import java.util.HashMap;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.SimpleHttpConnectionManager;
import org.apache.commons.httpclient.URI;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpClientParams;

	 public class MessageSender {
		 /**
			 * 
			 * @param url 应用地址，类似于http://ip:port/msg/
			 * @param account 账号
			 * @param pswd 密码
			 * @param mobile 手机号码，多个号码使用","分割
			 * @param msg 短信内容
			 * @param needstatus 是否需要状态报告，需要true，不需要false
			 * @return 返回值定义参见HTTP协议文档
			 * @throws Exception
			 */
		 public static HashMap<String,String> batchSend(String mobile) throws Exception {
		
			 HttpClient client = new HttpClient(new HttpClientParams(), new SimpleHttpConnectionManager(true));
			 String url = "http://sapi.253.com/msg/";// 应用地址
				String account = "ddyueche";// 账号
				String pswd = "20171018qC!";// 密码
				boolean needstatus = true;// 是否需要状态报告，需要true，不需要false
				String extno = null;// 扩展码
				String code=createRandomVcode();//随机验证码
				String msg = "尊敬的HR~，您的验证码是"+ code; // 短信内容
				HashMap<String,String> m = new HashMap<String,String>();
			   
			 //创建提交方法实例
			 GetMethod method = new GetMethod();
			 try {
				 URI base = new URI(url, false);
				 method.setURI(new URI(base, "HttpBatchSendSM", false));
				 //用NameValuePair数组结构将参数对象封装,之后作为参数保存至method实例中
				 method.setQueryString(new NameValuePair[] { 
						 new NameValuePair("account", account),
						 new NameValuePair("pswd", pswd), 
						 new NameValuePair("mobile", mobile),
						 new NameValuePair("needstatus", String.valueOf(needstatus)), 
						 new NameValuePair("msg", msg),
						 new NameValuePair("extno", extno), 
				 });
				 
				 int result = client.executeMethod(method);
					m.put("result", Integer.toString(result));
					m.put("code", code);
				 return m;
			 } finally {
				 method.releaseConnection();
			 }
		 }
		 
		 public static  String createRandomVcode(){
		        //验证码
		        String vcode="";
		        for(int i = 0; i < 6; i++) {
		            vcode = vcode + (int)(Math.random() * 9);
		        }
		        return vcode;
		    }


	}