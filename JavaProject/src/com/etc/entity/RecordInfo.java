package com.etc.entity;

import java.sql.Date;
import java.sql.Time;

public class RecordInfo {

	private int recordId;
	private BookInfo Book;
	private UserInfo User;
	private Date recordBeginDate;
	private Date recordEndDate;
	private int recordState;
	private Date recordDate;
	
	
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
	
	
	
	
	
	
	public int getRecordId() {
		return recordId;
	}
	public void setRecordId(int recordId) {
		this.recordId = recordId;
	}

	public BookInfo getBook() {
		return Book;
	}
	public void setBook(BookInfo book) {
		Book = book;
	}
	public UserInfo getUser() {
		return User;
	}
	public void setUser(UserInfo user) {
		User = user;
	}
	public Date getRecordBeginDate() {
		return recordBeginDate;
	}
	public void setRecordBeginDate(Date recordBeginDate) {
		this.recordBeginDate = recordBeginDate;
	}
	public Date getRecordEndDate() {
		return recordEndDate;
	}
	public void setRecordEndDate(Date recordEndDate) {
		this.recordEndDate = recordEndDate;
	}
	public int getRecordState() {
		return recordState;
	}
	public RecordInfo(BookInfo book, UserInfo user) {
		super();
		Book = book;
		User = user;
	}
	public void setRecordState(int recordDate) {
		this.recordState = recordDate;
	}
	public RecordInfo(int recordId, BookInfo book, UserInfo user,
			Date recordBeginDate, Date recordEndDate, int recordDate) {
		super();
		this.recordId = recordId;
		Book = book;
		User = user;
		this.recordBeginDate = recordBeginDate;
		this.recordEndDate = recordEndDate;
		this.recordState = recordDate;
	}
	
	public RecordInfo(int recordId, BookInfo book, UserInfo user,
			Date recordBeginDate, Date recordEndDate, int recordState,
			Date recordDate, int pageNum, int pageSize) {
		super();
		this.recordId = recordId;
		Book = book;
		User = user;
		this.recordBeginDate = recordBeginDate;
		this.recordEndDate = recordEndDate;
		this.recordState = recordState;
		this.recordDate = recordDate;
		this.pageNum = pageNum;
		this.pageSize = pageSize;
	}
	public RecordInfo(int recordId, java.util.Date recordDate) {
		super();
		this.recordId = recordId;
		this.recordDate = (Date) recordDate;
	}

	public RecordInfo(){}

	@Override
	public String toString() {
		return "RecordInfo [recordId=" + recordId + ", Book=" + Book
				+ ", User=" + User + ", recordBeginDate=" + recordBeginDate
				+ ", recordEndDate=" + recordEndDate + ", recordState="
				+ recordState + ", recordDate=" + recordDate + ", pageNum="
				+ pageNum + ", pageSize=" + pageSize + "]";
	}
	public Date getRecordDate() {
		return recordDate;
	}
	public void setRecordDate(Date recordDate) {
		this.recordDate = recordDate;
	}
	
}
