package org.zerock.myapp.servlet;import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serial;

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
          
@WebServlet(name = "GetAttributeFromAppScopeServlet", urlPatterns = "/GetAttributeFromAppScope")
public class GetAttributeFromAppScopeServlet extends HttpServlet {
	@Serial private static final long serialVersionUID = 1L;
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		log.debug("service({},{})", req.getClass().getSimpleName(), res.getClass().getSimpleName());
	
		try {
			//---
			// 요청처리
			ServletContext ctx = this.getServletContext();
			
			//--1---
			String param1 = (String)ctx.getAttribute("PARAM1");
			String param2 = (String)ctx.getAttribute("PARAM2");
			String param3 = (String)ctx.getAttribute("PARAM3");
			
			log.info("\t + param1: {} ", param1);
			log.info("\t + param2: {} ", param2);
			log.info("\t + param3: {} ", param3);
			
			//--2---
			String _param1 = (String)ctx.getAttribute("_PARAM1_");
			String _param2 = (String)ctx.getAttribute("_PARAM2_");
			String _param3 = (String)ctx.getAttribute("_PARAM3_");
			
			log.info("\t + _param1: {} ", _param1);
			log.info("\t + _param2: {} ", _param2);
			log.info("\t + _param3: {} ", _param3);
			
			//--3-----------
			// App Scope에 바인딩 되어있는 6개의 공유 속성을 언바인딩(Unbinding, 삭제)
			ctx.removeAttribute("PARAM1");
			ctx.removeAttribute("PARAM2");
			ctx.removeAttribute("PARAM3");
			
			ctx.removeAttribute("_PARAM1_");
			ctx.removeAttribute("_PARAM2_");
			ctx.removeAttribute("_PARAM3_");  
			
			//---
			// 응답처리
			res.setCharacterEncoding("UTF-8");
			res.setContentType("text/html; charset=UTF-8");
			
			@Cleanup PrintWriter out = res.getWriter();
			
			out.println("<h3>init callback: param1(%s), param2(%s), param3(%s)</h3>".formatted(param1,param2,param3));
			out.println("<h3>init callback: _param1(%s), _param2(%s), _param3(%s)</h3>".formatted(_param1,_param2,_param3));
			out.flush();
			
		}catch(Throwable t) {
			throw new IOException(t);
		}
		
	}// service

}	// end class
