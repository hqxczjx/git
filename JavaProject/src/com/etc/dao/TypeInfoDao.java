package com.etc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import com.etc.entity.TypeInfo;

public interface TypeInfoDao {

	@Select("select * from typeinfo")
	public List<TypeInfo> find();
	
	@Delete("delete from typeinfo where typeId=#{id}")
	public void delete(int id);
	 
	@Insert("insert into typeinfo values(typeId=#{typeId},typeName=#{typeName})")
	public int add(TypeInfo type);
	
	@Select("select * from typeinfo where typeId=#{typeId}")
	public TypeInfo findById(int id);
	
}
