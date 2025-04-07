package org.zerock.myapp.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serial;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Cleanup;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@NoArgsConstructor
@Slf4j

@WebServlet( name = "CartDeleteCookieServlet", urlPatterns = "/CartDeleteCookie")
public class CartDeleteCookieServlet extends HttpServlet {
	@Serial private static final long serialVersionUID = 1L;	

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		log.debug("service({},{}) invoked", req, res);
		
		try {// 클라이언트에 저장된 모든 쿠키 삭제 -> 장바구니 삭제
			// 배열로 다시 얻은 각각의 쿠키의 유효시간을 0(초) 또는 1(초)로 만들어서
			// 다시 보내면, 클라이언트 브라우저는 변경된 유효시간으로 각 쿠키만료삭제
			
			//--1----
			Cookie[] cookies = req.getCookies();
			
			//--2----
			for (Cookie cookie : cookies) {
				cookie.setMaxAge(1);	// 1초동안만 유효
				res.addCookie(cookie); 	// HTTP response 의 Set-Cookie 헤더에, 계속 쿠키추가
			}// enhanced for
			
			//--3----
			res.setCharacterEncoding("utf8");
			res.setContentType("text/html; charset=utf8");
			
			@Cleanup PrintWriter out = res.getWriter();
			
			out.println("<p>모든 쿠키의 유효기간을 1초로 재설정완료되었습니다.</p>");
			out.println("<p>모든 쿠키의 삭제완료</p>");
			
			out.flush();
			
		}catch(Throwable t) {
			throw new IOException(t);
		}
		
		
	}// service

	
}// end class
