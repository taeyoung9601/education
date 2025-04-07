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

@WebServlet(name = "HServlet", urlPatterns = "/H")
public class HServlet extends HttpServlet {
	@Serial private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		log.debug("service({},{}) invoked.", req.getClass().getSimpleName(), res.getClass().getSimpleName());
	
		try {
			//--1-------
			log.info("\t+ IServlet");
			
			//--2---
			final String model = "비지니스데이터";
			
			//--3---
			// Binding model into the Request Scope shared area.
			req.setAttribute("_MODEL_", model);
			
			//--4-----
			// Forwarding request to JSP to make a HTML response more easily.
//			String target = "/WEB-INF/views/response.jsp";
			
			RequestDispatcher dispatcher = req.getRequestDispatcher("/I");
			dispatcher.forward(req, res);
			
		}catch(Throwable t) {
			throw new ServletException(t);
		}
	}// service

}// end class
