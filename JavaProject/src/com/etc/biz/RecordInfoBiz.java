package com.etc.biz;

import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.etc.dao.BookInfoDao;
import com.etc.dao.RecordInfoDao;
import com.etc.dao.UserInfoDao;
import com.etc.entity.BookInfo;
import com.etc.entity.PageUtil;
import com.etc.entity.RecordInfo;
import com.etc.entity.UserInfo;
import com.etc.util.SessionFactoryUtil;

public class RecordInfoBiz {

	public void recordTuo() {
		SqlSession session = SessionFactoryUtil.getSession();
		try {
			RecordInfoDao dao = session.getMapper(RecordInfoDao.class);

			java.util.Date date = new java.util.Date();
			Date now = new Date(date.getTime());

			dao.updateTuo(now);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();
		} finally {

			session.close();
		}
	}

	public void recordXuReturn(int recordId) {
		SqlSession session = SessionFactoryUtil.getSession();
		try {
			RecordInfo recordinfo = new RecordInfo();

			RecordInfoDao dao = session.getMapper(RecordInfoDao.class);
			recordinfo = dao.findById(recordId);
			// 把时间拿出来，加一个月然后再放回去
			Date Enddate = recordinfo.getRecordEndDate();
			java.util.Date d = new java.util.Date(Enddate.getTime());

			// 推迟一个月
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(d);
			calendar.add(calendar.MONTH, 1);
			d = calendar.getTime();

			// 放回去
			Date NEnddate = new Date(d.getTime());
			recordinfo.setRecordEndDate(NEnddate);

			dao.updateXuRetrurn(recordinfo);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();
		} finally {

			session.close();
		}
	}

	public void recordReturn(RecordInfo recordinfo) {
		SqlSession session = SessionFactoryUtil.getSession();
		try {
			RecordInfoDao dao = session.getMapper(RecordInfoDao.class);
			dao.updateReturn(recordinfo);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();
		} finally {

			session.close();
		}
	}

	public void add(RecordInfo recordinfo) {
		SqlSession session = SessionFactoryUtil.getSession();
		try {
			RecordInfoDao dao = session.getMapper(RecordInfoDao.class);
			// 开始时间为当前
			java.sql.Date date = new java.sql.Date(
					new java.util.Date().getTime());
			recordinfo.setRecordBeginDate(date);

			// 借书时间为1个月
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(date);
			calendar.add(calendar.MONTH, 1);

			java.util.Date d = calendar.getTime();
			Date nDate = new Date(d.getTime());
			
			recordinfo.setRecordEndDate(nDate);


			dao.add(recordinfo);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();
		} finally {

			session.close();
		}
	}
	
	public PageUtil<RecordInfo> pageFind(int pageIndex, int pageSize) {
		//pageUtil 需要pageIndex pageSize count pageNumber list
		PageUtil<RecordInfo> pageUtil = new PageUtil<RecordInfo>();
		List<RecordInfo> list = null;
		int count = 0;
		int pageNumber =0;
		SqlSession session=SessionFactoryUtil.getSession();
		try {
			
			RecordInfoDao dao=session.getMapper(RecordInfoDao.class);
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
	
	
	public PageUtil<RecordInfo> pageFindById(int id,int pageIndex, int pageSize) {
		//pageUtil 需要pageIndex pageSize count pageNumber list
		PageUtil<RecordInfo> pageUtil = new PageUtil<RecordInfo>();
		List<RecordInfo> list = null;
		int count = 0;
		int pageNumber =0;
		SqlSession session=SessionFactoryUtil.getSession();
		try {
			
			RecordInfoDao dao=session.getMapper(RecordInfoDao.class);
			count = dao.findSumById(id);
			pageNumber = (int)Math.ceil((double)count/pageSize);
			int pageNum = (pageIndex-1)*pageSize;
			
//			Map map = new HashMap<String, String>();
//			map.put("pageNum",pageNum);
//			map.put("pageSize",pageSize);
			
			list = dao.findPageById(id,pageNum,pageSize);
			
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
	
	
	
	
	

	public int PeopleSum(int userId) {
		int sum = 0;
		SqlSession session = SessionFactoryUtil.getSession();
		try {
			RecordInfoDao dao = session.getMapper(RecordInfoDao.class);
			sum = dao.PeopleSum(userId);
		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();
		} finally {

			session.close();
		}
		return sum;
	}


	public List<RecordInfo> findByUserId0(int userId) {
		List<RecordInfo> list = null;
		SqlSession session = SessionFactoryUtil.getSession();

		try {
			RecordInfoDao dao = session.getMapper(RecordInfoDao.class);
			list = dao.findByUserId0(userId);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {

			session.close();
		}
		return list;
	}

	public List<RecordInfo> findByRecordState0() {
		List<RecordInfo> list = null;
		SqlSession session = SessionFactoryUtil.getSession();
		try {
			RecordInfoDao dao = session.getMapper(RecordInfoDao.class);
			list = dao.findByRecordState0();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {

			session.close();
		}
		return list;
	}

	public List<RecordInfo> findByUserId(int userId) {
		List<RecordInfo> list = null;
		SqlSession session = SessionFactoryUtil.getSession();
		try {
			RecordInfoDao dao = session.getMapper(RecordInfoDao.class);
			list = dao.findByUserId(userId);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {

			session.close();
		}
		return list;
	}

	public List<RecordInfo> findByBookId(int BookId) {

		List<RecordInfo> list = null;
		SqlSession session = SessionFactoryUtil.getSession();
		try {
			RecordInfoDao dao = session.getMapper(RecordInfoDao.class);
			list = dao.findByBookId(BookId);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {

			session.close();
		}
		return list;
	}

	public List<RecordInfo> find() {

		List<RecordInfo> list = null;
		SqlSession session = SessionFactoryUtil.getSession();
		try {
			RecordInfoDao dao = session.getMapper(RecordInfoDao.class);
			list = dao.find();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {

			session.close();
		}
		return list;
	}

	public List<BookInfo> HotBoard() {
		List<BookInfo> list = null;
		SqlSession session = SessionFactoryUtil.getSession();
		try {
			RecordInfoDao dao = session.getMapper(RecordInfoDao.class);
			list = dao.HotBoard();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {

			session.close();
		}
		return list;
	}

}
