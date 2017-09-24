package com.etc.biz;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.etc.dao.BookInfoDao;
import com.etc.dao.RoleInfoDao;
import com.etc.dao.UserInfoDao;
import com.etc.entity.BookInfo;
import com.etc.entity.PageUtil;
import com.etc.entity.RoleInfo;
import com.etc.entity.UserInfo;
import com.etc.util.SessionFactoryUtil;

public class UserInfoBiz {
	
	public int VaUser(String acount, String pwd,int role){
		int v = 0 ;
		List<UserInfo> list = null;
		SqlSession session = SessionFactoryUtil.getSession();
		try {
			UserInfoDao dao = session.getMapper(UserInfoDao.class);
			list = dao.find();
			for (UserInfo userInfo : list) {
				
				if(userInfo.getuserAcount().equals(acount)){
					if(userInfo.getUserPass().equals(pwd) && userInfo.getRoleinfo().getRoleId() == role){
						v = 2;
						return v;
					}
					return v;
				}
			}
			
			
			v = 1; 
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			session.close();
		}
		
		return v;
	}
	
	

	
	public PageUtil<UserInfo> pageFind(int pageIndex, int pageSize) {
		//pageUtil 需要pageIndex pageSize count pageNumber list
		PageUtil<UserInfo> pageUtil = new PageUtil<UserInfo>();
		List<UserInfo> list = null;
		int count = 0;
		int pageNumber =0;
		SqlSession session=SessionFactoryUtil.getSession();
		try {
			
			UserInfoDao dao=session.getMapper(UserInfoDao.class);
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
	
	
	
	
	
	public List<UserInfo> find(){
		List<UserInfo> list = null;
		SqlSession session = SessionFactoryUtil.getSession();
		try {
			UserInfoDao dao = session.getMapper(UserInfoDao.class);
			list = dao.find();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			session.close();
		}
		return list;
	}
	
	public List<UserInfo> findadmin(){
		List<UserInfo> list = null;
		SqlSession session = SessionFactoryUtil.getSession();
		try {
			UserInfoDao dao = session.getMapper(UserInfoDao.class);
			list = dao.findadmin();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			session.close();
		}
		return list;
	}
	public List<UserInfo> finduser(){
		List<UserInfo> list = null;
		SqlSession session = SessionFactoryUtil.getSession();
		try {
			UserInfoDao dao = session.getMapper(UserInfoDao.class);
			list = dao.finduser();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			session.close();
		}
		return list;
	}
	
	public int findByAcount(String userAcount){
		List<UserInfo> list = null;
		int sum = 0;
		SqlSession session = SessionFactoryUtil.getSession();
		try {
			UserInfoDao dao = session.getMapper(UserInfoDao.class);
			list = dao.findByAcount(userAcount);
			sum = list.size();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			session.close();
		}
		return sum;
	}
	
	
	public UserInfo findById(int id){
		UserInfo list = null;
		SqlSession session = SessionFactoryUtil.getSession();
		try {
			UserInfoDao dao = session.getMapper(UserInfoDao.class);
			list = dao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			session.close();
		}
		return list;
	}
	
	public UserInfo findByIdAll(int id)
	{
		UserInfo list = null;
		SqlSession session = SessionFactoryUtil.getSession();
		try {
			UserInfoDao dao = session.getMapper(UserInfoDao.class);
			list = dao.findByIdAll(id);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			session.close();
		}
		return list;
	}
	
	
	
	
	
	public void delete(int id){
		SqlSession session = SessionFactoryUtil.getSession();
		try {
			UserInfoDao dao = session.getMapper(UserInfoDao.class);
			dao.delete(id);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			session.close();
		}
	}
	
	public void add(UserInfo userinfo){
		SqlSession session = SessionFactoryUtil.getSession();
		try {
			UserInfoDao  dao = session.getMapper(UserInfoDao.class); // 映射数据访问接口
			dao.add(userinfo);
			session.commit();
			
		} catch (Exception e) {
			System.out.println("有错误：" + e.getMessage());
		} finally {
			session.close(); // 关闭连接
		}
	}
	
	public void update(UserInfo userinfo){
		SqlSession session = SessionFactoryUtil.getSession();
		try {
			UserInfoDao  dao = session.getMapper(UserInfoDao.class); // 映射数据访问接口
			dao.updateByAcount(userinfo);
			session.commit();
		} catch (Exception e) {
			System.out.println("有错误：" + e.getMessage());
		} finally {
			session.close(); // 关闭连接
		}
	}
	public void deltoup(int id){
		SqlSession session = SessionFactoryUtil.getSession();
		try {
			UserInfoDao  dao = session.getMapper(UserInfoDao.class); // 映射数据访问接口
			dao.deltoup(id);
			session.commit();
		} catch (Exception e) {
			System.out.println("有错误：" + e.getMessage());
		} finally {
			session.close(); // 关闭连接
		}
	}
}
