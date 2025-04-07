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

@WebServlet(name= "LoginServlet",urlPatterns = "/Login")
public class LoginServlet extends HttpServlet {
	@Serial private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		log.debug("service({},{}) invoked.", req.getClass().getSimpleName(), res.getClass().getSimpleName());
	
		try {
			
			log.info("\t + LoginServlet");
			
			//---
			// 요청처리
			//---
			String userid = req.getParameter("userid");
			String passwd = req.getParameter("passwd");
			
			log.info("\t + userid: {} , passwd: {}", userid, passwd);
			
			Objects.requireNonNull(userid);
			Objects.requireNonNull(passwd);
			
			//---
			// 응답처리
			//---
			
			res.setCharacterEncoding("UTF-8");
			res.setContentType("text/html; charset=UTF-8");
			
			@Cleanup PrintWriter out = res.getWriter();
			out.println("<html><body>");
			out.println("<h3>LoginServlet</h3><hr>");
			
			out.println("1. userid: %s".formatted(userid));
			out.println("2. passwd: %s".formatted(passwd));
			
			out.println("</body></html>");
			
			out.flush();
		}catch(Throwable t) {
			throw new IOException(t);
		}
	}

}
