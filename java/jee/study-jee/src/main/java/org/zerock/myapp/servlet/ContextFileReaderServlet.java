package org.zerock.myapp.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Serial;
import java.util.Objects;

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

@WebServlet(name = "ContextFileReaderServlet", urlPatterns = "/ContextFileReader")
public class ContextFileReaderServlet extends HttpServlet {
	@Serial private static final long serialVersionUID = 1L;
	
	// Document Root ( 즉 , webapp 폴더) 부터 시작하면 됩니다.
	private final String path = "/WEB-INF/temp.txt";
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		log.debug("service({},{})", req.getClass().getSimpleName(), res.getClass().getSimpleName());
		
		log.info("\t + path: {}", path);
		
		try {
			// -------
			// 요청 처리
			//--1---
			ServletContext ctx = this.getServletContext();
			log.debug("this :{}", this);
			Objects.requireNonNull(ctx);
			
			//--2---
			@Cleanup InputStream is = ctx.getResourceAsStream(path);
			log.debug("is :{}", is);
			Objects.requireNonNull(is);
			
			@Cleanup BufferedReader br = new BufferedReader(new InputStreamReader(is));
			log.debug("br :{}", br);
			Objects.requireNonNull(br);
			
			//--3---
			StringBuffer sb = new StringBuffer();		// Thread-Safe
//			StringBuilder sb = new StringBuilder();		// Thread-Unsafe
			
			String line = null;
			while((line = br.readLine())!= null) { //EOF: leader
				sb.append(line);
			}
			
			log.info("=".repeat(80));
			System.out.println(sb.toString());
			log.info("=".repeat(80));
			
			// -------
			// 응답 처리
			//--1---
			res.setCharacterEncoding("UTF-8");
			res.setContentType("text/html; charset=UTF-8");
			
			@Cleanup PrintWriter out = res.getWriter();
			out.println("<html><body>");
			out.println("<textarea style='width=760px; height=560px;'>%s</textarea>".formatted(sb.toString()));
			out.println("</body></html>");
			
			out.flush();
			
		}catch(Throwable t) {
			throw new IOException(t);
		}
	}// service

}	// end class
