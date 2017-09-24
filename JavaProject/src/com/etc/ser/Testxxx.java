package com.etc.ser;


import java.util.List;

import com.etc.biz.BookInfoBiz;
import com.etc.biz.MsgInfoBiz;
import com.etc.biz.RecordInfoBiz;
import com.etc.biz.TypeInfoBiz;
import com.etc.biz.UserInfoBiz;
import com.etc.entity.BookInfo;
import com.etc.entity.MsgInfo;
import com.etc.entity.RecordInfo;
import com.etc.entity.TypeInfo;
import com.etc.entity.UserInfo;

/**
 * 大家给自己一个测试类，Test+自己的名字
 * @author Administrator
 *
 */
public class Testxxx {
	public static void main(String arg[]){

		List<BookInfo> list=null;
		
		list=new RecordInfoBiz().HotBoard();
		System.out.println(list);
		
	}
	
}
