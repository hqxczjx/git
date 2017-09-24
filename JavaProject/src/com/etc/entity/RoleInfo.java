package com.etc.entity;

import java.util.List;

public class RoleInfo {
	private int roleId;
	private String roleName;
	private List<UserInfo> users;
	
	public int getRoleId() {
		return roleId;
	}
	public RoleInfo() {
		super();
	}
	
	public RoleInfo(int roleId, String roleName) {
		super();
		this.roleId = roleId;
		this.roleName = roleName;
		this.users = null;
	}
	
	public RoleInfo(int roleId) {
		super();
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	@Override
	public String toString() {
		return "RoleInfo [roleId=" + roleId + ", roleName=" + roleName + "]";
	}
	public List<UserInfo> getUsers() {
		return users;
	}
	public void setUsers(List<UserInfo> users) {
		this.users = users;
	}
	
}
