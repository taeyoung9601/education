package org.zerock.myapp.servlet.requestforwarding;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serial;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Cleanup;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor

@WebServlet(name = "DServlet", urlPatterns = "/D")
public class DServlet extends HttpServlet {
	@Serial private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		log.debug("service({},{}) invoked.", req.getClass().getSimpleName(), res.getClass().getSimpleName());
	
		try {
			// --1---
			log.info("\t* 4th.Runner");
			
			// --2---
			// 그 다음 선수에게 전달할 입력 데이터가 있다면,
			// 공유 데이터 영역인, Request Scope을 이용해서 전달하라!
			log.info("\t + Pre-Runner:{}", req.getAttribute("_RUNNER_"));
			req.setAttribute("_RUNNER_", "DServlet"); 	// Re-binding
			
			// --3---
			// 응답처리 수행
			
			res.setCharacterEncoding("utf8");	// UTF-8, UTF89.utf-8,utf8
			res.setContentType("text/html; charset=utf8");
			
			@Cleanup PrintWriter out = res.getWriter();
			
			out.println("<h1>----우승----</h1>");
			
			out.flush();
		}catch(Throwable t) {
			throw new ServletException(t);
		}
	}// service

}// end class
