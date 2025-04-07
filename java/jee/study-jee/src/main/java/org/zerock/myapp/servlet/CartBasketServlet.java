package org.zerock.myapp.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serial;
import java.util.Set;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.Cleanup;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@NoArgsConstructor
@Slf4j

@WebServlet( name = "CartBasketServlet", urlPatterns = "/CartBasket")
public class CartBasketServlet extends HttpServlet {
	@Serial private static final long serialVersionUID = 1L;
	private static final String CART_KEY = "_PRODUCTS_";
	

	@SuppressWarnings("unchecked")
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		log.debug("service({},{}) invoked", req.getClass(), res.getClass());
		
		try {
			// 특정 웹 브라우저에 할당된 Session Scope(=금고 상자)에 저장되어 있는, 장바구니 역할을 하는
			// Set<String>을 끄집어내어(그렇다고 진짜 끄집어내는게 아니라, 주소만 얻어내어,) 셋안에 포함되어있는
			// 구슬들(=이게 요소객체이죠)의 목록을 콘솔과 응답으로 주면, 현재 장바구니의 목록을 확인 가능하겠죠.
			// 이걸 하는게 이 두 번째 서블릿 컴포넌트입니다.
			
			//--1----
			// 이 서블릿의 역할은 장바구니 확인지, 새로운 요청에 대한 세션생성은 포함하지 않습니다.
			HttpSession sess = req.getSession(false);	// false: 가용한 세션이 없어도, 새로운 세션을 만들어내지말라
			
			//--2---
			if(sess == null) { // 현재 이 서블릿으로 요청을 보낸 웹 브라우저에 대해서는, 세션(금고상자,ID)가 없다!
				res.sendError(400,"No available session scope found."); // 400 , Bad Request
				
				return ;
			}
			
			//--3---
			Object obj = sess.getAttribute(CART_KEY);
			if(obj == null) {	// 세션(=금고상자)은 있는데, 그 안에 장바구니 Set<String> 컬렉션이 없음
				res.sendError(412,"Please do request to the /CartSave.");
				
				return;
			}
			
			//--4----
			Set<String> cart = (Set<String>)obj;
			cart.forEach(p -> log.info(p.toString()));
			
			//--5----
			res.setCharacterEncoding("utf8");
			res.setContentType("text/html; charset=utf8");
			
			@Cleanup PrintWriter out = res.getWriter();
			
			cart.forEach(p-> out.println("<p>You prefered product: %s</p>".formatted(p)));
			
			out.flush();
			
		}catch(Throwable t) {
			throw new IOException(t);
		}
		
	}// service

	
}// end class
