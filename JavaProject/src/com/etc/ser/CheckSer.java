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
import com.etc.entity.BookInfo;
import com.etc.entity.PageUtil;

@WebServlet("/CheckSer")
public class CheckSer extends HttpServlet {

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
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

		if(request.getParameter("searchwhat") == "" || request.getParameter("searchwhat") == null ){
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		
		String searchtype= request.getParameter("searchType");
		String matchtype=request.getParameter("matchType");
		String searchwhat=request.getParameter("searchwhat");
		//String searchwhat=new String(request.getParameter("matchType").getBytes("iso-8859-1"),"utf-8");
		List<BookInfo> book=null;
		
		if(matchtype.equals("free"))
		{
			searchwhat="%"+searchwhat+"%";
		}
		if(searchtype.equals("all"))
		{
			book=new BookInfoBiz().findByString(searchwhat);
		}
		else if(searchtype.equals("book"))
		{
			book=new BookInfoBiz().findBybookName(searchwhat);
		}
		else if(searchtype.equals("isbn"))
		{
			book=new BookInfoBiz().findBybookIden(searchwhat);
		}
		else 
		{
			book=new BookInfoBiz().findByauthor(searchwhat);
		}

		request.setAttribute("books", book);

		request.setAttribute("sum", book.size());

		request.getRequestDispatcher("search.jsp").forward(request, response);
	}

}
