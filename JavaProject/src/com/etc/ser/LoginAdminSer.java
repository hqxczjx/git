package com.etc.ser;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.etc.biz.UserInfoBiz;
import com.etc.entity.RoleInfo;
import com.etc.entity.UserInfo;

@WebServlet("/LoginAdminSer")
public class LoginAdminSer extends HttpServlet {

	public LoginAdminSer() {
		super();
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
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
		
		if(session.getAttribute("info") != null){
			UserInfo userinfo = (UserInfo)session.getAttribute("info");
			
			if(userinfo != null && userinfo.getRoleinfo().getRoleId() == 1 ){
				((HttpServletResponse)response).sendRedirect("admin/bookmanager");
				return ;
			}
		}
		
		if(request.getParameter("user") != null && request.getParameter("pwd") != null ){
			String user = request.getParameter("user") ;
			String pwd = request.getParameter("pwd");
			int role = 1;
			
			UserInfoBiz uiz = new UserInfoBiz();
			int v = uiz.VaUser(user, pwd,role);
			if(v == 0 ){
				msg = "账号密码错误!";
			}else if(v == 1) {
				msg = "账号不存在!";
			}else{
				
				session.setAttribute("info", new UserInfo(user,null,new RoleInfo(role,null)));
				response.sendRedirect("admin/bookmanager");
				return;
			}
			
		}
		request.setAttribute("msg", msg);
		request.getRequestDispatcher("loginAdmin.jsp").forward(request, response);
		
	}
	public void init() throws ServletException {
		// Put your code here
	}

}
