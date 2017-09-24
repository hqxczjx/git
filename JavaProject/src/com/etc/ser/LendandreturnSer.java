package com.etc.ser;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.etc.biz.BookInfoBiz;
import com.etc.biz.MsgInfoBiz;
import com.etc.biz.RecordInfoBiz;
import com.etc.biz.UserInfoBiz;
import com.etc.entity.BookInfo;
import com.etc.entity.MsgInfo;
import com.etc.entity.PageUtil;
import com.etc.entity.RecordInfo;
import com.etc.entity.UserInfo;

@WebServlet("/admin/LendandreturnSer")
public class LendandreturnSer extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public LendandreturnSer() {
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
		// 功能续借 还书
		RecordInfoBiz biz = new RecordInfoBiz();

		if (request.getParameter("action") != null) {
			int id = Integer.parseInt(request.getParameter("id"));

			if (request.getParameter("action").equals("toreturn")) {// 还书
				java.sql.Date date = new java.sql.Date(
						new java.util.Date().getTime());

				RecordInfo record = new RecordInfo(id, date);

				biz.recordReturn(record);

			} else if (request.getParameter("action").equals("renew")) {// 续借
				biz.recordXuReturn(id);
			}

			response.sendRedirect("./LendandreturnSer");
			return;
		}

		if (request.getParameter("findid") != null) {
			int findid = Integer.parseInt(request.getParameter("findid"));
			RecordInfoBiz biz2 = new RecordInfoBiz();
			int index = 1;
			if (request.getParameter("index") != null) {
				index = Integer.parseInt(request.getParameter("index"));
			}

			PageUtil<RecordInfo> p = biz.pageFindById(findid, index, 8);
			request.setAttribute("pageUtil", p);
			request.setAttribute("findid", findid);
			request.getRequestDispatcher("./larfinduser.jsp").forward(request,
					response);
			return;
		}

		int index = 1;
		if (request.getParameter("index") != null) {
			index = Integer.parseInt(request.getParameter("index"));
		}

		PageUtil<RecordInfo> p = biz.pageFind(index, 8);
		request.setAttribute("pageUtil", p);

		request.getRequestDispatcher("./lendandreturn.jsp").forward(request,
				response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getParameter("searchwhat") == null
				|| request.getParameter("searchwhat") == "") {
			response.sendRedirect("LendandreturnSer");
			return;
		}

		// 通过radio判断是什么搜索 用户 书本
		List<BookInfo> book = null;

		String searchType = request.getParameter("searchType");
		String searchwhat = request.getParameter("searchwhat");

		if (searchType.equals("user")) {

			RecordInfoBiz biz = new RecordInfoBiz();

			int index = 1;
			if (request.getParameter("index") != null) {
				index = Integer.parseInt(request.getParameter("index"));
			}

			PageUtil<RecordInfo> p = biz.pageFindById(
					Integer.parseInt(searchwhat), index, 8);
			request.setAttribute("pageUtil", p);
			request.setAttribute("findid", Integer.parseInt(searchwhat));
			request.getRequestDispatcher("./larfinduser.jsp").forward(request,
					response);

		} else if (searchType.equals("book")) {
			BookInfo books = null;
			books = new BookInfoBiz().findBybookIdenAll(searchwhat);
			
			if(books == null){
				response.setCharacterEncoding("utf-8");        
			    response.setContentType("text/html; charset=utf-8");        
				PrintWriter out = response.getWriter();
				out.println("找不到这本书，三秒钟后跳转至借书页面");
				response.setHeader("refresh","3;LendandreturnSer");
				return;
			}
			
			
			
			request.setAttribute("book", books);
			request.getRequestDispatcher("/admin/details.jsp").forward(request,
					response);

		}

	}

	public void init() throws ServletException {
		// Put your code here
	}

}
