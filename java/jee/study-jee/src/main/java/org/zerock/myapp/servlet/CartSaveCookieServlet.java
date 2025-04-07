package org.zerock.myapp.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serial;

import org.apache.catalina.tribes.util.Arrays;

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

@WebServlet( name = "CartSaveCookieServlet", urlPatterns = "/CartSaveCookie")
public class CartSaveCookieServlet extends HttpServlet {
	@Serial private static final long serialVersionUID = 1L;
	Cookie cookie;
	

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		log.debug("service({},{}) invoked", req, res);
		
		try {	// Cookie 기반의 장바구니 구현
			//--1---
			final String product = req.getParameter("product");
			log.info("\t1. product:{}", product);
			
			if(product == null) {
				res.sendError(400, "No product found.");
				
				return ;
			}
			//--2---
			Cookie[] cookies =  req.getCookies();		//현재 요청을 보낸 URL에 대한 쿠키 저장소가 텅 비어 있으면 
														// 이 배열은 null이 되겠죠.
			log.info("\t2. cookies:{}", Arrays.toString(cookies));
		
			//--3---
			if(cookies == null || cookies.length == 0) {	// 클라이언트에서 보낸 쿠키가 하나도 없다면.
//				cookie = new Cookie("product-"+1,product);
				cookie = new Cookie("_AUTHENTICATED_", "SUCCESS");
			}else {
				// 쿠키마다 고유한 키 값을 가져야 한다 -> prefix: product +1/2/3/...
				cookie = new Cookie("product-"+(cookies.length+1), product);	// 문제가 생긴다!
			}
			
			// if set 0 -> Delete this cookie.
			// In seconds
			cookie.setMaxAge(1* 60 * 60);	// timeout: 1 hour.
			
			log.info("\t3. cookie: {}", cookie);
			
			//--4---
			// HTTP response's 'Set-Cookie' header 에 포함되어 전송하면
			// 웹 표준에 의해서, 웹 브라우저는 응답의 이 헤더항목에 값이 있으면 (즉, 쿠키가 설정되어 있으면)
			// 쿠키 파일에 저장 -> 개발자 도구 -> 쿠키 저장소에 보이게 됩니다.
			res.addCookie(cookie);
			
			//--5---
			res.setCharacterEncoding("utf8");
			res.setContentType("text/html; charset=utf8");
			
			@Cleanup PrintWriter out = res.getWriter();
			
			out.println("<h3>장바구니에 저장성공- 클라이언트에 쿠키로 저장됨.</h3>");
			
			out.flush();
			
		}catch(Throwable t) {
			throw new IOException(t);
		}
		
	}// service

	
}// end class
