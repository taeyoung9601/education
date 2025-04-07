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

@WebServlet( name = "CartBasketCookieServlet", urlPatterns = "/CartBasketCookie")
public class CartBasketCookieServlet extends HttpServlet {
	@Serial private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		log.debug("service({},{}) invoked", req.getClass(), res.getClass());
		
		try {	// 장바구니에 저장된 모든 상품 보기 - 쿠키기반
			//--1---
			Cookie[] cookies = req.getCookies();
			if(cookies!=null) {
				for(Cookie cookie : cookies) {
					String cookieName = cookie.getName();
					String cookieValue = cookie.getValue();
					
					log.info("({},{})", cookieName, cookieValue );
				}
			}
			
			//--3---
			res.setCharacterEncoding("utf8");
			res.setContentType("text/html; charset=utf8");
			
			@Cleanup PrintWriter out = res.getWriter();
			
			for(Cookie cookie : cookies) {
				String cookieName = cookie.getName();
				String cookieValue = cookie.getValue();
				
				out.println("<ul>");
				out.println("<li>(%s,%s)</li>".formatted(cookieName,cookieValue));
				out.println("</ul>");
			}
			
			out.flush();
			
		}catch(Throwable t) {
			throw new IOException(t);
		}
		
	}// service

	
}// end class
