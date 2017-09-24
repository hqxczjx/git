package com.etc.biz;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import com.etc.dao.BookInfoDao;
import com.etc.entity.BookInfo;
import com.etc.entity.PageUtil;
import com.etc.util.SessionFactoryUtil;


public class BookInfoBiz {
	
	public PageUtil<BookInfo> pageFind(int pageIndex, int pageSize) {
		//pageUtil 需要pageIndex pageSize count pageNumber list
		PageUtil<BookInfo> pageUtil = new PageUtil<BookInfo>();
		List<BookInfo> list = null;
		int count = 0;
		int pageNumber =0;
		SqlSession session=SessionFactoryUtil.getSession();
		try {
			
			BookInfoDao dao=session.getMapper(BookInfoDao.class);
			count = dao.findSum();
			pageNumber = (int)Math.ceil((double)count/pageSize);
			int pageNum = (pageIndex-1)*pageSize;
			
			Map map = new HashMap<String, String>();
			map.put("pageNum",pageNum);
			map.put("pageSize",pageSize);
			
			list = dao.findPage(map);
			
			pageUtil.setIndex(pageIndex);
			pageUtil.setPageSize(pageSize);
			pageUtil.setPageNumber(pageNumber);
			pageUtil.setCount(count);
			pageUtil.setList(list);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally{
			session.close();
		}
		return pageUtil;
	}
	
	public PageUtil<BookInfo> pageFindType(int pageIndex, int pageSize,int type) {
		//pageUtil 需要pageIndex pageSize count pageNumber list
		PageUtil<BookInfo> pageUtil = new PageUtil<BookInfo>();
		List<BookInfo> list = null;
		int count = 0;
		int pageNumber =0;
		SqlSession session=SessionFactoryUtil.getSession();
		try {
			
			BookInfoDao dao=session.getMapper(BookInfoDao.class);
			count = dao.findSumByType(type);
			pageNumber = (int)Math.ceil((double)count/pageSize);
			int pageNum = (pageIndex-1)*pageSize;
			
			Map map = new HashMap<String, String>();
			map.put("pageNum",pageNum);
			map.put("pageSize",pageSize);
			map.put("type",type);
			
			
			list = dao.findPageType(map);
			
			pageUtil.setIndex(pageIndex);
			pageUtil.setPageSize(pageSize);
			pageUtil.setPageNumber(pageNumber);
			pageUtil.setCount(count);
			pageUtil.setList(list);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally{
			session.close();
		}
		return pageUtil;
	}
	
	
	public List<BookInfo> find()
	{

		List<BookInfo> list=null;
		SqlSession session=SessionFactoryUtil.getSession();
		try {
		 BookInfoDao dao=session.getMapper(BookInfoDao.class);
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
			BookInfoDao dao=session.getMapper(BookInfoDao.class);
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
	
	public void add(BookInfo book)
	{
		SqlSession session=SessionFactoryUtil.getSession();
		
		
		try
		{
			BookInfoDao dao=session.getMapper(BookInfoDao.class);
			dao.add(book);
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
	public void update(BookInfo book)
	{
		SqlSession session=SessionFactoryUtil.getSession();
		try
		{
			BookInfoDao dao=session.getMapper(BookInfoDao.class);
			dao.update(book);
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
	
	public List<BookInfo> findBybookName(String bookname)
	{
		SqlSession session=SessionFactoryUtil.getSession();
		List<BookInfo> list=null;
		try
		{
			BookInfoDao dao=session.getMapper(BookInfoDao.class);
			list=dao.findBybookName(bookname);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			session.rollback();
		}
		finally{
			
			session.close();
		}
		return list;
	}



	public List<BookInfo> findByauthor(String author)


	{
		SqlSession session=SessionFactoryUtil.getSession();
		List<BookInfo> list=null;
		try
		{
			BookInfoDao dao=session.getMapper(BookInfoDao.class);
			list=dao.findByauthor(author);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			session.rollback();
		}
		finally{
			
			session.close();
		}
		return list;
	}
	

	public List<BookInfo> findBybookIden(String bookIden)



	{
		SqlSession session=SessionFactoryUtil.getSession();
		List<BookInfo> list=null;
		try
		{
			BookInfoDao dao=session.getMapper(BookInfoDao.class);
			list=dao.findBybookIden(bookIden);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			session.rollback();
		}
		finally{
			
			session.close();
		}
		return list;
	}
	
	public BookInfo findBybookIdenAll(String bookIden){
		SqlSession session=SessionFactoryUtil.getSession();
		BookInfo b=new BookInfo();
		try
		{
			BookInfoDao dao=session.getMapper(BookInfoDao.class);
			b=dao.findBybookIdenAll(bookIden);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			session.rollback();
		}
		finally{
			
			session.close();
		}
		return b;
	}
	
	public List<BookInfo> findByString(String s)
	{
		SqlSession session=SessionFactoryUtil.getSession();
		List<BookInfo> list=null;
		try
		{
			BookInfoDao dao=session.getMapper(BookInfoDao.class);
			list=dao.findByString(s);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			session.rollback();
		}
		finally{
			
			session.close();
		}
		return list;
	}
	
	public List<BookInfo> findByType(int type)
	{
		SqlSession session=SessionFactoryUtil.getSession();
		List<BookInfo> list=null;
		try
		{
			BookInfoDao dao=session.getMapper(BookInfoDao.class);
			list=dao.findByType(type);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			session.rollback();
		}
		finally{
			
			session.close();
		}
		return list;
	}
	
	public BookInfo findById(int bookId)
	{
		SqlSession session=SessionFactoryUtil.getSession();
		BookInfo b=new BookInfo();
		try
		{
			BookInfoDao dao=session.getMapper(BookInfoDao.class);
			b=dao.findById(bookId);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			session.rollback();
		}
		finally{
			
			session.close();
		}
		return b;
	}
	
}
