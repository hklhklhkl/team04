package com.ztkj.front.vo;

import java.util.Date;

public class HotPost {

	private int block_id; //板块id
	private String block_name; //板块名称
	private int count; //帖子数量
	private int hot ; //热门值
	private Date time;//帖子时间
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
