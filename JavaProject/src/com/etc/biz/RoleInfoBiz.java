package com.etc.biz;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.etc.dao.RoleInfoDao;
import com.etc.entity.RoleInfo;
import com.etc.entity.UserInfo;
import com.etc.util.SessionFactoryUtil;

public class RoleInfoBiz {
	
	public List<RoleInfo> find(){
		SqlSession session = SessionFactoryUtil.getSession();
		List<RoleInfo> list= null;
		try {
			RoleInfoDao dao = session.getMapper(RoleInfoDao.class);
			list = dao.find();
			
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			session.close();
		}
		return list;
		
		
	}
	public RoleInfo findById(int id){
		SqlSession session = SessionFactoryUtil.getSession();
		RoleInfo roleinfo = null;
		try {
			RoleInfoDao dao = session.getMapper(RoleInfoDao.class);
			roleinfo = dao.findById(id);
			
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			session.close();
		}
		return roleinfo;
	}
	
	public void delete(int id){
		SqlSession session = SessionFactoryUtil.getSession();
		try {
			RoleInfoDao  dao = session.getMapper(RoleInfoDao.class); // 映射数据访问接口
			dao.delete(id);
			session.commit();
		} catch (Exception e) {
			System.out.println("有错误：" + e.getMessage());
		} finally {
			session.close(); // 关闭连接
		}
	}

	public void add(RoleInfo roleInfo){
		SqlSession session = SessionFactoryUtil.getSession();
		try {
			RoleInfoDao  dao = session.getMapper(RoleInfoDao.class); // 映射数据访问接口
			dao.add(roleInfo);
			session.commit();
			
		} catch (Exception e) {
			System.out.println("有错误：" + e.getMessage());
		} finally {
			session.close(); // 关闭连接
		}
	}
	
	public void update(RoleInfo roleInfo){
		SqlSession session = SessionFactoryUtil.getSession();
		try {
			RoleInfoDao  dao = session.getMapper(RoleInfoDao.class); // 映射数据访问接口
			dao.update(roleInfo);
			session.commit();
		} catch (Exception e) {
			System.out.println("有错误：" + e.getMessage());
		} finally {
			session.close(); // 关闭连接
		}
	}
	
}
