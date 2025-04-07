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

@WebServlet(name = "AServlet", urlPatterns = "/A")
public class AServlet extends HttpServlet {
	@Serial private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		log.debug("service({},{}) invoked.", req.getClass().getSimpleName(), res.getClass().getSimpleName());
	
		try {
			// --1---
			log.info("\t* 1st.Runner");
			
			// --2---
			// 그 다음 선수에게 전달할 입력 데이터가 있다면,
			// 공유 데이터 영역인, Request Scope을 이용해서 전달하라!
			req.setAttribute("_RUNNER_", "AServlet"); 	// Binding, Re-binding

			// --3---
			// Request Forwarding 수행
//			RequestDispatcher dispatcher = req.getRequestDispatcher("/B");		// "/B" : target - 그 다음선수 지정
			
			// 부가적인 데이터 전달은 Request Scope을 이용하기도 하지만,
			// 우리가 잘 알고있는, GET방식(즉, URI에 Query String을 붙여서)으로도 전달 가능
			final String target = "/B?%s=%s&%s=%s".formatted("key1","value1","key2","value2");
			RequestDispatcher dispatcher = req.getRequestDispatcher(target);	// GET 방식으로도 요청파라미터 전달
			dispatcher.forward(req, res);
		}catch(Throwable t) {
			throw new ServletException(t);
		} // try-catch
		
	}// service

}// end class
