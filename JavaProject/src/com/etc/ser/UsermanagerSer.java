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
import com.etc.entity.PageUtil;
import com.etc.entity.RoleInfo;
import com.etc.entity.UserInfo;

@WebServlet("/admin/UsermanagerSer")
public class UsermanagerSer extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public UsermanagerSer() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserInfoBiz biz = new UserInfoBiz();
		if (request.getParameter("action") != null) {
			if (request.getParameter("action").equals("del")) {
				biz.deltoup(Integer.parseInt(request.getParameter("id")));
			} else if (request.getParameter("action").equals("update")) {

				UserInfo user = biz.findById(Integer.parseInt(request
						.getParameter("id")));
				request.setAttribute("user", user);
				request.getRequestDispatcher("/admin/userEdit.jsp").forward(
						request, response);

				return;

			}
		}
		
		
		int index =1;
		if(request.getParameter("index")!= null)
		{
			index = Integer.parseInt(request.getParameter("index"));
		}
		
			PageUtil<UserInfo> p=biz.pageFind(index, 8);
			request.setAttribute("pageUtil", p);
			
		
		
//		List<UserInfo> list = biz.finduser();
//		request.setAttribute("list", list);
		request.getRequestDispatcher("/admin/usermanager.jsp").forward(request,
				response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserInfoBiz biz = new UserInfoBiz();
		if (request.getParameter("action").equals("edit")) {// 管理员修改用户修改
			String name = request.getParameter("user");
			String pwd = request.getParameter("pwd");
			biz.update(new UserInfo(name, pwd, new RoleInfo(2, null)));
			response.sendRedirect("./UsermanagerSer");
			return;

		} else if (request.getParameter("action").equals("add")) {// 管理员添加用户信息
			String msg = null;
			int sum = 0;
			String user = request.getParameter("user");
			String pwd = request.getParameter("pwd");
			if (user != null && !user.equals("") && pwd != null
					&& !pwd.equals("")) {
				sum = biz.findByAcount(user);
				if (sum == 0) {
					biz.add(new UserInfo(user, pwd, new RoleInfo(2)));
					response.sendRedirect("./UsermanagerSer");
					return;
				} else {
					msg = "账号密码已存在";
				}
			} else {
				msg = "输入信息有误";
			}
			request.setAttribute("msg", msg);
			request.getRequestDispatcher("/admin/addUser.jsp").forward(request,
					response);
		}

	}

	public void init() throws ServletException {
		// Put your code here
	}

}
