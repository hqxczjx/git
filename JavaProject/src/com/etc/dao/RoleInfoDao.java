package com.etc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import com.etc.entity.RoleInfo;
import com.etc.entity.UserInfo;


public interface RoleInfoDao {
	@Select("select * from roleinfo")
	public List<RoleInfo> find();

	@Select("select * from roleinfo where roleId=#{id}")
	// 返回映射实体的配置
	@Results({
			@Result(column = "roleId", id = true, property = "roleId"),
			@Result(property = "roleName", column = "roleName")
		}
	)
	public RoleInfo findById(int id);

	// 采用注解来实现配置，替代了xml映射文件
	@Delete("delete from roleinfo where roleId=#{id}")
	public void delete(int id);

	@Insert("insert into roleinfo values(null,#{roleName})")
	@SelectKey(statement = "select last_insert_id()", before = false, resultType = int.class, keyProperty = "roleId")
	public void add(RoleInfo roleInfo);
	
	@Update("update roleinfo set roleName=#{roleName} where roleId=#{roleId}")
	public void update(RoleInfo roleInfo);
	
}
