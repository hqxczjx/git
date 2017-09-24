package com.etc.ser;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.etc.biz.MsgInfoBiz;
import com.etc.entity.MsgInfo;

@WebServlet("/IndexSer")
public class IndexSer extends HttpServlet {

	public IndexSer() {
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
		
		MsgInfoBiz miz = new MsgInfoBiz();
		List<MsgInfo> list = miz.findByIsActive();
		
		
		request.setAttribute("msgInfo", list);
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	public void init() throws ServletException {
		// Put your code here
	}

}
