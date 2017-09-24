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

import com.etc.biz.RecordInfoBiz;
import com.etc.biz.UserInfoBiz;
import com.etc.entity.RecordInfo;
import com.etc.entity.UserInfo;

@WebServlet("/user/PersonSer")
public class PersonSer extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public PersonSer() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RecordInfoBiz riz = new RecordInfoBiz();
		UserInfo info = (UserInfo) request.getSession().getAttribute("info");


		List<UserInfo> user = new UserInfoBiz().find();
		
		//续借
		if(request.getParameter("action")!=null){
			int id = Integer.parseInt(request.getParameter("id"));
			riz.recordXuReturn(id);
			
			response.sendRedirect("./PersonSer");
			return;
		}
		
		int id = 0;
		for(UserInfo a:user){
			if(a.getuserAcount().equals(info.getuserAcount())){
				id= a.getUserId();
				break;
			}
		}


		//检查是否拖欠
		riz.recordTuo();
		
		//历史借阅部分正在查阅部分
		List<RecordInfo> list = riz.findByUserId(id);
		request.setAttribute("list", list);
		request.getRequestDispatcher("person.jsp").forward(request,
				response);

	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
