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
import lombok.Cleanup;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor

@WebServlet(name = "EServlet", urlPatterns = "/E")
public class EServlet extends HttpServlet {
	@Serial private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		log.debug("service({},{}) invoked.", req.getClass().getSimpleName(), res.getClass().getSimpleName());
	
		try {
			// --1---
			@Cleanup PrintWriter out = res.getWriter();
			
			out.println("<html><body>");
			out.println("<h3>EServlet</h3>");
			
			// --3---
			RequestDispatcher dispatcher = req.getRequestDispatcher("/F");
			dispatcher.include(req, res);
			
			out.println("</body></html>");
			
			out.flush();
			// --2---
			

		
			
		}catch(Throwable t) {
			throw new ServletException(t);
		} // try-catch
		
	}// service

}// end class
