package com.etc.entity;

public class MsgInfo {

	private int MsgId;
	private String MsgContent;
	private int IsActive;
	public int getMsgId() {
		return MsgId;
	}
	public void setMsgId(int msgId) {
		MsgId = msgId;
	}
	public String getMsgContent() {
		return MsgContent;
	}
	public void setMsgContent(String msgContent) {
		MsgContent = msgContent;
	}
	public int getIsActive() {
		return IsActive;
	}
	public void setIsActive(int isActive) {
		IsActive = isActive;
	}
	public MsgInfo(int msgId, String msgContent, int isActive) {
		super();
		MsgId = msgId;
		MsgContent = msgContent;
		IsActive = isActive;
	}
	
	public MsgInfo(String msgContent, int isActive) {
		super();
		MsgContent = msgContent;
		IsActive = isActive;
	}
	public MsgInfo(){}
	@Override
	public String toString() {
		return "MsgInfo [MsgId=" + MsgId + ", MsgContent=" + MsgContent
				+ ", IsActive=" + IsActive + "]";
	}
	
}
