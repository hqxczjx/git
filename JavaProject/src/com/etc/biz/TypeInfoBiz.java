package com.etc.biz;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.etc.dao.TypeInfoDao;
import com.etc.entity.TypeInfo;
import com.etc.util.SessionFactoryUtil;

public class TypeInfoBiz {
	
	public List<TypeInfo> find()
	{

		List<TypeInfo> list=null;
		SqlSession session=SessionFactoryUtil.getSession();
		try {
			TypeInfoDao dao=session.getMapper(TypeInfoDao.class);
			list=dao.find();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally{
			session.close();
		}
		return list;
	}
	
	public void delete(int id)
	{
		SqlSession session=SessionFactoryUtil.getSession();
		try
		{
			TypeInfoDao dao=session.getMapper(TypeInfoDao.class);
			dao.delete(id);
			session.commit();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			session.rollback();
		}
		finally{
			
			session.close();
		}
	}
	
	public void add(TypeInfo type)
	{
		SqlSession session=SessionFactoryUtil.getSession();
		
		
		try
		{
			TypeInfoDao dao=session.getMapper(TypeInfoDao.class);
			dao.add(type);
			session.commit();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			session.rollback();
		}
		finally{
			
			session.close();
		}
	}
	public TypeInfo findById(int id)
	{
		SqlSession session=SessionFactoryUtil.getSession();	
		TypeInfo type=new TypeInfo();
		try
		{
			TypeInfoDao dao=session.getMapper(TypeInfoDao.class);
			type=dao.findById(id);	
		}
		catch(Exception e)
		{
			e.printStackTrace();
			session.rollback();
		}
		finally{
			
			session.close();
		}
		return type;
	}
	
	

}
