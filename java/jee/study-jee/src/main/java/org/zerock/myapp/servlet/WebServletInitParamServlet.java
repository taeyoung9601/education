package org.zerock.myapp.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serial;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Cleanup;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor

@WebServlet( 
		name = "/WebServletInitParamServlet",
		urlPatterns = "/WebServletInitParam",
		initParams = { 
				@WebInitParam(name = "jdbcDriver", value = "VALUE1"), 
				@WebInitParam(name = "jdbcUrl", value = "VALUE2"), 
				@WebInitParam(name = "jdbcUser", value = "VALUE3"), 
				@WebInitParam(name = "jdbcPass", value = "VALUE4")
		})
public class WebServletInitParamServlet extends HttpServlet {
	@Serial private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		log.debug("service({},{}) invoked.", req, res);
		
		try {
			
			log.info("\t + WebServletInitParamServlet");
			
			//---
			// 요청처리
			//---
			
			String jdbcDriver = this.getInitParameter("jdbcDriver");
			String jdbcUrl = this.getInitParameter("jdbcUrl");
			String jdbcUser = this.getInitParameter("jdbcUser");
			String jdbcPass = this.getInitParameter("jdbcPass");
			
			log.info("\t + jdbcDriver({}), jdbcUrl({}), jdbcUser({}),jdbcPass({})", jdbcDriver, jdbcUrl,jdbcUser,jdbcPass );
			
			//---
			// 응답처리
			//---
			
			res.setCharacterEncoding("UTF-8");
			res.setContentType("text/html; charset=UTF-8");
			
			@Cleanup PrintWriter out = res.getWriter();
			out.println("<html><body>");
			out.println("<h3>WebServletInitParamServlet</h3><hr>");
			out.println("</body></html>");
			out.flush();
		}catch(Exception e) {
			throw new IOException(e);
		}
	}

}
