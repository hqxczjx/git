package com.etc.ser;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.etc.biz.BookInfoBiz;
import com.etc.biz.RecordInfoBiz;
import com.etc.entity.BookInfo;
import com.etc.entity.RecordInfo;

@WebServlet("/AjaxBookIdSumSer")
public class AjaxBookIdSumSer extends HttpServlet {

	public AjaxBookIdSumSer() {
		super();
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
			}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try{
			int BookId = Integer.parseInt(request.getParameter("bookId")) ;
			BookInfoBiz biz = new BookInfoBiz();
			RecordInfoBiz riz = new RecordInfoBiz();
			
			List<RecordInfo> rlist = riz.findByBookId(BookId);
			BookInfo bookInfo = biz.findById(BookId);
		
			int s = bookInfo.getBookNum() - rlist.size();
			response.setHeader("content-type", "text/html;charset=UTF-8");
		    response.setCharacterEncoding("utf-8");
		    response.getWriter().print(s);
	
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public void init() throws ServletException {
		// Put your code here
	}

}
