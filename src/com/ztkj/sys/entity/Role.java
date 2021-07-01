package com.ztkj.sys.entity;

import java.util.Date;

public class Role {

	private int roleId;
	private String roleName;
	private int roleState;
	private int roleCreatorId;
	private Date roleCreateDate;
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public int getRoleState() {
		return roleState;
	}
	public void setRoleState(int roleState) {
		this.roleState = roleState;
	}
	public int getRoleCreatorId() {
		return roleCreatorId;
	}
	public void setRoleCreatorId(int roleCreatorId) {
		this.roleCreatorId = roleCreatorId;
	}
	public Date getRoleCreateDate() {
		return roleCreateDate;
	}
	public void setRoleCreateDate(Date roleCreateDate) {
		this.roleCreateDate = roleCreateDate;
	}
	
}
