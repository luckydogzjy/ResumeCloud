package com.qc.rc.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.qc.rc.common.ServerResponse;
import com.qc.rc.service.IPictureService;

@Controller
@RequestMapping("Picture/")
public class PictureController {
	
	@Autowired
	private IPictureService iPictureService;
	
	@RequestMapping(value ="upload.do",method = RequestMethod.POST)
	@ResponseBody
    public  ServerResponse upload(@RequestParam(value="upload_file" ,required = false) MultipartFile file,HttpServletRequest request){
       String path =request.getSession().getServletContext().getRealPath("upload");
       String msg = iPictureService.upload(file,path);
       return ServerResponse.createBySuccess("成功");
    }
	
	
	@RequestMapping(value = "get_pic.do",method = RequestMethod.GET)
	@ResponseBody
    public String getPic(Integer PicId) throws IOException {
        return iPictureService.getPic(PicId);
    }
}
