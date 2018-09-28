package com.qc.rc.entity;

import java.util.Date;

import org.springframework.stereotype.Component;
@Component
public class UserResume {
	
		private String urId;
		private String urUesrId;
		private String urResumeId;
		private String urResumeGetway;
		private Integer urResumeShareFlag;
		
		private Integer urDeleteFlag;
		private Date urCreateTime;
		private Date urUpdateTime;
		

		
		
		public String getUrId() {
			return urId;
		}
		public void setUrId(String urId) {
			this.urId = urId;
		}
		public String getUrUesrId() {
			return urUesrId;
		}
		public void setUrUesrId(String urUesrId) {
			this.urUesrId = urUesrId;
		}
		public String getUrResumeId() {
			return urResumeId;
		}
		public void setUrResumeId(String urResumeId) {
			this.urResumeId = urResumeId;
		}
		public String getUrResumeGetway() {
			return urResumeGetway;
		}
		public void setUrResumeGetway(String urResumeGetway) {
			this.urResumeGetway = urResumeGetway;
		}
		public Integer getUrResumeShareFlag() {
			return urResumeShareFlag;
		}
		public void setUrResumeShareFlag(Integer urResumeShareFlag) {
			this.urResumeShareFlag = urResumeShareFlag;
		}
		public Integer getUrDeleteFlag() {
			return urDeleteFlag;
		}
		public void setUrDeleteFlag(Integer urDeleteFlag) {
			this.urDeleteFlag = urDeleteFlag;
		}
		public Date getUrCreateTime() {
			return urCreateTime;
		}
		public void setUrCreateTime(Date urCreateTime) {
			this.urCreateTime = urCreateTime;
		}
		public Date getUrUpdateTime() {
			return urUpdateTime;
		}
		public void setUrUpdateTime(Date urUpdateTime) {
			this.urUpdateTime = urUpdateTime;
		}

		
		/**
		 * @param urId
		 * @param urUesrId
		 * @param urResumeId
		 * @param urResumeGetway
		 * @param urResumeShareFlag
		 * @param urDeleteFlag
		 * @param urCreateTime
		 * @param urUpdateTime
		 */
		public UserResume(String urId, String urUesrId, String urResumeId, String urResumeGetway,
				Integer urResumeShareFlag, Integer urDeleteFlag, Date urCreateTime, Date urUpdateTime) {
			super();
			this.urId = urId;
			this.urUesrId = urUesrId;
			this.urResumeId = urResumeId;
			this.urResumeGetway = urResumeGetway;
			this.urResumeShareFlag = urResumeShareFlag;
			this.urDeleteFlag = urDeleteFlag;
			this.urCreateTime = urCreateTime;
			this.urUpdateTime = urUpdateTime;
		}
		public UserResume(){
			super();
		}
		
		
}
