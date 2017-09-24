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
import com.etc.biz.TypeInfoBiz;
import com.etc.entity.BookInfo;
import com.etc.entity.PageUtil;
import com.etc.entity.TypeInfo;

@WebServlet("/ClassificationSer")
public class ClassificationSer extends HttpServlet {

	public ClassificationSer() {
		super();
	}

	
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BookInfoBiz biz = new BookInfoBiz();
		TypeInfoBiz tiz = new TypeInfoBiz();
		List<BookInfo> blist = null;
		List<TypeInfo> tlist = tiz.find();
		String typeString = null;
		int typeSum = 0;
		int type = 0;
		String typeId = "";
		int pageIndex = 1;
		int pageSize = 2;
		PageUtil<BookInfo> pu = null;
		
		if(request.getParameter("index") != null){
			 pageIndex = Integer.parseInt(request.getParameter("index"));
		}	
		
		if(request.getParameter("type") == null || request.getParameter("type") == ""){
			typeString = "全部";
			blist = biz.find();
			pu = biz.pageFind(pageIndex, pageSize);
			//blist = pu.getList();
			typeSum = blist.size();
		}else {
			type = Integer.parseInt(request.getParameter("type"));
			typeString = tiz.findById(type).getTypeName();
			typeId = String.valueOf(tiz.findById(type).getTypeId());
			pu = biz.pageFindType(pageIndex, pageSize, type);
			blist = biz.findByType(type);
			//blist = pu.getList();
			typeSum = blist.size();
		}

		request.setAttribute("typeString", typeString);
		request.setAttribute("TypeId", typeId);
		request.setAttribute("typeSum", typeSum);
		
		//request.setAttribute("blist", blist);
		request.setAttribute("pageUtilBookInfo", pu);
		request.setAttribute("tlist", tlist);
		request.getRequestDispatcher("classification.jsp").forward(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			doGet(request, response);
	}

	public void init() throws ServletException {
		// Put your code here
	}

}
