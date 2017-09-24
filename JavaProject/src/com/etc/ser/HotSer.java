package com.etc.ser;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.etc.biz.RecordInfoBiz;
import com.etc.entity.BookInfo;

@WebServlet("/HotSer")
public class HotSer extends HttpServlet {

	public HotSer() {
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
		List<BookInfo> list=null;
		
		list=new RecordInfoBiz().HotBoard();
		request.setAttribute("books", list);
		request.getRequestDispatcher("hot.jsp").forward(request, response);
	}

	public void init() throws ServletException {
		// Put your code here
		
		
	}

}
