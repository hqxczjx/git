package com.etc.entity;

public class BookInfo {

	private int bookId;
	private String bookName;
	private String bookItr;
	private String bookDetail;
	private int bookNum;
	private String bookIden;
	private String author;
	private TypeInfo type;
	private String bookPic;
	
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
	
	

	
	
	
	public String getBookPic() {
		return bookPic;
	}
	public void setBookPic(String bookPic) {
		this.bookPic = bookPic;
	}
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getBookItr() {
		return bookItr;
	}
	public void setBookItr(String bookItr) {
		this.bookItr = bookItr;
	}
	public String getBookDetail() {
		return bookDetail;
	}
	public void setBookDetail(String bookDetail) {
		this.bookDetail = bookDetail;
	}
	public int getBookNum() {
		return bookNum;
	}
	public void setBookNum(int bookNum) {
		this.bookNum = bookNum;
	}
	public String getBookIden() {
		return bookIden;
	}
	public void setBookIden(String bookIden) {
		this.bookIden = bookIden;
	}

	public String getauthor() {
		return author;
	}
	public void setauthor(String author) {
		this.author = author;
	}
	public TypeInfo getType() {
		return type;
	}
	public void setType(TypeInfo type) {
		this.type = type;
	}
	
	
	public BookInfo(int bookId, String bookName, String bookItr,
			String bookDetail, int bookNum, String bookIden, String author,
			TypeInfo type, String bookPic) {
		super();
		this.bookId = bookId;
		this.bookName = bookName;
		this.bookItr = bookItr;
		this.bookDetail = bookDetail;
		this.bookNum = bookNum;
		this.bookIden = bookIden;
		this.author = author;
		this.type = type;
		this.bookPic = bookPic;
	}

	public BookInfo(int bookId, String bookName, String bookItr,
			String bookDetail, int bookNum, String bookIden, String author,
			TypeInfo type, String bookPic, int pageNum, int pageSize) {
		super();
		this.bookId = bookId;
		this.bookName = bookName;
		this.bookItr = bookItr;
		this.bookDetail = bookDetail;
		this.bookNum = bookNum;
		this.bookIden = bookIden;
		this.author = author;
		this.type = type;
		this.bookPic = bookPic;
		this.pageNum = pageNum;
		this.pageSize = pageSize;
	}
	public BookInfo(){}
	@Override
	public String toString() {
		return "BookInfo [bookId=" + bookId + ", bookName=" + bookName
				+ ", bookItr=" + bookItr + ", bookDetail=" + bookDetail
				+ ", bookNum=" + bookNum + ", bookIden=" + bookIden
				+ ", author=" + author + ", type=" + type + ", bookPic="
				+ bookPic + ", pageNum=" + pageNum + ", pageSize=" + pageSize
				+ "]";
	}
	
	
}
