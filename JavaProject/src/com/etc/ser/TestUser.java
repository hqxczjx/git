package com.etc.ser;

import java.util.List;

import com.etc.biz.UserInfoBiz;
import com.etc.entity.RoleInfo;
import com.etc.entity.UserInfo;

public class TestUser {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
//		UserInfo user  = new UserInfoBiz().findById(1);
//		System.out.println(user);
//		
//		UserInfoBiz biz = new UserInfoBiz();
//		biz.delete(4);
//		biz.delete(5);
		
//		UserInfoBiz biz = new UserInfoBiz();
//		biz.deltoup(8);
		
		
//		RoleInfo info = new RoleInfo();
//		info.setRoleId(1);
//		UserInfo user = new UserInfo();
//		user.setUserAccount("7777");
//		user.setUserPass("123");
//		user.setRoleinfo(info);
//		UserInfoBiz biz = new UserInfoBiz();
//		biz.add(user);
		List<UserInfo> list = new UserInfoBiz().find();
		for (UserInfo users : list) {
			System.out.println(users);
		}
//		
//		RoleInfo info = new RoleInfo();
//		info.setRoleId(2);
//		UserInfo user = new UserInfo();
//		user.setUserId(8);
//		user.setUserPass("3333");
//		user.setUserAccount("99999");
//		user.setRoleinfo(info);
//		user.setDeleted(1);
//		UserInfoBiz biz = new UserInfoBiz();
//		biz.update(user);
//		List<UserInfo> list = new UserInfoBiz().find();
//		for (UserInfo Info : list) {
//			System.out.println(Info);
//		}
		
	}
}

