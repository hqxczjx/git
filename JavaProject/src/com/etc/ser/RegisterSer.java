package com.etc.ser;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.etc.biz.UserInfoBiz;
import com.etc.entity.RoleInfo;
import com.etc.entity.UserInfo;

@WebServlet("/RegisterSer")
public class RegisterSer extends HttpServlet {

	public RegisterSer() {
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
		
		UserInfoBiz uiz = new UserInfoBiz();
		String msg = "";
		HttpSession session = request.getSession();
		
		if(request.getParameter("action") != null){
			String action = request.getParameter("action");
			String user = request.getParameter("user");
			String pwd = request.getParameter("pwd");
			String repwd = request.getParameter("repwd");
			
			int sum = 0;
			if(action.equals("add")){
				if (user != null && !user.equals("") && pwd != null && !pwd.equals("") && repwd != null && !repwd.equals("") && pwd.equals(repwd)) {
					sum = uiz.findByAcount(user);
					if(sum == 0 ){
						uiz.add(new UserInfo(user, pwd, new RoleInfo(2)));
						response.sendRedirect("LoginSer");
						return;
					}else {
						msg = "账号密码已存在";
					}
				}else{
					msg = "输入信息有误";
				}
			}else{
					if(pwd.equals(repwd) && pwd != "" && pwd != null){
						UserInfo info = (UserInfo) session.getAttribute("info");
						info.setUserPass(pwd);
						
						System.out.println(info);
						uiz.update(info);
					}else{
					}
					
					session.invalidate();
					response.sendRedirect("index.jsp");
					return;
			}
		}
		
		request.setAttribute("msg", msg);
		request.getRequestDispatcher("register.jsp").forward(request, response);
	}

	public void init() throws ServletException {
		// Put your code here
	}

}
