package com.ztkj.utils;

import java.util.List;

//类的泛型  T
public class Page<T> {

	private int totalCount;			//总记录数
	private int currentPage = 1;	//当前页
	private int totalPage;			//总页数
	private int pageSize = 5;		//每页显示的条数
	private int firstPage = 1;		//首页
	private int forwardPage;		//上一页
	private int nextPage;			//下一页
	private int lastPage;			//尾页

	private int startIndex;			//分页的起始位置
	private int endIndex;			//分页的结束位置
	
	private T entity;				//存储所有模糊查询出来的list集合
	private List<T> list;			//存储查询出来的list集合
	
	public T getEntity() {
		return entity;
	}
	public void setEntity(T entity) {
		this.entity = entity;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	
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
	public int getFirstPage() {
		return firstPage;
	}
	public void setFirstPage(int firstPage) {
		this.firstPage = firstPage;
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
		/*//给总记录数赋值
		setTotalCount(totalCount);
		//给总页数赋值
		setTotalPage(totalCount%getPageSize()==0?totalCount/getPageSize():totalCount/getPageSize()+1);		
		//给上一页的属性赋值,若当前页数为1,则上一页还是1,否则上一页的值为当前页-1
		setForwardPage(getCurrentPage()==1?1:getCurrentPage()-1);
		//给下一页的属性赋值,若当前页数为总页数,则下一页还是总页数,否则下一页的值为当前页+1
		setNextPage(getCurrentPage()==getTotalPage()?getTotalPage():getCurrentPage()+1);
		//给尾页赋值
		setLastPage(getTotalPage());
		
		setStartIndex((getCurrentPage()-1)*getPageSize()+1);
		setEndIndex(getCurrentPage()*getPageSize());*/
		
		this.totalCount = totalCount;
		this.totalPage = totalCount%pageSize == 0?totalCount/pageSize:totalCount/pageSize+1;
		this.forwardPage = currentPage == 1?1:currentPage-1;
		this.nextPage = currentPage == totalPage?totalPage:currentPage+1;
		this.lastPage = totalPage;		
		this.startIndex = (currentPage-1)*pageSize+1;
		this.endIndex = currentPage*pageSize;
			
	}
}
