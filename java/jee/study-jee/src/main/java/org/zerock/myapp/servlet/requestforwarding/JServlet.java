package org.zerock.myapp.servlet.requestforwarding;

import java.io.IOException;
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

@WebServlet(name = "JServlet", urlPatterns = "/J")
public class JServlet extends HttpServlet {
	@Serial private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		log.debug("service({},{}) invoked.", req.getClass().getSimpleName(), res.getClass().getSimpleName());
	
		try {
			//--1-------
			log.info("\t+ IServlet");
			
			//--2---
			req.setAttribute("_MODEL_", "JServlet");
			
			//--3-----
			final String target = "/WEB-INF/views/response.html";
			
			RequestDispatcher dispatcher = req.getRequestDispatcher(target);
			dispatcher.forward(req, res);
			
		}catch(Throwable t) {
			throw new ServletException(t);
		} // try-catch
		
	}// service

}// end class
