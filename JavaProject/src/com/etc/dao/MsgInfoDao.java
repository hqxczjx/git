package com.etc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import com.etc.entity.MsgInfo;



public interface MsgInfoDao {

	@Select("select * from msginfo")
	public List<MsgInfo> find();
	
	@Delete("delete from msginfo where MsgId=#{id}")
	public void delete(int id);
	 
	@Insert("insert into msginfo values(null,#{MsgContent},#{IsActive})")
	@Options(useGeneratedKeys = true, keyProperty = "MsgId")
	public int add(MsgInfo Msg);
	
	@Select("select * from msginfo where MsgId=#{MsgId}")
	public MsgInfo findById(int id);
	
	@Select("select * from msginfo where IsActive=1")
	public List<MsgInfo> findIsActive();
	
	@Update("update msginfo set MsgContent=#{MsgContent}")
	public void updateContent(String content);
	
	@Update("update msginfo set MsgContent=#{MsgContent},IsActive=#{IsActive} where MsgId = #{MsgId}")
	public void update(MsgInfo Msg);
	
	@Update("update msginfo set IsActive=#{IsActive} where MsgId=#{MsgId}")
	public void changActive(MsgInfo msg);

}
