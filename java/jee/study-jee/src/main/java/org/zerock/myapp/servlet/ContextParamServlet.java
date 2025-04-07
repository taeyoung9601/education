package org.zerock.myapp.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serial;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
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

@WebServlet(name ="ContextParamServlet", urlPatterns = "/ContextParam")
public class ContextParamServlet extends HttpServlet {
	@Serial private static final long serialVersionUID = 1L;

	@Override
	public void init(ServletConfig config) throws ServletException {
		log.debug("init({}) invoked.", config);
		super.init(config);
		
		ServletContext ctx = config.getServletContext();
		
		String param1 = ctx.getInitParameter("PARAM1");
		String param2 = ctx.getInitParameter("PARAM2");
		String param3 = ctx.getInitParameter("PARAM3");
		
		log.info("\t + param1({}), param2({}), param3({})", param1,param2,param3);
		
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		log.debug("service({},{}) invoked.", req.getClass().getSimpleName(), res.getClass().getSimpleName());
	
		try {
			
			log.info("\t + ContextParamServlet");
			
			//---
			// 요청처리
			//---
			ServletContext ctx = this.getServletContext();
			String param1 = ctx.getInitParameter("PARAM1");
			String param2 = ctx.getInitParameter("PARAM2");
			String param3 = ctx.getInitParameter("PARAM3");
			
			//---
			// 응답처리
			//---
			res.setCharacterEncoding("UTF-8");
			res.setContentType("text/html; charset=UTF-8");
			
			@Cleanup PrintWriter out = res.getWriter();
			out.println("<h3>ContextParamServlet</h3><hr>");
			out.println("<h2>PARAM1: %s</h2>".formatted(param1));
			out.println("<h2>PARAM2: %s</h2>".formatted(param2));
			out.println("<h2>PARAM3: %s</h2>".formatted(param3));
			out.flush();
		}catch(Throwable t) {
			throw new IOException(t);
		}
	}

}
