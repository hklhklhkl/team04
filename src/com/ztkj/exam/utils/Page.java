	package com.ztkj.exam.utils;

public class Page {
	private int totalCount;		//总记录数
	private int currentPage=1;	//当前页
	private int totalPage;		//总页数
	private int pageSize =5;	//每页的显示条数
	private int firstPage =1;	//首页
	private int	forwardPage;	//上一页
	private int	nextPage;		//下一页
	private int	lastPage;		//尾页
	private int startIndex;		//分页起始位置
	private int endIndex;		//分页的结束位置
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getFistPage() {
		return firstPage;
	}
	public void setFistPage(int fistPage) {
		this.firstPage = fistPage;
	}
	public int getForwardPage() {
		return forwardPage;
	}
	public void setForwardPage(int forwardPage) {
		this.forwardPage = forwardPage;
	}
	public int getNextPage() {
		return nextPage;
	}
	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}
	public int getLastPage() {
		return lastPage;
	}
	public void setLastPage(int lastPage) {
		this.lastPage = lastPage;
	}
	public int getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}
	public int getEndIndex() {
		return endIndex;
	}
	public void setEndIndex(int endIndex) {
		this.endIndex = endIndex;
	}
	public void setPageProperties(int totalCount){
		setTotalCount(totalCount);
		//给总页数赋值
		setTotalPage(totalCount%getPageSize()==0?totalCount/getPageSize():totalCount/getPageSize()+1);
		//给上一页的属性赋值,若当前页为一,若上一页为一,则为一，否则为当前页减一
		setForwardPage(getCurrentPage()==1?1:getCurrentPage()-1);
		//给下一页赋值
		setNextPage(getCurrentPage()==getTotalPage()?getTotalPage():getCurrentPage()+1);
		//给尾页赋值
		setLastPage(getTotalPage());
		setStartIndex((getCurrentPage()-1)*getPageSize()+1);
		setEndIndex(getCurrentPage()*getPageSize());
		
	}
	
}
