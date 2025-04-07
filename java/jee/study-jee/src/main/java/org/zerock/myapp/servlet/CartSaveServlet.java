package org.zerock.myapp.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serial;
import java.util.HashSet;
import java.util.Objects;
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

@WebServlet( name = "CartSaveServlet", urlPatterns = "/CartSave")
public class CartSaveServlet extends HttpServlet {
	@Serial private static final long serialVersionUID = 1L;
	private static final String CART_KEY = "_PRODUCTS_";
	

	@SuppressWarnings("unchecked")
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		log.info("service({},{}) invoked", req.getClass(), res.getClass());
		
//		HTTP status code : 500 for test
//		if(true) throw new NullPointerException("TEST");
		
		try {
			// 요청처리
			//------
			//--1----
			// Create a HTTP session for web browser sending first request.
			HttpSession session = req.getSession();						// == req.getSesion(true) 
			log.info("\t + HTTP session id : {}", session.getId());
			
			Objects.requireNonNull(session);
			
			//--2----
			// 장바구니 역할을 수행할 리스트를 세션금고상자(=Session Scope)에서 얻어내서
			// Step1. 리스트(장바구니)가 없으면 => 새로 만들어서, 금고 상자(=Session Scope)에 넣고
			// Step2. 리스트(장바구니)가 있으면 => 이 리스트에다 전송된 새로운 아이템 저장
			String product = req.getParameter("product");
			log.info("\t + product: {}", product);
			if(product == null || "".equals(product)) {
				res.sendError(400, "No request parameter named 'product' found.");		
				
				return;
			}
			
			Object obj = session.getAttribute(CART_KEY);
			
			Set<String> cart = (Set<String>)obj;
			
			cart = Objects.requireNonNullElse(cart, new HashSet<String>());
			
			// 숙제: 장바구니 역할을 하는 셋을 공유 영역(Session Scope)에 오직 한번만 바인딩하는 방법은?
			session.setAttribute(CART_KEY, cart);	// Binding -> Repeated Re-Binding
			cart.add(product);
			
			
			
//			List<String> cart = (List<String>)obj;
//			cart = Objects.requireNonNullElse(cart, new Vector<String>() );
//			session.setAttribute(CART_KEY, cart);
//			
//			cart.remove(product);
//			cart.add(product);					// Add new item to the list			
			
			// 응답처리
			//------
			// 이러한 boilerplate code 라고 한다 -> 필터의 전처리에서 해주면 깔끔
			res.setCharacterEncoding("utf8");
			res.setContentType("text/html; charset=utf8");
			
			@Cleanup PrintWriter out = res.getWriter();
			
			out.println("<h3>장바구니에 새로운 아이템이 저장되었습니다.</h3>");
			out.flush();
			
		}catch(Throwable t) {
			throw new IOException(t);
		}
		
	}// service

	
}// end class
