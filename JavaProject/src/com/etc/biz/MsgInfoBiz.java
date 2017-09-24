package com.etc.biz;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.etc.dao.MsgInfoDao;
import com.etc.entity.MsgInfo;
import com.etc.util.SessionFactoryUtil;

public class MsgInfoBiz {

	public List<MsgInfo> find()
	{

		List<MsgInfo> list=null;
		SqlSession session=SessionFactoryUtil.getSession();
		try {
		 MsgInfoDao dao=session.getMapper(MsgInfoDao.class);
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
			MsgInfoDao dao=session.getMapper(MsgInfoDao.class);
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
	
	public void add(MsgInfo msg)
	{
		SqlSession session=SessionFactoryUtil.getSession();
		try
		{
			MsgInfoDao dao=session.getMapper(MsgInfoDao.class);
			dao.add(msg);
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
	
	public void update(MsgInfo msg)
	{
		SqlSession session=SessionFactoryUtil.getSession();
		try
		{
			MsgInfoDao dao=session.getMapper(MsgInfoDao.class);
			dao.update(msg);
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
	
	public void updateContent(String content)
	{
		SqlSession session=SessionFactoryUtil.getSession();
		try
		{
			MsgInfoDao dao=session.getMapper(MsgInfoDao.class);
			dao.updateContent(content);
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
	
	public void changActive(MsgInfo m)
	{
		SqlSession session=SessionFactoryUtil.getSession();
		try
		{
			MsgInfoDao dao=session.getMapper(MsgInfoDao.class);
			if(m.getIsActive()==1)
			{
				m.setIsActive(0);
			}
			else
			{
				m.setIsActive(1);
			}
			dao.changActive(m);
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
	public MsgInfo findById(int id)
	{

		SqlSession session=SessionFactoryUtil.getSession();
		MsgInfo m=new MsgInfo();
		try
		{
			MsgInfoDao dao=session.getMapper(MsgInfoDao.class);
			m=dao.findById(id);
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
		return m;
	}
	
	public List<MsgInfo> findByIsActive()
	{
		List<MsgInfo> list=null;
		SqlSession session=SessionFactoryUtil.getSession();
		try {
		 MsgInfoDao dao=session.getMapper(MsgInfoDao.class);
			list=dao.findIsActive();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally{
			
			session.close();
		}
		return list;
	}
}
