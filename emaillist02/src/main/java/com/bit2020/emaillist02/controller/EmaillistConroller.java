package com.bit2020.emaillist02.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bit2020.emaillist02.dao.EmaillistDao;
import com.bit2020.emaillist02.vo.EmaillistVo;


public class EmaillistConroller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset = UTF-8");
		String action = request.getParameter("a");
		
		if("add".equals(action)) {
			String firstName = request.getParameter("fn");
			String lastName = request.getParameter("ln");
			String email = request.getParameter("email");
			
			EmaillistVo vo = new EmaillistVo();
			vo.setFirstName(firstName);
			vo.setLastName(lastName);
			vo.setEmail(email);
			
			new EmaillistDao().insert(vo);
			response.sendRedirect(request.getContextPath()+"/el");
		}else if("form".equals(action)) {
			 RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/form.jsp");
			 rd.forward(request, response);
		}else if("deleteform".equals(action)) {
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/deleteform.jsp");
			rd.forward(request, response);
		}else if("delete".equals(action)) {
			String no = request.getParameter("no");
			String lastName = request.getParameter("ln");
			
			if(new EmaillistDao().delete(Long.parseLong(no), lastName)) {
				response.sendRedirect(request.getContextPath()+"/el?a=form");
			}else {
				PrintWriter out = response.getWriter();
				out.println("<script>alert('이름이 틀립니다!'); location.href='/emaillist02/el'; </script>");
				out.flush();
			}
		}
		else {
			List<EmaillistVo> list = new EmaillistDao().findAll();
			
			request.setAttribute("list", list);
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/index.jsp");
			rd.forward(request, response);
			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
