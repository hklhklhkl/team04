package com.ztkj.sys.entity;

import java.util.Date;

public class Module {

	private int moduleId;
	private String moduleName;
	private int moduleParentId;
	private String moduleUrl;
	private int moduleState;
	private int moduleCreatorId;
	private Date moduleCreateDate;
	public int getModuleId() {
		return moduleId;
	}
	public void setModuleId(int moduleId) {
		this.moduleId = moduleId;
	}
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	public int getModuleParentId() {
		return moduleParentId;
	}
	public void setModuleParentId(int moduleParentId) {
		this.moduleParentId = moduleParentId;
	}
	public String getModuleUrl() {
		return moduleUrl;
	}
	public void setModuleUrl(String moduleUrl) {
		this.moduleUrl = moduleUrl;
	}
	public int getModuleState() {
		return moduleState;
	}
	public void setModuleState(int moduleState) {
		this.moduleState = moduleState;
	}
	public int getModuleCreatorId() {
		return moduleCreatorId;
	}
	public void setModuleCreatorId(int modulCreateorId) {
		this.moduleCreatorId = modulCreateorId;
	}
	public Date getModuleCreateDate() {
		return moduleCreateDate;
	}
	public void setModuleCreateDate(Date modulCreateDate) {
		this.moduleCreateDate = modulCreateDate;
	}
	
}
