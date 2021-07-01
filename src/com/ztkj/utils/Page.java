package com.ztkj.utils;

import java.util.List;

//��ķ���  T
public class Page<T> {

	private int totalCount;			//�ܼ�¼��
	private int currentPage = 1;	//��ǰҳ
	private int totalPage;			//��ҳ��
	private int pageSize = 5;		//ÿҳ��ʾ������
	private int firstPage = 1;		//��ҳ
	private int forwardPage;		//��һҳ
	private int nextPage;			//��һҳ
	private int lastPage;			//βҳ

	private int startIndex;			//��ҳ����ʼλ��
	private int endIndex;			//��ҳ�Ľ���λ��
	
	private T entity;				//�洢����ģ����ѯ������list����
	private List<T> list;			//�洢��ѯ������list����
	
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
		/*//���ܼ�¼����ֵ
		setTotalCount(totalCount);
		//����ҳ����ֵ
		setTotalPage(totalCount%getPageSize()==0?totalCount/getPageSize():totalCount/getPageSize()+1);		
		//����һҳ�����Ը�ֵ,����ǰҳ��Ϊ1,����һҳ����1,������һҳ��ֵΪ��ǰҳ-1
		setForwardPage(getCurrentPage()==1?1:getCurrentPage()-1);
		//����һҳ�����Ը�ֵ,����ǰҳ��Ϊ��ҳ��,����һҳ������ҳ��,������һҳ��ֵΪ��ǰҳ+1
		setNextPage(getCurrentPage()==getTotalPage()?getTotalPage():getCurrentPage()+1);
		//��βҳ��ֵ
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
