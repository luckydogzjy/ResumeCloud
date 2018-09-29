
package com.qc.rc.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qc.rc.common.GetUuid;
import com.qc.rc.dao.DownloadRecordMapper;
import com.qc.rc.dao.ResumeMapper;
import com.qc.rc.dao.SharingCenterMapper;
import com.qc.rc.dao.UserResumeMapper;
import com.qc.rc.entity.DownloadRecord;
import com.qc.rc.entity.Resume;
import com.qc.rc.entity.SharingCenter;
import com.qc.rc.entity.User;
import com.qc.rc.entity.UserResume;
import com.qc.rc.entity.pojo.DownloadRecordPojo;
import com.qc.rc.entity.pojo.ResumePojo;
import com.qc.rc.entity.pojo.SharingCenterPojo;
import com.qc.rc.service.SharingCenterService;

@Service("SharingCenterService")
public class SharingCenterServiceImpl implements SharingCenterService {
	
	@Autowired
	private SharingCenterMapper sharingCenterMapper;
	@Autowired
	private ResumeMapper resumeMapper;
	@Autowired
	private UserResumeMapper userResumeMapper;
	@Autowired
	private DownloadRecordMapper downloadRecordMapper;
	@Autowired
	private UserResume userResume;
	
	private static Integer pageShow = 5;
		
	@Override
	public Map<String, Object> getSharingResumeListByCondition(String userId,ResumePojo resumePojo,Integer page) {
		
		//引入分页查询，使用PageHelper分页功能
        //在查询之前传入当前页，然后多少记录
		PageHelper.startPage(page,pageShow);
		List<SharingCenterPojo> list = sharingCenterMapper.getSharingResumeListByCondition(resumePojo);
		//取到符合条件信息后，取当前用户所兑换过的简历列表
	//	List<DownloadRecord> downloadRecords = sharingCenterMapper.getDownloadRecordById(userId);
		List<DownloadRecordPojo> downloadRecords = downloadRecordMapper.getDownloadRecordById(userId);
			
		PageInfo<SharingCenterPojo> pageInfo = new PageInfo<SharingCenterPojo>(list);
		
		for(int i = 0; i < downloadRecords.size(); i++){
			
			for(int j = 0; j < list.size(); j++){
				
				if (downloadRecords.get(i).getDrSharingCenterId().equals(list.get(j).getScId())) {
					//将当前用户兑换过的简历信息，在list里将标志位赋值为1，意思为当前用户已经兑换过，
					//在前台显示 已兑换 按钮   替换信息
					
					list.get(j).setResume(resumeMapper.getResumeDetailsById(downloadRecords.get(i).getUrResumeId()));
					list.get(j).setFlag(1);
					list.get(j).setNowResumeId(downloadRecords.get(i).getUrResumeId());
				}
			}
		}
		
		Map<String,Object> model = new HashMap<String,Object>(); 
		model.put("sharingList", list);
		model.put("page", pageInfo);
		model.put("userId", userId);
		model.put("search", resumePojo);
		
		return model;
	}

	//点击兑换按钮后要做的事
	//1.首先判断积分是否足够，若足够  前端判断
	//2.根据id调用ResumeMapper里的selectResumeById()返回一个resume对象
	//3.将这个对象重新set 一个 id 并调用ResumeMapper里的insertResume()插入到数据库RC_RESUME表
	//4.新建个userResume对象，附上对应的值。调用UserResumeMapper里的SharingInsertUserResume()插入数据库RC_USER_RESUME表
	//5.根据userId和sharingCenterId插入RC_DOWNLOAD_RECORD一条数据
	//6.兑换次数+1
	//7.RC_USER表里的积分字段USER_INTEGRAL减去相应分数
	
	@Override
	public void exchangeResume(User user,ResumePojo searchResumePojo, SharingCenter sharingCenter) throws Exception{
		
		//2.根据id返回resume
		Resume resume = resumeMapper.selectResumeById(sharingCenter.getScResumeId());
		System.out.println(resume.toString());
		//3.重新set id  调用uuid方法
		String id = GetUuid.getuuid32();    //现在的简历id
		resume.setResumeId(id);
		resume.setResumeCreateUser(user.getUserName());
		//插入数据库,存在出异常的可能 ，向上抛
		resumeMapper.insertResume(resume);
		System.out.println(user.getUserId());
		//4.赋值	
		userResume.setUrId(GetUuid.getuuid32());
		userResume.setUrUesrId(user.getUserId());
		userResume.setUrResumeId(id); //现在的简历id
		userResume.setUrResumeGetway(sharingCenter.getScResumeId()); //原来的简历id
		userResume.setUrResumeShareFlag(1);
		//插入
		userResumeMapper.SharingInsertUserResume(userResume);
		//5.插入RC_DOWNLOAD_RECORD一条数据
		String drId = GetUuid.getuuid32();  //后期改为uuid  
		downloadRecordMapper.insertDownloadRecord(drId, user.getUserId(), sharingCenter.getScId());
		//6.兑换次数+1
		sharingCenterMapper.updateDownloadCount(sharingCenter.getScId());
		//7.减积分
	
	}

	@Override
	public void cancelSharingResume(String scId) throws Exception{
		
		sharingCenterMapper.cancelSharingResume(scId);
	}

}
