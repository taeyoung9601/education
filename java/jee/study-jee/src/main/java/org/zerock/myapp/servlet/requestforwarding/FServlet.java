package org.zerock.myapp.servlet.requestforwarding;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serial;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor

@WebServlet(name = "FServlet", urlPatterns = "/F")
public class FServlet extends HttpServlet {
	@Serial private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		log.debug("service({},{}) invoked.", req.getClass().getSimpleName(), res.getClass().getSimpleName());
	
		try {
			// --1---
			PrintWriter out = res.getWriter();
			
			out.println("<html><body>");
			out.println("<h3>FServlet</h3>");
			
			// --2---
			RequestDispatcher dispatcher = req.getRequestDispatcher("/G");
			
			// target 으로 Include Request 를 보내어 수행시키고,
			// target 이 만든 응답을 현재의 서블릿의 응답에 "포함"시키겠다는 의미
			dispatcher.include(req, res);
						
		}catch(Throwable t) {
			throw new ServletException(t);
		} // try-catch
		
	}// service

}// end class
