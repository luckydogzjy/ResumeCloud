package com.qc.rc.common;

import java.util.List;

public class PageBean<T> {
	//��������offset
    //ȡ����pageSize
	//pageNum ��ǰҳ��	
	//allPage ��ҳ��
	//allSize ���ֶ����� count��*��

	private Integer offset;
	private Integer pageSize;
	private Integer pageNum;
	private Integer allPage;
	private Integer allSize;
	private List<T> datalist;
	
	public Integer getOffset() {
		return offset;
	}
	public void setOffset() {
		if(this.pageNum!=0 && this.pageSize!=0){
			this.offset = (this.pageNum-1)*this.pageSize;
		}
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getPageNum() {
		return pageNum;
	}
	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}
	public Integer getAllPage() {
		return allPage;
	}
	public void setAllPage() {
		if(this.allSize!=0 && this.pageSize!=0){
			if (this.allSize % this.pageSize == 0) {
				this.allPage = this.allSize/this.pageSize;
			}else {
				this.allPage = this.allSize/this.pageSize+1;
			}
		}
		
	}
	public List<T> getDatalist() {
		return datalist;
	}
	public void setDatalist(List<T> datalist) {
		this.datalist = datalist;
	}
	public Integer getAllSize() {
		return allSize;
	}
	public void setAllSize(Integer allSize) {
		this.allSize = allSize;
	}
	
	
}
