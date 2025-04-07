package org.zerock.myapp.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serial;
import java.util.Objects;

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

@WebServlet(name= "SportAndGenderServlet",urlPatterns = "/SportAndGender")
public class SportAndGenderServlet extends HttpServlet {
	@Serial private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		log.debug("service({},{}) invoked.", req.getClass().getSimpleName(), res.getClass().getSimpleName());
	
		try {
			
			log.info("\t + SportAndGenderServlet");
			
			//---
			// 요청처리
			//---
			res.setCharacterEncoding("UTF-8");	// ***
			
			// If no checked, return null(***)
			String[] sports = req.getParameterValues("sports");
			sports = Objects.requireNonNullElse(sports, new String[] {});	// If null, return empty array.
			
			String gender = req.getParameter("gender");
			gender = Objects.requireNonNullElse(gender,"");		// If null, return empty String
			
			//---
			// 응답처리
			//---
			
			res.setCharacterEncoding("UTF-8");
			res.setContentType("text/html; charset=UTF-8");
			
			@Cleanup PrintWriter out = res.getWriter();
			out.println("<html><body>");
			out.println("<h3>SportAndGenderServlet</h3><hr>");
			
			for(String sport : sports) {
				out.println("1. 좋아하는 운동: %s<br>".formatted(sport));
			}
			
			out.println("2. 성별: %s".formatted(gender));
			
			out.println("</body></html>");
			
			out.flush();
		}catch(Throwable t) {
			throw new IOException(t);
		}
	}

}
