<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%-- For JSTL 3.x --%>
<%@ taglib uri="jakarta.tags.core" 	prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" 	prefix="fmt" %>

<%-- For JSTL 1.x --%>
<%-- @ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" --%>
<%-- @ taglib uri="http://java.sun.com/jsp/jstl/fmt" 	prefix="fmt" --%>

<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>Member</title>
	
	<style>
		a { text-decoration: none; }
	</style>
</head>

<body>
	<h3>${pageContext.request.requestURI}</h3>
	<hr>
	
	<h3><a href="/security/logout">Custom Logout</a></h3>

</body>
</html>

