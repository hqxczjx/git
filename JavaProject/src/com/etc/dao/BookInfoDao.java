package com.etc.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.etc.entity.BookInfo;


public interface BookInfoDao {

	@Select("select * from bookinfo")
	@Results({
		@Result(property="type",column="type",one=@One(select = "com.etc.dao.TypeInfoDao.findById"))
	})
	public List<BookInfo> find();
	
	@Select("select * from bookinfo limit #{pageNum},#{pageSize}")
	@Results({
		@Result(property="type",column="type",one=@One(select = "com.etc.dao.TypeInfoDao.findById"))
	})
	public List<BookInfo> findPage(Map<String, String> map);
	
	
	@Delete("delete from bookinfo where bookId=#{id}")
	public void delete(int id);
	 
	@Insert("insert into bookinfo values(null,#{bookName},#{bookPic},#{bookItr},#{bookDetail},#{bookNum},#{bookIden},#{author},#{type.typeId})")
	public int add(BookInfo book);
	
	@Select("select * from bookinfo where bookName like #{bookName}")
	public List<BookInfo> findBybookName(String bookname);
	
	@Update("update bookinfo set bookName=#{bookName},bookItr=#{bookItr},bookPic=#{bookPic},bookDetail=#{bookDetail},bookNum=#{bookNum},bookIden=#{bookIden},author=#{author}" +
			",type=#{type.typeId} where bookId=#{bookId}")
	public void update(BookInfo book);

   
	@Select("select * from bookinfo where author like #{author} ")
	public List<BookInfo> findByauthor(String author);

    
	@Select("select * from bookinfo where bookId = #{bookId} ")
	@Results({
		@Result(property="type",column="type",one=@One(select = "com.etc.dao.TypeInfoDao.findById"))
	})
	public BookInfo findById(int bookId);
	
	@Select("select * from bookinfo where type = #{type} ")
	public List<BookInfo> findByType(int type);
	
	@Select("select * from bookinfo where type = #{type} limit #{pageNum},#{pageSize}")
	@Results({
		@Result(property="type",column="type",one=@One(select = "com.etc.dao.TypeInfoDao.findById"))
	})
	public List<BookInfo> findPageType(Map<String, String> map);

	@Select("select * from bookinfo where bookIden like #{bookIden}")
	public List<BookInfo> findBybookIden(String bookIden);
	

	@Select("select * from bookinfo where bookIden = #{bookIden}")
	@Results({
		@Result(property="type",column="type",one=@One(select = "com.etc.dao.TypeInfoDao.findById"))
	})
	public BookInfo findBybookIdenAll(String bookIden);
	
	
	@Select("select * from bookinfo where author like #{s} or bookIden like #{s} or bookName like #{s}")
	public List<BookInfo> findByString(String s);
	
	
	//书本总数
	@Select("select count(*) from bookinfo")
	public int findSum();
	
	//书本总量根据type
	@Select("select count(*) from bookinfo where type = #{type}")
	public int findSumByType(int type);
	
	
	
}
