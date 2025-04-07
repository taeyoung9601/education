<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Date" %>

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
	<title>JSTL core library</title>
    <style>
        body {
            font-family: D2Coding, sans-serif;
            font-size: 1.1em;
        }
        h3 {
        	color: red;'
        }
    </style>
</head>

<body>
	<h3>${pageContext.request.requestURI}</h3>
	<hr>
	
	<h3>01. &lt;c:set&gt;</h3>	
	<p>EL 변수를 만들어 내는데 사용 - 자연스럽게 EL표현식 문법인 <code>&#36;&#123;EL변수명&#125;</code>으로 출력가능</p>
	
	<c:set var="name" value="Pyramide"/>
	<p>Name: ${name}</p>
	
	
	<h3>02. &lt;c:remove&gt;</h3>
	
	<c:remove var="name"/>
	<p>Name: ${name}</p>
	
	
	<h3>03. &lt;c:choose&gt;, &lt;c:when&gt;, &lt;c:otherwise&gt;</h3>
	
	<c:set var="age" value="87" />
	
	<c:choose>
		<c:when test="${age > 17 and age <= 65}">
			<p>성인이시네요!?</p>
		</c:when>	
		
		<c:when test="${age <= 17}"	>
			<p>미성년자이시네요!?</p>
		</c:when>
		
		<c:otherwise>
			<p>노년층이시네요!?</p>
		</c:otherwise>
	</c:choose>
	
		
	<h3>04. &lt;c:forEach&gt;</h3>
	
	<c:forEach var="counter" begin="1" end="7" step="1">
		<span>counter: ${counter}</span><br>
	</c:forEach>
	
	<hr>
	
	names1: ${ [ 'NAME-1', 'NAME-2', 'NAME-3' ] } <br>
	
	<c:set var="names" value="${ [ 'NAME-1', 'NAME-2', 'NAME-3' ] }" />
	
	<c:forEach var="name" items="${names}">
		<span>name: ${name}</span><br>
	</c:forEach>
	
	
	<h3>05. &lt;c:forTokens&gt;</h3>
	
	<c:set var="fruits" value="apple,banana,grape" />
	
	<c:forTokens var="fruit" items="${fruits}" delims=",">
		<span>fruit: ${fruit}</span><br>
	</c:forTokens>
	
	<h3>06. &lt;c:if&gt;</h3>
	
	<c:set var="score" value="97" />
	
	<c:if test="${score > 90}">
		<span>Wonderful !</span><br>
	</c:if>
	
	<c:if test="${score > 80 and score <= 90}"><br>
		<span>Good !</span>
	</c:if>
	
	<h3>07. &lt;c:out&gt;</h3>
	
	<c:set var="myName" value="Yoseph" />
	<span>myName1: ${myName}</span><br>
	<span>myName2: <c:out value="${myName}" /></span><br>
	
	<h3>08. &lt;c:redirect&gt;, &lt;c:param&gt;</h3>
	
	<%-- <c:redirect url="https://naver.com" /> --%>
	
	<%--
	<c:redirect url="/person/me">
		<c:param name="name" value="Yoseph" />
		<c:param name="age" value="23" />
	</c:redirect>
	 --%>
	
	<h3>09. &lt;c:url&gt;, &lt;c:param&gt;</h3>
	
	<c:url var="goToPersonMe" value="/person/me">
		<c:param name="name" value="Yoseph" />
		<c:param name="age" value="23" />
	</c:url>
	
	<span>url: ${goToPersonMe}</span><p>
	
	<a href="${goToPersonMe}">go To PersonMe</a>
	
	<h3>10. &lt;c:import&gt;</h3>
	<p>이 import 는 한 개의 화면을, 여러개의 JSP로 나누어서 구현하는 페이지 모듈화에서 사용</p>
	
	<c:import url="/header.jsp" />
	
	<h3>Center</h3>
	
	<c:import url="/footer.jsp" />
	
	<h3>11. &lt;c:catch&gt;</h3>
    
    <c:catch var="ex">
    	<span>Try-Block</span><br>
    	
    	<% int result = 10 / 0; %>
    </c:catch>
	
	<c:if test="${not empty ex}">
		<span>${ex}</span><br>
	</c:if>
	
	

</body>
</html>

