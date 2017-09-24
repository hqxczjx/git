package com.etc.entity;

import java.util.List;

public class PageUtil<T> {
	private int index; //当前页
 	private int pageSize; //每页大小
	private int count;	//总记录数
	private int pageNumber; //某页所需的数据
	private List<T> list;
	
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	@Override
	public String toString() {
		return "PageUtil [index=" + index + ", pageSize=" + pageSize
				+ ", count=" + count + ", pageNumber=" + pageNumber + ", list="
				+ list + "]";
	}
}
