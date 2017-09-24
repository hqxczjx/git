package com.etc.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import com.etc.entity.BookInfo;
import com.etc.entity.UserInfo;

public interface UserInfoDao {
	
	@Select("select * from userinfo where deleted = 0 and role = 2 and userAcount = #{userAcount} and userPass = #{userPass}")
	public List<UserInfo> VaUser(String userAcount,String userPass);
	
	@Select("select * from userinfo where deleted=0")
	@Results({
		@Result(column = "userId", id = true, property = "userId"),
		@Result(property = "userAcount", column = "userAcount"),
		@Result(property = "userPass", column = "userPass" ),
		@Result(property = "roleinfo", column = "role"  ,
				one=@One(select = "com.etc.dao.RoleInfoDao.findById"))
	}
	)
	public List<UserInfo> find();
	
	
	
	
	@Select("select count(*) from userinfo ")
	public int findSum();
	
	
	
	
	@Select("select * from userinfo where  role=2 limit #{pageNum},#{pageSize}")
	@Results({
		@Result(column = "userId", id = true, property = "userId"),
		@Result(property = "userAcount", column = "userAcount"),
		@Result(property = "userPass", column = "userPass" ),
		@Result(property = "roleinfo", column = "role" ,
				one=@One(select = "com.etc.dao.RoleInfoDao.findById"))
	}
)
	public List<UserInfo>  findPage(Map<String, String> map);
	
	
	
	
	
	
	@Select("select * from userinfo where userAcount = #{userAcount}")
	public List<UserInfo> findByAcount(String userAcount);
	
	
	/**
	 * 返回管理员用户表
	 * @return
	 */
	@Select("select * from userinfo where deleted=0 and role=1")
	@Results({
		@Result(column = "userId", id = true, property = "userId"),
		@Result(property = "userAcount", column = "userAcount"),
		@Result(property = "userPass", column = "userPass" ),
		@Result(property = "roleinfo", column = "role"  ,
				one=@One(select = "com.etc.dao.RoleInfoDao.findById"))
	}
)
	public List<UserInfo> findadmin();
	
	/**
	 * 返回普通用户
	 * @return
	 */
	
	@Select("select * from userinfo where deleted=0 and role=2")
	@Results({
		@Result(column = "userId", id = true, property = "userId"),
		@Result(property = "userAcount", column = "userAcount"),
		@Result(property = "userPass", column = "userPass" ),
		@Result(property = "roleinfo", column = "role" ,
				one=@One(select = "com.etc.dao.RoleInfoDao.findById"))
	}
)
	public List<UserInfo> finduser();
	
	
	@Select("select * from userinfo where userId = #{userId} and deleted=0")
	@Results({
		@Result(column = "userId", id = true, property = "userId"),
		@Result(property = "userAcount", column = "userAcount"),
		@Result(property = "userPass", column = "userPass" ),
		@Result(property = "roleinfo", column = "role"  ,
		one=@One(select = "com.etc.dao.RoleInfoDao.findById"))
	}
)
	public UserInfo findById(int id);
	
	//包括已删除的
	@Select("select * from userinfo where userId = #{userId} ")
	@Results({
		@Result(column = "userId", id = true, property = "userId"),
		@Result(property = "userAcount", column = "userAcount"),
		@Result(property = "userPass", column = "userPass" ),
		@Result(property = "roleinfo", column = "role"  ,
		one=@One(select = "com.etc.dao.RoleInfoDao.findById"))
	}
	)
	public UserInfo findByIdAll(int id);
	
	@Select("delete from userinfo where userId = #{id}")
	public void delete(int id);
	
	@Update("update userinfo set deleted=1 where userId = #{id}")
	public void deltoup(int id);
	
	@Update("update userinfo set userAcount=#{userAcount},userPass=#{userPass},role=#{roleinfo.roleId} where userId = #{userId}")
	public void update(UserInfo userinfo);
	
	@Update("update userinfo set userAcount=#{userAcount},userPass=#{userPass},role=#{roleinfo.roleId} where userAcount = #{userAcount}")
	public void updateByAcount(UserInfo userinfo);
	
	@Insert("insert into userinfo values(null,#{userAcount},#{userPass},#{roleinfo.roleId},0)")
	@SelectKey(statement = "select last_insert_id()", before = false, resultType = int.class, keyProperty = "userId")
	public void add(UserInfo userinfo);
	
	
}

