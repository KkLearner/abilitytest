package com.abilitytest.common;

import java.util.List;

//page bean
public class Page {
	
	private int totalCount = 0; //总记录数
	private int pageSize = 10;  //一页有多少条记录
	private int pageNum = 1; //页数

	private List<?> pageList;
	
	public Page(){	
	}
	
	public Page(int pageNum, int pageSize, int totalCount){
		this.pageNum = pageNum;
		this.pageSize = pageSize;
		this.totalCount = totalCount;
	}

	//求当前总页数
	public int getTotalPage(){
		if(totalCount == 0){
			return 0;
		}
		int totalPage = totalCount / pageSize;
		if (totalPage == 0 || totalCount % pageSize != 0) {
			++totalPage;
		}
		return totalPage;
	}
	
	//获取总记录数
	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public long getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public long getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public List<?> getPageList() {
		return pageList;
	}

	public void setPageList(List<?> pageList) {
		this.pageList = pageList;
	}
	
	//////
	//获取本页第一条记录
	public long getFirstResult(){
		return (pageNum - 1) * pageSize;
	}

}

