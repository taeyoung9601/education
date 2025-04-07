<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>${pageContext.request.requestURI}</h3>
	<hr>
	
	<div>
		<p>1. My Name: ${personDto.getName()}</p>
		<p>2. My Age: ${personDto.age}</p>
		<p>3. model : ${_MODEL_.name}</p>
		<p>4. model : ${_MODEL_.age}</p>
	</div>
</body>
</html>