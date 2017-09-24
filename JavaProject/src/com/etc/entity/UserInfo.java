package com.etc.entity;
/**
 * test一下而已，可删除
 * @author Administrator
 *
 */
public class UserInfo {
	private int userId;
	private String userAcount;
	private String userPass;
	private RoleInfo roleinfo;
	private int deleted;
	
	
	private int pageNum;
	private int pageSize;
	
	private int getPageNum() {
		return pageNum;
	}
	private void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	private int getPageSize() {
		return pageSize;
	}
	private void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	
	
	
	
	
	

	public UserInfo(){};
	
	public UserInfo(int userId, String userAcount, String userPass,
			RoleInfo roleinfo, int deleted) {
		super();
		this.userId = userId;
		this.userAcount = userAcount;
		this.userPass = userPass;
		this.roleinfo = roleinfo;
		this.deleted = deleted;
	}
	
	public UserInfo(String userAcount, String userPass, RoleInfo roleinfo) {
		super();
		this.userAcount = userAcount;
		this.userPass = userPass;
		this.roleinfo = roleinfo;
	}

	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public int getDeleted() {
		return deleted;
	}
	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}
	public String getuserAcount() {
		return userAcount;
	}
	public void setuserAcount(String userAcount) {
		this.userAcount = userAcount;
	}
	public String getUserPass() {
		return userPass;
	}
	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}
	public RoleInfo getRoleinfo() {
		return roleinfo;
	}
	public void setRoleinfo(RoleInfo roleinfo) {
		this.roleinfo = roleinfo;
	}
	public UserInfo(int userId, String userAcount, String userPass,
			RoleInfo roleinfo, int deleted, int pageNum, int pageSize) {
		super();
		this.userId = userId;
		this.userAcount = userAcount;
		this.userPass = userPass;
		this.roleinfo = roleinfo;
		this.deleted = deleted;
		this.pageNum = pageNum;
		this.pageSize = pageSize;
	}
	@Override
	public String toString() {
		return "UserInfo [userId=" + userId + ", userAcount=" + userAcount
				+ ", userPass=" + userPass + ", roleinfo=" + roleinfo
				+ ", deleted=" + deleted + ", pageNum=" + pageNum
				+ ", pageSize=" + pageSize + "]";
	}
	
	
	
}
