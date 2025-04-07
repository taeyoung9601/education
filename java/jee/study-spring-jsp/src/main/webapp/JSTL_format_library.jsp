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
	<title>JSTL format library</title>
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
	
	<h3>01. &lt;fmt:formatDate&gt;</h3>
	<p>현업에서 가장 필요한 포맷 라이브러리 태그</p>
	
	<c:set var="now" value="<%= new java.util.Date()  %>" />
	
	<span>now1: ${now}</span><br>
	<span>now2: <fmt:formatDate value="${now}" pattern="yyyy/MM/dd HH:mm:ss.SSS"/></span><br>
	<span>now3: <fmt:formatDate value="${now}" pattern="yyyy/MM/dd"/></span><br>
	<span>now4: <fmt:formatDate value="${now}" pattern="HH:mm:ss.SSS"/></span><br>
	
	<h3>02. &lt;fmt:setTimeZone&gt;</h3>
	<span>Please refer to the GPT archived link.</span>
	
	<h3>03. &lt;fmt:timeZone&gt;</h3>
	<span>Please refer to the GPT archived link.</span>
	
	<h3>04. &lt;fmt:formatNumber&gt;</h3>
	<span>Please refer to the GPT archived link.</span>
	
	<h3>05. &lt;fmt:parseDate&gt;</h3>
	<span>Please refer to the GPT archived link.</span>
	
	<h3>06. &lt;fmt:parseNumber&gt;</h3>
	<span>Please refer to the GPT archived link.</span>
	
	<h3>07. &lt;fmt:requestEncoding&gt;</h3>
	<span>Please refer to the GPT archived link.</span>
	
	<h3>08. &lt;fmt:setBundle&gt;, &lt;fmt:message&gt;</h3>
	<span>Please refer to the GPT archived link.</span>
	
<!-- 	
	<fmt:setBundle basename="messages" var="myBundle" /> myBundle: ${myBundle} <br>
	<fmt:message key="greeting" bundle="${myBundle}" />	
	
	<hr>

	<fmt:bundle basename="messages">
	    <fmt:message key="greeting" />
	</fmt:bundle>
 -->

	<h3>09. &lt;fmt:bundle&gt;, &lt;fmt:message&gt;, &lt;fmt:param&gt;</h3>
	<span>Please refer to the GPT archived link.</span>
	
<!-- 	
	<fmt:bundle basename="messages">
	    <fmt:message key="greeting">
	        <fmt:param value="Yoseph" />
	    </fmt:message>
	</fmt:bundle>	
 -->
	
	<h3>10. &lt;fmt:setLocale&gt;</h3>
<!-- 	
    <fmt:setLocale value="fr_FR" />
    <fmt:setLocale value="ko_KR" />

	<fmt:bundle basename="messages">
	    <fmt:message key="greeting" />
	</fmt:bundle>
  -->   

</body>
</html>

