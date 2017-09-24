package com.etc.ser;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.etc.biz.BookInfoBiz;
import com.etc.biz.RecordInfoBiz;
import com.etc.biz.UserInfoBiz;
import com.etc.entity.BookInfo;
import com.etc.entity.RecordInfo;
import com.etc.entity.UserInfo;
@WebServlet("/admin/BorrowSer")
public class BorrowSer extends HttpServlet {

	
	public BorrowSer() {
		super();
	}

	
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RecordInfoBiz biz = new RecordInfoBiz();
		int userId = Integer.parseInt(request.getParameter("userId"));
		String bookIden = request.getParameter("bookIden");
		UserInfo user = new UserInfoBiz().findById(userId);
		BookInfo book = new BookInfoBiz().findBybookIdenAll(bookIden);
		RecordInfo record = new RecordInfo(book,user);
		biz.add(record);
		response.sendRedirect("./LendandreturnSer");
	}

	
	public void init() throws ServletException {
		// Put your code here
	}

}
