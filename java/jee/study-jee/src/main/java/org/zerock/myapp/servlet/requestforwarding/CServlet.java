package org.zerock.myapp.servlet.requestforwarding;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.Serial;

@Slf4j
@NoArgsConstructor

@WebServlet(name = "CServlet", urlPatterns = "/C")
public class CServlet extends HttpServlet {
	@Serial private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		log.debug("service({},{}) invoked.", req.getClass().getSimpleName(), res.getClass().getSimpleName());
	
		try {
			// --1---
			log.info("\t* 3rd.Runner");
			
			// --2---
			// 그 다음 선수에게 전달할 입력 데이터가 있다면,
			// 공유 데이터 영역인, Request Scope을 이용해서 전달하라!
			log.info("\t + Pre-Runner:{}", req.getAttribute("_RUNNER_"));
			req.setAttribute("_RUNNER_", "CServlet"); 	// Re-binding
			
			// --3---
			RequestDispatcher dispatcher = req.getRequestDispatcher("/D"); // "/D"로 매핑된 서블릿으로 요청위임
			dispatcher.forward(req, res);
			
		}catch(Throwable t) {
			throw new ServletException(t);
		}
	}// service

}// end class
