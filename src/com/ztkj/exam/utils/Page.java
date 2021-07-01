	package com.ztkj.exam.utils;

public class Page {
	private int totalCount;		//�ܼ�¼��
	private int currentPage=1;	//��ǰҳ
	private int totalPage;		//��ҳ��
	private int pageSize =5;	//ÿҳ����ʾ����
	private int firstPage =1;	//��ҳ
	private int	forwardPage;	//��һҳ
	private int	nextPage;		//��һҳ
	private int	lastPage;		//βҳ
	private int startIndex;		//��ҳ��ʼλ��
	private int endIndex;		//��ҳ�Ľ���λ��
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
		//����ҳ����ֵ
		setTotalPage(totalCount%getPageSize()==0?totalCount/getPageSize():totalCount/getPageSize()+1);
		//����һҳ�����Ը�ֵ,����ǰҳΪһ,����һҳΪһ,��Ϊһ������Ϊ��ǰҳ��һ
		setForwardPage(getCurrentPage()==1?1:getCurrentPage()-1);
		//����һҳ��ֵ
		setNextPage(getCurrentPage()==getTotalPage()?getTotalPage():getCurrentPage()+1);
		//��βҳ��ֵ
		setLastPage(getTotalPage());
		setStartIndex((getCurrentPage()-1)*getPageSize()+1);
		setEndIndex(getCurrentPage()*getPageSize());
		
	}
	
}
