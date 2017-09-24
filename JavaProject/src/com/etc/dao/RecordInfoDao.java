package com.etc.dao;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.etc.entity.BookInfo;
import com.etc.entity.RecordInfo;
import com.etc.entity.UserInfo;

public interface RecordInfoDao {
	//借书
	@Insert("insert into recordinfo values(null,#{user.userId},#{book.bookId},#{recordBeginDate},#{recordEndDate},0,null)")
	public int add(RecordInfo recordinfo);
	
	//还书
	@Update("update recordinfo set recordDate = #{recordDate}, recordState = 2 where recordId = #{recordId}") 
	public int updateReturn(RecordInfo recordinfo);

	//续借
	@Update("update recordinfo set recordEndDate = #{recordEndDate},recordState = 1 where recordId = #{recordId}")
	public int updateXuRetrurn(RecordInfo recordinfo);
	
	//拖欠
	@Update("update recordinfo set recordState = 3 where recordEndDate < #{now} and recordstate != 2")
	public int updateTuo(Date now);
	
	//所有记录
	@Select("select * from recordinfo")
	@Results({
		@Result(property = "User", column="userId",one=@One(select = "com.etc.dao.UserInfoDao.findById")),
		@Result(property = "Book", column="bookId",one=@One(select = "com.etc.dao.BookInfoDao.findById"))
	})
	public List<RecordInfo> find();

	@Select("select count(*) from recordinfo ")
	public int findSum();
	
	
	@Select("select count(*) from recordinfo where userId = #{userId}")
	public int findSumById(int userId );
	
	
	@Select("select * from recordinfo limit #{pageNum},#{pageSize}")
	@Results({
		@Result(property = "User", column="userId",one=@One(select = "com.etc.dao.UserInfoDao.findByIdAll")),
		@Result(property = "Book", column="bookId",one=@One(select = "com.etc.dao.BookInfoDao.findById"))
	})
	public List<RecordInfo>  findPage(Map<String, String> map);
	
	@Select("select * from recordinfo where userId =  #{userId} limit #{pageNum},#{pageSize}")
	@Results({
		@Result(property = "User", column="userId",one=@One(select = "com.etc.dao.UserInfoDao.findByIdAll")),
		@Result(property = "Book", column="bookId",one=@One(select = "com.etc.dao.BookInfoDao.findById"))
	})
	public List<RecordInfo>  findPageById(@Param("userId")int userId,@Param("pageNum")int pageNum,@Param("pageSize")int pageSize);
	
	
	
	//byUserId
	@Select("select * from recordinfo where userId = #{userId}")
	@Results({
		@Result(property = "User", column="userId",one=@One(select = "com.etc.dao.UserInfoDao.findById")),
		@Result(property = "Book", column="bookId",one=@One(select = "com.etc.dao.BookInfoDao.findById"))
	})
	public List<RecordInfo> findByUserId(int userId);
	
	
	@Select("select * from recordinfo where recordState = 0")
	@Results({
		@Result(property = "User", column="userId",one=@One(select = "com.etc.dao.UserInfoDao.findById")),
		@Result(property = "Book", column="bookId",one=@One(select = "com.etc.dao.BookInfoDao.findById"))
	})
	public List<RecordInfo> findByRecordState0();
	
	
	
	//byUserId0
	@Select("select * from recordinfo where userId = #{userId} and recordState = 0")
	@Results({
		@Result(property = "User", column="userId",one=@One(select = "com.etc.dao.UserInfoDao.findById")),
		@Result(property = "Book", column="bookId",one=@One(select = "com.etc.dao.BookInfoDao.findById"))
	})
	public List<RecordInfo> findByUserId0(int id);
	
	//byUserId1
	@Select("select * from recordinfo where userId = #{userId} and recordState = 1")
	@Results({
		@Result(property = "User", column="userId",one=@One(select = "com.etc.dao.UserInfoDao.findById")),
		@Result(property = "Book", column="bookId",one=@One(select = "com.etc.dao.BookInfoDao.findById"))
	})
	public List<RecordInfo> findByUserId1(int userId);
	
	//byUserId2
	@Select("select * from recordinfo where userId = #{userId} and recordState = 2")
	@Results({
		@Result(property = "User", column="userId",one=@One(select = "com.etc.dao.UserInfoDao.findById")),
		@Result(property = "Book", column="bookId",one=@One(select = "com.etc.dao.BookInfoDao.findById"))
	})
	public List<RecordInfo> findByUserId2(int userId);
	
	//byUserId3
	@Select("select * from recordinfo where userId = #{userId} and recordState = 3")
	@Results({
		@Result(property = "User", column="userId",one=@One(select = "com.etc.dao.UserInfoDao.findById")),
		@Result(property = "Book", column="bookId",one=@One(select = "com.etc.dao.BookInfoDao.findById"))
	})
	public List<RecordInfo> findByUserId3(int userId);
	
	//byId
	@Select("select * from recordinfo where recordId = #{recordId}")
	@Results({
		@Result(property = "User", column="userId",one=@One(select = "com.etc.dao.UserInfoDao.findById")),
		@Result(property = "Book", column="bookId",one=@One(select = "com.etc.dao.BookInfoDao.findById"))
	})
	public RecordInfo findById(int recordId);
	
	//bybookId
	@Select("select * from recordinfo where bookId = #{bookId}")
	@Results({
		@Result(property = "User", column="userId",one=@One(select = "com.etc.dao.UserInfoDao.findById")),
		@Result(property = "Book", column="bookId",one=@One(select = "com.etc.dao.BookInfoDao.findById"))
	})
	public List<RecordInfo> findByBookId(int bookId);
	
	//byuserId
	@Select("select * from recordinfo where userId = #{userId}")
	@Results({
		@Result(property = "User", column="userId",one=@One(select = "com.etc.dao.UserInfoDao.findById")),
		@Result(property = "Book", column="bookId",one=@One(select = "com.etc.dao.BookInfoDao.findById"))
	})
	public List<RecordInfo> findByuserId(int userId);
	
	//个人总共
	@Select("select count(*) from recordinfo where userId = #{userId}")
	public int PeopleSum(int userId);
	
	@Select("select b.* from recordinfo r join bookinfo b on r.bookId = b.bookId group by r.bookId order by count(*) desc")
	@Results({
		@Result(property="type",column="type",one=@One(select = "com.etc.dao.TypeInfoDao.findById"))
	})
	public List<BookInfo> HotBoard();
	
}
