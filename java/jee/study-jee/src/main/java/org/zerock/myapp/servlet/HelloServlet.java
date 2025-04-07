package org.zerock.myapp.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serial;
import java.util.Arrays;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Cleanup;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
//@Log4j2
@NoArgsConstructor

@WebServlet({"/Hello", "/a", "/b" , "/c2" })
public class HelloServlet extends HttpServlet {
	@Serial private static final long serialVersionUID = 1L;

	// HTTP request 의 HTTP method 가 GET방식이든, POST 방식이든, 그 외의 표준에 정의된 다른
	// 전송방식이든 무조건 요청을 받고 처리할 수 있는 유일한 메소드입니다!
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		log.debug("service({},{}) invoked." , req, res);
		
		try {
			// 1. 요청 처리
			//----
			// 1-1.Request Parameters 획득
			log.info("\t + Request URL : {}", req.getRequestURL());
			log.info("\t + Request URI : {}", req.getRequestURI());
			log.info("\t + Parameter Map : {}", req.getParameterMap());
			
			String key1 = req.getParameter("key1");		// getParameter : 여러개의 값이 전송되었을 때에, 가장 처음 값만 반환
			String[] key2 = req.getParameterValues("key2");	
			String key3 = req.getParameter("key3");
			
			log.info(key1);
			log.info(Arrays.toString(key2));
			log.info(key3);
			
			//---
			// 비지니스 로직의 수행
			//---
			
			
			// 2. 응답 처리 (자바 I/O) - SSR (Server - Side Rendering)
			//----
			// Set character encoding of HTTP response message
			// HTTP response 문자열의 인코딩 설정
			res.setCharacterEncoding("UTF-8");
			// HTTP response body에 저장한 HTML 문서의 인코딩
			res.setContentType("text/html; charset=UTF-8");
			
			// HTTP response body(=Payload)에 응답 데이터 출력
			@Cleanup PrintWriter out = res.getWriter();
			out.println("<html><body>");
			out.println("<h3>(key1,key2,key3) = (%s,%s,%s)</h3>".formatted(key1,Arrays.toString(key2),key3));
			out.println("<a href=T>로그인</a>");
			out.println("</body></html>");
//			out.write("Today is Friday!!!!!!!z!!!!!!!!");
			out.flush();			// 출력 버퍼 강제 방출
			
		}catch(Throwable t) {
			
			throw new ServletException(t);
			
		}// try catch

	}

}