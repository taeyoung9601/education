package org.zerock.myapp.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Cleanup;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.PrintWriter;

@NoArgsConstructor
@Slf4j

@WebServlet("/T")
public class T extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		log.debug("service({},{}) invoked." , req, res);
		
		try {
			
			res.setCharacterEncoding("UTF-8");
			res.setContentType("text/html; charset=UTF-8");
			
			@Cleanup PrintWriter out = res.getWriter();
			out.println("<html><body>");
			out.println("<h3>로그인</h3>");
			out.println("<input placeholder= 아이디></input>");
			out.println("<input placeholder= 비밀번호></input>");
			out.println("</body></html>");
//			out.write("Today is Friday!!!!!!!z!!!!!!!!");
			out.flush();			// 출력 버퍼 강제 방출
			
		}catch(Throwable t) {
			
			throw new ServletException(t);
			
		}// try catch

	}

}
