package com.ztkj.front.vo;

import java.util.Date;

public class HotPost {

	private int block_id; //���id
	private String block_name; //�������
	private int count; //��������
	private int hot ; //����ֵ
	private Date time;//����ʱ��
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public int getBlock_id() {
		return block_id;
	}
	public void setBlock_id(int block_id) {
		this.block_id = block_id;
	}
	public String getBlock_name() {
		return block_name;
	}
	public void setBlock_name(String block_name) {
		this.block_name = block_name;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getHot() {
		return hot;
	}
	public void setHot(int hot) {
		this.hot = hot;
	}
	
}
