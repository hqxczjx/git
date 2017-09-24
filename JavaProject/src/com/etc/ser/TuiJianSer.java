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
@WebServlet("/admin/TuiJianSer")
public class TuiJianSer extends HttpServlet {

	public TuiJianSer() {
		super();
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if(request.getParameter("action")!=null){
			String action = request.getParameter("action");
			MsgInfoBiz miz = new MsgInfoBiz();
			MsgInfo msg = null;
			if(action.equals("del")){
				int msgId = Integer.parseInt(request.getParameter("id"));
				miz.delete(msgId);
				response.sendRedirect("TuiJianSer");
				return ;
			}else if(action.equals("update")){
				int msgId = Integer.parseInt(request.getParameter("id"));
				msg = miz.findById(msgId);
			}
			request.setAttribute("msg", msg);
			request.getRequestDispatcher("tuijianUpdate.jsp").forward(request, response);
			return;
		}
		
		
		List<MsgInfo> list = new MsgInfoBiz().find();
		request.setAttribute("list", list);
		request.getRequestDispatcher("tuijian.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			MsgInfoBiz miz =new MsgInfoBiz();
		
		if(request.getParameter("action").equals("update")){
			String msgContent = request.getParameter("msgContent");
			int isActive = Integer.parseInt(request.getParameter("isActive"));
			int msgId = Integer.parseInt(request.getParameter("id"));
			MsgInfo msg = new MsgInfo(msgId, msgContent, isActive);
			miz.update(msg);
			
		}else{
			String msgContent = request.getParameter("msgContent");
			int isActive = Integer.parseInt(request.getParameter("isActive"));
			
			MsgInfo msg = new MsgInfo(msgContent, isActive);
			miz.add(msg);
		}
		
		
		response.sendRedirect("TuiJianSer");
	}
	public void init() throws ServletException {
		// Put your code here
	}

}
