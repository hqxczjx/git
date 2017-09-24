package com.etc.ser;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.catalina.core.ApplicationPart;



import com.etc.biz.BookInfoBiz;
import com.etc.biz.TypeInfoBiz;
import com.etc.entity.BookInfo;
import com.etc.entity.PageUtil;
import com.etc.entity.TypeInfo;

@WebServlet("/admin/bookmanager")


//@MultipartConfig(location="e:/workplace")
//@MultipartConfig(location="D:/Workspaces/MyEclipse Professional")
@MultipartConfig(location="C:/Users/Administrator/AppData/Local/MyEclipse Professional")
//@MultipartConfig(location="C:/Users/Administrator/Workspaces/MyEclipse Professional")


public class BookManagerSer extends HttpServlet {

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



		BookInfoBiz bb=new BookInfoBiz();
		
		List<TypeInfo> types=null;
		types=new TypeInfoBiz().find();
		request.setAttribute("types", types);
		
		
		if(request.getParameter("tag")!=null)
		{
			
			if(request.getParameter("tag").equals("del"))
			{
				int bookId=Integer.parseInt(request.getParameter("bookid"));
				bb.delete(bookId);
				
				response.sendRedirect("bookmanager");
				return;
				
			}
			else if(request.getParameter("tag").equals("add"))
			{
				List<TypeInfo> typelist=new TypeInfoBiz().find();
				request.setAttribute("types", typelist);
				request.getRequestDispatcher("addbook.jsp").forward(request, response);
				return;
			}
			if(request.getParameter("tag").equals("update"))
			{
				int bookId=Integer.parseInt(request.getParameter("bookid"));
				BookInfo book=bb.findById(bookId);
				
				request.setAttribute("book", book);
				request.getRequestDispatcher("addbook.jsp").forward(request, response);
				return;
			}
		}
		
		int index =1;
		if(request.getParameter("index")!= null)
			index = Integer.parseInt(request.getParameter("index"));
		
		
		if(request.getParameter("typeId")!=null && request.getParameter("typeId")!="")
		{
			int typeId=Integer.parseInt(request.getParameter("typeId"));
			request.setAttribute("TypeId", typeId);
			
			PageUtil<BookInfo> p=bb.pageFindType(index, 2, typeId);
			request.setAttribute("pageUtil", p);
		
		}
		else
		{
			PageUtil<BookInfo> p=bb.pageFind(index, 2);
			request.setAttribute("pageUtil", p);
			
		}

		request.getRequestDispatcher("bookmanager.jsp").forward(request, response);

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
		
		int bookid=0;
		
		if(!request.getParameter("bookid").equals(""))
		{
			bookid=Integer.parseInt(request.getParameter("bookid"));
		}
		

		BookInfoBiz bb=new BookInfoBiz();
		String bookname=request.getParameter("bookname");
		String bookitr=request.getParameter("bookitr");
		String bookdetail=request.getParameter("bookdetail");
		int booknum=Integer.parseInt(request.getParameter("booknum"));
		String author=request.getParameter("author");
		String bookiden=request.getParameter("bookiden");
		String filename=request.getParameter("bookpic2");
		int typeid=Integer.parseInt(request.getParameter("typeid"));
	
		PrintWriter out=response.getWriter();

//		String path="E:/workplace/zhongruanshixun/JavaProject/WebRoot";
          String path=this.getServletContext().getRealPath("/");
//		String path="D:/Workspaces/MyEclipse Professional/zhongruanshixun/JavaProject/WebRoot";


		Part p=request.getPart("bookpic");
		if(p.getContentType().contains("image"))
		{
			ApplicationPart ap=(ApplicationPart)p;
			String fname1=ap.getSubmittedFileName();
			int path_idx=fname1.lastIndexOf("\\")+1;
			String fname2=fname1.substring(path_idx,fname1.length());
			p.write(path+"/upload/"+fname2);
			filename="upload/"+fname2;
		}
		else
		{		
			out.write("请上传图片文件!");
		}
		
	    BookInfo b=new BookInfo();
	  
	    
	    b.setBookPic(filename);
	    b.setBookDetail(bookdetail);
	    b.setauthor(author);
	    b.setBookIden(bookiden);
	    b.setBookItr(bookitr);
		b.setBookNum(booknum);
		b.setType(new TypeInfoBiz().findById(typeid));
		b.setBookName(bookname);
		System.out.println(b);
		if (bookid!=0)
		{
		
			b.setBookId(bookid);
			bb.update(b);
		}
		else
		{
			bb.add(b);
			
		}	
		
		response.sendRedirect("bookmanager");
		
		

	}

}
