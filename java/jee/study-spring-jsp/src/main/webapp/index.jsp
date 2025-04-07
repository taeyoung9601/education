<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%-- For JSTL 3.x --%>
<%@ taglib uri="jakarta.tags.core"    prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt"    prefix="fmt" %>

<%-- For JSTL 1.x --%>
<%-- @ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" --%>
<%-- @ taglib uri="http://java.sun.com/jsp/jstl/fmt"    prefix="fmt" --%>

<!DOCTYPE html>
<html>

<head>
   <meta charset="UTF-8">
   <title>Home</title>
</head>

<body>
   <h2>${pageContext.request.requestURI}</h3>
   <hr>
   
   <h2>1. JSTL - Core library Feature: </h3>
   <p>EL 변수를 만들어 내는데 사용 - 자연스럽게 EL 표현식 문법인 ${EL변수명}으로 출력 가능</p>
   
   <h3>01. &lt;c:set&gt;</h3>
   <c:set var ="name" value="Pyramide"/>
   <p>Name: ${name}</p>
   
   <h3>02. &lt;c:remove&gt;</h3>
   <c:remove var="name"/>
   <p>Name: ${name}</p>
   
   <h3>03. &lt;c:choose&gt;,&lt;c:when&gt;, &lt;c:otherwise&gt;</h3>
   
   <c:set var="age" value="87"/>
   
   <c:choose>
  		<c:when test="${age >17 and age <= 65}">
  			<p>성인입니다</p>
  		</c:when>
  		
  		<c:when test= "${age < 17}">
  			<p>성인이 아니네요</p>
  		</c:when>
  		
  		<c:otherwise>
  			<p>노인이시네요</p>
  		</c:otherwise>
   </c:choose>
   
   <h3>04. &lt;c:forEach&gt; </h3>
   
   <c:forEach var="counter" begin="1" end="7" step="1">
   		<span>counter: ${counter}</span><br>
   </c:forEach>
   
   <hr>
   
   names1: ${['NAME-1','NAME-2','NAME-3']} <br>
   <c:set var="names" value="${['NAME-1','NAME-2','NAME-3']}"/>
   
   <c:forEach var= "name" items="${names}">
   		<span>name: ${name}</span><br>
   </c:forEach>
   
   <h3>05. &lt;c:forTokens&gt;</h3>
   
   <c:set var="fruits" value="apple,banana,grape" />
   
   <c:forTokens var="fruit" items="${fruits}" delims=",">
      <span>fruit: ${fruit}</span><br>
   </c:forTokens>
   
 
    <h3>06. &lt;c:if&gt;</h3>
        
        <c:set var="score" value="97"/>
        
        <c:if test="${score > 90}">
           <span>Good !</span>
        </c:if>
   
         <c:if test="${score > 80 and score <= 90}">
         <span>Not Bad !</span>
        </c:if>

   <h3>07. &lt;c:out&gt;</h3>
   
   <c:set var="myName" value="Tae"/>
   <span>myName1: ${myName}</span><br>
   <span>myName2: <c:out value="${myName}"/></span><br>
   
   <h3>08. &lt;c:redirect&gt;</h3>
   
   <%--<c:redirect url="https://naver.com"/>--%>
   
   <%-- 
   <c:redirect url="/person/me">
   		<c:param name="name" value="Tae"/>
   		<c:param name="age" value="27"/>
   </c:redirect>
   --%>	
	
   <h3>09. &lt;c:url&gt;, &lt;c:param&gt;</h3>
   
   <c:url var="goToPersonMe" value="/person/me">
   		<c:param name= "name" value="Tae"/>
   		<c:param name= "age" value= "27"/>
   </c:url>
   
   <span>url:${goToPersonMe}</span><p></p>
	
   <a href="${goToPersonMe}">goToPersonMe</a>
	
   <h3>10. &lt;c:import&gt;</h3>
   <p>이 import는 한 개의 화면을, 여러 개의 JSP로 나누어서 구현하는 페이지 모듈화에서 사용</p>
   
   <c:import url="/header.jsp"/>
   
   <h3>Center</h3>
   
   <c:import url="/footer.jsp"/>
   <hr>
   
   
   <h3>11. &lt;c:catch&gt;</h3>
   
   <c:catch var="ex">
   		<span>Try-Block</span>
   		
   		<% int result = 10/0; %>
   </c:catch>
   
   <c:if test="${not empty ex}">
   		<span>${ex}</span><br>
   </c:if>
   
   <h2>1. JSTL - Formatting library Feature:</h2>
   
   <h3>01. &lt;fmt:formatDate&gt;</h3>
   <p>현업에서 가장 필요한 포멧 라이브러리 태그</p>
   
   <c:set var="now" value= "<%=new java.util.Date() %>"/>
   <span>now1:${now}</span>
   <span>now2:<fmt:formatDate pattern="yyyy/MM/dd HH:mm:ss.SSS" value="${now}"/></span>
   <span>now3:<fmt:formatDate pattern="yyyy/MM/dd" value="${now}"/></span>
   <span>now4:<fmt:formatDate pattern="HH:mm:ss.SSS" value="${now}"/></span>
	
	<h3>02. &lt;fmt:setTimeZone&gt;</h3>
	<span>Please refer to the GPT archived link</span>
	
	<h3>03. &lt;fmt:TimeZone&gt;</h3>
	
	<h3>04. &lt;fmt:setTimeZone&gt;</h3>
	
	<h3>05. &lt;fmt:parseDate&gt; </h3>
	<h3>06. &lt;fmt:parseNumber&gt; </h3>
	<h3>07. &lt;fmt:&gt; </h3>
	<h3>08. &lt;fmt:&gt; </h3>
</body>
</html>

