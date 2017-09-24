package com.etc.ser;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import com.etc.biz.BookInfoBiz;
import com.etc.biz.RecordInfoBiz;
import com.etc.entity.BookInfo;
import com.etc.entity.PageUtil;
import com.etc.entity.RecordInfo;
import com.etc.entity.UserInfo;

public class TestLuo {
	public static void main(String arf[]){
		 BookInfoBiz biz = new BookInfoBiz();
		 PageUtil<BookInfo> page = biz.pageFind(1, 2);
		 List<BookInfo> list = page.getList();
		 for (BookInfo bookInfo : list) {
			System.out.print(bookInfo);
		}
		
	}
}
