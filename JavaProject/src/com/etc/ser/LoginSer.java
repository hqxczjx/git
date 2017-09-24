package com.etc.ser;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.jms.Session;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.etc.biz.UserInfoBiz;
import com.etc.entity.RoleInfo;
import com.etc.entity.UserInfo;

@WebServlet("/LoginSer")
public class LoginSer extends HttpServlet {

	public LoginSer() {
		super();
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			String msg = "";
			HttpSession session = request.getSession();
			
			if(request.getParameter("action") != null){
				session.invalidate();
				response.sendRedirect("first");
				return;
			}
			
			
			if(request.getParameter("user") != null && request.getParameter("pwd") != null ){
				String user = request.getParameter("user") ;
				String pwd = request.getParameter("pwd");
				int role = 2;
				
				UserInfoBiz uiz = new UserInfoBiz();
				int v = uiz.VaUser(user, pwd,role);
				if(v == 0 ){
					msg = "账号密码错误!";
				}else if(v == 1) {
					msg = "账号不存在!";
				}else{
					
					session.setAttribute("info", new UserInfo(user,null,new RoleInfo(role,null)));
					response.sendRedirect("IndexSer");
					return;
				}
				
			}
			request.setAttribute("msg", msg);
			request.getRequestDispatcher("login.jsp").forward(request, response);
	}

	public void init() throws ServletException {
		// Put your code here
	}

}
