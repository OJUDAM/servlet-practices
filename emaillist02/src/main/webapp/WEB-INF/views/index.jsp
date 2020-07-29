<%@page import="com.bit2020.emaillist02.dao.EmaillistDao"%>
<%@page import="com.bit2020.emaillist02.vo.EmaillistVo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
    List<EmaillistVo> list = new EmaillistDao().findAll();
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>이메일 리스트에 오신것을 환영합니다~~~!!</h1>
	<hr>
	<h3><a href="<%= request.getContextPath()%>/el?a=form">>>메일 등록<<</a></h3>
	<%
		for(EmaillistVo vo : list){
	%>
	<!-- 메일정보 리스트 -->
	<table border="1" cellpadding="5" cellspacing="2">
		
		<tr>
			<td align=right>First name:</td>
			<td><%=vo.getFirstName() %></td>
			
		</tr>
		<tr>
			<td align=right width="110">Last name:</td>
			<td width="110"><%=vo.getLastName() %></td>
		</tr>
		<tr>
			<td align=right>Email address:</td>
			<td><%=vo.getEmail() %></td>
		</tr>
		<tr>
			<a href="<%= request.getContextPath()%>/el?a=deleteform&no=<%=vo.getNo()%>">삭제</a>
		</tr>
	</table>
	<br>
	<%
		}
	%>
	
	<br>
</body>
</html>