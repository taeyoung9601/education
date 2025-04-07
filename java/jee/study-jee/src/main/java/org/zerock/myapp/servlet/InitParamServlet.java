package org.zerock.myapp.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serial;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Cleanup;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor


public class InitParamServlet extends HttpServlet {
	@Serial private static final long serialVersionUID = 1L;

	@Override
	public void init(ServletConfig config) throws ServletException {
		log.debug("init({}) invoked.", config);
		super.init(config);
		
		String jdbcDriver = config.getInitParameter("jdbcDriver");
		String jdbcUrl = config.getInitParameter("jdbcUrl");
		String jdbcUser = config.getInitParameter("jdbcUser");
		String jdbcPass = config.getInitParameter("jdbcPass");
		
		log.info("1. jdbcDriver({}), jdbcUrl({}), jdbcUser({}),jdbcPass({})", jdbcDriver, jdbcUrl,jdbcUser,jdbcPass );
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		log.debug("service({},{}) invoked.", req.getClass().getSimpleName(), res.getClass().getSimpleName());
	
		try {
			
			log.info("\t + InitParamServlet");
			
			//---
			// 요청처리
			//---
			
			String jdbcDriver = this.getInitParameter("jdbcDriver");
			String jdbcUrl = this.getInitParameter("jdbcUrl");
			String jdbcUser = this.getInitParameter("jdbcUser");
			String jdbcPass = this.getInitParameter("jdbcPass");
			
			log.info("2. jdbcDriver({}), jdbcUrl({}), jdbcUser({}),jdbcPass({})", jdbcDriver, jdbcUrl,jdbcUser,jdbcPass );
			
			//---
			// 응답처리
			//---
			
			res.setCharacterEncoding("UTF-8");
			res.setContentType("text/html; charset=UTF-8");
			
			@Cleanup PrintWriter out = res.getWriter();
			out.println("<html><body>");
			out.println("<h3>InitParamServlet</h3><hr>");
			out.println("</body></html>");
			out.flush();
		}catch(Throwable t) {
			throw new IOException(t);
		}
	}


}
