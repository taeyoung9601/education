<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>응답화면</title>
</head>
<body>
	<h3>/WEB-INF/views/response.jsp</h3>
	<hr>
	
	<p>Model: ${_MODEL_}</p>
	<p>key1:<%= request.getParameter("key1") %></p>
	<p>key2:<%= request.getParameter("key2") %></p>
	
</html>