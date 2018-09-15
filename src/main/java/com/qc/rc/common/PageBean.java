package com.qc.rc.common;

import java.util.List;

public class PageBean<T> {
	/*RowBounds（pageNum,pagesize）中第一个参数是页码数！！
	 * 
	 */
    //取几行pageSize
	//pageNum 当前页数	
	//allPage 总页数
	//allSize 总字段数量 count（*）

	private Integer pageSize;
	private Integer pageNum;
	private Integer allPage;
	private Integer allSize;
	private List<T> datalist;
	
	@Override
	public String toString() {
		return "PageBean [ pageSize=" + pageSize + ", pageNum=" + pageNum + ", allPage=" + allPage
				+ ", allSize=" + allSize + ", datalist=" + datalist + "]";
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
