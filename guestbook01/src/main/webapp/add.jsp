<%@page import="com.bit2020.guestbook01.dao.guestbookDao.GuestBookDao"%>
<%@page import="com.bit2020.guestbook01.vo.guestbookvo.GuestBookVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
	String name = request.getParameter("name");
	String password = request.getParameter("password");
	String message = request.getParameter("message");
	
	GuestBookVo vo = new GuestBookVo();
	vo.setName(name);
	vo.setPassword(password);
	vo.setMessage(message);
	
	new GuestBookDao().insert(vo);
	
	response.sendRedirect("index.jsp");
%>
