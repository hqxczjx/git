package com.etc.util;


import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * 创建sqlSession对象的工厂工具类，单例模式
 * @author Administrator
 *
 */
public class SessionFactoryUtil {
	//1.私有化构造函数
	private SessionFactoryUtil(){}
	
	static SqlSessionFactory sessionFactory = null;
	static Reader reader = null;
	//这是一个静态代码块，在程序编译时就执行初始化，
	static {
		try {
			//加载配置文件，形成文件流
			reader = Resources.getResourceAsReader("config/mybatis-config.xml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	//创建工厂，作用是创建sqlsession
	private static SqlSessionFactory getSessionFactory(){
		if(sessionFactory == null){
			sessionFactory = new SqlSessionFactoryBuilder().build(reader);
		}
		
		return sessionFactory;
	}
	
	public static SqlSession getSession() {
		return getSessionFactory().openSession();
	}
}
